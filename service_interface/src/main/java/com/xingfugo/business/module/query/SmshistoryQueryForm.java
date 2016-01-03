package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 短信发送记录表单查询实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 10:56:02 CST 2014
 */

public class SmshistoryQueryForm extends BasePageForm{
	private String phoneattr;
	
	private String sms_desc ;
	
	private String send_date_begin;
	
	private String send_date_end;

	public String getPhoneattr() {
		return phoneattr;
	}

	public void setPhoneattr(String phoneattr) {
		this.phoneattr = phoneattr;
	}

	public String getSms_desc() {
		return sms_desc;
	}

	public void setSms_desc(String sms_desc) {
		this.sms_desc = sms_desc;
	}

	public String getSend_date_begin() {
		return send_date_begin;
	}

	public void setSend_date_begin(String send_date_begin) {
		this.send_date_begin = send_date_begin;
	}

	public String getSend_date_end() {
		return send_date_end;
	}

	public void setSend_date_end(String send_date_end) {
		this.send_date_end = send_date_end;
	}
	
	
	
}

