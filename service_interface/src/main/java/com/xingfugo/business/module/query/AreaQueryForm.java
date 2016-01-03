package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 地区管理表单查询实体
 * @author 创建人 史倩倩
 * @date 创建日期 Tue Sep 02 14:10:46 CST 2014
 */

public class AreaQueryForm extends BasePageForm{
	
	private String area_name;
	
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	private String up_area_id;

	public String getUp_area_id() {
		return up_area_id;
	}
	public void setUp_area_id(String up_area_id) {
		this.up_area_id = up_area_id;
	}
	
	public String area_level;

	public String getArea_level() {
		return area_level;
	}
	public void setArea_level(String area_level) {
		this.area_level = area_level;
	}
	public String is_open;

	public String getIs_open() {
		return is_open;
	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
	}

}

