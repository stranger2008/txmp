package com.xingfugo.business.module;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 菜单实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 03 16:36:37 CST 2014
 */
public class Sysmenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String menu_id;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	@NotBlank
	@Length(max=20)
	private String menu_name;
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	@NotBlank
	@Length(max=3)
	private String syscode;
	public String getSyscode() {
		return syscode;
	}
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}
	
	@NotBlank
	@Length(max=10)
	private String up_menu_id;
	public String getUp_menu_id() {
		return up_menu_id;
	}
	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}
	
	@NotBlank
	@DecimalMin(value="1")
	@Length(max=1)
	private String menu_level;
	public String getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(String menu_level) {
		this.menu_level = menu_level;
	}
	
	@NotBlank
	@DecimalMin(value="0")
	@DecimalMax(value="2147483647")
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	@NotBlank
	@Length(max=1)
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	@NotBlank
	@Length(max=100)
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@NotBlank
	@Length(max=10)
	private String target;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	@Length(max=600)
	private String oper_basic_right;
	public String getOper_basic_right() {
		return oper_basic_right;
	}
	public void setOper_basic_right(String oper_basic_right) {
		this.oper_basic_right = oper_basic_right;
	}
	
	private String up_menu_name;
	private String para_key;
	public String getUp_menu_name() {
		return up_menu_name;
	}
	public void setUp_menu_name(String up_menu_name) {
		this.up_menu_name = up_menu_name;
	}
	public String getPara_key() {
		return para_key;
	}
	public void setPara_key(String para_key) {
		this.para_key = para_key;
	}

}

