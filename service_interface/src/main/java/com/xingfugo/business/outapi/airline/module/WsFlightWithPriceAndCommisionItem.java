package com.xingfugo.business.outapi.airline.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class WsFlightWithPriceAndCommisionItem {

	@XStreamImplicit(itemFieldName="flights")
	private List<WsFlightWithPriceAndCommision> wFlightWithPriceAndCommisionList;

	public List<WsFlightWithPriceAndCommision> getWFlightWithPriceAndCommisionList() {
		return wFlightWithPriceAndCommisionList;
	}

	public void setWFlightWithPriceAndCommisionList(
			List<WsFlightWithPriceAndCommision> flightWithPriceAndCommisionList) {
		wFlightWithPriceAndCommisionList = flightWithPriceAndCommisionList;
	}
	/**
	 * 出发日期@XStreamAlias("name")
	 */
	@XStreamAlias("date")
	private String date;
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
	 * 航程公里数
	 */
	@XStreamAlias("distance")
	private Double distance;
	/**
	 * 成人的机建费
	 */
	@XStreamAlias("audletAirportTax")
	private Double audletAirportTax;
	/**
	 * 成人的燃油费
	 */
	@XStreamAlias("audletFuelTax")
	private Double audletFuelTax;
	/**
	 * Y舱价格
	 */
	@XStreamAlias("basePrice")
	private Double basePrice;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getAudletAirportTax() {
		return audletAirportTax;
	}

	public void setAudletAirportTax(Double audletAirportTax) {
		this.audletAirportTax = audletAirportTax;
	}

	public Double getAudletFuelTax() {
		return audletFuelTax;
	}

	public void setAudletFuelTax(Double audletFuelTax) {
		this.audletFuelTax = audletFuelTax;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	

}
