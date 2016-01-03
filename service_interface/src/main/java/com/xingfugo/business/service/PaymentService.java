package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.PaymentDao;
import com.xingfugo.business.module.Payment;

/**
 * @function 功能 支付方式Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 11 10:13:43 CST 2014
 */

@Service
public class PaymentService extends GenericService<Payment,String>{

	private PaymentDao paymentDao;
	
	public PaymentService() {}
	
	@Autowired
	public PaymentService(PaymentDao paymentDao) {
		super(paymentDao);
		this.paymentDao = paymentDao;
	}
	
	public List isPaycodeExist(Map map) {
		return paymentDao.isPaycodeExist(map);
	}

}

