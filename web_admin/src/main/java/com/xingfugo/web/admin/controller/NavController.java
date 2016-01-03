package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Nav;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.NavQueryForm;
import com.xingfugo.business.service.NavService;

/**
 * @function 功能 导航Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Thu Aug 28 13:10:44 CST 2014
 */
 
@Controller
public class NavController extends BaseController{
	
	@Autowired
	private NavService navService;
	
	//列表
	@RequestMapping(value="nav/index")
	public String list(NavQueryForm navQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = navService.getListByPage(navQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("navQueryForm", navQueryForm);
		return "nav/index";
	}
	
	//进入新增
	@RequestMapping(value="nav/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Nav nav = new Nav();
		nav.setSort_no("0");
		nav.setIsshow("0");
		nav.setIsopen("_self");
		nav.setLink_url("http://");
		model.addAttribute("nav", nav);
		return "nav/add";
	}
	
	//新增
	@RequestMapping(value="nav/add",method=RequestMethod.POST)
	public String insert(@Valid Nav nav,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "nav/add";
		}
		navService.insert(nav);
		operatePrompt(rAttr, "新增导航成功");
		return "redirect:"+basePath()+"nav/index.action";
	}
	
	//进入修改
	@RequestMapping(value="nav/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Nav nav = navService.getByPk(id);
		model.addAttribute("nav", nav);
		return "nav/update";
	}
	
	//修改
	@RequestMapping(value="nav/update",method=RequestMethod.POST)
	public String update(@Valid Nav nav,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "nav/update";
		}
		navService.update(nav);
		operatePrompt(rAttr, "修改导航成功");
		return "redirect:"+basePath()+"nav/index.action";
	}
	
	//删除
	@RequestMapping(value="nav/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		navService.delete(id);
		operatePrompt(rAttr, "删除导航成功");
		return "redirect:"+basePath()+"nav/index.action";
	}
	
}

