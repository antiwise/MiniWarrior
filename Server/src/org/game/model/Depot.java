/**
 * @(#)Depot.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;

public class Depot{
	private int depotId;
	private int goodsAmount;
	private User user;
	private Goods goods;
	public Depot()
	{
	}
	


	public Depot(int depotId, int goodsAmount) {
		this.depotId = depotId;
		this.goodsAmount = goodsAmount;
	}
	
	public void setDepotId(int depotId) {
		this.depotId = depotId; 
	}

	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount; 
	}

	public void setUser(User user) {
		this.user = user; 
	}

	public void setGoods(Goods goods) {
		this.goods = goods; 
	}

	public int getDepotId() {
		return (this.depotId); 
	}

	public int getGoodsAmount() {
		return (this.goodsAmount); 
	}

	public User getUser() {
		return (this.user); 
	}

	public Goods getGoods() {
		return (this.goods); 
	}


}