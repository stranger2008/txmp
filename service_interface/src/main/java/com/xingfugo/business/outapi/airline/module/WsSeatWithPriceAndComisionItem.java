package com.xingfugo.business.outapi.airline.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class WsSeatWithPriceAndComisionItem {
	 
 	/**
	 * 航班号
	 */
 @XStreamAlias("flightNo")
	private String flightNo;
	/**
	 * 舱位码
	 */
 @XStreamAlias("seatCode")
	private String seatCode;
	/**
	 * 舱位状态
	 */
 @XStreamAlias("seatStatus")
	private String seatStatus;
	/**
	 * 折扣
	 */
 @XStreamAlias("discount")
	private String discount;
	/**
	 * 舱位说明
	 */
 @XStreamAlias("seatMsg")
	private String seatMsg;
	/**
	 * 票面价
	 */
 @XStreamAlias("parPrice")
	private String parPrice;
	/**
	 * 是否特价舱位
	 */
 @XStreamAlias("seatType")
	private String seatType;
	/**
	 * 政策信息    存放政策键
	 */
 	@XStreamAlias("policyData")
	private WSPolicyData policyData;
	/**
	 * 成人的结算价
	 */
	@XStreamAlias("settlePrice")
	private String settlePrice;
	/**
	 * 备用参数
	 */
	@XStreamAlias("param1")
	private String param1;
	/**
	 * 服务级别
	 */
	@XStreamAlias("param2")
	private String param2;
	/**
	 * 备用参数
	 */
	@XStreamAlias("param3")
	private String param3;
	/**
	 * 备用参数
	 */
	@XStreamAlias("param4")
	private String param4;
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public String getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getSeatMsg() {
		return seatMsg;
	}
	public void setSeatMsg(String seatMsg) {
		this.seatMsg = seatMsg;
	}
	public String getParPrice() {
		return parPrice;
	}
	public void setParPrice(String parPrice) {
		this.parPrice = parPrice;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public WSPolicyData getPolicyData() {
		return policyData;
	}
	public void setPolicyData(WSPolicyData policyData) {
		this.policyData = policyData;
	}
	public String getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(String settlePrice) {
		this.settlePrice = settlePrice;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getParam4() {
		return param4;
	}
	public void setParam4(String param4) {
		this.param4 = param4;
	}
	

}
