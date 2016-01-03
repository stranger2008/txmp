package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.outapi.airline.module.Contacts;


/**
 * @author wyl
 *联系人管理
 */
public interface ContactsDao {
	/**
	 * @param userId
	 * @return
	 * 根据登录ID获取联系人列表
	 */
	public List<Contacts> contactsListById(Integer id);
	
	/**
	 * 添加联系人
	 */
	public Integer insertContacts(Contacts contacts);
	
	/**
	 * @param id
	 * 删除联系人
	 */
	public void deleteContactsById(String id);

}
