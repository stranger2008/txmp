package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class AreaListRes extends AbstractRes {
	
	@XStreamAlias("body")
	private AreaListResBody body;

	public AreaListResBody getBody() {
		return body;
	}

	public void setBody(AreaListResBody body) {
		this.body = body;
	}
}
