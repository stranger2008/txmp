package com.xingfugo.business.module;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * @function 功能 系统数据字典实体
 * @author 创建人 史倩倩
 * @date 创建日期 Mon Sep 01 11:34:06 CST 2014
 */
public class Commpara implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String para_id;
	public String getPara_id() {
		return para_id;
	}
	public void setPara_id(String para_id) {
		this.para_id = para_id;
	}
	@NotBlank
	@Length(max=20)
	private String para_code;
	public String getPara_code() {
		return para_code;
	}
	public void setPara_code(String para_code) {
		this.para_code = para_code;
	}
	
	@NotBlank
	@Length(max=20)
	private String para_name;
	public String getPara_name() {
		return para_name;
	}
	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}
	
	@NotBlank
	@Length(max=20)
	private String para_key;
	public String getPara_key() {
		return para_key;
	}
	public void setPara_key(String para_key) {
		this.para_key = para_key;
	}
	
	@NotBlank
	@Length(max=200)
	private String para_value;
	public String getPara_value() {
		return para_value;
	}
	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}
	
	@NotBlank
	@Length(max=11)
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	@Length(max=1)
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	

}

