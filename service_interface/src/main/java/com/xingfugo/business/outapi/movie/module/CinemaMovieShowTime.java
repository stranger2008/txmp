package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 影院影片排期
 */
public class CinemaMovieShowTime {
	
	private String cinemaNo;
	private String filmNo;
	
	// 排期编号
	@XStreamAlias("SeqNo")
	private String seqNo;

	// 日期
	@XStreamAlias("ShowDate")
	private String showDate;

	// 放映时间
	@XStreamAlias("ShowTime")
	private String showTime;

	// 放映类型,0-2D，1-3D，2-4D，3-IMAX
	@XStreamAlias("ShowType")
	private String showType;

	// 语言
	@XStreamAlias("Language")
	private String language;

	// 影厅编号
	@XStreamAlias("HallNo")
	private String hallNo;

	// 影厅名称
	@XStreamAlias("HallName")
	private String hallName;

	// 影厅座位数
	@XStreamAlias("SeatNum")
	private int seatNum;

	// 门市价
	@XStreamAlias("CinemaPrice")
	private String cinemaPrice;

	// 销售价
	@XStreamAlias("SalePrice")
	private String salePrice;

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
	
	public String getShowTypeDesc() {
		if("0".equals(showType)){
			return "2D";
		}
		
		if("1".equals(showType)){
			return "3D";
		}
		
		if("2".equals(showType)){
			return "4D";
		}
		
		return "IMAX";
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

}
