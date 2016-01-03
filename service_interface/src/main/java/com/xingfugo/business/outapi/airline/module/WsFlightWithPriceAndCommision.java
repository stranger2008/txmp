package com.xingfugo.business.outapi.airline.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class WsFlightWithPriceAndCommision {

	/**
	 * 是否共享航班
	 */
	@XStreamAlias("codeShare")
	private String codeShare;
	/**
	 * 共享航班号
	 */
	@XStreamAlias("shareNum")
	private String shareNum;
	/**
	 * 航班号
	 */
	@XStreamAlias("flightNo")
	private String flightNo;
	/**
	 * 联接协议级别
	 */
	@XStreamAlias("link")
	private String link;
	/**
	 * 出发城市
	 */
	@XStreamAlias("orgCity")
	private String orgCity;
	/**
	 * 抵达城市
	 */
	@XStreamAlias("dstCity")
	private String dstCity;
	/**
	 * 起飞时间
	 */
	@XStreamAlias("depTime")
	private String depTime;
	/**
	 * 起飞修正时间
	 */
	@XStreamAlias("depModifyTime")
	private String depModifyTime;
	/**
	 * 降落时间
	 */
	@XStreamAlias("arriTime")
	private String arriTime;
	/**
	 * 降落修正时间
	 */
	@XStreamAlias("arriModifyTime")
	private String arriModifyTime;
	/**
	 * 餐食标识
	 */
	@XStreamAlias("meal")
	private String meal;
	/**
	 * 机型
	 */
	@XStreamAlias("planeType")
	private String planeType;
	/**
	 * 经停次数
	 */
	@XStreamAlias("stopnum")
	private String stopnum;
	/**
	 * 是否电子客票
	 */
	@XStreamAlias("isElectronicTicket")
	private String isElectronicTicket;
	/**
	 * 始发航站楼
	 */
	@XStreamAlias("orgJetquay")
	private String orgJetquay;
	/**
	 * 到达航站楼
	 */
	@XStreamAlias("dstJetquay")
	private String dstJetquay;
	/**
	 * 舱位信息
	 */
	@XStreamImplicit(itemFieldName="seatItems")
	private List<WsSeatWithPriceAndComisionItem> seatItems;
	/**
	 * 成人的机建费
	 */
	@XStreamAlias("airportTax")
	private String airportTax;
	/**
	 * 抵达日期
	 */
	@XStreamAlias("param1")
	private String param1;
	/**
	 * 儿童机建费
	 */
	@XStreamAlias("param2")
	private String param2;
	/**
	 * 成人的燃油费
	 */
	@XStreamAlias("fuelTax")
	private String fuelTax;
	/**
	 * 儿童燃油费
	 */
	@XStreamAlias("param3")
	private String param3;
	/**
	 * 备用参数
	 */
	@XStreamAlias("param4")
	private String param4;
	public String getCodeShare() {
		return codeShare;
	}
	public void setCodeShare(String codeShare) {
		this.codeShare = codeShare;
	}
	public String getShareNum() {
		return shareNum;
	}
	public void setShareNum(String shareNum) {
		this.shareNum = shareNum;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getOrgCity() {
		return orgCity;
	}
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	public String getDstCity() {
		return dstCity;
	}
	public void setDstCity(String dstCity) {
		this.dstCity = dstCity;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getDepModifyTime() {
		return depModifyTime;
	}
	public void setDepModifyTime(String depModifyTime) {
		this.depModifyTime = depModifyTime;
	}
	public String getArriTime() {
		return arriTime;
	}
	public void setArriTime(String arriTime) {
		this.arriTime = arriTime;
	}
	public String getArriModifyTime() {
		return arriModifyTime;
	}
	public void setArriModifyTime(String arriModifyTime) {
		this.arriModifyTime = arriModifyTime;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public String getStopnum() {
		return stopnum;
	}
	public void setStopnum(String stopnum) {
		this.stopnum = stopnum;
	}
	public String getIsElectronicTicket() {
		return isElectronicTicket;
	}
	public void setIsElectronicTicket(String isElectronicTicket) {
		this.isElectronicTicket = isElectronicTicket;
	}
	public String getOrgJetquay() {
		return orgJetquay;
	}
	public void setOrgJetquay(String orgJetquay) {
		this.orgJetquay = orgJetquay;
	}
	public String getDstJetquay() {
		return dstJetquay;
	}
	public void setDstJetquay(String dstJetquay) {
		this.dstJetquay = dstJetquay;
	}
	public List<WsSeatWithPriceAndComisionItem> getSeatItems() {
		return seatItems;
	}
	public void setSeatItems(List<WsSeatWithPriceAndComisionItem> seatItems) {
		this.seatItems = seatItems;
	}
	public String getAirportTax() {
		return airportTax;
	}
	public void setAirportTax(String airportTax) {
		this.airportTax = airportTax;
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
	public String getFuelTax() {
		return fuelTax;
	}
	public void setFuelTax(String fuelTax) {
		this.fuelTax = fuelTax;
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
