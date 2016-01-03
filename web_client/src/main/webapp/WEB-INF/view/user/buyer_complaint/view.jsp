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
		    $("#complaintMegSubmit").click(function () {
		    	var content = $("#content").val().replace(/\s+/g,"");
		    	if(content == ""){	
			    	alert("回复留言不能为空");
			    	document.getElementById("content").focus();
			    }
			    else if(content.length<1 || content.length>500){	
			    	alert("回复留言字数请控制在1-500字");
			    	document.getElementById("content").focus();
			    }
			    else{
		    		document.getElementById("complaintMegForm").submit();
		    	}
		    });
		})
		//
		 function solveComplaint(com_id) {
       			if(window.confirm("您确认投诉已解决？")){
						window.location.href='<%=basePath%>user/solveAndAppComplaint.action?com_id='+com_id+'&remark=0';
				}
    		};
    		
   		 function appOperators(com_id) {
      			if(window.confirm("您确认申请运营商介入？")){
					window.location.href='<%=basePath%>user/solveAndAppComplaint.action?com_id='+com_id+'&remark=1';
			}
   		};
		    
		$(function(){
			var imgPath = '${buyer_complaint.img_evidence }';
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
                    	<div style="padding:10px 20px;background-color:#f5f5f5;border:1px solid #f5f5f5;">投诉详情</div>
                        <table width="100%" border="0" class="account-repair-head" cellpadding="5" cellspacing="0" style="padding:10px 20px;">
                            <tr>
                                <td width="20%" style="border:1px solid #ddd;padding-left:10px;">涉及订单：${buyer_complaint.order_id}</td>
                                <td width="15%" style="border:1px solid #ddd;padding-left:10px;border-left:none;">类型：${comp_type}</td>
                                <td width="23%" style="border:1px solid #ddd;padding-left:10px;border-left:none;">涉及商家：${cust_name}</td>
                                <td width="20%" style="border:1px solid #ddd;padding-left:10px;border-left:none;">投诉状态：
                                   <c:if test="${buyer_complaint.status==0}">商家处理中</c:if>
                                   <c:if test="${buyer_complaint.status==2}">运营商处理中</c:if>
                                   <c:if test="${buyer_complaint.status==1}">已处理</c:if>
                                </td>
                                <td  style="border:1px solid #ddd;padding-left:10px;border-left:none;">投诉日期：
                                <fmt:formatDate value="${buyer_complaint.in_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                            </tr>
                            <tr>
	                            <td colspan="5" style="border:1px solid #ddd;padding-left:10px;border-top:none;overflow:hidden;">投诉原因：${buyer_complaint.reason}
	                            	<div id="imgDiv" style="margin-top:10px;"></div>
	                            </td>
	                        </tr>
	                        <c:if test="${!empty buyer_complaint.remark}">
	                        <tr>
	                            <td colspan="5" style="border:1px solid #ddd;padding-left:10px;border-top:none;overflow:hidden;">运营商处理结果：${buyer_complaint.remark}</td>
	                        </tr>
	                        </c:if>
                            <tr>
	                            <td colspan="5" align="right">
	                             	<c:if test="${buyer_complaint.status ==0}">
	                            	<a href="javascript:appOperators('${buyer_complaint.complaint_id}');" style="border:1px solid #ddd;padding:8px 15px;">申请运营商介入</a>
	                            	</c:if>
	                            	<c:if test="${buyer_complaint.status ==0 || buyer_complaint.status ==2}">
	                            <a href="javascript:solveComplaint('${buyer_complaint.complaint_id}');" style="border:1px solid #ddd;padding:8px 15px;">已解决</a>
	                            </c:if>
	                            <a href="<%=basePath %>user/buyer_complaint.action" style="border:1px solid #ddd;padding:8px 15px;">返回</a>
	                            </td>
                            </tr>
                        </table>
                    </div>
                    <!-- 回复留言begin -->
                     <div style="min-height:200px;margin-bottom:50px;overflow:hidden;">
                    	<div style="padding:10px 20px;background-color:#f5f5f5;border:1px solid #f5f5f5;">回复留言</div>
                    	
						<c:if test="${buyer_complaint.status ==0 || buyer_complaint.status ==2}">
                        <div style="padding:10px 20px;">
                        	<sf:form  action="user/add_complaint_meg.action" method="post" id="complaintMegForm" modelAttribute="buyer_complaint_meg">
                        		<sf:hidden path="com_id" value="${buyer_complaint.complaint_id}"/>
	                        	<sf:textarea path="content" rows="3" cols="20" cssStyle="width:500px;height:100px;float:left;" maxlength="500" />
	                        	<a href="javascript:void(0)" id="complaintMegSubmit" style="border:1px solid #ddd;padding:8px 15px;float:left;margin:40px 0 0 20px;">发送</a>
                        	</sf:form>
                        </div>
                        </c:if>
                        
                        <div class="clearfix"></div>
                        <div style="margin-top:10px;padding:10px 20px;">
                        	<c:forEach items="${megList}" var="item">
                        		<c:if test="${item.u_type==0}">
	                        		<div style="margin:10px 10px 10px 0;">
		                         	<div style="margin-bottom:5px;"><fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		                        	<div style="float:left;">我的留言 > </div>
		                        	<div style="border:1px solid #ddd;padding:8px 15px;width:400px;float:left;overflow:hidden;line-height:15px;">${item.content}</div>
		                        	<div class="clearfix"></div>
		                        	</div>
	                        	</c:if>
	                        	<c:if test="${item.u_type==1}">
	                        	<div style="margin:10px 10px 10px 0;">
		                        	<div style="float:right;margin-bottom:5px;"><fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		                        	<div class="clearfix"></div>
		                        	<div style="float:right;"> < ${cust_name}</div>
		                        	<div style="border:1px solid #ddd;padding:8px 15px;width:400px;float:right;overflow:hidden;line-height:15px;">${item.content}</div>
		                        	<div class="clearfix"></div>
	                        	</div>
                        		</c:if>
                        	</c:forEach>
                        </div>
                      
                    </div>
                  	<!-- 回复留言end-->
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
