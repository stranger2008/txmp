package com.xingfugo.business.dao;

import java.util.Map;
import java.util.List;

import com.xingfugo.business.module.Role_right;

/**
 * @function 功能 权限dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Thu Sep 04 16:42:37 CST 2014
 */
public interface Role_rightDao extends GenericDao<Role_right,String>{
	
	public List getUserRole_rights(Map<String, String> map);
	
}













