package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Advinfo;

/**
 * @function 功能 广告信息管理dao层业务接口
 * @author  创建人刘香玲
 * @date  创建日期 Wed Sep 10 16:29:59 CST 2014
 */
public interface AdvinfoDao extends GenericDao<Advinfo,String>{
	
	int deleteByPosId(String pos_ids);
	
	List<Map<String,Object>> getListForStatic(Map<String, String> map);
}













