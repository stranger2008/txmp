package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.GoodsaskDao;
import com.xingfugo.business.module.Goodsask;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsaskQueryForm;
import com.xingfugo.util.PageResultBuilder;


@Service
public class GoodsaskService {

	@Autowired
	private GoodsaskDao goodsaskDao;
	
	public Integer insertGoodsask(Goodsask goodsask){
		goodsaskDao.insertGoodsask(goodsask);
		return goodsask.getTrade_id();
	}
	
	public PageResult getGoodsaskByPage(GoodsaskQueryForm goodsaskQueryForm){
		List list = goodsaskDao.getGoodsaskByPage(goodsaskQueryForm);
		PageResult result = PageResultBuilder.build(goodsaskQueryForm.getPg(), list);
		return result;
	}
	
	public void deleteGoodsask(Integer id){
		goodsaskDao.deleteGoodsask(id);
	}

	public void batchDeleteGoodsask(List<Integer> ids){
		goodsaskDao.batchDeleteGoodsask(ids);
	}
	
	public int getGoodsaskByGoodsid(Integer goods_id){
		return goodsaskDao.getGoodsaskByGoodsid(goods_id);
	}
	
	public int getRecontCountByNowUserid(Integer user_id){
		return goodsaskDao.getRecontCountByNowUserid(user_id);
	}
	
	public Goodsask get(Integer trade_id){
		return this.goodsaskDao.get(trade_id);
	}
	
	public void reply(Goodsask goodsask){
		this.goodsaskDao.reply(goodsask);
	}
	
	public void delete(String id){
		this.goodsaskDao.delete(id);
	}
}
