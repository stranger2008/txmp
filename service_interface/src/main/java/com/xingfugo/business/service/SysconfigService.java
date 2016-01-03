package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.SysconfigDao;
import com.xingfugo.business.module.Sysconfig;

/**
 * @function 功能 基本设置Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Mon Sep 01 11:39:25 CST 2014
 */

@Service
public class SysconfigService extends GenericService<Sysconfig,String>{

	private SysconfigDao sysconfigDao;
	
	public SysconfigService() {}
	
	@Autowired
	public SysconfigService(SysconfigDao sysconfigDao) {
		super(sysconfigDao);
		this.sysconfigDao = sysconfigDao;
	}
	
	
	public String getByVarName(String var_name){
		return sysconfigDao.getByVarName(var_name);
	}

}

