package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SMSVoucherQueryResBody {
	
	// 订单信息
	@XStreamAlias("OrderInfo")
	private SMSVoucherQueryOrderInfoRes smsVoucherQueryOrderInfoRes;
	
	// 凭证信息
	@XStreamAlias("VoucherInfo")
	private SMSVoucherQueryVoucherInfoRes smsVoucherQueryVoucherInfoRes;

	public SMSVoucherQueryOrderInfoRes getSmsVoucherQueryOrderInfoRes() {
		return smsVoucherQueryOrderInfoRes;
	}

	public void setSmsVoucherQueryOrderInfoRes(
			SMSVoucherQueryOrderInfoRes smsVoucherQueryOrderInfoRes) {
		this.smsVoucherQueryOrderInfoRes = smsVoucherQueryOrderInfoRes;
	}

	public SMSVoucherQueryVoucherInfoRes getSmsVoucherQueryVoucherInfoRes() {
		return smsVoucherQueryVoucherInfoRes;
	}

	public void setSmsVoucherQueryVoucherInfoRes(
			SMSVoucherQueryVoucherInfoRes smsVoucherQueryVoucherInfoRes) {
		this.smsVoucherQueryVoucherInfoRes = smsVoucherQueryVoucherInfoRes;
	}
}
