package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 举报记录信息表单查询实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Mon Oct 20 11:43:07 CST 2014
 */

public class Buyer_reportQueryForm extends BasePageForm{
	
	private String r_type;
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String  cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	private String  goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String  in_date_begin;
	
	private String in_date_end;
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
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	
	
	
}

