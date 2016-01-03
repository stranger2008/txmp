package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class EmptyBodyReq extends AbstractReq{
	@XStreamAlias("body")
	private EmptyReqBody body;

	public EmptyReqBody getBody() {
		return body;
	}

	public void setBody(EmptyReqBody body) {
		this.body = body;
	}
}
