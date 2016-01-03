package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.EmailhistoryDao;
import com.xingfugo.business.module.Emailhistory;

/**
 * @function 功能 邮件发送记录Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 10:56:02 CST 2014
 */

@Service
public class EmailhistoryService extends GenericService<Emailhistory,String>{

	private EmailhistoryDao emailhistoryDao;
	
	public EmailhistoryService() {}
	
	@Autowired
	public EmailhistoryService(EmailhistoryDao emailhistoryDao) {
		super(emailhistoryDao);
		this.emailhistoryDao = emailhistoryDao;
	}

}

