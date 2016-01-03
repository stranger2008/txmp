package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MovieDetailReqBody {
	@XStreamAlias("filmNo")
	private String filmNo;
	
	@XStreamAlias("IsParater")
	private int IsParater;

	public String getFilmNo() {
		return filmNo;
	}

	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}

	public int getIsParater() {
		return IsParater;
	}

	public void setIsParater(int isParater) {
		IsParater = isParater;
	}
}
