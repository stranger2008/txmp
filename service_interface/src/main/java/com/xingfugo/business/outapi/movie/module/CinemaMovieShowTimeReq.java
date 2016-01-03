package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaMovieShowTimeReq extends AbstractReq {
	@XStreamAlias("body")
	private CinemaMovieShowTimeReqBody body;

	public CinemaMovieShowTimeReqBody getBody() {
		return body;
	}

	public void setBody(CinemaMovieShowTimeReqBody body) {
		this.body = body;
	}
}
