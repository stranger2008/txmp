package com.xingfugo.module;

import org.hibernate.validator.constraints.NotBlank;

import com.xingfugo.business.module.User;
import com.xingfugo.validator.Mobile;

//用户注册
public class UserReg{
	
	private Integer user_id;
	private String user_name;
	@Mobile
	private String cellphone;
	@NotBlank
	private String passwd;
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
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
