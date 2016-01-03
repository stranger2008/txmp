package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 商家信息表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Sat Sep 20 14:01:11 CST 2014
 */

public class MemberQueryForm extends BasePageForm{
	
	private String cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String apply_time;
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	
	private Boolean has_area_attr_name;
	public Boolean getHas_area_attr_name() {
		return has_area_attr_name;
	}
	public void setHas_area_attr_name(Boolean has_area_attr_name) {
		this.has_area_attr_name = has_area_attr_name;
	}
	
	private String apply_time_begin;
	private String apply_time_end;
	public String getApply_time_begin() {
		return apply_time_begin;
	}
	public void setApply_time_begin(String apply_time_begin) {
		this.apply_time_begin = apply_time_begin;
	}
	public String getApply_time_end() {
		return apply_time_end;
	}
	public void setApply_time_end(String apply_time_end) {
		this.apply_time_end = apply_time_end;
	}
	private String audit_status;
	public String getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}
	
}

