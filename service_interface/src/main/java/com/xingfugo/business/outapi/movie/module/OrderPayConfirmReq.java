package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class OrderPayConfirmReq extends AbstractReq {
	@XStreamAlias("body")
	private OrderPayConfirmReqBody body;

	public OrderPayConfirmReqBody getBody() {
		return body;
	}

	public void setBody(OrderPayConfirmReqBody body) {
		this.body = body;
	}
}
