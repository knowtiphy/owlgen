package org.knowtiphy.owlgen;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class OWLUtils
{

    public static boolean hasProperty(OWLContext context, OWLClass cls, OWLObjectPropertyExpression prop)
    {
        OWLClassExpression restriction = context.dataFactory.getOWLObjectSomeValuesFrom(prop, context.dataFactory.getOWLThing());
        OWLClassExpression complement = context.dataFactory.getOWLObjectComplementOf(restriction);
        OWLClassExpression intersection = context.dataFactory.getOWLObjectIntersectionOf(cls, complement);
        return !context.reasoner.isSatisfiable(intersection);
    }

    public static boolean hasProperty(OWLContext context, OWLClass cls,
            OWLDataPropertyExpression prop, OWL2Datatype dataType)
    {
        OWLClassExpression restriction = context.dataFactory.getOWLDataSomeValuesFrom(prop, dataType);
        OWLClassExpression complement = context.dataFactory.getOWLObjectComplementOf(restriction);
        OWLClassExpression intersection = context.dataFactory.getOWLObjectIntersectionOf(cls, complement);
        return !context.reasoner.isSatisfiable(intersection);
    }

    public static boolean hasPropertyUnique(OWLContext context, OWLClass cls,
            OWLDataPropertyExpression prop, OWL2Datatype dataType)
    {
        OWLClassExpression restriction = context.dataFactory.getOWLDataExactCardinality(1, prop, dataType);
        OWLClassExpression complement = context.dataFactory.getOWLObjectComplementOf(restriction);
        OWLClassExpression intersection = context.dataFactory.getOWLObjectIntersectionOf(cls, complement);
        return !context.reasoner.isSatisfiable(intersection);
    }

    public static boolean overlaps(OWLContext context, OWLClass cls1, OWLClass cls2)
    {
        OWLClassExpression intersection = context.dataFactory.getOWLObjectIntersectionOf(cls1, cls2);
        return context.reasoner.isSatisfiable(intersection);
    }

    public static boolean overlaps(OWLContext context, OWLDataProperty dProp, OWL2Datatype dataType)
    {
        return context.ontology.dataPropertyRangeAxioms(dProp).anyMatch(
                p -> p.getRange().asOWLDatatype().getIRI().equals(dataType.getIRI()));
    }

    public static boolean isExtremal(OWLDataProperty prop)
    {
        return prop.isOWLBottomDataProperty() || prop.isOWLTopDataProperty();
    }

    public static boolean isExtremal(OWLClass cls)
    {
        return cls.isBottomEntity() || cls.isTopEntity();
    }
}
