/**
 * @(#)Sculpt.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;

public class Sculpt
{
	private int sculptId;
	private String sculptName;
	private int sculptType;

	
	public Sculpt() {

	}
	

	public Sculpt(int sculptId, String sculptName, int sculptType) {
		this.sculptId = sculptId;
		this.sculptName = sculptName;
		this.sculptType = sculptType;
	}


	public void setSculptId(int sculptId) 
	{
		this.sculptId = sculptId; 
	}

	public void setSculptName(String sculptName) 
	{
		this.sculptName = sculptName; 
	}

	public void setSculptType(int sculptType) 
	{
		this.sculptType = sculptType; 
	}

	public int getSculptId() 
	{
		return (this.sculptId); 
	}

	public String getSculptName() 
	{
		return (this.sculptName); 
	}

	public int getSculptType()
	{
		return (this.sculptType);
	}
}
