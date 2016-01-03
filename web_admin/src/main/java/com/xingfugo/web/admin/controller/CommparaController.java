package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Commpara;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CommparaQueryForm;
import com.xingfugo.business.service.CommparaService;

/**
 * @function 功能 系统数据字典Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Mon Sep 01 11:34:06 CST 2014
 */
 
@Controller
public class CommparaController extends BaseController{
	
	//0：数据字典 有效参数默认值
	public static final String VALUE_NUM = "0";
	
	@Autowired
	private CommparaService commparaService;
	
	//列表
	@RequestMapping(value="commpara/index")
	public String list(CommparaQueryForm commparaQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = commparaService.getListByPage(commparaQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("commparaQueryForm", commparaQueryForm);
		return "commpara/index";
	}
	
	//进入新增
	@RequestMapping(value="commpara/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Commpara commpara = new Commpara();
		commpara.setEnabled("0");
		model.addAttribute("commpara", commpara);
		return "commpara/add";
	}
	
	//新增
	@RequestMapping(value="commpara/add",method=RequestMethod.POST)
	public String insert(@Valid Commpara commpara,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "commpara/add";
		}
		commparaService.insert(commpara);
		operatePrompt(rAttr, "新增友情链接成功");
		return "redirect:"+basePath()+"commpara/index.action";
	}
	
	//进入修改
	@RequestMapping(value="commpara/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Commpara commpara = commparaService.getByPk(id);
		model.addAttribute("commpara", commpara);
		return "commpara/update";
	}
	
	//修改
	@RequestMapping(value="commpara/update",method=RequestMethod.POST)
	public String update(@Valid Commpara commpara,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "commpara/update";
		}
		commparaService.update(commpara);
		operatePrompt(rAttr, "修改友情链接成功");
		return "redirect:"+basePath()+"commpara/index.action";
	}
	
	//删除
	@RequestMapping(value="commpara/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		commparaService.delete(id);
		operatePrompt(rAttr, "删除友情链接成功");
		return "redirect:"+basePath()+"commpara/index.action";
	}
	
}

