/**
 * @(#)StringTools.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.util;

public class StringTools 
{
	/**
	 * 根据类的字符串提取出类名
	 * @param str
	 * @return 类名字符串
	 */
	public static String getClassName(Object obj)
	{
		String str = obj.toString();
		//先搜索该字符串是否有小数点，以确定该类是否带包名
		int index = str.indexOf(".") + 1;
		int last = str.indexOf("@");
		String temp = str.substring(index,last);
		return temp;
	}
}
