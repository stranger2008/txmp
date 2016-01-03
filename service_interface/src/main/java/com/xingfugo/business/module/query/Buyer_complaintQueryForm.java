package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 投诉记录信息表单查询实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Tue Oct 14 17:58:10 CST 2014
 */

public class Buyer_complaintQueryForm extends BasePageForm{
	
	private String com_type;
	public String getCom_type() {
		return com_type;
	}
	public void setCom_type(String com_type) {
		this.com_type = com_type;
	}
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private String user_id;
	
	private String cust_id;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String user_name;
	
	private String cust_name;
	
	private String in_date_begin;
	
	private String in_date_end;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getIn_date_begin() {
		return in_date_begin;
	}
	public void setIn_date_begin(String in_date_begin) {
		this.in_date_begin = in_date_begin;
	}
	public String getIn_date_end() {
		return in_date_end;
	}
	public void setIn_date_end(String in_date_end) {
		this.in_date_end = in_date_end;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	
	
	
}

