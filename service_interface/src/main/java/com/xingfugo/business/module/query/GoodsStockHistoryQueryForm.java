package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

public class GoodsStockHistoryQueryForm extends BasePageForm {
	private Integer goodsId;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
}
