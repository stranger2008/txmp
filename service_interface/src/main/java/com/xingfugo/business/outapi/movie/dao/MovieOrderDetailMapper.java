package com.xingfugo.business.outapi.movie.dao;

import com.xingfugo.business.outapi.movie.module.local.MovieOrderDetail;

public interface MovieOrderDetailMapper {
    int insert(MovieOrderDetail movieOrderDetail);

    MovieOrderDetail selectByPrimaryKey(Integer id);
    
    MovieOrderDetail selectByOrderId(String orderId);
}