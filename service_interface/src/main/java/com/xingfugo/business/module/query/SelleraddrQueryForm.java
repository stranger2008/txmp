package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 卖家发货地址表单查询实体
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Sep 24 19:11:11 CST 2014
 */

public class SelleraddrQueryForm extends BasePageForm{
	private String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
}

