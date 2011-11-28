/**
 * @(#)ShopGoods.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;
public class ShopGoods{
	private int shopGoodsId;
	private int shopGoodsPrice;
	private int goodsAmount;
	private Goods goods;
	private Shop shop;
	
	public ShopGoods()
	{
	}


	public ShopGoods(int shopGoodsId, int shopGoodsPrice, 
				int goodsAmount) {
		this.shopGoodsId = shopGoodsId;
		this.shopGoodsPrice = shopGoodsPrice;
		this.goodsAmount = goodsAmount;
	}
	
	public void setShopGoodsId(int shopGoodsId) {
		this.shopGoodsId = shopGoodsId; 
	}

	public void setShop(Shop shop) {
		this.shop = shop; 
	}

	public void setShopGoodsPrice(int shopGoodsPrice) {
		this.shopGoodsPrice = shopGoodsPrice; 
	}

	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount; 
	}

	public void setGoods(Goods goods) {
		this.goods = goods; 
	}

	public int getShopGoodsId() {
		return (this.shopGoodsId); 
	}

	public Shop getShop() {
		return (this.shop); 
	}

	public int getShopGoodsPrice() {
		return (this.shopGoodsPrice); 
	}

	public int getGoodsAmount() {
		return (this.goodsAmount); 
	}

	public Goods getGoods() {
		return (this.goods); 
	}
}
