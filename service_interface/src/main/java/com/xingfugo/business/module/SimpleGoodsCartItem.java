package com.xingfugo.business.module;

public class SimpleGoodsCartItem {
	private Integer goods_id = null;
	private Integer amount = null;
	private Integer stock = null;
	private String goods_name = null;
	
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SimpleGoodsCartItem){
			SimpleGoodsCartItem item = (SimpleGoodsCartItem)obj;
			return this.getGoods_id().equals(item.getGoods_id());
		}
		
		return false;
	}
	@Override
	public int hashCode() {
		return goods_id.hashCode();
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public boolean hasEnoughStock(){
		return (stock >= amount);
	}
}
