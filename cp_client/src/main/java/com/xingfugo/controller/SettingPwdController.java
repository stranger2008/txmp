package com.xingfugo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.User;
import com.xingfugo.business.service.SmshistoryService;
import com.xingfugo.business.service.UserService;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.module.FindPwdSetting;
import com.xingfugo.module.SetNewPwd;
import com.xingfugo.util.Md5;

//个人会员
@Controller
public class SettingPwdController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(SettingPwdController.class.getSimpleName());
	//修改密码
	public static final String SESSION_UPDATE_PASSWORD = "0";
	//找回密码
	public static final String SESSION_FIND_PASSWORD = "0";
	//找回密码--手机号码
	public static final String SESSION_FINDPASSWORD_PHONE = "";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmshistoryService smshistoryService;
	

	
	//找回密码--填写找回密码信息：手机号码、验证码
	@RequestMapping(value="findPwd/index",method=RequestMethod.GET)
	public String index(HttpServletRequest request,FindPwdSetting findPwdSetting,ModelMap model){
		model.addAttribute("findPwdSetting", findPwdSetting);
		return "findPwd/index";
	}
	
	@RequestMapping(value="findPwd/index",method=RequestMethod.POST)
	public String verifyUser(HttpServletRequest request,@Valid FindPwdSetting findPwdSetting,Errors errors){
		if (errors.hasErrors()){
	           return "findPwd/index";
		}
		String cellphone =  findPwdSetting.getCellphone().trim();
		String check_code = findPwdSetting.getCheck_code().trim();
		String randCheckCode = "";
		if(SessionUtil.get(getRequest(), "randCheckCode") != null)
			randCheckCode = SessionUtil.get(getRequest(), "randCheckCode").toString();
		
		if(!check_code.equalsIgnoreCase(randCheckCode)){
			errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确");
			return "findPwd/index";
		}
		User user = userService.getUserByPhone(cellphone);
		if(user == null)
		{
			errors.rejectValue("cellphone", "user.cellphone.cellphonee_not_exist", "您输入的手机号码不存在");
			return "findPwd/index";
		}
		else
		{
			SessionUtil.putString(getRequest(),SESSION_FINDPASSWORD_PHONE,cellphone);
			SessionUtil.putString(getRequest(),SESSION_FIND_PASSWORD,"1");
		}
		return "redirect:"+basePath()+"findPwd/verifyIdentity.action";
	}
	
	//找回密码--验证身份
	@RequestMapping(value="findPwd/verifyIdentity",method=RequestMethod.GET)
	public String verifyIdentity(HttpServletRequest request,ModelMap model){
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
			return "redirect:"+basePath()+"findPwd/index.action";
		String cellphone = SessionUtil.getString(getRequest(),SESSION_FINDPASSWORD_PHONE);
		if("".equals(cellphone))
			return "redirect:"+basePath()+"findPwd/index.action";
		User user = userService.getUserByPhone(cellphone);
		if(user == null)
			return "redirect:"+basePath()+"findPwd/index.action";
		else
		{
			String user_name = user.getUser_name();
			String user_name_s = user_name;
			String cellphone_s = cellphone;
			Integer user_name_len = user_name.length();
			if(user_name_len>2)
			{
				String suser_name_f = user_name.substring(0,1);
				String user_name_e = user_name.substring(user_name_len-1,user_name_len);
				user_name_s = suser_name_f +"******" + user_name_e;
			}
			String str_f = cellphone.substring(0,3);
			String str_e = cellphone.substring(cellphone.length()-3,cellphone.length());
			cellphone_s = str_f +"*****" + str_e;
			model.addAttribute("user_name_s",user_name_s);
			model.addAttribute("cellphone_s",cellphone_s);
			model.addAttribute("cellphone",cellphone);
		}
		return "findPwd/verifyIdentity";
	}
	//验证手机验证码是否正确
	@RequestMapping(value="findPwTestPhone",method=RequestMethod.GET)
	@ResponseBody
	public String findPwTestPhone(String phone,String confirmCode) throws Exception {
		String content = "";
		String confirm_mark = "0";
		if(StringUtils.isNotBlank(phone.trim()))
		{
			content =  smshistoryService.getRegCodeByPhone(phone); //由手机号获取数据库中的验证码
			if(StringUtils.isNotBlank(confirmCode.trim()) && content !=null && content.equals(confirmCode.trim()))
			{
				confirm_mark =  "1";
			}
		}
		SessionUtil.putString(getRequest(),SESSION_FIND_PASSWORD,confirm_mark);
		return confirm_mark;
	}
	//找回密码--获取手机号码，验证码时间，用于倒计时
	@RequestMapping(value="getcellPhoneBySendDate",method=RequestMethod.GET)
	@ResponseBody
	public String getcellPhoneBySendDate(String phone) throws Exception {
		String send_date_time = "";
		if(phone != null && StringUtils.isNotBlank(phone.trim()) && !"".equals(phone.trim()))
		{
			String send_date =  smshistoryService.getPhoneBySendDate(phone);
			if(StringUtils.isNotBlank(send_date) && send_date !=null)
			{
				send_date_time =  send_date;
			}
		}
		return send_date_time;
	}
	//
	//
	//找回密码--设置新密码
	@RequestMapping(value="findPwd/setNewPwd",method=RequestMethod.GET)
	public String setNewPwd(HttpServletRequest request,SetNewPwd setNewPwd,ModelMap model){
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
		{
			return "redirect:"+basePath()+"findPwd/index.action";
		}
		model.addAttribute("setNewPwd",setNewPwd);
		return "findPwd/setNewPwd";
	}
	

	//找回密码--设置新密码
	@RequestMapping(value="findPwd/setNewPwd",method=RequestMethod.POST)
	public String setNewPwdPost(HttpServletRequest request,ModelMap model,@Valid SetNewPwd setNewPwd,Errors errors){
		if (errors.hasErrors()){
	           return "findPwd/setNewPwd";
		}
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
			return "redirect:"+basePath()+"findPwd/index.action";
		String new_password = setNewPwd.getNewPwd();
		if(StringUtils.isNotBlank(new_password))
		{
			String cellphone = SessionUtil.getString(getRequest(),SESSION_FINDPASSWORD_PHONE);
			if("".equals(cellphone))
				return "redirect:"+basePath()+"findPwd/index.action";
			User user = userService.getUserByPhone(cellphone);
			if(user == null)
				return "redirect:"+basePath()+"findPwd/index.action";
			String new_password_s = Md5.getMD5(new_password.getBytes());
			user.setPasswd(new_password_s);
			userService.updatepwd(user);
			SessionUtil.getSession(request).removeAttribute(SESSION_FINDPASSWORD_PHONE);
		}
		else
			return "redirect:"+basePath()+"findPwd/index.action";
		model.addAttribute("setNewPwd",setNewPwd);
		return "redirect:"+basePath()+"findPwd/success.action";
	}
	
	//找回密码--成功
	@RequestMapping(value="findPwd/success",method=RequestMethod.GET)
	public String findPwdSuccess(HttpServletRequest request,ModelMap model){
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
		{
			return "redirect:"+basePath()+"findPwd/index.action";
		}
		SessionUtil.getSession(request).removeAttribute(SESSION_FIND_PASSWORD);
		return "findPwd/success";
	}
	//
	//修改密码--获取手机验证码
	@RequestMapping(value="user/updatePassword",method=RequestMethod.GET)
	public String updatePwSendPhone(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		User user = userService.getMemberUserById(Integer.parseInt(user_id));
		if(user == null){
			return "redirect:"+basePath()+"login.action";
		}
		String cellphone = user.getCellphone();
		String cellphone_s ="";
		if(StringUtils.isNotBlank(cellphone) && cellphone.length()>10)
		{
			String str_f = cellphone.substring(0,3);
			String str_e = cellphone.substring(8,cellphone.length());
			cellphone_s = str_f +"*****" + str_e;
		}
		model.addAttribute("cellphone",cellphone);
		model.addAttribute("cellphone_s",cellphone_s);
		return "user/updatePassword/index";
	}
	
	//
	//修改密码--进入修改密码页面
	@RequestMapping(value="user/setNewPwd",method=RequestMethod.GET)
	public String updatePassword(HttpServletRequest request,SetNewPwd setNewPwd,ModelMap model){
		String session_update_pw = SessionUtil.getString(getRequest(),SESSION_UPDATE_PASSWORD);
		if("0".equals(session_update_pw))
		{
			return "redirect:"+basePath()+"user/updatePassword.action";
		}
		model.addAttribute("setNewPwd", setNewPwd);
		return "user/updatePassword/setNewPwd";
	}
	//
	//修改密码
	@RequestMapping(value="user/updatePW",method=RequestMethod.POST)
	public String setPassword(HttpServletRequest request,@Valid SetNewPwd setNewPwd,Errors errors) throws Exception {
		String session_update_pw = SessionUtil.getString(getRequest(),SESSION_UPDATE_PASSWORD);
		if("0".equals(session_update_pw))
			return "redirect:"+basePath()+"user/updatePassword.action";
		String old_password = setNewPwd.getOldPwd();
		String new_passwd = setNewPwd.getNewPwd();
		boolean mark = false;
		if(StringUtils.isNotBlank(old_password.trim()))
		{
			String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
			User user = userService.getMemberUserById(Integer.parseInt(user_id));
			if(user == null){
				return "redirect:"+basePath()+"login.action";
			}
		    String old_pwd = Md5.getMD5(old_password.getBytes());
			if(!old_pwd.equals(user.getPasswd()))
				errors.rejectValue("oldPwd","user.passwd.login_passwd_wrong", "旧密码不正确");
			else
				mark = true;
			if (errors.hasErrors()){
		           return "user/updatePassword/setNewPwd";
			}
			if(mark)
			{
				String new_password = Md5.getMD5(new_passwd.getBytes());
				user.setPasswd(new_password);
				userService.updatepwd(user);
			}
				
		}
		return "redirect:"+basePath()+"user/success.action";
	}
	//
	//修改密码-密码修改成功
	@RequestMapping(value="user/success",method=RequestMethod.GET)
	public String updatePasswordSuccess(HttpServletRequest request,@Valid User user,Errors errors){
		String session_update_pw = SessionUtil.getString(getRequest(),SESSION_UPDATE_PASSWORD);
		if("0".equals(session_update_pw))
		{
			return "redirect:"+basePath()+"user/updatePassword.action";
		}
		SessionUtil.putString(getRequest(),SESSION_UPDATE_PASSWORD,"0");
		return "user/updatePassword/success";
	}
	//
	//
}
