package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Memberuser;

/**
 * @function 功能 会员列表dao层业务接口
 * @author  创建人史倩倩
 * @date  创建日期 Fri Sep 05 10:54:27 CST 2014
 */
public interface MemberuserDao extends GenericDao<Memberuser,String>{

	public int updatepwd(Memberuser memberuser);

	public Memberuser getByPkpwd(String id);

	public int hasExist(Memberuser memberuser);

	public List hasPhonenumber(String cellnumber);
	
}













