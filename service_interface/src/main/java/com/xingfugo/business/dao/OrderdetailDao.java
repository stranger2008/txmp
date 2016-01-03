package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;


public interface OrderdetailDao {
	
	public void batchInsertOrderdetail(List list);
	
	public List getOrderdetailList(String order_id);
	
	public List getOrderdetailByOrderIds(List<String> order_ids);
	
	public List<Map<String,Object>> getOrderdetailForGoodsreturn(Map map);
	
	public List<Map<String, Object>> getGoodsorderevaldetail(String order_id);

	public List<Map<String, Object>> getsellereval(String order_id);
}
