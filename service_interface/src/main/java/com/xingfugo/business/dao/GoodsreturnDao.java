package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Goodsreturn;
import com.xingfugo.business.module.query.GoodsreturnQueryForm;



public interface GoodsreturnDao {
	
	public Integer insert(Goodsreturn goodsreturn);
	
	//分页查询
	public List getGoodsreturnByPage(GoodsreturnQueryForm goodsreturnQueryForm);
	
	public List getStateCount(Map map);
	
	/**
	 * 根据主键获取信息(包括用户名称、商品名称等信息)
	 * @param tradeid
	 * @return
	 */
	public Map getByPk(String trade_id);
	/**
	 * 修改退换货状态
	 * @param map
	 */
	public void updateState(Map map);
	/**
	 * 修改退换货信息
	 * @param goodsreturn
	 * @return
	 */
	public int updateInfo(Goodsreturn goodsreturn);
	/**
	 * 根据id查询退换货信息(对应表字段信息)
	 * @param tradeid
	 * @return
	 */
	public Goodsreturn getByTradeid(String tradeid);
	/**
	 * 查询同一订单中是否有未完成的退换货申请
	 * @param goodsreturn
	 * @return int 返回值 大于0表示有；等于0表示无
	 */
	int hasUncomplete(Goodsreturn goodsreturn);
	/**
	 * 获取退换货申请的order_id,和goods_id信息
	 * @return
	 */
	List getOrderAndGoodsId(String user_id);
}
