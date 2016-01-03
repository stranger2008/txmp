package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.HotWordDao;

@Service
public class HotWordService {
	
	@Autowired
	private HotWordDao hotWordDao;
	
	public List<String> list(){
		return hotWordDao.list();
	}
}
