package com.xingfugo.web.admin.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Sysconfig;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.SysconfigQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.SysconfigService;

/**
 * @function 功能 基本设置Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Mon Sep 01 11:39:25 CST 2014
 */
 
@Controller
public class SysconfigController extends BaseController{
	
	@Autowired
	private SysconfigService sysconfigService;
	//参数实现类
	@Autowired
	private CommparaService commparaService;
	//所属组对应的参数表中的值
	private static final String GROUPCODE = "var_group";
	
	//列表
	@RequestMapping(value="sysconfig/index")
	public String list(SysconfigQueryForm sysconfigQueryForm,ModelMap model) throws Exception {
		List code = commparaService.getListByParacode(GROUPCODE);
		if(sysconfigQueryForm.getVar_group()==null){
			if(code!=null&&code.size()>0){
				Map fristMap = (Map)code.get(0);
				String fristGroup = (String)fristMap.get("para_value");
				//基本设置默认查询的所属组
				sysconfigQueryForm.setVar_group(fristGroup);
			}
		}
		PageResult pageResult = sysconfigService.getListByPage(sysconfigQueryForm);
		pageOper(model, pageResult);
		model.put("groupMap", code);
		model.addAttribute("sysconfigQueryForm", sysconfigQueryForm);
		return "sysconfig/index";
	}
	
	//进入新增
	@RequestMapping(value="sysconfig/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Sysconfig sysconfig = new Sysconfig();
		model.addAttribute("sysconfig", sysconfig);
		return "sysconfig/add";
	}
	
	//新增
	@RequestMapping(value="sysconfig/add",method=RequestMethod.POST)
	public String insert(@Valid Sysconfig sysconfig,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "sysconfig/add";
		}
		sysconfigService.insert(sysconfig);
		operatePrompt(rAttr, "新增基本设置成功");
		return "redirect:"+basePath()+"sysconfig/index.action";
	}
	
	//进入修改
	@RequestMapping(value="sysconfig/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Sysconfig sysconfig = sysconfigService.getByPk(id);
		model.addAttribute("sysconfig", sysconfig);
		return "sysconfig/update";
	}
	
	//修改
	@RequestMapping(value="sysconfig/update",method=RequestMethod.POST)
	public String update(@Valid Sysconfig sysconfig,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "sysconfig/update";
		}
		String type = sysconfig.getVar_type();
		if("1".equals(type)){
		}
		sysconfigService.update(sysconfig);
		operatePrompt(rAttr, "修改基本设置成功");
		return "redirect:"+basePath()+"sysconfig/index.action?var_group="+sysconfig.getVar_group();
	}
	
	//删除
	@RequestMapping(value="sysconfig/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		sysconfigService.delete(id);
		operatePrompt(rAttr, "删除基本设置成功");
		return "redirect:"+basePath()+"sysconfig/index.action";
	}
	
}

