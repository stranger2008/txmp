package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class SMSVoucherResendRes extends AbstractRes {

	@XStreamAlias("body")
	private SMSVoucherResendResBody body;

	public SMSVoucherResendResBody getBody() {
		return body;
	}

	public void setBody(SMSVoucherResendResBody body) {
		this.body = body;
	}
}
