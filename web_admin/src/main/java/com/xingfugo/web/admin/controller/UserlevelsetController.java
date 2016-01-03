package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Userlevelset;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.UserlevelsetQueryForm;
import com.xingfugo.business.service.UserlevelsetService;

/**
 * @function 功能 会员等级Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 05 15:54:11 CST 2014
 */
 
@Controller
public class UserlevelsetController extends BaseController{
	
	@Autowired
	private UserlevelsetService userlevelsetService;
	
	//列表
	@RequestMapping(value="userlevelset/index")
	public String list(UserlevelsetQueryForm userlevelsetQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = userlevelsetService.getListByPage(userlevelsetQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("userlevelsetQueryForm", userlevelsetQueryForm);
		return "userlevelset/index";
	}
	
	//进入新增
	@RequestMapping(value="userlevelset/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Userlevelset userlevelset = new Userlevelset();
		model.addAttribute("userlevelset", userlevelset);
		return "userlevelset/add";
	}
	
	//新增
	@RequestMapping(value="userlevelset/add",method=RequestMethod.POST)
	public String insert(@Valid Userlevelset userlevelset,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "userlevelset/add";
		}
		userlevelsetService.insert(userlevelset);
		operatePrompt(rAttr, "新增会员等级成功");
		return "redirect:"+basePath()+"userlevelset/index.action";
	}
	
	//进入修改
	@RequestMapping(value="userlevelset/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Userlevelset userlevelset = userlevelsetService.getedititem(id);
		model.addAttribute("userlevelset", userlevelset);
		return "userlevelset/update";
	}
	
	//修改
	@RequestMapping(value="userlevelset/update",method=RequestMethod.POST)
	public String update(@Valid Userlevelset userlevelset,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "userlevelset/update";
		}
		userlevelsetService.update(userlevelset);
		operatePrompt(rAttr, "修改会员等级成功");
		return "redirect:"+basePath()+"userlevelset/index.action";
	}
	
	//删除
	@RequestMapping(value="userlevelset/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		userlevelsetService.delete(id);
		operatePrompt(rAttr, "删除会员等级成功");
		return "redirect:"+basePath()+"userlevelset/index.action";
	}
	
}

