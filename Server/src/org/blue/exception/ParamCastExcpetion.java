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
 * </p>SysncException�����࣬�ǲ���ת���쳣����ϵͳ���Զ�һ������ת���ɶ�Ӧ����������ʱ
 * �п����׳����쳣�����쳣Ϊ������ʽ��׽�����׳�.</p>
 * �̳���
 * @see SysncException
 * 
 * @author soda
 *
 */
public class ParamCastExcpetion extends SysncException 
{
	public ParamCastExcpetion()
	{
		super();
	}
	
	public ParamCastExcpetion(String msg)
	{
		super(msg);
	}
}
