package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.SellerGoods;
import com.xingfugo.business.module.SellerGoodsWithBLOBs;

public interface SellerGoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);
    
    int batchDelete(List<Integer> goodsIdList);

    int insert(SellerGoodsWithBLOBs record);

    int insertSelective(SellerGoodsWithBLOBs record);

    SellerGoodsWithBLOBs selectByPrimaryKey(Integer goodsId);

    
    int updateBaseInfoByPrimaryKeyWithBLOBs(SellerGoodsWithBLOBs record);

    int updateByPrimaryKey(SellerGoods record);
    
    void updateStockByPrimaryKey(Map<String, Integer> param);
    
    void updateDownDateByPrimaryKey(Map<String, Object> param);

	void returnByPrimaryKey(Integer goodsId);

	void deletegoodsByPrimaryKey(Integer goodsId);

	void batchreturn(List<Integer> delGoodsIdList);
}