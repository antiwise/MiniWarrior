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
 * �û�����
 * @author soda
 *
 */
public interface PersonSpeak 
{
	/**
	 * �û�������Ϣ������
	 * @param userName
	 * @param toUser
	 * @param message
	 */
	public void speakToOne(String userName, String toUser, String message);
	
	/**
	 * �û�������Ϣ�����к���
	 * @param userName
	 * @param friendName
	 * @param message
	 */
	public void toFriends(String userName, String message);
}
