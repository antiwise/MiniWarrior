/**
 * @(#)XMLFactory.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.xml;

public class XMLFactory 
{
	private static XMLFactory factory;
	
	private XMLFactory(){}
	
	public static XMLFactory getXMLFactory()
	{
		if(factory == null)
		{
			factory = new XMLFactory();
		}
		return factory;
	}
	
	public static HandleXml getHandleXml()
	{
		return new HandleXmlImpl();
	}
}