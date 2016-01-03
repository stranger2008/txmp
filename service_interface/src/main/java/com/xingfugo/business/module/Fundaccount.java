package com.xingfugo.business.module;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @function 功能 会员资金表实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:24:02 CST 2014
 */
public class Fundaccount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String account_no;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	private Integer cust_id;
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	
	private String cust_type;
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	
	private BigDecimal fund_num;
	public BigDecimal getFund_num() {
		return fund_num;
	}
	public void setFund_num(BigDecimal fund_num) {
		this.fund_num = fund_num;
	}
	
	private BigDecimal use_num;
	public BigDecimal getUse_num() {
		return use_num;
	}
	public void setUse_num(BigDecimal use_num) {
		this.use_num = use_num;
	}
	
	private BigDecimal freeze_num;
	public BigDecimal getFreeze_num() {
		return freeze_num;
	}
	public void setFreeze_num(BigDecimal freeze_num) {
		this.freeze_num = freeze_num;
	}
	
	private String pay_passwd;
	public String getPay_passwd() {
		return pay_passwd;
	}
	public void setPay_passwd(String pay_passwd) {
		this.pay_passwd = pay_passwd;
	}
	
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	private Date in_date;
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

}