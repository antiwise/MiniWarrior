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
 * ϵͳ�����࣬��Ҫ��������ͻ��˵�½�������˳�ʱ����Ӧ��Ӧ���¼�
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
	 * ��ȡClientResponse��ʵ��
	 * @return ClientResponse��ʵ��
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
	 * �ͻ�������ʱ���ø÷���
	 * @param client �ͻ��˶���
	 * @param xml �ͻ��˵�½ʱ��������xml��ʽ����
	 * @return true/false ���ӳɹ�/ʧ��
	 */
	public void onConect(Client client, String xml)
	{
		Object obj = system.getStartEndObject();
		Object result = null;
		try
		{
			SendBean bean = handXml.parseStartEndXml(xml);
			Class cl = obj.getClass();
			//ȡ����������
			List params = bean.getParams();
			List paramsType = bean.getParamsTypes();
			//��������Ϊ��ʱ
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
				//�û�û�з��Ͳ�����Ĭ���и�client����
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