/**
 * @(#)Application.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.global;

import java.util.Map;
import java.util.HashMap;
import org.blue.response.StartEndListenter;

/**
 * �û�������
 * ֻ��ע��һ���ͻ������Ӻ�����Ͽ���ʵ���࣬���ע�ᣬֻ�����һ��
 */

public class Application  
{
	private static Map map = new HashMap();
	/**
	 * ע��ͻ������Ӻ�����Ͽ�ʱ�ļ����࣬�Խ��ܿͻ��˵Ŀ�ʼ�ͽ�����Ϣ
	 * @param obj StartEndListenterʵ����
	 */
	public static void addStartEndListenter(StartEndListenter obj)
	{
		SystemCommon.getSystemCommon().addStartEndListenter(obj);
	}
	
	/**
	 * ע����Ӧ�ͻ��˵��÷�������
	 * @param obj ��Ӧ�ͻ��˵��Ķ���
	 */
	public static void regeditClass(String key,Class clas)
	{
		SystemCommon.getSystemCommon().regeditClass(key, clas);
	}
	
	/**
	 * ���ø��Ƿ�����ÿͻ������ӷ�����
	 * @param f true/false
	 */
	public static void setStart(boolean f)
	{
		SystemCommon.getSystemCommon().setStart(f);
	}
	
	/**
	 * ���ø��Ƿ�����ÿͻ������ӷ�����,���������������ص���Ϣ
	 * @param f true/false
	 * @param m ���������ظ��ͻ��˵���Ϣ
	 */
	public static void setStart(boolean f,String m)
	{
		SystemCommon.getSystemCommon().setStart(f, m);
	}
	
	public static void put(Object key,Object obj)
	{
		map.put(key,obj);
	}
	
	public static Object get(Object key)
	{
		return map.get(key);
	}
}

