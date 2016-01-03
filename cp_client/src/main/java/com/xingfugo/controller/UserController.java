package com.xingfugo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.User;
import com.xingfugo.business.module.UserCart;
import com.xingfugo.business.service.GoodsaskService;
import com.xingfugo.business.service.SmshistoryService;
import com.xingfugo.business.service.UserCartService;
import com.xingfugo.business.service.UserService;
import com.xingfugo.business.service.VisitorCartService;
import com.xingfugo.common.CartCookieUtil;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.module.UserLogin;
import com.xingfugo.module.UserReg;
import com.xingfugo.util.Md5;
import com.xingfugo.util.RandomStrUtil;

//个人会员
@Controller
public class UserController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(UserController.class.getSimpleName());
	
	@Autowired
	private UserService userService;
	@Autowired
	private SmshistoryService smshistoryService;
	
	@Autowired
	private VisitorCartService visitorCartService;
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private GoodsaskService goodsaskService;
	
	//把游客购物车移到登录会员正式购物车中
	public void setUserCartFromVisitorCart(String user_id,HttpServletResponse response){
		String visitor_user_id = CartCookieUtil.getVisitorCartName(visitorCartService.redisExpiredSeconds(),getRequest(),response);
		UserCart userCart = visitorCartService.getUserCart(visitor_user_id);
		List cartList = userCart.getCartItems();
		if(cartList != null && cartList.size()>0){
			userCartService.save(user_id, cartList);
			visitorCartService.empty(visitor_user_id);
		}
	}

	//进入注册页面
	@RequestMapping(value="signup",method=RequestMethod.GET)
	public String signup(ModelMap model){
		//已登录跳转至首页
		if(!SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID).equals("")){
			return "redirect:"+basePath()+"index.action";
		}
		UserReg userReg = new UserReg();
		model.addAttribute(userReg);
		return "signup";
	}
	
	//个人会员注册
	@RequestMapping(value="regon",method=RequestMethod.POST)
	public String regon(@Valid UserReg userReg,Errors errors,HttpServletRequest request){
		
		if (errors.hasErrors()){
	           return "signup";
		}
		
		String cellphone = userReg.getCellphone();
        String passwd = userReg.getPasswd();
        String sure_passwd = userReg.getSure_passwd();
        String check_code = userReg.getCheck_code();
        
        
        
        Map uMap = new HashMap();
        uMap.put("cellphone", cellphone);
        List userList = userService.getUserByMap(uMap);
		
		if(userList != null && userList.size() > 0){
			//手机号已注册
			errors.rejectValue("user_name", "user.user_name.login_username_not_exist", "手机号已注册"); 
		}
		
		if(!passwd.equals(sure_passwd)){
			errors.rejectValue("sure_passwd", "user.sure_passwd.nosame", "两次密码输入不一样"); 
		}

		//获取短信发送出去并存储在数据库中的验证码
		String dbSmdCode = smshistoryService.getRegCodeByPhone(cellphone);
		
		if(!check_code.equals(dbSmdCode)){
			errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确"); 
		}
		
		if (errors.hasErrors()){
	           return "signup";
		}
		
		
		User user = new User();
		user.setPasswd(Md5.getMD5(passwd.getBytes()));
		String user_name = "mp_"+RandomStrUtil.getWordAndNum();
		user.setUser_name(user_name);
		user.setCellphone(cellphone);
		Integer user_id = userService.insertUserGetUserId(user);
		
		SessionUtil.putString(request,Constants.SESSION_USER_ID, String.valueOf(user_id));
		SessionUtil.putString(request,Constants.SESSION_USER_NAME, user_name);
		
		return "redirect:"+basePath()+"user/uccenter.action";
	}
	
	//退出登录
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		SessionUtil.putString(request,Constants.SESSION_USER_ID, "");
		SessionUtil.putString(request,Constants.SESSION_USER_NAME, "");
		return "redirect:"+basePath()+"index.action";
	}
	
	//进入登录页面
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(ModelMap model){
		
		//已登录跳转至首页
		if(!SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID).equals("")){
			return "redirect:"+basePath()+"index.action";
		}
		
		SessionUtil.put(getRequest(), "redirect_page", getRequest().getParameter("redirect_page"));
		
		UserLogin userLogin = new UserLogin();
		model.addAttribute("userLogin", userLogin);
		return "login";
	}
	
	//个人会员登录
	@RequestMapping(value="logon",method=RequestMethod.POST)
	public ModelAndView logon(@Valid UserLogin userLogin,Errors errors,
			HttpServletRequest request,HttpServletResponse response,
			ModelMap modelMap){
		
		if (errors.hasErrors()){
	           return new ModelAndView("login");
		}
		
		String user_account = userLogin.getCellphone();
        String passwd = userLogin.getPasswd();
        
        Map uMap = new HashMap();
        uMap.put("user_account", user_account);
        List userList = userService.getUserByMap(uMap);
		if(userList == null || userList.size() == 0){
			//输入信息在用户名、手机号、邮箱中均不存在
			errors.rejectValue("user_name", "user.user_name.login_username_not_exist", "输入帐号不存在"); 
		}
		String user_id = "",user_name="";
		if(userList!=null && userList.size()>0){
			Map userMap = (HashMap)userList.get(0);
			if(userMap.get("passwd") != null && !passwd.equals("")){
				passwd = Md5.getMD5(passwd.getBytes());
				if(!passwd.equals(userMap.get("passwd").toString())){
					//密码不正确
					errors.rejectValue("passwd", "user.passwd.login_passwd_wrong", "密码不正确"); 
				}
			}
			if(userMap.get("user_id") != null){
				user_id = userMap.get("user_id").toString();
			}
			if(userMap.get("user_name") != null){
				user_name = userMap.get("user_name").toString();
			}
		}
		
		String check_code = userLogin.getCheck_code();
		String randCheckCode = "";
		if(SessionUtil.get(request, "randCheckCode") != null){
			randCheckCode = SessionUtil.get(request, "randCheckCode").toString();
		}
		if(!check_code.equalsIgnoreCase(randCheckCode)){
			errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确"); 
		}
		
		if (errors.hasErrors()){
	           return new ModelAndView("login");
		}
		
		SessionUtil.putString(request,Constants.SESSION_USER_ID, user_id);
		SessionUtil.putString(request,Constants.SESSION_USER_NAME, user_name);
		
		//把游客购物车的内容移到正式登录会员的购物车去
		//且把游客购物车清掉
		setUserCartFromVisitorCart(user_id,response);
		
		//跳转到登录前指定要进入的页面
		String redirect_page = SessionUtil.getString(request,"redirect_page");
		if(!redirect_page.equals("")){
			SessionUtil.put(request,"redirect_page","");
			return new ModelAndView("redirect:"+redirect_page);
		}
		
		//跳转到登录前要进入的页面
		String login_callback = SessionUtil.getString(request,"login_callback");
		if(!login_callback.equals("")){
			SessionUtil.putString(request,"login_callback","");
			Map<String, Object> paramMap = (Map<String, Object>)SessionUtil.get(request,"login_redirect_param");
			if(paramMap != null && !paramMap.isEmpty()){
				Iterator<String> keyIt = paramMap.keySet().iterator();
				while(keyIt.hasNext()){
					String pn = keyIt.next();
					modelMap.addAttribute(pn, paramMap.get(pn));
				}
				SessionUtil.put(request,"login_redirect_param", null);
			}
			return new ModelAndView(new RedirectView(login_callback, false, false, true));
		}
		
		return new ModelAndView("redirect:"+basePath()+"user/uccenter.action");
	}
	
	//进入会员中心首页
	@RequestMapping(value="user/uccenter",method=RequestMethod.GET)
	public String uccenter(ModelMap model){
		
		//返回当前用户当天商品咨询的商家回复数量
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		int reContCount = goodsaskService.getRecontCountByNowUserid(Integer.parseInt(user_id));
		model.addAttribute("reContCount", reContCount);
		
		return "user/index";
	}
	
	//发短信
	@RequestMapping(value="sendPhoneCode",method=RequestMethod.GET)
	@ResponseBody
	public String sendPhoneCode(String phone) throws Exception {
		return smshistoryService.registerSendCheckCode(phone);
	}
	
	private static final String SSOCODE = "xfg_sso_code";
	
	//外部系统传入url地址，实现自动触屏版单点登录效果
	//成功返回1，失败返回0
	//加密方式：md5(md5(user_id)+'xfg_sso_code')
	@RequestMapping(value="ssologin",method=RequestMethod.GET)
	@ResponseBody
	public String ssoLogin(String user_id,String verifyCode){
		if(StringUtils.isBlank(user_id) || StringUtils.isBlank(verifyCode)){
			return "0";
		}
		User user = this.userService.getMemberUserById(Integer.parseInt(user_id));
		if(user == null){
			return "0";
		}
		String _user_id = user_id;
		_user_id = Md5.getMD5(_user_id.getBytes());
		StringBuffer md5Str = new StringBuffer();
		md5Str.append(_user_id);
		md5Str.append(SSOCODE);
		String _verifyCode = Md5.getMD5(md5Str.toString().getBytes());
		if(verifyCode.equals(_verifyCode)){
			//验证通过后放入session
			SessionUtil.putString(getRequest(),Constants.SESSION_USER_ID, user_id);
			SessionUtil.putString(getRequest(),Constants.SESSION_USER_NAME, user.getUser_name());
			return "";
		}else{
			return "0";
		}
	}
	
}
