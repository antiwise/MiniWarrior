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
 * </p>SysncException的子类，是参数转换异常。当系统尝试对一个参数转换成对应的数据类型时
 * 有可能抛出该异常，该异常为必须显式捕捉或者抛出.</p>
 * 继承自
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
