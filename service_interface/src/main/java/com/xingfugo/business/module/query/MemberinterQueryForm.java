package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 会员积分表单查询实体
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 15:59:56 CST 2014
 */

public class MemberinterQueryForm extends BasePageForm{
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	private String min_credit_num;
	
	private String max_credit_num;
	public String getMin_credit_num() {
		return min_credit_num;
	}
	public void setMin_credit_num(String min_credit_num) {
		this.min_credit_num = min_credit_num;
	}
	public String getMax_credit_num() {
		return max_credit_num;
	}
	public void setMax_credit_num(String max_credit_num) {
		this.max_credit_num = max_credit_num;
	}
	
	
}

