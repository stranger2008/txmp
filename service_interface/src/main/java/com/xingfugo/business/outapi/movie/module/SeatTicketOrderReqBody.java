package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SeatTicketOrderReqBody {
	// 手机号
	@XStreamAlias("Mobile")
	private String mobile;

	// 订单类型, 1.	正常订单;2.	活动订单
	@XStreamAlias("OrderType")
	private int orderType;

	// 活动编号
	@XStreamAlias("ActNo")
	private String actNo;

	// 影院编号
	@XStreamAlias("CinemaNo")
	private String cinemaNo;

	// 影片编号
	@XStreamAlias("FilmNo")
	private String filmNo;

	// 排期编号
	@XStreamAlias("SeqNo")
	private String seqNo;

	// 影厅编号
	@XStreamAlias("HallNo")
	private String hallNo;

	// 单张票价
	@XStreamAlias("Price")
	private String price;

	// 座位数组
	@XStreamAlias("Seats")
	private String seats;

	// 城市编号
	@XStreamAlias("CityNo")
	private String cityNo;

	// 是否自定义价格,0-否 1-是  默认为否
	@XStreamAlias("IsCustomPrice")
	private String isCustomPrice;

	// 签名
	@XStreamAlias("Sign")
	private String sign;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getActNo() {
		return actNo;
	}

	public void setActNo(String actNo) {
		this.actNo = actNo;
	}

	public String getCinemaNo() {
		return cinemaNo;
	}

	public void setCinemaNo(String cinemaNo) {
		this.cinemaNo = cinemaNo;
	}

	public String getFilmNo() {
		return filmNo;
	}

	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getHallNo() {
		return hallNo;
	}

	public void setHallNo(String hallNo) {
		this.hallNo = hallNo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getIsCustomPrice() {
		return isCustomPrice;
	}

	public void setIsCustomPrice(String isCustomPrice) {
		this.isCustomPrice = isCustomPrice;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
