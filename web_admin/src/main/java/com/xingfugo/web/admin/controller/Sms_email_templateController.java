package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Sms_email_template;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.Sms_email_templateQueryForm;
import com.xingfugo.business.service.Sms_email_templateService;

/**
 * @function 功能 模板Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 25 09:04:58 CST 2014
 */
 
@Controller
public class Sms_email_templateController extends BaseController{
	
	@Autowired
	private Sms_email_templateService sms_email_templateService;
	
	//短信模板管理列表
	@RequestMapping(value="sms_email_template/sms_template_index")
	public String sms_template_list(Sms_email_templateQueryForm sms_email_templateQueryForm,ModelMap model) throws Exception {
		sms_email_templateQueryForm.setTemp_type("0");
		PageResult pageResult = sms_email_templateService.getListByPage(sms_email_templateQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("sms_email_templateQueryForm", sms_email_templateQueryForm);
		return "sms_email_template/sms_template_index";
	}
	
	//邮件模板管理列表
	@RequestMapping(value="sms_email_template/email_template_index")
	public String email_template_list(Sms_email_templateQueryForm sms_email_templateQueryForm,ModelMap model) throws Exception {
		sms_email_templateQueryForm.setTemp_type("1");
		PageResult pageResult = sms_email_templateService.getListByPage(sms_email_templateQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("sms_email_templateQueryForm", sms_email_templateQueryForm);
		return "sms_email_template/email_template_index";
	}
	
	//进入新增
	@RequestMapping(value="sms_email_template/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Sms_email_template sms_email_template = new Sms_email_template();
		model.addAttribute("sms_email_template", sms_email_template);
		return "sms_email_template/add";
	}
	
	//新增
	@RequestMapping(value="sms_email_template/add",method=RequestMethod.POST)
	public String insert(@Valid Sms_email_template sms_email_template,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "sms_email_template/add";
		}
		sms_email_templateService.insert(sms_email_template);
		operatePrompt(rAttr, "新增模板成功");
		return "redirect:"+basePath()+"sms_email_template/index.action";
	}
	
	//进入修改
	@RequestMapping(value="sms_email_template/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Sms_email_template sms_email_template = sms_email_templateService.getByTemplate(id);
		model.addAttribute("sms_email_template", sms_email_template);
		return "sms_email_template/update";
	}
	
	//修改
	@RequestMapping(value="sms_email_template/update",method=RequestMethod.POST)
	public String update(@Valid Sms_email_template sms_email_template,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "sms_email_template/update";
		}
		sms_email_templateService.update(sms_email_template);
		operatePrompt(rAttr, "修改模板成功");
		String temp_type = sms_email_template.getTemp_type();
		if(temp_type!=null && "1".equals(temp_type))
			return "redirect:"+basePath()+"sms_email_template/email_template_index.action";
		else
			return "redirect:"+basePath()+"sms_email_template/sms_template_index.action";
	}
	
	//删除
	@RequestMapping(value="sms_email_template/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		sms_email_templateService.delete(id);
		operatePrompt(rAttr, "删除模板成功");
		return "redirect:"+basePath()+"sms_email_template/index.action";
	}
	
}

