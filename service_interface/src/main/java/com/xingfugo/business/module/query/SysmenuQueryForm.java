package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 菜单表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 03 16:36:37 CST 2014
 */

public class SysmenuQueryForm extends BasePageForm{
	
	private String syscode;
	private String up_menu_id;

	public String getSyscode() {
		return syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	public String getUp_menu_id() {
		return up_menu_id;
	}

	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}
	
}

