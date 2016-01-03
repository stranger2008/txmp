package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Nav;

/**
 * @function 功能 导航dao层业务接口
 * @author  创建人李良林
 * @date  创建日期 Thu Aug 28 13:10:44 CST 2014
 */
public interface NavDao extends GenericDao<Nav,String>{

	List<Map<String, Object>> getListForStatic(Map<String, String> attrMap);

	List getallList();
	
}













