package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 模板表单查询实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 09:04:58 CST 2014
 */

public class Sms_email_templateQueryForm extends BasePageForm{
	//0：短信 1：邮件
	private String temp_type;

	public String getTemp_type() {
		return temp_type;
	}

	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}
	
}

