package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 会员资金异动表表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:27:07 CST 2014
 */

public class FundhistoryQueryForm extends BasePageForm{
	
	private String account_no;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	private String fund_in;
	public String getFund_in() {
		return fund_in;
	}
	public void setFund_in(String fund_in) {
		this.fund_in = fund_in;
	}
	
	private String fund_out;
	public String getFund_out() {
		return fund_out;
	}
	public void setFund_out(String fund_out) {
		this.fund_out = fund_out;
	}
	
	private String balance;
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	private String action_type;
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	
	//会员类型
	private String cust_type;
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	
	//检索时间范围
	private String in_date_range;
	public String getIn_date_range() {
		return in_date_range;
	}
	public void setIn_date_range(String in_date_range) {
		this.in_date_range = in_date_range;
	}
	
	private String start_in_date;
	private String end_in_date;
	public String getStart_in_date() {
		return start_in_date;
	}
	public void setStart_in_date(String start_in_date) {
		this.start_in_date = start_in_date;
	}
	public String getEnd_in_date() {
		return end_in_date;
	}
	public void setEnd_in_date(String end_in_date) {
		this.end_in_date = end_in_date;
	}
	
	
}

