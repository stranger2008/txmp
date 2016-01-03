package com.xingfugo.business.outapi.lottery.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.LotteryAccountDao;
import com.xingfugo.business.outapi.lottery.module.LotteryAccount;

@Service
public class LotteryAccountService {
	@Autowired
	private  LotteryAccountDao lotteryAccountDao;
	
	//添加彩票购买用户信息
	public void insertAccount(LotteryAccount lotteryAccount){
		
		if(lotteryAccount!=null){
			lotteryAccountDao.insertAccount(lotteryAccount);
		}
		
		
	}
	//查找彩票购买用户
	public LotteryAccount getLotteryAccountByUserId(String userId){
		
		LotteryAccount lotteryAccount =null;
		if(StringUtils.isNotBlank(userId)){
			lotteryAccount =lotteryAccountDao.getLotteryAccountByUserId(userId);
		}
		return lotteryAccount;
	}
	
	//修改彩票购买用户信息
	public void updateLotteryAccount(LotteryAccount lotteryAccount){
			if(lotteryAccount!=null){
				lotteryAccountDao.updateLotteryAccount(lotteryAccount);
				
			}
	}
}
