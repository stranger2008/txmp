package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Goodseval;

/**
 * @function 功能 商品评价dao层业务接口
 * @author  创建人史倩倩
 * @date  创建日期 Sat Oct 11 11:15:27 CST 2014
 */
public interface GoodsevalDao extends GenericDao<Goodseval,String>{

	List getdetailByGoodsidAndOrderid(Map param);

	void deletedetailByGoodsidAndOrderid(Map param);

	List<Map<String, Object>> getGoodsevallist(String id);

	List<Map<String, Object>> getGoodsevallevel(String id);

	List<Map<String, Object>> getsummember(String id);

	List<Map<String, Object>> getgoodsnum(String id);

	List<Map<String, Object>> getSellerinfo(String cust_id);

	List<Map<String, Object>> showSelecteval(Map param);

	String getGoodnumByGoodsid(String id);

	void updateBytradeid(Map param);
	
}













