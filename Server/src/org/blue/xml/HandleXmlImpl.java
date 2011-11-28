/**
 * @(#)HandleXml.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.xml;
import java.util.List;
import java.util.Map;
import org.blue.remote.FlashObject;
import org.blue.bean.SendBean;
/**
 * ����flash��java֮����xml��ʽ���������
 * �Ѹ��ַ���ת���ɹ���Ӧ�Ķ���.
 * �ýӿ�������ʵ��:
 * @see HandleXmlStringImpl
 * 
 * @author soda
 *
 */

class HandleXmlImpl implements HandleXml
{
	private ParseXml parseXml;
	private SwitchXml switchXml;
	
	public HandleXmlImpl()
	{
		parseXml = new ParseXml();
		switchXml = new SwitchXml();
	}
	/**
	 *  ���ܿͻ��˴�����������xml�ַ�����������зֽ�
	 * @param xml xml�ַ�������
	 * @return SendBeanʵ��
	 */
	public SendBean parseCallXml(String xml)
	{
		return parseXml.parseSendXml(xml);
	}
	
	/**
	 * ���ܿͻ��˴�����������xml�ַ�����������зֽ�
	 * @param xml xml��ʽ����
	 * @return SendBeanʵ��
	 */
	public SendBean parseStartEndXml(String xml)
	{
		return parseXml.parseStartEndXml(xml);
	}
	
	/**
	 * ���÷������ݵķ�������
	 * @param type
	 */
	public void setType(String type)
	{
		switchXml.setType(type);
	}
	
	/**
	 * �Ѻ��пͻ��˷����ĸ�������ת���ɶ�Ӧ��xml�ַ���
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̵��õĶ���ķ���
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ
	 * @param redMethod Զ����Ϣ���ص��ø÷���
	 */
	public void setProperty(String serverName,String method,String returnName,String redMethod)
	{
		switchXml.setProperty(serverName, method, returnName, redMethod);
	}
	
	/**
	 * ���ø�xml�ַ�����property���Ե�ֵΪnoting.
	 */
	public void setProperty()
	{
		switchXml.setProperty();
	}
	
	/**
	 * �����ַ�������
	 * @param value
	 */
	public void setParam(String value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * �������Ͳ���
	 * @param value
	 */
	public void setParam(int value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����˫���Ȳ���
	 * @param value
	 */
	public void setParam(double value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ���ò����Ͳ���
	 * @param value
	 */
	public void setParam(boolean value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����flash�������
	 * @param value
	 */
	public void setParam(FlashObject value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����flash�������
	 * @param value
	 */
	public void setParam(Map value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ���ö�������
	 * @param values
	 */
	public void setParam(List value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * �����ַ�������
	 * @param values
	 */
	public void setParams(String[] value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ������������
	 * @param values
	 */
	public void setParams(int[] value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ����˫��������
	 * @param values
	 */
	public void setParams(double[] value)
	{
		switchXml.setParam(value);
	}
	
	/**
	 * ��ȡ���ɹ涨��ʽ��xml�ļ�
	 * @return xml��ʽ���ַ���
	 */
	public String getXml()
	{
		return switchXml.getXml();
	}
}
