package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class AreaMovieShowTimeReq extends AbstractReq {
	@XStreamAlias("body")
	private AreaMovieShowTimeReqBody body;

	public AreaMovieShowTimeReqBody getBody() {
		return body;
	}

	public void setBody(AreaMovieShowTimeReqBody body) {
		this.body = body;
	}
}
