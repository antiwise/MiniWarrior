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
 * </p>SysncException的子类，是响应客户端异常。当系统响应客户端调用的一个
 * 方法时，有可能抛出该异常，该异常为必须显式捕捉或者抛出.</p>
 * 继承自
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
