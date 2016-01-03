package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.InterhistoryDao;
import com.xingfugo.business.module.Interhistory;

/**
 * @function 功能 会员积分异动列表Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 17:03:43 CST 2014
 */

@Service
public class InterhistoryService extends GenericService<Interhistory,String>{

	private InterhistoryDao interhistoryDao;
	
	public InterhistoryService() {}
	
	@Autowired
	public InterhistoryService(InterhistoryDao interhistoryDao) {
		super(interhistoryDao);
		this.interhistoryDao = interhistoryDao;
	}

}

