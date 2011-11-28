/**
 * @(#)Goods.java
 * 
 * @author soda E-mail:junaisy@163.com
 * @version 1.0
 * <br>Program Name: FlashNetGame
 * <br>Date: 2007-4-18
 */
package org.game.model;

public class Goods{
	private int goodsId;
	private String goodsName;
	private int goodsValue;
	private String goodsProperty;
	private int isOverlap;
	private String goodsImage;
	private GoodsItem goodsItem;
	public Goods()
	{
	}


	public Goods(int goodsId, String goodsName, int goodsValue, 
				String goodsProperty, int isOverlap, String goodsImage) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsValue = goodsValue;
		this.goodsProperty = goodsProperty;
		this.isOverlap = isOverlap;
		this.goodsImage = goodsImage;
	}
	
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId; 
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName; 
	}

	public void setGoodsValue(int goodsValue) {
		this.goodsValue = goodsValue; 
	}

	public void setGoodsProperty(String goodsProperty) {
		this.goodsProperty = goodsProperty; 
	}

	public void setIsOverlap(int isOverlap) {
		this.isOverlap = isOverlap; 
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage; 
	}

	public void setGoodsItem(GoodsItem goodsItem) {
		this.goodsItem = goodsItem; 
	}

	public int getGoodsId() {
		return (this.goodsId); 
	}

	public String getGoodsName() {
		return (this.goodsName); 
	}

	public int getGoodsValue() {
		return (this.goodsValue); 
	}

	public String getGoodsProperty() {
		return (this.goodsProperty); 
	}

	public int getIsOverlap() {
		return (this.isOverlap); 
	}

	public String getGoodsImage() {
		return (this.goodsImage); 
	}

	public GoodsItem getGoodsItem() {
		return (this.goodsItem); 
	}
}