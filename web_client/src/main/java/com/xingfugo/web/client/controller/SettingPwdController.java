package com.xingfugo.web.client.controller;

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
import com.xingfugo.util.Md5;
import com.xingfugo.web.client.common.SessionUtil;
import com.xingfugo.web.client.module.FindPwd;
import com.xingfugo.web.client.module.Updatepasswd;

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
	
	//修改密码--获取手机验证码
	@RequestMapping(value="user/updatePwSendPhone",method=RequestMethod.GET)
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
		model.addAttribute("cellphone_s",cellphone_s);
		model.addAttribute("user",user);
		return "user/updatePassword/updatePwSendPhone";
	}
	
	//修改密码--验证手机验证码
	@RequestMapping(value="user/updatePwTestPhone",method=RequestMethod.GET)
	@ResponseBody
	public String updatePwTestPhone(String phone,String confirmCode) throws Exception {
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
		SessionUtil.putString(getRequest(),SESSION_UPDATE_PASSWORD,confirm_mark);
		return confirm_mark;
	}
	//
	//修改密码--进入修改密码页面
	@RequestMapping(value="user/updatePassword",method=RequestMethod.GET)
	public String updatePassword(HttpServletRequest request,ModelMap model){
		String session_update_pw = SessionUtil.getString(getRequest(),SESSION_UPDATE_PASSWORD);
		if("0".equals(session_update_pw))
		{
			return "redirect:"+basePath()+"user/updatePwSendPhone.action";
		}
		return "user/updatePassword/updatePassword";
	}
	//
	//修改密码
	@RequestMapping(value="user/updatePW",method=RequestMethod.POST)
	public String updatePW(HttpServletRequest request,@Valid Updatepasswd updatepasswd,Errors errors) throws Exception {
		String session_update_pw = SessionUtil.getString(getRequest(),SESSION_UPDATE_PASSWORD);
		if("0".equals(session_update_pw))
		{
			return "redirect:"+basePath()+"user/updatePwSendPhone.action";
		}
		String old_password = updatepasswd.getOld_passwd();
		String new_passwd = updatepasswd.getNew_passwd();
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
				errors.rejectValue("old_passwd","user.passwd.login_passwd_wrong", "旧密码不正确");
			else
				mark = true;
			if (errors.hasErrors()){
		           return "user/updatePassword/updatePassword";
			}
			if(mark)
			{
				String new_password = Md5.getMD5(new_passwd.getBytes());
				user.setPasswd(new_password);
				userService.updatepwd(user);
			}
				
		}
		return "redirect:"+basePath()+"user/updateSuccess.action";
	}
	//
	//修改密码-密码修改成功
	@RequestMapping(value="user/updateSuccess",method=RequestMethod.GET)
	public String updatePasswordSuccess(HttpServletRequest request,@Valid User user,Errors errors){
		String session_update_pw = SessionUtil.getString(getRequest(),SESSION_UPDATE_PASSWORD);
		if("0".equals(session_update_pw))
		{
			return "redirect:"+basePath()+"user/updatePwSendPhone.action";
		}
		SessionUtil.putString(getRequest(),SESSION_UPDATE_PASSWORD,"0");
		return "user/updatePassword/updateSuccess";
	}
	//修改密码--获取手机号码，验证码时间，用于倒计时
	@RequestMapping(value="getPhoneBySendDate",method=RequestMethod.GET)
	@ResponseBody
	public String getPhoneBySendDate(String phone) throws Exception {
		String send_date_time = "";
		if(phone != null && StringUtils.isNotBlank(phone.trim()))
		{
			String send_date =  smshistoryService.getPhoneBySendDate(phone);
			if(StringUtils.isNotBlank(send_date) && send_date !=null)
			{
				send_date_time =  String.valueOf(Integer.valueOf(send_date)+1);
			}
		}
		return send_date_time;
	}
	//修改密码--获取手机号码，验证码时间，用于倒计时
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
	//找回密码--填写找回密码信息：手机号码、验证码
	@RequestMapping(value="findPwd/verifyUser",method=RequestMethod.GET)
	public String verifyUser(HttpServletRequest request,FindPwd findPwd,ModelMap model){
		model.addAttribute("findPwd", findPwd);
		return "findPwd/verifyUser";
	}
	//找回密码--查找手机号码是否存在
	@RequestMapping(value="findPwd/verifyByPhone",method=RequestMethod.POST)
	public String verifyByPhone(HttpServletRequest request,@Valid FindPwd findPwd,Errors errors) throws Exception {
		if (errors.hasErrors()){
	           return "findPwd/verifyUser";
		}
		String cellphone =  findPwd.getCellphone().trim();
		String check_code = findPwd.getCheck_code().trim();
		if(StringUtils.isNotBlank(cellphone) && StringUtils.isNotBlank(check_code))
		{
			String randCheckCode = "";
			if(SessionUtil.get(getRequest(), "randCheckCode") != null)
				randCheckCode = SessionUtil.get(getRequest(), "randCheckCode").toString();
			
			if(!check_code.equalsIgnoreCase(randCheckCode)){
				errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确");
				return "findPwd/verifyUser";
			}
			User user = userService.getUserByPhone(cellphone);
			if(user == null)
			{
				errors.rejectValue("cellphone", "user.cellphone.cellphonee_not_exist", "您输入的手机号码不存在");
				return "findPwd/verifyUser";
			}
			else
			{
				SessionUtil.putString(getRequest(),SESSION_FINDPASSWORD_PHONE,cellphone);
				SessionUtil.putString(getRequest(),SESSION_FIND_PASSWORD,"1");
			}
		}
		return "redirect:"+basePath()+"findPwd/verifyIdentity.action";
	}
	//找回密码--验证身份
	@RequestMapping(value="findPwd/verifyIdentity",method=RequestMethod.GET)
	public String verifyIdentity(HttpServletRequest request,ModelMap model){
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
		{
			return "redirect:"+basePath()+"findPwd/verifyUser.action";
		}
		String cellphone = SessionUtil.getString(getRequest(),SESSION_FINDPASSWORD_PHONE);
		if("".equals(cellphone))
		{
			return "redirect:"+basePath()+"findPwd/verifyUser.action";
		}
		User user = userService.getUserByPhone(cellphone);
		if(user == null)
		{
			return "redirect:"+basePath()+"findPwd/verifyUser.action";
		}
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
	//找回密码--验证手机验证码
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
	//
	//找回密码--设置新密码
	@RequestMapping(value="findPwd/setNewPwd",method=RequestMethod.GET)
	public String setNewPwd(HttpServletRequest request,Updatepasswd updatepasswd,ModelMap model){
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
		{
			return "redirect:"+basePath()+"findPwd/verifyUser.action";
		}
		model.addAttribute("updatepasswd",updatepasswd);
		return "findPwd/setNewPwd";
	}
	

	//找回密码--设置新密码
	@RequestMapping(value="findPwd/setNewPwdPost",method=RequestMethod.POST)
	public String setNewPwdPost(HttpServletRequest request,ModelMap model,@Valid Updatepasswd updatepasswd,Errors errors){
		if (errors.hasErrors()){
	           return "findPwd/setNewPwd";
		}
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
		{
			return "redirect:"+basePath()+"findPwd/verifyUser.action";
		}
		String new_password = updatepasswd.getOld_passwd().trim();
		if(StringUtils.isNotBlank(new_password))
		{
			String cellphone = SessionUtil.getString(getRequest(),SESSION_FINDPASSWORD_PHONE);
			if("".equals(cellphone))
			{
				return "redirect:"+basePath()+"findPwd/verifyUser.action";
			}
			User user = userService.getUserByPhone(cellphone);
			if(user == null)
			{
				return "redirect:"+basePath()+"findPwd/verifyUser.action";
			}
			String new_password_s = Md5.getMD5(new_password.getBytes());
			user.setPasswd(new_password_s);
			userService.updatepwd(user);
			SessionUtil.getSession(request).removeAttribute(SESSION_FINDPASSWORD_PHONE);
		}
		else
			return "redirect:"+basePath()+"findPwd/verifyUser.action";
		
		return "redirect:"+basePath()+"findPwd/findPwdSuccess.action";
	}
	//找回密码--成功
	@RequestMapping(value="findPwd/findPwdSuccess",method=RequestMethod.GET)
	public String findPwdSuccess(HttpServletRequest request,ModelMap model){
		String session_find_pwssword = SessionUtil.getString(getRequest(),SESSION_FIND_PASSWORD);
		if("".equals(session_find_pwssword) || "0".equals(session_find_pwssword))
		{
			return "redirect:"+basePath()+"findPwd/verifyUser.action";
		}
		SessionUtil.getSession(request).removeAttribute(SESSION_FIND_PASSWORD);
		return "findPwd/findPwdSuccess";
	}
	//
}
