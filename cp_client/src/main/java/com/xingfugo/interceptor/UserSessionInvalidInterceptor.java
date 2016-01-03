package com.xingfugo.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xingfugo.business.common.Constants;
import com.xingfugo.common.SessionUtil;

//用户session失效权限控制
public class UserSessionInvalidInterceptor extends HandlerInterceptorAdapter {
	
	private static final String loginUrl = "/login.action";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//登录后跳转到登录前指定要返回的页面
		SessionUtil.putString(request, "login_callback", request.getRequestURL().toString());
		
		String session_user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(session_user_id.equals("")){
			Map paramterMap = request.getParameterMap();
			if(paramterMap != null && !paramterMap.isEmpty()){
				SessionUtil.put(request, "login_redirect_param",  paramterMap);
				//paramterMap.clone()
			}
			
			response.sendRedirect(request.getContextPath() + loginUrl);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
