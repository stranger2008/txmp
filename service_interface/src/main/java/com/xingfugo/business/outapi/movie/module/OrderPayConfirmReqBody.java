package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderPayConfirmReqBody {
	// 手机号
	@XStreamAlias("Mobile")
	private String mobile;

	// 订单号
	@XStreamAlias("OrderNo")
	private String orderNo;

	// 订单金额
	@XStreamAlias("orderPrice")
	private String orderPrice;

	// 支付金额
	@XStreamAlias("payment")
	private String payment;

	// 分销商URL,非必要字段 
	@XStreamAlias("DistributorUrl")
	private String distributorUrl = "";

	// 签名
	@XStreamAlias("sign")
	private String sign;

	// 备注,非必要字段 
	@XStreamAlias("Remark")
	private String remark = "";

	//发送短信类型,非必要字段 
	@XStreamAlias("SendTyp")
	private String sendTyp = "";

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDistributorUrl() {
		return distributorUrl;
	}

	public void setDistributorUrl(String distributorUrl) {
		this.distributorUrl = distributorUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSendTyp() {
		return sendTyp;
	}

	public void setSendTyp(String sendTyp) {
		this.sendTyp = sendTyp;
	}
}
