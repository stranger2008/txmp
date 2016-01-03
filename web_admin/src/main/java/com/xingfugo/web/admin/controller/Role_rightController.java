package com.xingfugo.web.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Role_right;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.Role_rightQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.Role_rightService;
import com.xingfugo.business.service.SysmenuService;
import com.xingfugo.util.RandomStrUtil;

/**
 * @function 功能 权限Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Thu Sep 04 16:42:37 CST 2014
 */
 
@Controller
public class Role_rightController extends BaseController{
	
	@Autowired
	private Role_rightService role_rightService;
	//数据字典
	@Autowired
	private CommparaService commparaService;
	@Autowired
	private SysmenuService sysmenuService;
	
	private static final String PARA_CODE = "syscode";
	private static final String SYSCODE = "adm";
	private static final String PARA_VALUE_KEY = "para_value";
	
	//列表
	@RequestMapping(value="role_right/index")
	public String list(Role_rightQueryForm role_rightQueryForm,ModelMap model) throws Exception {
		role_rightQueryForm.setSyscode(SYSCODE);
		PageResult pageResult = role_rightService.getListByPage(role_rightQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("role_rightQueryForm", role_rightQueryForm);
		return "role_right/index";
	}
	
	//进入新增
	@RequestMapping(value="role_right/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		//所属后台
//		List commparas = commparaService.getListByParacode(PARA_CODE);
//		model.addAttribute("commparas", commparas);
//		
//		if(commparas != null && commparas.size() > 0) {
//			//所属菜单
//			List sysmenus = sysmenuService.getSysmenuListBySyscode((String) (((Map) commparas.get(0)).get(PARA_VALUE_KEY)));
//			model.addAttribute("sysmenus", sysmenus);
//		}
		
		List sysmenus = sysmenuService.getSysmenuListBySyscode(SYSCODE);
		model.addAttribute("sysmenus", sysmenus);
		
		Role_right role_right = new Role_right();
		role_right.setSyscode(SYSCODE);//默认为'商城后台'编码
		model.addAttribute("role_right", role_right);
		return "role_right/add";
	}
	
	//新增
	@RequestMapping(value="role_right/add",method=RequestMethod.POST)
	public String insert(@Valid Role_right role_right,Errors errors,ModelMap model,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
			//所属后台
//			List commparas = commparaService.getListByParacode(PARA_CODE);
//			model.addAttribute("commparas", commparas);
			
			//所属菜单
			String menu_right = role_right.getMenu_attr(); 
			List<Map<String, Object>> sysmenus = sysmenuService.getSysmenuListBySyscode(role_right.getSyscode());
			if(menu_right != null && (!"".equals(menu_right)) && sysmenus != null && sysmenus.size() > 0) {
				String[] rightArr = menu_right.split(",");
				List<String> rights = Arrays.asList(rightArr);
				rights = new ArrayList(rights);
				for(Map<String, Object> sysmenu : sysmenus) {
					if(rights.size() == 0) {
						break;
					}
					for(int i=0; i<rights.size(); i++){
						String right = rights.get(i);
						if(right.equals(sysmenu.get("menu_id").toString())){
							sysmenu.put("has_right", true);
							rights.remove(i);
							break;
						}
					}
				}
			}
			model.addAttribute("sysmenus", sysmenus);
			return "role_right/add";
		}
		role_right.setRight_id(RandomStrUtil.getNumberRand());
		role_rightService.insert(role_right);
		operatePrompt(rAttr, "新增权限成功");
		return "redirect:"+basePath()+"role_right/index.action";
	}
	
	//进入修改
	@RequestMapping(value="role_right/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model, RedirectAttributes rAttr) throws Exception {
		//所属后台
//		List commparas = commparaService.getListByParacode(PARA_CODE);
//		model.addAttribute("commparas", commparas);
		
		Role_right role_right = role_rightService.getByPk(id);
		if(role_right == null){
			operatePrompt(rAttr, "加载权限信息失败,请稍后再试");
			return "redirect:"+basePath()+"role_right/index.action";
		}
		
		//所属菜单
		String menu_right = role_right.getMenu_attr(); 
		List<Map<String, Object>> sysmenus = sysmenuService.getSysmenuListBySyscode(role_right.getSyscode());
		if(menu_right != null && (!"".equals(menu_right)) && sysmenus != null && sysmenus.size() > 0) {
			String[] rightArr = menu_right.split(",");
			List<String> rights = Arrays.asList(rightArr);
			rights = new ArrayList(rights);
			for(Map<String, Object> sysmenu : sysmenus) {
				if(rights.size() == 0) {
					break;
				}
				for(int i=0; i<rights.size(); i++){
					String right = rights.get(i);
					if(right.equals(sysmenu.get("menu_id").toString())){
						sysmenu.put("has_right", true);
						rights.remove(i);
						break;
					}
				}
			}
		}
		model.addAttribute("sysmenus", sysmenus);
		model.addAttribute("role_right", role_right);
		return "role_right/update";
	}
	
	//修改
	@RequestMapping(value="role_right/update",method=RequestMethod.POST)
	public String update(@Valid Role_right role_right,Errors errors,ModelMap model,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
			//所属后台
//			List commparas = commparaService.getListByParacode(PARA_CODE);
//			model.addAttribute("commparas", commparas);
			
			//所属菜单
			String menu_right = role_right.getMenu_attr(); 
			List<Map<String, Object>> sysmenus = sysmenuService.getSysmenuListBySyscode(role_right.getSyscode());
			if(menu_right != null && (!"".equals(menu_right)) && sysmenus != null && sysmenus.size() > 0) {
				String[] rightArr = menu_right.split(",");
				List<String> rights = Arrays.asList(rightArr);
				rights = new ArrayList(rights);
				for(Map<String, Object> sysmenu : sysmenus) {
					if(rights.size() == 0) {
						break;
					}
					for(int i=0; i<rights.size(); i++){
						String right = rights.get(i);
						if(right.equals(sysmenu.get("menu_id").toString())){
							sysmenu.put("has_right", true);
							rights.remove(i);
							break;
						}
					}
				}
			}
			model.addAttribute("sysmenus", sysmenus);
	        return "role_right/update";
		}
		role_rightService.update(role_right);
		operatePrompt(rAttr, "修改权限成功");
		return "redirect:"+basePath()+"role_right/index.action";
	}
	
	//删除
	@RequestMapping(value="role_right/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		role_rightService.delete(id);
		operatePrompt(rAttr, "删除权限成功");
		return "redirect:"+basePath()+"role_right/index.action";
	}
	

	
	@RequestMapping(value="role_right/getsysmenubysyscode", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getSysmenuBySyscode(String syscode, String right_id) throws Exception {
		List<Map<String, Object>> sysmenus = sysmenuService.getSysmenuListBySyscode(syscode);
		
		if(right_id != null && (!"".equals(right_id))) {
			Role_right role_right = role_rightService.getByPk(right_id);
			
			String menu_attr = role_right.getMenu_attr();
			if(menu_attr != null && (!"".equals(menu_attr)) && sysmenus != null && sysmenus.size() > 0) {
				String[] rightArr = menu_attr.split(",");
				List<String> rights = Arrays.asList(rightArr);
				rights = new ArrayList(rights);
				for(Map<String, Object> sysmenu : sysmenus) {
					if(rights.size() == 0) {
						break;
					}
					for(int i=0; i<rights.size(); i++){
						String right = rights.get(i);
						if(right.equals(sysmenu.get("menu_id").toString())){
							sysmenu.put("has_right", true);
							rights.remove(i);
							break;
						}
					}
				}
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(sysmenus);
		return jsonArray.toString();
	}
	
}

