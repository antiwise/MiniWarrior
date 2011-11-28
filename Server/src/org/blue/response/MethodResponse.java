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
 * �ͻ��˵��÷���˷���ʱ������Ӧ�ö��󷽷�����������ִ����
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
	 * @param client �ͻ�����Ϣ��װ����
	 * @param xml �ͻ��˴��������ַ�����Ϣ
	 */
	public void response(Client client,String xml)
	{
		bean.clear();
		bean = handXml.parseCallXml(xml);
		try
		{
			//��ʼִ�з�����ƣ����ö�Ӧ����ķ���
			//System.out.println("bean.getServerName():" + bean.getServerName());
			Class objClass = system.getResponseClass(bean.getServerName());
			List params = bean.getParams();
			List paramsType = bean.getParamsTypes();
			int number = params.size();
			Class[] cses = null;
			Object[] objs = null;
			//����в���������
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
			//��������Ϊ�գ��ѽ�����͸��ͻ���
			if(objss != null)
			{
				//���пͻ��ˣ��ѽ�����ظ��ͻ��˶�Ӧ�Ķ���ķ���
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
					//System.out.println("���з�װmap");
					Map temp = (Map)objss;
					client.setParam(temp);
				}
				client.send();
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
			//��Զ�̵��÷����쳣ʱ�����пͻ��˵��쳣��Ӧ����
			client.call(bean.getReturnName(),"onFault");
			client.setParam(e.toString());
			client.send();
			System.out.println("����ҵ���쳣��" + e);
		}
	}
}