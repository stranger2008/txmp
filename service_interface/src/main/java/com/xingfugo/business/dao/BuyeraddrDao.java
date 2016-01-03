package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Buyeraddr;

public interface BuyeraddrDao {
	
	public Integer insertBuyeraddr(Buyeraddr buyeraddr);
	
	public void updateBuyeraddr(Buyeraddr buyeraddr);
	
	public void deleteBuyeraddr(String id);
	
	public List getBuyeraddrByUserId(String user_id);
	
	public Buyeraddr getByPk(String id);
	
	//除此ID地址外，其他改为非默认地址
	public void updateIsDefaultAddr(Buyeraddr buyeraddr);
	
	public Buyeraddr getDefaultBuyeraddr(String user_id);
}
