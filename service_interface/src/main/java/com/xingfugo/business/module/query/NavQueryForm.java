package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 导航表单查询实体
 * @author 创建人 李良林
 * @date 创建日期 Thu Aug 28 13:10:44 CST 2014
 */

public class NavQueryForm extends BasePageForm{
	
	private String nav_name;
	public String getNav_name() {
		return nav_name;
	}
	public void setNav_name(String nav_name) {
		this.nav_name = nav_name;
	}
	
	private String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
}

