package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class OrderDetailQueryRes extends AbstractRes {

	@XStreamAlias("body")
	private OrderDetail body;

	public OrderDetail getBody() {
		return body;
	}

	public void setBody(OrderDetail body) {
		this.body = body;
	}
}
