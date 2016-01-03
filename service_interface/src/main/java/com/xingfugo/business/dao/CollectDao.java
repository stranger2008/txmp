package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Collect;
import com.xingfugo.business.module.query.CollectQueryForm;


public interface CollectDao {
	
	public void insertCollect(Collect collect);
	
	public void batchInsertCollect(List<Collect> collectList);
	
	public void deleteCollect(Integer id);
	
	public void batchDeleteCollect(List<Integer> ids);
	
	public List getCollectGoodsListByPage(CollectQueryForm collectQueryForm);
	
	public List getCollectShopListByPage(CollectQueryForm collectQueryForm);
	
	public List getCollectListByMap(Map map);
	//批量删除
	public void delete(String coll_id);
	
}
