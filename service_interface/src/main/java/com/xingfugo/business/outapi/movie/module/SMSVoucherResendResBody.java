package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SMSVoucherResendResBody {
	// 返回状态,0-成功 1-失败
	@XStreamAlias("errCode")
	private Integer errCode;

	// 返回信息,成功或失败信息
	@XStreamAlias("errMsg")
	private String errMsg;

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public boolean isSuccess(){
		return (0 == errCode);
	}
}
