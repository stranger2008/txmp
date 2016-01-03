package com.xingfugo.business.service;

import org.springframework.stereotype.Service;

@Service
public class UserCartService extends CartService {
	private static final String KYE_USER_CART_PREFIX = "user_cart:";
	
	@Override
	public int redisExpiredSeconds() {
		return 0;
	}

	@Override
	public String redisUserCartKey(String user_id){
		return KYE_USER_CART_PREFIX + user_id;
	}
}
