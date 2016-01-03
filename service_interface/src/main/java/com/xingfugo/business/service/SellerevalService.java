package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.SellerevalDao;
import com.xingfugo.business.module.Sellereval;

/**
 * @function 功能 商家评价Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Sat Oct 11 11:10:59 CST 2014
 */

@Service
public class SellerevalService extends GenericService<Sellereval,String>{

	private SellerevalDao sellerevalDao;
	
	public SellerevalService() {}
	
	@Autowired
	public SellerevalService(SellerevalDao sellerevalDao) {
		super(sellerevalDao);
		this.sellerevalDao = sellerevalDao;
	}

	public List<Map<String, Object>> getStareval(String cust_id) {
		return sellerevalDao.getStareval(cust_id);
	}

}

