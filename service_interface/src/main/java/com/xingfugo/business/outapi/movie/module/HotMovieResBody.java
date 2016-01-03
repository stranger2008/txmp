package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class HotMovieResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<HotMovie> hotMovieList;

	public List<HotMovie> getHotMovieList() {
		return hotMovieList;
	}

	public void setHotMovieList(List<HotMovie> hotMovieList) {
		this.hotMovieList = hotMovieList;
	}
}
