package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.Fundhistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.FundhistoryQueryForm;
import com.xingfugo.business.service.FundaccountService;
import com.xingfugo.business.service.FundhistoryService;

/**
 * @function 功能 会员资金异动表Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:27:07 CST 2014
 */
 
@Controller
public class FundhistoryController extends BaseController{
	
	@Autowired
	private FundhistoryService fundhistoryService;
	@Autowired
	private FundaccountService fundaccountService;
	
	//列表
	@RequestMapping(value="fundhistory/index")
	public String list(FundhistoryQueryForm fundhistoryQueryForm,ModelMap model) throws Exception {
		String account_no = fundhistoryQueryForm.getAccount_no();
		if(account_no != null && account_no.trim().length() > 0) {
			Fundaccount fundaccount = fundaccountService.getByPk(account_no);
			if(fundaccount != null) {
				fundhistoryQueryForm.setCust_type(fundaccount.getCust_type());
				model.addAttribute("fundaccount", fundaccount.getAccount_no());
			}
		}
		PageResult pageResult = fundhistoryService.getListByPage(fundhistoryQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("fundhistoryQueryForm", fundhistoryQueryForm);
		return "fundhistory/index";
	}
	
	//进入新增
	@RequestMapping(value="fundhistory/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Fundhistory fundhistory = new Fundhistory();
		model.addAttribute("fundhistory", fundhistory);
		return "fundhistory/add";
	}
	
	//新增
	@RequestMapping(value="fundhistory/add",method=RequestMethod.POST)
	public String insert(@Valid Fundhistory fundhistory,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "fundhistory/add";
		}
		fundhistoryService.insert(fundhistory);
		operatePrompt(rAttr, "新增会员资金异动表成功");
		return "redirect:"+basePath()+"fundhistory/index.action";
	}
	
	//进入修改
	@RequestMapping(value="fundhistory/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Fundhistory fundhistory = fundhistoryService.getByPk(id);
		model.addAttribute("fundhistory", fundhistory);
		return "fundhistory/update";
	}
	
	//修改
	@RequestMapping(value="fundhistory/update",method=RequestMethod.POST)
	public String update(@Valid Fundhistory fundhistory,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "fundhistory/update";
		}
		fundhistoryService.update(fundhistory);
		operatePrompt(rAttr, "修改会员资金异动表成功");
		return "redirect:"+basePath()+"fundhistory/index.action";
	}
	
	//删除
	@RequestMapping(value="fundhistory/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		fundhistoryService.delete(id);
		operatePrompt(rAttr, "删除会员资金异动表成功");
		return "redirect:"+basePath()+"fundhistory/index.action";
	}
	
}

