/**
 * @(#)HandleXml.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:JavaSyncServer
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

public interface HandleXml 
{
	/**
	 *  ���ܿͻ��˴�����������xml�ַ�����������зֽ�
	 * @param xml xml�ַ�������
	 * @return SendBeanʵ��
	 */
	public SendBean parseCallXml(String xml);
	
	/**
	 * ���ܿͻ��˴�����������xml�ַ�����������зֽ�
	 * @param xml xml��ʽ����
	 * @return SendBeanʵ��
	 */
	public SendBean parseStartEndXml(String xml);
	
	/**
	 * ���÷������ݵķ�������
	 * @param type
	 */
	public void setType(String type);
	
	/**
	 * �Ѻ��пͻ��˷����ĸ�������ת���ɶ�Ӧ��xml�ַ���
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̵��õĶ���ķ���
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ
	 * @param redMethod Զ����Ϣ���ص��ø÷���
	 */
	public void setProperty(String serverName,String method,String returnName,String redMethod);
	
	/**
	 * ���ø�xml�ַ�����property���Ե�ֵΪnoting.
	 */
	public void setProperty();
	
	/**
	 * �����ַ�������
	 * @param value
	 */
	public void setParam(String value);
	
	/**
	 * �������Ͳ���
	 * @param value
	 */
	public void setParam(int value);
	
	/**
	 * ����˫���Ȳ���
	 * @param value
	 */
	public void setParam(double value);
	
	/**
	 * ���ò����Ͳ���
	 * @param value
	 */
	public void setParam(boolean value);
	
	/**
	 * ����flash�������
	 * @param value
	 */
	public void setParam(FlashObject value);
	
	/**
	 * ����flash�������
	 * @param value
	 */
	public void setParam(Map value);
	
	/**
	 * ���ö�������
	 * @param values
	 */
	public void setParam(List values);
	
	/**
	 * �����ַ�������
	 * @param values
	 */
	public void setParams(String[] value);
	
	/**
	 * ������������
	 * @param values
	 */
	public void setParams(int[] value);
	
	/**
	 * ����˫��������
	 * @param values
	 */
	public void setParams(double[] value);
	
	/**
	 * ��ȡ���ɹ涨��ʽ��xml�ļ�
	 * @return xml��ʽ���ַ���
	 */
	public String getXml();
}
