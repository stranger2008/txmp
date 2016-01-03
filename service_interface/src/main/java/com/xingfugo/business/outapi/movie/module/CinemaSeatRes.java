package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaSeatRes extends AbstractRes {

	@XStreamAlias("body")
	private CinemaSeatResBody body;

	public CinemaSeatResBody getBody() {
		return body;
	}

	public void setBody(CinemaSeatResBody body) {
		this.body = body;
	}
}
