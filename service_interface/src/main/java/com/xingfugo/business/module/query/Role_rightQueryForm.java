package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 权限表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Thu Sep 04 16:42:37 CST 2014
 */

public class Role_rightQueryForm extends BasePageForm{
	
	private String right_name;
	public String getRight_name() {
		return right_name;
	}
	public void setRight_name(String right_name) {
		this.right_name = right_name;
	}
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private String syscode;
	public String getSyscode() {
		return syscode;
	}
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}
	
	
}

