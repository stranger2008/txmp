package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class CinemaSeatReq extends AbstractReq {
	@XStreamAlias("body")
	private CinemaSeatReqBody body;

	public CinemaSeatReqBody getBody() {
		return body;
	}

	public void setBody(CinemaSeatReqBody body) {
		this.body = body;
	}
}
