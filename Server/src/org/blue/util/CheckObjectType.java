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
	 * ��ȡCheckObjectTypeʵ��
	 * @return CheckObjectTypeʵ��
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
	 * �ж�һ�������Ƿ�������һ�����ʵ��
	 * @param obj ��Ҫ�жϵĶ���
	 * @param type ��Ҫ�ȽϵĶ����class
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
