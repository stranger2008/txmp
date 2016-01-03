<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>订单列表</title>
</head>
<body>

<div class="position">订单列表</div>

<sf:form action="order/list.action" id="orderForm" modelAttribute="goodsorderQueryForm">

			<table class="search" cellpadding="0" cellspacing="10" width="100%">
                <tbody>
                    <tr>
                        <td>
                        	<c:forEach items="${orderCountList}" var="item">
                        		<a href="<%=basePath%>order/list.action?order_state=${item.order_state}">${item.order_state_name}(${item.ct})</a>&nbsp;
                        	</c:forEach>
						</td>
                    </tr>
                    <tr>
                        <td>
                        	<sf:input path="keywords" placeholder="商品名称、订单编号" maxlength="50" cssClass="w400"/>
                        	<input name="sub" value="搜索" class="sear-btn" type="submit"/>
						</td>
                    </tr>
                </tbody>
            </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th width="50%">订单信息</th>
                	<th width="10%">订单金额</th>
                	<th width="10%">
                		<sf:select path="sear_days" items="${seardaysMap}" onchange="document.getElementById('orderForm').submit();"/>
                	</th>
                	<th width="20%">
						<sf:select path="order_state" items="${orderStateMap}" onchange="document.getElementById('orderForm').submit();"/>
					</th>
                	<th width="10%">操作</th>
                </tr>
                
                <c:if test="${!empty pageResult.list}">
                
                <c:forEach items="${pageResult.list}" var="item">
                
                <tr class="tab_td">
                	<td colspan="6">
                		订单编号：<a href="<%=basePath%>order/detail-${item.order_id}.action">${item.order_id}</a>
                	</td>
                </tr>
                <tr class="tab_td">
                	<td>
                		<c:forEach items="${item.orderdetails}" var="orderdetail">
                			<table width="100%">
                				<tr>
                					<td style="border:0px;">
                						<c:if test="${!empty orderdetail.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${orderdetail.img_path}"/>" onload="DrawImage(this,90,90)"/>
			                       		</c:if>
                					</td>
                					<td style="border:0px;">
                						${orderdetail.goods_name}<br/>
                						数量：${orderdetail.order_num}&nbsp;单价：￥${orderdetail.order_price}
                					</td>
                				</tr>
                			</table>
					 		
					 	</c:forEach>
                	</td>
                	<td>
                		￥${item.tatal_amount}
                	</td>
                	<td>
                		${item.order_time}
                	</td>
                	<td>
                		${item.order_state_name}
                	</td>
                	<td>
                		<a href="<%=basePath%>order/detail-${item.order_id}.action">查看详细</a><br/>
                		<c:if test="${item.order_state==1}">
                			<button class="tab_btnlist" type="button" onclick="location.href='<%=basePath%>order/deliver-${item.order_id }.action';"/>发货</button>
                		</c:if>
                	</td>
                	
                </tr>
                
                </c:forEach>
                
                </c:if>
                
                
            </table>
            <div class="page">${pageBar}</div>
            
            <c:if test="${empty pageResult.list}">
	            <center>无订单信息</center>
	        </c:if>
            
</sf:form>
            
</body>
</html>
