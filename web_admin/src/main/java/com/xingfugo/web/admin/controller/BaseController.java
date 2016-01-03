package com.xingfugo.web.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

//所有controller父类
@Controller
public class BaseController {
	
	//获取项目根地址，如http://localhost:8080/xfg_cp_client
	public String basePath(){
		HttpServletRequest request = getRequest();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	}
	
	//获取request对象，不用在每个方法上加request参数
	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	//分页数据对象放入model
	public void pageOper(ModelMap model,PageResult pageResult){
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("pageBar", PageResultBuilder.buildPageBar(pageResult).getNumberPageBar());
	}
	
	//操作提示到前端页面
	void operatePrompt(RedirectAttributes redirectAttributes,String msg){
		redirectAttributes.addFlashAttribute("promptMessage", msg);
	}
	
	//XSS验证，包括html sql script
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//binder.registerCustomEditor(String.class, new StringEscapeEditor(true, true, true));
	}
	
}
