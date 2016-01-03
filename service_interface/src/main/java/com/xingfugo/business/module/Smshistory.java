package com.xingfugo.business.module;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * @function 功能 记录短信发送历史记录实体
 * @date 创建日期 Tue Jul 19 16:23:30 CST 2011
 */
public class Smshistory implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	String phoneattr;
	public String getPhoneattr() {
		return phoneattr;
	}
	public void setPhoneattr(String phoneattr) {
		this.phoneattr = phoneattr;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	Timestamp send_date;
	public Timestamp getSend_date() {
		return send_date;
	}
	public void setSend_date(Timestamp send_date) {
		this.send_date = send_date;
	}
	
	String sms_desc;
	public String getSms_desc() {
		return sms_desc;
	}
	public void setSms_desc(String sms_desc) {
		this.sms_desc = sms_desc;
	}
}

