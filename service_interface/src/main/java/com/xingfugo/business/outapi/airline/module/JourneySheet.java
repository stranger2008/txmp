package com.xingfugo.business.outapi.airline.module;

public class JourneySheet {
	
	//行程单标识
	private Integer journeysheet_id;
	//会员标识
	private Integer user_id;
	//姓名
	private String name;
	//手机号码
	private String phone;
	//收货地址
	private String area_attr;
	//详细地址
	private String address;
	//邮政编码
	private String zipcode;
	//固定电话
	private String tel;
	//默认配送信息
	private String distributioninfo;
	
	
	public Integer getJourneysheet_id() {
		return journeysheet_id;
	}
	public void setJourneysheet_id(Integer journeysheet_id) {
		this.journeysheet_id = journeysheet_id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDistributioninfo() {
		return distributioninfo;
	}
	public void setDistributioninfo(String distributioninfo) {
		this.distributioninfo = distributioninfo;
	}
}
