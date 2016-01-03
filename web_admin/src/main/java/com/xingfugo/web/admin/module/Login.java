package com.xingfugo.web.admin.module;

import org.hibernate.validator.constraints.NotBlank;

public class Login {
	@NotBlank
	private String user_name;
	@NotBlank
	private String passwd;
	@NotBlank
	private String check_code;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
}
