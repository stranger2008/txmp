package com.xingfugo.business.outapi.airline.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.PassengersDao;
import com.xingfugo.business.outapi.airline.module.Passengers;

/**
 * @author wyl
 * 乘机人管理
 */
@Service
public class PassengerService {
	
	@Autowired
	private PassengersDao passengersDao;
	
	/**
	 * @param userId
	 * @return
	 * 根据登录ID获取乘机人列表
	 */
	public List<Passengers> passengersListById(Integer userId){
		if(userId==null){
			return null;
		}
		return passengersDao.passengersListById(userId);  
	}
	
	
	/**
	 * @param id
	 * @return
	 * 根据ID获取乘机人
	 */
	public  Passengers getPaaserger(String id){
		
		return 	passengersDao.getPaaserger(id);
	}
	
	/**
	 * 添加乘机人
	 */
	public Integer insertPassenger(Passengers passengers){
		try {
			return  passengersDao.insertPassenger(passengers);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * @param id
	 * 删除乘机人
	 */
	public Integer deletePassengerById(String id){
		try {
			passengersDao.deletePassengerById(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	/**
	 * 根据选中的checkbox 查找对应的 乘机人
	 */
	public  List<Passengers> passengersListByIds(String userIds){
		if(StringUtils.isNotBlank(userIds)){
			List<String> idList = new ArrayList<String>();
			String[] ids = userIds.split(",");
			for(String idStr : ids){
				idList.add(idStr);
			}
			return passengersDao.passengersListByIds(idList);
			
		}
		return null;
	}
}
