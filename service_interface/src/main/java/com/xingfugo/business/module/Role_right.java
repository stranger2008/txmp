package com.xingfugo.business.module;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 权限实体
 * @author 创建人 陈显革
 * @date 创建日期 Thu Sep 04 16:42:37 CST 2014
 */
public class Role_right implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String right_id;
	public String getRight_id() {
		return right_id;
	}
	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}
	
	@NotBlank
	@Length(max=20)
	private String right_name;
	public String getRight_name() {
		return right_name;
	}
	public void setRight_name(String right_name) {
		this.right_name = right_name;
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
	@Length(max=100)
	private String menu_attr;
	public String getMenu_attr() {
		return menu_attr;
	}
	public void setMenu_attr(String menu_attr) {
		this.menu_attr = menu_attr;
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
	
	@Length(max=200)
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

