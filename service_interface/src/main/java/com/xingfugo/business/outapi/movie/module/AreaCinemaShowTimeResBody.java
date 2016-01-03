package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class AreaCinemaShowTimeResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<AreaCinemaShowTime> areaCinemaShowTimeList;

	public List<AreaCinemaShowTime> getAreaCinemaShowTimeList() {
		return areaCinemaShowTimeList;
	}

	public void setAreaCinemaShowTimeList(
			List<AreaCinemaShowTime> areaCinemaShowTimeList) {
		this.areaCinemaShowTimeList = areaCinemaShowTimeList;
	}

}
