<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--底部开始-->
<div class="module" style="margin-top:10px;">
    <footer class="footer margin-center">
        <section class="footer_btn clearfix">
            <p class="user-info fl">
                <span>
                	<c:if test="${!empty sessionScope.session_user_name}">
                		${sessionScope.session_user_name}
                		<a href="<%=basePath %>logout.action">[退出]</a>
                	</c:if>
                	<c:if test="${empty sessionScope.session_user_name}" >
                		<a href="<%=basePath %>login.action">登录</a></span>
	                	<span>|</span>
	                	<span><a href="<%=basePath %>signup.action">注册</a>
                	</c:if>
                </span>
            </p>
            <p class="gotop fr">
                <a href="<%=basePath %>getapp.action">App</a>
                <a href="javascript:scroll(0,0)" id="J_GoTop">Top</a>
            </p>
        </section>
        <p class="copyright">
            ©2014 <a href="<%=basePath %>aboutus/9.action">天下名品</a> 400-6666-288
        </p>
        <nav class="footer_nav">
            <a href="#" title="" class="hover_footer">触屏版</a><a href="#" title="">电脑版</a>
        </nav>
    </footer>
</div>
