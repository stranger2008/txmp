package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CinemaResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<Cinema> cinemaList;

	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(List<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}
}
