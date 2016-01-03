package com.xingfugo.web.seller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.service.SysmenuService;
import com.xingfugo.web.seller.common.SessionUtil;


//用户session失效权限控制
public class UserSessionInvalidInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGINURL = "/login.action";
	
	//商家后台菜单系统编码
	private static final String SHOP_SYSCODE = "sho";
	
	@Autowired
	private SysmenuService sysmenuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String session_cust_id = SessionUtil.getString(request,Constants.SESSION_CUST_ID);
		if(session_cust_id.equals("")){
			response.sendRedirect(request.getContextPath() + LOGINURL);
			return false;
		}
		
		//菜单数据库处理
		menuOper(request);
		
		return super.preHandle(request, response, handler);
	}

	//菜单数据处理
	private void menuOper(HttpServletRequest request){
		//一级菜单list
		List oneMenuList = sysmenuService.getOneLevelMenuBySyscode(SHOP_SYSCODE);
		//一级菜单第一个菜单ID
		String one_menu_id = SessionUtil.getString(request, "one_menu_id");
		if(one_menu_id.equals("")){
			one_menu_id = sysmenuService.getFirstMenuByList(oneMenuList);
		}
		//二级菜单List
		List twoMenuList = sysmenuService.getMenuListByUpmenuid(one_menu_id);
		request.setAttribute("one_menu_id", one_menu_id);
		request.setAttribute("oneMenuList", oneMenuList);
		request.setAttribute("twoMenuList", twoMenuList);
	}
	
}
