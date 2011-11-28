package org.game.vo;

public class UserVo {
	private String name;
	private String pass;
	public UserVo()
	{
	}
	public UserVo(String name,String pass)
	{
		this.name = name;
		this.pass = pass;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return pass;
	}
	public String getName()
	{
		return name;
	}
}
