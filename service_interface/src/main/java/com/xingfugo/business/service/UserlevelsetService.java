package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.UserlevelsetDao;
import com.xingfugo.business.module.Userlevelset;

/**
 * @function 功能 会员等级Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 05 15:54:11 CST 2014
 */

@Service
public class UserlevelsetService extends GenericService<Userlevelset,String>{

	private UserlevelsetDao userlevelsetDao;
	
	public UserlevelsetService() {}
	
	@Autowired
	public UserlevelsetService(UserlevelsetDao userlevelsetDao) {
		super(userlevelsetDao);
		this.userlevelsetDao = userlevelsetDao;
	}
	
	public List<Userlevelset> getListall(){
		
		return  userlevelsetDao.getListall();
		
	}

	public Userlevelset getedititem(String id) {
		return userlevelsetDao.getedititem(id);
	}

}

