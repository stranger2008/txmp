package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Smshistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.SmshistoryQueryForm;
import com.xingfugo.business.service.SmshistoryService;

/**
 * @function 功能 短信发送记录Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 10:56:02 CST 2014
 */
 
@Controller
public class SmshistoryController extends BaseController{
	
	@Autowired
	private SmshistoryService smshistoryService;
	
	//列表
	@RequestMapping(value="smshistory/index")
	public String list(SmshistoryQueryForm smshistoryQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = smshistoryService.getListByPage(smshistoryQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("smshistoryQueryForm", smshistoryQueryForm);
		return "smshistory/index";
	}	
	
	//删除
	@RequestMapping(value="smshistory/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		smshistoryService.delete(id);
		operatePrompt(rAttr, "删除短信历史记录成功");
		return "redirect:"+basePath()+"smshistory/index.action";
	}
	
}

