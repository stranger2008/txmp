package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.outapi.lottery.module.LotteryAccount;
import com.xingfugo.business.outapi.lottery.module.LotteryOrder;
//订单管理
public interface LotteryOrderDao {
	//添加订单信息
	public void insertAccount(LotteryOrder lotteryOrder);
	
	//根据登录用户查找订单列表
	public List<LotteryAccount> getLotteryOrderByUserId(String cust_id);
	
	//删除订单
	public void deleteLotteryAccount(String orderNo);
	
	//查询订单列表
	public List<Map<String, Object>> getList(Map<String, String> map);
}
