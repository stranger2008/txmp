package com.xingfugo.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AdvinfoDao;
import com.xingfugo.business.module.Advinfo;
import com.xingfugo.util.ImgPathUitls;

/**
 * @function 功能 广告信息管理Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 10 16:29:59 CST 2014
 */

@Service
public class AdvinfoService extends GenericService<Advinfo,String>{

	private AdvinfoDao advinfoDao;
	
	public AdvinfoService() {}
	
	@Autowired
	public AdvinfoService(AdvinfoDao advinfoDao) {
		super(advinfoDao);
		this.advinfoDao = advinfoDao;
	}

	public int delAdvinfosByPosId(String pos_ids){
		return advinfoDao.deleteByPosId(pos_ids);
	}
	
	public List<Map<String,Object>> getListForStatic(Map<String, String> map, String mainHostName){
		List<Map<String,Object>> advList = advinfoDao.getListForStatic(map);
		for (Map<String, Object> map2 : advList) {
			if(map2.get("img_path")!=null){
				map2.put("img_path",ImgPathUitls.filterImagePath(map2.get("img_path").toString()));
			}
			if(map2.get("link_url")!=null){
				if(map2.get("link_url").toString().startsWith("/")){
					map2.put("link_url", mainHostName+map2.get("link_url").toString().substring(1));
				}else if(!map2.get("link_url").toString().startsWith("http://")){
					map2.put("link_url", mainHostName+map2.get("link_url"));
				}
			}
		}
		return advList;
	}
}

