package com.xingfugo.business.module.mybatis;


public class BasePageForm {
	//用于接收分页参数，并将分页参数最终提交给MyBatis查询使用
	@APaging
	protected DefaultPagingBean pg = new DefaultPagingBean();
	
	public final DefaultPagingBean getPg() {
		return pg;
	}
}
