package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaInfoRes extends AbstractRes {

	@XStreamAlias("body")
	private CinemaInfo body;

	public CinemaInfo getBody() {
		return body;
	}

	public void setBody(CinemaInfo body) {
		this.body = body;
	}

}
