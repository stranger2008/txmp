package com.xingfugo.business.module;

import java.io.Serializable;
/**
 * @function 功能 机票订单详情实体
 * @author 创建人 王益龙
 * @date 创建日期 Thu Aug 28 11:36:30 CST 2014
 */
public class Api_air_orderdetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String orgCity;
	public String getOrgCity() {
		return orgCity;
	}
	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}
	
	private String dstCity;
	public String getDstCity() {
		return dstCity;
	}
	public void setDstCity(String dstCity) {
		this.dstCity = dstCity;
	}
	
	private String flightNo;
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	private String depTime;
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	
	private String arriTime;
	public String getArriTime() {
		return arriTime;
	}
	public void setArriTime(String arriTime) {
		this.arriTime = arriTime;
	}
	
	private String passenger_id;
	public String getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(String passenger_id) {
		this.passenger_id = passenger_id;
	}
	
	private String contact_id;
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	
	private String is_need_nvoice;
	public String getIs_need_nvoice() {
		return is_need_nvoice;
	}
	public void setIs_need_nvoice(String is_need_nvoice) {
		this.is_need_nvoice = is_need_nvoice;
	}
	
	private String journeysheet_id;
	public String getJourneysheet_id() {
		return journeysheet_id;
	}
	public void setJourneysheet_id(String journeysheet_id) {
		this.journeysheet_id = journeysheet_id;
	}
	

}

