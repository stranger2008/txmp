package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.OrderdetailDao;
import com.xingfugo.business.module.Orderdetail;
import com.xingfugo.util.ImgPathUitls;


@Service
public class OrderdetailService{
	
	@Autowired
	private OrderdetailDao orderdetailDao;
	
	public List<Map<String,Object>> getOrderdetailForGoodsreturn(Map param){
		List<Map<String,Object>> map = orderdetailDao.getOrderdetailForGoodsreturn(param);
		ImgPathUitls.filterImagePath(map);
		return map;
	}
	
	public List getOrderdetailList(String order_id) {
		return orderdetailDao.getOrderdetailList(order_id);
	}

	public List<Map<String, Object>> getGoodsorderevaldetail(String order_id){
		List<Map<String,Object>> map = orderdetailDao.getGoodsorderevaldetail(order_id);
		return map;
	}

	public List<Map<String, Object>> getsellereval(String order_id) {
		return orderdetailDao.getsellereval(order_id);
	}
}
