package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

public class CollectQueryForm extends BasePageForm{
	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
