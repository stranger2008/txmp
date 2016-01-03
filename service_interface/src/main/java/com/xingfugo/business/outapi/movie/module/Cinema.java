package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Cinema {
	@XStreamAlias("AreaNo")
	private String areaNo;

	@XStreamAlias("AreaName")
	private String areaName;

	@XStreamAlias("Address")
	private String address;

	@XStreamAlias("CinemaNo")
	private String cinemaNo;

	@XStreamAlias("CinemaName")
	private String cinemaName;

	@XStreamAlias("CinemaLogo")
	private String cinemaLogo;

	@XStreamAlias("AverageDegree")
	private String averageDegree;

	@XStreamAlias("PhoneNo")
	private String phoneNo;

	@XStreamAlias("Traffic")
	private String traffic;

	@XStreamAlias("FouseAmount")
	private int fouseAmount;

	//经纬度,纬度和经度以英文逗号隔开，格式：lat,lng
	@XStreamAlias("LatLng")
	private String latLng;

	//售票标志,1．仅选座;2．仅通兑;3．选座和通兑;4．仅查排期
	@XStreamAlias("SellFlag")
	private String sellFlag;

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCinemaNo() {
		return cinemaNo;
	}

	public void setCinemaNo(String cinemaNo) {
		this.cinemaNo = cinemaNo;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getCinemaLogo() {
		return cinemaLogo;
	}

	public void setCinemaLogo(String cinemaLogo) {
		this.cinemaLogo = cinemaLogo;
	}

	public String getAverageDegree() {
		return averageDegree;
	}

	public void setAverageDegree(String averageDegree) {
		this.averageDegree = averageDegree;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public int getFouseAmount() {
		return fouseAmount;
	}

	public void setFouseAmount(int fouseAmount) {
		this.fouseAmount = fouseAmount;
	}

	public String getLatLng() {
		return latLng;
	}

	public void setLatLng(String latLng) {
		this.latLng = latLng;
	}

	public String getSellFlag() {
		return sellFlag;
	}

	public void setSellFlag(String sellFlag) {
		this.sellFlag = sellFlag;
	}
}
