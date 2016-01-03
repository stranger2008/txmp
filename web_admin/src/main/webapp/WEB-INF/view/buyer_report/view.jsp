<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/calendar/WdatePicker.js"></script>
<title>举报记录详情</title>
<script type="text/javascript">	
    	//	
		$(function () {
		    $("#complaintMegSubmit").click(function () {
		    	var remark = $("#remark").val().replace(/\s+/g,"");
			    if(remark == ""){	
			    	alert("运营商处理结果不能为空");
			    	document.getElementById("remark").focus();
			    }
			    else if(remark.length<1 || remark.length>500){	
			    	alert("运营商处理信息字数请控制在1-500字");
			    	document.getElementById("remark").focus();
			    }
			    else{
		    		document.getElementById("reportForm").submit();
		    	}
		    });
		})
		//
		    
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

<div class="position">举报记录详情</div>
<div class="clearfix"></div>
      <table width="100%" border="0" class="account-repair-head" cellpadding="5" cellspacing="0" style="padding:10px 20px;">
          <tr >
              <td  width="30%" style="border:1px solid #ddd;padding:10px 0 10px 10px;">
              	被举报商家：<a href="<%=basePath%>shopconfig/view.action?cust_id=${cust_id}" target="_blank" class="account-order-num"> ${cust_name }</a>
              </td>
              <td colspan="2" style="border:1px solid #ddd;border-left:none;padding:10px 0 10px 10px;">相关宝贝：
              	<a href="<%=basePath%>goods/view.action?id=${buyer_report.goods_id}" target="_blank" class="account-order-num"> ${goods_name}</a>
              </td>
          </tr>
          <tr>
              <td style="border:1px solid #ddd;border-top:none;padding:10px 0 10px 10px;">类型：${r_type}</td>
              <td width="10%" style="border:1px solid #ddd;border-top:none;border-left:none;padding:10px 0 10px 10px;">状态：
                 <c:if test="${buyer_report.status==0}">未处理</c:if>
                 <c:if test="${buyer_report.status==1}">已处理</c:if>
              </td>
              <td  style="border:1px solid #ddd;border-top:none;border-left:none;padding:10px 0 10px 10px;">举报日期：
              	<fmt:formatDate value="${buyer_report.in_date}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
          </tr>
          <tr>
           <td colspan="3" style="border:1px solid #ddd;border-top:none;overflow:hidden;padding:10px 0 10px 10px;">举报详情：${buyer_report.r_desc}
           	<div id="imgDiv" style="margin-top:10px;"></div>
           </td>
       </tr>
       <c:if test="${!empty buyer_report.remark}">
       <tr>
           <td colspan="3" style="border:1px solid #ddd;border-top:none;overflow:hidden;padding:10px 0 10px 10px;">处理结果：${buyer_report.remark}</td>
       </tr>
       </c:if>
         <tr height="50px;">
        <td colspan="3">
        
	        <c:if test="${empty buyer_report.remark}">
	           <div style="padding:10px 0px;">
	           	<sf:form  action="buyer_report/view.action" method="post" id="reportForm" modelAttribute="buyer_report_s">
	           		<sf:hidden path="report_id" value="${buyer_report.report_id}"/>
	            	<sf:textarea path="remark" rows="3" cols="20" placeholder="运营商处理信息" cssStyle="width:400px;height:60px;float:left;" maxlength="500" />
	            	<a href="javascript:void(0)" id="complaintMegSubmit" style="border:1px solid #ddd;padding:5px 15px;float:left;margin:10px 0 0 20px;">提交</a>
	           	</sf:form>
	           </div>
	        </c:if>
      		
           <a href="<%=basePath %>buyer_report/index.action" style="border:1px solid #ddd;padding:5px 15px;float:right;">返回</a>
         </td>
        </tr>
      </table>
	
</body>
</html>

