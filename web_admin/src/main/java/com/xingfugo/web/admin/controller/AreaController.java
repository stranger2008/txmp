package com.xingfugo.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Area;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.AreaQueryForm;
import com.xingfugo.business.service.AreaService;
import com.xingfugo.util.RandomStrUtil;

/**
 * @function 功能 地区管理Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Tue Sep 02 14:10:46 CST 2014
 */
 
@Controller
public class AreaController extends BaseController{
	
	//0：地区管理 是否为城市默认值
	public static final String IS_CITY = "0";
	//0：地区管理 是否开通子站默认值
	public static final String IS_OPEN = "1";
	
	//9999999999：地区管理  国家
	public static final String UP_AREA_ID = "9999999999";
	//默认父级导航显示
	public static final String DEFAULT_UP_AREA_NAME = "国家";
	//默认父级别
	public static final String DEFAULT_UP_AREA_LEVEL = "0";
	
	public String up_area_id;
	/*
	 * 地区信息集合
	 */
	public List areaList;
	
	@Autowired
	private AreaService areaService;
	
	//页面进入显示列表
	@RequestMapping(value="area/index")
	public String list(AreaQueryForm areaQueryForm,ModelMap model) throws Exception {
		if(areaQueryForm.getUp_area_id() == null){
			areaQueryForm.setUp_area_id(UP_AREA_ID);
		}
		//列表分类导航显示
		List<Area> parentareaList = new ArrayList<Area>();
		//默认一级分类对象
		Area area = new Area();
		area.setArea_name(DEFAULT_UP_AREA_NAME);
		area.setArea_level(DEFAULT_UP_AREA_LEVEL);
		area.setArea_id(UP_AREA_ID);
		parentareaList.add(area);
		if(areaQueryForm.getIs_open() != null ){
			if(!areaQueryForm.getIs_open().equals("")){				
				areaQueryForm.setUp_area_id("");
			}else{
				if(areaQueryForm.getUp_area_id().equals("")){
					areaQueryForm.setUp_area_id(UP_AREA_ID);
				}else{
					areaQueryForm.setUp_area_id(areaQueryForm.getUp_area_id());
					}
				}
			}
		else{
			areaQueryForm.setUp_area_id(areaQueryForm.getUp_area_id());
		}
		areaService.setAreaTreeByCatid(parentareaList,areaQueryForm.getUp_area_id() );
		PageResult pageResult = areaService.getListByPage(areaQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("areaQueryForm", areaQueryForm);
		if(parentareaList.size() > 1){			
			model.put("parentareaList", parentareaList);
		}
		return "area/index";
	}
	
	//进入新增
	@RequestMapping(value="area/add",method=RequestMethod.GET)
	public String add(ModelMap model, String up_area_id) throws Exception {
		Area area = new Area();
		//area.setIs_city(IS_CITY);
		area.setIs_open(IS_OPEN);
		area.setUp_area_id(up_area_id);
		
		//获取父级分类
		Area parent_area = areaService.getByPk(up_area_id);
		//设置分类级别
		Integer level = (parent_area==null||parent_area.getArea_level()==null) ? 1 : Integer.parseInt(parent_area.getArea_level());
		area.setArea_level(level.toString());
		String parent_area_name = parent_area==null?"":parent_area.getArea_name();
		if(parent_area_name.equals("")){
			parent_area_name="无";
		}
		if(parent_area == null){
			area.setIs_city("2");
		}else if(parent_area.getArea_level().equals("1")){
			area.setIs_city("1");
		}else{
			area.setIs_city("0");
		}
		model.addAttribute("parentAreaName", parent_area_name);
		
		model.addAttribute("area", area);
		return "area/add";
	}
	
	//新增
	@RequestMapping(value="area/add",method=RequestMethod.POST)
	public String insert(@Valid Area area,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "area/add";
		}
		String id = RandomStrUtil.getNumberRand();
		area.setArea_id(id);
		areaService.insert(area);
		operatePrompt(rAttr, "新增地区管理成功");
		return "redirect:"+basePath()+"area/addindex.action?up_area_id=" + area.getUp_area_id();
	}
	
	//点击新增时 列表显示
	@RequestMapping(value="area/addindex")
	public String addlist(AreaQueryForm areaQueryForm,ModelMap model, String up_area_id) throws Exception {
		if(areaQueryForm.getUp_area_id() == null){
			areaQueryForm.setUp_area_id(up_area_id);
		}
		//列表分类导航显示
		List<Area> parentareaList = new ArrayList<Area>();
		//默认一级分类对象
		Area area = new Area();
		area.setArea_name(DEFAULT_UP_AREA_NAME);
		area.setArea_level(DEFAULT_UP_AREA_LEVEL);
		//area.setArea_id(up_area_id);
		parentareaList.add(area);
		areaService.setAreaTreeByCatid(parentareaList,areaQueryForm.getUp_area_id() );
		PageResult pageResult = areaService.getListByPage(areaQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("areaQueryForm", areaQueryForm);
		model.put("parentareaList", parentareaList);
		return "area/index";
	}
	
	//进入修改
	@RequestMapping(value="area/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Area area = areaService.getByPk(id);
		Area area1 = areaService.getByPk(area.getUp_area_id());
		String parent_area_name = "";
		if(area1 == null){
			parent_area_name="无";
		}else{
			parent_area_name = area1.getArea_name();
		}
		model.addAttribute("parent_area_name",parent_area_name);
		model.addAttribute("area", area);
		return "area/update";
	}
	
	//修改
	@RequestMapping(value="area/update",method=RequestMethod.POST)
	public String update(@Valid Area area,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "area/update";
		}
		areaService.update(area);
		operatePrompt(rAttr, "修改地区管理成功");
		return "redirect:"+basePath()+"area/addindex.action?up_area_id=" + area.getUp_area_id();
	}
	
	//删除
	@RequestMapping(value="area/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
//		areaService.delete(id);
		operatePrompt(rAttr, "删除地区管理成功");
		Recursive(id);
		String parent_id = this.getRequest().getParameter("up_area_id");
		return "redirect:"+basePath()+"area/addindex.action?up_area_id=" + parent_id;
	}
	
	/**
	 *  方法描述 创建一个递归方法用于批量删除
	 */
	public boolean Recursive(String id) {
		String area_id = "";
		Map map = new HashMap();
		map.put("up_area_id", id);
		// 通过对传进来的ID进行查询
		areaList = areaService.getList(map);
		if(areaList.size() > 0){
			
		}
		// 删除传进来的ID行
		areaService.delete(id);
		// 若存在有子ID
		if (areaList.size() > 0) {
			Map upmap  = new HashMap();
			for (int i = 0; i < areaList.size(); i++) {
				upmap  = (HashMap) areaList.get(i);
				// 如果通过上一级获取到本级的ID不为空，则进入递归继续查找并删除
				if (upmap.get("area_id") != null && !upmap.get("area_id").equals("")) {
					area_id = upmap.get("area_id").toString();
					// 进入递归循环
					Recursive(area_id);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public String getUp_area_id() {
		return up_area_id;
	}

	public void setUp_area_id(String up_area_id) {
		this.up_area_id = up_area_id;
	}
}

