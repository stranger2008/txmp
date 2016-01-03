<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品咨询列表</title>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<style type="text/css">
#c_date_begin,#c_date_end,#re_date_begin,#re_date_end{
	width:85px;
	text-align:left;
}
</style>
</head>
<body>

<div class="position">商品咨询</div>

<sf:form action="goodsask/index.action" id="queryForm" modelAttribute="goodsaskQueryForm">

<div class="searchdiv">
	<ul>
		<li>
			<span>商品名称：</span>
			<sf:input path="goods_name"/>
		</li>
		<li>
			<span>咨询内容：</span>
			<sf:input path="c_content"/>
		</li>
		<li>
			<span>回复内容：</span>
			<sf:input path="re_content"/>
		</li>
		<li>
			<span>咨询时间：</span>
			<sf:input path="c_date_begin" id="c_date_begin" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'c_date_end\\',{d:-1})}'})" />
			-
			<sf:input path="c_date_end" id="c_date_end" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'c_date_begin\\',{d:1})}'})" />
		</li>
		<li>
			<span>回复时间：</span>
			<sf:input path="re_date_begin" id="re_date_begin" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'re_date_end\\',{d:-1})}'})" />
			 - 
			<sf:input path="re_date_end" id="re_date_end" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'re_date_begin\\',{d:1})}'})" />
		</li>
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>
<c:if test="${!empty pageResult.list}">
   <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
       <tr class="tab_th">
       <th width="5%"><input type="checkbox"></th>
       	<th>商品名称</th>
       	<th>咨询内容</th>
       	<th>回复内容</th>
       </tr>
       <c:forEach items="${pageResult.list}" var="item">                
        <tr class="tab_td">
        	<td><input type="checkbox" name="id" value="${item.trade_id}"></td>
        	<td width="30%">
        		${item.goods_name}
        	</td>
        	<td width="40%">	                		
        		${item.c_content}<br/>
        		咨询人：${item.user_name}	时间：<fmt:formatDate value="${item.c_date}" pattern="yyyy-MM-dd HH:mm:ss" />
        	</td>
        	<td>
        		<c:if test="${!empty item.re_content}">
        			${item.re_content}<br/>
        			回复人：${item.cust_name} 时间：<fmt:formatDate value="${item.re_date}" pattern="yyyy-MM-dd HH:mm:ss" />
        		</c:if>
        		<c:if test="${empty item.re_content}">
        			未回复
        		</c:if>
        	</td>
        </tr>                
       </c:forEach>
   </table>
   <div class="page">${pageBar}</div>            
</c:if>           
   <c:if test="${empty pageResult.list}">
    <center>无咨询内容</center>
</c:if>           
</sf:form>           
</body>
</html>
