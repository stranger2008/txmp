package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.GoodsbrandDao;
import com.xingfugo.business.module.Goodsbrand;
import com.xingfugo.util.ImgPathUitls;

/**
 * @function 功能 商品品牌Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 11 19:32:19 CST 2014
 */

@Service
public class GoodsbrandService extends GenericService<Goodsbrand,String>{

	private GoodsbrandDao goodsbrandDao;
	
	public GoodsbrandService() {}
	
	@Autowired
	public GoodsbrandService(GoodsbrandDao goodsbrandDao) {
		super(goodsbrandDao);
		this.goodsbrandDao = goodsbrandDao;
	}
	
	public List isBrandNameExist(Map map) {
		return goodsbrandDao.isBrandNameExist(map);
	}
	
	public List selectByGoodsAttr(Map map) {
		return goodsbrandDao.selectByGoodsAttr(map);
	}

	public List<Map<String, Object>> getListForStatic(
			Map<String, String> attrMap, String mainHostName) {
		List<Map<String,Object>> list =  goodsbrandDao.getListForStatic(attrMap);
		for (Map<String, Object> map : list) {
			if(map.get("logo")!=null){
				map.put("logo",ImgPathUitls.filterImagePath(map.get("logo").toString()));
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> getAllListForStatic(
			Map<String, String> attrMap) {
		List<Map<String,Object>> list =  goodsbrandDao.getList(attrMap);
		for (Map<String, Object> map : list) {
			if(map.get("logo")!=null){
				map.put("logo",ImgPathUitls.filterImagePath(map.get("logo").toString()));
			}
		}
		return list;
	}
}

