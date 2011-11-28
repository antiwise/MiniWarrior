/**
 * @(#)CheckObjectType.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.util;

public class CheckObjectType 
{
	private static CheckObjectType check;
	private CheckObjectType(){}
	
	/**
	 * 获取CheckObjectType实例
	 * @return CheckObjectType实例
	 */
	public static CheckObjectType getCheckObjectType()
	{
		if(check == null)
		{
			check = new CheckObjectType();
		}
		return check;
	}
	
	/**
	 * 判断一个对象是否属于另一个类的实例
	 * @param obj 需要判断的对象
	 * @param type 需要比较的对象的class
	 * @return true/false
	 */
	public boolean isType(Object obj,Class type)
	{
		if(obj.getClass() == type)
		{
			return true;
		}
		return false;
	}
}
