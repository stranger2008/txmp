package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Area {
	@XStreamAlias("AreaNo")
	private String no;
	
	@XStreamAlias("AreaName")
	private String name;
	
	@XStreamAlias("AreaLevel")
	private int level;

	@XStreamAlias("pAreaNo")
	private String parentNo;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null){
			return false;
		}
		
		if(o instanceof Area){
			Area a = (Area)o;
			return getNo().equals(a.getNo());
		}
		
		return false;
	}	
}
