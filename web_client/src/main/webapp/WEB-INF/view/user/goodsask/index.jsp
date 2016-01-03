<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <style type="text/css">
    	.selectpsd{ width:100%;font-size:14px; padding:4px 0;margin-top:5px;color:#666; border:1px solid #d7d7d7;}
    </style>
    <title>我的消息</title>    

  </head>
  
  <body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--头部下搜索框-->
<%@ include file="/WEB-INF/view/user/inc/top.jsp" %>		 
<!--nav开始-->
<%@ include file="/WEB-INF/view/user/inc/nav.jsp" %>	

<!--个人中心-->
<div class="w1200">
	<div class="usercenter">
    	<div class="position">
        	<a href="#"><strong>我的天下名品</strong></a><span></span><a href="#">账户安全</a>
        </div>
        
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
						
         <div class="user-info fr">
        	<div class="mymsg">
            	<h2>我的消息</h2>
            	<form action="<%=basePath %>user/goodsasklist.action">
	                <table width="100%" cellpadding="0" cellspacing="0" class="msg-tab">                   	                   	                		
            			<tr>
                			<th class="msg-time"><label>时间</label></th><th>主题</th>
                		</tr>
	                    <c:if test="${!empty pageResult.list}"> 
	                	  <c:forEach items="${pageResult.list}" var="gask">
	                		<tr>
	                    		<td class="msg-time"><label>${fn:substring(gask.c_date,0,19)}</label></td>
	                    		<td><p class="msg-p">${gask.c_content}</p>
	                    		<p class="msg-r" style="color:#e10000;">
		                    		<c:if test="${!empty gask.re_content}">
		                    			回复：${gask.re_content}
		                    		</c:if>
	                    		</p></td>
	                    	</tr>
		                  </c:forEach>
		                </c:if>	                
		               <c:if test="${empty pageResult.list}">
		              		<tr>
	                    		<td colspan="2" align="center">暂无我的消息</td>
	                    	</tr>
		               </c:if>
		               <c:if test="${!empty pageResult.list}">
			               <tr>
			               		<td colspan="2" align="center"><div class="account-page">${pageBar}</div></td>
			               </tr> 
		               </c:if>                       
	                </table>
	                
               </form>
          </div>
          	
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
