/**
 * @(#)ClientImpl.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.remote;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.blue.remote.FlashObject;
import org.blue.xml.SwitchXml;
import org.blue.util.StringTools;
import org.blue.global.Application;

/**
 *<p>客户端对象，包含客户端的基本数据以及基本功能，系统的核心类</p>
 * @author soda
 */
class ClientImpl implements Client
{
	private boolean flag;
	private String id;
	private String name;
	private PrintWriter send;
	private SwitchXml switchXml;
	
	
	public ClientImpl()
	{
		switchXml = new SwitchXml();
		flag = false;
	}
	
	/**
	 * 设置该客户端对象是否处于空闲
	 * @param flag
	 */
	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}
	
	/**
	 * 获取该客户端对象的旗标
	 * @return ture/false 是否空闲
	 */
	public boolean getFlag()
	{
		return flag;
	}
	
	/**
	 * 设置client的唯一标识
	 * @param id 唯一标识
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 * 设置clientc的名称
	 * @param name 名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * 设置clientc的PrintWriter
	 * @param send 用户的PrintWriter
	 */
	public void setPrintWriter(PrintWriter send)
	{
		this.send = send;
	}
	
	/**
	 * 获取client的唯一标识
	 * @return client的唯一标识
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * 获取client的名字
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 远程呼叫客户端默认的对象(连接对象)的方法，没有参数没有返回值
	 * @param method 客户端默认的远程呼叫方法名
	 */
	public void call(String method)
	{
		switchXml.setProperty(null, method, null, null);
	}
	
	/**
	 * 呼叫客户指定的对象的方法
	 * @param serverName 需要远程调用的对象的标识
	 * @param method 需要远程呼叫的方法名
	 */
	public void call(String serverName,String method)
	{
		switchXml.setProperty(serverName, method, null, null);
	}
	
	/**
	 * 呼叫客户指定的对象的默认方法（onResult方法）
	 * @param serverName 需要远程调用的对象的标识
	 * @param method 需要远程调用的方法名
	 * @param resultObj 如果远程方法有返回值，则在这个对象里的方法响应
	 */
	public void call(String serverName,String method,Object resultObj)
	{
		String className = StringTools.getClassName(resultObj);
		String key = StringTools.getClassName(resultObj);
		System.out.println("call key:");
		Application.regeditClass(key,resultObj.getClass());
		switchXml.setProperty(serverName, method, className, null);
	}
	
	/**
	 * 呼叫客户端默认的对象的方法，如果有返回值则反馈到相应的地方
	 * @param method 需要远程调用的方法名
	 * @param resultObj 如果远程方法有返回值，则在这个对象里的方法响应。
	 * @param redMethod 远程信息返回调用该方法，如果为null则调默认的用onResult方法
	 */
	public void call(String method,Object resultObj,String redMethod)
	{
		String className = StringTools.getClassName(resultObj);
		String key = StringTools.getClassName(resultObj);
		System.out.println("call key:");
		Application.regeditClass(key,resultObj.getClass());
		switchXml.setProperty(null, method, className, redMethod);
	}
	
	/**
	 * 呼叫客户指定的对象的方法，如果有返回值则反馈到相应的地方
	 * @param serverName 需要远程调用的对象的标识
	 * @param method 需要远程调用的对象的方法
	 * @param resultObj 如果远程方法有返回值，则在这个对象里的方法响应。
	 * @param redMethod 远程信息返回调用该方法，如果为null则调默认的用onResult方法
	 */
	public void call(String serverName,String method,Object resultObj,String redMethod)
	{
		String className = StringTools.getClassName(resultObj);
		switchXml.setProperty(serverName, method, className, redMethod);
	}
	
	/**
	 * 远程方法所有参数以对象数组的形式传递
	 * （暂时不支持）
	 * @param vaules 参数对象数组
	 */
	public void setParams(List vaules)
	{
		switchXml.setParams(vaules);
	}
	
	/**
	 * 传递字符串的参数
	 * @param value 字符串
	 */
	public void setParam(String value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * 传递整型的参数
	 * @param value 整型
	 */
	public void setParam(int value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * 传递双精度的参数
	 * @param value 双精度
	 */
	public void setParam(double value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * 传递布尔值的参数
	 * @param value 布尔值
	 */
	public void setParam(boolean value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * 传递List(数组)的参数
	 * @param value List(数组)
	 */
	public void setParam(List value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * 传递FlashObject的参数
	 * @param value FlashObject对象实例
	 */
	public void setParam(FlashObject value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * 传递FlashObject的参数
	 * @param value FlashObject对象实例
	 */
	public void setParam(Map value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * 设置字符串数组
	 * @param value
	 */
	public void setParam(String[] value)
	{
		switchXml.setParam(value);
	}
	/**
	 * 设置双精度串数组
	 * @param value
	 */
	public void setParam(double[] value)
	{
		switchXml.setParam(value);
	}

	/**
	 * 发送xml字符串到客户端
	 */
	public void send()
	{
		String xml = switchXml.getXml();
		send.print(xml + "\0");
		send.flush();
	}
	
	/**
	 * 回收该客户端对象，并把所有数据初始
	 */
	public void clear()
	{
		flag = true;
		id = null;
		name = null;
		send = null;
	}
	
}
