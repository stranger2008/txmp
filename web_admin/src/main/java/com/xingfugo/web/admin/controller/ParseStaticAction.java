package com.xingfugo.web.admin.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingfugo.business.module.query.AdvposQueryForm;
import com.xingfugo.business.service.SysconfigService;

@Controller
@RequestMapping("static/")
public class ParseStaticAction extends BaseController{

	@Autowired
	private SysconfigService sysconfigService;
	
	@RequestMapping(value="parseHtml")
	public String list() throws Exception {
		return "static/index";
	}
	
}
