package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class SeatTicketOrderReq extends AbstractReq {
	@XStreamAlias("body")
	private SeatTicketOrderReqBody body;

	public SeatTicketOrderReqBody getBody() {
		return body;
	}

	public void setBody(SeatTicketOrderReqBody body) {
		this.body = body;
	}
}
