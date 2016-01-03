package com.xingfugo.web.seller.chinapay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//无卡支付工具类
public class PayUtil {
	// 商户号
	public static final String MerId = "808080201305027";

	public static final String domain = "http://119.161.187.240/xfg_web_client/";

	/**
	 * 支付信息是否正常key
	 */
	public static final String PAYMENT_INFO_IS_NORMAL = "isNormal";
	//支付信息正常
	public static final String PAYMENT_INFO_IS_NORMAL_TRUE = "1";
	//支付信息不正常
	public static final String PAYMENT_INFO_IS_NORMAL_FALSE = "0";

	// 获取此目录绝对地址
	public static String getThisPath() {
		return PayUtil.class.getResource("").getPath();
	}

	// 获取当前日期
	public static String getOrderDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	// 获取客户端IP地址
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获取支付信息
	 * @param TransAmt 交易金额
	 * @param OrdNo 订单号
	 * @param ClientIp 客户端ip地址,必须通过PayUtil.getClientIp(HttpServletRequest request)方法取得
	 * @param BgRetUrl 支付完成后银联请求业务处理url(ajax请求)
	 * @param PageRetUrl 支付完成后跳转页面url
	 * @return 支付信息
	 */
	public static Map<String,String> getPayMap(final String TransAmt, final String OrdNo, final String ClientIp, final String BgRetUrl, final String PageRetUrl) {
		//待支付信息
		Map<String,String> payMap = new HashMap<String,String>();
		
		if(OrdNo == null) {
			payMap.put(PAYMENT_INFO_IS_NORMAL, PAYMENT_INFO_IS_NORMAL_FALSE);
			return payMap;
		}
		
		//MerId为ChinaPay统一分配给商户的商户号，15位长度，必填
	    String MerId = PayUtil.MerId;
	    
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		//验证银联key的正确性
		boolean flag = key.buildKey(MerId, 0, PayUtil.getThisPath() + "MerPrK_808080201305027_20140929181055.key");
		if(!flag) {
			payMap.put(PAYMENT_INFO_IS_NORMAL, PAYMENT_INFO_IS_NORMAL_FALSE);
			return payMap;
		}
		
		//由于网银支付接口的订单必须要是16位，但我们系统的订单号是17位
		//即不能用他们的订单号字段，所以用这个备用字段
		if(OrdNo.length() < 16) {
			payMap.put(PAYMENT_INFO_IS_NORMAL, PAYMENT_INFO_IS_NORMAL_FALSE);
			return payMap;
		}
		String OrdId = OrdNo.length() > 16 ? OrdNo.substring(0, 16) : OrdNo;
	    
	    //订单交易币种，3位长度，固定为人民币156，必填
	    String CuryId = "156";
	    
	    //订单交易日期，8位长度，必填
	    String TransDate = PayUtil.getOrderDate();
	    
	    //交易类型，4位长度，必填 0001：付款 0002：退款
	    String TransType = "0001";
	    
	    //加密字符串
	    String ChkValue = "";
	    
	    //接口版本号
	    String Version = "20100304";
		
		String Priv1 = OrdNo;
		
		//发送给银联的信息
		String MsgBody = MerId+ OrdId+ TransAmt+ CuryId+ TransDate+ TransType + Priv1 + ClientIp;
		
		chinapay.SecureLink t = new chinapay.SecureLink (key);
		ChkValue = t.Sign(MsgBody);
		
		//ChkValue= t.signOrder(MerId, OrdId, TransAmt, CuryId, TransDate, TransType) ;
		
		String GateId = "";
		
		payMap.put(PAYMENT_INFO_IS_NORMAL, PAYMENT_INFO_IS_NORMAL_TRUE);
		payMap.put("MerId", MerId);
		payMap.put("OrdId", OrdId);
		payMap.put("TransAmt", TransAmt);
		payMap.put("CuryId", CuryId);
		payMap.put("TransDate", TransDate);
		payMap.put("TransType", TransType);
		payMap.put("Version", Version);
		payMap.put("BgRetUrl", BgRetUrl);
		payMap.put("PageRetUrl", PageRetUrl);
		payMap.put("GateId", GateId);
		payMap.put("Priv1", Priv1);
		payMap.put("ClientIp", ClientIp);
		payMap.put("ChkValue", ChkValue);
		
		return payMap;
	}

	public static void main(String args[]) {
		System.out.println(PayUtil.getOrderDate());
	}
}
