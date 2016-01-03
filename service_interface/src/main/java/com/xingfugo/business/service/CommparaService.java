package com.xingfugo.business.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.CommparaDao;
import com.xingfugo.business.module.Commpara;


@Service
public class CommparaService extends GenericService<Commpara,String>{

	private CommparaDao commparaDao;
	
	private static final String PLASE_SELECT = "请选择";
	
	public CommparaService() {}
	
	@Autowired
	public CommparaService(CommparaDao commparaDao) {
		super(commparaDao);
		this.commparaDao = commparaDao;
	}
	
	//根据para_code返回list
	public List getListByParacode(String para_code) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("para_code", para_code);
		return this.commparaDao.getList(map);
	}
	
	//根据para_code返回Select Map
	public Map getSelectMapByParacode(String para_code){
		List list = this.getListByParacode(para_code);
		Map map = new LinkedHashMap();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Map _tempmap = (Map)itr.next();
			String para_key = "",para_value = "";
			if(_tempmap.get("para_key") != null){
				para_key = _tempmap.get("para_key").toString(); 
			}
			if(_tempmap.get("para_value") != null){
				para_value = _tempmap.get("para_value").toString(); 
			}
			map.put(para_value, para_key);
		}
		return map;
	}
	
	//根据para_code返回Select Map
	public Map getSelectMapByParacode(boolean is_select,String para_code){
		List list = this.getListByParacode(para_code);
		Map map = new LinkedHashMap();
		Iterator itr = list.iterator();
		if(is_select){
			map.put("", PLASE_SELECT);
		}
		while(itr.hasNext()){
			Map _tempmap = (Map)itr.next();
			String para_key = "",para_value = "";
			if(_tempmap.get("para_key") != null){
				para_key = _tempmap.get("para_key").toString(); 
			}
			if(_tempmap.get("para_value") != null){
				para_value = _tempmap.get("para_value").toString(); 
			}
			map.put(para_value, para_key);
		}
		return map;
	}
	
	//根据para_code、para_value返回Map
	public Map getMapByParacode(String para_code,String para_value) {
		if(para_value == null || para_value.equals("")){
			return null;
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("para_code", para_code);
		map.put("para_value", para_value);
		List list = this.commparaDao.getList(map);
		if(list != null){
			return (Map)list.get(0);
		}else{
			return null;
		}
	}
	
	//根据para_code、para_value返回para_key
	public String getParakeyByParacode(String para_code,String para_value) {
		Map map = getMapByParacode(para_code,para_value);
		if(map != null && map.get("para_key") != null){
			return map.get("para_key").toString();
		}else{
			return "";
		}
	}

}
