package org.blue.bean;

public class PropertyBean 
{
	//�����ö���
	private String serverName;
	//�����ö���ķ���
	private String method;
	//���Է������ݷ���ʱ�Ľ��ܶ���
	private String returnName;
	//���Է������ݷ���ʱ�Ľ��ܶ���ķ���
	private String redMethod;
	
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
	 * ��PropertyBeanʵ���������������գ���Ϊnull
	 */
	public void clear()
	{
		serverName = null;
		method = null;
		returnName = null;
		redMethod = null;
	}
}
