package com.xingfugo.business.module;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 等级配置实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 24 10:36:12 CST 2014
 */
public class Memberlevel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String level_code;
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}
	
	@NotBlank
	@Length(max=20)
	private String level_name;
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	
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
	

}

