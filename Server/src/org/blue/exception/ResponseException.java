/**
 * @(#)ParamCastExcpetion.java
 * 
 * @author soda  E-mail:sujun10@21cn.com
 * @version 1.0
 * <br>Copyright (C), 2007 soda.C
 * <br>This program is protected by copyright laws.
 * <br>Program Name:FlashSyncServer
 * <br>Date:2007.1
 */
package org.blue.exception;
/**
 * </p>SysncException�����࣬����Ӧ�ͻ����쳣����ϵͳ��Ӧ�ͻ��˵��õ�һ��
 * ����ʱ���п����׳����쳣�����쳣Ϊ������ʽ��׽�����׳�.</p>
 * �̳���
 * @see SysncException
 * 
 * @author soda
 *
 */
public class ResponseException extends SysncException 
{
	public ResponseException()
	{
		super();
	}
	
	public ResponseException(String msg)
	{
		super(msg);
	}
}
