package com.xingfugo.outapi.chinapay;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

//无卡支付工具类
public class PayUtil {
	// 商户号
	public static final String MerId = "808080061794018";

	public static final String domain = "http://119.161.187.240/xfg_cp_client/";

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

	public static void main(String args[]) {
		System.out.println(PayUtil.getOrderDate());
	}
}
