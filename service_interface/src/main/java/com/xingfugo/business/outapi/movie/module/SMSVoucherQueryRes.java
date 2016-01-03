package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class SMSVoucherQueryRes extends AbstractRes {

	@XStreamAlias("body")
	private SMSVoucherQueryResBody body;

	public SMSVoucherQueryResBody getBody() {
		return body;
	}

	public void setBody(SMSVoucherQueryResBody body) {
		this.body = body;
	}
}
