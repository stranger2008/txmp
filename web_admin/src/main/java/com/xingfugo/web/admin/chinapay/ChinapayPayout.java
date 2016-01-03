package com.xingfugo.web.admin.chinapay;

public class ChinapayPayout {

	//商户号, 数字, 定长15未
	private String MerId;
	
	//商户日期yyyyMMdd, 数字, 定长8位
	private String MerDate;
	
	//流水号, 数字, 变长16位
	private String MerSeqId;
	
	//收款卡号, 数字, 变长25位
	private String CardNo;
	
	//收款人姓名, 字符, 变长25位
	private String UserName;
	
	//开户银行名称, 字符, 变长50位
	private String OpenBank;
	
	//省份, 字符, 变长20位
	private String Prov;
	
	//城市, 字符, 变长40位
	private String City;
	
	//金额, 单位分, 数字, 变长12位
	private String TransAmt;
	
	//用途, 字符, 变长25位
	private String Purpose;
	
	//支行名称, 字符, 变长80位
	private String SubBank;
	
	//付款标志, 字符, 定长2位
	private String Flag;
	
	//版本号, 字符, 变长8位
	private String Version;
	
	//交易签名, 字符, 定长256位
	private String ChkValue;
	
	
	//应答码
	private String ResponseCode;
	
	//
	private String CpDate;
	
	//
	private String CpSeqId;
	
	//
	private String Stat;
	
	//
	private String FeeAmt;
	
	//
	private String BackDate;
	
	private String PaymentUrl;
	
	private String FromDate;
	private String ToDate;
	private String MerAmt;
	private String Type;
	private String Data;
	
	public String getFromDate() {
		return FromDate;
	}

	public void setFromDate(String fromDate) {
		FromDate = fromDate;
	}

	public String getToDate() {
		return ToDate;
	}

	public void setToDate(String toDate) {
		ToDate = toDate;
	}

	public String getMerAmt() {
		return MerAmt;
	}

	public void setMerAmt(String merAmt) {
		MerAmt = merAmt;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public String toString() {
		return new StringBuffer(MerId)
		.append(MerDate)
		.append(MerSeqId)
		.append(CardNo)
		.append(UserName)
		.append(OpenBank)
		.append(Prov)
		.append(City)
		.append(TransAmt)
		.append(Purpose)
		.append(SubBank)
		.append(Flag)
		.append(Version).toString();
	}

	public String getMerId() {
		return MerId;
	}

	public void setMerId(String merId) {
		MerId = merId;
	}

	public String getMerDate() {
		return MerDate;
	}

	public void setMerDate(String merDate) {
		MerDate = merDate;
	}

	public String getMerSeqId() {
		return MerSeqId;
	}

	public void setMerSeqId(String merSeqId) {
		MerSeqId = merSeqId;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getOpenBank() {
		return OpenBank;
	}

	public void setOpenBank(String openBank) {
		OpenBank = openBank;
	}

	public String getProv() {
		return Prov;
	}

	public void setProv(String prov) {
		Prov = prov;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getPurpose() {
		return Purpose;
	}

	public void setPurpose(String purpose) {
		Purpose = purpose;
	}

	public String getSubBank() {
		return SubBank;
	}

	public void setSubBank(String subBank) {
		SubBank = subBank;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}

	public String getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(String responseCode) {
		ResponseCode = responseCode;
	}

	public String getCpDate() {
		return CpDate;
	}

	public void setCpDate(String cpDate) {
		CpDate = cpDate;
	}

	public String getCpSeqId() {
		return CpSeqId;
	}

	public void setCpSeqId(String cpSeqId) {
		CpSeqId = cpSeqId;
	}

	public String getStat() {
		return Stat;
	}

	public void setStat(String stat) {
		Stat = stat;
	}

	public String getFeeAmt() {
		return FeeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}

	public String getBackDate() {
		return BackDate;
	}

	public void setBackDate(String backDate) {
		BackDate = backDate;
	}

	public String getPaymentUrl() {
		return PaymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		PaymentUrl = paymentUrl;
	}
	
}
