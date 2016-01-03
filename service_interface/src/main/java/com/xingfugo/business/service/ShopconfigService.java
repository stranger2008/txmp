package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.ShopconfigDao;
import com.xingfugo.business.module.Shopconfig;
import com.xingfugo.util.ImgPathUitls;


@Service
public class ShopconfigService{

	@Autowired
	private ShopconfigDao shopconfigDao;
	
	@Autowired
	private AreaService areaService;
	
	public Shopconfig getShopconfig(Integer cust_id) {
		Shopconfig shop = shopconfigDao.getShopconfig(cust_id);
		if(shop != null){
			String area_attr = shop.getArea_attr();
			if(StringUtils.isNotEmpty(area_attr)){
				String area_name = areaService.getAreaAttrNameByAreaIdAttr(area_attr, "1");
				shop.setArea_name(area_name);
			}
			
			shop.setShop_logo(ImgPathUitls.filterImagePath(shop.getShop_logo()));
		}
		
		return shop;
	}
	
	public Shopconfig getShopconfigById(Integer shop_id) {
		Shopconfig shop = shopconfigDao.getShopconfigById(shop_id);
		if(shop != null){
			String area_attr = shop.getArea_attr();
			if(StringUtils.isNotEmpty(area_attr)){
				String area_name = areaService.getAreaAttrNameByAreaIdAttr(area_attr, "1");
				shop.setArea_name(area_name);
			}
			
			shop.setShop_logo(ImgPathUitls.filterImagePath(shop.getShop_logo()));
		}
		
		return shop;
	}
	
	//保存商家店铺信息
	public void save(Shopconfig shopconfig){
		this.shopconfigDao.update(shopconfig);
		
//		if(getShopconfig(shopconfig.getCust_id()) != null){
//			this.shopconfigDao.update(shopconfig);
//		}else{
//			//未关闭
//			shopconfig.setIs_close("1");
//			this.shopconfigDao.insert(shopconfig);
//		}
	}
	
	/**
	 * 删除商家店铺
	 * @param cust_id 商家标识
	 * @author 陈显革
	 */
	public void deleteShopconfigByCustid(String cust_id) {
		this.shopconfigDao.deleteShopconfigByCustid(cust_id);
	}
	
	/**
	 * 新增商家店铺
	 * @param shopconfig
	 */
	public void insert(Shopconfig shopconfig) {
		this.shopconfigDao.insert(shopconfig);
	}
	
	/**
	 * 根据商家id,查询商家店铺
	 * @param cust_id
	 * @return
	 * @author 陈显革
	 */
	public Shopconfig selectShopconfigWithAreaNameByCustid(Integer cust_id) {
		return this.shopconfigDao.selectShopconfigWithAreaNameByCustid(cust_id);
	}
	
	/**
	 * 查询店铺详细信息
	 * @param map (hasAreaName, hasMember, hasLevel 当值为1时,会查询出所在地区,商家名称, 会员级别)
	 * @return
	 * @author 陈显革
	 */
	public List<Map<String, Object>> selectShopconfigDetail(Map<String, String> map) {
		return this.shopconfigDao.selectShopconfigDetail(map);
	}

	public List<Map<String, Object>> getitemsBycustid(String string) {
		return this.shopconfigDao.getitemsBycustid(string);
		
	}

}
