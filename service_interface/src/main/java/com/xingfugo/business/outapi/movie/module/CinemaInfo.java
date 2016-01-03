package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CinemaInfo {
	private String cinemaNo;
	
	//区域编号
	@XStreamAlias("AreaNo")
	private String areaNo;

	//区域名称
	@XStreamAlias("AreaName")
	private String areaName;

	//影院地址
	@XStreamAlias("Address")
	private String address;

	//影院名称
	@XStreamAlias("CinemaName")
	private String cinemaName;

	//影院Logo
	@XStreamAlias("CinemaLogo")
	private String cinemaLogo;

	//影院综合评分,保留小数点后1位
	@XStreamAlias("AverageDegree")
	private String averageDegree;

	//影院办公电话
	@XStreamAlias("PhoneNo")
	private String phoneNo;

	//影院交通线路
	@XStreamAlias("Traffic")
	private String traffic;

	//关注人数
	@XStreamAlias("FouseAmount")
	private int fouseAmount;

	//影院终端图
	@XStreamAlias("DeviceUrl")
	private String deviceUrl;

	//影院终端描述
	@XStreamAlias("DeviceDesc")
	private String deviceDesc;

	//影院介绍
	@XStreamAlias("Introduction")
	private String introduction;

	//售票标志,1．仅选座;2．仅通兑;3．选座和通兑;4．仅查排期
	@XStreamAlias("SellFlag")
	private String sellFlag;

	//影院图片地址集合,英文逗号分隔 格式：图片1,图片2,图片3
	@XStreamAlias("CinemaImages")
	private String cinemaImages;

	//经纬度,纬度和经度以英文逗号隔开，格式：lat,lng
	@XStreamAlias("LatLng")
	private String latLng;

	//是否支持停车场,0:不提供1:提供
	@XStreamAlias("IsHasPark")
	private String isHasPark;

	//停车场说明
	@XStreamAlias("ParkMemo")
	private String parkMemo;

	//是否支持刷卡,0:不提供1:提供
	@XStreamAlias("IsSupportUnionPay")
	private String isSupportUnionPay;

	//是否支持3D影片,0:不提供1:提供
	@XStreamAlias("IsSupport3D")
	private String isSupport3D;

	//是否提供卖品,0:不提供1:提供
	@XStreamAlias("IsSupportGoods")
	private String isSupportGoods;

	//是否支持休息区,0:不提供1:提供
	@XStreamAlias("IsSupportRest")
	private String isSupportRest;

	//休息区说明
	@XStreamAlias("RestMemo")
	private String restMemo;

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

	public String getDeviceUrl() {
		return deviceUrl;
	}

	public void setDeviceUrl(String deviceUrl) {
		this.deviceUrl = deviceUrl;
	}

	public String getDeviceDesc() {
		return deviceDesc;
	}

	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSellFlag() {
		return sellFlag;
	}

	public void setSellFlag(String sellFlag) {
		this.sellFlag = sellFlag;
	}

	public String getCinemaImages() {
		return cinemaImages;
	}

	public void setCinemaImages(String cinemaImages) {
		this.cinemaImages = cinemaImages;
	}

	public String getLatLng() {
		return latLng;
	}

	public void setLatLng(String latLng) {
		this.latLng = latLng;
	}

	public String getIsHasPark() {
		return isHasPark;
	}

	public void setIsHasPark(String isHasPark) {
		this.isHasPark = isHasPark;
	}

	public String getParkMemo() {
		return parkMemo;
	}

	public void setParkMemo(String parkMemo) {
		this.parkMemo = parkMemo;
	}

	public String getIsSupportUnionPay() {
		return isSupportUnionPay;
	}

	public void setIsSupportUnionPay(String isSupportUnionPay) {
		this.isSupportUnionPay = isSupportUnionPay;
	}

	public String getIsSupport3D() {
		return isSupport3D;
	}

	public void setIsSupport3D(String isSupport3D) {
		this.isSupport3D = isSupport3D;
	}

	public String getIsSupportGoods() {
		return isSupportGoods;
	}

	public void setIsSupportGoods(String isSupportGoods) {
		this.isSupportGoods = isSupportGoods;
	}

	public String getIsSupportRest() {
		return isSupportRest;
	}

	public void setIsSupportRest(String isSupportRest) {
		this.isSupportRest = isSupportRest;
	}

	public String getRestMemo() {
		return restMemo;
	}

	public void setRestMemo(String restMemo) {
		this.restMemo = restMemo;
	}

	public String getCinemaNo() {
		return cinemaNo;
	}

	public void setCinemaNo(String cinemaNo) {
		this.cinemaNo = cinemaNo;
	}

}
