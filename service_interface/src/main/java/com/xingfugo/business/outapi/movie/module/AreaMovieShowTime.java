package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 地区影片排期
 */
public class AreaMovieShowTime {
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
	
	@XStreamAlias("PhoneNo")
	private String phoneNo;
	
	@XStreamAlias("AverageDegree")
	private String averageDegree;
	
	//经纬度, 纬度和经度以英文逗号隔开，格式：lat,lng
	@XStreamAlias("LatLng")
	private String latLng;
	
	@XStreamAlias("SeqNo")
	private String seqNo;
	
	@XStreamAlias("ShowDate")
	private String showDate;
	
	//放映类型,0-2D，1-3D，2-4D，3-IMAX
	@XStreamAlias("ShowTime")
	private String showTime;
	
	@XStreamAlias("ShowType")
	private String showType;
	
	@XStreamAlias("Language")
	private String language;
	
	@XStreamAlias("HallNo")
	private String hallNo;
	
	@XStreamAlias("HallName")
	private String hallName;
	
	@XStreamAlias("SeatNum")
	private int seatNum;
	
	@XStreamAlias("CinemaPrice")
	private String cinemaPrice;
	
	@XStreamAlias("SalePrice")
	private String salePrice;

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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAverageDegree() {
		return averageDegree;
	}

	public void setAverageDegree(String averageDegree) {
		this.averageDegree = averageDegree;
	}

	public String getLatLng() {
		return latLng;
	}

	public void setLatLng(String latLng) {
		this.latLng = latLng;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHallNo() {
		return hallNo;
	}

	public void setHallNo(String hallNo) {
		this.hallNo = hallNo;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public String getCinemaPrice() {
		return cinemaPrice;
	}

	public void setCinemaPrice(String cinemaPrice) {
		this.cinemaPrice = cinemaPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	
}
