package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Advertise;

public interface AdvertiseDao {
	List<Advertise> getAllAdvertiseList(String posType);
}
