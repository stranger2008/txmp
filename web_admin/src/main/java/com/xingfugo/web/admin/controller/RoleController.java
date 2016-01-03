package com.xingfugo.web.admin.controller;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Role;
import com.xingfugo.business.module.Sysuser;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.RoleQueryForm;
import com.xingfugo.business.service.RoleService;
import com.xingfugo.business.service.Role_rightService;
import com.xingfugo.business.service.SysmenuService;
import com.xingfugo.business.service.SysuserService;
import com.xingfugo.web.admin.common.SessionUtil;

/**
 * @function 功能 角色管理Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Tue Sep 02 15:21:30 CST 2014
 */
 
@Controller
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SysmenuService sysmenuService;
	
	@Autowired
	private Role_rightService role_rightService;
	
	//客户标识默认为0
	private static final String CUST_ID = "0";
	
	//列表
	@RequestMapping(value="role/index")
	public String list(RoleQueryForm roleQueryForm,ModelMap model) throws Exception {
		roleQueryForm.setCust_id(CUST_ID);
		PageResult pageResult = roleService.getListByPage(roleQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("roleQueryForm", roleQueryForm);
		return "role/index";
	}
	
	//进入新增
	@RequestMapping(value="role/add",method=RequestMethod.GET)
	public String add(ModelMap model, RedirectAttributes rAttr) throws Exception {

		//查询用户有权限的菜单和操作
		List sysmenuAdmin = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"adm");
		
		List sysmenuStatic = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"sth");
		
		model.addAttribute("sysmenuAdmin", sysmenuAdmin);
		model.addAttribute("sysmenuStatic", sysmenuStatic);
		
		Role role = new Role();
		model.addAttribute("role", role);
		return "role/add";
	}
	
	//新增
	@RequestMapping(value="role/add",method=RequestMethod.POST)
	public String insert(@Valid Role role,Errors errors, ModelMap model, RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
			//查询用户有权限的菜单和操作
			List sysmenuAdmin = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"adm");
			
			List sysmenuStatic = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"sth");
			
			
			String menu_right = role.getMenu_right();//菜单权限
			String oper_right = role.getOper_right();//操作权限
			
			sysmenuAdmin = this.initSysmenuAndOperRights(sysmenuAdmin, menu_right, oper_right);
			sysmenuStatic = this.initSysmenuAndOperRights(sysmenuStatic, menu_right, oper_right);
			
			model.addAttribute("sysmenuAdmin", sysmenuAdmin);
			model.addAttribute("sysmenuStatic", sysmenuStatic);
	        return "role/add";
		}
		role.setCust_id(CUST_ID);
		roleService.insert(role);
		operatePrompt(rAttr, "新增角色管理成功");
		return "redirect:"+basePath()+"role/index.action";
	}
	
	//进入修改
	@RequestMapping(value="role/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model, RedirectAttributes rAttr) throws Exception {
		Role role = roleService.getByPk(id);
		if(role == null){
			operatePrompt(rAttr, "加载角色信息失败,请稍后再试");
			return "redirect:"+basePath()+"role/index.action";
		}
		
		//查询用户有权限的菜单和操作
		List sysmenuAdmin = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"adm");
		
		List sysmenuStatic = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"sth");
		
		String menu_right = role.getMenu_right();//菜单权限
		String oper_right = role.getOper_right();//操作权限
		
		sysmenuAdmin = this.initSysmenuAndOperRights(sysmenuAdmin, menu_right, oper_right);
		
		sysmenuStatic = this.initSysmenuAndOperRights(sysmenuStatic, menu_right, oper_right);
		
		model.addAttribute("role", role);
		model.addAttribute("sysmenuAdmin", sysmenuAdmin);
		model.addAttribute("sysmenuStatic", sysmenuStatic);
		return "role/update";
	}
	
	//修改
	@RequestMapping(value="role/update",method=RequestMethod.POST)
	public String update(@Valid Role role,Errors errors, ModelMap model, RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
			
			//查询用户有权限的菜单和操作
			List sysmenuAdmin = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"adm");
			List sysmenuStatic = this.getUserSysmenusAndRoleRights(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID),"sth");
			
			String menu_right = role.getMenu_right();//菜单权限
			String oper_right = role.getOper_right();//操作权限
			
			sysmenuAdmin = this.initSysmenuAndOperRights(sysmenuAdmin, menu_right, oper_right);
			
			sysmenuStatic = this.initSysmenuAndOperRights(sysmenuStatic, menu_right, oper_right);
			model.addAttribute("sysmenuAdmin", sysmenuAdmin);
			model.addAttribute("sysmenuStatic", sysmenuStatic);
	        return "role/update";
		}
		role.setCust_id(CUST_ID);
		roleService.update(role);
		operatePrompt(rAttr, "修改角色管理成功");
		return "redirect:"+basePath()+"role/index.action";
	}
	
	//删除
	@RequestMapping(value="role/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		roleService.delete(id);
		operatePrompt(rAttr, "删除角色管理成功");
		return "redirect:"+basePath()+"role/index.action";
	}
	
	/**
	 * 取得用户有权限的菜单和操作(菜单和操作对应)
	 * @param user_id
	 * @return
	 */
	private List getUserSysmenusAndRoleRights(String user_id,String syscode) {
		List sysmenus = sysmenuService.getUserSysmenus(user_id, null, null,syscode);
		if(sysmenus == null || sysmenus.size() == 0) {
			return new ArrayList();
		}
		List role_rights = role_rightService.getUserRole_rights(user_id, null);
		if(role_rights == null || role_rights.size() == 0) {
			return sysmenus;
		}
		//菜单对应的操作列表,Key=role_rights
		for(Map sysmenu : (List<Map>) sysmenus) {
			Object mrrs = sysmenu.get("role_rights");
			if(mrrs == null) {
				List mrrList = new ArrayList();
				sysmenu.put("role_rights", mrrList);
			}
			for(Map role_right : (List<Map>) role_rights) {
				if(sysmenu.get("menu_id").equals(role_right.get("menu_attr"))) {
					((List) (sysmenu.get("role_rights"))).add(role_right);
				}
			}
		}
		return sysmenus;
	}
	
	/**
	 * 给已经赋予权限(菜单权限、操作权限)的数据添加has_right:true标识
	 * @param sysmenus 菜单列表
	 * @param menu_right 菜单权限串
	 * @param oper_right 操作权限串
	 * @return
	 */
	private List<Map<String, Object>> initSysmenuAndOperRights(List<Map<String, Object>> sysmenus, String menu_right, String oper_right) {
		List<String> menu_rights = null;
		if(menu_right != null && (!"".equals(menu_right))) {
			String[] rightArr = menu_right.split(",");
			menu_rights = Arrays.asList(rightArr);
			menu_rights = new ArrayList(menu_rights);
		}
		
		List<String> oper_rights = null;
		if(oper_right != null && (!"".equals(oper_right))) {
			String[] rightArr = oper_right.split(",");
			oper_rights = Arrays.asList(rightArr);
			oper_rights = new ArrayList(oper_rights);
		}
		
		if(sysmenus != null && sysmenus.size() > 0) {
			for(Map<String, Object> sysmenu : (List<Map<String, Object>>)sysmenus) {
				//判断是否有此菜单的权限
				if(menu_rights != null && menu_rights.size() > 0) {
					for(int i=0; i<menu_rights.size(); i++){
						String right = menu_rights.get(i);
						if(right.equals((String)sysmenu.get("menu_id"))){
							sysmenu.put("has_right", true);
							menu_rights.remove(i);
							break;
						}
					}
				}
				//根据菜单,取得菜单的操作权限,并判断是否已经赋予角色(has_right)
				List role_rights = (List)sysmenu.get("role_rights");
				if(role_rights != null && role_rights.size() > 0) {
					for(Map role_right : (List<Map>) role_rights) {
						if(oper_rights == null || oper_rights.size() == 0) {
							break;
						}
						for(int i=0; i<oper_rights.size(); i++) {
							String right = oper_rights.get(i);
							if(right.equals((String)role_right.get("right_id"))) {
								role_right.put("has_right", true);
								oper_rights.remove(i);
								break;
							}
						}
					}
				}
				sysmenu.put("role_rights", role_rights);
			}
		}
		return sysmenus;
	}
	
}

