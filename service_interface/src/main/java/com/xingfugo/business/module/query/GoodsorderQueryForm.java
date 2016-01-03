package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

public class GoodsorderQueryForm extends BasePageForm{
	
	private String order_id;
	/**
	 * 卖家会员标识
	 */
	private String cust_id;
	/**
	 * 买家会员标识
	 */
	private String user_id;
	
	//查询发布时间：如：一天内，一个星期内，一个月内
	private String sear_days;
	
	//订单多少天支持退换货
	private String return_time;
	
	private String order_state;
	
	private String keywords;
	
	private String order_type;
	
	private String order_time;
	
	private String order_state_name;
	
	private String tatal_amount;
	
	private String is_del;
	
	
	public String getIs_del() {
		return is_del;
	}

	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}

	public String getReturn_time() {
		return return_time;
	}

	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	public String getSear_days() {
		return sear_days;
	}

	public void setSear_days(String sear_days) {
		this.sear_days = sear_days;
	}

	/**
	 * 获取卖家会员标识
	 * @return
	 */
	public String getCust_id() {
		return cust_id;
	}

	/**
	 * 设置卖家会员标识
	 * @param user_id
	 */
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	/**
	 * 获取买家会员标识
	 * @return
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * 设置买家会员标识
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public String getOrder_state_name() {
		return order_state_name;
	}

	public void setOrder_state_name(String order_state_name) {
		this.order_state_name = order_state_name;
	}

	public String getTatal_amount() {
		return tatal_amount;
	}

	public void setTatal_amount(String tatal_amount) {
		this.tatal_amount = tatal_amount;
	}
	
	private String goods_name;
	private String order_time_begin;
	private String order_time_end;

	public String getOrder_time_begin() {
		return order_time_begin;
	}

	public void setOrder_time_begin(String order_time_begin) {
		this.order_time_begin = order_time_begin;
	}

	public String getOrder_time_end() {
		return order_time_end;
	}

	public void setOrder_time_end(String order_time_end) {
		this.order_time_end = order_time_end;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	
}
