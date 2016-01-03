package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AboutusDao;
import com.xingfugo.business.module.Aboutus;

/**
 * @function 功能 文章管理Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Thu Sep 04 17:05:56 CST 2014
 */

@Service
public class AboutusService extends GenericService<Aboutus,String>{

	private AboutusDao aboutusDao;
	
	public AboutusService() {}
	
	@Autowired
	public AboutusService(AboutusDao aboutusDao) {
		super(aboutusDao);
		this.aboutusDao = aboutusDao;
	}
	
	public List getAboutusInfoByCatids(List catidsList) {
		return this.aboutusDao.getAboutusInfoByCatids(catidsList);
	}

	public List<Map<String, Object>> getListForStatic(
			Map<String, String> attrMap, String mainHostName) {
		return this.aboutusDao.getListForStatic(attrMap);
	}

	public List<Map<String, Object>> getAllListForStatic() {
		return this.aboutusDao.getAllListForStatic();
	}

}

