package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.GoodsReturnHis;

public interface GoodsReturnHisDao {
	
	public int insert(GoodsReturnHis goodsReturnHis);
	public List<GoodsReturnHis> getByLinkId(String link_id);
}
