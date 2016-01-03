<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/calendar/WdatePicker.js"></script>
<title>举报管理</title>
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

<div class="position">举报管理</div>
	<div style="min-height:200px;overflow:hidden;">
	   	<div style="padding:10px 10px;background-color:#f5f5f5;border:1px solid #f5f5f5;">举报详情</div>
	       <table width="100%" border="0" class="account-repair-head" cellpadding="5" cellspacing="0" style="padding:10px 0px;">
	           <tr>
                  <td colspan="3" style="border:1px solid #ddd;padding:5px 0px 5px 10px;">相关宝贝：
                  	<a href="<%=basePath%>goods/${buyer_report.goods_id}.action" target="_blank" class="account-order-num"> ${goods_name}</a>
                  </td>
              </tr>
              <tr>
                  <td style="border:1px solid #ddd;padding-left:10px;border-top:none;">类型：${r_type}</td>
                  <td width="15%" style="border:1px solid #ddd;padding:5px 0px 5px 10px;border-top:none;border-left:none;">状态：
                     <c:if test="${buyer_report.status==0}">未处理</c:if>
                     <c:if test="${buyer_report.status==1}">已处理</c:if>
                  </td>
                  <td  style="border:1px solid #ddd;padding-left:10px;border-top:none;border-left:none;">举报日期：
                  	<fmt:formatDate value="${buyer_report.in_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                  </td>
              </tr>
              <tr>
               <td colspan="3" style="border:1px solid #ddd;padding:5px 0px 5px 10px;border-top:none;overflow:hidden;">举报详情：${buyer_report.r_desc}
               	<div id="imgDiv" style="margin-top:10px;"></div>
               </td>
           </tr>
           <c:if test="${!empty buyer_report.remark}">
           <tr>
               <td colspan="3" style="border:1px solid #ddd;padding:5px 0px 5px 10px;border-top:none;overflow:hidden;">处理结果：${buyer_report.remark}</td>
           </tr>
           </c:if>
              <tr>
               <td colspan="3" align="right" height="60px;">
               	<a href="<%=basePath %>buyer_report/list.action" style="border:1px solid #ddd;padding:5px 10px;">返回</a>
               </td>
              </tr>
	       </table>
	   </div>	  
           
</body>
</html>
