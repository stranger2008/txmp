package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 等级配置表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 24 10:36:12 CST 2014
 */

public class MemberlevelQueryForm extends BasePageForm{
	
	private String level_code;
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}
	
	private String level_name;
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	
}

