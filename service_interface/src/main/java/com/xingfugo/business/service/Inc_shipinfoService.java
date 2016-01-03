package com.xingfugo.business.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Inc_shipinfoDao;
import com.xingfugo.business.module.Inc_shipinfo;

/**
 * @function 功能 物流运送信息Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Fri Sep 26 19:19:33 CST 2014
 */

@Service
public class Inc_shipinfoService extends GenericService<Inc_shipinfo,String>{

	private Inc_shipinfoDao inc_shipinfoDao;
	
	public Inc_shipinfoService() {}
	
	@Autowired
	public Inc_shipinfoService(Inc_shipinfoDao inc_shipinfoDao) {
		super(inc_shipinfoDao);
		this.inc_shipinfoDao = inc_shipinfoDao;
	}

	/**
	 * 根据关联标识和会员类型查找物流信息
	 */
	public Inc_shipinfo getByLinkId(String link_id , String user_type){
		Map param = new HashMap();
		param.put("link_id", link_id);
		param.put("user_type", user_type);
		return inc_shipinfoDao.getByLinkId(param);
	}
}

