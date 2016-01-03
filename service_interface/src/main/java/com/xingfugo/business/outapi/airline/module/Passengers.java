package com.xingfugo.business.outapi.airline.module;

import org.hibernate.validator.constraints.NotBlank;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Passengers {
	//乘机人标识
	private Integer passenger_id;
	//会员标识
	@NotBlank
	@XStreamAlias("user_id")
	private Integer user_id;
	//乘客姓名
	@NotBlank
	@XStreamAlias("name")
	private String name;
	//乘客类型
	@NotBlank
	@XStreamAlias("type")
	private String type;
	//证件类型
	@NotBlank
	@XStreamAlias("identityType")
	private String identityType;
	//证件号码
	@NotBlank
	@XStreamAlias("identityNo")
	private String identityNo;
	//生日
	@NotBlank
	@XStreamAlias("birthday")
	private String birthday;
	//默认乘机人
	@XStreamAlias("defaultbyair")
	private String defaultbyair;
	public Integer getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(Integer passenger_id) {
		this.passenger_id = passenger_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDefaultbyair() {
		return defaultbyair;
	}
	public void setDefaultbyair(String defaultbyair) {
		this.defaultbyair = defaultbyair;
	}
	
	
	
}
