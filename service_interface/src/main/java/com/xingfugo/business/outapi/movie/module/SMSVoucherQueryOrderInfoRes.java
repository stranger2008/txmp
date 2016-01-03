package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SMSVoucherQueryOrderInfoRes {
	// 订单编号
	@XStreamAlias("OrderNo")
	private String orderNo;

	// 使用状态,1:已成功;2:失败;3:取消;4:未处理;5:退款成功
	@XStreamAlias("OrderStatus")
	private Integer orderStatus;

	// 下单时间
	@XStreamAlias("OrderTime")
	private String orderTime;

	// 支付状态,1:未支付;3：已支付;6：内部全额退款;8:银行全额退款
	@XStreamAlias("PayStatus")
	private Integer payStatus;

	// 终端位置说明
	@XStreamAlias("Position")
	private String position;

	// 签名
	@XStreamAlias("Sign")
	private String sign;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
