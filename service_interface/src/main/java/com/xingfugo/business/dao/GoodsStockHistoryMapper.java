package com.xingfugo.business.dao;

import java.util.List;

import com.xingfugo.business.module.GoodsStockHistory;
import com.xingfugo.business.module.query.GoodsStockHistoryQueryForm;

public interface GoodsStockHistoryMapper {
    
	int insert(GoodsStockHistory goodsStockHistory);

    GoodsStockHistory selectByPrimaryKey(Integer id);
    
    List<GoodsStockHistory> selectByPage(GoodsStockHistoryQueryForm queryForm);
    
    /**
     * 批量插入商品库存历史
     * @param goodsStockHistorys
     */
    void batchInsert(List<GoodsStockHistory> goodsStockHistorys);
}