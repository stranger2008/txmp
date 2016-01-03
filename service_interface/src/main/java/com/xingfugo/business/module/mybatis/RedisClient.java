package com.xingfugo.business.module.mybatis;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	private static Logger LOG = LoggerFactory.getLogger(RedisClient.class.getSimpleName());
	public static JedisPool JEDIS_CACHE_POOL = null;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new RuntimeException("[redis.properties] is not found!");
		}
		
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(Integer.valueOf(REDIS_CONF.getString("redis.master.pool.max_active")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.master.pool.max_idle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.master.pool.max_wait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.master.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.master.pool.testOnReturn")));
		JEDIS_CACHE_POOL = new JedisPool(config, bundle.getString("redis.master.server.ip"),
				Integer.valueOf(bundle.getString("redis.master.server.port")));
		LOG.info("Redis连接池初始化完成");
		String flushDB = JEDIS_CACHE_POOL.getResource().flushDB();
		LOG.info("Redis 0号数据库清空完成：{}", flushDB);
	}
	
	private RedisClient(){
	}
}
