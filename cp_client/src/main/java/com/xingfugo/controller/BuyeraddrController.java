package com.xingfugo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Buyeraddr;
import com.xingfugo.business.service.BuyeraddrService;
import com.xingfugo.business.common.Constants;
import com.xingfugo.common.SessionUtil;

//收货地址
@Controller
public class BuyeraddrController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(BuyeraddrController.class.getSimpleName());
	
	@Autowired
	private BuyeraddrService buyeraddrService;
	
	//列表
	@RequestMapping(value="user/addrlist",method=RequestMethod.GET)
	public String addrlist(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		List addrlist = buyeraddrService.getBuyeraddrByUserId(user_id);
		model.addAttribute("addrlist", addrlist);
		return "user/addr/index";
	}
	
	//删除
	@RequestMapping(value="user/addrdel-{id}",method=RequestMethod.GET)
	public String addrdel(@PathVariable(value = "id") String id){
		buyeraddrService.deleteBuyeraddr(id);
		return "redirect:"+basePath()+"user/addrlist.action";
	}
	
	//添加
	@RequestMapping(value="user/addradd",method=RequestMethod.GET)
	public String addradd(ModelMap model){
		model.addAttribute("buyeraddr", new Buyeraddr());
		return "user/addr/add";
	}
	
	@RequestMapping(value="user/addrinsert",method=RequestMethod.POST)
	public String addrinsert(HttpServletRequest request,@Valid Buyeraddr buyeraddr,Errors errors){
		
		if (errors.hasErrors()){
	           return "user/addr/add";
		}
		
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		buyeraddrService.insertBuyeraddr(buyeraddr);
		return "redirect:"+basePath()+"user/addrlist.action";
	}
	
	//修改
	@RequestMapping(value="user/addredit-{id}",method=RequestMethod.GET)
	public String addrupdate(@PathVariable(value = "id") String id,ModelMap model){
		Buyeraddr buyeraddr = buyeraddrService.getByPk(id);
		model.addAttribute("buyeraddr",buyeraddr);
		return "user/addr/edit";
	}
	
	//修改为默认地址
	@RequestMapping(value="user/addrEditDefault-{id}",method=RequestMethod.GET)
	public String addrEditDefault(@PathVariable(value = "id") String id,ModelMap model){
		Buyeraddr buyeraddr = buyeraddrService.getByPk(id);
		buyeraddr.setIs_default("1");
		buyeraddrService.updateBuyeraddr(buyeraddr);
		return "redirect:"+basePath()+"user/addrlist.action";
	}
	
	@RequestMapping(value="user/addrupdate",method=RequestMethod.POST)
	public String addrupdate(HttpServletRequest request,@Valid Buyeraddr buyeraddr,Errors errors){
		
		if (errors.hasErrors()){
	           return "user/addr/edit";
		}
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		buyeraddrService.updateBuyeraddr(buyeraddr);
		return "redirect:"+basePath()+"user/addrlist.action";
	}
}
