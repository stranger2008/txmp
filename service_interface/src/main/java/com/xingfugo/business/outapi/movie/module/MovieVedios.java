package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class MovieVedios {
	@XStreamImplicit(itemFieldName="item")
	private List<MovieVedio> vedioList;

	public List<MovieVedio> getVedioList() {
		return vedioList;
	}

	public void setVedioList(List<MovieVedio> vedioList) {
		this.vedioList = vedioList;
	}
}
