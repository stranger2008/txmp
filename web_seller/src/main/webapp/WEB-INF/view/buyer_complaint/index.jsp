<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/calendar/WdatePicker.js"></script>
<title>投诉管理</title>
</head>
<body>

<div class="position">投诉管理</div>

<sf:form action="buyer_complaint/list.action" id="complaintForm" modelAttribute="buyer_complaintQueryForm">

			<table class="search" cellpadding="0" cellspacing="10" width="100%">
                <tbody>
                    <tr>
                    	<td width="30%">
                       		<span>订单编号：</span><sf:input path="order_id" maxlength="20" cssStyle="width:120px;"/>
						</td>
						<td colspan="2"><span>投诉方：</span><sf:input path="user_name" maxlength="30" cssStyle="width:120px;"/>
						&nbsp;&nbsp;<span>类型：</span><sf:select path="com_type" items="${compTypeMap}"/>
						</td>
                    </tr>
                     <tr>
                        <td>
                       		<span>投诉状态：</span>
                        	<sf:select path="status">
								<sf:option value="">请选择</sf:option>
								<sf:option value="0">商家处理中</sf:option>
								<sf:option value="2">运营商处理中</sf:option>
								<sf:option value="1">已处理</sf:option>
							</sf:select>
						</td>
						<td>
							<span>投诉日期：</span>
							<sf:input path="in_date_begin" id="in_date_begin" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'in_date_end\\',{d:-1})}',readOnly:true})" style="width:100px;"/>
			 					- 
							<sf:input path="in_date_end" id="in_date_end" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'in_date_begin\\',{d:1})}',readOnly:true})" style="width:100px;"/>
						</td>
						<td><input name="sub" value="搜索" class="sear-btn" type="submit"/></td>
                    </tr>
                </tbody>
            </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th width="17%">订单编号</th>
                	<th width="12%">类型</th>
                	<th width="15%">投诉方</th>
                	<th >投诉原因</th>
                	<th width="12%">投诉状态</th>
                	<th width="12%">投诉时间</th>
                	<th width="8%">操作</th>
                </tr>
                
               <c:if test="${!empty pageResult.list}">
                
                <c:forEach items="${pageResult.list}" var="item">
	                <tr class="tab_td">
	                	<td>${item.order_id}</td>
	                	<td>${item.com_type_s}</td>
	                	<td>${item.user_name}</td>
	                	<td>${item.reason }</td>
	                	<td>
	                       <c:if test="${item.status==0}">商家处理中</c:if>
	                       <c:if test="${item.status==2}">运营商处理中</c:if>
	                       <c:if test="${item.status==1}">已处理</c:if>
	                    </td>
	                    <td>
	                    	<fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd"/>
	                    </td>
	                	<td align="center"><a href="<%=basePath%>buyer_complaint/viewComplaint-${item.complaint_id}.action" >查看</a></td>
	                </tr>
                </c:forEach>
                
              </c:if>
                
            </table>
            <div class="page">${pageBar}</div>
            
            <c:if test="${empty pageResult.list}">
	            <center>无投诉信息</center>
	        </c:if>
            
</sf:form>
            
</body>
</html>
