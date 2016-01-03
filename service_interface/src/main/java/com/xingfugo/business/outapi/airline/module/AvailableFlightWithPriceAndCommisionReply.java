package com.xingfugo.business.outapi.airline.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("availableFlightWithPriceAndCommisionReply")
public class AvailableFlightWithPriceAndCommisionReply {
	/**
	 * 返回标识 S-成功;F-失败;
	 */
	@XStreamAlias("returnCode")
	private String returnCode;
	/**
	 * 失败信息
	 */
	@XStreamAlias("returnMessage")
	private String returnMessage;
	/**
	 * 航班信息
	 */
	@XStreamAlias("flightItems")
	private WsFlightWithPriceAndCommisionItem flightItems;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public WsFlightWithPriceAndCommisionItem getFlightItems() {
		return flightItems;
	}

	public void setFlightItems(WsFlightWithPriceAndCommisionItem flightItems) {
		this.flightItems = flightItems;
	}
}
