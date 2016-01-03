package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CinemaReqBody {
	@XStreamAlias("areaNo")
	private String areaNo;
	
	@XStreamAlias("filmNo")
	private String filmNo;

	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getFilmNo() {
		return filmNo;
	}
	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}
}
