package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaMovieShowTimeRes extends AbstractRes {

	@XStreamAlias("body")
	private CinemaMovieShowTimeResBody body;

	public CinemaMovieShowTimeResBody getBody() {
		return body;
	}

	public void setBody(CinemaMovieShowTimeResBody body) {
		this.body = body;
	}
}
