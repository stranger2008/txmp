package com.xingfugo.business.module.query;

public class SellerGoodsQueryForm extends GoodsQueryForm {
	//查询类型：0，商品查询；1：库存查询；2：上下架查询
	private int t = 0;

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}
	
}
