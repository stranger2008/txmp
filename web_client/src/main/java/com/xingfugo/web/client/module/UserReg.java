package com.xingfugo.web.client.module;

import org.hibernate.validator.constraints.NotBlank;

import com.xingfugo.business.module.User;

//用户注册
public class UserReg extends User{
	@NotBlank
	private String sure_passwd;
	@NotBlank
	private String check_code;
	public String getSure_passwd() {
		return sure_passwd;
	}
	public void setSure_passwd(String sure_passwd) {
		this.sure_passwd = sure_passwd;
	}
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	
}
