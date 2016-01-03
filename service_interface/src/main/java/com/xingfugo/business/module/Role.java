package com.xingfugo.business.module;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 角色管理实体
 * @author 创建人 陈显革
 * @date 创建日期 Tue Sep 02 15:21:30 CST 2014
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String role_id;
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	@NotBlank
	@Length(max=20)
	private String role_name;
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	@NotBlank
	private String menu_right;
	public String getMenu_right() {
		return menu_right;
	}
	public void setMenu_right(String menu_right) {
		this.menu_right = menu_right;
	}
	
	private String oper_right;
	public String getOper_right() {
		return oper_right;
	}
	public void setOper_right(String oper_right) {
		this.oper_right = oper_right;
	}
	
	@Length(max=200)
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	

}

