package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 用户表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Fri Sep 05 13:53:56 CST 2014
 */

public class SysuserQueryForm extends BasePageForm{
	
	private String user_name;
	private String real_name;
	private String state;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

