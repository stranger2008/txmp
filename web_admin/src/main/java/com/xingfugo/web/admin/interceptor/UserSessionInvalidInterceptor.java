package com.xingfugo.web.admin.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.service.SysmenuService;
import com.xingfugo.business.service.SysuserService;
import com.xingfugo.web.admin.common.SessionUtil;


//用户session失效权限控制
public class UserSessionInvalidInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGINURL = "/login.action";
	//权限警告页面
	private static final String PERMISSION_WARN_URL = "/warning.action";
	
	//被允许的url
	private static final String[] ALLOWED_URLS = {"sysuser/index.action", "sysuser/update-personal.action","public/getdownmenu.action"};
	
	//商家后台菜单系统编码
	private static final String MENU_ROOT = "1111111111";
	
	@Autowired
	private SysmenuService sysmenuService;
	@Autowired
	private SysuserService sysuserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
//		if(principal!=null){
//			String username = principal.getName();   
//			if(username!=null&&!"".equals(username)){
//				Map<String,Object> loginMap = principal.getAttributes();
//				String user_id="",role_id="";
//				if(loginMap.get("userId")!=null){
//					user_id = loginMap.get("userId").toString();
//				}
//				if(loginMap.get("roleId")!=null){
//					role_id = loginMap.get("roleId").toString();
//				}
//				if(!user_id.equals(""))
//				SessionUtil.putString(request, Constants.SESSION_USER_ID, user_id);
//				SessionUtil.putString(request, Constants.SESSION_USER_NAME, username);
//				SessionUtil.putString(request, "session_role_id", role_id);
//			}
//		}
		
		
		String session_user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(session_user_id.equals("")){
			response.sendRedirect(request.getContextPath() + LOGINURL);
			return false;
		}
		//菜单数据库处理
		menuOper(request);

		String servletPath = request.getServletPath();
		String url = null;
		if(servletPath != null && servletPath.length() > 1) {
			url = servletPath.substring(1);
		}
		
		//默认被允许的url
		boolean isAllowed = false;
		
		for(int i=0; i<ALLOWED_URLS.length; i++) {
			if(ALLOWED_URLS[i].equals(url)) {
				isAllowed = true;
				break;
			}
		}
		
		if(!isAllowed) {
			if(!sysuserService.hasPermission(session_user_id, url)) {
				response.sendRedirect(request.getContextPath() + PERMISSION_WARN_URL);
				return false;
			}
		}
		
		request.getSession().setAttribute("prev_url", servletPath);
		
		return super.preHandle(request, response, handler);
	}

	//菜单数据处理
	private void menuOper(HttpServletRequest request){
		List oneMenuList = null;//一级菜单
		List twoMenuList = null;//二级菜单
		
		String currentUserId = SessionUtil.getString(request, Constants.SESSION_USER_ID);
		oneMenuList = sysmenuService.getUserSysmenus(currentUserId, MENU_ROOT, "0");
		//一级菜单第一个菜单ID
		String one_menu_id = SessionUtil.getString(request, "one_menu_id");
		if(one_menu_id.equals("")){
			if(oneMenuList != null && oneMenuList.size() > 1) {
				one_menu_id = (String) ((Map)(oneMenuList.get(0))).get("menu_id");
			}
		}
		if(one_menu_id != null && !"".equals(one_menu_id)) {
			//二级菜单List
			twoMenuList = sysmenuService.getUserSysmenus(currentUserId, one_menu_id, "0");
		}
		
		request.setAttribute("one_menu_id", one_menu_id);
		request.setAttribute("oneMenuList", oneMenuList);
		request.setAttribute("twoMenuList", twoMenuList);
	}
	
}
