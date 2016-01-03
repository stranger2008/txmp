package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 配置文件movieConf.xml节点内容的映射对象 
 */
@XStreamAlias("movieConf")
public class Config {
	@XStreamAlias("serverRootURL")
	private String serverRootURL;
	
	@XStreamAlias("interfaceURL")
	private String interfaceURL;
	
	@XStreamAlias("interfaceParam")
	private String interfaceParam;
	
	@XStreamAlias("seatSelectURL")
	private String seatSelectURL;
	
	@XStreamAlias("appKey")
	private String appKey;
	
	@XStreamAlias("appCode")
	private String appCode;

	@XStreamAlias("maxSelectedSeat")
	private int maxSelectedSeat;
	
	public String getServerRootURL() {
		return serverRootURL;
	}

	public void setServerRootURL(String serverRootURL) {
		this.serverRootURL = serverRootURL;
	}

	public String getInterfaceURL() {
		return interfaceURL;
	}

	public void setInterfaceURL(String interfaceURL) {
		this.interfaceURL = interfaceURL;
	}

	public String getInterfaceParam() {
		return interfaceParam;
	}

	public void setInterfaceParam(String interfaceParam) {
		this.interfaceParam = interfaceParam;
	}

	public String getSeatSelectURL() {
		return seatSelectURL;
	}

	public void setSeatSelectURL(String seatSelectURL) {
		this.seatSelectURL = seatSelectURL;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public int getMaxSelectedSeat() {
		return maxSelectedSeat;
	}

	public void setMaxSelectedSeat(int maxSelectedSeat) {
		this.maxSelectedSeat = maxSelectedSeat;
	}
	
}
