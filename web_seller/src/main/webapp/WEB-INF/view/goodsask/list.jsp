<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品咨询列表</title>
</head>
<body>

<div class="position">商品咨询</div>

<sf:form action="goodsask/list.action" id="goodsaskForm" modelAttribute="goodsaskQueryForm">

			<table class="search" cellpadding="0" cellspacing="10" width="100%" style="margin:0px;padding:0px;">
                <tbody>
                    <tr>
                        <td class="sear-tit">
							<sf:select path="sear_days" items="${seardaysMap}" onchange="document.getElementById('goodsaskForm').submit();"/>
						</td>
                    </tr>
                </tbody>
            </table>
            
            <c:if test="${!empty pageResult.list}">
            
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th>咨询内容</th>
                	<th>回复</th>
                </tr>
                
                <c:forEach items="${pageResult.list}" var="item">
                
                <tr class="tab_td">
                	<td width="60%">
                		咨询人：${item.user_name}<br/>
                		商品：${item.goods_name}<br/>
                		内容：${item.c_content}<br/>
                		时间：${item.c_date}<br/>
                	</td>
                	<td>
                		<c:if test="${!empty item.re_content}">
                		内容：${item.re_content}<br/>
                		时间：${item.re_date}<br/>
                		</c:if>
                		<c:if test="${empty item.re_content}">
                		未回复<br/>
                		</c:if>
                		<a href="<%=basePath%>goodsask/reply-${item.trade_id}.action">回复</a>
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
