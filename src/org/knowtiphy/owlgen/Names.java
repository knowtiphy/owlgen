package org.knowtiphy.owlgen;

import org.semanticweb.owlapi.model.OWLProperty;

public class Names
{
	private static String lowerFirst(String str)
	{
		if (str == null || str.length() == 0)
		{
			return "";
		}
		else if (str.length() == 1)
		{
			return str.toLowerCase();
		}
		else
		{
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	private static String upperFirst(String str)
	{
		if (str == null || str.length() == 0)
		{
			return "";
		}
		else if (str.length() == 1)
		{
			return str.toUpperCase();
		}
		else

		{
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	private static String extractPropName(OWLProperty prop)
	{
		String raw = prop.getIRI().getFragment();
		if (raw.startsWith("is"))
		{
			return raw.substring(2);
		}
		else if (raw.startsWith("has"))
		{
			return raw.substring(3);
		}
		else
		{
			return raw;
		}
	}

	public static String propertyName(OWLProperty prop)
	{
		return lowerFirst(extractPropName(prop));
	}
}
