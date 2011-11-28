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
	//�����ö���
	private String serverName;
	//�����ö���ķ���
	private String method;
	//���Է������ݷ���ʱ�Ľ��ܶ���
	private String returnName;
	//���Է������ݷ���ʱ�Ľ��ܶ���ķ���
	private String redMethod;
	//��������
	private List params;
	//�������ͼ���
	private List paramsTypes; 
	
	/**
	 * ���ñ����õķ������
	 * @param serverName ����ı�����Ψһ������
	 */
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}
	
	/**
	 * ��ȡ�����õķ���Ķ��������
	 * @return �����õķ���Ķ�����������ַ���
	 */
	public String getServerName()
	{
		return serverName;
	}
	
	/**
	 * ���ñ����õķ������ķ�����
	 * @param method ������
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}
	
	/**
	 * ��ȡ�����õķ���Ķ���ķ�����
	 * @return ������
	 */
	public String getMethod()
	{
		return method;
	}
	
	/**
	 * ���ý��ܷ������ݶ������û�У�����Ϊnull
	 * @param returnName���������
	 */
	public void setReturnName(String returnName)
	{
		this.returnName = returnName;
	}
	
	/**
	 * ��ȡ�������ݶ������û�У�����Ϊnull
	 * @return ������
	 */
	public String getReturnName()
	{
		return returnName;
	}
	
	/**
	 * ���ý��ܷ������ݶ���ķ��������û�У�����Ϊnull
	 * @param returnName���������
	 */
	public void setRedMethod(String redMethod)
	{
		this.redMethod = redMethod;
	}
	
	/**
	 * ��ȡ�������ݶ���ķ��������û�У�����Ϊnull
	 * @return ����ķ�����
	 */
	public String getRedMethod()
	{
		return redMethod;
	}
	/**
	 * �������еĲ���.
	 * @param params ��������.
	 */
	public void setParams(List params)
	{
		this.params = params;
	}
	
	/**
	 * �������еĲ������ͼ���
	 * @param paramsTypes ����
	 */
	public void setParamsTypes(List paramsTypes)
	{
		this.paramsTypes = paramsTypes;
	}
	
	/**
	 * �������еĲ���.
	 * @return ���ز�������.
	 */
	public List getParams()
	{
		return params;
	}
	
	/**
	 * �������еĲ�������.
	 * @return ���ز������ͼ���.
	 */
	public List getParamsTypes()
	{
		return paramsTypes;
	}
	
	/**
	 * �����������
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
