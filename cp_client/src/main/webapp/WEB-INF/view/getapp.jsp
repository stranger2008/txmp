<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>APP下载-<%=web_title %></title>
</head>

<body>
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    	<!--app下载-->
		<div class="module">
		    <section>
		    	<div class="download-bg margin-center">
		            <div class="pos-app">
		                <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">下载APP</a>
		            </div>
		            <div class="app-img"><img src="<%=basePath %>inc/images/app-img.png"></div>
					<div class="app-btns">
		            	<span class="android-btn"><a href="<%=basePath %>downloadapp.action" target="_blank"><span class="app-icon1"></span><span>下载</span><span>Android 版本</span></a></span>
		                <p class="app-txt">适用于2.2以上版本</p>
		            	<span class="app-btn"><a href="https://itunes.apple.com/cn/app/tian-xia-ming-pin/id895388666?mt=8"><span class="app-icon2"></span><span>下载</span><span>IOS 版本</span></a></span>
		                <p class="app-txt">适用于6.0以上版本</p>
		            </div>
		        </div>
		    </section>
		</div>
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    
</body>
</html>
