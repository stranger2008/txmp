package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Membercat;

/**
 * @function 功能 商家自定义分类dao层业务接口
 * @author  创建人陈晓艺
 * @date  创建日期 Sat Sep 27 16:03:05 CST 2014
 */
public interface MembercatDao extends GenericDao<Membercat,String>{
	//根据自定义分类ID串找出对应的分类信息
	public List<Map<String,String>> getCatMapByIds(String cat_ids);
}













