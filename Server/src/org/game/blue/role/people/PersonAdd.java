/**
 * @(#)PersonAdd.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.blue.role.people;

public interface PersonAdd 
{
	/**
	 * ������Ʒ����Ʒ����
	 * @param userId���û�id
	 * @param goodsId����Ʒid
	 * @param goodsAmount����Ʒ����
	 */
	public boolean addGoodColumn(int userId, int goodsId, int goodsAmount);
	
	 /**
	 * ����ֿ���Ʒ����
	 * @param userId���û�id
	 * @param goodsId����Ʒid
	 * @param goodsAmount����Ʒ����
	 */
	public boolean addDepotGood(int userId, int goodsId, int goodsAmount);
	
	/**
	 * ���������Ϣ
	 * @param userId
	 * @param frientId
	 */
	public boolean addFriend(int userId,int frientId);
}
