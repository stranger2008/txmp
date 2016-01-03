package com.xingfugo.web.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadProxyController {
	
	@RequestMapping("getimgpath")
	public String proxyUploadResult(String data, ModelMap modelMap){
		modelMap.addAttribute("data", data);
		return "proxy";
	}
}
