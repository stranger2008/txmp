package com.xingfugo.web.seller.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Goodsask;
import com.xingfugo.business.module.query.GoodsaskQueryForm;
import com.xingfugo.business.service.GoodsaskService;
import com.xingfugo.web.seller.common.SessionUtil;
import com.xingfugo.web.seller.module.Goodsaskreply;

//商品咨询
@Controller
public class GoodsaskController extends BaseController{
	
	@Autowired
	private GoodsaskService goodsaskService;
	
	//商品咨询列表，根据cust_id读取
	@RequestMapping(value="goodsask/list")
	public String list(GoodsaskQueryForm goodsaskQueryForm,ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		goodsaskQueryForm.setCust_id(Integer.parseInt(cust_id));
		
		
		Map<Integer, String> seardaysMap = new LinkedHashMap<Integer, String>();  
		seardaysMap.put(10*365, "显示全部");
		seardaysMap.put(1, "一天内");
		seardaysMap.put(7, "一周内");
		seardaysMap.put(30, "一月内");
		seardaysMap.put(365, "一年内");
		
		if(goodsaskQueryForm.getSear_days() == null){
			goodsaskQueryForm.setSear_days(30);
		}
		
		model.put("seardaysMap", seardaysMap);
		pageOper(model,goodsaskService.getGoodsaskByPage(goodsaskQueryForm));
		model.addAttribute("goodsaskQueryForm", goodsaskQueryForm);
		
		return "goodsask/list";
	}
	
	//进入回复页面
	@RequestMapping(value="goodsask/reply-{id}",method=RequestMethod.GET)
	public String reply(@PathVariable(value = "id") Integer id,ModelMap model) throws Exception {
		Goodsask goodsask = this.goodsaskService.get(id);
		Goodsaskreply goodsaskreply = new Goodsaskreply();
		goodsaskreply.setTrade_id(goodsask.getTrade_id());
		goodsaskreply.setRe_content(goodsask.getRe_content());
		goodsaskreply.setC_content(goodsask.getC_content());
		
		model.addAttribute("goodsaskreply", goodsaskreply);
		return "goodsask/reply";
	}
	
	//回复执行操作
	@RequestMapping(value="goodsask/reply",method=RequestMethod.POST)
	public String reply(@Valid Goodsaskreply goodsaskreply,Errors errors,ModelMap model) throws Exception {
		if (errors.hasErrors()){
	        return "goodsask/reply";
		}
		Goodsask goodsask = new Goodsask();
		goodsask.setRe_content(goodsaskreply.getRe_content());
		goodsask.setTrade_id(goodsaskreply.getTrade_id());
		this.goodsaskService.reply(goodsask);
		return "redirect:"+basePath()+"goodsask/list.action";
	}
}
