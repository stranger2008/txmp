package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Sellereval;

/**
 * @function 功能 商家评价dao层业务接口
 * @author  创建人史倩倩
 * @date  创建日期 Sat Oct 11 11:10:59 CST 2014
 */
public interface SellerevalDao extends GenericDao<Sellereval,String>{

	List<Map<String, Object>> getStareval(String cust_id);
	
}













