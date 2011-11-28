/**
 * @(#)PersonSpeak.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.blue.role.people;

/**
 * 用户聊天
 * @author soda
 *
 */
public interface PersonSpeak 
{
	/**
	 * 用户发送信息给好友
	 * @param userName
	 * @param toUser
	 * @param message
	 */
	public void speakToOne(String userName, String toUser, String message);
	
	/**
	 * 用户发送信息给所有好友
	 * @param userName
	 * @param friendName
	 * @param message
	 */
	public void toFriends(String userName, String message);
}
