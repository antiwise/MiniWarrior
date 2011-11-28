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
 * 系统功用的类，属于系统内置类，系统核心类
 * 只能注册一个客户端连接和意外断开的实现类，多个注册，只算最后一个
 */

public class SystemCommon 
{
	//存放客户端调用类的class
	private Map classMap;
	//存放互调数据类型类的class
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
	 * 保存各基本数据类型的class对象
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
	 * 注册客户端连接和意外断开时的监听类，以接受客户端的开始和结束信息
	 * @param obj StartEndListenter实现类
	 */
	public void addStartEndListenter(StartEndListenter obj)
	{
		connectObject = obj;
	}
	
	/**
	 * 获取客户端连接和意外断开时的监听类
	 * @return StartEndListenter实现类实例
	 */
	public StartEndListenter getStartEndObject()
	{
		return connectObject;
	}
	
	/**
	 * 注册响应客户端调用方法的类
	 * @param obj 响应客户端调的对象
	 */
	public void regeditClass(String key,Class clas)
	{
		classMap.put(key,clas);
	}

	/**
	 * 获取响应客户端调用类的class
	 */
	public Class getResponseClass(String className)
	{
		return (Class)classMap.get(className);
	}
	
	/**
	 * 设置数据基本类型的class
	 * @param dataMap
	 */
	public  void setData(Map data)
	{
		dataMap = data;
	}
	
	/**
	 * 根据名称获取对应的数据类型的class
	 * @param name
	 * @return Class
	 */
	public  Class getDataClass(String name)
	{
		return (Class)dataMap.get(name);
	}
	
	/**
	 * 设置该是否允许该客户端连接服务器
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
	 * 设置该是否允许该客户端连接服务器,并附带服务器返回的信息
	 * @param f true/false
	 * @param m 服务器返回给客户端的信息
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

