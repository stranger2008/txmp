package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.query.GoodsorderQueryForm;


public interface GoodsorderDao {
	
	public void batchInsertGoodsorder(List list);
	
	public List getGoodsorderByPage(GoodsorderQueryForm goodsorderQueryForm);
	
	//机票订单列表
	public List getAirLineorderByPage(GoodsorderQueryForm goodsorderQueryForm);
	
	//电影票列表
	public List movieOrderListByPage(GoodsorderQueryForm goodsorderQueryForm);
	
	//机票订单详细
	public List getOrderInfoByOrderNo(GoodsorderQueryForm gf);
	//彩票订单列表
	public List getLotteryOrderListByPage(GoodsorderQueryForm gf);
	
	public Goodsorder getGoodsorderByOrderid(String order_id);
	
	public void updateOrderState(Map map);
	
	//对未付款、且已经过去24小时的订单执行交易关闭操作
	public void updateUnpayOrder(Map map);
	
	//获取未付款、且已经过去24小时的订单商品详细，包括商品标识和购买数量
	public List getUnpayOrderGoods(Map map);
	
	//对已发货、未确认收货、且过去7天的订单执行自动确认收货操作，交易成功 
	public void updateUnReceiptOrder(Map map);
	
	//交易订单状态统计
	public List getOrderCount(Map map);
	
	//个人用户付款
	public void userPay(Map map);
	
	//卖家发货
	public void sellerDeliver(Map map);
	
	//个人用户确认收货
	public void userReceipt(Map map);
	
	//对支付之后 订单状态 支付状态进行修改
	public void updateOrderAndPayStatus(Map<String, String> map);

	/**
	 * 查询订单详细信息(后台管理员)
	 * @param order_id
	 * @return
	 * @author 陈显革
	 * @date 2014-09-23
	 */
	public Map<String, Object> selectGoodsorderDetailById(String order_id);
	
	public Goodsorder getByPk(String order_id);
	
	/**
	 * 查询各类订单数量
	 * @param map (cust_id: 商家标识, order_states: 订单状态)
	 * @return
	 * @author 陈显革
	 * @date 2014-09-26
	 */
	public List<Map<String, Object>> selectOrderCountGroupByOrderState(Map<String, String> map);

	
	// 删除订单 将is_del设为1
	public void cancellist(String id);
	// 回收站显示删除的订单  即is_del为1的数据
	public List cancellists(String is_del);
	//还原回收站中删除的订单  即is_del设为0
	public void returncancel(String id);
	//彻底删除回收站中的订单  即is_del设为2
	public void removecancel(String id);

	public List getlistByOrderid(String order_id);
	
}
