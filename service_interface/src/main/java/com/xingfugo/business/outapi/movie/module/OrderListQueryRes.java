package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class OrderListQueryRes extends AbstractRes {

	@XStreamAlias("body")
	private OrderListQueryResBody body;

	public OrderListQueryResBody getBody() {
		return body;
	}

	public void setBody(OrderListQueryResBody body) {
		this.body = body;
	}
}
