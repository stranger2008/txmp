package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.outapi.airline.module.JourneySheet;

/**
 * @author wyl
 * 行程单管理
 */
public interface JourneysheetDao {
	
	/**
	 * 添加行程单
	 */
	public Integer insertJourneySheet(JourneySheet journeySheet);
	
	
	/**
	 * @return
	 * 根据登录ID获取配送信息
	 */
	public List<JourneySheet> getJourneySheetById(String userId);
	
	/**
	 * 修改配送信息
	 */
	public void updateJourneySheet(JourneySheet journeySheet);
	
}
