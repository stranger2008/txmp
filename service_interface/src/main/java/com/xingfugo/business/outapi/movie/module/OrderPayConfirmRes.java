package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class OrderPayConfirmRes extends AbstractRes {

	@XStreamAlias("body")
	private OrderPayConfirmResBody body;

	public OrderPayConfirmResBody getBody() {
		return body;
	}

	public void setBody(OrderPayConfirmResBody body) {
		this.body = body;
	}
}
