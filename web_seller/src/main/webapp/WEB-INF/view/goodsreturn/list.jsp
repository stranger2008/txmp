<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>退换货列表</title>
</head>
<body>

<div class="position">退换货列表</div>

<sf:form action="goodsreturn/list.action" id="orderForm" modelAttribute="goodsreturnQueryForm">

			<table class="search" cellpadding="0" cellspacing="10" width="100%">
                <tbody>
                    <tr>
                        <td>
                        	<c:forEach items="${stateCountList}" var="item">
                        		<a href="<%=basePath%>goodsreturn/list.action?info_state=${item.info_state}">${item.info_state_name}(${item.ct})</a>&nbsp;
                        	</c:forEach>
						</td>
                    </tr>
                    <tr>
                        <td>
                        	<sf:input path="keywords" placeholder="商品名称、订单号" maxlength="50" cssClass="w400"/>
                        	<input name="sub" value="搜索" class="sear-btn" type="submit"/>
						</td>
                    </tr>
                </tbody>
            </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th width="10%">
						<sf:select path="biz_type" items="${biztypeMap}" onchange="document.getElementById('orderForm').submit();"/>
					</th>
                	<th width="50%">商品信息</th>
                	<th width="10%">
                		<sf:select path="sear_days"  onchange="document.getElementById('orderForm').submit();">
                			<sf:option value="">显示全部</sf:option>
							<sf:options items="${seardaysMap}"></sf:options>
                		</sf:select>
                	</th>
                	<th width="20%">
						<sf:select path="info_state" items="${infoStateMap}" onchange="document.getElementById('orderForm').submit();"/>
					</th>
                	<th width="10%">操作</th>
                </tr>
                
                <c:if test="${!empty pageResult.list}">
                
                <c:forEach items="${pageResult.list}" var="item">
                
                <tr class="tab_td">
                	<td>
                		${item.biz_type_name }
                	</td>
                	<td>
                			<table width="100%">
                				<tr>
                					<td style="border:0px; width:100px;">
                						<c:if test="${!empty item.goods_img_path}">
			                        		<img src="<h:imgSubstr imgpath="${item.goods_img_path}"/>" />
			                       		</c:if>
                					</td>
                					<td style="border:0px;">
                						订单号：${item.order_id}&nbsp;<a href="<%=basePath%>order/detail-${item.order_id}.action" target="_blank">查看订单</a><br/>
                						商品名称：${item.goods_name}
                					</td>
                				</tr>
                			</table>
                	</td>
                	<td>
                		<fmt:formatDate value="${item.in_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                	</td>
                	<td>
                		${item.info_state_name}
                	</td>
                	<td>
                		<a href="<%=basePath%>goodsreturn/detail-${item.trade_id}.action">查看详细</a><br/>
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
	            <center>无${biz_type_name }记录</center>
	        </c:if>
            
</sf:form>
            
</body>
</html>
