package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class AreaListResBody {
	
	@XStreamImplicit(itemFieldName="item")
	private List<Area> areaList;

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
}
