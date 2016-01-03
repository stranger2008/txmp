package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Member;

/**
 * @function 功能 商家信息dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Sat Sep 20 14:01:11 CST 2014
 */
public interface MemberDao extends GenericDao<Member,String>{
	//修改密码
	public void updatePasswd(Map map);
	
	/**
	 * 查询商家信息带所属地区名称
	 * @param cust_id
	 * @return
	 * @author 陈显革 2014-09-20
	 */
	public Member selectMemberByIdWithAreaName(String cust_id);
	
	/**
	 * 审核商家信息
	 * @param member
	 * @author 陈显革 2014-09-22
	 */
	public void auditMember(Member member);
	
	/**
	 * 新增并返回id
	 * @param member
	 */
	public void insertReturnPk(Member member);
	
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 */
	public int isUsernameExist(String username);
	
	/**
	 * 判断入驻联系人和联系电话是否同时存在
	 * @param member
	 * @return
	 */
	public int isContactNameAndContactPhoneUsed(Member member);
	
	/**
	 * 查询入驻进度
	 * @param member
	 * @return
	 */
	public List<Member> selectJoinUsProgress(Member member);
}













