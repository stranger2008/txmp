package com.xingfugo.business.outapi.lottery.module;

public class CurrentInfoResponse {
	
	/**
	 * 响应代码
	 */
	private String resultcode;
	/**
	 * 响应时间格式(yyyy-mm-dd hh24:mi:ss)
	 */
	private String responsetime;
	/**
	 * 当前销售状态编号
	 */
	private String currsalestatus;
	/**
	 * 当前期号
	 */
	private String currperiod;
	/**
	 * 当期销售开始时间
	 */
	private String currstarttime;
	/**
	 * 当期销售截至时间
	 */
	private String currendtime;
	/**
	 * 当期促销标识
	 */
	private String currsalepromotion;
	/**
	 * 游戏编号
	 */
	private String gameid;
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getResponsetime() {
		return responsetime;
	}
	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}
	public String getCurrsalestatus() {
		return currsalestatus;
	}
	public void setCurrsalestatus(String currsalestatus) {
		this.currsalestatus = currsalestatus;
	}
	public String getCurrperiod() {
		return currperiod;
	}
	public void setCurrperiod(String currperiod) {
		this.currperiod = currperiod;
	}
	public String getCurrstarttime() {
		return currstarttime;
	}
	public void setCurrstarttime(String currstarttime) {
		this.currstarttime = currstarttime;
	}
	public String getCurrendtime() {
		return currendtime;
	}
	public void setCurrendtime(String currendtime) {
		this.currendtime = currendtime;
	}
	public String getCurrsalepromotion() {
		return currsalepromotion;
	}
	public void setCurrsalepromotion(String currsalepromotion) {
		this.currsalepromotion = currsalepromotion;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
}
