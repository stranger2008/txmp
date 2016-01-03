package com.xingfugo.web.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Buyeraddr;
import com.xingfugo.business.module.User;
import com.xingfugo.business.service.AreaService;
import com.xingfugo.business.service.BuyeraddrService;
import com.xingfugo.web.client.common.SessionUtil;

//收货地址
@Controller
public class BuyeraddrController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(BuyeraddrController.class.getSimpleName());
	
	//限制收货地址的数量 必须减1
	private final static Integer limitBuyerAddrNum = 9 ;
	
	@Autowired
	private BuyeraddrService buyeraddrService;
	@Autowired
	private AreaService areaService;
	
	private final static String ORDER_ADDRESS_MAP = "order_address_map";
	
	//列表
	@RequestMapping(value="user/addrlist",method=RequestMethod.GET)
	public String addrlist(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		List addrlist = buyeraddrService.getBuyeraddrByUserId(user_id);
		model.addAttribute("addrlist", addrlist);
		model.addAttribute("addrNum", addrlist.size());
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
	public String addradd(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		List addrlist = buyeraddrService.getBuyeraddrByUserId(user_id);
		if(addrlist.size() > limitBuyerAddrNum)
			return "redirect:"+basePath()+"user/addrlist.action";
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
	
	@RequestMapping(value="user/updateBuyerAddr",method=RequestMethod.POST)
	public String updateBuyerAddr(HttpServletRequest request,@Valid Buyeraddr buyeraddr,Errors errors){
		if (errors.hasErrors()){
	           return "user/addr/edit";
		}
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		buyeraddrService.updateBuyeraddr(buyeraddr);
		return "redirect:"+basePath()+"user/addrlist.action";
	}
	
	@RequestMapping(value="user/addrupdate",method=RequestMethod.POST)
	@ResponseBody
	public String addrupdate(HttpServletRequest request,@Valid Buyeraddr buyeraddr,Errors errors){
		if (errors.hasErrors()){
	           return "user/addr/edit";
		}
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		buyeraddrService.updateBuyeraddr(buyeraddr);
		return String.valueOf("success");
	}
	
	//ajax修改地址
	@RequestMapping(value="user/addrupdate-ajax",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addrinsert(Buyeraddr buyeraddr, ModelMap model){
		String user_id = SessionUtil.getString(this.getRequest(),Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		buyeraddrService.updateBuyeraddr(buyeraddr);
		return String.valueOf(buyeraddr.getAddr_id());
	}
	
	
	//修改为默认地址
	@RequestMapping(value="user/addrEditDefault-{id}",method=RequestMethod.GET)
	@ResponseBody
	public String addrEditDefault(@PathVariable(value = "id") String id,ModelMap model,HttpServletRequest request){
		Buyeraddr buyeraddr = buyeraddrService.getByPk(id);
		buyeraddr.setIs_default("1");
		buyeraddrService.updateBuyeraddr(buyeraddr);
		return String.valueOf("success");
	}
	
	//查看收货地址多少个
	@RequestMapping(value="user/testBuyerAddrNum",method=RequestMethod.GET)
	@ResponseBody
	public Integer testBuyerAddrNum(HttpServletRequest request) throws Exception {
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		List addrlist = buyeraddrService.getBuyeraddrByUserId(user_id);
		return addrlist.size();
	}
	//
	
	//对象转MAP
	public Map buyeraddr2Map(Buyeraddr buyeraddr){
		if(buyeraddr == null){
			return new HashMap();
		}
		String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(buyeraddr.getArea_attr());
		Map<String,String> map = new HashMap<String,String>();
		map.put("area_attr", buyeraddr.getArea_attr());
		map.put("area_name_str", area_name_str);
		map.put("cust_name", buyeraddr.getCust_name());
		map.put("cell_phone", buyeraddr.getCell_phone());
		map.put("addr_id", String.valueOf(buyeraddr.getAddr_id()));
		map.put("address", buyeraddr.getAddress());
		return map;
	}
}
