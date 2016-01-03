package com.xingfugo.business.outapi.airline.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class AirportCode {
	@XStreamAlias("code")
	private String code;
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("city")
	private String city;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

}
