<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/calendar/WdatePicker.js"></script>
<title>举报管理</title>
</head>
<body>

<div class="position">举报管理</div>

<sf:form action="buyer_report/list.action"   modelAttribute="buyer_reportQueryForm">

			<table class="search" cellpadding="0" cellspacing="10" width="100%">
                <tbody>                 
                     <tr>
                        <td>
                       		<span>举报状态：</span>
                        	<sf:select path="status">
								<sf:option value="">请选择</sf:option>
								<sf:option value="0">未处理</sf:option>
								<sf:option value="1">已处理</sf:option>
							</sf:select>
						</td>
						<td>
							<span>举报日期：</span>
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
                    <th width="30%" align="center">相关宝贝</th>
                    <th width="10%" align="center">举报类型</th>
                    <th  align="center">描述</th>
                    <th width="15%"align="center">处理结果</th>
                    <th width="6%"align="center">状态</th>
                    <th width="10%" align="center">举报时间</th>
                    <th width="5%" align="center">操作</th>
                 </tr>
                
               <c:if test="${!empty pageResult.list}">
                
                <c:forEach items="${pageResult.list}" var="item">
	                <tr class="tab_td">
	                	<td><a href="<%=basePath%>goods/detail/${item.goods_id}.action">${item.goods_name}</a></td>
	                	<td>${item.report_type_s}</td>
	                	<td>${item.r_desc}</td>
	                	<td>${item.remark }</td>
	                	<td>
	                       <c:if test="${item.status==0}">未处理</c:if>
	                       <c:if test="${item.status==1}">已处理</c:if>
	                    </td>
	                    <td>
	                    	<fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd"/>
	                    </td>
	                	<td align="center"><a href="<%=basePath%>buyer_report/view-${item.report_id}.action" >查看</a></td>
	                </tr>
                </c:forEach>
                
              </c:if>
                
            </table>
            <div class="page">${pageBar}</div>
            
            <c:if test="${empty pageResult.list}">
	            <center>无举报信息</center>
	        </c:if>
            
</sf:form>
            
</body>
</html>
