package com.xingfugo.web.admin.chinapay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import chinapay.Base64;
import chinapay.PrivateKey;
import chinapay.SecureLink;

public class ChinapayUtil {
	
	//商户号key
	private static final String CHINAPAY_MERID_KEY = "chinapay.merid";
	//商户签名私钥文件位置key
	private static final String CHINAPAY_MERKEY_FILE_KEY = "chinapay.merkey.file";
	//银联签名公钥文件位置key
	private static final String CHINAPAY_PUBKEY_FILE_KEY = "chinapay.pubkey.file";
	//支付地址key
	private static final String CHINAPAY_PAYMENT_URL_KEY = "chinapay.payment.url";
	//银联支付版本号
	private static final String CHINAPAY_VERSION_KEY = "chinapay.version";
	
	/**
	 * 获取银联支出(提现)数据
	 * @param chinapayPayout
	 * @return
	 */
	public static ChinapayPayout getChinapayPayout(final ChinapayPayout chinapayPayout) {
		//获取配置信息
		Properties chinapayConfig = ChinapayConfig.getInstance().getProperties();
		//商户号
		String MerId = chinapayConfig.getProperty(CHINAPAY_MERID_KEY);
		//商户密钥文件位置
		String MerkeyFilePath = getPath(ChinapayUtil.class) + chinapayConfig.getProperty(CHINAPAY_MERKEY_FILE_KEY);
		//银联支付版本号
		String Version = chinapayConfig.getProperty(CHINAPAY_VERSION_KEY);
		//支付地址
		String PaymentUrl = chinapayConfig.getProperty(CHINAPAY_PAYMENT_URL_KEY);
		
		//
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		
		//商户日期yyyyMMdd格式
		String MerDate = yyyyMMdd.format(new Date());
		//流水号
		String MerSeqId = chinapayPayout.getMerSeqId();
		//收款人卡号
		String CardNo = chinapayPayout.getCardNo();
		//收款人姓名 中文
		String UserName = chinapayPayout.getUserName();
		//开户行名称 中文
		String OpenBank = chinapayPayout.getOpenBank();
		//开户行省份
		String Prov = chinapayPayout.getProv();
		//开户行城市
		String City = chinapayPayout.getCity();
		//交易金额
		String TransAmt = transAmt2ChinapayTransAmt(chinapayPayout.getTransAmt());
		//用途
		String Purpose = "提现";///
		//支行名称
		String SubBank = chinapayPayout.getSubBank();
		//付款标志
		String Flag = "00";
		
		ChinapayPayout payout = new ChinapayPayout();
		payout.setMerId(MerId);
		payout.setMerDate(MerDate);
		payout.setMerSeqId(MerSeqId);
		payout.setCardNo(CardNo);
		payout.setUserName(UserName);
		payout.setOpenBank(OpenBank);
		payout.setProv(Prov);
		payout.setCity(City);
		payout.setTransAmt(TransAmt);
		payout.setPurpose(Purpose);
		payout.setSubBank(SubBank);
		payout.setFlag(Flag);
		payout.setVersion(Version);
		payout.setPaymentUrl(PaymentUrl);
		
		//用Base64对待签名字符串编码
		String PlainData = payout.toString();
		PlainData = new String(Base64.encode(PlainData.getBytes()));
		
		//使用私/公钥的方式, 固定为0
		int KeyUsage = 0;
		
		//初始化商户私钥
		PrivateKey privateKey = new PrivateKey();
		boolean f1 = privateKey.buildKey(MerId, KeyUsage, MerkeyFilePath);
		//用商户私钥签名
		SecureLink secureLink = new SecureLink(privateKey);
		//交易签名数据
		String ChkValue = secureLink.Sign(PlainData);
		
		payout.setChkValue(ChkValue);
		
		return payout;
	}
	
	/**
	 * 请求银联支出支付(提现)
	 * @param chinapayPayout
	 * @throws java.io.IOException
	 */
	public static String toChinapayPayout(final ChinapayPayout chinapayPayout) throws IOException {
		//获取配置信息
		Properties chinapayConfig = ChinapayConfig.getInstance().getProperties();
		//银联公钥文件位置
		String PubkeyFilePath = getPath(ChinapayUtil.class) + chinapayConfig.getProperty(CHINAPAY_PUBKEY_FILE_KEY);
		
		//签名标志
		String SignFlag = "1";
		
		//请求银联支付
		HttpClient httpClient = new HttpClient();
		//设置http请求参数
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
		//设置请求路径(银联支付路径)
		PostMethod postMethod = new PostMethod(chinapayPayout.getPaymentUrl());
		NameValuePair[] data = { 
				new NameValuePair("merId", chinapayPayout.getMerId()),
				new NameValuePair("merDate", chinapayPayout.getMerDate()),
				new NameValuePair("merSeqId", chinapayPayout.getMerSeqId()),
				new NameValuePair("cardNo", chinapayPayout.getCardNo()),
				new NameValuePair("usrName", chinapayPayout.getUserName()),
				new NameValuePair("openBank", chinapayPayout.getOpenBank()),
				new NameValuePair("prov", chinapayPayout.getProv()),
				new NameValuePair("city", chinapayPayout.getCity()),
				new NameValuePair("transAmt", chinapayPayout.getTransAmt()),
				new NameValuePair("purpose", chinapayPayout.getPurpose()),
				new NameValuePair("subBank", chinapayPayout.getSubBank()),
				new NameValuePair("flag", chinapayPayout.getFlag()),
 				new NameValuePair("version", chinapayPayout.getVersion()),
				new NameValuePair("chkValue", chinapayPayout.getChkValue()),
				new NameValuePair("signFlag", SignFlag)
		};
		postMethod.setRequestBody(data);
		
		try {
			httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//读取请求放回内容
		InputStream is = null;
		try {
			is = postMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//处理放回内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String tempStr = null;
		StringBuffer html = new StringBuffer();
		while((tempStr = reader.readLine()) != null) {
			html.append(tempStr);
		}
		//返回报文
		String resMsg = html.toString();
		int idx = resMsg.lastIndexOf("&");
		
		//使用私/公钥的方式
		final int KeyUsage = 0;
		
		//处理返回数据
		String str[] = resMsg.split("&");
		if(str.length == 10){
			int Res_Code = str[0].indexOf("=");
			int Res_merId = str[1].indexOf("=");
			int Res_merDate = str[2].indexOf("=");
			int Res_merSeqId = str[3].indexOf("=");
			int Res_cpDate = str[4].indexOf("=");
			int Res_cpSeqId = str[5].indexOf("=");
			int Res_transAmt = str[6].indexOf("=");
			int Res_stat = str[7].indexOf("=");
			int Res_cardNo = str[8].indexOf("=");
			int Res_chkValue = str[9].indexOf("=");
		
			String responseCode = str[0].substring(Res_Code+1);
			String MerId = str[1].substring(Res_merId+1);
			String MerDate = str[2].substring(Res_merDate+1);
			String MerSeqId = str[3].substring(Res_merSeqId+1);
			String CpDate = str[4].substring(Res_cpDate+1);
			String CpSeqId = str[5].substring(Res_cpSeqId+1);
			String TransAmt = str[6].substring(Res_transAmt+1);
			String Stat = str[7].substring(Res_stat+1);
			String CardNo = str[8].substring(Res_cardNo+1);
			String ChkValue = str[9].substring(Res_chkValue+1);
			
//			if("0000".equals(responseCode)) {//提交成功
//				return "0300";
//			} else if("0100".equals(responseCode)) {//商户提交的字段长度、格式错误
//				return "0304";
//			} else if("0101".equals(responseCode)) {//商户验签错误
//				return "0305";
//			} else if("0102".equals(responseCode)) {//手续费计算出错
//				return "0306";
//			} else if("0103".equals(responseCode)) {//商户备付金帐户金额不足
//				return "0307";
//			} else if("0104".equals(responseCode)) {//操作拒绝
//				return "0308";
//			} else if("0105".equals(responseCode)) {//重复交易
//				return "0309";
//			}


			System.out.println("responseCode=" + responseCode);
			System.out.println("merId=" + MerId);
			System.out.println("merDate=" + MerDate);
			System.out.println("merSeqId=" + MerSeqId);
			System.out.println("transAmt=" + TransAmt);
			System.out.println("cpDate=" + CpDate);
			System.out.println("cpSeqId=" + CpSeqId);
			System.out.println("Stat=" + Stat);
			System.out.println("cardNo=" + CardNo);
			System.out.println("chkValue=" + ChkValue);


			String msg = resMsg.substring(0,idx);
			String plainData = new String(Base64.encode(msg.getBytes()));
			System.out.println("需要验签的字段：" + msg);

			//传入显示页面的数据准备
//			ChinapayPayout pay = new ChinapayPayout();
//			pay.setResponseCode(responseCode);
//			pay.setMerId(MerId);
//			pay.setMerDate(MerDate);
//			pay.setMerSeqId(MerSeqId);
//			pay.setCpDate(CpDate);
//			pay.setCpSeqId(CpSeqId);
//			pay.setTransAmt(TransAmt);
//			pay.setStat(Stat);
//			pay.setCardNo(CardNo);
//			pay.setData(resMsg);
			
			//对收到的ChinaPay应答传回的域段进行验签		
			boolean buildOK = false;
			
			PrivateKey privateKey = new PrivateKey();
			try {
				buildOK = privateKey.buildKey("999999999999999", KeyUsage, PubkeyFilePath);
			} catch (Exception e) {
				e.printStackTrace();
				return "0302";
			}
			if (!buildOK) {
				System.out.println("build error!");

				if("0000".equals(responseCode)) {//提交成功
					return "0300";
				} else if("0100".equals(responseCode)) {//商户提交的字段长度、格式错误
					return "0304";
				} else if("0101".equals(responseCode)) {//商户验签错误
					return "0305";
				} else if("0102".equals(responseCode)) {//手续费计算出错
					return "0306";
				} else if("0103".equals(responseCode)) {//商户备付金帐户金额不足
					return "0307";
				} else if("0104".equals(responseCode)) {//操作拒绝
					return "0308";
				} else if("0105".equals(responseCode)) {//重复交易
					return "0309";
				}
				//创建银联签名公钥失败
				return "0302";
			}
			
			SecureLink secureLink = new SecureLink(privateKey);
			boolean res = secureLink.verifyAuthToken(plainData, ChkValue);
			if (res){
				System.out.println("验签数据正确!");
				return "0300";
			}
			else {
				System.out.println("签名数据不匹配！");
				return "0303";
			}
		}
	
		//交易失败应答
		if(str.length == 2){
			int Res_Code = str[0].indexOf("=");
			int Res_chkValue = str[1].indexOf("=");
			
			String responseCode = str[0].substring(Res_Code+1);
			String ChkValue_res = str[1].substring(Res_chkValue+1);
			System.out.println("responseCode=" + responseCode);
			System.out.println("chkValue=" + ChkValue_res);
			
			String plainData = str[0];
			String plainData1 = new String(Base64.encode(plainData.getBytes()));
			System.out.println("需要验签的字段：" + plainData);
			
//			if("0000".equals(responseCode)) {//提交成功
//				return "0300";
//			} else if("0100".equals(responseCode)) {//商户提交的字段长度、格式错误
//				return "0304";
//			} else if("0101".equals(responseCode)) {//商户验签错误
//				return "0305";
//			} else if("0102".equals(responseCode)) {//手续费计算出错
//				return "0306";
//			} else if("0103".equals(responseCode)) {//商户备付金帐户金额不足
//				return "0307";
//			} else if("0104".equals(responseCode)) {//操作拒绝
//				return "0308";
//			} else if("0105".equals(responseCode)) {//重复交易
//				return "0309";
//			}
		
			//对收到的ChinaPay应答传回的域段进行验签
			boolean buildOK = false;
			boolean res = false;
			PrivateKey key = new PrivateKey();
			try {
				buildOK = key.buildKey("999999999999999", KeyUsage, PubkeyFilePath);
			} catch (Exception e) {
				e.printStackTrace();
				return "0301";
			}
			if (!buildOK) {
				System.out.println("build error!");
				return "0301";
			}
		
			SecureLink sl = new SecureLink(key);
			res=sl.verifyAuthToken(plainData1,chinapayPayout.getChkValue());
			System.out.println(res);
			if (res){
				System.out.println("验签数据正确!");
				return "0300";
			}
			else {
				System.out.println("签名数据不匹配！");
				if("0000".equals(responseCode)) {//提交成功
					return "0300";
				} else if("0100".equals(responseCode)) {//商户提交的字段长度、格式错误
					return "0304";
				} else if("0101".equals(responseCode)) {//商户验签错误
					return "0305";
				} else if("0102".equals(responseCode)) {//手续费计算出错
					return "0306";
				} else if("0103".equals(responseCode)) {//商户备付金帐户金额不足
					return "0307";
				} else if("0104".equals(responseCode)) {//操作拒绝
					return "0308";
				} else if("0105".equals(responseCode)) {//重复交易
					return "0309";
				}
			}
		}
		return "0300";
	}
	
	/**
	 * 把交易金额转换成银联规定交易金额格式
	 * @param transAmt
	 * @return
	 */
	public static String transAmt2ChinapayTransAmt(String transAmt) {
		transAmt = transAmt.replace(".", "");
		StringBuffer transAmtSB = new StringBuffer();
		for(int i=0; i<12 - transAmt.length(); i++) {
			transAmtSB.append("0");
		}
		transAmtSB.append(transAmt);
		return transAmtSB.toString();
	}
	
	/**
	 * 把交易金额转换成银联规定交易金额格式
	 * @param transAmt
	 * @return
	 */
	public static String transAmt2ChinapayTransAmt(BigDecimal transAmt) {
		transAmt = transAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		return transAmt2ChinapayTransAmt(transAmt.toString());
	}
	
	private static String getPath(Class name) {
		String strResult = null;
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			strResult = name.getResource("").getPath().replace("file:/", "")
					.replace("%20", " ");
		} else {
			strResult = name.getResource("").getPath().replace("file:", "")
					.replace("%20", " ");
		}
		return strResult;
	}
	
	public static void main(String[] args) {
		String data = "80808020130502755845526812521640000000100001562014102200015584552681252164192.168.1.122";
		ChinapayPayout pay = new ChinapayPayout();
		pay.setMerId("808080201305027");
		pay.setUserName("陈显革");
		pay.setCardNo("6222620910002028318");
		pay.setProv("北京市");
		pay.setCity("北京市");
		pay.setMerAmt("10");
		pay.setMerSeqId("1234567891023456");
		//8080802013050272014102314215368423241376222620910002028318陈显革工商银行上海上海000000001000提现浦东支行0020090501
		System.out.println(ChinapayUtil.class.getResource("").getPath());
	}
	
}
