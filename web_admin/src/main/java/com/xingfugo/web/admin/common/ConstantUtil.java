package com.xingfugo.web.admin.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {

	/**
	 * 商品类别
	 */
	public static final String GOODS_MODULE_TYPE="goods";
	/**
	 * 文章类别
	 */
	public static final String ARTI_MODULE_TYPE="article";
	/**
	 * 广告类别
	 */
	public static final String ADV_MODULE_TYPE="adv";
	
	/**
	 * 广告位类型
	 */
	public static final String ADV_TYPE="adv_type";
	
	//模块类别
	public static Map<String,String> moduleMap ;

	static {
		if(moduleMap==null){
			moduleMap = new HashMap<String,String>();
		}
		moduleMap.put(GOODS_MODULE_TYPE, "商品");
		moduleMap.put(ARTI_MODULE_TYPE, "文章");
		moduleMap.put(ADV_MODULE_TYPE, "广告");
	}
	
}
