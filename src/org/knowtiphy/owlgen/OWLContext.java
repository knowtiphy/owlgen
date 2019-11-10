package org.knowtiphy.owlgen;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.HermiT.Reasoner;

import java.io.File;

//
public class OWLContext
{

    OWLOntology ontology;
    OWLReasoner reasoner;
    OWLDataFactory dataFactory;

    public OWLContext(OWLOntologyManager manager, File file) throws OWLOntologyCreationException
    {
        this.ontology = manager.loadOntologyFromOntologyDocument(file);
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
        dataFactory = manager.getOWLDataFactory();
    }
}
