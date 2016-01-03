package com.xingfugo.business.outapi.movie.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.outapi.movie.module.AbstractReq;
import com.xingfugo.business.outapi.movie.module.EmptyBodyReq;
import com.xingfugo.business.outapi.movie.module.ReqHeader;
import com.xingfugo.util.HttpClientUtils;
import com.xingfugo.util.Md5;
import com.xingfugo.util.XmlUtils;

@Service
public class HttpClientService {
	private static Logger LOG = LoggerFactory.getLogger(HttpClientService.class);
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private ConfigService movieConf;
	
	public String getMovieInterfaceURL(){
		return movieConf.getServerRootURL() + movieConf.getInterfaceURL();
	}
	
	public String getMovieSeatSelectURL(){
		return movieConf.getServerRootURL() + movieConf.getSeatSelectURL();
	}
	
	public String getAppKey(){
		return movieConf.getAppKey();
	}
	
	public int getMaxSelectedSeat(){
		return movieConf.getMaxSelectedSeat();
	}
	
	public String getAppCode(){
		return movieConf.getAppCode();
	}
	
	public String getValidCode(String timeStamp){
		String key = timeStamp + getAppCode();
		return Md5.getMD5(key.getBytes());
	}
	
	@Deprecated
	public String getSeatCallBackURL(boolean isEncode) {
		//TODO 调整具体的选座回调URL
		String callBackURL = "http://119.161.187.240/xfg_cp_client/movie/save.action";
		if(!isEncode){
			return callBackURL;
		}
		
		try {
			callBackURL = URLEncoder.encode(callBackURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return callBackURL;
		}

		return callBackURL;
	}
	
	@Deprecated
	public String getSeatValidCode(String seqNo, String price, String cityNo,
			boolean isCustomPrice) {
		StringBuffer key = new StringBuffer(getAppKey());
		key.append(seqNo);
		key.append(price);
		key.append(cityNo);
		key.append(isCustomPrice ? 1 : 0);
		key.append(getSeatCallBackURL(false));
		key.append(getAppCode());
		LOG.debug("MD5前的选座信息:{}", key.toString());
		return Md5.getMD5(key.toString().getBytes());
	}
	
	public ReqHeader createReqHeader(String tradeId){
		ReqHeader reqHeader = new ReqHeader();
		reqHeader.setAppKey(getAppKey());
		reqHeader.setTimeStamp(DATE_FORMAT.format(new Date()));
		reqHeader.setTradaId(tradeId);
		reqHeader.setValidCode(getValidCode(reqHeader.getTimeStamp()));
		
		return reqHeader;
	}
	
	public EmptyBodyReq createEmptyBodyReq(String tradeId){
		EmptyBodyReq emptyBodyReq = new EmptyBodyReq();
		emptyBodyReq.setHead(createReqHeader(tradeId));
		//emptyBodyReq.setBody(new EmptyReqBody());
		return emptyBodyReq;
	}
	
	public <T> T post(AbstractReq req, Class<T> clz){
		String xml = XmlUtils.toXml(req);
		LOG.debug("转换XML参数完成：{}", xml);
		Map<String, Object> paramMap = createParamMap(xml);
		String xmlStr = null;
		try {
			xmlStr = HttpClientUtils.POSTMethod(getMovieInterfaceURL(), paramMap);
			LOG.debug("POST请求返回结果：{}", xmlStr);
		} catch (Exception e) {
			LOG.error("提交请求发生异常", e);
			return null;
		}
		
		return XmlUtils.toBean(xmlStr, clz);
	}
	
	private Map<String, Object> createParamMap(String xml){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(movieConf.getInterfaceParam(), xml);
		return paramMap;
	}
}
