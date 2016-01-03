package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CinemaMovieShowTimeResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<CinemaMovieShowTime> cinemaMovieShowTimeList;

	public List<CinemaMovieShowTime> getCinemaMovieShowTimeList() {
		return cinemaMovieShowTimeList;
	}

	public void setCinemaMovieShowTimeList(
			List<CinemaMovieShowTime> cinemaMovieShowTimeList) {
		this.cinemaMovieShowTimeList = cinemaMovieShowTimeList;
	}

}
