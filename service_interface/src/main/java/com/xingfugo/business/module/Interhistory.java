package com.xingfugo.business.module;

import java.io.Serializable;
/**
 * @function 功能 会员积分异动列表实体
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 17:03:43 CST 2014
 */
public class Interhistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String credit_in;
	public String getCredit_in() {
		return credit_in;
	}
	public void setCredit_in(String credit_in) {
		this.credit_in = credit_in;
	}
	
	private String credit_out;
	public String getCredit_out() {
		return credit_out;
	}
	public void setCredit_out(String credit_out) {
		this.credit_out = credit_out;
	}
	
	private String credit_remain;
	public String getCredit_remain() {
		return credit_remain;
	}
	public void setCredit_remain(String credit_remain) {
		this.credit_remain = credit_remain;
	}
	
	private String credit_action;
	public String getCredit_action() {
		return credit_action;
	}
	public void setCredit_action(String credit_action) {
		this.credit_action = credit_action;
	}
	
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	

}

