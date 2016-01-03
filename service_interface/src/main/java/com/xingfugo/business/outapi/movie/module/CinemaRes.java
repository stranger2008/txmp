package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaRes extends AbstractRes {

	@XStreamAlias("body")
	private CinemaResBody body;

	public CinemaResBody getBody() {
		return body;
	}

	public void setBody(CinemaResBody body) {
		this.body = body;
	}
}
