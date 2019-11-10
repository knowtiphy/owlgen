package org.knowtiphy.owlgen;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import java.util.HashMap;
import java.util.Map;

public class ClsInfo
{

    public String clsName;
    public Map<OWLDataProperty, PropertyInfo> dataProps = new HashMap<>();
    public Map<OWLObjectProperty, PropertyInfo> objectProps = new HashMap<>();

    public ClsInfo(OWLContext context, OWLClass cls)
    {
        //	compute the class name
        clsName = cls.getIRI().getFragment();

        //	compute the data properties
        NodeSet<OWLDataProperty> allDProps = context.reasoner.getSubDataProperties(context.dataFactory.getOWLTopDataProperty());
        allDProps.entities().forEach(prop ->
        {
            if (!OWLUtils.isExtremal(prop))
            {
                for (OWL2Datatype dataType : DataTypes.dataTypes.keySet())
                {
                    if (OWLUtils.hasPropertyUnique(context, cls, prop, dataType))
                    {
                        dataProps.put(prop, new PropertyInfo(true, Names.propertyName(prop),
                                DataTypes.propertyDataTypes.get(dataType)));
                    } else if (OWLUtils.hasProperty(context, cls, prop, dataType))
                    {
                        dataProps.put(prop, new PropertyInfo(false, Names.propertyName(prop),
                                "ObservableList<" + DataTypes.dataTypes.get(dataType) + ">"));
                    }
                }
            }
        });

        //	compute the data properties
//		NodeSet<OWLObjectPropertyExpression> allOProps =
//				context.reasoner.getSubObjectProperties(context.dataFactory.getOWLTopObjectProperty());
//		allOProps.entities().forEach(prop -> {
//			if (!OWLUtils.isExtremal(prop))
//			{
//				for (OWL2Datatype dataType : DataTypes.uniqueDataTypes.keySet())
//				{
//					if (OWLUtils.hasPropertyUnique(context, cls, prop, dataType))
//					{
//						dataProps.put(prop, new PropertyInfo(true, Names.propertyName(prop),
//								DataTypes.collectionDataTypes.get(dataType)));
//					}
//					else if (OWLUtils.hasProperty(context, cls, prop, dataType))
//					{
//						dataProps.put(prop, new PropertyInfo(false, Names.propertyName(prop),
//								DataTypes.collectionDataTypes.get(dataType)));					}
//				}
//			}
//		});
    }
}