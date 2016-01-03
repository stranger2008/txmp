package com.xingfugo.business.module;

import java.io.Serializable;
/**
 * @function 功能 商家自定义分类实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Sat Sep 27 16:03:05 CST 2014
 */
public class Membercat implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	private String cat_name;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String up_cat_id;
	public String getUp_cat_id() {
		return up_cat_id;
	}
	public void setUp_cat_id(String up_cat_id) {
		this.up_cat_id = up_cat_id;
	}
	
	private String cat_level;
	public String getCat_level() {
		return cat_level;
	}
	public void setCat_level(String cat_level) {
		this.cat_level = cat_level;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	

}

