package com.xingfugo.module;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.xingfugo.validator.Mobile;


public class SetNewPwd{
	
	@NotBlank
	@Length(max=30)
	private String oldPwd;
	
	@NotBlank
	@Length(max=30)
	private String newPwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	

	
}
