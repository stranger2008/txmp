package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Goodsbrand;

/**
 * @function 功能 商品品牌dao层业务接口
 * @author  创建人陈晓艺
 * @date  创建日期 Thu Sep 11 19:32:19 CST 2014
 */
public interface GoodsbrandDao extends GenericDao<Goodsbrand,String>{
	public List isBrandNameExist(Map map);
	public List selectByGoodsAttr(Map map);
	public List<Map<String, Object>> getListForStatic(
			Map<String, String> attrMap);
}













