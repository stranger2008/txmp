package com.xingfugo.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.MembercatDao;
import com.xingfugo.business.module.Membercat;

/**
 * @function 功能 商家自定义分类Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Sat Sep 27 16:03:05 CST 2014
 */

@Service
public class MembercatService extends GenericService<Membercat,String>{

	private MembercatDao membercatDao;
	
	public MembercatService() {}
	
	@Autowired
	public MembercatService(MembercatDao membercatDao) {
		super(membercatDao);
		this.membercatDao = membercatDao;
	}
	
	/**
	 * 根据自定义分类ID串找出对应的分类信息
	 * @param cat_ids
	 * @return
	 */
	public Map<String,String> getCatMapByIds(String cat_ids){
		List<Map<String,String>> catInfoList= membercatDao.getCatMapByIds(cat_ids);
		if(catInfoList==null||catInfoList.isEmpty()){
			return null;
		}
		Map<String,String> result = new HashMap<String,String>();
		for(Map<String,String> catInfo : catInfoList){
			result.put(catInfo.get("cat_id"),catInfo.get("cat_name"));
		}
		return result;
	}
}

