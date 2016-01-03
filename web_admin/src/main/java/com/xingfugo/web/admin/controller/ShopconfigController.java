package com.xingfugo.web.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Shopconfig;
import com.xingfugo.business.service.ShopconfigService;
import com.xingfugo.util.ImgPathUitls;

/**
 * @function 功能 店铺信息Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Mon Sep 22 18:16:27 CST 2014
 */
 
@Controller
public class ShopconfigController extends BaseController{
	
	@Autowired
	private ShopconfigService shopconfigService;
	
	//查看
	@RequestMapping(value="shopconfig/view",method=RequestMethod.GET)
	public String view(Integer cust_id,ModelMap model) throws Exception {
		Shopconfig shopconfig = shopconfigService.selectShopconfigWithAreaNameByCustid(cust_id);
		shopconfig.setShop_logo(ImgPathUitls.filterImagePath(shopconfig.getShop_logo()));
		model.addAttribute("shopconfig", shopconfig);
		return "shopconfig/view";
	}
	
}

