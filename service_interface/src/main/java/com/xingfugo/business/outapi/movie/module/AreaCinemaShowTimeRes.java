package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class AreaCinemaShowTimeRes extends AbstractRes {

	@XStreamAlias("body")
	private AreaCinemaShowTimeResBody body;

	public AreaCinemaShowTimeResBody getBody() {
		return body;
	}

	public void setBody(AreaCinemaShowTimeResBody body) {
		this.body = body;
	}
}
