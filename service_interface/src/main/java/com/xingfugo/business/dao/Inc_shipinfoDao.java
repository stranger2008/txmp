package com.xingfugo.business.dao;

import java.util.Map;

import com.xingfugo.business.module.Inc_shipinfo;

/**
 * @function 功能 物流运送信息dao层业务接口
 * @author  创建人刘香玲
 * @date  创建日期 Fri Sep 26 19:19:33 CST 2014
 */
public interface Inc_shipinfoDao extends GenericDao<Inc_shipinfo,String>{
	/**
	 * 根据关联标识和会员类型查找物流信息
	 * @param param
	 * @return
	 */
	Inc_shipinfo getByLinkId(Map param);
}













