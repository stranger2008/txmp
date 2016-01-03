package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class OrderDetailItemList {
	@XStreamImplicit
	private List<OrderDetailItem>  orderDetailItemList;

	public List<OrderDetailItem> getOrderDetailItemList() {
		return orderDetailItemList;
	}

	public void setOrderDetailItemList(List<OrderDetailItem> orderDetailItemList) {
		this.orderDetailItemList = orderDetailItemList;
	}

}
