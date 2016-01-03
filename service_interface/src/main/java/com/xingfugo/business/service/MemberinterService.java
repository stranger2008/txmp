package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.MemberinterDao;
import com.xingfugo.business.module.Memberinter;

/**
 * @function 功能 会员积分Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 15:59:56 CST 2014
 */

@Service
public class MemberinterService extends GenericService<Memberinter,String>{

	private MemberinterDao memberinterDao;
	
	public MemberinterService() {}
	
	@Autowired
	public MemberinterService(MemberinterDao memberinterDao) {
		super(memberinterDao);
		this.memberinterDao = memberinterDao;
	}

}

