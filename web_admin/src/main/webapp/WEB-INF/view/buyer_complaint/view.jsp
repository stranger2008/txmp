<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/calendar/WdatePicker.js"></script>
<title>投诉记录详情</title>
<script type="text/javascript">	
    	//	
		$(function () {
		    $("#complaintMegSubmit").click(function () {
		    	var remark = $("#remark").val().replace(/\s+/g,"");
			    if(remark == ""){	
			    	alert("运营商处理信息不能为空");
			    	document.getElementById("remark").focus();
			    }
			    else if(remark.length<1 || remark.length>500){	
			    	alert("备注信息字数请控制在1-500字");
			    	document.getElementById("remark").focus();
			    }
			    else{
		    		document.getElementById("complaintForm").submit();
		    	}
		    });
		})
		//
		 function solveComplaint(com_id) {
       			if(window.confirm("您确认投诉已解决？")){
						window.location.href='<%=basePath%>buyer_complaint/solveComplaint.action?com_id='+com_id+'&remark=0';
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

<div class="position">投诉记录详情</div>
<div class="clearfix"></div>
      <table width="100%" border="0" class="account-repair-head" cellpadding="5" cellspacing="0" style="padding:10px 0px;">
          <tr height="35px;">
            <td width="20%" style="border:1px solid #ddd;padding-left:10px;">涉及订单：${buyer_complaint.order_id}</td>
            <td width="15%" style="border:1px solid #ddd;padding-left:10px;border-left:none;">类型：${comp_type}</td>
            <td width="15%" style="border:1px solid #ddd;padding-left:10px;border-left:none;">投诉方：${user_name}</td>
            <td width="15%" style="border:1px solid #ddd;padding-left:10px;border-left:none;">涉及商家：${cust_name}</td>
            <td width="15%" style="border:1px solid #ddd;padding-left:10px;border-left:none;">投诉状态：
               <c:if test="${buyer_complaint.status==0}">商家处理中</c:if>
               <c:if test="${buyer_complaint.status==2}">运营商处理中</c:if>
               <c:if test="${buyer_complaint.status==1}">已处理</c:if>
            </td>
            <td  style="border:1px solid #ddd;padding-left:10px;border-left:none;">投诉日期：
            <fmt:formatDate value="${buyer_complaint.in_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
        </tr>
        <tr height="35px;" style="line-height:20px;">
         <td colspan="6" style="border:1px solid #ddd;padding-left:10px;border-top:none;border-bottom:none;overflow:hidden;">投诉原因：${buyer_complaint.reason}</td>
        </tr>
        <tr>
         <td colspan="6" style="border:1px solid #ddd;padding:0 0 10px 10px;border-top:none;overflow:hidden;">
         	<div id="imgDiv" style="margin-top:10px;"></div>
         </td>
        </tr>
     <c:if test="${!empty buyer_complaint.remark}">
     <tr height="35px;">
         <td colspan="6" style="border:1px solid #ddd;padding-left:10px;border-top:none;overflow:hidden;">运营商处理结果：${buyer_complaint.remark}</td>
     </tr>
     </c:if>
        <tr height="50px;">
        <td colspan="6">
        
	        <c:if test="${buyer_complaint.status ==2  && empty buyer_complaint.remark}">
	           <div style="padding:10px 0px;">
	           	<sf:form  action="buyer_complaint/addComplaint.action" method="post" id="complaintForm" modelAttribute="buyer_complaint_s">
	           		<sf:hidden path="complaint_id" value="${buyer_complaint.complaint_id}"/>
	            	<sf:textarea path="remark" rows="3" cols="20" placeholder="运营商处理信息" cssStyle="width:400px;height:60px;float:left;" maxlength="500" />
	            	<a href="javascript:void(0)" id="complaintMegSubmit" style="border:1px solid #ddd;padding:5px 15px;float:left;margin:10px 0 0 20px;">提交</a>
	           	</sf:form>
	           </div>
	        </c:if>
      		
           <a href="<%=basePath %>buyer_complaint/index.action" style="border:1px solid #ddd;padding:5px 15px;float:right;">返回</a>
           		<c:if test="${!empty buyer_complaint.remark}">
           		<a href="javascript:solveComplaint('${buyer_complaint.complaint_id}');" style="border:1px solid #ddd;padding:5px 15px;float:right;margin-right:10px;">已解决</a>
           </c:if>
         </td>
        </tr>
    </table>
	
	 <!-- 回复留言begin -->
	 			<c:if test="${!empty megList}">
                     <div style="min-height:200px;margin-bottom:50px;overflow:hidden;">
                    	<div style="padding:10px 20px;background-color:#f5f5f5;border:1px solid #f5f5f5;">留言信息</div>
                        <div style="margin-top:10px;padding:0px 20px;">
                        	<c:forEach items="${megList}" var="item">
                        		<c:if test="${item.u_type==0}">
	                        		<div style="margin:5px 10px 5px 0;">
		                         	<div style="margin-bottom:5px;"><fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		                        	<div style="float:left;">${user_name} > </div>
		                        	<div style="border:1px solid #ddd;padding:8px 15px;width:400px;float:left;overflow:hidden;line-height:15px;">${item.content}</div>
		                        	<div class="clearfix"></div>
		                        	</div>
	                        	</c:if>
	                        	<c:if test="${item.u_type==1}">
	                        	<div style="margin:5px 10px 5px 0;">
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
                  </c:if>
         <!-- 回复留言end-->

            
</body>
</html>

