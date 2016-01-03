package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.OrderMapper;
import com.xingfugo.business.module.Order;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.OrderQueryForm;
import com.xingfugo.util.PageResultBuilder;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	public void insert(Order order){
		orderMapper.insert(order);
	}
	
	public void deleteByPrimaryKey(String orderId){
		orderMapper.deleteByPrimaryKey(orderId);
	}
	
	public void insertSelective(Order order){
		orderMapper.insertSelective(order);
	}
	
	public void updateByPrimaryKey(Order order){
		orderMapper.updateByPrimaryKey(order);
	}
	
	public void updateByPrimaryKeySelective(Order order){
		orderMapper.updateByPrimaryKeySelective(order);
	}
	
	public Order selectByPrimaryKey(String orderId){
		return orderMapper.selectByPrimaryKey(orderId);
	}
	
	public PageResult queryOrderByPage(OrderQueryForm orderQueryForm){
		List<Order> orderList = orderMapper.selectOrderByPage(orderQueryForm);
		PageResult pageResutl = PageResultBuilder.build(orderQueryForm.getPg(), orderList);
		return pageResutl;
	}
}
