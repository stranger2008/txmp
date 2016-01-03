package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class MovieDetailRes extends AbstractRes {

	@XStreamAlias("body")
	private MovieDetail body;

	public MovieDetail getBody() {
		return body;
	}

	public void setBody(MovieDetail body) {
		this.body = body;
	}

}
