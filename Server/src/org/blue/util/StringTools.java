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
	 * ��������ַ�����ȡ������
	 * @param str
	 * @return �����ַ���
	 */
	public static String getClassName(Object obj)
	{
		String str = obj.toString();
		//���������ַ����Ƿ���С���㣬��ȷ�������Ƿ������
		int index = str.indexOf(".") + 1;
		int last = str.indexOf("@");
		String temp = str.substring(index,last);
		return temp;
	}
}
