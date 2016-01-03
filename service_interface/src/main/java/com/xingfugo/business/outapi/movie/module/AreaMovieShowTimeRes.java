package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class AreaMovieShowTimeRes extends AbstractRes {

	@XStreamAlias("body")
	private AreaMovieShowTimeResBody body;

	public AreaMovieShowTimeResBody getBody() {
		return body;
	}

	public void setBody(AreaMovieShowTimeResBody body) {
		this.body = body;
	}
}
