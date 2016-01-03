package com.xingfugo.web.admin.controller;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Advinfo;
import com.xingfugo.business.module.Advpos;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.AdvinfoQueryForm;
import com.xingfugo.business.service.AdvinfoService;
import com.xingfugo.business.service.AdvposService;

/**
 * @function 功能 广告信息管理Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 10 16:29:59 CST 2014
 */
 
@Controller
public class AdvinfoController extends BaseController{
	
	@Autowired
	private AdvinfoService advinfoService;
	@Autowired
	private AdvposService advposService;
	//列表
	@RequestMapping(value="advinfo/index")
	public String list(AdvinfoQueryForm advinfoQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = advinfoService.getListByPage(advinfoQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("advinfoQueryForm", advinfoQueryForm);
		String posId = advinfoQueryForm.getPos_id();
		if(posId!=null){
			Advpos advpos = advposService.getByPk(posId);
			model.addAttribute("pos_name", advpos==null?"":advpos.getPos_name());
		}
		return "advinfo/index";
	}
	
	//进入新增
	@RequestMapping(value="advinfo/add",method=RequestMethod.GET)
	public String add(ModelMap model,String pos_id) throws Exception {
		Advinfo advinfo = new Advinfo();
		advinfo.setPos_id(pos_id);
		model.addAttribute("advinfo", advinfo);
		return "advinfo/add";
	}
	
	//新增
	@RequestMapping(value="advinfo/add",method=RequestMethod.POST)
	public String insert(@Valid Advinfo advinfo,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "advinfo/add";
		}
		Timestamp in_date = new Timestamp(System.currentTimeMillis());
		advinfo.setIn_date(in_date);
		advinfoService.insert(advinfo);
		operatePrompt(rAttr, "新增广告信息管理成功");
		return "redirect:"+basePath()+"advinfo/index.action?pos_id="+advinfo.getPos_id();
	}
	
	//进入修改
	@RequestMapping(value="advinfo/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Advinfo advinfo = advinfoService.getByPk(id);
		model.addAttribute("advinfo", advinfo);
		return "advinfo/update";
	}
	
	//修改
	@RequestMapping(value="advinfo/update",method=RequestMethod.POST)
	public String update(@Valid Advinfo advinfo,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "advinfo/update";
		}
		advinfoService.update(advinfo);
		operatePrompt(rAttr, "修改广告信息管理成功");
		return "redirect:"+basePath()+"advinfo/index.action?pos_id="+advinfo.getPos_id();
	}
	
	//删除
	@RequestMapping(value="advinfo/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		advinfoService.delete(id);
		operatePrompt(rAttr, "删除广告信息管理成功");
		return "redirect:"+basePath()+"advinfo/index.action";
	}
	
}

