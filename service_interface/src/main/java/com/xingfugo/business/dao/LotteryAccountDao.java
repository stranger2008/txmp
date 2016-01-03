package com.xingfugo.business.dao;

import com.xingfugo.business.outapi.lottery.module.LotteryAccount;

public interface LotteryAccountDao {
	
	//添加彩票购买用户信息
	public void insertAccount(LotteryAccount lotteryAccount);
	
	//查找彩票购买用户
	public LotteryAccount getLotteryAccountByUserId(String userId);
	
	//修改彩票购买用户信息
	public void updateLotteryAccount(LotteryAccount lotteryAccount);
	
	
}
