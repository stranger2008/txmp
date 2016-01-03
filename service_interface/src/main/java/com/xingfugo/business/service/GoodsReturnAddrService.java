package com.xingfugo.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AreaDao;
import com.xingfugo.business.dao.GoodsReturnAddrDao;
import com.xingfugo.business.module.GoodsReturnAddr;

/**
 * @function 功能 退换货收货地址Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 24 14:57:55 CST 2014
 */

@Service
public class GoodsReturnAddrService extends GenericService<GoodsReturnAddr,String>{

	private GoodsReturnAddrDao goodsReturnAddrDao;
	@Autowired
	private AreaDao areaDao;
	
	public GoodsReturnAddrService() {}
	
	@Autowired
	public GoodsReturnAddrService(GoodsReturnAddrDao goodsReturnAddrDao) {
		super(goodsReturnAddrDao);
		this.goodsReturnAddrDao = goodsReturnAddrDao;
	}
	/**
	 * 根据关联标识查询退换货收货地址信息
	 * @param link_id 关联标识
	 * @param user_type 会员类型
	 * @return
	 */
	public GoodsReturnAddr getByLinkId(String link_id , String user_type){
		Map param = new HashMap();
		param.put("link_id", link_id);
		param.put("user_type", user_type);
		List<GoodsReturnAddr> returnAddrList = goodsReturnAddrDao.getByLinkId(param);
		if(returnAddrList==null||returnAddrList.size()==0){
			return new GoodsReturnAddr();
		}
		GoodsReturnAddr returnAddr = returnAddrList.get(0);
		String area_attr = returnAddr.getArea_attr();
		if(area_attr.startsWith(",")){
			area_attr = area_attr.substring(1,area_attr.length());
		}
		if(area_attr.endsWith(",")){
			area_attr = area_attr.substring(0, area_attr.lastIndexOf(","));
		}
		List area_nmList = areaDao.getAreaAttrNameByAreaIdAttr(area_attr);
		String area_attr_name = "";
		if(area_nmList!=null&& !area_nmList.isEmpty()){
			for(Object obj : area_nmList){
				Map areaMap = (Map)obj;
				if(areaMap.get("area_name") != null){
					area_attr_name += areaMap.get("area_name");
				}
			}
		}
		returnAddr.setArea_attr_name(area_attr_name);
		return returnAddr;
	}
}

