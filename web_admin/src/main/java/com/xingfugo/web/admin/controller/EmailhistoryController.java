package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Emailhistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.EmailhistoryQueryForm;
import com.xingfugo.business.service.EmailhistoryService;

/**
 * @function 功能 邮件发送记录Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 10:56:02 CST 2014
 */
 
@Controller
public class EmailhistoryController extends BaseController{
	
	@Autowired
	private EmailhistoryService emailhistoryService;
	
	//列表
	@RequestMapping(value="emailhistory/index")
	public String list(EmailhistoryQueryForm emailhistoryQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = emailhistoryService.getListByPage(emailhistoryQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("emailhistoryQueryForm", emailhistoryQueryForm);
		return "emailhistory/index";
	}
	
	//进入新增
	@RequestMapping(value="emailhistory/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Emailhistory emailhistory = new Emailhistory();
		model.addAttribute("emailhistory", emailhistory);
		return "emailhistory/add";
	}
	
	//新增
	@RequestMapping(value="emailhistory/add",method=RequestMethod.POST)
	public String insert(@Valid Emailhistory emailhistory,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "emailhistory/add";
		}
		emailhistoryService.insert(emailhistory);
		operatePrompt(rAttr, "新增邮件发送记录成功");
		return "redirect:"+basePath()+"emailhistory/index.action";
	}
	
	//进入修改
	@RequestMapping(value="emailhistory/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Emailhistory emailhistory = emailhistoryService.getByPk(id);
		model.addAttribute("emailhistory", emailhistory);
		return "emailhistory/update";
	}
	
	//修改
	@RequestMapping(value="emailhistory/update",method=RequestMethod.POST)
	public String update(@Valid Emailhistory emailhistory,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "emailhistory/update";
		}
		emailhistoryService.update(emailhistory);
		operatePrompt(rAttr, "修改邮件发送记录成功");
		return "redirect:"+basePath()+"emailhistory/index.action";
	}
	
	//删除
	@RequestMapping(value="emailhistory/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		emailhistoryService.delete(id);
		operatePrompt(rAttr, "删除邮件发送记录成功");
		return "redirect:"+basePath()+"emailhistory/index.action";
	}
	
}

