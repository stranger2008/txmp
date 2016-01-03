package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

public class GoodsreturnQueryForm extends BasePageForm{
	private String keywords;
	private String user_id;
	private String cust_id;
	private String biz_type;
	//商品退换货标识
	private String trade_id;
	//查询发布时间：如：一天内，一个星期内，一个月内
	private String sear_days;
	
	private String info_state;
	
	public String getBiz_type() {
		return biz_type;
	}

	public void setBiz_type(String biz_type) {
		this.biz_type = biz_type;
	}

	public String getSear_days() {
		return sear_days;
	}

	public void setSear_days(String sear_days) {
		this.sear_days = sear_days;
	}

	public String getInfo_state() {
		return info_state;
	}

	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}

}
