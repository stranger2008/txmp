package com.xingfugo.web.client.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//session封装管理
public class SessionUtil {
	public static void put(HttpServletRequest request,String name,Object value){
		getSession(request).setAttribute(name, value);
	}
	
	public static Object get(HttpServletRequest request,String name){
		return getSession(request).getAttribute(name);
	}
	
	public static void putString(HttpServletRequest request,String name,String value){
		getSession(request).setAttribute(name, value);
	}
	
	public static String getString(HttpServletRequest request,String name){
		if(getSession(request).getAttribute(name) != null){
			return getSession(request).getAttribute(name).toString();
		}else{
			return "";
		}
	}
	
	public static HttpSession getSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		return session;
	}
}
