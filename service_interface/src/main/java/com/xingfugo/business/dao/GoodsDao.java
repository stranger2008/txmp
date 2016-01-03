package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.SimpleGoodsCartItem;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.module.Goods;


public interface GoodsDao {
	
	public List<Map<String, Object>> getGoodsListByPage(GoodsQueryForm goodsQueryForm);
	
	public List<Map<String, Object>> getGoodsList(GoodsQueryForm goodsQueryForm);
	public int getTotalNum(GoodsQueryForm goodsQueryForm);
	
	public Map<String, Object> getGoodsDetailById(int goodsId);
	
	public List<Map<String, Object>> getGoodsDetailListByIds(List<Integer> goodsIdList);
	
	public Map<String, Object> getGoodsDetailWithCustById(int goodsId);
	
	public List<Map<String, Object>> getGoodsDetailListWithCustByIds(List<Integer> goodsIdList);
	
	public void updateStockAndSaledNum(int goodsId);
	
	public void batchUpdateStockAndSaledNum(List<SimpleGoodsCartItem> goodsCartItemList);
	
	//猜你喜欢
	public List getGoodsOfUserLikeWeGuess();
	
	//批量修改商品库存，批量取消订单的时候批量回滚库存和销售量
	public void batchUpdateStockAndSaledNumByRollback(List list);
	
	/**
	 * 根据商品id,查询商品信息
	 * @param goods_id 商品id
	 * @return 商品信息对象
	 * @author 陈显革
	 * @date 2014-09-12
	 */
	public Goods getByPk(String goods_id);
	
	/**
	 * 更新商品状态,当未审核通过时,可附加未通过原因
	 * @param map
	 * @author 陈显革
	 * @date 2014-09-12
	 */
	public void updateGoodsStatus(Map<String, String> map);
	
	/**
	 * 删除商品
	 * @param goods_id
	 * @author 陈显革
	 * @date 2014-09-12
	 */
	public void delete(String goods_id);
	
	/**
	 * 取得各种状态商品的数量
	 * @return
	 * @author 陈显革
	 * @date 2014-09-18
	 */
	public List<Map<String, Object>> getGoodsStatusCounts(GoodsQueryForm goodsQueryForm);

	
	/**
	 * 用于生成静态页面，查询所有goods
	 * @param attrMap 
	 * @return
	 */
	public List<Map<String, Object>> getListForStatic(Map<String, String> attrMap);

	void returnByPrimaryKey(Integer goodsId);

	void delete_0(Integer id);

	public void batchreturn(List<Integer> delGoodsIdList);

	public List<Map<String, Object>> getAllListForStatic();
}
