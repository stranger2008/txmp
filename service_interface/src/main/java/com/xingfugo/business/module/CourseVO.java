package com.xingfugo.business.module;

import java.util.List;

public class CourseVO extends Course{

	private List<Course_video> vlist;
	
	private List<Course> boundList;

	public List<Course_video> getVlist() {
		return vlist;
	}

	public void setVlist(List<Course_video> vlist) {
		this.vlist = vlist;
	}

	public List<Course> getBoundList() {
		return boundList;
	}

	public void setBoundList(List<Course> boundList) {
		this.boundList = boundList;
	}
	
	
	
	
}
