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

package org.blue.exception;
/**
 * <p>FlashSyncServer��ܵ�����쳣��������FlashSyncServer�쳣�ĸ���</p>
 *
 */

public class SysncException extends Exception 
{
	public SysncException()
	{
		super();
	}
	
	public SysncException(final String msg)
	{
		super(msg);
	}
}
