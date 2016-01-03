package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class AreaCinemaShowTimeReq extends AbstractReq {
	@XStreamAlias("body")
	private AreaCinemaShowTimeReqBody body;

	public AreaCinemaShowTimeReqBody getBody() {
		return body;
	}

	public void setBody(AreaCinemaShowTimeReqBody body) {
		this.body = body;
	}
}
