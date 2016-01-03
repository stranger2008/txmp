<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
	<title>投诉管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <script type="text/javascript">	
    	//
		$(function () {
		    $("#searchInfoBt").click(function () {
		    	document.getElementById("complaintForm").submit();
		    });
		})
		//
	</script>
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
		
		<sf:form action="user/buyer_complaint.action" id="complaintForm" modelAttribute="buyer_complaintQueryForm">
		
        <div class="user-info fr">
            <div class="store">
                <h1 class="store-title">投诉管理</h1>
              
                <p style="float:right;margin:0 15px 5px 0;">
                	<sf:input path="order_id" cssClass="my-o-s-input" placeholder="订单编号"/>
                    <a class="my-o-s-go" id="searchInfoBt" href="javascript:void(0);">查询</a>
                </p>
                <div class="store-list" id="store-show">
                    <div>
                         <table cellspacing="0" cellpadding="0" border="0" width="100%" class="account-cust-msg">
                            <tr bgcolor="#f5f5f5">
                                <td width="15%" align="center">订单编号</td>
                                <td width="16%" align="center">类型</td>
                                <td width="15%" align="center">被投诉方</td>
                                <td align="center">投诉原因</td>
                                <td width="14%"align="center">投诉状态</td>
                                <td width="14%" align="center">申请时间</td>
                                <td width="8%" align="center">操作</td>
                            </tr>
                        	<c:forEach items="${pageResult.list}" var="item">
                        	<tr>
                                <td align="center" style="padding:15px 0px;">
                                	<a href="<%=basePath%>user/order-${item.order_id }.action" class="account-order-num"> ${item.order_id }</a>
                                </td>
                                <td align="center" style="padding:15px 0px;">${item.com_type_s}</td>
                                <td align="center" style="padding:15px 0px;">${item.cust_name}</td>
                                <td align="center" style="padding:15px 0px;">${item.reason }</td>
                                <td align="center" style="padding:15px 0px;">
	                               <c:if test="${item.status==0}">商家处理中</c:if>
	                               <c:if test="${item.status==2}">运营商处理中</c:if>
                                   <c:if test="${item.status==1}">已处理</c:if>
                                </td>
                                <td align="center" style="padding:15px 0px;">
                                	<fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd"/>
                                </td>
                                <td align="center" style="padding:15px 0px;"><a href="<%=basePath%>user/view_complaint-${item.complaint_id}.action" style="color:#005ea7;">查看</a></td>
                            </tr>
                        	</c:forEach>
                        </table>
                         <div class="page">${pageBar}</div>
                    </div>
                  </div>   
            </div>
           
        </div>
       
        </sf:form>
	</div>
</div>

<div class="clearfix"></div>


        
  <!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
