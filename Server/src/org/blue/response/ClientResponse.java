/**
 * @(#)ClientResponse.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.response;

import java.util.List;
import java.lang.reflect.Method;

import org.blue.xml.HandleXml;
import org.blue.xml.XMLFactory;
import org.blue.bean.SendBean;
import org.blue.global.SystemCommon;
import org.blue.remote.Client;

/**
 * 系统核心类，主要用来处理客户端登陆和意外退出时，响应相应的事件
 * @author soda
 */
public class ClientResponse
{
	private static ClientResponse client;
	private HandleXml handXml;
	private SystemCommon system;
	
	private ClientResponse()
	{
		handXml = XMLFactory.getHandleXml();
		system = SystemCommon.getSystemCommon();
	}
	
	/**
	 * 获取ClientResponse的实例
	 * @return ClientResponse的实例
	 */
	public static ClientResponse getClientResponse()
	{
		if(client == null)
		{
			client = new ClientResponse();
		}
		return client;
	}
	
	/**    
	 * 客户端连接时调用该方法
	 * @param client 客户端对象
	 * @param xml 客户端登陆时传过来的xml格式数据
	 * @return true/false 连接成功/失败
	 */
	public void onConect(Client client, String xml)
	{
		Object obj = system.getStartEndObject();
		Object result = null;
		try
		{
			SendBean bean = handXml.parseStartEndXml(xml);
			Class cl = obj.getClass();
			//取出参数集合
			List params = bean.getParams();
			List paramsType = bean.getParamsTypes();
			//当参数不为空时
			if(params.size() != 0)
			{
				int number = params.size();
				Class[] cses = new Class[number + 1];
				Object[] objs = new Object[number + 1];
				cses[0] = Client.class;
				objs[0] = client;
				for(int i = 1; i <= number; i++)
				{
					String temp = (String)paramsType.get(i - 1);
					cses[i] = system.getDataClass(temp);
					objs[i] = params.get(i - 1);
				}
				Method method = cl.getMethod("onConnet", cses);
				result = method.invoke(obj, objs);
			}
			else
			{
				//用户没有发送参数，默认有个client参数
				Method method = cl.getMethod("onConnet", Client.class);
				Object[] objs = new Object[1];
				objs[0] = client;
				result = method.invoke(obj, objs);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}