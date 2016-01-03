package com.xingfugo.business.outapi.movie.dao;

import com.xingfugo.business.outapi.movie.module.local.MovieSMSVoucher;

public interface MovieSMSVoucherMapper {

    int insert(MovieSMSVoucher record);

    MovieSMSVoucher selectByPrimaryKey(Integer id);
    
    MovieSMSVoucher selectByOrderId(String orderId);

    int updateByPrimaryKeySelective(MovieSMSVoucher record);

}