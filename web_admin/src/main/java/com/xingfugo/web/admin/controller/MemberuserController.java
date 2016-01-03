package com.xingfugo.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Memberuser;
import com.xingfugo.business.module.Userlevelset;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.MemberuserQueryForm;
import com.xingfugo.business.service.MemberuserService;
import com.xingfugo.business.service.UserlevelsetService;
import com.xingfugo.util.Md5;

/**
 * @function 功能 会员列表Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 05 10:54:27 CST 2014
 */
 
@Controller
public class MemberuserController extends BaseController{
	//0：默认正常状态
	private static String DEFAULT_STATUS_VALUE="0";
	//新增默认会员  普通会员
	private static String DEFAULT_LEVEL = "普通会员";
	
	@Autowired
	private MemberuserService memberuserService;
	@Autowired
	private UserlevelsetService userlevelsetService;
	
	//列表
	@RequestMapping(value="memberuser/index")
	public String list(MemberuserQueryForm memberuserQueryForm,ModelMap model) throws Exception {
		
		List<String> memlist = new ArrayList<String>();
		memlist.add("");
		List<Userlevelset> userlevelset = userlevelsetService.getListall();
		for(Userlevelset userList: userlevelset){
			memlist.add(userList.getLevel_name());
		}
		
		PageResult pageResult = memberuserService.getListByPage(memberuserQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("memberuserQueryForm", memberuserQueryForm);
		model.addAttribute("memlist", memlist);
		return "memberuser/index";
	}
	
	//进入新增
	@RequestMapping(value="memberuser/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		List<String> memlist = new ArrayList<String>();
		List<Userlevelset> userlevelset = userlevelsetService.getListall();
		for(Userlevelset userList: userlevelset){
			memlist.add(userList.getLevel_name());
		}
		
		Memberuser memberuser = new Memberuser();
		memberuser.setState_code(DEFAULT_STATUS_VALUE);
		model.addAttribute("memberuser", memberuser);
		model.addAttribute("memlist", memlist);
		return "memberuser/add";
	}
	
	//新增
	@RequestMapping(value="memberuser/add",method=RequestMethod.POST)
	public String insert(@Valid Memberuser memberuser,Errors errors,RedirectAttributes rAttr,ModelMap model) throws Exception {
		List<String> memlist = new ArrayList<String>();
		List<Userlevelset> userlevelset = userlevelsetService.getListall();
		for(Userlevelset userList: userlevelset){
			memlist.add(userList.getLevel_name());
		}
		model.addAttribute("memlist", memlist);
		if (errors.hasErrors()){
	           return "memberuser/add";
		}
		
		if(memberuserService.hasExist(memberuser)){
			 errors.rejectValue("user_name",null, "该用户名已经存在");
			 return "memberuser/add";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		memberuser.setLogin_time(df.format(new Date()));
		if(memberuser.getSex()!= null && memberuser.getSex().equals("0")){
			memberuser.setSex("男");
		}else if(memberuser.getSex()!= null && memberuser.getSex().equals("1")){
			memberuser.setSex("女");
		}
		memberuser.setPasswd(Md5.getMD5(memberuser.getPasswd().getBytes()));
		memberuser.setUser_level(DEFAULT_LEVEL);
		memberuserService.insert(memberuser);
		operatePrompt(rAttr, "新增个人会员成功");
		return "redirect:"+basePath()+"memberuser/index.action";
	}

	//进入修改
	@RequestMapping(value="memberuser/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model, String user_level, String login_time) throws Exception {
		Memberuser memberuser = memberuserService.getByPk(id);
		
		List<String> memlist = new ArrayList<String>();
		List<Userlevelset> userlevelset = userlevelsetService.getListall();
		for(Userlevelset userList: userlevelset){
			memlist.add(userList.getLevel_name());
		}
		if(memberuser.getSex()!= null && memberuser.getSex().equals("男")){
			memberuser.setSex("0");
		}else if(memberuser.getSex()!= null && memberuser.getSex().equals("女")){
			memberuser.setSex("1");
		}
		memberuser.setUser_level(user_level);
		model.addAttribute("memberuser", memberuser);
		model.addAttribute("memlist", memlist);
		return "memberuser/update";
	}
	
	//修改
	@RequestMapping(value="memberuser/update",method=RequestMethod.POST)
	public String update(@Valid Memberuser memberuser,Errors errors,RedirectAttributes rAttr,ModelMap model) throws Exception {
		List<String> memlist = new ArrayList<String>();
		List<Userlevelset> userlevelset = userlevelsetService.getListall();
		for(Userlevelset userList: userlevelset){
			memlist.add(userList.getLevel_name());
		}
		model.addAttribute("memlist", memlist);
		if (errors.hasErrors()){
	           return "memberuser/update";
		}
		
		byte[] byBuffer = new byte[200];
		byBuffer = memberuser.getPasswd().getBytes();
		String kwMD5 = Md5.getMD5(byBuffer);
		memberuser.setPasswd(kwMD5);
		if(memberuser.getSex()!= null && memberuser.getSex().equals("0")){
			memberuser.setSex("男");
		}else if(memberuser.getSex()!= null && memberuser.getSex().equals("1")){
			memberuser.setSex("女");
		}
		memberuserService.update(memberuser);
		operatePrompt(rAttr, "修改个人会员成功");
		return "redirect:"+basePath()+"memberuser/index.action";
	}
	
	//进入修改密码
	@RequestMapping(value="memberuser/updatepwd",method=RequestMethod.GET)
	public String viewpwd(String id,ModelMap model) throws Exception {
		Memberuser memberuser = memberuserService.getByPkpwd(id);
		
		model.addAttribute("memberuser", memberuser);
		return "memberuser/updatepwd";
	}
	
	//修改密码
	@RequestMapping(value="memberuser/updatepwd",method=RequestMethod.POST)
	public String updatepwd(@Valid Memberuser memberuser,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "memberuser/updatepwd";
		}

		memberuser.setPasswd(Md5.getMD5(memberuser.getPasswd().getBytes()));
		memberuserService.updatepwd(memberuser);
		operatePrompt(rAttr, "修改会员密码成功");
		return "redirect:"+basePath()+"memberuser/index.action";
	}
	
	//删除
	@RequestMapping(value="memberuser/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		memberuserService.delete(id);
		operatePrompt(rAttr, "删除个人会员成功");
		return "redirect:"+basePath()+"memberuser/index.action";
	}
	
}

