package com.xingfugo.business.module.mybatis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.xingfugo.util.SerializeUtil;

//mybatis整合redis缓存实现
public class MybatisRedisCache implements Cache {
	private static Logger LOG = LoggerFactory.getLogger(MybatisRedisCache.class.getSimpleName());
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id = null;
	
	public MybatisRedisCache(String id){
		this.id = id;
	}
	
	private Jedis getJedis(){
		return RedisClient.JEDIS_CACHE_POOL.getResource();
	}
	
	public void clear() {
		Jedis jedis = getJedis();
		jedis.flushDB();
		RedisClient.JEDIS_CACHE_POOL.returnResource(jedis);
	}

	public String getId() {
		return this.id;
	}

	public Object getObject(Object key) {
		Jedis jedis = getJedis();
		Object value = SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key.toString())));  
		RedisClient.JEDIS_CACHE_POOL.returnResource(jedis);
        LOG.info("Redis getObject:{}={}", key, value);
        return value;		
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock; 
	}

	public int getSize() {
		Jedis jedis = getJedis();
		int size = Integer.valueOf(jedis.dbSize().toString());
		RedisClient.JEDIS_CACHE_POOL.returnResource(jedis);
		return size;
	}

	public void putObject(Object key, Object value) {
		Jedis jedis = getJedis();
        jedis.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value)); 
        LOG.info("Redis putObject:{}={}", key, value);  
		RedisClient.JEDIS_CACHE_POOL.returnResource(jedis);
	}

	public Object removeObject(Object key) {
		Jedis jedis = getJedis();
		Object obj = jedis.expire(SerializeUtil.serialize(key.toString()),0);
		RedisClient.JEDIS_CACHE_POOL.returnResource(jedis);
		return obj;
	}
}
