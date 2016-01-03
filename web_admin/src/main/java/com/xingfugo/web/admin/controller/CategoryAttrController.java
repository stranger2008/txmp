package com.xingfugo.web.admin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.CategoryAttr;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CategoryAttrQueryForm;
import com.xingfugo.business.service.AttrValueService;
import com.xingfugo.business.service.CategoryAttrService;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.util.RandomStrUtil;

/**
 * @function 功能 分类属性Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Mon Sep 15 17:23:41 CST 2014
 */
 
@Controller
public class CategoryAttrController extends BaseController{
	
	//分类类型(商品)
	private static final String CATEGOEY_MODULE_TYPE = "goods";
	//分类属性类型,默认单行文本框
	private static final String CATEGORY_ATTR_TYPE_TEXT = "0";
	//分类属性是否必须,默认否
	private static final String CATEGORY_ATTR_IS_MUST = "0";
	
	@Autowired
	private CategoryAttrService categoryAttrService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AttrValueService attrValueService;
	
	//列表
	@RequestMapping(value="categoryattr/index")
	public String list(CategoryAttrQueryForm categoryattrQueryForm,ModelMap model,RedirectAttributes rAttr) throws Exception {
		//所属分类id,必须是单个id
		String catAttr = categoryattrQueryForm.getCatAttr();
		if(catAttr == null || "".equals(catAttr)) {
			operatePrompt(rAttr, "分类不存在,查询分类属性失败");
			return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index";
		}
		//所属分类
		List categorys = categoryService.getParentsByCatid(catAttr);
		if(categorys == null || categorys.size() == 0) {
			operatePrompt(rAttr, "分类不存在,查询分类属性失败");
			return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index";
		}
		
		model.put("categorys", categorys);
		
		categoryattrQueryForm.setModuleType(CATEGOEY_MODULE_TYPE);
		PageResult pageResult = categoryAttrService.getListByPage(categoryattrQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("categoryattrQueryForm", categoryattrQueryForm);
		return "categoryattr/index";
	}
	
	//进入新增
	@RequestMapping(value="categoryattr/add",method=RequestMethod.GET)
	public String add(String catAttr, ModelMap model,RedirectAttributes rAttr) throws Exception {
		if(catAttr == null || "".equals(catAttr)) {
			operatePrompt(rAttr, "分类不存在,查询分类属性失败");
			return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index.action";
		}
		//所属分类
		List categorys = categoryService.getParentsByCatid(catAttr);
		if(categorys == null || categorys.size() == 0) {
			operatePrompt(rAttr, "分类不存在,查询分类属性失败");
			return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index.action";
		}
		model.put("categorys", categorys);
		
		CategoryAttr categoryAttr = new CategoryAttr();
		categoryAttr.setAttrType(CATEGORY_ATTR_TYPE_TEXT);
		categoryAttr.setIsMust(CATEGORY_ATTR_IS_MUST);
		categoryAttr.setSortNo(0);
		categoryAttr.setCatId(catAttr);//记录所属直接分类,以返回列表参数
		model.addAttribute("categoryAttr", categoryAttr);
		return "categoryattr/add";
	}
	
	//新增
	@RequestMapping(value="categoryattr/add",method=RequestMethod.POST)
	public String insert(@Valid CategoryAttr categoryAttr ,Errors errors,RedirectAttributes rAttr, ModelMap model) throws Exception {
		String catId = categoryAttr.getCatId();
		if (errors.hasErrors()){
			if(catId == null || "".equals(catId)) {
				operatePrompt(rAttr, "分类不存在,查询分类属性失败");
				return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index.action";
			}
			///所属分类
			List categorys = categoryService.getParentsByCatid(catId);
			if(categorys == null || categorys.size() == 0) {
				operatePrompt(rAttr, "分类不存在,查询分类属性失败");
				return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index.action";
			}
			model.put("categorys", categorys);
	        return "categoryattr/add";
		}
		
		categoryAttr.setModuleType(CATEGOEY_MODULE_TYPE);
		categoryAttr.setAttrId(RandomStrUtil.getNumberRand());
		categoryAttrService.insert(categoryAttr);
		operatePrompt(rAttr, "新增分类属性成功");
		return "redirect:"+basePath()+"categoryattr/index.action?catAttr=" + catId;
	}
	
	//进入修改
	@RequestMapping(value="categoryattr/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model,RedirectAttributes rAttr) throws Exception {
		CategoryAttr categoryAttr = categoryAttrService.getByPk(id);
		//所属分类
		List categorys = categoryService.getParentsByCatid(categoryAttr.getCatId());
		if(categorys == null || categorys.size() == 0) {
			operatePrompt(rAttr, "分类不存在,查询分类属性失败");
			return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index.action";
		}
		//属性值
		Map<String, String> map = new HashMap<String, String>();
		map.put("attr_id", id);
		List attrValues = attrValueService.getList(map);
		
		model.put("categorys", categorys);
		model.put("attrValues", attrValues);
		model.addAttribute("categoryAttr", categoryAttr);
		return "categoryattr/update";
	}
	
	//修改
	@RequestMapping(value="categoryattr/update",method=RequestMethod.POST)
	public String update(@Valid CategoryAttr categoryAttr, Errors errors, RedirectAttributes rAttr, ModelMap model) throws Exception {
		String catId = categoryAttr.getCatId();
		if (errors.hasErrors()){
			if(catId == null || "".equals(catId)) {
				operatePrompt(rAttr, "分类不存在,查询分类属性失败");
				return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index.action";
			}
			///所属分类
			List categorys = categoryService.getParentsByCatid(catId);
			if(categorys == null || categorys.size() == 0) {
				operatePrompt(rAttr, "分类不存在,查询分类属性失败");
				return "redirect:"+basePath()+"category/" + CATEGOEY_MODULE_TYPE + "Index.action";
			}
			model.put("categorys", categorys);
			
			//属性值
			Map<String, String> map = new HashMap<String, String>();
			map.put("attr_id", categoryAttr.getAttrId());
			List attrValues = attrValueService.getList(map);
			
			model.put("categoryAttr", categoryAttr);
			
			model.put("attrValues", attrValues);
	        return "categoryattr/update";
		}
		categoryAttrService.update(categoryAttr);
		operatePrompt(rAttr, "修改分类属性成功");
		return "redirect:"+basePath()+"categoryattr/index.action?catAttr=" + catId;
	}
	
	//删除
	@RequestMapping(value="categoryattr/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		CategoryAttr categoryAttr = null;
		String[] ids = id.split(",");
		for(int i=0; i<ids.length; i++) {
			categoryAttr = categoryAttrService.getByPk(ids[i]);
			if(categoryAttr != null) {
				break;
			}
		}
		categoryAttrService.delete(id);
		operatePrompt(rAttr, "删除分类属性成功");
		return "redirect:"+basePath()+"categoryattr/index.action?catAttr=" + categoryAttr.getCatId();
	}
	
	/**
	 * 把分类属性添加分类名称(cate_names)
	 * @param categoryAttrs
	 * @return
	 */
	private List<Map<String, Object>> cookCategoryAttrCategory(List<Map<String, Object>> categoryAttrs) {
		Set<String> categoryIds = new HashSet<String>();
		if(categoryAttrs != null && categoryAttrs.size() > 0) {
			//遍历商品,把商品的所属分类放进列表中
			for(Map<String, Object> ca : categoryAttrs) {
				String cate_attr = (String) ca.get("catAttr");
				if(cate_attr != null && (!"".equals(cate_attr.trim()))) {
					String[] cateArr = cate_attr.trim().split(",");
					for(String cate : cateArr) {
						if(cate != null && (!"".equals(cate.trim()))) {
							categoryIds.add(cate.trim());
						}
					}
				}
			}
		} else {
			return categoryAttrs;
		}
		
		//所有分类id串
		String cIds = "";
		
		for(String cid: categoryIds) {
			cIds = cIds + "," + cid;
		}
		
		if("".equals(cIds)) {
			return categoryAttrs;
		}
		cIds = cIds.substring(1);
		
		//查询所有分类id串
		Map<String, String> categorys = categoryService.getCatMapByIds(cIds);
		if(categorys == null) {
			return categoryAttrs;
		}
		
		for(Map<String, Object> ca : categoryAttrs) {
			String cate_attr = (String) ca.get("catAttr");
			if(cate_attr != null && (!"".equals(cate_attr.trim()))) {
				String cate_names = "";
				String[] cateArr = cate_attr.trim().split(",");
				for(String cate : cateArr) {
					cate_names = cate_names + "," + categorys.get(cate);
				}
				if(!"".equals(cate_names)) {
					ca.put("catNames", cate_names.substring(1));
				}
			}
		}
		return categoryAttrs;
	}
	
}

