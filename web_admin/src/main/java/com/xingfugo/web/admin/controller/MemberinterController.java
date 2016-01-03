package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Memberinter;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.MemberinterQueryForm;
import com.xingfugo.business.service.MemberinterService;

/**
 * @function 功能 会员积分Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 15:59:56 CST 2014
 */
 
@Controller
public class MemberinterController extends BaseController{
	
	@Autowired
	private MemberinterService memberinterService;
	
	//列表
	@RequestMapping(value="memberinter/index")
	public String list(MemberinterQueryForm memberinterQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = memberinterService.getListByPage(memberinterQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("memberinterQueryForm", memberinterQueryForm);
		return "memberinter/index";
	}
	
	//进入新增
	@RequestMapping(value="memberinter/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Memberinter memberinter = new Memberinter();
		model.addAttribute("memberinter", memberinter);
		return "memberinter/add";
	}
	
	//新增
	@RequestMapping(value="memberinter/add",method=RequestMethod.POST)
	public String insert(@Valid Memberinter memberinter,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "memberinter/add";
		}
		memberinterService.insert(memberinter);
		operatePrompt(rAttr, "新增会员积分成功");
		return "redirect:"+basePath()+"memberinter/index.action";
	}
	
	//进入修改
	@RequestMapping(value="memberinter/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Memberinter memberinter = memberinterService.getByPk(id);
		model.addAttribute("memberinter", memberinter);
		return "memberinter/update";
	}
	
	//修改
	@RequestMapping(value="memberinter/update",method=RequestMethod.POST)
	public String update(@Valid Memberinter memberinter,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "memberinter/update";
		}
		memberinterService.update(memberinter);
		operatePrompt(rAttr, "修改会员积分成功");
		return "redirect:"+basePath()+"memberinter/index.action";
	}
	
	//删除
	@RequestMapping(value="memberinter/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		memberinterService.delete(id);
		operatePrompt(rAttr, "删除会员积分成功");
		return "redirect:"+basePath()+"memberinter/index.action";
	}
	
}

