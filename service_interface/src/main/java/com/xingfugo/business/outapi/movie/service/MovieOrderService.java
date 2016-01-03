package com.xingfugo.business.outapi.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.OrderhistoryDao;
import com.xingfugo.business.module.Order;
import com.xingfugo.business.module.Orderhistory;
import com.xingfugo.business.outapi.movie.dao.MovieOrderDetailMapper;
import com.xingfugo.business.outapi.movie.dao.MovieSMSVoucherMapper;
import com.xingfugo.business.outapi.movie.module.local.MovieOrder;
import com.xingfugo.business.outapi.movie.module.local.MovieOrderDetail;
import com.xingfugo.business.outapi.movie.module.local.MovieSMSVoucher;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.OrderService;

@Service
public class MovieOrderService {
	
	public static final String ORDER_TYPE_MOVIE = "movie";

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MovieOrderDetailMapper movieOrderDetailMapper;
	
	@Autowired
	private MovieSMSVoucherMapper movieSMSVoucherMapper;
	
	@Autowired
	private OrderhistoryDao orderhistoryDao;
	
	@Transactional
	public Integer insertOrder(Order order, MovieOrderDetail movieOrderDetail,
			Orderhistory orderHistory) {
		//等待买家付款
		try {
			order.setOrderState(GoodsorderService.STATE_WAIT_BUYER_PAY);
			//订单类型：电影票订单
			order.setOrderType(ORDER_TYPE_MOVIE);
			
			//设置同一订单号
			movieOrderDetail.setOrderId(order.getOrderId());
			orderHistory.setOrder_id(order.getOrderId());
			
			//买家提交订单
			orderHistory.setAction_name(GoodsorderService.ACTION_BUYER_SUBMIT_ORDER);

			orderService.insertSelective(order);
			movieOrderDetailMapper.insert(movieOrderDetail);
			orderhistoryDao.insertOrderhistory(orderHistory);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@Transactional
	public void updateOrder(Order order, Orderhistory orderhistory){
		orderhistory.setOrder_id(order.getOrderId());
		
		orderService.updateByPrimaryKeySelective(order);
		orderhistoryDao.insertOrderhistory(orderhistory);
	}
	
	//插入凭证使用信息
	public void insertOrderVoucher(MovieSMSVoucher movieSMSVoucher){
		movieSMSVoucherMapper.insert(movieSMSVoucher);
	}
	
	//更改凭证使用信息
	public void updateOrderVoucher(MovieSMSVoucher movieSMSVoucher){
		movieSMSVoucherMapper.updateByPrimaryKeySelective(movieSMSVoucher);
	}
	
	//电影票订单详情
	public MovieOrder selectOrder(String orderId, boolean includeDetail){
		MovieOrder movieOrder = new MovieOrder();
		Order order = orderService.selectByPrimaryKey(orderId);
		movieOrder.setOrder(order);

		if(includeDetail){
			MovieOrderDetail orderDetail = movieOrderDetailMapper.selectByOrderId(order.getOrderId());
			MovieSMSVoucher movieSMSVoucher = movieSMSVoucherMapper.selectByOrderId(order.getOrderId());
			
			movieOrder.setOrderDetail(orderDetail);
			movieOrder.setMovieSMSVoucher(movieSMSVoucher);
		}
		
		return movieOrder;
	}
	
	public MovieSMSVoucher selectOrderVocher(String orderId){
		MovieSMSVoucher movieSMSVoucher = movieSMSVoucherMapper.selectByOrderId(orderId);
		return movieSMSVoucher;
	}
}
