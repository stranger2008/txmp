package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ResHeader {
	@XStreamAlias("tradaId")
	private String tradaId;
	
	@XStreamAlias("timeStamp")
	private String timeStamp;
	
	@XStreamAlias("errCode")
	private String errCode;
	
	@XStreamAlias("errMsg")
	private String errMsg;

	public String getTradaId() {
		return tradaId;
	}

	public void setTradaId(String tradaId) {
		this.tradaId = tradaId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
