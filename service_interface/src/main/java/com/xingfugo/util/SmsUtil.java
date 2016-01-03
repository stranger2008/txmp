package com.xingfugo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//短信接口
public class SmsUtil {
	
	private static final String username = "xfhx";
	private static final String password = "123456";
	private static final String productid = "546523";
	
	public static void main(String[] args) throws IOException {
		//SmsUtil.send("18801005267", "xfhx_test_msg");
		SmsUtil.getBalance();
	}
	
	//查询账户余额，即剩余的短信数
	public static void getBalance() throws IOException{
		StringBuffer sb = new StringBuffer("http://218.85.139.46:9001/balance.do?");
		sb.append("username="+username);
		sb.append("&password="+password);
		sb.append("&productid="+productid);
		URL url = new URL(sb.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputline = in.readLine();
		System.out.println("短信发送剩余条数："+inputline);
	}
	
	//短信发送
	//mobile：手机号码，content：短信内容
	public static String send(String mobile,String content) throws IOException{
		StringBuffer sb = new StringBuffer("http://218.85.139.46:9001/sendXSms.do?");
		sb.append("username="+username);
		sb.append("&password="+password);
		sb.append("&productid="+productid);
		sb.append("&mobile="+mobile);
		sb.append("&content=" + URLEncoder.encode(content, "utf-8"));
		sb.append("&dstime=");
		sb.append("&xh=");
		URL url = new URL(sb.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputline = in.readLine();
		System.out.println("短信发送返回内容："+inputline);
		
		String code = "";
		if(inputline.indexOf(",") > -1){
			code = inputline.substring(0,inputline.indexOf(","));
		}else{
			code = inputline;
		}
		//System.out.println(code);
		return code;
	}
}
