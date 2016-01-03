package com.xingfugo.web.client.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Interhistory;
import com.xingfugo.business.module.Memberinter;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.InterhistoryQueryForm;
import com.xingfugo.business.service.InterhistoryService;
import com.xingfugo.business.service.MemberinterService;
import com.xingfugo.web.client.common.SessionUtil;

/**
 * @function 功能 会员积分异动列表Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Nov 05 17:03:43 CST 2014
 */
 
@Controller
public class InterhistoryController extends BaseController{
	
	@Autowired
	private InterhistoryService interhistoryService;
	@Autowired
	private MemberinterService memberinterService;
	
	//列表
	@RequestMapping(value="interhistory/index")
	public String list(InterhistoryQueryForm interhistoryQueryForm,ModelMap model) throws Exception {
		String user_id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		interhistoryQueryForm.setUser_id(user_id);
		PageResult pageResult = interhistoryService.getListByPage(interhistoryQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("interhistoryQueryForm", interhistoryQueryForm);
		
		//总积分  冻结积分
		Memberinter memberinter = memberinterService.getByPk(user_id);
		model.addAttribute("memberinter", memberinter);
		
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
		return "redirect:"+basePath()+"interhistory/index.action";
	}
	
	//删除
	@RequestMapping(value="interhistory/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		interhistoryService.delete(id);
		return "redirect:"+basePath()+"interhistory/index.action";
	}
	
}

