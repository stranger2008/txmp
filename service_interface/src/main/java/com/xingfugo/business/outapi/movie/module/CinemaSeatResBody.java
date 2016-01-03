package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CinemaSeatResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<CinemaSeat> cinemaSeatList;

	public List<CinemaSeat> getCinemaSeatList() {
		return cinemaSeatList;
	}

	public void setCinemaSeatList(List<CinemaSeat> cinemaSeatList) {
		this.cinemaSeatList = cinemaSeatList;
	}
}
