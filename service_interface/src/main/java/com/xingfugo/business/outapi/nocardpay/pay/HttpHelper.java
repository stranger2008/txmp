package com.xingfugo.business.outapi.nocardpay.pay;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class HttpHelper {
	private static Log log = LogFactory.getLog(HttpHelper.class);
	public static int Post(Map<String, String> map, String url, StringBuilder sb) {
		int status = -1;
		BufferedOutputStream out = null;
		BufferedReader in = null;
		String result = "";
		try {
			String param="";
			for (Map.Entry<String, String> entry : map.entrySet()) {
				param+=entry.getKey()+"="+entry
						.getValue()+"&";
			}
			if(!param.isEmpty())param=param.substring(0,param.length()-1);
			
			URL realUrl = new URL(url);
			// �򿪺�URL֮���l��
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setConnectTimeout(Sys.POST_CONN_TIMEOUT);
			conn.setReadTimeout(Sys.POST_SO_TIMEOUT);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new BufferedOutputStream(conn.getOutputStream());
			if (param != null && !param.isEmpty())
				out.write(param.getBytes("utf-8"));
			out.flush();
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				status=200;
				result += line;
			}
			sb.append(result);
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
		}
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {

			}
		}
		return status;
		
		
	}

}
