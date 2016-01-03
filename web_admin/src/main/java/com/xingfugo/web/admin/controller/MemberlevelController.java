package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Memberlevel;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.MemberlevelQueryForm;
import com.xingfugo.business.service.MemberlevelService;

/**
 * @function 功能 等级配置Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 24 10:36:12 CST 2014
 */
 
@Controller
public class MemberlevelController extends BaseController{
	
	@Autowired
	private MemberlevelService memberlevelService;
	
	//列表
	@RequestMapping(value="memberlevel/index")
	public String list(MemberlevelQueryForm memberlevelQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = memberlevelService.getListByPage(memberlevelQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("memberlevelQueryForm", memberlevelQueryForm);
		return "memberlevel/index";
	}
	
	//进入修改
	@RequestMapping(value="memberlevel/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model,RedirectAttributes rAttr) throws Exception {
		Memberlevel memberlevel = memberlevelService.getByPk(id);
		if(memberlevel==null) {
			operatePrompt(rAttr, "获取等级配置信息失败");
			return "redirect:"+basePath()+"memberlevel/index.action";
		}
		model.addAttribute("memberlevel", memberlevel);
		return "memberlevel/update";
	}
	
	//修改
	@RequestMapping(value="memberlevel/update",method=RequestMethod.POST)
	public String update(@Valid Memberlevel memberlevel,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "memberlevel/update";
		}
		memberlevelService.update(memberlevel);
		operatePrompt(rAttr, "修改等级配置成功");
		return "redirect:"+basePath()+"memberlevel/index.action";
	}
	
}

