package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SMSVoucherQueryReqBody {
	// 订单号
	@XStreamAlias("OrderNo")
	private String orderNo;

	// 签名
	@XStreamAlias("Sign")
	private String sign;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
