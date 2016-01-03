<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>帮助中心-<%=web_title %></title>
</head>

<body>
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">帮助中心</a>
                </div>
            </section>
        </div>
        
    	
       <div class="module">
	        <section class="box_sign margin-center">
	            <h1 class="sign_tit">${aboutus.title}</h1>
	               <label>
	               	<div class="about">
	               	${aboutus.content}
	               	</div>
				</label>
	        </section>
	    </div>
                
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
