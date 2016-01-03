package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 实体分类表单查询实体
 * @author 创建人 刘香玲
 * @date 创建日期 Tue Sep 02 14:07:15 CST 2014
 */

public class CategoryQueryForm extends BasePageForm{
	/**
	 * 分类名称
	 */
	private String cat_name;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	/**
	 * 上级分类id
	 */
	private String up_cat_id;
	public String getUp_cat_id() {
		return up_cat_id;
	}
	public void setUp_cat_id(String up_cat_id) {
		this.up_cat_id = up_cat_id;
	}
	
	/**
	 * 分类级别
	 */
	private String cat_level;
	public String getCat_level() {
		return cat_level;
	}
	public void setCat_level(String cat_level) {
		this.cat_level = cat_level;
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
	
	/**
	 * 是否显示
	 */
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
}

