package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SMSVoucherResendReqBody {
	// 凭证号
	@XStreamAlias("VoucherNo")
	private String voucherNo;

	// 手机号
	@XStreamAlias("MobileNo")
	private String mobileNo;
	
	// 发送方式，1:彩信不补发；2:彩信+失败自动补发；3：短信；4: 短信+彩信
	@XStreamAlias("SendType")
	private Integer SendType = 1;

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getSendType() {
		return SendType;
	}

	public void setSendType(Integer sendType) {
		SendType = sendType;
	}
}
