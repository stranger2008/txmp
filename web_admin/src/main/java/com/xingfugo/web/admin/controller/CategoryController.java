package com.xingfugo.web.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Category;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CategoryQueryForm;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.util.RandomStrUtil;
import com.xingfugo.web.admin.common.ConstantUtil;

/**
 * @function 功能 实体分类Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Tue Sep 02 14:07:15 CST 2014
 */
 
@Controller
public class CategoryController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	
	//默认进入列表显示第一级分类
	private static final String DEFAULT_SHOW_LEVEL="1";
	//默认一级分类的父级Id
	private static final String FIRST_LEVEL_PARENT_ID="1111111111";
	 
	//默认显示模块
	private static final String DEFAULT_MODULE_TYPE="goods";
	private static final int SHOW = 0;
	private static final int HIDE = 1;
	 
	
	//列表
	@RequestMapping(value="category/{module_type}Index")
	public String list(CategoryQueryForm categoryQueryForm,ModelMap model) throws Exception {
		//设置默认父级节点查询条件
		String up_cat_id = categoryQueryForm.getUp_cat_id();
		if(up_cat_id == null){
			categoryQueryForm.setCat_level(DEFAULT_SHOW_LEVEL);
			categoryQueryForm.setUp_cat_id(FIRST_LEVEL_PARENT_ID);
			up_cat_id = FIRST_LEVEL_PARENT_ID;
		}
		String type = categoryQueryForm.getModule_type();
		if(type == null){
			categoryQueryForm.setModule_type(DEFAULT_MODULE_TYPE);
			type = DEFAULT_MODULE_TYPE;
		}
		//列表分类导航变量（存储所有父级分类）
		List<Category> parentCatList = new ArrayList<Category>();
		
		categoryService.setCatTreeByCatid(parentCatList,up_cat_id );
		if(parentCatList.size()>0){
			setFirstCat(parentCatList, type);
		}
		PageResult pageResult = categoryService.getListByPage(categoryQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("categoryQueryForm", categoryQueryForm);
		model.put("parentCatList", parentCatList);
		String typeName = ConstantUtil.moduleMap.get(type);
		model.put("typeName", typeName);
		return "category/index";
	}
	//设置默认一级分类对象
	private void setFirstCat(List<Category> catList , String type){
		Category first = new Category();
		//默认一级分类导航名称
		String typeName = ConstantUtil.moduleMap.get(type);
		typeName = typeName==null?"":typeName;
		first.setCat_name(typeName+"分类");
		first.setCat_level("0");
		first.setCat_id(FIRST_LEVEL_PARENT_ID);
		catList.add(0, first);
	}
	//进入新增
	@RequestMapping(value="category/{module_type}Add",method=RequestMethod.GET)
	public String add(ModelMap model , @PathVariable(value="module_type") String module_type) throws Exception {
		String up_cat_id = this.getRequest().getParameter("up_cat_id");
		
		if(up_cat_id==null||module_type==null){
			return "redirect:"+basePath()+"category/"+module_type+"Index.action";
		}
		Category category = new Category();
		category.setUp_cat_id(up_cat_id);
		category.setModule_type(module_type);
		//获取父级分类
		Category parent_cat = categoryService.getByPk(up_cat_id);
		//设置分类级别
		int level = (parent_cat==null||parent_cat.getCat_level()==null) ? 0 : Integer.parseInt(parent_cat.getCat_level());
		level = level+1;
		category.setCat_level(level+"");
		
		model.addAttribute("category", category);
		//上级分类名称
		String parent_cat_name = parent_cat==null?"无":parent_cat.getCat_name();
		model.addAttribute("parentCatName", parent_cat_name);
		String typeName =  ConstantUtil.moduleMap.get(module_type);
		model.addAttribute("typeName", typeName);
		return "category/add";
	}
	
	//新增
	@RequestMapping(value="category/{module_type}Add",method=RequestMethod.POST)
	public String insert(@Valid Category category,Errors errors,RedirectAttributes rAttr ,ModelMap model) throws Exception {
		//获取父级分类
		String upId = category==null||category.getUp_cat_id()==null?FIRST_LEVEL_PARENT_ID : category.getUp_cat_id();
		Category parent_cat = categoryService.getByPk(upId);
		//上级分类名称
		String parent_cat_name = parent_cat==null?"无":parent_cat.getCat_name();
		String typeName =  ConstantUtil.moduleMap.get(category.getModule_type());
		if (errors.hasErrors()){
			model.addAttribute("parentCatName", parent_cat_name);
			model.addAttribute("typeName", typeName);
			return "category/add";
		}
		String cat_id = RandomStrUtil.getNumberRand();
		category.setCat_id(cat_id);
		if(categoryService.hasExist(category)){
			model.addAttribute("parentCatName", parent_cat_name);
			model.addAttribute("typeName", typeName);
			errors.rejectValue("en_name",null, "该英文名称已经存在");
			return "category/add";
		}
		categoryService.insert(category);
		operatePrompt(rAttr, "新增实体分类成功");
		String url = basePath()+"category/"+category.getModule_type()+"Index.action?up_cat_id="+category.getUp_cat_id() ;
		return "redirect:" + url;
	}
	
	//进入修改
	@RequestMapping(value="category/{module_type}Update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Category category = categoryService.getByPk(id);
		model.addAttribute("category", category);
		//获取父级分类
		Category parent_cat = categoryService.getByPk(category.getUp_cat_id());
		//上级分类名称
		String parent_cat_name = parent_cat==null?"无":parent_cat.getCat_name();
		model.addAttribute("parentCatName", parent_cat_name);
		String typeName =  ConstantUtil.moduleMap.get(category.getModule_type());
		model.addAttribute("typeName", typeName);
		return "category/update";
	}
	
	//修改
	@RequestMapping(value="category/{module_type}Update",method=RequestMethod.POST)
	public String update(@Valid Category category,Errors errors,RedirectAttributes rAttr, ModelMap model) throws Exception {
		//获取父级分类
		String upId = category==null||category.getUp_cat_id()==null?FIRST_LEVEL_PARENT_ID : category.getUp_cat_id();
		Category parent_cat = categoryService.getByPk(upId);
		//上级分类名称
		String parent_cat_name = parent_cat==null?"无":parent_cat.getCat_name();
		String typeName =  ConstantUtil.moduleMap.get(category.getModule_type());
		if (errors.hasErrors()){
			model.addAttribute("parentCatName", parent_cat_name);
			model.addAttribute("typeName", typeName);
	        return "category/update";
		}
		if(categoryService.hasExist(category)){
			model.addAttribute("parentCatName", parent_cat_name);
			model.addAttribute("typeName", typeName);
			errors.rejectValue("en_name",null, "该英文名称已经存在");
			return "category/update";
		}
		categoryService.update(category);
		operatePrompt(rAttr, "修改实体分类成功");
		String type = category.getModule_type();
		String url = basePath()+"category/" + type + "Index.action?up_cat_id=" + category.getUp_cat_id();
		return "redirect:" + url;
	}
	
	//删除
	@RequestMapping(value="category/{module_type}Delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr,@PathVariable(value="module_type") String module_type) throws Exception {
		if(id==null||id.length()<1){
			return "redirect:"+basePath()+"category/"+module_type+"Index.action";
		}
		String[] ids = id.split(",");
		List<String> idList = new ArrayList<String>();
		for(String one: ids ){
			idList.add(one);
		}
		delChildren(idList);
		categoryService.delete(id);
		operatePrompt(rAttr, "删除实体分类成功");
		String parent_id = this.getRequest().getParameter("up_cat_id");
		return "redirect:"+basePath()+"category/"+module_type+"Index.action?up_cat_id="+parent_id;
	}
	//删除子分类
	private void delChildren(List<String> parentIds){
		if(parentIds==null||parentIds.isEmpty()){
			return;
		}
		for(String id : parentIds){
			 List<String> childIds = categoryService.getCategoryByParentId(id);
			 if(childIds!=null&& childIds.size()>0){
				 delChildren(childIds);
				 String idStr = childIds.toString();
				 idStr = idStr.substring(1,idStr.length()-1);
				 categoryService.delete(idStr);
			 }
		 }
	};
	
	
	/**
	 * 批量修改是否显示的状态
	 * @param id
	 * @param rAttr
	 * @param module_type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="category/{module_type}DisplayStatus",method=RequestMethod.POST)
	public String updDisplayStatus(String id,RedirectAttributes rAttr,@PathVariable(value="module_type") String module_type) throws Exception {
		if(id==null||id.length()<1){
			return "redirect:"+basePath()+"category/"+module_type+"Index.action";
		}
		String isDisplay = getRequest().getParameter("isDisplay");
		Map info = new HashMap();
		if(isDisplay!=null&&"show".equals(isDisplay)){
			//设置为显示
			info.put("is_display", SHOW);
		}else{
			//设置为不显示
			info.put("is_display", HIDE);
			
		}
		 
		info.put("cat_id", id);
		categoryService.updateWhetherDisplay(info);
		operatePrompt(rAttr, "分类显示状态修改成功");
		String parent_id = this.getRequest().getParameter("up_cat_id");
		return "redirect:"+basePath()+"category/"+module_type+"Index.action?up_cat_id="+parent_id;
	}
	
	/**
	 * 取得分类列表(ajax)
	 * @param categoryQueryForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="category/getcategorys",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCategorys(CategoryQueryForm categoryQueryForm) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("module_type", categoryQueryForm.getModule_type());
		map.put("up_cat_id", categoryQueryForm.getUp_cat_id());
		map.put("is_display", SHOW+"" );
		List categorys = this.categoryService.getList(map);
		JSONArray jsonArray = JSONArray.fromObject(categorys);
		return jsonArray.toString();
	}
	 
}

