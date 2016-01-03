package com.xingfugo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingfugo.business.module.Advertise;
import com.xingfugo.business.service.AdvertiseService;

@Controller
public class TxmjController {
	@Autowired
	private AdvertiseService advertiseService;
	
	private static List<String> txmcList = null;
	
	static{
		txmcList = new ArrayList<String>();
		txmcList.add("ad1");
		txmcList.add("ad9");
		txmcList.add("ad10");
		txmcList.add("ad11");
		txmcList.add("ad12");
		txmcList.add("ad13");
		txmcList.add("ad14");
		txmcList.add("ad15");
	}
	
	@RequestMapping("txmj")
	public String txmj(ModelMap modelMap){
		List<Advertise> advertiseList = advertiseService.getAllTxmjAdvertise();
		for(Advertise ad : advertiseList){
			String posId = ad.getPos_id().toString();
			posId = "ad" + posId;
			
			Object obj = modelMap.get(posId);
			if(obj == null){
				if("ad1".equals(posId)){
					List<Advertise> adList = new ArrayList<Advertise>();
					adList.add(ad);
					modelMap.addAttribute(posId, adList);
				}
				else{
					modelMap.addAttribute(posId, ad);
				}
				
				continue;
			}
			
			if(obj instanceof List){
				List<Advertise> adList = (List<Advertise>)obj;
				adList.add(ad);
				modelMap.addAttribute(posId, adList);
			}
		}
		
		return "/channel/txmj/index";
	}
	
	
	@RequestMapping("txmc")
	public String txmc(ModelMap modelMap){
		List<Advertise> advertiseList = advertiseService.getAllTxmcAdvertise();
		for(Advertise ad : advertiseList){
			String posId = ad.getPos_id().toString();
			posId = "ad" + posId;
			
			Object obj = modelMap.get(posId);
			if(obj == null){
				if(txmcList.contains(posId)){
					List<Advertise> adList = new ArrayList<Advertise>();
					adList.add(ad);
					modelMap.addAttribute(posId, adList);
				}
				else{
					modelMap.addAttribute(posId, ad);
				}
				
				continue;
			}
			
			if(obj instanceof List){
				List<Advertise> adList = (List<Advertise>)obj;
				adList.add(ad);
				modelMap.addAttribute(posId, adList);
			}
		}
		
		return "/channel/txmc/index";
	}
	
	
	@RequestMapping("txzb")
	public String txzb(ModelMap modelMap){
		List<Advertise> advertiseList = advertiseService.getAllTxzbAdvertise();
		for(Advertise ad : advertiseList){
			String posId = ad.getPos_id().toString();
			posId = "ad" + posId;
			
			Object obj = modelMap.get(posId);
			if(obj == null){
				if("ad1".equals(posId)){
					List<Advertise> adList = new ArrayList<Advertise>();
					adList.add(ad);
					modelMap.addAttribute(posId, adList);
				}
				else{
					modelMap.addAttribute(posId, ad);
				}
				
				continue;
			}
			
			if(obj instanceof List){
				List<Advertise> adList = (List<Advertise>)obj;
				adList.add(ad);
				modelMap.addAttribute(posId, adList);
			}
		}
		
		return "/channel/txzb/index";
	}
}
