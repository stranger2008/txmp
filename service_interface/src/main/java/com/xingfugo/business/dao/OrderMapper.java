package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Order;
import com.xingfugo.business.module.query.OrderQueryForm;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
	List<Order> selectOrderByPage(OrderQueryForm orderQueryForm);
}