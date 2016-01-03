package com.xingfugo.business.module;

import org.hibernate.validator.constraints.NotBlank;

public class PaymentOrder {

	@NotBlank
	private String order_id;
	@NotBlank
	private String payment_way;
	private String total_price;
	private String respInfo;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPayment_way() {
		return payment_way;
	}
	public void setPayment_way(String payment_way) {
		this.payment_way = payment_way;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getRespInfo() {
		return respInfo;
	}
	public void setRespInfo(String respInfo) {
		this.respInfo = respInfo;
	}
	
}
