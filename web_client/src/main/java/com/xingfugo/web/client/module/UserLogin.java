package com.xingfugo.web.client.module;



import com.xingfugo.business.module.User;

//用户登录
public class UserLogin extends User{
	
	private String check_code;
	
	//自动登录
	private String  autoLogin;
	
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	public String getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}
	
	
}
