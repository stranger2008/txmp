package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Orderhistory;


public interface OrderhistoryDao {
	
	public void batchInsertOrderhistory(List list);
	
	public List getOrderhistoryList(String order_id);
	
	public void insertOrderhistory(Orderhistory orderhistory);
	
	//定时任务修改订单状态，譬如插入订单异动历史记录表
	
	public void insertUpdateUnpayOrder(Map map);
	
	public void insertUpdateUnReceiptOrder(Map map);
}
