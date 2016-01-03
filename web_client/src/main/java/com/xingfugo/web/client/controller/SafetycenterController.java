package com.xingfugo.web.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Memberuser;
import com.xingfugo.business.service.MemberuserService;
import com.xingfugo.business.service.SmshistoryService;
import com.xingfugo.web.client.common.SessionUtil;

//触屏版首页
@Controller
public class SafetycenterController extends BaseController{

	private final static String SESSION_AERA_ID = "session_area_id";
	
	@Autowired
	private MemberuserService memberuserService;
	@Autowired
	private SmshistoryService smshistoryService;
	
	
	//安全中心页面
	@RequestMapping(value="/safetycenter/index",method=RequestMethod.GET)
	public String safetycenter(ModelMap model,HttpServletResponse response){
		
		// 获取当前用户id
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		Memberuser mem = memberuserService.getByPk(user_id);
		// 获取之前绑定手机
		String cellnum = mem.getCellphone();
		String first = cellnum.substring(0, 3);
		String last = cellnum.substring(8, 11);
		String cellphone = first+"*****"+last;
		
		model.addAttribute("cellphone", cellphone);
		return "safetycenter/index";
	}
	//绑定手机 点击修改  进入身份验证显示页面
	@RequestMapping(value="/safetycenter/bindmobile",method=RequestMethod.GET)
	public String bindmobile(ModelMap model,HttpServletResponse response){
			
		// 获取当前用户id
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		Memberuser mem = memberuserService.getByPk(user_id);
		// 获取之前绑定手机
		String cellnum = mem.getCellphone();
		String first = cellnum.substring(0, 3);
		String last = cellnum.substring(8, 11);
		String cellphone = first+"*****"+last;
		
		model.addAttribute("cellnum", cellnum);
		model.addAttribute("cellphone", cellphone);
		
		return "safetycenter/bindmobile";
	}
	//绑定手机   身份验证显示页面
	@RequestMapping(value="/safetycenter/bindmobile2",method=RequestMethod.GET)
	@ResponseBody
	public String bindmobile2(ModelMap model,HttpServletResponse response,String code){
			
		// 获取当前用户id
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		Memberuser mem = memberuserService.getByPk(user_id);
		String cellnum = mem.getCellphone();
		
		// 获取验证码  判断验证码是够正确
		String dbSmdCode = smshistoryService.getRegCodeByPhone(cellnum);
		if(dbSmdCode != null && dbSmdCode.equals(code)){
			return "1";
		}else{			
			return "0";
		}
	}
	//绑定手机   进入修改绑定手机页面
	@RequestMapping(value="/safetycenter/bindmobile3",method=RequestMethod.GET)
	public String bindmobile3(ModelMap model,HttpServletResponse response){
		// 获取当前用户id
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		Memberuser mem = memberuserService.getByPk(user_id);
		// 获取之前绑定手机
		String cellnum = mem.getCellphone();
		model.addAttribute("cellnum", cellnum);
		// 返回修改绑定手机页面			
		return "safetycenter/bindmobile2";
	}
	//绑定手机   显示修改绑定手机页面
	@RequestMapping(value="/safetycenter/bindmobile4",method=RequestMethod.GET)
	@ResponseBody
	public String bindmobile4(ModelMap model,HttpServletResponse response,String code,String cellphone){
			
		// 获取当前用户id
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		Memberuser mem = memberuserService.getByPk(user_id);
		// 获取新手机验证码  并判断验证码是否正确
		String dbSmdCode = smshistoryService.getRegCodeByPhone(cellphone);
		if(dbSmdCode != null && dbSmdCode.equals(code)){
			// 判断输入手机是否相同
			if(mem.getCellphone().equals(cellphone)){
				return "2";
			}
			mem.setCellphone(cellphone);
			memberuserService.update(mem);
			return "1";
		}else{			
			return "0";
		}
	}
	//绑定手机   绑定手机完成页面
	@RequestMapping(value="/safetycenter/bindmobile6",method=RequestMethod.GET)
	@ResponseBody
	public String bindmobile6(ModelMap model,HttpServletResponse response,String phone){
		List list = memberuserService.hasPhonenumber(phone);
		if(list.size() > 0){
			return "0";
		}			
		return "1";
	}
	//绑定手机   绑定手机完成页面
	@RequestMapping(value="/safetycenter/bindmobile5",method=RequestMethod.GET)
	public String bindmobile5(ModelMap model,HttpServletResponse response){
					
		return "safetycenter/bindmobile3";
	}
	
}
