package com.xingfugo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Aboutus;
import com.xingfugo.business.service.AboutusService;

//关于我们读取
@Controller
public class AboutusController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(AboutusController.class.getSimpleName());
	
	@Autowired
	private AboutusService aboutusService;
	
	//查询一条关于我们详细信息
	@RequestMapping(value="aboutus/{id}",method=RequestMethod.GET)
	public String aboutus(@PathVariable(value = "id") String id,ModelMap model){
		Aboutus aboutus = aboutusService.getByPk(id);
		model.addAttribute("aboutus", aboutus);
		return "aboutus";
	}
}
