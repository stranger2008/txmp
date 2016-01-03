package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Buyer_complaint;

/**
 * @function 功能 投诉记录信息dao层业务接口
 * @author  创建人陈晓艺
 * @date  创建日期 Tue Oct 14 17:58:10 CST 2014
 */
public interface Buyer_complaintDao extends GenericDao<Buyer_complaint,String>{
	public List getComplaintByOrderId(String order_id);
}













