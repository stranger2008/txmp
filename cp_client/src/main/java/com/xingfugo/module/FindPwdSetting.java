package com.xingfugo.module;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.xingfugo.validator.Mobile;


//找回密码 验证手机号码、验证码
public class FindPwdSetting{
	
	@NotBlank
	@Length(max=20)
	private String cellphone;
	@NotBlank
	@Length(max=6)
	private String check_code;

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCheck_code() {
		return check_code;
	}

	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	
	
	
	
	
	
	
}
