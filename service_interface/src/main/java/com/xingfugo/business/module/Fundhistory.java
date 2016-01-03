package com.xingfugo.business.module;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @function 功能 会员资金异动表实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:27:07 CST 2014
 */
public class Fundhistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String account_no;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	private BigDecimal fund_in;
	public BigDecimal getFund_in() {
		return fund_in;
	}
	public void setFund_in(BigDecimal fund_in) {
		this.fund_in = fund_in;
	}
	
	private BigDecimal fund_out;
	public BigDecimal getFund_out() {
		return fund_out;
	}
	public void setFund_out(BigDecimal fund_out) {
		this.fund_out = fund_out;
	}
	
	private BigDecimal balance;
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	private String action_type;
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	private Date in_date;
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	
	private Integer user_id;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	private Integer cust_id;
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	

}

