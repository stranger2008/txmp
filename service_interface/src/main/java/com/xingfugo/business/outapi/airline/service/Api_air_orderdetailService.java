package com.xingfugo.business.outapi.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Api_air_orderdetailDao;
import com.xingfugo.business.module.Api_air_orderdetail;
import com.xingfugo.business.service.GenericService;

/**
 * @function 功能 机票订单详情Service层业务实现
 * @author 创建人 王益龙
 * @date 创建日期 Thu Aug 28 11:36:30 CST 2014
 */

@Service
public class Api_air_orderdetailService extends GenericService<Api_air_orderdetail,String>{

	private Api_air_orderdetailDao api_air_orderdetailDao;
	
	@Autowired
	public Api_air_orderdetailService(Api_air_orderdetailDao api_air_orderdetailDao) {
		super(api_air_orderdetailDao);
		this.api_air_orderdetailDao = api_air_orderdetailDao;
	}

}

