package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Course_video;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.Course_videoQueryForm;
import com.xingfugo.business.service.Course_videoService;

/**
 * @function 功能 课程视频管理Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Sat Apr 04 16:10:38 CST 2015
 */
 
@Controller
public class Course_videoController extends BaseController{
	
	@Autowired
	private Course_videoService course_videoService;
	
	//列表
	@RequestMapping(value="course_video/index")
	public String list(Course_videoQueryForm course_videoQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = course_videoService.getListByPage(course_videoQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("course_videoQueryForm", course_videoQueryForm);
		return "course_video/index";
	}
	
	//进入新增
	@RequestMapping(value="course_video/add",method=RequestMethod.GET)
	public String add(ModelMap model,Course_video course_video) throws Exception {
		if(course_video == null){
			course_video = new Course_video();
		}
		model.addAttribute("course_video", course_video);
		return "course_video/add";
	}
	
	//新增
	@RequestMapping(value="course_video/add",method=RequestMethod.POST)
	public String insert(@Valid Course_video course_video,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "course_video/add";
		}
		course_videoService.insert(course_video);
		operatePrompt(rAttr, "新增课程视频管理成功");
		return "redirect:"+basePath()+"course_video/index.action?c_id="+course_video.getC_id();
	}
	
	//进入修改
	@RequestMapping(value="course_video/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Course_video course_video = course_videoService.getByPk(id);
		model.addAttribute("course_video", course_video);
		return "course_video/update";
	}
	
	//修改
	@RequestMapping(value="course_video/update",method=RequestMethod.POST)
	public String update(@Valid Course_video course_video,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "course_video/update";
		}
		course_videoService.update(course_video);
		operatePrompt(rAttr, "修改课程视频管理成功");
		return "redirect:"+basePath()+"course_video/index.action";
	}
	
	//删除
	@RequestMapping(value="course_video/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		course_videoService.delete(id);
		operatePrompt(rAttr, "删除课程视频管理成功");
		return "redirect:"+basePath()+"course_video/index.action";
	}
	
}

