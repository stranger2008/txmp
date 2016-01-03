package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 友情链接表单查询实体
 * @author 创建人 李良林
 * @date 创建日期 Thu Aug 28 10:22:12 CST 2014
 */

public class LinkQueryForm extends BasePageForm{
	
	private String link_name;
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

