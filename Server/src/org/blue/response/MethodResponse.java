/**
 * @(#)MethodResponse.java
 * 
 * @author  soda  E-mail:sujun10@21cn.com
 * @version  1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:JavaSyncServer
 * <br>Date:2007.1
 */
package org.blue.response;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blue.xml.HandleXml;
import org.blue.xml.XMLFactory;
import org.blue.bean.SendBean;
import org.blue.remote.Client;
import org.blue.remote.FlashObject;
import org.blue.global.SystemCommon;

/**
 * 客户端调用服务端方法时，会响应该对象方法，服务器内执对象
 * @author soda
 *
 */
public class MethodResponse 
{
	private HandleXml handXml;
	private SendBean bean;
	private SystemCommon system;
	
	public MethodResponse()
	{
		handXml = XMLFactory.getHandleXml();
		bean = new SendBean();
		system = SystemCommon.getSystemCommon();
	}
	
	/**
	 * 
	 * @param client 客户端信息封装对象
	 * @param xml 客户端传过来的字符串信息
	 */
	public void response(Client client,String xml)
	{
		bean.clear();
		bean = handXml.parseCallXml(xml);
		try
		{
			//开始执行反射机制，调用对应对象的方法
			//System.out.println("bean.getServerName():" + bean.getServerName());
			Class objClass = system.getResponseClass(bean.getServerName());
			List params = bean.getParams();
			List paramsType = bean.getParamsTypes();
			int number = params.size();
			Class[] cses = null;
			Object[] objs = null;
			//如果有参数传过来
			if(number != 0)
			{
				cses = new Class[number];
				for(int i = 0; i < number; i++)
				{
					String temp = (String)paramsType.get(i);
					cses[i] = system.getDataClass(temp);
					//System.out.println("cses[i]==========" + cses[i]);
				}
				objs = new Object[number];
				for(int i = 0; i < number; i++)
				{
					objs[i] = params.get(i);
				}
			}
			//System.out.println("11111========================");
			//System.out.println("objClass:" + objClass);
			//System.out.println("bean.getMethod():" + bean.getMethod());
			Method method = objClass.getMethod(bean.getMethod(), cses);
			//System.out.println("22222========================");
			Object obj = objClass.newInstance();
			//System.out.println("333333========================");
			Object objss = method.invoke(obj, objs);
			//System.out.println("objss========================" + objss);
			//如果结果不为空，把结果发送给客户端
			if(objss != null)
			{
				//呼叫客户端，把结果返回给客户端对应的对象的方法
				client.call(bean.getReturnName(),"onResult");

				if(objss.getClass() == String.class)
				{
					client.setParam((String)objss);
				}
				else if(objss.getClass() == Double.class)
				{
					double temp = ((Double)objss).doubleValue();
					client.setParam(temp);
				}
				else if(objss.getClass() == Boolean.class)
				{
					boolean temp = ((Boolean)objss).booleanValue();
					client.setParam(temp);
				}
				else if(objss.getClass() == FlashObject.class)
				{
					FlashObject temp = (FlashObject)objss;
					client.setParam(temp);
				}
				else if(objss.getClass() == HashMap.class)
				{
					//System.out.println("进行封装map");
					Map temp = (Map)objss;
					client.setParam(temp);
				}
				client.send();
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
			//当远程调用方法异常时，呼叫客户端的异常响应方法
			client.call(bean.getReturnName(),"onFault");
			client.setParam(e.toString());
			client.send();
			System.out.println("出现业务异常！" + e);
		}
	}
}