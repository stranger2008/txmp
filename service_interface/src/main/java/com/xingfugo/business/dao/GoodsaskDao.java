package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.Goodsask;
import com.xingfugo.business.module.query.GoodsaskQueryForm;


public interface GoodsaskDao {
	
	public void insertGoodsask(Goodsask goodsask);
	
	public List getGoodsaskByPage(GoodsaskQueryForm goodsaskQueryForm);
	
	public void deleteGoodsask(Integer id);
	
	public void batchDeleteGoodsask(List<Integer> ids);
	
	//根据商品ID找出商品对应的咨询总数
	public int getGoodsaskByGoodsid(Integer goods_id);
	
	//返回当前用户当天商品咨询的商家回复数量
	public int getRecontCountByNowUserid(Integer user_id);
	
	//根据主键获取咨询信息
	public Goodsask get(Integer trade_id);
	
	//商家回复咨询
	public void reply(Goodsask goodsask);
	
	public void delete(String id);
}
