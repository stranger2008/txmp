package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Shopconfig;


public interface ShopconfigDao {
	
	public Shopconfig getShopconfig(Integer cust_id);
	
	public Shopconfig getShopconfigById(Integer shop_id);
	
	public void update(Shopconfig shopconfig);
	
	public void insert(Shopconfig shopconfig);
	
	/**
	 * 删除商家店铺
	 * @param cust_id 商家标识
	 * @author 陈显革
	 */
	public void deleteShopconfigByCustid(String cust_id);
	
	/**
	 * 根据商家id,查询商家店铺
	 * @param cust_id
	 * @return
	 * @author 陈显革
	 */
	public Shopconfig selectShopconfigWithAreaNameByCustid(Integer cust_id);
	
	/**
	 * 查询店铺详细信息
	 * @param map (hasAreaName, hasMember, hasLevel 当值为1时,会查询出所在地区,商家名称, 会员级别)
	 * @return
	 * @author 陈显革
	 */
	public List<Map<String, Object>> selectShopconfigDetail(Map<String, String> map);

	public List<Map<String, Object>> getitemsBycustid(String string);
	
}
