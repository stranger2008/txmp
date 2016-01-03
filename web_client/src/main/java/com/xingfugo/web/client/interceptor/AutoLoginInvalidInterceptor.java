package com.xingfugo.web.client.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.User;
import com.xingfugo.business.service.UserService;
import com.xingfugo.util.Md5;
import com.xingfugo.web.client.common.SessionUtil;

public class AutoLoginInvalidInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;
	//自动登录--存入cookie的名
	public static final String AUTOLOGIN_COOKIE_NAME = "tianXiaMingPin";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String session_user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(session_user_id)){
			Cookie[] cookies =  request.getCookies();
	        if (cookies != null) {
	            for(Cookie cookie : cookies) {
	            	 if (AUTOLOGIN_COOKIE_NAME.equals(cookie.getName())) {
	            		 setUser(request,cookie);
	            		 break;
	            	 }
	            }
	        }
		}
		
        return super.preHandle(request, response, handler);
        
	}
	
	public void setUser(HttpServletRequest request,Cookie autoCookie){
		//cookie结构:加密user_id|user_name|有效日期毫秒数
        String[] values = autoCookie.getValue().split("\\|");
        //从值里面得到cookie有效期，判断cookie是否有效，如果cookie无效，则直接放行
        long expiresTime = Long.parseLong(values[2]);
        if(System.currentTimeMillis() < expiresTime){
        	//根据user_name判断加密的user_id是否相等
	        User user = userService.getMemberUserByUsername(values[1]);
	        if(user != null){
		        String user_id = user.getUser_id().toString();
		        if(Md5.getMD5(user_id.getBytes()).equals(values[0]))
		        {
		        	SessionUtil.putString(request,Constants.SESSION_USER_ID, user_id);
			   		SessionUtil.putString(request,Constants.SESSION_USER_NAME, values[1]);
		        }
	        }
        }
	}
        
	
}
