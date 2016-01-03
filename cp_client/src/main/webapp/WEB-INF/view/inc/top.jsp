<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	String showApp = request.getParameter("showApp");
%>
<!--头部开始-->
<header class="module">
    <section class="top margin-center">
        <div class="top_a clearfix">
            <a href="<%=basePath %>areaselect.action" class="loc fl">
            	<c:if test="${!empty sessionScope.session_area_name}">
               		${sessionScope.session_area_name}
               	</c:if>
               	<c:if test="${empty sessionScope.session_area_name}" >
               		地区
               	</c:if>
            </a>
            <% 
            	if(showApp!=null){
            %>
            <a href="<%=basePath %>getapp.action" class="upl fr">APP</a>
            <% 
            	}
            %>
            <span class="top-span fr"><a href="<%=basePath %>cartlist.action">购物车</a>|<a href="<%=basePath %>user/uccenter.action">会员中心</a></span>
        </div>
        <div class="logo_m margin-center">
            <a href="<%=basePath %>"><img src="<%=basePath %>inc/images/logo_m.png"></a>	
        </div>
    </section>
</header>