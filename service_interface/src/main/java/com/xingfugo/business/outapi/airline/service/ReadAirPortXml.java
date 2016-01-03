package com.xingfugo.business.outapi.airline.service;

import com.lean.redis.JedisService;
import com.xingfugo.business.outapi.airline.module.AirportCode;
import com.xingfugo.business.outapi.airline.module.Airports;
import com.xingfugo.util.CreateSpringContext;
import com.xingfugo.util.XmlUtils;

/**
 * @author wyl
 *	将机场名称，三字码加载到缓存
 */
public abstract class ReadAirPortXml {
	
	public static  Airports airports;

	private static JedisService jedisService = new JedisService();

	static {
		 airports =XmlUtils.toBean(ReadAirPortXml.class.getResourceAsStream("/airportCode.xml"),Airports.class);
				for(AirportCode ac:airports.getAirportCode()){
					jedisService.SETS.sadd(ac.getCode(), ac.getName());
				}
		}
}
