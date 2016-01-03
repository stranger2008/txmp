package com.xingfugo.business.module;

import java.io.Serializable;
/**
 * @function 功能 邮件发送记录实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 10:56:02 CST 2014
 */
public class Emailhistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
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
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	

}

