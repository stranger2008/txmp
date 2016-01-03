package com.xingfugo.business.outapi.airline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.JourneysheetDao;
import com.xingfugo.business.outapi.airline.module.JourneySheet;

/**
 * @author wyl
 *  行程单管理
 */
@Service
public class JourneysheetService {

	@Autowired
	private JourneysheetDao journeysheetDao;
	
	/**
	 * 添加行程单
	 */
	public Integer  insertJourneySheet(JourneySheet journeySheet){
		try {
			return 	journeysheetDao.insertJourneySheet(journeySheet);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * @return
	 * 根据登录ID获取配送信息
	 */
	public List<JourneySheet> getJourneySheetById(String userId){
		
		return journeysheetDao.getJourneySheetById(userId);
	}
	
	/**
	 * 修改配送信息
	 */
	public Integer updateJourneySheet(JourneySheet journeySheet){
		 try {
			journeysheetDao.updateJourneySheet(journeySheet);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
}
