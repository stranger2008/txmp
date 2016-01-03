package com.xingfugo.web.client.module;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import com.xingfugo.validator.Mobile;

public class FindPwd {	
	@NotBlank
	@Length(max=17)
	@Mobile
	private String cellphone;
	
	@NotBlank
	@Length(max=4)
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

