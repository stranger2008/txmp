package com.xingfugo.business.dao;

import java.util.Map;
import java.util.List;

import com.xingfugo.business.module.Sysuser;

/**
 * @function 功能 用户dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Fri Sep 05 13:53:56 CST 2014
 */
public interface SysuserDao extends GenericDao<Sysuser,String>{
	public List isUsernameExist(Map map);
	
	public void updateUserPasswd(Sysuser sysuser);
}













