package com.xingfugo.business.module;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 物流运送信息实体
 * @author 创建人 刘香玲
 * @date 创建日期 Fri Sep 26 19:19:33 CST 2014
 */
public class Inc_shipinfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String link_id;
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	@NotBlank
	@Length(max=1)
	private String ship_id;
	public String getShip_id() {
		return ship_id;
	}
	private String ship_name;
	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}
	@NotBlank
	@Length(max=30)
	private String ship_no;
	public String getShip_no() {
		return ship_no;
	}
	public void setShip_no(String ship_no) {
		this.ship_no = ship_no;
	}
	@Length(max=600)
	private String ship_desc;
	public String getShip_desc() {
		return ship_desc;
	}
	public void setShip_desc(String ship_desc) {
		this.ship_desc = ship_desc;
	}
	@Length(max=600)
	private String ship_img;
	public String getShip_img() {
		return ship_img;
	}
	public void setShip_img(String ship_img) {
		this.ship_img = ship_img;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String user_type;
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	private Date in_date;
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	public String getShip_name() {
		return ship_name;
	}
	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}
	

}

