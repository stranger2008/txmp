package com.xingfugo.web.client.module;

import org.hibernate.validator.constraints.NotBlank;

public class Updatepasswd {
	@NotBlank
	private String old_passwd;
	@NotBlank
	private String new_passwd;
	public String getOld_passwd() {
		return old_passwd;
	}
	public void setOld_passwd(String old_passwd) {
		this.old_passwd = old_passwd;
	}
	public String getNew_passwd() {
		return new_passwd;
	}
	public void setNew_passwd(String new_passwd) {
		this.new_passwd = new_passwd;
	}
	
	
}
