package com.xingfugo.business.outapi.movie.module;

import java.util.ArrayList;
import java.util.List;

public class AreaTheater extends Area {
	private List<Cinema> theaterList = new ArrayList<Cinema>();

	public List<Cinema> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Cinema> theaterList) {
		this.theaterList = theaterList;
	}
	
	public void addTheater(Cinema cinema){
		this.theaterList.add(cinema);
	}
}
