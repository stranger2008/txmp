package com.xingfugo.web.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.common.Constants;

//商品分类
@Controller
public class CategoryController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(CategoryController.class.getSimpleName());
	
	@Autowired
	private CategoryService categoryService;
	
	//取商品分类，id为空则取出所有一级分类，否则取出以id值为父级的子级分类
	@RequestMapping(value="category",method=RequestMethod.GET)
	public String getCategory(String id,ModelMap model){
		
		if(id == null || id.equals("")){
			id = Constants.UP_INFO_ID;
		}
		
		List catList = categoryService.getCatWithDownCatByUpCatId(id);
		model.addAttribute("catList", catList);
		
		return "category";
	}
}
