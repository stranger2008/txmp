package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.LevelinfoDao;
import com.xingfugo.business.module.Levelinfo;

/**
 * @function 功能 店铺级别Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 24 11:26:37 CST 2014
 */

@Service
public class LevelinfoService extends GenericService<Levelinfo,String>{

	private LevelinfoDao levelinfoDao;
	
	public LevelinfoService() {}
	
	@Autowired
	public LevelinfoService(LevelinfoDao levelinfoDao) {
		super(levelinfoDao);
		this.levelinfoDao = levelinfoDao;
	}
	
	/**
	 * 根据商家id,删除商家等级记录
	 * @param cust_id
	 */
	public void deleteByCustid(String cust_id){
		this.levelinfoDao.deleteByCustid(cust_id);
	}

}

