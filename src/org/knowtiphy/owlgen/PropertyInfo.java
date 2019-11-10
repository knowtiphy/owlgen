package org.knowtiphy.owlgen;

public class PropertyInfo
{
	public boolean unique;
	public String declarationName;
	public String type;

	public PropertyInfo(boolean unique, String declarationName, String type)
	{
		assert type != null;
		this.unique = unique;
		this.declarationName = declarationName;
		this.type = type;
	}
}
