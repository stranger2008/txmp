package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.GoodsevalDao;
import com.xingfugo.business.module.Goodseval;
import com.xingfugo.util.ImgPathUitls;

/**
 * @function 功能 商品评价Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Sat Oct 11 11:15:27 CST 2014
 */

@Service
public class GoodsevalService extends GenericService<Goodseval,String>{

	private GoodsevalDao goodsevalDao;
	
	public GoodsevalService() {}
	
	@Autowired
	public GoodsevalService(GoodsevalDao goodsevalDao) {
		super(goodsevalDao);
		this.goodsevalDao = goodsevalDao;
	}

	public List getdetailByGoodsidAndOrderid(Map param) {
		return goodsevalDao.getdetailByGoodsidAndOrderid( param);
	}

	public void deletedetailByGoodsidAndOrderid(Map param) {
		goodsevalDao.deletedetailByGoodsidAndOrderid( param);
		
	}

	public List<Map<String, Object>> getGoodsevallist(String id) {
		List<Map<String,Object>> map = goodsevalDao.getGoodsevallist(id);
		ImgPathUitls.filterImagePath(map);
		return map;
	}

	public List<Map<String, Object>> getGoodsevallevel(String id) {
		return goodsevalDao.getGoodsevallevel(id);
	}

	public List<Map<String, Object>> getsummember(String id) {
		return goodsevalDao.getsummember(id);
	}

	public List<Map<String, Object>> getgoodsnum(String id) {
		return goodsevalDao.getgoodsnum(id);
	}

	public List<Map<String, Object>> getSellerinfo(String cust_id) {
		return goodsevalDao.getSellerinfo(cust_id);
	}

	public List<Map<String, Object>> showSelecteval(Map param) {
		return goodsevalDao.showSelecteval(param);
	}

	public String getGoodnumByGoodsid(String id) {
		return goodsevalDao.getGoodnumByGoodsid(id);
	}

	public void updateBytradeid(Map param) {
		goodsevalDao.updateBytradeid(param);
	}

}

