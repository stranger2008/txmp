package com.xingfugo.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;




/**
 * @author wyl
 * http 远程请求 返回数据
 */
public class HttpClientUtils {
	
	/**
	 * 以Post方法访问
	 * @param url 请求地址
	 * @param argsMap 携带的参数
	 * @return  String 返回结果
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
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
	
	@SuppressWarnings("deprecation")
	public static String uploadFile(String url, String localFile) throws ClientProtocolException, IOException {  
        HttpClient httpclient = new DefaultHttpClient();  
        HttpPost httpPost = new HttpPost(url);  
        MultipartEntity entity = new MultipartEntity();
        
        if(localFile.contains(",")){
        	String[] file = localFile.split(",");
        	int size = file.length;
        	for(int i = 0; i < size; i++){
        		FileBody fileBody = new FileBody(new File(file[0]));  
        		//StringBody stringBody = new StringBody("文件的描述");  
        		entity.addPart("file"+i, fileBody);  
        		//entity.addPart("desc", stringBody);
        	}
        }
        else{
    		FileBody fileBody = new FileBody(new File(localFile));  
    		//StringBody stringBody = new StringBody("文件的描述");  
    		entity.addPart("file", fileBody);  
    		//entity.addPart("desc", stringBody);
        }
        
        httpPost.setEntity(entity);  
        HttpResponse httpResponse = httpclient.execute(httpPost);
		// 获取返回的数据
		HttpEntity httpEntity = httpResponse.getEntity();
		//byte[] dataByte = null;
		String result = null;
		if (httpEntity != null) {
			result = EntityUtils.toString(httpEntity);
			//byte[] responseBytes = getData(httpEntity);
			//dataByte = responseBytes;
			httpPost.abort();
		}
		//将字节数组转换成为字符串
		//String result = bytesToString(dataByte);
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
	@SuppressWarnings("deprecation")
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