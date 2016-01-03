package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 物流运送信息表单查询实体
 * @author 创建人 刘香玲
 * @date 创建日期 Fri Sep 26 19:19:33 CST 2014
 */

public class Inc_shipinfoQueryForm extends BasePageForm{
	
	private String link_id;
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	
}

