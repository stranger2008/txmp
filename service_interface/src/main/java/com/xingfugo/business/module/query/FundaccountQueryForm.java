package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 会员资金表表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:24:02 CST 2014
 */

public class FundaccountQueryForm extends BasePageForm{
	
	private String cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	private String cust_type;
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	private String account_no;
	private String min_fund_num;
	private String max_fund_num;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getMin_fund_num() {
		return min_fund_num;
	}
	public void setMin_fund_num(String min_fund_num) {
		this.min_fund_num = min_fund_num;
	}
	public String getMax_fund_num() {
		return max_fund_num;
	}
	public void setMax_fund_num(String max_fund_num) {
		this.max_fund_num = max_fund_num;
	}
	
}

