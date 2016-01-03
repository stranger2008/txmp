package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.outapi.airline.module.Passengers;

public interface PassengersDao {
	/**
	 * @param userId
	 * @return
	 * 根据登录ID获取乘机人列表
	 */
	public List<Passengers> passengersListById(int userId);
	
	/**
	 * 添加乘机人
	 */
	public Integer insertPassenger(Passengers passengers);
	
	/**
	 * @param id
	 * 删除乘机人
	 */
	public void deletePassengerById(String id);
	
	/**
	 * @param userIds
	 * @return
	 * 根据选中的checkbox 查找对应的 乘机人
	 */
	public List<Passengers> passengersListByIds(List list);
	
	/**
	 * @param id
	 * @return
	 * 
	 */
	public  Passengers getPaaserger(String id);
}
