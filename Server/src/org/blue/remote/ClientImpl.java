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
 *<p>�ͻ��˶��󣬰����ͻ��˵Ļ��������Լ��������ܣ�ϵͳ�ĺ�����</p>
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
	 * ���øÿͻ��˶����Ƿ��ڿ���
	 * @param flag
	 */
	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}
	
	/**
	 * ��ȡ�ÿͻ��˶�������
	 * @return ture/false �Ƿ����
	 */
	public boolean getFlag()
	{
		return flag;
	}
	
	/**
	 * ����client��Ψһ��ʶ
	 * @param id Ψһ��ʶ
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 * ����clientc������
	 * @param name ����
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * ����clientc��PrintWriter
	 * @param send �û���PrintWriter
	 */
	public void setPrintWriter(PrintWriter send)
	{
		this.send = send;
	}
	
	/**
	 * ��ȡclient��Ψһ��ʶ
	 * @return client��Ψһ��ʶ
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * ��ȡclient������
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Զ�̺��пͻ���Ĭ�ϵĶ���(���Ӷ���)�ķ�����û�в���û�з���ֵ
	 * @param method �ͻ���Ĭ�ϵ�Զ�̺��з�����
	 */
	public void call(String method)
	{
		switchXml.setProperty(null, method, null, null);
	}
	
	/**
	 * ���пͻ�ָ���Ķ���ķ���
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̺��еķ�����
	 */
	public void call(String serverName,String method)
	{
		switchXml.setProperty(serverName, method, null, null);
	}
	
	/**
	 * ���пͻ�ָ���Ķ����Ĭ�Ϸ�����onResult������
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̵��õķ�����
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ
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
	 * ���пͻ���Ĭ�ϵĶ���ķ���������з���ֵ��������Ӧ�ĵط�
	 * @param method ��ҪԶ�̵��õķ�����
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ��
	 * @param redMethod Զ����Ϣ���ص��ø÷��������Ϊnull���Ĭ�ϵ���onResult����
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
	 * ���пͻ�ָ���Ķ���ķ���������з���ֵ��������Ӧ�ĵط�
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̵��õĶ���ķ���
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ��
	 * @param redMethod Զ����Ϣ���ص��ø÷��������Ϊnull���Ĭ�ϵ���onResult����
	 */
	public void call(String serverName,String method,Object resultObj,String redMethod)
	{
		String className = StringTools.getClassName(resultObj);
		switchXml.setProperty(serverName, method, className, redMethod);
	}
	
	/**
	 * Զ�̷������в����Զ����������ʽ����
	 * ����ʱ��֧�֣�
	 * @param vaules ������������
	 */
	public void setParams(List vaules)
	{
		switchXml.setParams(vaules);
	}
	
	/**
	 * �����ַ����Ĳ���
	 * @param value �ַ���
	 */
	public void setParam(String value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * �������͵Ĳ���
	 * @param value ����
	 */
	public void setParam(int value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����˫���ȵĲ���
	 * @param value ˫����
	 */
	public void setParam(double value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ���ݲ���ֵ�Ĳ���
	 * @param value ����ֵ
	 */
	public void setParam(boolean value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����List(����)�Ĳ���
	 * @param value List(����)
	 */
	public void setParam(List value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����FlashObject�Ĳ���
	 * @param value FlashObject����ʵ��
	 */
	public void setParam(FlashObject value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����FlashObject�Ĳ���
	 * @param value FlashObject����ʵ��
	 */
	public void setParam(Map value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * �����ַ�������
	 * @param value
	 */
	public void setParam(String[] value)
	{
		switchXml.setParam(value);
	}
	/**
	 * ����˫���ȴ�����
	 * @param value
	 */
	public void setParam(double[] value)
	{
		switchXml.setParam(value);
	}

	/**
	 * ����xml�ַ������ͻ���
	 */
	public void send()
	{
		String xml = switchXml.getXml();
		send.print(xml + "\0");
		send.flush();
	}
	
	/**
	 * ���ոÿͻ��˶��󣬲����������ݳ�ʼ
	 */
	public void clear()
	{
		flag = true;
		id = null;
		name = null;
		send = null;
	}
	
}
