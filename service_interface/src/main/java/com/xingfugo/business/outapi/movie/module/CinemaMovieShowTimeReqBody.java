package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CinemaMovieShowTimeReqBody {
	@XStreamAlias("cinemaNo")
	private String cinemaNo;

	@XStreamAlias("filmNo")
	private String filmNo;

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

}
