<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>库存管理</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>
<div class="position">库存管理</div>
<sf:form method="post" action="goods/stock/update.action" modelAttribute="goodsStockHistory" id="goodsStock" >
<sf:hidden path="goodsId" />
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td width="15%" class="addtab_tit">商品名称</td>
		<td width="85%">${sellerGoodsWithBLOBs.goodsName}</td>
	</tr>
	<tr>
		<td class="addtab_tit">当前库存</td>
		<td>${sellerGoodsWithBLOBs.nowStock}</td>
	</tr>
	<tr>
		<td class="addtab_tit">操作类型</td>
		<td>
			<sf:radiobutton path="changeType" value="1" id="changeType1" cssClass="chkbox"/>
			<label for="changeType1" class="lab-txt" >增加库存</label>
			<sf:radiobutton path="changeType" value="0" id="changeType0" cssClass="chkbox"/>
			<label for="changeType0" class="lab-txt" >减少库存</label>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="changeType" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">变更数量<span>*</span></td>
		<td>
			<sf:input path="changeAmount" />
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="changeAmount" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>
</sf:form>
</body>
</html>
