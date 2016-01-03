package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Selleraddr;

/**
 * @function 功能 卖家发货地址dao层业务接口
 * @author  创建人史倩倩
 * @date  创建日期 Wed Sep 24 19:11:11 CST 2014
 */
public interface SelleraddrDao extends GenericDao<Selleraddr,String>{

	public void updateIsDefaultAddr(Selleraddr selleraddr);

	public void updateSelleraddr(Selleraddr selleraddr);

	public void updateIsDefaultAddr_0(Selleraddr selleraddr);
	
	/**
	 * 获取商家默认收获地址
	 * @author liuxl
	 * @param cust_id
	 * @return
	 */
	public Selleraddr getDefaultSelleraddr(String cust_id);
	
}













