package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 基本设置表单查询实体
 * @author 创建人 刘香玲
 * @date 创建日期 Mon Sep 01 11:39:25 CST 2014
 */

public class SysconfigQueryForm extends BasePageForm{
	
	private String var_group;
	public String getVar_group() {
		return var_group;
	}
	public void setVar_group(String var_group) {
		this.var_group = var_group;
	}
	
}

