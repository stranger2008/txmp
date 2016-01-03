package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class HotMovieReq extends AbstractReq {
	@XStreamAlias("body")
	private HotMovieReqBody body;

	public HotMovieReqBody getBody() {
		return body;
	}

	public void setBody(HotMovieReqBody body) {
		this.body = body;
	}
}
