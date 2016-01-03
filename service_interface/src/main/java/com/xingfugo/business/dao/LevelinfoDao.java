package com.xingfugo.business.dao;

import com.xingfugo.business.module.Levelinfo;

/**
 * @function 功能 店铺级别dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Wed Sep 24 11:26:37 CST 2014
 */
public interface LevelinfoDao extends GenericDao<Levelinfo,String>{
	
	/**
	 * 根据商家id,删除商家等级记录
	 * @param cust_id
	 */
	public void deleteByCustid(String cust_id);
	
}













