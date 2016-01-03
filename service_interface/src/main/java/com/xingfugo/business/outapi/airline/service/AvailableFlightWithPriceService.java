package com.xingfugo.business.outapi.airline.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xingfugo.business.outapi.airline.module.AvailableFlightWithPriceAndCommisionReply;
import com.xingfugo.business.outapi.airline.module.Passengers;
import com.xingfugo.business.outapi.airline.webservice.CreateOrderByPassengerReply;
import com.xingfugo.business.outapi.airline.webservice.WsBookPassenger;
import com.xingfugo.util.HttpClientUtils;
import com.xingfugo.util.XmlUtils;

@Service
public class AvailableFlightWithPriceService {

	/**
	 * 根据查询条件查询机票
	 * @param url
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public AvailableFlightWithPriceAndCommisionReply getAvailableFlightWithPriceAndCommision(String url, Map map){
		AvailableFlightWithPriceAndCommisionReply awac=  null;
		try {
			//通过Httpclient 远程访问,返回xml字符串结果
			String result=HttpClientUtils.POSTMethod(url,map);
			if(StringUtils.isNotBlank(result)){
				//解析xml字符串 
				awac=XmlUtils.toBean(result, AvailableFlightWithPriceAndCommisionReply.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return awac;
	}
	
	
	public CreateOrderByPassengerReply getCreateOrderByPassengerReply(String url, Map map){
		CreateOrderByPassengerReply createOrderByPassengerReply =null;
		//通过Httpclient 远程访问,返回xml字符串结果
		try {
			String result=HttpClientUtils.POSTMethod(url,map);
			if(StringUtils.isNotBlank(result)){
				//解析xml字符串 
				createOrderByPassengerReply=XmlUtils.toBean(result, CreateOrderByPassengerReply.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return  null;
		}
		return createOrderByPassengerReply;
	}
	
	
	/**
	 * 转换
	 */
	public   List<WsBookPassenger> getPassenger(List<Passengers> p){
		List<WsBookPassenger> list =new ArrayList<WsBookPassenger>();
		if(p!=null){
			for(int i =0;i<p.size();i++){
				Map map =(Map)p.get(i);
				WsBookPassenger wp =new WsBookPassenger();
				wp.setBirthday((String)map.get("birthday"));
				wp.setIdentityNo((String)map.get("identityNo"));
				wp.setIdentityType((String)map.get("identityType"));
				wp.setName((String)map.get("name"));
				wp.setType(Integer.parseInt((String)map.get("type")));
				list.add(wp);
			}
		}
		return list;
		
	}
	/**
	 * 转换
	 */
	public   List<WsBookPassenger> convertPassenger(List<Passengers> p){
		List<WsBookPassenger> list =new ArrayList<WsBookPassenger>();
		if(p!=null){
			for(Passengers ps:p){
				WsBookPassenger wp =new WsBookPassenger();
				wp.setBirthday(ps.getBirthday());
				wp.setIdentityNo(ps.getIdentityNo());
				wp.setIdentityType(ps.getIdentityType());
				wp.setName(ps.getName());
				wp.setType(Integer.parseInt(ps.getType()));
				list.add(wp);
			}
		}
		return list;
		
	}
}
