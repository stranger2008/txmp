package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Smshistory;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.query.SmshistoryQueryForm;


public interface SmshistoryDao {
	
	public String getRegCodeByPhone(String phone);
	
	public void insertSmsInfo(Smshistory smshistory);
	
	public String getPhoneBySendDate(String phone);

	public List hasPhonenumber(String cellnumber);
	
	//分页查询
	public List<Map<String,Object>> getListByPage(SmshistoryQueryForm smshistoryQueryForm);
	
	public void delete(String trade_id);
}
