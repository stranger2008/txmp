package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.NavDao;
import com.xingfugo.business.module.Nav;

/**
 * @function 功能 导航Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Thu Aug 28 13:10:44 CST 2014
 */

@Service
public class NavService extends GenericService<Nav,String>{

	private NavDao navDao;
	
	public NavService() {}
	
	@Autowired
	public NavService(NavDao navDao) {
		super(navDao);
		this.navDao = navDao;
	}

	public List<Map<String, Object>> getListForStatic(
			Map<String, String> attrMap,String mainHostName) {
		List<Map<String, Object>> navList = navDao.getListForStatic(attrMap);
		for(Map<String,Object> map2:navList){
			if(map2.get("link_url")!=null){
				if(!"/".equals(map2.get("link_url"))){
					if(map2.get("link_url").toString().startsWith("/")){
						map2.put("link_url", mainHostName+map2.get("link_url").toString().substring(1));
					}else if(!map2.get("link_url").toString().startsWith("http://")){
						map2.put("link_url", mainHostName+map2.get("link_url"));
					}
				}
			}
		}
		return navList;
	}

	public List getallList() {
		// TODO Auto-generated method stub
		return navDao.getallList();
	}

}

