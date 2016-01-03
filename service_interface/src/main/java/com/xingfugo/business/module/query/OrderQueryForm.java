package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

public class OrderQueryForm extends BasePageForm{
	private Integer cust_id;
	private Integer user_id;
	
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
