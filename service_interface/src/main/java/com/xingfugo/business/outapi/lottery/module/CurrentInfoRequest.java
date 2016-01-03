package com.xingfugo.business.outapi.lottery.module;

public class CurrentInfoRequest {
	
	/**
	 * 商户编号
	 */
	private String merchantnum;
	/**
	 * 游戏编号
	 */
	private String gameid;
	/**
	 * 请求类别
	 */
	private String requesttype;
	/**
	 * 请求时间格式(yyyy-mm-dd hh24:mi:ss)
	 */
	private String requesttime;
	public String getMerchantnum() {
		return merchantnum;
	}
	public void setMerchantnum(String merchantnum) {
		this.merchantnum = merchantnum;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getRequesttype() {
		return requesttype;
	}
	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	
	
	
	

}
