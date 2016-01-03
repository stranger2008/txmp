package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CommingMovieRes extends AbstractRes {

	@XStreamAlias("body")
	private CommingMovieResBody body;

	public CommingMovieResBody getBody() {
		return body;
	}

	public void setBody(CommingMovieResBody body) {
		this.body = body;
	}
}
