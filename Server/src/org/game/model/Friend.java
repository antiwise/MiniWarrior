/**
 * @(#)Friend.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;

public class Friend 
{
	private int friendId;
	private User user;
	private User friend;
	public Friend()
	{
		
	}
	public Friend(int friendId)
	{
		this.friendId = friendId;
	}

	
	public void setFriendId(int friendId)
	{
		this.friendId = friendId;
	}
	
	public int getFriendId()
	{
		return friendId;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	public User getUser()
	{
		return user;
	}
	public void setFriend(User friend)
	{
		this.friend = friend;
	}
	public User getFriend()
	{
		return friend;
	}
}
