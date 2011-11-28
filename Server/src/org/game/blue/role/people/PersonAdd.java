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
	 * 插入物品栏物品数据
	 * @param userId：用户id
	 * @param goodsId：物品id
	 * @param goodsAmount：物品数量
	 */
	public boolean addGoodColumn(int userId, int goodsId, int goodsAmount);
	
	 /**
	 * 插入仓库物品数据
	 * @param userId：用户id
	 * @param goodsId：物品id
	 * @param goodsAmount：物品数量
	 */
	public boolean addDepotGood(int userId, int goodsId, int goodsAmount);
	
	/**
	 * 插入好友信息
	 * @param userId
	 * @param frientId
	 */
	public boolean addFriend(int userId,int frientId);
}
