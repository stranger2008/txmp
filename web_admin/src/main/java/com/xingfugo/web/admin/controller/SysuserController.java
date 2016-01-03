package com.xingfugo.web.admin.controller;

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

import com.xingfugo.business.module.Sysuser;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.SysuserQueryForm;
import com.xingfugo.business.service.RoleService;
import com.xingfugo.business.service.SysmenuService;
import com.xingfugo.business.service.SysuserService;
import com.xingfugo.business.common.Constants;
import com.xingfugo.util.Md5;
import com.xingfugo.web.admin.common.SessionUtil;
import com.xingfugo.web.admin.module.Login;

/**
 * @function 功能 用户Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Fri Sep 05 13:53:56 CST 2014
 */
 
@Controller
public class SysuserController extends BaseController{
	
	@Autowired
	private SysuserService sysuserService;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private SysmenuService sysmenuService;
	
	//根菜单ID
	private static final String MENU_ROOT = "1111111111";
	//管理员标识
	private static final String ADMIN_CODE = "3";
	//用户正常标识
	private static final String USER_STATE_NORMAL = "0";
	
	//欢迎页面
	@RequestMapping(value="sysuser/index",method=RequestMethod.GET)
	public String index(ModelMap model){
		return "sysuser/index";
	}
	//列表
	@RequestMapping(value="sysuser/list")
	public String list(SysuserQueryForm sysuserQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = sysuserService.getListByPage(sysuserQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("sysuserQueryForm", sysuserQueryForm);
		return "sysuser/list";
	}
	
	//进入新增
	@RequestMapping(value="sysuser/add",method=RequestMethod.GET)
	public String add(ModelMap model, RedirectAttributes rAttr) throws Exception {
		Sysuser sysuser = new Sysuser();
		sysuser.setUser_type("4");
		sysuser.setState("0");
		model.addAttribute("sysuser", sysuser);
		
		List roles = roleService.getUserRoles(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
		model.addAttribute("roles", roles);
		return "sysuser/add";
	}
	
	//新增
	@RequestMapping(value="sysuser/add",method=RequestMethod.POST)
	public String insert(@Valid Sysuser sysuser,Errors errors,ModelMap model,RedirectAttributes rAttr) throws Exception {
		if(sysuser.getUser_name() != null && !"".equals(sysuser.getUser_name())) {
			Map uMap = new HashMap();
			uMap.put("user_name", sysuser.getUser_name());
			List us = sysuserService.isUsernameExist(uMap);
			if(us != null && us.size() > 0) {
				errors.rejectValue("user_name", null, "用户名已存在");
			}
		}
		if(sysuser.getPasswd() == null || "".equals(sysuser.getPasswd().trim())) {
			errors.rejectValue("passwd", null, "密码不能为空");
		}
		if(sysuser.getRole_id() != null && sysuser.getRole_id().split(",").length > 18) {
			errors.rejectValue("role_id", null, "最多可选择18个角色");
		}
		if (errors.hasErrors()){
			
			List roles = roleService.getUserRoles(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
			model.addAttribute("roles", roles);
	        return "sysuser/add";
		}
		sysuser.setPasswd(Md5.getMD5(sysuser.getPasswd().getBytes()));
		sysuserService.insert(sysuser);
		operatePrompt(rAttr, "新增用户成功");
		return "redirect:"+basePath()+"sysuser/list.action";
	}
	
	//进入修改
	@RequestMapping(value="sysuser/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model, RedirectAttributes rAttr) throws Exception {
		Sysuser sysuser = sysuserService.getByPk(id);
		model.addAttribute("sysuser", sysuser);
		
		List roles = roleService.getUserRoles(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
		
		model.addAttribute("roles", roles);
		return "sysuser/update";
	}
	
	//修改个人信息
	@RequestMapping(value="sysuser/update-personal",method=RequestMethod.GET)
	public String updatePersonal(ModelMap model, RedirectAttributes rAttr) throws Exception {
		String id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		if(id == null || "".equals(id)) {
			return "login";
		}
		Sysuser sysuser = sysuserService.getByPk(id);
		if(sysuser == null) {
			return "login";
		}
		model.addAttribute("sysuser", sysuser);
		
		List roles = roleService.getUserRoles(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
		
		model.addAttribute("roles", roles);
		return "sysuser/update";
	}
	
	//修改
	@RequestMapping(value="sysuser/update",method=RequestMethod.POST)
	public String update(@Valid Sysuser sysuser,Errors errors,ModelMap model,RedirectAttributes rAttr) throws Exception {
		if(sysuser.getUser_id() != null && !"".equals(sysuser.getUser_id()) && sysuser.getUser_name() != null && !"".equals(sysuser.getUser_name())) {
			Map uMap = new HashMap();
			uMap.put("user_id", sysuser.getUser_id());
			uMap.put("user_name", sysuser.getUser_name());
			List us = sysuserService.isUsernameExist(uMap);
			if(us != null && us.size() > 0) {
				errors.rejectValue("user_name", null, "用户名已存在");
			}
		}
		if(sysuser.getRole_id() != null && sysuser.getRole_id().split(",").length > 18) {
			errors.rejectValue("role_id", null, "最多可选择18个角色");
		}
		if (errors.hasErrors()){
			List roles = roleService.getUserRoles(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
			
			model.addAttribute("roles", roles);
	        return "sysuser/update";
		}
		sysuserService.update(sysuser);
		operatePrompt(rAttr, "修改用户成功");
		return "redirect:"+basePath()+"sysuser/list.action";
	}
	
	//进入重置密码
	@RequestMapping(value="sysuser/update-passwd",method=RequestMethod.GET)
	public String viewPasswd(String id,ModelMap model) throws Exception {
		Sysuser sysuser = sysuserService.getByPk(id);
		model.addAttribute("sysuser", sysuser);
		
		return "sysuser/update-passwd";
	}
	
	//重置密码
	@RequestMapping(value="sysuser/update-passwd",method=RequestMethod.POST)
	public String updatePasswd(@Valid Sysuser sysuser,Errors errors,RedirectAttributes rAttr) throws Exception {
		if(sysuser.getPasswd() == null || "".equals(sysuser.getPasswd().trim())) {
			errors.rejectValue("passwd", null, "密码不能为空");
		}
		if (errors.hasErrors()){
	        return "sysuser/update-passwd";
		}
		sysuser.setPasswd(Md5.getMD5(sysuser.getPasswd().getBytes()));
		sysuserService.updateUserPasswd(sysuser);
		operatePrompt(rAttr, "重置用户密码成功");
		return "redirect:"+basePath()+"sysuser/list.action";
	}
	
	//删除
	@RequestMapping(value="sysuser/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		sysuserService.delete(id);
		operatePrompt(rAttr, "删除用户成功");
		return "redirect:"+basePath()+"sysuser/list.action";
	}
	
	//登出
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(ModelMap model){
		SessionUtil.put(getRequest(), Constants.SESSION_CUST_ID, "");
		SessionUtil.put(getRequest(), Constants.SESSION_USER_NAME, "");
		return "redirect:"+basePath()+"login.action";
	}
	
	//进入登录页面
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(ModelMap model){
		model.addAttribute("login", new Login());
		return "login";
	}
	
	//运营商登录
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String loginok(@Valid Login login,Errors errors){
		if (errors.hasErrors()){
	           return "login";
		}
		String user_name = login.getUser_name();
		String passwd = login.getPasswd();
		String check_code = login.getCheck_code();
		Map paraMap = new HashMap();
		paraMap.put("user_name", user_name);
		List sysuserList = sysuserService.getList(paraMap);
		Map retMap = new HashMap();
		if(sysuserList != null && sysuserList.size() > 0){
			retMap = (HashMap)sysuserList.get(0);
		}else{
			errors.rejectValue("user_name", "member.user_name.login_username_not_exist", "用户名不存在"); 
		}
		passwd = Md5.getMD5(passwd.getBytes());
		String _passwd = "",user_id = "";
		if(retMap.get("passwd")!=null){
			_passwd = retMap.get("passwd").toString();
		}
		if(retMap.get("user_id")!=null){
			user_id = retMap.get("user_id").toString();
		}
		if(!passwd.equals(_passwd)){
			//密码不正确
			errors.rejectValue("passwd", "user.passwd.login_passwd_wrong", "密码不正确"); 
		}
		
		String randCheckCode = "";
		if(SessionUtil.get(getRequest(), "randCheckCode") != null){
			randCheckCode = SessionUtil.get(getRequest(), "randCheckCode").toString();
		}
		if(!check_code.equalsIgnoreCase(randCheckCode)){
			errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确"); 
		}
		
		if(errors.getAllErrors() == null || errors.getAllErrors().size() == 0) {
			if(retMap.get("user_type") != null && (!ADMIN_CODE.equals(retMap.get("user_type")))) {
				if(retMap.get("state") != null && (!USER_STATE_NORMAL.equals(retMap.get("state")))) {
					errors.rejectValue("user_name", null, "该用户已被禁用"); 
				}
			}
		}
		
		if (errors.hasErrors()){
	           return "login";
		}
		
		SessionUtil.put(getRequest(), Constants.SESSION_USER_ID, user_id);
		SessionUtil.put(getRequest(), Constants.SESSION_USER_NAME, user_name);
		
		return "redirect:"+basePath()+"sysuser/index.action";
	}
	
	//无权限警告页面
	@RequestMapping(value="warning")
	public String warning(String warnInfo, ModelMap model) throws Exception {
		List oneMenuList = null;//一级菜单
		List twoMenuList = null;//二级菜单
		
		String currentUserId = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		oneMenuList = sysmenuService.getUserSysmenus(currentUserId, MENU_ROOT, "0");
		//一级菜单第一个菜单ID
		String one_menu_id = SessionUtil.getString(this.getRequest(), "one_menu_id");
		if(one_menu_id.equals("")){
			if(oneMenuList != null && oneMenuList.size() > 1) {
				one_menu_id = (String) ((Map)(oneMenuList.get(0))).get("menu_id");
			}
		}
		if(one_menu_id != null && !"".equals(one_menu_id)) {
			//二级菜单List
			twoMenuList = sysmenuService.getUserSysmenus(currentUserId, one_menu_id, "0");
		}
		
		model.put("one_menu_id", one_menu_id);
		model.put("oneMenuList", oneMenuList);
		model.put("twoMenuList", twoMenuList);
		return "inc/warning";
	}
	
}

