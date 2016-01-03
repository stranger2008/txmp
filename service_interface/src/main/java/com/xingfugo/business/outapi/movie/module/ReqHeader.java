package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ReqHeader {
	
	@XStreamAlias("tradaId")
	private String tradaId;
	
	@XStreamAlias("timeStamp")
	private String timeStamp;
	
	@XStreamAlias("valiCode")
	private String validCode;
	
	@XStreamAlias("appKey")
	private String appKey;

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

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}
