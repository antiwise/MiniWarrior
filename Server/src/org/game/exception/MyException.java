package org.game.exception;



	/**
	 * @Author E-mail:icelong.w@gmail.com
	 */

public class MyException extends Exception
{
	private static final long serialVersionUID = 48L;
	public MyException()
	{
	}	
	public MyException(String msg)
	{
		super(msg);
	}
}
