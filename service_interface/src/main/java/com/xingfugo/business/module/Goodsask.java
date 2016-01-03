package com.xingfugo.business.module;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * @function 功能 记录商品咨询信息实体
 * @date 创建日期 Fri Mar 23 11:24:44 CST 2012
 */
public class Goodsask implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer trade_id;
	public Integer getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(Integer trade_id) {
		this.trade_id = trade_id;
	}
	
	@NotNull
	private Integer goods_id;
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	private String c_type;
	public String getC_type() {
		return c_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	@NotNull
	private Integer user_id;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	@NotEmpty
	@Length(max=500)
	private String c_content;
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	
	private Timestamp c_date;
	public Timestamp getC_date() {
		return c_date;
	}
	public void setC_date(Timestamp c_date) {
		this.c_date = c_date;
	}
	
	private String re_cust_id;
	public String getRe_cust_id() {
		return re_cust_id;
	}
	public void setRe_cust_id(String re_cust_id) {
		this.re_cust_id = re_cust_id;
	}
	@Length(max=500)
	private String re_content;
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	
	private String re_date;
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	
	private String re_man;
	public String getRe_man() {
		return re_man;
	}
	public void setRe_man(String re_man) {
		this.re_man = re_man;
	}
	
	private String is_enable;
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	
	private String star;
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	
}

