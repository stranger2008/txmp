package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Sms_email_templateDao;
import com.xingfugo.business.module.Sms_email_template;

/**
 * @function 功能 模板Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 09:04:58 CST 2014
 */

@Service
public class Sms_email_templateService extends GenericService<Sms_email_template,String>{

	private Sms_email_templateDao sms_email_templateDao;
	
	public Sms_email_templateService() {}
	
	@Autowired
	public Sms_email_templateService(Sms_email_templateDao sms_email_templateDao) {
		super(sms_email_templateDao);
		this.sms_email_templateDao = sms_email_templateDao;
	}
	
	public  Sms_email_template getByTemplate(String id)
	{
		return this.sms_email_templateDao.getByPk(id);
		 
	}
}

