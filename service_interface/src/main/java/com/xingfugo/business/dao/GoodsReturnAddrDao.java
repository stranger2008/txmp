package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.GoodsReturnAddr;

/**
 * @function 功能 退换货收货地址dao层业务接口
 * @author  创建人刘香玲
 * @date  创建日期 Wed Sep 24 14:57:55 CST 2014
 */
public interface GoodsReturnAddrDao extends GenericDao<GoodsReturnAddr,String>{
	List<GoodsReturnAddr> getByLinkId(Map param);
}













