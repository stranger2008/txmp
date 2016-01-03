package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Payment;

/**
 * @function 功能 支付方式dao层业务接口
 * @author  创建人陈晓艺
 * @date  创建日期 Thu Sep 11 10:13:43 CST 2014
 */
public interface PaymentDao extends GenericDao<Payment,String>{
	public List isPaycodeExist(Map map);
}













