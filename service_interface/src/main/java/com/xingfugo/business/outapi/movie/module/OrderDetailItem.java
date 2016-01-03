package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderDetailItem {
	// 订单编号
	@XStreamAlias("OrderNo")
	private String orderNo;

	// 商品ID
	@XStreamAlias("GoodsID")
	private String goodsID;

	// 商品名称
	@XStreamAlias("GoodsName")
	private String goodsName;

	// 总金额
	@XStreamAlias("AllMoney")
	private String allMoney;

	// 商品单价
	@XStreamAlias("SinglePrice")
	private String singlePrice;

	// 数量
	@XStreamAlias("GoodsAmount")
	private int goodsAmount;

	// 商品类型 1:选座 2:通兑 3：卖品 4: 兑换券 5:现金券 6：点卡
	@XStreamAlias("ProductType")
	private int productType;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public String getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(String singlePrice) {
		this.singlePrice = singlePrice;
	}

	public int getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

}
