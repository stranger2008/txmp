package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

public class GoodsaskQueryForm extends BasePageForm{
	private String user_id;
	
	private Integer goods_id;
	
	private Integer cust_id;
	//商品名称
	private String goods_name;
	//咨询内容
	private String c_content;
	//回复内容
	private String re_content;
	//咨询时间
	private String c_date_begin;
	private String c_date_end;
	//回复时间
	private String re_date_begin;
	private String re_date_end;
	//查询发布时间：如：一天内，一个星期内，一个月内
	public Integer sear_days;
	
	public Integer getSear_days() {
		return sear_days;
	}

	public void setSear_days(Integer sear_days) {
		this.sear_days = sear_days;
	}

	public Integer getCust_id() {
		return cust_id;
	}

	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getRe_content() {
		return re_content;
	}

	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}

	public String getC_date_begin() {
		return c_date_begin;
	}

	public void setC_date_begin(String c_date_begin) {
		this.c_date_begin = c_date_begin;
	}

	public String getC_date_end() {
		return c_date_end;
	}

	public void setC_date_end(String c_date_end) {
		this.c_date_end = c_date_end;
	}

	public String getRe_date_begin() {
		return re_date_begin;
	}

	public void setRe_date_begin(String re_date_begin) {
		this.re_date_begin = re_date_begin;
	}

	public String getRe_date_end() {
		return re_date_end;
	}

	public void setRe_date_end(String re_date_end) {
		this.re_date_end = re_date_end;
	}
	
	
}
