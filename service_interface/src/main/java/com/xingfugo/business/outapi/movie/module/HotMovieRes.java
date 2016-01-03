package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class HotMovieRes extends AbstractRes {

	@XStreamAlias("body")
	private HotMovieResBody body;

	public HotMovieResBody getBody() {
		return body;
	}

	public void setBody(HotMovieResBody body) {
		this.body = body;
	}
}
