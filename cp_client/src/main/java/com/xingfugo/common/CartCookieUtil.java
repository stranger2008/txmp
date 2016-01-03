package com.xingfugo.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xingfugo.util.RandomStrUtil;

//游客购物车cookie操作
public class CartCookieUtil {
	
	private final static String VISITOR_KEY_ID = "visitor_key_id";
	
	//获取游客购物车key的id值
	public static String getVisitorCartName(int expiredSeconds,HttpServletRequest request,HttpServletResponse response){
		String value = getCookieKeyValue(request,VISITOR_KEY_ID);
		if(value.equals("")){
			String _randVal = RandomStrUtil.getNumberRand();
			Cookie vis_key = new Cookie(VISITOR_KEY_ID, _randVal);
			vis_key.setMaxAge(expiredSeconds); //set expire time to 216000 sec
			vis_key.setPath("/");
			response.addCookie(vis_key);
			value = _randVal;
		}
		return value;
	}
	
	//传入cookie的key，得到值
	public static String getCookieKeyValue(HttpServletRequest request,String key){
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
