package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VoucherInfo {
	// 凭证编号
	@XStreamAlias("VoucherNo")
	private String voucherNo;

	// 凭证ID
	@XStreamAlias("VoucherId")
	private String voucherId;

	// 条形码图,Base64格式
	@XStreamAlias("BarcodeImage")
	private String barcodeImage;

	// 使用状态,1:未使用;2:已使用;3:使用完;4:过期;5:取消
	@XStreamAlias("UseStatus")
	private Integer useStatus;

	// 短信内容
	@XStreamAlias("SmsContent")
	private String smsContent;

	// 彩信内容
	@XStreamAlias("MmsContent")
	private String mmsContent;

	// 下发时间
	@XStreamAlias("SendTime")
	private String sendTime;

	// 下发状态,1:提交网关;2:下发成功;3:下发失败
	@XStreamAlias("SendStatus")
	private Integer sendStatus;

	// 下发方式,0：不下发;1：下发短信;2：下发彩信;3：下发短彩信
	@XStreamAlias("SendType")
	private Integer sendType;

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public String getBarcodeImage() {
		return barcodeImage;
	}

	public void setBarcodeImage(String barcodeImage) {
		this.barcodeImage = barcodeImage;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getMmsContent() {
		return mmsContent;
	}

	public void setMmsContent(String mmsContent) {
		this.mmsContent = mmsContent;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
}
