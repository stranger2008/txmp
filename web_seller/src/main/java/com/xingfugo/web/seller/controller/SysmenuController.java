package com.xingfugo.web.seller.controller;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.service.SysmenuService;
import com.xingfugo.web.seller.common.SessionUtil;

//后台菜单
@Controller
public class SysmenuController extends BaseController{
	
	@Autowired
	private SysmenuService sysmenuService;
	
	/**
	 * 商家后台，点击一级菜单显示二级菜单
	 */
	@RequestMapping(value="public/getdownmenu",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getdownmenu(String up_menu_id) throws Exception {
		SessionUtil.putString(getRequest(),"one_menu_id",up_menu_id);
		List menuList = this.sysmenuService.getMenuListByUpmenuid(up_menu_id);
		JSONArray jsonArray = JSONArray.fromObject(menuList);
		return jsonArray.toString();
	}
}
