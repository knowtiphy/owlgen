package org.knowtiphy.owlgen;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.reasoner.NodeSet;

import java.io.File;
import java.io.PrintWriter;

public class OwlGen
{

    private static void generateDeclarations(PrintWriter pw, ClsInfo clsInfo)
    {
        for (var pi : clsInfo.dataProps.values())
        {
            pw.format("\tprivate %s %s = new %s();\n", pi.type, pi.declarationName,
                    pi.unique ? pi.type : "FXCollections.observableArrayList");
        }
    }

    private static void generateGetters(PrintWriter pw, ClsInfo clsInfo)
    {
        for (var pi : clsInfo.dataProps.values())
        {
            pw.format("\tpublic %s %s%s()\n", pi.type, pi.declarationName, pi.unique ? "Property" : "");
            pw.println("\t{");
            pw.format("\t\treturn %s;\n", pi.declarationName);
            pw.println("\t}");
        }
    }

    private static void generateConstructor(PrintWriter pw, ClsInfo clsInfo)
    {
        pw.format("\tpublic %s(String id, IStorage storage)\n", clsInfo.clsName);
        pw.println("\t{");
        pw.println("\t\tsuper(id, storage);");
        for (var entry : clsInfo.dataProps.entrySet())
        {
            OWLDataProperty prop = entry.getKey();
            PropertyInfo pi = entry.getValue();
            pw.format("\t\tdeclareU(\"%s\", %s);\n", prop.getIRI(), pi.declarationName);
        }
        pw.println("\t}");
    }

//	init
//	{
//		declareU(Vocabulary.FROM, from, Functions.STMT_TO_EMAIL_ADDRESS)
//	}
    private static void generateClass(OWLContext context, GenContext genContext, OWLClass cls)
    {
        ClsInfo clsInfo = new ClsInfo(context, cls);
        PrintWriter pw = genContext.getPrintWriter(clsInfo.clsName);

        pw.format("package %s;\n\n", genContext.packageName);
        pw.format("class %s extends OWLPeer implements IOWLEntity\n", clsInfo.clsName);
        pw.println("{");

        generateDeclarations(pw, clsInfo);
        pw.println();
        generateConstructor(pw, clsInfo);
        pw.println();
        generateGetters(pw, clsInfo);
        pw.println("}");

        pw.flush();
    }

    public static void main(String[] args) throws OWLOntologyCreationException
    {
        String ontologyName = args[0];
        String targetDir = args[1];
        String packageName = args[2];

        GenContext genContext = new GenContext(targetDir, packageName);
        OWLContext context = new OWLContext(OWLManager.createOWLOntologyManager(), new File(ontologyName));

        NodeSet<OWLClass> nodes = context.reasoner.getSubClasses(context.dataFactory.getOWLThing());
        nodes.entities().forEach(cls ->
        {
            if (!OWLUtils.isExtremal(cls))
            {
                generateClass(context, genContext, cls);
            }
        });
    }
}
