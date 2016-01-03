package com.xingfugo.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.App_android_version;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.App_android_versionQueryForm;
import com.xingfugo.business.service.App_android_versionService;

/**
 * @function 功能 手机端版本Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 12 15:27:55 CST 2014
 */
 
@Controller
public class App_android_versionController extends BaseController{
	
	@Autowired
	private App_android_versionService app_android_versionService;
	
	//列表
	@RequestMapping(value="app_android_version/index")
	public String list(App_android_versionQueryForm app_android_versionQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = app_android_versionService.getListByPage(app_android_versionQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("app_android_versionQueryForm", app_android_versionQueryForm);
		return "app_android_version/index";
	}
	
	//进入新增
	@RequestMapping(value="app_android_version/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		App_android_version app_android_version = new App_android_version();
		model.addAttribute("app_android_version", app_android_version);
		return "app_android_version/add";
	}
	
	//新增
	@RequestMapping(value="app_android_version/add",method=RequestMethod.POST)
	public String insert(@Valid App_android_version app_android_version,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "app_android_version/add";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		app_android_version.setPublish_time(df.format(new Date()));
		app_android_versionService.insert(app_android_version);
		operatePrompt(rAttr, "新增手机端版本成功");
		return "redirect:"+basePath()+"app_android_version/index.action";
	}
	
	//进入修改
	@RequestMapping(value="app_android_version/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		App_android_version app_android_version = app_android_versionService.getByPk(id);
		model.addAttribute("app_android_version", app_android_version);
		return "app_android_version/update";
	}
	
	//修改
	@RequestMapping(value="app_android_version/update",method=RequestMethod.POST)
	public String update(@Valid App_android_version app_android_version,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "app_android_version/update";
		}
		app_android_versionService.update(app_android_version);
		operatePrompt(rAttr, "修改手机端版本成功");
		return "redirect:"+basePath()+"app_android_version/index.action";
	}
	
	//删除
	@RequestMapping(value="app_android_version/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		app_android_versionService.delete(id);
		operatePrompt(rAttr, "删除手机端版本成功");
		return "redirect:"+basePath()+"app_android_version/index.action";
	}
	
}

