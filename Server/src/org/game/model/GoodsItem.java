/**
 * @(#)GoodsItem.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;

import java.util.Set;
import java.util.HashSet;
public class GoodsItem
{
	private int goodsItemId;
	private String goodsItemName;
	private Set goods = new HashSet();
	
	public GoodsItem()
	{
		
	}
	public GoodsItem(String goodsItemName)
	{
		this.goodsItemName = goodsItemName;
	}
	
	public GoodsItem(int goodsItemId, String goodsItemName) {
		this.goodsItemId = goodsItemId;
		this.goodsItemName = goodsItemName;
	}

	public void setGoodsItemId(int goodsItemId) {
		this.goodsItemId = goodsItemId; 
	}

	public void setGoodsItemName(String goodsItemName) {
		this.goodsItemName = goodsItemName; 
	}

	public int getGoodsItemId() {
		return (this.goodsItemId); 
	}

	public String getGoodsItemName() {
		return (this.goodsItemName); 
	}
	
	public void setGoods(Set goods)
	{
		this.goods = goods;
	}
	public Set getGoods()
	{
		return goods;
	}
}
