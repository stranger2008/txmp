package com.xingfugo.business.module.query;

import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 课程视频管理表单查询实体
 * @author 创建人 李良林
 * @date 创建日期 Sat Apr 04 16:10:38 CST 2015
 */

public class Course_videoQueryForm extends BasePageForm{
	
	private String c_id;

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	
	
	
}

