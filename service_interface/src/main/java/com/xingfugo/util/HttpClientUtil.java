package com.xingfugo.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author wyl
 * http 远程请求 返回数据
 */
public class HttpClientUtil {
	
	/**
	 * 以Post方法访问
	 * @param url 请求地址
	 * @param argsMap 携带的参数
	 * @return  String 返回结果
	 * @throws Exception
	 */
	public static String POSTMethod(String url,Map<String, Object> argsMap) throws Exception{
		byte[] dataByte = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		//设置参数
		UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(setHttpParams(argsMap), "UTF-8");
		httpPost.setEntity(encodedFormEntity);
		// 执行请求
		HttpResponse httpResponse = httpClient.execute(httpPost);
		// 获取返回的数据
		HttpEntity httpEntity = httpResponse.getEntity();
		if (httpEntity != null) {
			byte[] responseBytes = getData(httpEntity);
			dataByte = responseBytes;
			httpPost.abort();
		}
		//将字节数组转换成为字符串
		String result = bytesToString(dataByte);
		return result;
	}
	
	
	/**
	 * 获取数据
	 * @param httpEntity
	 * @return
	 * @throws Exception
	 */
	private static byte[] getData(HttpEntity httpEntity) throws Exception{
		BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		bufferedHttpEntity.writeTo(byteArrayOutputStream);
		byte[] responseBytes = byteArrayOutputStream.toByteArray();
		return responseBytes;
	}
	
	/**
	 * 设置HttpPost请求参数
	 * @param argsMap
	 * @return BasicHttpParams
	 */
	private static List<BasicNameValuePair> setHttpParams(Map<String, Object> argsMap){
		List<BasicNameValuePair> nameValuePairList = new ArrayList<BasicNameValuePair>();
		//设置请求参数
		if (argsMap!=null && !argsMap.isEmpty()) {
			Set<Entry<String, Object>> set = argsMap.entrySet();
			Iterator<Entry<String, Object>> iterator = set.iterator();
			while(iterator.hasNext()){
				Entry<String, Object> entry = iterator.next();
				BasicNameValuePair basicNameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				nameValuePairList.add(basicNameValuePair);
			}
		}
		return nameValuePairList;
	}
	
	/**
	 * 将字节数组转换成字符串
	 * @param bytes
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	private static String bytesToString(byte[] bytes) throws UnsupportedEncodingException{
		if (bytes!=null) {
			String returnStr = new String(bytes,"utf-8");
			return returnStr;
		}
		return null;
	}
	
	/**
	 * @param xml
	 * @param url
	 * @return
	 * 发送xml请求
	 */
	public String xmlPost(String xml,String url) {
		  HttpClient httpclient= new DefaultHttpClient(); 
		  byte[] dataByte = null;
		  //将字节数组转换成为字符串
			String result =null;
		try {
			  HttpPost httpPost = new HttpPost(url); 
			  StringEntity myEntity = new StringEntity(xml, "utf-8"); 
			  httpPost.addHeader("Content-Type", "text/xml"); 
			  httpPost.setEntity(myEntity); 
			  HttpResponse httpResponse = httpclient.execute(httpPost); 
			  HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					byte[] responseBytes = getData(httpEntity);
					dataByte = responseBytes;
					httpPost.abort();
				}
				result = bytesToString(dataByte);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
          return result;
	}
	
	
	
	

}