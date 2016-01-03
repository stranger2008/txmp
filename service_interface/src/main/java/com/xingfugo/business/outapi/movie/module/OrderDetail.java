package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderDetail {
	// 订单编号
	@XStreamAlias("OrderNo")
	private String orderNo;

	// 订单金额
	@XStreamAlias("AllMoney")
	private String allMoney;

	// 下单时间 格式yyyyMMddHHmmss
	@XStreamAlias("CreateTime")
	private String createTime;

	// 支付状态 1:未支付 2:部分支付 3：已支付 4：部分退款 5：内部部分退款 6：内部全额退款 7：无需支付
	@XStreamAlias("PayStatus")
	private String payStatus;

	// 当前状态 1:已成功 2:失败 3:取消 4:未处理 5:退款成功
	@XStreamAlias("Status")
	private int status;

	// 支付时间
	@XStreamAlias("PayTime")
	private String payTime;

	@XStreamAlias("orderDetailList")
	private OrderDetailItemList itemList;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public OrderDetailItemList getItemList() {
		return itemList;
	}

	public void setItemList(OrderDetailItemList itemList) {
		this.itemList = itemList;
	}

}
