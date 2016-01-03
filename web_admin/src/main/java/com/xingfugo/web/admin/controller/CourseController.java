package com.xingfugo.web.admin.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Course;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CourseQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.CourseService;
import com.xingfugo.web.admin.common.SessionUtil;

/**
 * @function 功能 课程管理Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Sat Apr 04 15:02:05 CST 2015
 */
 
@Controller
public class CourseController extends BaseController{
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CommparaService commparaService;
	
	//类型
		private static final String COMP_TYPE = "course_type";
	
	//列表
	@RequestMapping(value="course/index")
	public String list(CourseQueryForm courseQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = courseService.getListByPage(courseQueryForm);
		
		Map courseTypeMap = this.commparaService.getSelectMapByParacode(true,COMP_TYPE);
		
		List list = pageResult.getList();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Map map = (Map)itr.next();
			String c_type = "";
			if(map.get("c_type") != null){
				c_type = map.get("c_type").toString();
			}
			if(StringUtils.isNotBlank(c_type) && courseTypeMap.get(c_type) != null){
				map.put("c_type_name", courseTypeMap.get(c_type).toString());
			}
		}
		
		pageOper(model, pageResult);
		
		//类型
		
		model.addAttribute("courseTypeMap", courseTypeMap);
		
		model.addAttribute("courseQueryForm", courseQueryForm);
		return "course/index";
	}
	
	//进入新增
	@RequestMapping(value="course/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Course course = new Course();
		//类型
		Map courseTypeMap = this.commparaService.getSelectMapByParacode(true,COMP_TYPE);
		model.addAttribute("courseTypeMap", courseTypeMap);
		model.addAttribute("course", course);
		return "course/add";
	}
	
	//新增
	@RequestMapping(value="course/add",method=RequestMethod.POST)
	public String insert(@Valid Course course,Errors errors,RedirectAttributes rAttr,HttpServletRequest request) throws Exception {
		if (errors.hasErrors()){
	           return "course/add";
		}
		String cust_id = SessionUtil.getString(request,Constants.SESSION_CUST_ID);
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(cust_id)){
			cust_id = "-1";
		}
		course.setCust_id(cust_id);
		course.setUser_id(user_id);
		courseService.insert(course);
		operatePrompt(rAttr, "新增课程管理成功");
		return "redirect:"+basePath()+"course/index.action";
	}
	
	//进入修改
	@RequestMapping(value="course/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Course course = courseService.getByPk(id);
		//类型
		Map courseTypeMap = this.commparaService.getSelectMapByParacode(true,COMP_TYPE);
		model.addAttribute("courseTypeMap", courseTypeMap);
		model.addAttribute("course", course);
		return "course/update";
	}
	
	//修改
	@RequestMapping(value="course/update",method=RequestMethod.POST)
	public String update(@Valid Course course,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "course/update";
		}
		courseService.update(course);
		operatePrompt(rAttr, "修改课程管理成功");
		return "redirect:"+basePath()+"course/index.action";
	}
	
	//删除
	@RequestMapping(value="course/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		courseService.delete(id);
		operatePrompt(rAttr, "删除课程管理成功");
		return "redirect:"+basePath()+"course/index.action";
	}
	
}

