package com.xingfugo.business.module;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * @function 功能 基本设置实体
 * @author 创建人 刘香玲
 * @date 创建日期 Mon Sep 01 11:39:25 CST 2014
 */
public class Sysconfig implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String var_id;
	public String getVar_id() {
		return var_id;
	}
	public void setVar_id(String var_id) {
		this.var_id = var_id;
	}
	
	/**
	 * 变量名称
	 */
	@NotBlank
	@Length(max=20)
	private String var_name;
	public String getVar_name() {
		return var_name;
	}
	public void setVar_name(String var_name) {
		this.var_name = var_name;
	}
	/**
	 * 变量值
	 */
	@NotBlank
	@Length(max=800)
	private String var_value;
	public String getVar_value() {
		return var_value;
	}
	public void setVar_value(String var_value) {
		this.var_value = var_value;
	}
	/**
	 * 变量说明
	 */
	@NotBlank
	@Length(max=300)
	private String var_desc;
	public String getVar_desc() {
		return var_desc;
	}
	public void setVar_desc(String var_desc) {
		this.var_desc = var_desc;
	}
	/**
	 * 所属组
	 */
	private String var_group;
	public String getVar_group() {
		return var_group;
	}
	public void setVar_group(String var_group) {
		this.var_group = var_group;
	}
	/**
	 * 变量类型
	 */
	private String var_type;
	public String getVar_type() {
		return var_type;
	}
	public void setVar_type(String var_type) {
		this.var_type = var_type;
	}
	/**
	 * 系统变量
	 */
	private String val_sys;
	public String getVal_sys() {
		return val_sys;
	}
	public void setVal_sys(String val_sys) {
		this.val_sys = val_sys;
	}
	/**
	 * 所属模块
	 */
	private String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
}

