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
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.FundaccountQueryForm;
import com.xingfugo.business.service.FundaccountService;

/**
 * @function 功能 会员资金表Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:24:02 CST 2014
 */
 
@Controller
public class FundaccountController extends BaseController{
	
	//个人-会员类型-资金账户
	private static final String PERSONAL_CUST_TYPE_OF_FUNDACCOUNT = "0";
	//企业-会员类型-资金账户
	private static final String ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT = "1";
	
	@Autowired
	private FundaccountService fundaccountService;
	
	//个人资金
	@RequestMapping(value="fundaccount/memberuser-index")
	public String memberuserIndex(FundaccountQueryForm fundaccountQueryForm,ModelMap model) throws Exception {
		fundaccountQueryForm.setCust_type(PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
		PageResult pageResult = fundaccountService.getFundaccountListOfMemberuserByPage(fundaccountQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("fundaccountQueryForm", fundaccountQueryForm);
		return "fundaccount/memberuser-index";
	}
	
	//商家资金
	@RequestMapping(value="fundaccount/member-index")
	public String memberIndex(FundaccountQueryForm fundaccountQueryForm,ModelMap model) throws Exception {
		fundaccountQueryForm.setCust_type(ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT);
		PageResult pageResult = fundaccountService.getFundaccountListOfMemberByPage(fundaccountQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("fundaccountQueryForm", fundaccountQueryForm);
		return "fundaccount/member-index";
	}
	
	//启用
	@RequestMapping(value="fundaccount/enable")
	public String enable(String id,RedirectAttributes rAttr) throws Exception {
		fundaccountService.enableAccount(id);
		operatePrompt(rAttr, "启用资金账户成功");
		return "redirect:"+basePath()+"fundaccount/index.action";
	}
	//禁用
	@RequestMapping(value="fundaccount/disable")
	public String disable(String id,RedirectAttributes rAttr) throws Exception {
		fundaccountService.disableAccount(id);
		operatePrompt(rAttr, "禁用资金账户成功");
		return "redirect:"+basePath()+"fundaccount/index.action";
	}
	
	//进入新增
	@RequestMapping(value="fundaccount/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Fundaccount fundaccount = new Fundaccount();
		model.addAttribute("fundaccount", fundaccount);
		return "fundaccount/add";
	}
	
	//新增
	@RequestMapping(value="fundaccount/add",method=RequestMethod.POST)
	public String insert(@Valid Fundaccount fundaccount,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "fundaccount/add";
		}
		fundaccountService.insert(fundaccount);
		operatePrompt(rAttr, "新增会员资金表成功");
		return "redirect:"+basePath()+"fundaccount/index.action";
	}
	
	//进入修改
	@RequestMapping(value="fundaccount/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Fundaccount fundaccount = fundaccountService.getByPk(id);
		model.addAttribute("fundaccount", fundaccount);
		return "fundaccount/update";
	}
	
	//修改
	@RequestMapping(value="fundaccount/update",method=RequestMethod.POST)
	public String update(@Valid Fundaccount fundaccount,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "fundaccount/update";
		}
		fundaccountService.update(fundaccount);
		operatePrompt(rAttr, "修改会员资金表成功");
		return "redirect:"+basePath()+"fundaccount/index.action";
	}
	
	//删除
	@RequestMapping(value="fundaccount/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		fundaccountService.delete(id);
		operatePrompt(rAttr, "删除会员资金表成功");
		return "redirect:"+basePath()+"fundaccount/index.action";
	}
	
}

