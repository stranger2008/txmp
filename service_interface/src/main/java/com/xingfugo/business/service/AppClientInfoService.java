package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AppClientInfoDao;
import com.xingfugo.business.module.AppClientInfo;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

@Service
public class AppClientInfoService {
	
	@Autowired
	private AppClientInfoDao appClientInfoDao;
	
	public AppClientInfo selectByPrimaryKey(Integer id){
		return appClientInfoDao.selectByPrimaryKey(id);
	}
	
	public int insert(AppClientInfo appClientInfo){
		appClientInfoDao.insert(appClientInfo);
		return appClientInfo.getId();
	}
	
	public PageResult selectByPage(BasePageForm pageForm){
		List<AppClientInfo> list = appClientInfoDao.selectByPage(pageForm);
		return PageResultBuilder.build(pageForm.getPg(), list);
	}
	
}
