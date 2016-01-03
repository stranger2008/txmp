package com.xingfugo.business.outapi.airline.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.Api_air_orderdetailDao;
import com.xingfugo.business.dao.OrderhistoryDao;
import com.xingfugo.business.module.Api_air_orderdetail;
import com.xingfugo.business.module.Order;
import com.xingfugo.business.module.Orderhistory;
import com.xingfugo.business.outapi.airline.module.Passengers;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.OrderService;
import com.xingfugo.util.DateUtils;

@Service
public class AirLineService {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderhistoryDao orderhistoryDao;
	@Autowired
	private GoodsorderService goodsorderService;
	@Autowired
	private Api_air_orderdetailDao api_air_orderdetailDao;
	//类型 为机票
	public static final String ORDER_TYPE_MOVIE = "airline";
	//卖家会员标识
	public static final Integer SALE_CUST_ID = -1;
	
	/**
	 * @param api_air_orderdetail
	 * @param contact_id
	 * @param journeysheet_id
	 * @param passenge
	 * @param user_id
	 * @param taltolMoney
	 * @param mobile
	 * @param orderId
	 * 来自Android 或者IOS 机票订单
	 */
	public void insertOrderInfo(Api_air_orderdetail api_air_orderdetail,String contact_id,String journeysheet_id,List<Passengers> passenge, String user_id,double taltolMoney,String mobile,String orderId){
		//设置订单属性
		Order order =setOrder( user_id,mobile, new BigDecimal(taltolMoney),orderId);
		//设置Orderhistory属性
		Orderhistory orderHistory =setOrderhistory(user_id);
		//保存订单表，订单历史记录表 ，彩票订单表
		insertOrder(order,api_air_orderdetail,orderHistory,passenge, contact_id, journeysheet_id);
	}
	/**
	 * @param api_air_orderdetail
	 * @param contact_id
	 * @param journeysheet_id
	 * @param passenge
	 * @param user_id
	 * @param taltolMoney
	 * @param mobile
	 * @param orderId
	 * 通过机器人添加机票订单
	 */
	public void insertOrderInfo(Api_air_orderdetail api_air_orderdetail,String contact_id,String journeysheet_id,String passenge, String user_id,double taltolMoney,String mobile,String orderId){
		//设置订单属性
		Order order =setOrder( user_id,mobile, new BigDecimal(taltolMoney),orderId);
		//设置Orderhistory属性
		Orderhistory orderHistory =setOrderhistory(user_id);
		//保存订单表，订单历史记录表 ，彩票订单表
		insertOrder(order,api_air_orderdetail,orderHistory,passenge, contact_id, journeysheet_id);
	}
	
	//设置Orderhistory属性
	public Orderhistory setOrderhistory(String user_id){
		Orderhistory os =new Orderhistory();
		os.setUser_id(null);
		os.setCust_id(user_id);
		os.setAction_name(goodsorderService.ACTION_BUYER_SUBMIT_ORDER);
		return os;
	}
	
	//设置Order属性
	public Order setOrder(String userId,String moblie,BigDecimal total_amount,String orderId){
		Order o =new Order();
		o.setOrderId(orderId);
		o.setBuyCustId(Integer.parseInt(userId));
	 	o.setMobile(moblie);
	 	o.setSaleCustId(SALE_CUST_ID);
	 	o.setGoodsAmount(total_amount);
	 	o.setShipFree(new BigDecimal(0.0));//配送费用
	 	o.setTatalAmount(total_amount);
	 	o.setBuyMode("0");//0表示正常购买
	 	o.setOrderTime(DateUtils.getCurrDate());
	 	o.setConsignee("");
	 	o.setAreaAttr("");
	 	o.setAddress("");
		return o;
	}
	
	/**
	 * @param order
	 * @param api_air_orderdetail
	 * @param orderHistory
	 * @param list
	 * @param contact_id
	 * @param journeysheet_id
	 * 来自Android 或者IOS订单
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void insertOrder(Order order, Api_air_orderdetail api_air_orderdetail,Orderhistory orderHistory,List<Passengers> list,String contact_id,String journeysheet_id){
		
		//等待买家付款
		order.setOrderState(GoodsorderService.STATE_WAIT_BUYER_PAY);
		//订单类型：机票
		order.setOrderType(ORDER_TYPE_MOVIE);
		
		//订单详细
		for(int i =0;i<list.size();i++){
			if(list.get(i)!=null){
				Map map =(Map) list.get(i);
				api_air_orderdetail =bulidApi_air_orderdetail(api_air_orderdetail,contact_id,journeysheet_id,map.get("passenger_id").toString());
				api_air_orderdetail.setOrder_id(order.getOrderId());
				api_air_orderdetailDao.insert(api_air_orderdetail);
			}
		}
		
		//设置同一订单号
		api_air_orderdetail.setOrder_id(order.getOrderId());
		orderHistory.setOrder_id(order.getOrderId());
		
		//买家提交订单
		orderHistory.setAction_name(GoodsorderService.ACTION_BUYER_SUBMIT_ORDER);
		orderService.insertSelective(order);
		orderhistoryDao.insertOrderhistory(orderHistory);
	}
	
	/**
	 * @param order
	 * @param api_air_orderdetail
	 * @param orderHistory
	 * @param passagers
	 * @param contact_id
	 * @param journeysheet_id
	 * 来自机器人的订单
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void insertOrder(Order order, Api_air_orderdetail api_air_orderdetail,Orderhistory orderHistory,String passagers,String contact_id,String journeysheet_id){
		
		//等待买家付款
		order.setOrderState(GoodsorderService.STATE_WAIT_BUYER_PAY);
		//订单类型：机票
		order.setOrderType(ORDER_TYPE_MOVIE);
		
		String []passager=passagers.split(",");
		//订单详细
		for(int i =0;i<passager.length;i++){
			if(StringUtils.isNotBlank(passager[i])){
				String passager_id=passager[i];
				api_air_orderdetail =bulidApi_air_orderdetail(api_air_orderdetail,contact_id,journeysheet_id,passager_id);
				api_air_orderdetail.setOrder_id(order.getOrderId());
				api_air_orderdetailDao.insert(api_air_orderdetail);
			}
		}
		//设置同一订单号
		api_air_orderdetail.setOrder_id(order.getOrderId());
		orderHistory.setOrder_id(order.getOrderId());
		//买家提交订单
		orderHistory.setAction_name(GoodsorderService.ACTION_BUYER_SUBMIT_ORDER);
		orderService.insertSelective(order);
		orderhistoryDao.insertOrderhistory(orderHistory);
	}
	//构建Api_air_orderdetail 对象
	private Api_air_orderdetail bulidApi_air_orderdetail(Api_air_orderdetail aod,String contact_id,String journeysheet_id,String passenger_id){
		Api_air_orderdetail ao =aod;
			ao.setJourneysheet_id(journeysheet_id);
			ao.setContact_id(contact_id);
			ao.setPassenger_id(passenger_id);
		return ao;
	}
}
