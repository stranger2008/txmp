package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 店铺级别表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 24 11:26:37 CST 2014
 */

public class LevelinfoQueryForm extends BasePageForm{
	
	private String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
}

