package com.xingfugo.web.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingfugo.business.module.Advertise;
import com.xingfugo.business.service.AdvertiseService;

@Controller
@RequestMapping("/txmj")
public class TxmjController {
	@Autowired
	private AdvertiseService advertiseService;
	
	@RequestMapping("")
	public String index(ModelMap modelMap){
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
}
