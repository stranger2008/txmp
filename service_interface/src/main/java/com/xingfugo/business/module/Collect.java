package com.xingfugo.business.module;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class Collect {
	private Integer coll_id;
	private Timestamp in_date;
	@NotNull
	private String info_id;
	private String info_type;
	@NotNull
	private String user_id;
	public Integer getColl_id() {
		return coll_id;
	}
	public void setColl_id(Integer coll_id) {
		this.coll_id = coll_id;
	}
	public Timestamp getIn_date() {
		return in_date;
	}
	public void setIn_date(Timestamp in_date) {
		this.in_date = in_date;
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
