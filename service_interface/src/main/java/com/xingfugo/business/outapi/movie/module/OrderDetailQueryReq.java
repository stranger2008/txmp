package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class OrderDetailQueryReq extends AbstractReq {
	@XStreamAlias("body")
	private OrderDetailQueryReqBody body;

	public OrderDetailQueryReqBody getBody() {
		return body;
	}

	public void setBody(OrderDetailQueryReqBody body) {
		this.body = body;
	}
}
