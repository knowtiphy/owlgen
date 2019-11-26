package org.knowtiphy.owlgen;

import org.semanticweb.owlapi.vocab.OWL2Datatype;

import java.util.HashMap;
import java.util.Map;

//  blah blah
public class DataTypes
{

    public static final Map<OWL2Datatype, String> dataTypes = new HashMap<>();

    static
    {
        dataTypes.put(OWL2Datatype.XSD_BOOLEAN, "Boolean");
        dataTypes.put(OWL2Datatype.XSD_DATE_TIME, "ZonedDateTime");
        dataTypes.put(OWL2Datatype.XSD_DATE_TIME_STAMP, "ZonedDateTime");
        dataTypes.put(OWL2Datatype.XSD_INT, "Integer");
        dataTypes.put(OWL2Datatype.XSD_STRING, "String");
    }

    public static final Map<OWL2Datatype, String> propertyDataTypes = new HashMap<>();

    static
    {
        propertyDataTypes.put(OWL2Datatype.XSD_BOOLEAN, "SimpleBooleanProperty");
        propertyDataTypes.put(OWL2Datatype.XSD_DATE_TIME, "SimpleObjectProperty<ZonedDateTime>");
        propertyDataTypes.put(OWL2Datatype.XSD_DATE_TIME_STAMP, "SimpleObjectProperty<ZonedDateTime>");
        propertyDataTypes.put(OWL2Datatype.XSD_INT, "SimpleIntegerProperty");
        propertyDataTypes.put(OWL2Datatype.XSD_STRING, "SimpleStringProperty");
    }
}
