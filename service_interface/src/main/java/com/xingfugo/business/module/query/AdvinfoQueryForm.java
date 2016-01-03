package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 广告信息管理表单查询实体
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 10 16:29:59 CST 2014
 */

public class AdvinfoQueryForm extends BasePageForm{
	
	private String pos_id;
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	
	private String adv_name;
	public String getAdv_name() {
		return adv_name;
	}
	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	
}

