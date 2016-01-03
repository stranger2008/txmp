package com.xingfugo.business.module;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 模板实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 09:04:58 CST 2014
 */
public class Sms_email_template implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Length(max=50)
	private String temp_code;
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	@NotBlank
	@Length(max=50)
	private String temp_name;
	public String getTemp_name() {
		return temp_name;
	}
	public void setTemp_name(String temp_name) {
		this.temp_name = temp_name;
	}
	@NotBlank
	@Length(max=5000)
	private String temp_con;
	public String getTemp_con() {
		return temp_con;
	}
	public void setTemp_con(String temp_con) {
		this.temp_con = temp_con;
	}
	@NotBlank
	@Length(max=300)
	private String tag_desc;
	public String getTag_desc() {
		return tag_desc;
	}
	public void setTag_desc(String tag_desc) {
		this.tag_desc = tag_desc;
	}
	
	private String temp_type;
	public String getTemp_type() {
		return temp_type;
	}
	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}
	

}

