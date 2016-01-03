package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MovieBaseInfo {
	//影片编号
	@XStreamAlias("FilmNo")
	private String filmNo;
	
	//影片中文名称
	@XStreamAlias("FilmName")
	private String filmName;
	
	//影片图片
	@XStreamAlias("FrontImg")
	private String frontImg;
	
	//语言
	@XStreamAlias("Language")
	private String language;
	
	//片长,单位为分钟
	@XStreamAlias("Duration")
	private int duration;
	
	//影片类型,动作、科幻等
	@XStreamAlias("FilmType")
	private String filmType;
	
	//制片公司
	@XStreamAlias("Company")
	private String company;
	
	//主演
	@XStreamAlias("MainActors")
	private String mainActors;
	
	//首映日期,格式yyyy-MM-dd
	@XStreamAlias("FirstShowDate")
	private String firstShowDate;
	
	//原产地
	@XStreamAlias("OriginArea")
	private String originArea;
	
	//综合评分,精确到小数点后1位(影迷评分)
	@XStreamAlias("AverageDegree")
	private String averageDegree;
	
	//关注数量,默认0
	@XStreamAlias("FouseAmount")
	private int fouseAmount;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getFilmType() {
		return filmType;
	}

	public void setFilmType(String filmType) {
		this.filmType = filmType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMainActors() {
		return mainActors;
	}

	public void setMainActors(String mainActors) {
		this.mainActors = mainActors;
	}

	public String getFirstShowDate() {
		return firstShowDate;
	}

	public void setFirstShowDate(String firstShowDate) {
		this.firstShowDate = firstShowDate;
	}

	public String getOriginArea() {
		return originArea;
	}

	public void setOriginArea(String originArea) {
		this.originArea = originArea;
	}

	public String getAverageDegree() {
		return averageDegree;
	}

	public void setAverageDegree(String averageDegree) {
		this.averageDegree = averageDegree;
	}

	public int getFouseAmount() {
		return fouseAmount;
	}

	public void setFouseAmount(int fouseAmount) {
		this.fouseAmount = fouseAmount;
	}
}
