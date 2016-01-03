package com.xingfugo.web.client.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
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
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CollectQueryForm;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.service.CartService;
import com.xingfugo.business.service.CollectService;
import com.xingfugo.business.service.GoodsaskService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.MemberuserService;
import com.xingfugo.business.service.SmshistoryService;
import com.xingfugo.business.service.UserCartService;
import com.xingfugo.business.service.UserService;
import com.xingfugo.business.service.VisitorCartService;
import com.xingfugo.util.Md5;
import com.xingfugo.web.client.common.CartCookieUtil;
import com.xingfugo.web.client.common.SessionUtil;
import com.xingfugo.web.client.module.FindPwd;
import com.xingfugo.web.client.module.Updatepasswd;
import com.xingfugo.web.client.module.UserLogin;
import com.xingfugo.web.client.module.UserReg;

//个人会员
@Controller
public class UserController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(UserController.class.getSimpleName());
	
	//自动登录--存入cookie的名
	public static final String AUTOLOGIN_COOKIE_NAME = "tianXiaMingPin";
	
	@Autowired
	private UserService userService;
	@Autowired
	private MemberuserService memberuserService;
	@Autowired
	private SmshistoryService smshistoryService;
	
	@Autowired
	private VisitorCartService visitorCartService;
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private GoodsaskService goodsaskService;
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private CollectService collectService;
	
	private CartService cartService;
	
	//新增默认会员  普通会员
	private static String DEFAULT_LEVEL = "普通会员";
	//0：默认正常状态
	private static String DEFAULT_STATUS_VALUE="0";
	//0：未删除订单
	private static String IS_DEL="0";
	
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
		model.addAttribute("userReg",userReg);
		return "signup";
	}
	//验证手机
	@RequestMapping(value="checkcell",method=RequestMethod.GET)
	@ResponseBody
	public String checkcell(String cellnumber){
		List haslist = memberuserService.hasPhonenumber(cellnumber);
		if(haslist.size() > 0){
			return "2";
		}
//		Pattern p = Pattern.compile("^1[3578]\\d{9}$");
		Pattern p = Pattern.compile("^1\\d{10}$");
		Matcher m = p.matcher(cellnumber);
		if(m.find()){
			return "1";
		}
		return "0";
		
	}
	// 用户名检测是否存在
	@RequestMapping(value="checkname",method=RequestMethod.GET)
	@ResponseBody
	public String checkname(String username){
		
		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*$");
		Matcher m = p.matcher(username);
		if(!m.find()){
			return "2";
		}
        Map uMap = new HashMap();
        uMap.put("user_account", username);
        List userList = userService.getUserByMap(uMap);
        if(userList != null && userList.size() > 0){
			//输入的用户名存在
			return "1";
		}
		return "0";
	}
	// 提交验证
	@RequestMapping(value="checksubmit",method=RequestMethod.GET)
	@ResponseBody
	public String checksubmit(HttpServletRequest request){
		Map uMap = new HashMap();
        uMap.put("request", request.getParameter("user_name"));
        List userList = userService.getUserByMap(uMap);
		//
		String dbSmdCode = smshistoryService.getRegCodeByPhone(request.getParameter("cellnumber"));
		
		if(!request.getParameter("checkinput").equals(dbSmdCode)){
			return "1";
		}
		UserReg userReg = new UserReg();
		userReg.setPasswd(Md5.getMD5(request.getParameter("lenpwd").getBytes()));
		userReg.setUser_name(request.getParameter("user_name"));
		userReg.setCellphone(request.getParameter("cellnumber"));
		Integer user_id = userService.insertUserGetUserId(userReg);
		
		SessionUtil.putString(request,Constants.SESSION_USER_ID, String.valueOf(user_id));
		SessionUtil.putString(request,Constants.SESSION_USER_NAME, request.getParameter("user_name"));
		
		return "redirect:"+basePath()+"user/uccenter.action";
       
	}
	
	//个人会员注册
	@RequestMapping(value="regon",method=RequestMethod.POST)
	public String regon(@Valid UserReg userReg,Errors errors,HttpServletRequest request){
		
		if (errors.hasErrors()){
	        return "signup";
		}
		
		String user_name = userReg.getUser_name();
        String passwd = userReg.getPasswd();
        String sure_passwd = userReg.getSure_passwd();
        String check_code = userReg.getCheck_code();
        String cellphone = userReg.getCellphone();
        
        
        
        Map uMap = new HashMap();
        uMap.put("user_name", user_name);
        List userList = userService.getUserByMap(uMap);
		
		if(userList != null && userList.size() > 0){
			//用户已注册
			errors.rejectValue("user_name", "user.user_name.login_username_not_exist", "该用户名已注册"); 
		}
		
		if(!passwd.equals(sure_passwd)){
			errors.rejectValue("sure_passwd", "user.sure_passwd.nosame", "两次密码输入不一样"); 
		}

		//
		String dbSmdCode = smshistoryService.getRegCodeByPhone(cellphone);
		
		if(!check_code.equals(dbSmdCode)){
			errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确"); 
		}
		
		if (errors.hasErrors()){
	           return "signup";
		}
		
		userReg.setPasswd(Md5.getMD5(passwd.getBytes()));
//		String user_name = "xfg_"+RandomStrUtil.getWordAndNum();
//		userReg.setUser_name(user_name);
		userReg.setCellphone(cellphone);
		userReg.setUser_level(DEFAULT_LEVEL);
		userReg.setState_code(DEFAULT_STATUS_VALUE);
		Integer user_id = userService.insertUserGetUserId(userReg);
		
		SessionUtil.putString(request,Constants.SESSION_USER_ID, String.valueOf(user_id));
		SessionUtil.putString(request,Constants.SESSION_USER_NAME, user_name);
		
		return "redirect:"+basePath()+".action";
	}
	
	//退出登录
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response){
		SessionUtil.putString(request,Constants.SESSION_USER_ID, "");
		SessionUtil.putString(request,Constants.SESSION_USER_NAME, "");
		//自动登录 删除cookie
		Cookie cookie = new Cookie(AUTOLOGIN_COOKIE_NAME, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return "redirect:"+basePath()+"index.action";
	}
	
	//进入登录页面
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(ModelMap model){

		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		String user_name = SessionUtil.getString(getRequest(),Constants.SESSION_USER_NAME);
		//已登录跳转至首页
		if(StringUtils.isNotBlank(user_id) && StringUtils.isNotBlank(user_name))
		{
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
		
		String user_account = userLogin.getUser_name();
        String passwd = userLogin.getPasswd();
        //自动登录
        String autoLogin = userLogin.getAutoLogin();
        
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
		
//		String check_code = userLogin.getCheck_code();
//		String randCheckCode = "";
//		if(SessionUtil.get(request, "randCheckCode") != null){
//			randCheckCode = SessionUtil.get(request, "randCheckCode").toString();
//		}
//		if(!check_code.equalsIgnoreCase(randCheckCode)){
//			errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确"); 
//		}
		
		if (errors.hasErrors()){
	           return new ModelAndView("login");
		}
		
		SessionUtil.putString(request,Constants.SESSION_USER_ID, user_id);
		SessionUtil.putString(request,Constants.SESSION_USER_NAME, user_name);
		
		//自动登录
		Cookie cookie = null;
		if(autoLogin!=null && "0".equals(autoLogin))
		{
			String user_id_Md5  =  Md5.getMD5(user_id.getBytes());
			int maxAge=3600*24*7*1000; //一周时间 毫秒数
			long maxTime = System.currentTimeMillis() + maxAge; //现时间+有效时间
			cookie=new Cookie(AUTOLOGIN_COOKIE_NAME,user_id_Md5+"|"+user_name+"|"+maxTime);
			cookie.setMaxAge(maxAge);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
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

	//发短信
	@RequestMapping(value="sendPhoneCode",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
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
			return "redirect:"+basePath()+"login.action";
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
	
	//
	//会员中心
	@RequestMapping(value="user/uccenter",method=RequestMethod.GET)
	public String userCenter(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		User user = userService.getMemberUserById(Integer.parseInt(user_id));
		if(user == null) {
			return "user/index";
		}
		//订单
		GoodsorderQueryForm goodsorderQueryForm = new GoodsorderQueryForm();
		goodsorderQueryForm.setUser_id(user_id);
		goodsorderQueryForm.getPg().setSize(2);
		goodsorderQueryForm.setIs_del(IS_DEL);
		PageResult goodsList = goodsorderService.getGoodsOrderListByPage(goodsorderQueryForm);
		model.addAttribute("goodsResult", goodsList);
		
		//收藏商品
		CollectQueryForm collectGoodsQueryForm = new CollectQueryForm();
		collectGoodsQueryForm.setUser_id(user_id);
		collectGoodsQueryForm.getPg().setSize(5);
		PageResult collectGoodsList = collectService.getCollectGoodsListByPage(collectGoodsQueryForm);
		model.addAttribute("collectGoodsResult", collectGoodsList);
		
		//收藏店铺
		CollectQueryForm collectShopQueryForm = new CollectQueryForm();
		collectShopQueryForm.setUser_id(user_id);
		collectShopQueryForm.getPg().setSize(17);
		PageResult collectShopList = collectService.getCollectShopListByPage(collectShopQueryForm);
		model.addAttribute("collectShopResult", collectShopList);
		
		//待付款数量
		GoodsorderQueryForm noPayQueryForm = new GoodsorderQueryForm();
		noPayQueryForm.setUser_id(user_id);
		noPayQueryForm.setOrder_state("0");
		PageResult noPostList = goodsorderService.getGoodsOrderListByPage(noPayQueryForm);
		//待确认收货数量
		GoodsorderQueryForm cfmGoodsQueryForm = new GoodsorderQueryForm();
		cfmGoodsQueryForm.setUser_id(user_id);
		cfmGoodsQueryForm.setOrder_state("2");
		PageResult cfmGoodsList = goodsorderService.getGoodsOrderListByPage(cfmGoodsQueryForm);
		model.addAttribute("noPayNums",noPostList.getTotal());
		model.addAttribute("cfmGoodsNums",cfmGoodsList.getTotal());
		model.addAttribute("user_name",user.getUser_name());
		return "user/index";
	}
	//
	
	
}
