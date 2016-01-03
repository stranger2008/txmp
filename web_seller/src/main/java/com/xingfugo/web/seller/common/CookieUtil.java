package com.xingfugo.web.seller.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Cookie操作
public class CookieUtil {
	
	//放入cookie值
	public static void set(int expiredSeconds,String key,String value,HttpServletResponse response){
		Cookie vis_key = new Cookie(key, value);
		vis_key.setMaxAge(expiredSeconds); //set expire time to 216000 sec
		vis_key.setPath("/");
		response.addCookie(vis_key);
	}
	
	//得到cookie值
	public static String get(HttpServletRequest request,String key){
		String ret = "";
		Cookie cookie[]=request.getCookies();
		if(cookie != null){
			for(int i=0;i<cookie.length;i++){
				Cookie newCookie = cookie[i];
				if(newCookie.getName().equals(key)){
					ret = newCookie.getValue();
					break;
				}
			}
		}
		return ret;
	}
	
}
