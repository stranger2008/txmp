package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 文章管理表单查询实体
 * @author 创建人 刘香玲
 * @date 创建日期 Thu Sep 04 17:05:56 CST 2014
 */

public class AboutusQueryForm extends BasePageForm{
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
}

