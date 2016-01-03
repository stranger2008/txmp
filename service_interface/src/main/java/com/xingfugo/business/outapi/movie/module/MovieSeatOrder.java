package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MovieSeatOrder {
	// 订单编号
	@XStreamAlias("orderNo")
	private String orderNo;

	// 订单时间
	@XStreamAlias("orderTime")
	private String orderTime;

	// 手机号
	@XStreamAlias("mobile")
	private String mobile;

	// 排期
	@XStreamAlias("seqNo")
	private String seqNo;

	// 座位
	@XStreamAlias("seats")
	private String seats;

	// 签名
	@XStreamAlias("sign")
	private String sign;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
