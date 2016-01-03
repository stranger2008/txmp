package com.xingfugo.web.seller.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Selleraddr;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.SelleraddrQueryForm;
import com.xingfugo.business.service.SelleraddrService;
import com.xingfugo.util.RandomStrUtil;
import com.xingfugo.web.seller.common.SessionUtil;

/**
 * @function 功能 卖家发货地址Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Sep 24 18:42:45 CST 2014
 */
 
@Controller
public class SelleraddrController extends BaseController{
	
	@Autowired
	private SelleraddrService selleraddrService;
	
	private final static String IS_DEFALUT = "0";
	
	//列表
	@RequestMapping(value="selleraddr/index")
	public String list(SelleraddrQueryForm selleraddrQueryForm,ModelMap model,HttpServletRequest request) throws Exception {
		
		String cust_id = SessionUtil.getString(request,Constants.SESSION_CUST_ID);
		selleraddrQueryForm.setCust_id(cust_id);
		PageResult pageResult = selleraddrService.getListByPage(selleraddrQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("selleraddrQueryForm", selleraddrQueryForm);
		return "selleraddr/index";
	}
	
	//进入新增
	@RequestMapping(value="selleraddr/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Selleraddr selleraddr = new Selleraddr();
		selleraddr.setIs_default(IS_DEFALUT);
		model.addAttribute("selleraddr", selleraddr);
		return "selleraddr/add";
	}
	
	//新增
	@RequestMapping(value="selleraddr/add",method=RequestMethod.POST)
	public String insert(@Valid Selleraddr selleraddr,Errors errors,RedirectAttributes rAttr,HttpServletRequest request) throws Exception {
		if (errors.hasErrors()){
	           return "selleraddr/add";
		}
		String id = RandomStrUtil.getSixNumberRand();
		selleraddr.setAddr_id(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-MM-ss");
		selleraddr.setIn_date(df.format(new Date()));
		
		String cust_id = SessionUtil.getString(request,Constants.SESSION_CUST_ID);
		selleraddr.setCust_id(cust_id);
		selleraddrService.updateSelleraddr(selleraddr);
		
		selleraddrService.insert(selleraddr);
		operatePrompt(rAttr, "新增卖家发货地址成功");
		return "redirect:"+basePath()+"selleraddr/index.action";
	}
	
	//进入修改
	@RequestMapping(value="selleraddr/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Selleraddr selleraddr = selleraddrService.getByPk(id);
		model.addAttribute("selleraddr", selleraddr);
		return "selleraddr/update";
	}
	
	//修改
	@RequestMapping(value="selleraddr/update",method=RequestMethod.POST)
	public String update(@Valid Selleraddr selleraddr,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "selleraddr/update";
		}
		if(selleraddr.getIs_default().equals("1")){
			Selleraddr Selleraddr = selleraddrService.getByPk(selleraddr.getAddr_id());
			selleraddr.setIs_default("1");
			selleraddrService.updateSelleraddr(selleraddr);
		}else{
			Selleraddr Selleraddr = selleraddrService.getByPk(selleraddr.getAddr_id());
			selleraddr.setIs_default("0");
			selleraddrService.update(selleraddr);
		}
		
		selleraddrService.update(selleraddr);
		operatePrompt(rAttr, "修改卖家发货地址成功");
		return "redirect:"+basePath()+"selleraddr/index.action";	
	}
	
	
	//删除
	@RequestMapping(value="selleraddr/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		selleraddrService.delete(id);
		operatePrompt(rAttr, "删除卖家发货地址成功");
		return "redirect:"+basePath()+"selleraddr/index.action";
	}
	
}

