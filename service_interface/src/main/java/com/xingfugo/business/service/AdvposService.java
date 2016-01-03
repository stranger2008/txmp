package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AdvposDao;
import com.xingfugo.business.module.Advpos;

/**
 * @function 功能 广告管理Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 10 16:25:07 CST 2014
 */

@Service
public class AdvposService extends GenericService<Advpos,String>{

	private AdvposDao advposDao;
	
	public AdvposService() {}
	
	@Autowired
	public AdvposService(AdvposDao advposDao) {
		super(advposDao);
		this.advposDao = advposDao;
	}

}

