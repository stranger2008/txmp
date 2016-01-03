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
                    <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="javascript:void(0);">我的消息</a>
                </div>
            </section>
        </div>
        
<div class="module">
    <section>
    	<div class="mt5">
            <div class="ask-center">
                <div class="ask-section">
                
                <form action="<%=basePath %>user/goodsasklist.action">
                
                	<c:forEach items="${pageResult.list}" var="gask">
	                   	<a href="javascript:void(0);">
	                    	<ul class="ask-ul">
	                            <li class="ask-tit">
	                                <span>问：</span><span>${gask.c_content }</span>
	                                <br />
	                                <span class="gray">${gask.user_name }</span><span class="gray fr">
	                                <fmt:formatDate value='${gask.c_date }' pattern="yyyy-MM-dd HH:mm:ss" />
	                                </span>
	                            </li>
	                            <c:if test="${!empty gask.re_content }">
	                            	<li class="answer">
		                                <span style="color:#c80000;">答：</span><span style="color:#c80000;">${gask.re_content }<span class="gray fr"><fmt:formatDate value='${gask.re_date }' pattern="yyyy-MM-dd HH:mm:ss" /></span>
		                            </li>
	                            </c:if>
	                        </ul>
	                    </a>
	                </c:forEach>
	                
	                <c:if test="${empty pageResult.list}">
	                	暂无咨询
	                </c:if>
	                
	                <c:if test="${!empty pageResult.list}">
	                	${pageBar }
	                </c:if>
	                
	               </form>
                
                </div>
    
            </div>
        </div>       
    </section>
</div>
        

    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
