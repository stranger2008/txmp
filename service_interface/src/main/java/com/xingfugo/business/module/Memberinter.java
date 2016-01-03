package com.xingfugo.business.module;

import java.io.Serializable;
/**
 * @function 功能 会员积分实体
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 15:59:56 CST 2014
 */
public class Memberinter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String credit_sum;
	public String getCredit_sum() {
		return credit_sum;
	}
	public void setCredit_sum(String credit_sum) {
		this.credit_sum = credit_sum;
	}
	
	private String credit_usable;
	public String getCredit_usable() {
		return credit_usable;
	}
	public void setCredit_usable(String credit_usable) {
		this.credit_usable = credit_usable;
	}
	
	private String credit_exceed;
	public String getCredit_exceed() {
		return credit_exceed;
	}
	public void setCredit_exceed(String credit_exceed) {
		this.credit_exceed = credit_exceed;
	}
	
	private String credit_freeze;
	public String getCredit_freeze() {
		return credit_freeze;
	}
	public void setCredit_freeze(String credit_freeze) {
		this.credit_freeze = credit_freeze;
	}
	

}

