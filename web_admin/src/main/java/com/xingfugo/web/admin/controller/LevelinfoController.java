package com.xingfugo.web.admin.controller;

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

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Levelinfo;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.LevelinfoQueryForm;
import com.xingfugo.business.service.LevelinfoService;
import com.xingfugo.business.service.MemberlevelService;
import com.xingfugo.web.admin.common.SessionUtil;

/**
 * @function 功能 店铺级别Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 24 11:26:37 CST 2014
 */
 
@Controller
public class LevelinfoController extends BaseController{
	
	@Autowired
	private LevelinfoService levelinfoService;
	
	@Autowired
	private MemberlevelService memberlevelService;
	
	//查看店铺等级
	@RequestMapping(value="levelinfo/view")
	public String view(LevelinfoQueryForm levelinfoQueryForm,ModelMap model) throws Exception {
		Levelinfo levelinfo = new Levelinfo();
		levelinfo.setCust_id(levelinfoQueryForm.getCust_id());
		model.addAttribute("levelinfo", levelinfo);
		
		List<Map<String,Object>> memberlevels = memberlevelService.getList(null);
		model.addAttribute("memberlevels", memberlevels);
		
		PageResult pageResult = levelinfoService.getListByPage(levelinfoQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("levelinfoQueryForm", levelinfoQueryForm);
		return "levelinfo/view";
	}
	
	//新增
	@RequestMapping(value="levelinfo/add",method=RequestMethod.POST)
	public String insert(@Valid Levelinfo levelinfo,Errors errors,RedirectAttributes rAttr,ModelMap model) throws Exception {
		if (errors.hasErrors()){
			List<Map<String,Object>> memberlevels = memberlevelService.getList(null);
			model.addAttribute("memberlevels", memberlevels);
			
			LevelinfoQueryForm levelinfoQueryForm = new LevelinfoQueryForm();
			levelinfoQueryForm.setCust_id(levelinfo.getCust_id());
			PageResult pageResult = levelinfoService.getListByPage(levelinfoQueryForm);
			pageOper(model, pageResult);
			model.addAttribute("levelinfoQueryForm", levelinfoQueryForm);
			return "levelinfo/view";
		}
		levelinfo.setUser_id(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
		levelinfoService.insert(levelinfo);
		operatePrompt(rAttr, "新增店铺级别成功");
		return "redirect:"+basePath()+"levelinfo/view.action?cust_id=" + levelinfo.getCust_id();
	}
	
	//删除
	@RequestMapping(value="levelinfo/delete")
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		Levelinfo levelinfo = levelinfoService.getByPk(id);
		if(levelinfo == null) {
			operatePrompt(rAttr, "取得店铺级别信息失败,请稍后再试");
			return "redirect:"+basePath()+"levelinfo/view.action";
		}
		levelinfoService.delete(id);
		operatePrompt(rAttr, "删除店铺级别成功");
		return "redirect:"+basePath()+"levelinfo/view.action?cust_id=" + levelinfo.getCust_id();
	}
	
}

