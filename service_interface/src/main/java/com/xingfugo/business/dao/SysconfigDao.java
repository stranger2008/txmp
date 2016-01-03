package com.xingfugo.business.dao;

import org.apache.ibatis.annotations.Param;

import com.xingfugo.business.module.Sysconfig;

/**
 * @function 功能 基本设置dao层业务接口
 * @author  创建人刘香玲
 * @date  创建日期 Mon Sep 01 11:39:25 CST 2014
 */
public interface SysconfigDao extends GenericDao<Sysconfig,String>{
	
	public String getByVarName(@Param(value = "var_name") String var_name);
	
}













