package com.xingfugo.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.GoodsStockHistoryMapper;
import com.xingfugo.business.module.GoodsStockHistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsStockHistoryQueryForm;
import com.xingfugo.util.PageResultBuilder;

@Service
public class GoodsStockService {
	
	@Autowired
	private GoodsStockHistoryMapper mapper;
	
	public void save(GoodsStockHistory goodsStockHistory){
		mapper.insert(goodsStockHistory);
	}
	
	public PageResult queryPageList(GoodsStockHistoryQueryForm queryForm){
		List<GoodsStockHistory> pageList = mapper.selectByPage(queryForm);
		PageResult result = PageResultBuilder.build(queryForm.getPg(), pageList);
		return result;
	}
	
	/**
     * 批量插入商品库存历史
     * @param goodsStockHistorys
     */
    public void batchInsert(List<GoodsStockHistory> goodsStockHistorys) {
    	mapper.batchInsert(goodsStockHistorys);
    }
}
