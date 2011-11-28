/**
 * @(#)SystemCommon.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.global;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.blue.remote.FlashObject;
import org.blue.response.StartEndListenter;
import org.blue.util.StringTools;
/**
 * ϵͳ���õ��࣬����ϵͳ�����࣬ϵͳ������
 * ֻ��ע��һ���ͻ������Ӻ�����Ͽ���ʵ���࣬���ע�ᣬֻ�����һ��
 */

public class SystemCommon 
{
	//��ſͻ��˵������class
	private Map classMap;
	//��Ż��������������class
	private Map dataMap;
	private StartEndListenter connectObject;
	private boolean flag;
	private String msg;
	private static SystemCommon system;
	
	private SystemCommon()
	{
		classMap = new HashMap();
		dataMap = new HashMap();
		instanceDataMap();
	}
	
	public static SystemCommon getSystemCommon()
	{
		if(system == null)
		{
			system = new SystemCommon();
		}
		return system;
	}

	/**
	 * ����������������͵�class����
	 * @return
	 */
	private void instanceDataMap()
	{
		dataMap.put("string",String.class);
		dataMap.put("int",int.class);
		dataMap.put("double",double.class);
		dataMap.put("boolean",boolean.class);
		dataMap.put("array",List.class);
		dataMap.put("object", FlashObject.class);
		dataMap.put("number", double.class);
		dataMap.put("string[]", String[].class);
		dataMap.put("double[]", double[].class);
		dataMap.put("int[]", int[].class);
	}
	
	/**
	 * ע��ͻ������Ӻ�����Ͽ�ʱ�ļ����࣬�Խ��ܿͻ��˵Ŀ�ʼ�ͽ�����Ϣ
	 * @param obj StartEndListenterʵ����
	 */
	public void addStartEndListenter(StartEndListenter obj)
	{
		connectObject = obj;
	}
	
	/**
	 * ��ȡ�ͻ������Ӻ�����Ͽ�ʱ�ļ�����
	 * @return StartEndListenterʵ����ʵ��
	 */
	public StartEndListenter getStartEndObject()
	{
		return connectObject;
	}
	
	/**
	 * ע����Ӧ�ͻ��˵��÷�������
	 * @param obj ��Ӧ�ͻ��˵��Ķ���
	 */
	public void regeditClass(String key,Class clas)
	{
		classMap.put(key,clas);
	}

	/**
	 * ��ȡ��Ӧ�ͻ��˵������class
	 */
	public Class getResponseClass(String className)
	{
		return (Class)classMap.get(className);
	}
	
	/**
	 * �������ݻ������͵�class
	 * @param dataMap
	 */
	public  void setData(Map data)
	{
		dataMap = data;
	}
	
	/**
	 * �������ƻ�ȡ��Ӧ���������͵�class
	 * @param name
	 * @return Class
	 */
	public  Class getDataClass(String name)
	{
		return (Class)dataMap.get(name);
	}
	
	/**
	 * ���ø��Ƿ�����ÿͻ������ӷ�����
	 * @param f true/false
	 */
	public void setStart(boolean f)
	{
		flag = f;
		if(flag)
		{
			msg = "connect success";
		}
		else
		{
			msg = "connect fail";
		}
	}
	
	/**
	 * ���ø��Ƿ�����ÿͻ������ӷ�����,���������������ص���Ϣ
	 * @param f true/false
	 * @param m ���������ظ��ͻ��˵���Ϣ
	 */
	public void setStart(boolean f,String m)
	{
		flag = f;
		//msg = "connect success";
		if(flag)
		{
			msg = "connect success";
		}
		else
		{
			msg = "connect fail";
		}
	}
	
	public boolean getStart()
	{
		return flag;
	}
	
	public String getMsg()
	{
		return msg;
	}
}

