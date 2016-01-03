package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.MemberuserDao;
import com.xingfugo.business.module.Category;
import com.xingfugo.business.module.Memberuser;

/**
 * @function 功能 会员列表Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 05 10:54:27 CST 2014
 */

@Service
public class MemberuserService extends GenericService<Memberuser,String>{

	private MemberuserDao memberuserDao;
	
	public MemberuserService() {}
	
	@Autowired
	public MemberuserService(MemberuserDao memberuserDao) {
		super(memberuserDao);
		this.memberuserDao = memberuserDao;
	}
	
	public int updatepwd(Memberuser memberuser){
		return memberuserDao.updatepwd(memberuser);
		
	}

	public Memberuser getByPkpwd(String id) {
		return  memberuserDao.getByPkpwd(id);
	}
	
	/**
	 * 查询分类对象是否已经存在
	 * @param category
	 * @return
	 */
	public boolean hasExist(Memberuser memberuser){
		int num = memberuserDao.hasExist(memberuser);
		return num>0;
	}

	public List hasPhonenumber(String cellnumber) {
		return memberuserDao.hasPhonenumber(cellnumber);
	}
}

