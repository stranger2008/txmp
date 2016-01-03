package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 会员积分异动列表表单查询实体
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 17:03:43 CST 2014
 */

public class InterhistoryQueryForm extends BasePageForm{
	
	private String credit_action;
	public String getCredit_action() {
		return credit_action;
	}
	public void setCredit_action(String credit_action) {
		this.credit_action = credit_action;
	}
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	private String in_date_range;
	public String getIn_date_range() {
		return in_date_range;
	}
	public void setIn_date_range(String in_date_range) {
		this.in_date_range = in_date_range;
	}
	private String start_in_date;
	public String getStart_in_date() {
		return start_in_date;
	}
	public void setStart_in_date(String start_in_date) {
		this.start_in_date = start_in_date;
	}
	private String end_in_date;
	public String getEnd_in_date() {
		return end_in_date;
	}
	public void setEnd_in_date(String end_in_date) {
		this.end_in_date = end_in_date;
	}
	
}

