package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 角色管理表单查询实体
 * @author 创建人 陈显革
 * @date 创建日期 Tue Sep 02 15:21:30 CST 2014
 */

public class RoleQueryForm extends BasePageForm{
	private String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
}

