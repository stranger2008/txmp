package com.xingfugo.business.outapi.movie.module.local;

import com.xingfugo.business.module.Order;

public class MovieOrder {
	private Order order;
	private MovieOrderDetail orderDetail;
	private MovieSMSVoucher movieSMSVoucher;
	
	public MovieSMSVoucher getMovieSMSVoucher() {
		return movieSMSVoucher;
	}
	public void setMovieSMSVoucher(MovieSMSVoucher movieSMSVoucher) {
		this.movieSMSVoucher = movieSMSVoucher;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public MovieOrderDetail getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(MovieOrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
}
