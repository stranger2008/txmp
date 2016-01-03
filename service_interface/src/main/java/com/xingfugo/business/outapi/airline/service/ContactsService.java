package com.xingfugo.business.outapi.airline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.ContactsDao;
import com.xingfugo.business.outapi.airline.module.Contacts;

/**
 * @author wyl
 * 联系人管理
 */
@Service
public class ContactsService {
	
	@Autowired
	private ContactsDao contactsDao;
	/**
	 * @param userId
	 * @return
	 * 根据登录ID获取联系人列表
	 */
	public List<Contacts> contactsListById(Integer userId){
		if(userId!=null){
			return contactsDao.contactsListById(userId);
		}
		
		return null;
	}
	
	/**
	 * 添加联系人
	 */
	public Integer insertPassenger(Contacts contacts){
		try {
			return contactsDao.insertContacts(contacts);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @param id
	 * 删除联系人
	 */
	public Integer deleteContactsById(String id){
		try {
			contactsDao.deleteContactsById(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
