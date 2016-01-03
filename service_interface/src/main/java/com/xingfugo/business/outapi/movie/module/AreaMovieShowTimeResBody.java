package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class AreaMovieShowTimeResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<AreaMovieShowTime> areaMovieShowTimeList;

	public List<AreaMovieShowTime> getAreaMovieShowTimeList() {
		return areaMovieShowTimeList;
	}

	public void setAreaMovieShowTimeList(
			List<AreaMovieShowTime> areaMovieShowTimeList) {
		this.areaMovieShowTimeList = areaMovieShowTimeList;
	}
}
