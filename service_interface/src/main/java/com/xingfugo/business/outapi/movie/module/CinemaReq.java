package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaReq extends AbstractReq {
	@XStreamAlias("body")
	private CinemaReqBody body;

	public CinemaReqBody getBody() {
		return body;
	}

	public void setBody(CinemaReqBody body) {
		this.body = body;
	}
}
