package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mopon")
public class SMSVoucherQueryReq extends AbstractReq {
	@XStreamAlias("body")
	private SMSVoucherQueryReqBody body;

	public SMSVoucherQueryReqBody getBody() {
		return body;
	}

	public void setBody(SMSVoucherQueryReqBody body) {
		this.body = body;
	}
}
