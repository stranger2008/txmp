package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.ApiTourOrderDao;
import com.xingfugo.business.module.ApiTourOrder;

/**
 * @function 功能 旅游接口Service层业务实现
 * @author 创建人 郭峰
 * @date 创建日期 Thu Sep 04 10:09:56 CST 2014
 */

@Service
public class ApiTourOrderService extends GenericService<ApiTourOrder,String>{

	@Autowired
	private ApiTourOrderDao apiTourOrderDao;
	
	public ApiTourOrderService() {}

	@Override
	public void insert(ApiTourOrder apiTourOrder) {
		this.apiTourOrderDao.insert(apiTourOrder);
	}
	
	
	
	

}

