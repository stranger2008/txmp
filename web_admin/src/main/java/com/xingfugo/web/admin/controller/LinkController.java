package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Link;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.LinkQueryForm;
import com.xingfugo.business.service.LinkService;

/**
 * @function 功能 友情链接Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Wed Aug 27 17:37:04 CST 2014
 */
 
@Controller
public class LinkController extends BaseController{
	
	@Autowired
	private LinkService linkService;
	
	//列表
	@RequestMapping(value="link/index")
	public String list(LinkQueryForm linkQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = linkService.getListByPage(linkQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("linkQueryForm", linkQueryForm);
		return "link/index";
	}
	
	//进入新增
	@RequestMapping(value="link/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Link link = new Link();
		link.setUrl("http://");
		link.setSort_no("0");
		link.setIs_display("0");
		model.addAttribute("link", link);
		return "link/add";
	}
	
	//新增
	@RequestMapping(value="link/add",method=RequestMethod.POST)
	public String insert(@Valid Link link,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "link/add";
		}
		linkService.insert(link);
		operatePrompt(rAttr, "新增友情链接成功");
		return "redirect:"+basePath()+"link/index.action";
	}
	
	//进入修改
	@RequestMapping(value="link/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Link link = linkService.getByPk(id);
		model.addAttribute("link", link);
		return "link/update";
	}
	
	//修改
	@RequestMapping(value="link/update",method=RequestMethod.POST)
	public String update(@Valid Link link,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "link/update";
		}
		linkService.update(link);
		operatePrompt(rAttr, "修改友情链接成功");
		return "redirect:"+basePath()+"link/index.action";
	}
	
	//删除
	@RequestMapping(value="link/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		linkService.delete(id);
		operatePrompt(rAttr, "删除友情链接成功");
		return "redirect:"+basePath()+"link/index.action";
	}
	
}

