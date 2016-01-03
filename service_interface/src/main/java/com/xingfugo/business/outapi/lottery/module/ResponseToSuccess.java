package com.xingfugo.business.outapi.lottery.module;

public class ResponseToSuccess {
	//响应代码
	private String resultcode;
	//彩票序列号
	private String serialnum;
	//响应时间格式
	private String responsetime;
	//期号
	private String period;
	//游戏编号
	private String gameid;
	//直接返回商户生成的内容
	private String merchantserialnum;
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getSerialnum() {
		return serialnum;
	}
	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
	public String getResponsetime() {
		return responsetime;
	}
	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getMerchantserialnum() {
		return merchantserialnum;
	}
	public void setMerchantserialnum(String merchantserialnum) {
		this.merchantserialnum = merchantserialnum;
	}
	
	
}
