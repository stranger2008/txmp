package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class SMSVoucherResendReq extends AbstractReq {
	@XStreamAlias("body")
	private SMSVoucherResendReqBody body;

	public SMSVoucherResendReqBody getBody() {
		return body;
	}

	public void setBody(SMSVoucherResendReqBody body) {
		this.body = body;
	}
}
