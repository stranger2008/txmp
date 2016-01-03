package com.xingfugo.business.service;

import org.springframework.stereotype.Service;

@Service
public class VisitorCartService extends CartService {

	private static final String KYE_USER_CART_PREFIX = "visitor_cart:";
	
	//设置游客购物车过期时间6个小时
	@Override
	public int redisExpiredSeconds() {
		//60*60*6 6个小时
		return 21600;
	}

	@Override
	public String redisUserCartKey(String user_id) {
		return KYE_USER_CART_PREFIX + user_id;
	}
}
