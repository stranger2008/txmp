package com.xingfugo.business.module;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

public class SellerGoodsWithBLOBs extends SellerGoods {
	@Length(max=30000)
	private String goodsDetail;

    @Min(0)
    @Digits(integer=10, fraction=2)
    private String shipPrice = "0.00";

    private List<GoodsAttr> goodsAttrList = new ArrayList<GoodsAttr>();
    
    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(String shipPrice) {
        this.shipPrice = shipPrice;
    }

	public List<GoodsAttr> getGoodsAttrList() {
		return goodsAttrList;
	}

	public void setGoodsAttrList(List<GoodsAttr> goodsAttrList) {
		this.goodsAttrList = goodsAttrList;
	}
}