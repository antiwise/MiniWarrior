/**
 * @(#)Shop.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;
import java.util.Set;
import java.util.HashSet;

public class Shop 
{
	private int shopId;
	private String shopName;
	private Set<ShopGoods> shopGoods = new HashSet<ShopGoods>();
	
	public Shop()
	{
		
	}
	public Shop(String shopName )
	{
		this.shopName=shopName;
	}
	public void setShopId(int shopId)
	{
		this.shopId = shopId;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName; 
	}

	public void setShopGoods(Set<ShopGoods> shopGoods) {
		this.shopGoods = shopGoods; 
	}

	public int getShopId() {
		return (this.shopId); 
	}

	public String getShopName() {
		return (this.shopName); 
	}

	public Set<ShopGoods> getShopGoods() {
		return (this.shopGoods); 
	}
}
