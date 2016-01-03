package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Aboutus;

/**
 * @function 功能 文章管理dao层业务接口
 * @author  创建人刘香玲
 * @date  创建日期 Thu Sep 04 17:05:56 CST 2014
 */
public interface AboutusDao extends GenericDao<Aboutus,String>{
	
	//根据分类ID list找出所有对应的文章
	public List getAboutusInfoByCatids(List catidsList);

	public List<Map<String, Object>> getListForStatic(
			Map<String, String> attrMap);
	
	public List<Map<String,Object>> getAllListForStatic();
	
}













