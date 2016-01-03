package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class MovieDetailReq extends AbstractReq {
	@XStreamAlias("body")
	private MovieDetailReqBody body;

	public MovieDetailReqBody getBody() {
		return body;
	}

	public void setBody(MovieDetailReqBody body) {
		this.body = body;
	}
}
