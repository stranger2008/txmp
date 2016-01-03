package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Userlevelset;

/**
 * @function 功能 会员等级dao层业务接口
 * @author  创建人史倩倩
 * @date  创建日期 Fri Sep 05 15:54:11 CST 2014
 */
public interface UserlevelsetDao extends GenericDao<Userlevelset,String>{
	public List getListall();

	public Userlevelset getedititem(String id);
	
}













