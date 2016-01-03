package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class OrderListQueryReq extends AbstractReq {
	@XStreamAlias("body")
	private OrderListQueryReqBody body;

	public OrderListQueryReqBody getBody() {
		return body;
	}

	public void setBody(OrderListQueryReqBody body) {
		this.body = body;
	}
}
