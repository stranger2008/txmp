package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 手机端版本表单查询实体
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 12 15:27:55 CST 2014
 */

public class App_android_versionQueryForm extends BasePageForm{
	
	private String ver_code;
	public String getVer_code() {
		return ver_code;
	}
	public void setVer_code(String ver_code) {
		this.ver_code = ver_code;
	}
	
	private String ver_name;
	public String getVer_name() {
		return ver_name;
	}
	public void setVer_name(String ver_name) {
		this.ver_name = ver_name;
	}
	
	private String publish_time;
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
}

