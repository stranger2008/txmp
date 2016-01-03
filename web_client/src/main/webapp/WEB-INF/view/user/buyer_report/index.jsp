<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
	<title>举报管理</title>
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
		    	document.getElementById("reportForm").submit();
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
		
		<sf:form action="user/buyer_report.action" id="reportForm" modelAttribute="buyer_reportQueryForm">
		
        <div class="user-info fr">
            <div class="store">
                <h1 class="store-title">举报管理</h1>
              
                <p style="float:right;margin:0 15px 5px 0;">
                	<sf:input path="cust_name" cssClass="my-o-s-input" placeholder="被举报商家"/>
                    <a class="my-o-s-go" id="searchInfoBt" href="javascript:void(0);">查询</a>
                </p>
                <div class="store-list" id="store-show">
                    <div>
                         <table cellspacing="0" cellpadding="0" border="0" width="100%" class="account-cust-msg">
                            <tr bgcolor="#f5f5f5">
                                <td width="10%" align="center">被举报商家</td>
                                <td width="20%" align="center">相关宝贝</td>
                                <td width="10%" align="center">举报类型</td>
                                <td  align="center">描述</td>
                                <td width="15%"align="center">处理结果</td>
                                <td width="8%"align="center">状态</td>
                                <td width="10%" align="center">举报时间</td>
                                <td width="8%" align="center">操作</td>
                            </tr>
                        	<c:forEach items="${pageResult.list}" var="item">
                        	<tr>
                                <td align="center" style="padding:15px 0px;">
                                	<a href="<%=basePath%>shop/${item.cust_id}.action" target="_blank" class="account-order-num"> ${item.cust_name }</a>
                                </td>
                                <td align="center" style="padding:15px 0px;">
                                	<a href="<%=basePath%>goods/${item.goods_id}.action" target="_blank" class="account-order-num"> ${item.goods_name }</a>
                                
                                </td>
                                <td align="center" style="padding:15px 0px;">${item.report_type_s}</td>
                                <td align="center" style="padding:15px 0px;">${item.r_desc }</td>
                                <td align="center" style="padding:15px 0px;">${item.remark }</td>
                                <td align="center" style="padding:15px 0px;">
                                	<c:if test="${item.status==0}">未处理</c:if>
	                                <c:if test="${item.status==1}">已处理</c:if>
                                </td>
                                <td align="center" style="padding:15px 0px;">
                                	<fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd"/>
                                </td>
                                <td align="center" style="padding:15px 0px;"><a href="<%=basePath%>buyer_report/view-${item.report_id}.action" style="color:#005ea7;">查看</a></td>
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
