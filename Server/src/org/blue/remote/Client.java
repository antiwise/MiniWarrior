/**
 * @(#)Client.java
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
 *<p>�ͻ��˶��󣬰����ͻ��˵Ļ��������Լ��������ܣ�ϵͳ�ĺ��Ľӿ�</p>
 *<p>������ʵ����:
 * @see ClientImpl
 * @author soda
 */


public interface Client 
{
	/**
	 * ���øÿͻ��˶����Ƿ��ڿ���
	 * @param flag
	 */
	public void setFlag(boolean flag);
	
	/**
	 * ��ȡ�ÿͻ��˶�������
	 * @return ture/false �Ƿ����
	 */
	public boolean getFlag();
	
	/**
	 * ����client��Ψһ��ʶ
	 * @param id Ψһ��ʶ
	 */
	public void setId(String id);
	
	/**
	 * ����clientc������
	 * @param name ����
	 */
	public void setName(String name);
	
	/**
	 * ����clientc��PrintWriter
	 * @param send �û���PrintWriter
	 */
	public void setPrintWriter(PrintWriter send);
	
	/**
	 * ��ȡclient��Ψһ��ʶ
	 * @return client��Ψһ��ʶ
	 */
	public String getId();
	
	/**
	 * ��ȡclient������
	 */
	public String getName();
	
	/**
	 * Զ�̺��пͻ���Ĭ�ϵĶ���(���Ӷ���)�ķ�����û�в���û�з���ֵ
	 * @param method �ͻ���Ĭ�ϵ�Զ�̺��з�����
	 */
	public void call(String method);
	
	/**
	 * ���пͻ�ָ���Ķ���ķ���
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̺��еķ�����
	 */
	public void call(String serverName,String method);
	
	/**
	 * ���пͻ�ָ���Ķ����Ĭ�Ϸ�����onResult������
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̵��õķ�����
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ
	 */
	public void call(String serverName,String method,Object resultObj);
	
	/**
	 * ���пͻ���Ĭ�ϵĶ���ķ���������з���ֵ��������Ӧ�ĵط�
	 * @param method ��ҪԶ�̵��õķ�����
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ��
	 * @param redMethod Զ����Ϣ���ص��ø÷��������Ϊnull���Ĭ�ϵ���onResult����
	 */
	public void call(String method,Object resultObj,String redMethod);
	
	/**
	 * ���пͻ�ָ���Ķ���ķ���������з���ֵ��������Ӧ�ĵط�
	 * @param serverName ��ҪԶ�̵��õĶ���ı�ʶ
	 * @param method ��ҪԶ�̵��õĶ���ķ���
	 * @param resultObj ���Զ�̷����з���ֵ���������������ķ�����Ӧ��
	 * @param redMethod Զ����Ϣ���ص��ø÷��������Ϊnull���Ĭ�ϵ���onResult����
	 */
	public void call(String serverName,String method,Object resultObj,String redMethod);
	
	/**
	 * Զ�̷������в����Զ����������ʽ����
	 * ����ʱ��֧�֣�
	 * @param vaules ������������
	 */
	public void setParams(List vaules);
	
	/**
	 * �����ַ����Ĳ���
	 * @param value �ַ���
	 */
	public void setParam(String value);
	
	/**
	 * �������͵Ĳ���
	 * @param value ����
	 */
	public void setParam(int value);
	
	/**
	 * ����˫���ȵĲ���
	 * @param value ˫����
	 */
	public void setParam(double value);
	
	/**
	 * ���ݲ���ֵ�Ĳ���
	 * @param value ����ֵ
	 */
	public void setParam(boolean value);
	
	/**
	 * ����List(����)�Ĳ���
	 * @param value List(����)
	 */
	public void setParam(List value);
	
	/**
	 * ����FlashObject�Ĳ���
	 * @param value FlashObject����ʵ��
	 */
	public void setParam(FlashObject value);
	
	/**
	 * ����FlashObject�Ĳ���
	 * @param value FlashObject����ʵ��
	 */
	public void setParam(Map value);
	
	/**
	 * �����ַ�������
	 * @param value
	 */
	public void setParam(String[] value);
	/**
	 * ����˫���ȴ�����
	 * @param value
	 */
	public void setParam(double[] value);

	
	/**
	 * ����xml�ַ������ͻ���
	 */
	public void send();
	
	/**
	 * ���ոÿͻ��˶��󣬲����������ݳ�ʼ
	 */
	public void clear();
	
}
