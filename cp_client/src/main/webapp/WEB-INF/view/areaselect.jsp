<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>子站选择-<%=web_title %></title>
</head>

<body>
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">子站选择</a>
                </div>
            </section>
        </div>
        
        <!--内容开始-->
    <div class="module">
        <section>
            <div class="classify mt5">
                <div class="classify_cont">
                    <ul>
                        <li><a class="cls-area" href="<%=basePath %>areaindex-.action">全部</a></li>
                        <c:forEach items="${areaList}" var="area">
                        <li><a class="cls-area" href="<%=basePath %>areaindex-${area.area_id}.action">${area.area_name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </section>
    </div>
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    

</body>
</html>
