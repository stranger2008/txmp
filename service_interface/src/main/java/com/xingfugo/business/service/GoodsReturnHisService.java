package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.GoodsReturnHisDao;
import com.xingfugo.business.module.GoodsReturnHis;
@Service
public class GoodsReturnHisService {
	@Autowired
	private GoodsReturnHisDao goodsReturnHisDao;
	
	public List<GoodsReturnHis> getByLinkId(String link_id)
	{
		return goodsReturnHisDao.getByLinkId(link_id);
	}
   
}
