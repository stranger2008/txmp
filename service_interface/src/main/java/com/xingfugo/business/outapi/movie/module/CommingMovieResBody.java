package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CommingMovieResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<CommingMovie> commingMovieList;

	public List<CommingMovie> getCommingMovieList() {
		return commingMovieList;
	}

	public void setCommingMovieList(List<CommingMovie> commingMovieList) {
		this.commingMovieList = commingMovieList;
	}

}
