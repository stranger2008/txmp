package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 邮件发送记录表单查询实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 10:56:02 CST 2014
 */

public class EmailhistoryQueryForm extends BasePageForm{
	
	private String email_attr;
	public String getEmail_attr() {
		return email_attr;
	}
	public void setEmail_attr(String email_attr) {
		this.email_attr = email_attr;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String in_date_begin;
	
	private String in_date_end;
	
	public String getIn_date_begin() {
		return in_date_begin;
	}
	public void setIn_date_begin(String in_date_begin) {
		this.in_date_begin = in_date_begin;
	}
	public String getIn_date_end() {
		return in_date_end;
	}
	public void setIn_date_end(String in_date_end) {
		this.in_date_end = in_date_end;
	}
	
	
	
	
}

