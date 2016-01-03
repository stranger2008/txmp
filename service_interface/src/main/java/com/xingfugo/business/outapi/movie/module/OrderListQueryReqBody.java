package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderListQueryReqBody {
	// 用户手机号
	@XStreamAlias("Mobile")
	private String mobile;

	// 开始时间, 时间格式：2012-07-26
	@XStreamAlias("StartDate")
	private String startDate;

	// 结束时间, 时间格式：2012-07-26
	@XStreamAlias("EndDate")
	private String endDate;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



}
