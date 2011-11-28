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
 * <p>FlashSyncServer框架的最大异常，是所有FlashSyncServer异常的父类</p>
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
