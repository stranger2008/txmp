package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaInfoReq extends AbstractReq {
	@XStreamAlias("body")
	private CinemaInfoReqBody body;

	public CinemaInfoReqBody getBody() {
		return body;
	}

	public void setBody(CinemaInfoReqBody body) {
		this.body = body;
	}
}
