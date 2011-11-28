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
 * 用户工具类
 * 只能注册一个客户端连接和意外断开的实现类，多个注册，只算最后一个
 */

public class Application  
{
	private static Map map = new HashMap();
	/**
	 * 注册客户端连接和意外断开时的监听类，以接受客户端的开始和结束信息
	 * @param obj StartEndListenter实现类
	 */
	public static void addStartEndListenter(StartEndListenter obj)
	{
		SystemCommon.getSystemCommon().addStartEndListenter(obj);
	}
	
	/**
	 * 注册响应客户端调用方法的类
	 * @param obj 响应客户端调的对象
	 */
	public static void regeditClass(String key,Class clas)
	{
		SystemCommon.getSystemCommon().regeditClass(key, clas);
	}
	
	/**
	 * 设置该是否允许该客户端连接服务器
	 * @param f true/false
	 */
	public static void setStart(boolean f)
	{
		SystemCommon.getSystemCommon().setStart(f);
	}
	
	/**
	 * 设置该是否允许该客户端连接服务器,并附带服务器返回的信息
	 * @param f true/false
	 * @param m 服务器返回给客户端的信息
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

