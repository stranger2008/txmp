package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Interhistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.InterhistoryQueryForm;
import com.xingfugo.business.service.InterhistoryService;

/**
 * @function 功能 会员积分异动列表Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 17:03:43 CST 2014
 */
 
@Controller
public class InterhistoryController extends BaseController{
	
	@Autowired
	private InterhistoryService interhistoryService;
	
	//列表
	@RequestMapping(value="interhistory/index")
	public String list(String user_id,InterhistoryQueryForm interhistoryQueryForm,ModelMap model) throws Exception {
		interhistoryQueryForm.setUser_id(user_id);
		PageResult pageResult = interhistoryService.getListByPage(interhistoryQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("interhistoryQueryForm", interhistoryQueryForm);
		return "interhistory/index";
	}
	
	//进入新增
	@RequestMapping(value="interhistory/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Interhistory interhistory = new Interhistory();
		model.addAttribute("interhistory", interhistory);
		return "interhistory/add";
	}
	
	//新增
	@RequestMapping(value="interhistory/add",method=RequestMethod.POST)
	public String insert(@Valid Interhistory interhistory,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "interhistory/add";
		}
		interhistoryService.insert(interhistory);
		operatePrompt(rAttr, "新增会员积分异动列表成功");
		return "redirect:"+basePath()+"interhistory/index.action";
	}
	
	//进入修改
	@RequestMapping(value="interhistory/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Interhistory interhistory = interhistoryService.getByPk(id);
		model.addAttribute("interhistory", interhistory);
		return "interhistory/update";
	}
	
	//修改
	@RequestMapping(value="interhistory/update",method=RequestMethod.POST)
	public String update(@Valid Interhistory interhistory,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "interhistory/update";
		}
		interhistoryService.update(interhistory);
		operatePrompt(rAttr, "修改会员积分异动列表成功");
		return "redirect:"+basePath()+"interhistory/index.action";
	}
	
	//删除
	@RequestMapping(value="interhistory/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		interhistoryService.delete(id);
		operatePrompt(rAttr, "删除会员积分异动列表成功");
		return "redirect:"+basePath()+"interhistory/index.action";
	}
	
}

