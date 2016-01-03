package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 系统数据字典表单查询实体
 * @author 创建人 史倩倩
 * @date 创建日期 Mon Sep 01 11:34:06 CST 2014
 */

public class CommparaQueryForm extends BasePageForm{
	
	private String para_name;

	public String getPara_name() {
		return para_name;
	}

	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}
	private String para_code;
	private String para_key;
	private String para_value;

	public String getPara_code() {
		return para_code;
	}

	public void setPara_code(String para_code) {
		this.para_code = para_code;
	}

	public String getPara_key() {
		return para_key;
	}

	public void setPara_key(String para_key) {
		this.para_key = para_key;
	}

	public String getPara_value() {
		return para_value;
	}

	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}
	
}

