package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Buyer_complaint_megDao;
import com.xingfugo.business.module.Buyer_complaint_meg;

/**
 * @function 功能 会员投诉留言信息Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Tue Oct 14 18:04:48 CST 2014
 */

@Service
public class Buyer_complaint_megService extends GenericService<Buyer_complaint_meg,String>{

	private Buyer_complaint_megDao buyer_complaint_megDao;
	
	public Buyer_complaint_megService() {}
	
	@Autowired
	public Buyer_complaint_megService(Buyer_complaint_megDao buyer_complaint_megDao) {
		super(buyer_complaint_megDao);
		this.buyer_complaint_megDao = buyer_complaint_megDao;
	}
	
	public void deleteComplaintMeg(String com_id){
		this.buyer_complaint_megDao.deleteComplaintMeg(com_id);
	}

}

