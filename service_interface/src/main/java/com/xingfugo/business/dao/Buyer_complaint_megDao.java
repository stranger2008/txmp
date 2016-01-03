package com.xingfugo.business.dao;

import com.xingfugo.business.module.Buyer_complaint_meg;

/**
 * @function 功能 会员投诉留言信息dao层业务接口
 * @author  创建人陈晓艺
 * @date  创建日期 Tue Oct 14 18:04:48 CST 2014
 */
public interface Buyer_complaint_megDao extends GenericDao<Buyer_complaint_meg,String>{
	public void deleteComplaintMeg(String com_id);
}













