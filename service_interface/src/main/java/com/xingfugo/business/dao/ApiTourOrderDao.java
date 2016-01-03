package com.xingfugo.business.dao;

import com.xingfugo.business.module.ApiTourOrder;

/**
 * @function 功能 旅游接口dao层业务接口
 * @author  创建人郭峰
 * @date  创建日期 Thu Sep 04 10:09:56 CST 2014
 */
public interface ApiTourOrderDao extends GenericDao<ApiTourOrder,String>{
	
	public void insert(ApiTourOrder apiTourOrder);
}













