package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 地区影院的影片排期
 */
public class AreaCinemaShowTime {
	// 影片编号
	@XStreamAlias("FilmNo")
	private String filmNo;

	// 影片名称
	@XStreamAlias("FilmName")
	private String filmName;

	// 影片图片
	@XStreamAlias("FrontImg")
	private String frontImg;

	// 综合评分
	@XStreamAlias("AverageDegree")
	private String averageDegree;

	// 主演
	@XStreamAlias("MainActors")
	private String mainActors;

	// 导演
	@XStreamAlias("Directors")
	private String directors;

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

	public String getFilmNo() {
		return filmNo;
	}

	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}

	public String getAverageDegree() {
		return averageDegree;
	}

	public void setAverageDegree(String averageDegree) {
		this.averageDegree = averageDegree;
	}

	public String getMainActors() {
		return mainActors;
	}

	public void setMainActors(String mainActors) {
		this.mainActors = mainActors;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
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

	@Override
	public boolean equals(Object o) {
		if(o == null){
			return false;
		}
		
		if(o instanceof AreaCinemaShowTime){
			AreaCinemaShowTime a = (AreaCinemaShowTime)o;
			return getFilmNo().equals(a.getFilmNo());
		}
		
		return false;
	}
}
