package com.xingfugo.web.seller.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.Fundrecharge;
import com.xingfugo.business.module.Fundwithdraw;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.FundwithdrawQueryForm;
import com.xingfugo.business.service.FundaccountService;
import com.xingfugo.business.service.FundwithdrawService;
import com.xingfugo.web.seller.common.SessionUtil;

/**
 * @function 功能 资金提现Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Thu Oct 16 16:18:00 CST 2014
 */
 
@Controller
public class FundwithdrawController extends BaseController{
	

	//企业-会员类型-资金账户
	private static final String FUNDACCOUNT_TYPE_ENTERPRISE = "1";
	
	@Autowired
	private FundwithdrawService fundwithdrawService;
	@Autowired
	private FundaccountService fundaccountService;
	
	//列表
	@RequestMapping(value="fundwithdraw/index")
	public String list(FundwithdrawQueryForm fundwithdrawQueryForm ,ModelMap model) throws Exception {
		PageResult pageResult = fundwithdrawService.getListByPage(fundwithdrawQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("fundwithdrawQueryForm", fundwithdrawQueryForm);
		return "fundwithdraw/index";
	}
	
	//进入新增
	@RequestMapping(value="fundwithdraw/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Fundwithdraw fundwithdraw = new Fundwithdraw();
		model.addAttribute("fundwithdraw", fundwithdraw);
		return "fundwithdraw/add";
	}
	
	//新增
	@RequestMapping(value="fundwithdraw/add",method=RequestMethod.POST)
	public String insert(@Valid Fundwithdraw fundwithdraw,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "fundwithdraw/add";
		}
		fundwithdrawService.insert(fundwithdraw);
		operatePrompt(rAttr, "新增资金提现成功");
		return "redirect:"+basePath()+"fundwithdraw/index.action";
	}
	
	//进入修改
	@RequestMapping(value="fundwithdraw/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Fundwithdraw fundwithdraw = fundwithdrawService.getByPk(id);
		model.addAttribute("fundwithdraw", fundwithdraw);
		return "fundwithdraw/update";
	}
	
	//修改
	@RequestMapping(value="fundwithdraw/update",method=RequestMethod.POST)
	public String update(@Valid Fundwithdraw fundwithdraw,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "fundwithdraw/update";
		}
		fundwithdrawService.update(fundwithdraw);
		operatePrompt(rAttr, "修改资金提现成功");
		return "redirect:"+basePath()+"fundwithdraw/index.action";
	}
	
	//删除
	@RequestMapping(value="fundwithdraw/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		fundwithdrawService.delete(id);
		operatePrompt(rAttr, "删除资金提现成功");
		return "redirect:"+basePath()+"fundwithdraw/index.action";
	}
	
}

