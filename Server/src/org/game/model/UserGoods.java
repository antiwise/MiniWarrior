/**
 * @(#)UserGoods.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;

public class UserGoods{
	private int userGoodsId;
	private int goodsAmount;
	private User user;
	private Goods goods;
		
	public UserGoods()
	{
	}


	public UserGoods(int userGoodsId, int goodsAmount) {
		this.userGoodsId = userGoodsId;
		this.goodsAmount = goodsAmount;
	}
	
	public void setUserGoodsId(int userGoodsId) {
		this.userGoodsId = userGoodsId; 
	}

	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount; 
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	public void setGoods(Goods goods)
	{
		this.goods = goods;
	}

	public int getUserGoodsId() {
		return (this.userGoodsId);  
	}

	public int getGoodsAmount() {
		return (this.goodsAmount); 
	}
	
	public User getUser()
	{
		return (this.user);
	}
	public Goods getGoods()
	{
		return (this.goods);
	}





}