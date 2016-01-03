package com.xingfugo.business.outapi.airline.module.query;

/**
 * @author wyl
 * 机票搜索参数
 */
public class AvailableFlightWithPriceAndCommisionQueryForm {
	
	/**
	 * 公司代码
	 */
	private String agencyCode;
	/**
	 * 签名信息
	 */
	private String sign;
	/**
	 * 出发机场三字码
	 */
	private String orgAirportCode;
	/**
	 * 抵达城市三字码
	 */
	private String dstAirportCode;
	  
	/**
	 * 起飞日期
	 */
	private String date;
	/**
	 * 只返回可用舱位
	 */
	private String onlyAvailableSeat;
	/**
	 * 是否包括特殊政策
	 */
	private String onlyNormalCommision;
	/**
	 * 是否只返回在工作时间内政策
	 */
	private String onlyOnWorkingCommision;
	/**
	 * 是否可更换PNR出票
	 */
	private String onlySelfPNR;
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getOrgAirportCode() {
		return orgAirportCode;
	}
	public void setOrgAirportCode(String orgAirportCode) {
		this.orgAirportCode = orgAirportCode;
	}
	public String getDstAirportCode() {
		return dstAirportCode;
	}
	public void setDstAirportCode(String dstAirportCode) {
		this.dstAirportCode = dstAirportCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOnlyAvailableSeat() {
		return onlyAvailableSeat;
	}
	public void setOnlyAvailableSeat(String onlyAvailableSeat) {
		this.onlyAvailableSeat = onlyAvailableSeat;
	}
	public String getOnlyNormalCommision() {
		return onlyNormalCommision;
	}
	public void setOnlyNormalCommision(String onlyNormalCommision) {
		this.onlyNormalCommision = onlyNormalCommision;
	}
	public String getOnlyOnWorkingCommision() {
		return onlyOnWorkingCommision;
	}
	public void setOnlyOnWorkingCommision(String onlyOnWorkingCommision) {
		this.onlyOnWorkingCommision = onlyOnWorkingCommision;
	}
	public String getOnlySelfPNR() {
		return onlySelfPNR;
	}
	public void setOnlySelfPNR(String onlySelfPNR) {
		this.onlySelfPNR = onlySelfPNR;
	}
	
	
	
	
}
