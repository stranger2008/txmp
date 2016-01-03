package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class SMSVoucherQueryVoucherInfoRes {
	
	@XStreamImplicit(itemFieldName="item")
	private List<VoucherInfo> voucherInfoList;

	public List<VoucherInfo> getVoucherInfoList() {
		return voucherInfoList;
	}

	public void setVoucherInfoList(List<VoucherInfo> voucherInfoList) {
		this.voucherInfoList = voucherInfoList;
	}
}
