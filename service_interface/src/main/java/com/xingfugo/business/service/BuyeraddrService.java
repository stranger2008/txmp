package com.xingfugo.business.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.BuyeraddrDao;
import com.xingfugo.business.module.Buyeraddr;

@Service
public class BuyeraddrService{

	@Autowired
	private BuyeraddrDao buyeraddrDao;
	@Autowired
	private AreaService areaService;
	
	public void deleteBuyeraddr(String id) {
		buyeraddrDao.deleteBuyeraddr(id);
	}
	
	public Buyeraddr getByPk(String id) {
		return buyeraddrDao.getByPk(id);
	}

	public List getBuyeraddrByUserId(String user_id) {
		List list = buyeraddrDao.getBuyeraddrByUserId(user_id);
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Map amap = (Map)itr.next();
			String area_attr = "";
			if(amap.get("area_attr")!=null){
				area_attr = amap.get("area_attr").toString();
			}
			String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(area_attr);
			amap.put("area_name_str", area_name_str);
		}
		return list;
	}
	
	public Buyeraddr getDefaultBuyeraddr(String user_id){
		return buyeraddrDao.getDefaultBuyeraddr(user_id);
	}
	
	public Map<String, Object> getDefaultFullBuyeraddr(String user_id){
		Buyeraddr addr = buyeraddrDao.getDefaultBuyeraddr(user_id);
		return addAreaName(addr);
	}
	
	public Map<String, Object> getFullBuyeraddrByPk(String id) {
		Buyeraddr addr =  buyeraddrDao.getByPk(id);
		return addAreaName(addr);
	}
	
	public List getFullBuyeraddrByUserId(String user_id) {
		List list = buyeraddrDao.getBuyeraddrByUserId(user_id);
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Map map = (Map)itr.next();
			
			String area_attr = (String)map.get("area_attr");
			if(StringUtils.isEmpty(area_attr)){
				map.put("area_name", null);
				continue;
			}
			
			String area_name = areaService.getAreaAttrNameByAreaIdAttr(area_attr, "1");
			map.put("area_name", area_name);
		}
		
		return list;
	}
	
	private Map<String, Object> addAreaName(Buyeraddr addr){
		if(addr == null){
			return Collections.emptyMap();
		}
		
		Map<String, Object> map = null;
		try {
			map = PropertyUtils.describe(addr);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		if(map.containsKey("class")){
			map.remove("class");
		}
		
		String area_attr = addr.getArea_attr();
		String area_name = areaService.getAreaAttrNameByAreaIdAttr(area_attr, "1");
		map.put("area_name", area_name);
		
		return map;
	}
	
	@Transactional
	public Integer insertBuyeraddr(Buyeraddr buyeraddr) {
		updateDefaultAddrInfo(buyeraddr);
		buyeraddrDao.insertBuyeraddr(buyeraddr);
		return buyeraddr.getAddr_id();
	}

	@Transactional
	public void updateBuyeraddr(Buyeraddr buyeraddr) {
		updateDefaultAddrInfo(buyeraddr);
		buyeraddrDao.updateBuyeraddr(buyeraddr);
	}
	
	//修改默认地址
	public void updateDefaultAddrInfo(Buyeraddr buyeraddr){
		if("1".equals(buyeraddr.getIs_default())){
			buyeraddrDao.updateIsDefaultAddr(buyeraddr);
		}
	}
}
