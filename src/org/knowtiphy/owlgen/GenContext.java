package org.knowtiphy.owlgen;

import java.io.PrintWriter;

public class GenContext
{
	String targetDir;
	String packageName;

	public GenContext(String targetDir, String packageName)
	{
		this.targetDir = targetDir;
		this.packageName = packageName;
	}

	public PrintWriter getPrintWriter(String clsName)
	{
		//return new PrintWriter(new FileWriter(targetDir + "/" + clsName));
		return new PrintWriter(System.out);
	}

}