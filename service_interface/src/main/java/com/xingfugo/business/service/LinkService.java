package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.LinkDao;
import com.xingfugo.business.module.Link;

/**
 * @function 功能 友情链接Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Mon Aug 25 17:49:10 CST 2014
 */

@Service
public class LinkService extends GenericService<Link,String>{

	private LinkDao linkDao;
	
	public LinkService() {}
	
	@Autowired
	public LinkService(LinkDao linkDao) {
		super(linkDao);
		this.linkDao = linkDao;
	}

}

