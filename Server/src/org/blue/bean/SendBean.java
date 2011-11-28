/**
 * @(#)SysncException.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.bean;

import java.util.List;

public class SendBean 
{
	//被调用对象
	private String serverName;
	//被调用对象的方法
	private String method;
	//当对方有数据返回时的接受对象
	private String returnName;
	//当对方有数据返回时的接受对象的方法
	private String redMethod;
	//参数集合
	private List params;
	//参数类型集合
	private List paramsTypes; 
	
	/**
	 * 设置被调用的服务对象
	 * @param serverName 对象的别名，唯一的命名
	 */
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}
	
	/**
	 * 获取被调用的服务的对象的命名
	 * @return 被调用的服务的对象的命名的字符串
	 */
	public String getServerName()
	{
		return serverName;
	}
	
	/**
	 * 设置被调用的服务对象的方法名
	 * @param method 方法名
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}
	
	/**
	 * 获取被调用的服务的对象的方法名
	 * @return 方法名
	 */
	public String getMethod()
	{
		return method;
	}
	
	/**
	 * 设置接受返回数据对象，如果没有，则设为null
	 * @param returnName　对象别名
	 */
	public void setReturnName(String returnName)
	{
		this.returnName = returnName;
	}
	
	/**
	 * 获取返回数据对象，如果没有，则设为null
	 * @return 对象名
	 */
	public String getReturnName()
	{
		return returnName;
	}
	
	/**
	 * 设置接受返回数据对象的方法，如果没有，则设为null
	 * @param returnName　对象别名
	 */
	public void setRedMethod(String redMethod)
	{
		this.redMethod = redMethod;
	}
	
	/**
	 * 获取返回数据对象的方法，如果没有，则设为null
	 * @return 对象的方法名
	 */
	public String getRedMethod()
	{
		return redMethod;
	}
	/**
	 * 设置所有的参数.
	 * @param params 参数集合.
	 */
	public void setParams(List params)
	{
		this.params = params;
	}
	
	/**
	 * 设置所有的参数类型集合
	 * @param paramsTypes 集合
	 */
	public void setParamsTypes(List paramsTypes)
	{
		this.paramsTypes = paramsTypes;
	}
	
	/**
	 * 返回所有的参数.
	 * @return 返回参数集合.
	 */
	public List getParams()
	{
		return params;
	}
	
	/**
	 * 返回所有的参数类型.
	 * @return 返回参数类型集合.
	 */
	public List getParamsTypes()
	{
		return paramsTypes;
	}
	
	/**
	 * 清空所有数据
	 */
	public void clear()
	{
		serverName = null;
		method = null;
		returnName = null;
		redMethod = null;
		params = null;
		paramsTypes = null; 
	}
}
