<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>投诉管理</title>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
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
            <div class="add-count">
                <h1>投诉管理</h1>
				<div id="ImportBox">
				<sf:form method="post" action="user/add_complaint.action" modelAttribute="buyer_complaint">
				         <table width="100%" border="0" cellspacing="0" cellpadding="0">
					   		<tbody>
					   	  	<tr>
				               <td class="Su">订单编号：</td>
				               <td>${order_id} <sf:hidden path="order_id" value="${order_id}"/></td>
				            </tr>
				            <tr>
				               <td class="Su"><font color="#ff4545">*</font>类型：</td>
				               <td><sf:select path="com_type" items="${compTypeMap}"/>&nbsp;&nbsp;<em><sf:errors path="com_type" cssClass="error" cssStyle="color:#ff4545;"/></em></td>
				            </tr>
				            <tr>
				                <td class="Su"><font color="#ff4545">*</font>投诉原因：</td>
				                <td><sf:textarea path="reason" style="height:100px;width:300px;" maxlength="500"/>&nbsp;&nbsp;<em><sf:errors path="reason" cssClass="error" cssStyle="color:#ff4545;"/></em></td>
				            </tr>
				             <tr>
				                <td class="Su">用户凭证：</td>
				                <td>
					                <input type="hidden" name="imgNumLimit" id="imgNumLimit" value="5"/>
									<sf:hidden path="img_evidence" />
									<input id="file_adv" name="upload_file" type="file" />&nbsp;&nbsp;
									<em><sf:errors path="img_evidence" cssClass="error" cssStyle="color:#ff4545;"/></em>
									<div id="fileQueue"></div>
									<div class="img-lst"><ul id="imgView"></ul></div>
									<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
							        <script>
							        	$(function(){
							        		uploadImgComponent('<%=basePath%>','imgNumLimit','file_adv','fileQueue','img_evidence','imgView','buyer_complaint');
							        	})
							        </script>
				                </td>
				            </tr>
				            <tr>
				                <td class="Su"></td><td ><input value="提交" type="submit" id="submitForm" style="margin:20px 0 10px 10px;"/></td>
				            </tr>
				            </tbody>
				        </table>
				</sf:form>
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
