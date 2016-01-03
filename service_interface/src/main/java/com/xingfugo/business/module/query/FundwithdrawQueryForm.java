package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 资金提现表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Thu Oct 16 16:18:00 CST 2014
 */

public class FundwithdrawQueryForm extends BasePageForm{
	
	private String order_no;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	private String account_no;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	private String trans_amt;
	public String getTrans_amt() {
		return trans_amt;
	}
	public void setTrans_amt(String trans_amt) {
		this.trans_amt = trans_amt;
	}
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	private String min_trans_amt;
	
	private String max_trans_amt;
	public String getMin_trans_amt() {
		return min_trans_amt;
	}
	public void setMin_trans_amt(String min_trans_amt) {
		this.min_trans_amt = min_trans_amt;
	}
	public String getMax_trans_amt() {
		return max_trans_amt;
	}
	public void setMax_trans_amt(String max_trans_amt) {
		this.max_trans_amt = max_trans_amt;
	}
	
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	private Integer user_id;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}

