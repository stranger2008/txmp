<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>用户中心</title>
</head>

<body>

    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="<%=basePath %>user/goodsReturn_list.action">退换货</a>
                </div>
            </section>
        </div>
        
        
   <div class="module">
    <section>
    	<div class="cont mt5">
            <div class="order">
                <div class="order-section">
                    <div class="order-cont">
                        <div class="order-track">
                            <span class="order-txt-sts">状态：${info_state_name }</span>
                        </div>
                        <div class="order-flow">
                        	<span class="order-arr-t"></span>
                            <ul class="order-track-ul">
	                            <c:if test="${!empty goodsReturnHis}">
	                            	<c:forEach items="${goodsReturnHis}" var="grh">
	                              		<li>
	                              			<span>${grh.oper_name}</span>
	                              			<span>${grh.in_date}</span>
	                              		</li>
	                            	</c:forEach>
	                            </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
        
        
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>

    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>

