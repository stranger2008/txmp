package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class SeatTicketOrderRes extends AbstractRes {

	@XStreamAlias("body")
	private SeatTicketOrder body;

	public SeatTicketOrder getBody() {
		return body;
	}

	public void setBody(SeatTicketOrder body) {
		this.body = body;
	}
}
