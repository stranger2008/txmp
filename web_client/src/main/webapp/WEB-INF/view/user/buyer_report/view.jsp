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
		$(function(){
			var imgPath = '${buyer_report.img_evidence }';
			if(imgPath!=''){
				var imgDiv = $("#imgDiv");
				var imgPaths = imgPath.split(",");
				for(var i =0 ;i<imgPaths.length ;i++){
					imgDiv.append("<li><img width='100' height='100' src='"+imgPaths[i]+"' style='float:left;margin-right:5px;'/></li>");
				}
			}
		});    		
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
		
        <div class="user-info fr">
            <div class="store">
                <div class="store-list" id="store-show">
                    <div style="min-height:200px;overflow:hidden;">
                    	<div style="padding:10px 20px;background-color:#f5f5f5;border:1px solid #f5f5f5;">举报详情</div>
                        <table width="100%" border="0" class="account-repair-head" cellpadding="5" cellspacing="0" style="padding:10px 20px;">
                            <tr>
                                <td  width="30%" style="border:1px solid #ddd;padding-left:10px;">
                                	被举报商家：<a href="<%=basePath%>shop/${cust_id}.action" target="_blank" class="account-order-num"> ${cust_name }</a>
                                </td>
                                <td colspan="2" style="border:1px solid #ddd;padding-left:10px;border-left:none;">相关宝贝：
                                	<a href="<%=basePath%>goods/${buyer_report.goods_id}.action" target="_blank" class="account-order-num"> ${goods_name}</a>
                                </td>
                            </tr>
                            <tr>
                                <td style="border:1px solid #ddd;padding-left:10px;border-top:none;">类型：${r_type}</td>
                                <td width="10%" style="border:1px solid #ddd;padding-left:10px;border-top:none;border-left:none;">状态：
                                   <c:if test="${buyer_report.status==0}">未处理</c:if>
                                   <c:if test="${buyer_report.status==1}">已处理</c:if>
                                </td>
                                <td  style="border:1px solid #ddd;padding-left:10px;border-top:none;border-left:none;">举报日期：
                                	<fmt:formatDate value="${buyer_report.in_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                            </tr>
                            <tr>
	                            <td colspan="3" style="border:1px solid #ddd;padding-left:10px;border-top:none;overflow:hidden;">举报详情：${buyer_report.r_desc}
	                            	<div id="imgDiv" style="margin-top:10px;"></div>
	                            </td>
	                        </tr>
	                        <c:if test="${!empty buyer_report.remark}">
	                        <tr>
	                            <td colspan="3" style="border:1px solid #ddd;padding-left:10px;border-top:none;overflow:hidden;">处理结果：${buyer_report.remark}</td>
	                        </tr>
	                        </c:if>
                            <tr>
	                            <td colspan="3" align="right">
	                            	<a href="<%=basePath %>user/buyer_report.action" style="border:1px solid #ddd;padding:8px 15px;">返回</a>
	                            </td>
                            </tr>
                        </table>
                    </div>
                  </div>   
            </div>
           
        </div>
       
	</div>
</div>

<div class="clearfix"></div>


        
  <!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
