package com.xingfugo.business.outapi.movie.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xingfugo.util.Md5;

public class SeatTicketOrder {
	private static Logger LOG = LoggerFactory.getLogger(SeatTicketOrder.class);
	// 订单编号
	@XStreamAlias("OrderNo")
	private String orderNo;

	// 订单时间, 格式yyyyMMddHHmmss
	@XStreamAlias("OrderTime")
	private String orderTime;

	// 订单金额, 精确到小数点后两位，单位为元
	@XStreamAlias("OrderPrice")
	private String orderPrice;

	// 单价, 精确到小数点后两位，单位为元
	@XStreamAlias("Price")
	private String price;

	// 影片编号
	@XStreamAlias("FilmNo")
	private String filmNo;

	// 影院
	@XStreamAlias("CinemaNo")
	private String cinemaNo;

	// 排期
	@XStreamAlias("SeqNo")
	private String seqNo;

	// 座位
	@XStreamAlias("Seats")
	private String seats;

	// 签名
	@XStreamAlias("Sign")
	private String sign;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFilmNo() {
		return filmNo;
	}

	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}

	public String getCinemaNo() {
		return cinemaNo;
	}

	public void setCinemaNo(String cinemaNo) {
		this.cinemaNo = cinemaNo;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public boolean validSign(String appCode, String mobile){
		StringBuffer buf = new StringBuffer();
		buf.append(mobile);
		buf.append(orderNo);
		buf.append(orderTime);
		buf.append(price);
		buf.append(filmNo);
		buf.append(cinemaNo);
		buf.append(seqNo);
		buf.append(seats);
		buf.append(appCode);
		LOG.debug("验证订单返回信息:{}", buf.toString());
		String md5 = Md5.getMD5(buf.toString().getBytes());
		return md5.equalsIgnoreCase(sign);
	}
}
