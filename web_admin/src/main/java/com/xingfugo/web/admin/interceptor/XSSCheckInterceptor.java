package com.xingfugo.web.admin.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//XSS控制
public class XSSCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final String errorPath = "/public/error.action";//出错跳转的目的地
    private static String[] excludePaths;//不进行拦截的url
    private static String[] safeless = {"<script",   //需要拦截的JS字符关键字
             "</script",
             "<iframe",
             "<input",
             "</iframe",
             "<frame",
             "</frame",
             "set-cookie",
             "%3cscript",
             "%3c/script",
             "%3ciframe",
             "%3c/iframe",
             "%3cframe",
             "%3c/frame",
             "src=\"javascript:",
             "<body",
             "</body",
             "%3cbody",
             "%3c/body",
             //"<",
             //">",
             //"</",
             //"/>",
             //"%3c",
             //"%3e",
             //"%3c/",
             //"/%3e"
            };
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Enumeration params = request.getParameterNames();
        boolean isSafe = true;
        String requestUrl = request.getRequestURI();
        if(isSafe(requestUrl)) {
            requestUrl = requestUrl.substring(requestUrl.indexOf("/"));
            if(!excludeUrl(requestUrl)) {
                while (params.hasMoreElements()) {
                    String cache = request.getParameter((String) params.nextElement());
                    if(StringUtils.isNotBlank(cache)) {
                        if(!isSafe(cache)) {
                            isSafe = false;
                            break;
                        }
                    }
                }
            }
        } else {
            isSafe = false;
        }
              
        if(!isSafe) {
            response.sendRedirect(request.getContextPath() + errorPath+"?name=request_param_illegal");
            return false;
        }
        return super.preHandle(request, response, handler);
	}

	private static boolean isSafe(String str) {
        if(StringUtils.isNotBlank(str)) {    
            for (String s : safeless) {
                if(str.toLowerCase().contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }
          
    private boolean excludeUrl(String url) {      
        if(excludePaths != null && excludePaths.length > 0) {                  
            for (String path : excludePaths) {
                if(url.toLowerCase().equals(path)) {
                    return true;
                }
            }
        }
        return false;
    }
	
}
