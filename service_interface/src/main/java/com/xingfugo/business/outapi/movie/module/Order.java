package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Order {
	// 订单号
	@XStreamAlias("OrderNo")
	private String orderNo;

	// 订单时间 格式yyyyMMddHHmmss
	@XStreamAlias("OrderTime")
	private String orderTime;

	// 订单类型 1：普通订单 2：活动订单 3: 储值卡订单
	@XStreamAlias("OrderType")
	private int orderType;

	// 业务类型 1:选座 2:通兑 3:套餐 4：点卡
	@XStreamAlias("BusinessType")
	private int businessType;

	// 金额
	@XStreamAlias("Total")
	private String total;

	// 数量
	@XStreamAlias("count")
	private String count;

	// 支付状态 1:未支付 2:部分支付 3：已支付 4：部分退款 5：内部部分退款 6：内部全额退款 7：无需支付
	@XStreamAlias("PayStatus")
	private int payStatus;

	// 订单状态 1:已成功 2:失败 3:取消 4:未处理 5:退款成功
	@XStreamAlias("Status")
	private int status;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
