package com.xingfugo.web.admin.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Sysmenu;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.SysmenuQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.SysmenuService;
import com.xingfugo.web.admin.common.SessionUtil;
import com.xingfugo.util.RandomStrUtil;

//后台菜单
@Controller
public class SysmenuController extends BaseController{
	
	@Autowired
	private SysmenuService sysmenuService;
	//数据字典
	@Autowired
	private CommparaService commparaService;
	
	private static final String PARA_CODE = "syscode";
	private static final String PARA_CODE_KEY = "para_code";
	private static final String PARA_KEY_KEY = "para_key";
	private static final String PARA_VALUE_KEY = "para_value";
	private static final String MENU_ROOT = "1111111111";
	
	//列表
	@RequestMapping(value="sysmenu/index")
	public String list(SysmenuQueryForm sysmenuQueryForm,ModelMap model) throws Exception {
		List commparas = commparaService.getListByParacode(PARA_CODE);
		if(sysmenuQueryForm.getSyscode() == null || "".equals(sysmenuQueryForm.getSyscode())) {
			if(commparas != null && commparas.size() > 0) {
				sysmenuQueryForm.setSyscode((String) (((Map<String, Object>) commparas.get(0)).get(PARA_VALUE_KEY)));
			}
		}
		if(sysmenuQueryForm.getUp_menu_id() == null || "".equals(sysmenuQueryForm.getUp_menu_id())) {
			sysmenuQueryForm.setUp_menu_id(MENU_ROOT);
		} else {
			List parentSysmenu = sysmenuService.getParentSysmenuByMenuid(sysmenuQueryForm.getUp_menu_id());
			model.addAttribute("parentSysmenu", parentSysmenu);
		}
		model.addAttribute("commparas", commparas);
		PageResult pageResult = sysmenuService.getListByPage(sysmenuQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("sysmenuQueryForm", sysmenuQueryForm);
		return "sysmenu/index";
	}
	
	//查看下级
	@RequestMapping(value="sysmenu/listchildren",method=RequestMethod.GET)
	public String listChildren(SysmenuQueryForm sysmenuQueryForm, ModelMap model) throws Exception {
		Sysmenu sysmenu = new Sysmenu();
		model.addAttribute("sysmenu", sysmenu);
		return "sysmenu/index";
	}
	
	//进入新增
	@RequestMapping(value="sysmenu/add",method=RequestMethod.GET)
	public String add(Sysmenu sysmenu, ModelMap model, RedirectAttributes rAttr) throws Exception {
		//判断是否有上级id
		if(sysmenu.getUp_menu_id() == null || "".equals(sysmenu.getUp_menu_id())) {
			operatePrompt(rAttr, "获取上级菜单失败，请重试");
			return "redirect:"+basePath()+"sysmenu/index.action";
		}
		//判断是否是根菜单
		if(MENU_ROOT.equals(sysmenu.getUp_menu_id())) {
			//判断是否有所属平台
			if(sysmenu.getSyscode() == null || "".equals(sysmenu.getSyscode())) {
				operatePrompt(rAttr, "获取所属平台失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			sysmenu.setMenu_name("无");
			sysmenu.setMenu_level("0");
		} else {
			sysmenu = sysmenuService.getByPk(sysmenu.getUp_menu_id());
			if(sysmenu == null) {
				operatePrompt(rAttr, "获取上级菜单失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			//把up_menu_id的值换成自身的id，便于后面获取
			sysmenu.setUp_menu_id(sysmenu.getMenu_id());
		}

		//获取所属平台
		Map commparaParams = new HashMap();
		commparaParams.put(PARA_CODE_KEY, PARA_CODE);
		commparaParams.put(PARA_VALUE_KEY, sysmenu.getSyscode());
		List commparas = commparaService.getList(commparaParams);
		if(commparas == null || commparas.size() != 1) {
			operatePrompt(rAttr, "获取所属平台失败，请重试");
			return "redirect:"+basePath()+"sysmenu/index.action";
		}
		
		Sysmenu menu = new Sysmenu();
		menu.setUp_menu_id(sysmenu.getUp_menu_id());
		menu.setUp_menu_name(sysmenu.getMenu_name());
		menu.setMenu_level((Integer.valueOf(sysmenu.getMenu_level()) + 1) + "");
		
		Map commparaMap = (Map) commparas.get(0);
		menu.setSyscode((String) commparaMap.get(PARA_VALUE_KEY));
		menu.setPara_key((String) commparaMap.get(PARA_KEY_KEY));
		
		menu.setSort_no("0");
		menu.setEnabled("0");
		menu.setTarget("_self");
		model.addAttribute("sysmenu", menu);
		
		return "sysmenu/add";
	}
	
	//新增
	@RequestMapping(value="sysmenu/add",method=RequestMethod.POST)
	public String insert(@Valid Sysmenu sysmenu, Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
			if(MENU_ROOT.equals(sysmenu.getUp_menu_id())) {
				sysmenu.setUp_menu_name("无");
			} else {
				if(sysmenu.getUp_menu_id() == null || "".equals(sysmenu.getUp_menu_id())) {
					operatePrompt(rAttr, "获取上级菜单失败，请重试");
					return "redirect:"+basePath()+"sysmenu/index.action";
				}
				Sysmenu pSysmenu = sysmenuService.getByPk(sysmenu.getUp_menu_id());
				if(pSysmenu == null) {
					operatePrompt(rAttr, "获取上级菜单失败，请重试");
					return "redirect:"+basePath()+"sysmenu/index.action";
				}
				sysmenu.setUp_menu_name(pSysmenu.getMenu_name());
			}
			if(sysmenu.getSyscode() == null || "".equals(sysmenu.getSyscode())) {
				operatePrompt(rAttr, "获取所属平台失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			//查询所属平台
			Map commparaParams = new HashMap();
			commparaParams.put(PARA_CODE_KEY, PARA_CODE);
			commparaParams.put(PARA_VALUE_KEY, sysmenu.getSyscode());
			List commparas = commparaService.getList(commparaParams);
			if(commparas == null || commparas.size() != 1) {
				operatePrompt(rAttr, "获取所属平台失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			Map commparaMap = (Map) commparas.get(0);
			sysmenu.setPara_key((String) commparaMap.get(PARA_KEY_KEY));
			
	        return "sysmenu/add";
		}
		sysmenu.setMenu_id(RandomStrUtil.getNumberRand());
		sysmenuService.insert(sysmenu);
		operatePrompt(rAttr, "新增菜单成功");
		
		rAttr.addAttribute("syscode", sysmenu.getSyscode());
		rAttr.addAttribute("up_menu_id", sysmenu.getUp_menu_id());
		return "redirect:"+basePath()+"sysmenu/index.action";
	}
	
	//进入修改
	@RequestMapping(value="sysmenu/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model, RedirectAttributes rAttr) throws Exception {
		Sysmenu sysmenu = sysmenuService.getByPk(id);
		
		if(MENU_ROOT.equals(sysmenu.getUp_menu_id())) {
			sysmenu.setUp_menu_name("无");
		} else {
			if(sysmenu.getUp_menu_id() == null || "".equals(sysmenu.getUp_menu_id())) {
				operatePrompt(rAttr, "获取上级菜单失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			Sysmenu pSysmenu = sysmenuService.getByPk(sysmenu.getUp_menu_id());
			if(pSysmenu == null) {
				operatePrompt(rAttr, "获取上级菜单失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			sysmenu.setUp_menu_name(pSysmenu.getMenu_name());
		}
		if(sysmenu.getSyscode() == null || "".equals(sysmenu.getSyscode())) {
			operatePrompt(rAttr, "获取所属平台失败，请重试");
			return "redirect:"+basePath()+"sysmenu/index.action";
		}
		//查询所属平台
		Map commparaParams = new HashMap();
		commparaParams.put(PARA_CODE_KEY, PARA_CODE);
		commparaParams.put(PARA_VALUE_KEY, sysmenu.getSyscode());
		List commparas = commparaService.getList(commparaParams);
		if(commparas == null || commparas.size() != 1) {
			operatePrompt(rAttr, "获取所属平台失败，请重试");
			return "redirect:"+basePath()+"sysmenu/index.action";
		}
		Map commparaMap = (Map) commparas.get(0);
		sysmenu.setPara_key((String) commparaMap.get(PARA_KEY_KEY));
		
		model.addAttribute("sysmenu", sysmenu);
		return "sysmenu/update";
	}
	
	//修改
	@RequestMapping(value="sysmenu/update",method=RequestMethod.POST)
	public String update(@Valid Sysmenu sysmenu,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
			if(MENU_ROOT.equals(sysmenu.getUp_menu_id())) {
				sysmenu.setUp_menu_name("无");
			} else {
				if(sysmenu.getUp_menu_id() == null || "".equals(sysmenu.getUp_menu_id())) {
					operatePrompt(rAttr, "获取上级菜单失败，请重试");
					return "redirect:"+basePath()+"sysmenu/index.action";
				}
				Sysmenu pSysmenu = sysmenuService.getByPk(sysmenu.getUp_menu_id());
				if(pSysmenu == null) {
					operatePrompt(rAttr, "获取上级菜单失败，请重试");
					return "redirect:"+basePath()+"sysmenu/index.action";
				}
				sysmenu.setUp_menu_name(pSysmenu.getMenu_name());
			}
			if(sysmenu.getSyscode() == null || "".equals(sysmenu.getSyscode())) {
				operatePrompt(rAttr, "获取所属平台失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			//查询所属平台
			Map commparaParams = new HashMap();
			commparaParams.put(PARA_CODE_KEY, PARA_CODE);
			commparaParams.put(PARA_VALUE_KEY, sysmenu.getSyscode());
			List commparas = commparaService.getList(commparaParams);
			if(commparas == null || commparas.size() != 1) {
				operatePrompt(rAttr, "获取所属平台失败，请重试");
				return "redirect:"+basePath()+"sysmenu/index.action";
			}
			Map commparaMap = (Map) commparas.get(0);
			sysmenu.setPara_key((String) commparaMap.get(PARA_KEY_KEY));
			
	        return "sysmenu/update";
		}
		sysmenuService.update(sysmenu);
		rAttr.addAttribute("syscode", sysmenu.getSyscode());
		rAttr.addAttribute("up_menu_id", sysmenu.getUp_menu_id());
		operatePrompt(rAttr, "修改菜单成功");
		return "redirect:"+basePath()+"sysmenu/index.action";
	}
	
	//删除
	@RequestMapping(value="sysmenu/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		if(id == null || "".equals(id)) {
			operatePrompt(rAttr, "错误请求");
			return "redirect:"+basePath()+"sysmenu/index.action";
		}
		Sysmenu sysmenu = null;
		String[] ids = id.split(",");
		for(int i=0; i<ids.length; i++) {
			sysmenu = sysmenuService.getByPk(ids[i]);
			if(sysmenu != null) {
				break;
			}
		}
		
		if(sysmenu == null) {
			operatePrompt(rAttr, "菜单不存在");
			return "redirect:"+basePath()+"sysmenu/index.action";
		}
		
		rAttr.addAttribute("syscode", sysmenu.getSyscode());
		rAttr.addAttribute("up_menu_id", sysmenu.getUp_menu_id());
		sysmenuService.deleteSysmenuWithChildren(id);
		operatePrompt(rAttr, "删除菜单成功");
		return "redirect:"+basePath()+"sysmenu/index.action";
	}
	
	/**
	 * 商家后台，点击一级菜单显示二级菜单
	 */
	@RequestMapping(value="public/getdownmenu",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getdownmenu(String up_menu_id) throws Exception {
		SessionUtil.putString(getRequest(),"one_menu_id",up_menu_id);
		String user_id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		List menuList = this.sysmenuService.getUserSysmenus(user_id, up_menu_id, "0");
		JSONArray jsonArray = JSONArray.fromObject(menuList);
		return jsonArray.toString();
	}
}
