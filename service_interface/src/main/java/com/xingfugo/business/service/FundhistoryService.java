package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.FundhistoryDao;
import com.xingfugo.business.module.Fundhistory;

/**
 * @function 功能 会员资金异动表Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:27:07 CST 2014
 */

@Service
public class FundhistoryService extends GenericService<Fundhistory,String>{

	private FundhistoryDao fundhistoryDao;
	
	public FundhistoryService() {}
	
	@Autowired
	public FundhistoryService(FundhistoryDao fundhistoryDao) {
		super(fundhistoryDao);
		this.fundhistoryDao = fundhistoryDao;
	}

}

