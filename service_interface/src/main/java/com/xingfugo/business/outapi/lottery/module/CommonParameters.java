package com.xingfugo.business.outapi.lottery.module;

public class CommonParameters {
	//商户编号（系统分配）
	private String merchantnum;
	//请求类别
	private String requesttype;
	//由调用者产生编号，保证游戏当期唯一
	private String merchantserialnum;
	//请求时间格式	(yyyy-mm-dd hh24:mi:ss)
	private String requesttime;
	//游戏编号
	private String gameid;
	//外部期号
	private String period;
	//投注卡号（最长50位，数字、字母）
	private String wagercardnum;
	//投注密码（最长50位，数字、字母）
	private String wagercardpwd;
	public String getMerchantnum() {
		return merchantnum;
	}
	public void setMerchantnum(String merchantnum) {
		this.merchantnum = merchantnum;
	}
	public String getRequesttype() {
		return requesttype;
	}
	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}
	public String getMerchantserialnum() {
		return merchantserialnum;
	}
	public void setMerchantserialnum(String merchantserialnum) {
		this.merchantserialnum = merchantserialnum;
	}
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getWagercardnum() {
		return wagercardnum;
	}
	public void setWagercardnum(String wagercardnum) {
		this.wagercardnum = wagercardnum;
	}
	public String getWagercardpwd() {
		return wagercardpwd;
	}
	public void setWagercardpwd(String wagercardpwd) {
		this.wagercardpwd = wagercardpwd;
	}
	
}
