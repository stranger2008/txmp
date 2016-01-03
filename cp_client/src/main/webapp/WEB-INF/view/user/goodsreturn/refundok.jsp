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
    	<div class="movBg-Ok">
	    	<div class="movOrd-Ok">
	            <div class="movOrd-Ok-div">
	                <div class="movOrd-Ok-cont margin-center">
	                    <h2 class="movOrd-Ok-tit">退款申请提交成功！</h2>
	                    <p>请等待商家审核，返回<a href="<%=basePath %>user/uccenter.action">回会员中心</a></p>
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
