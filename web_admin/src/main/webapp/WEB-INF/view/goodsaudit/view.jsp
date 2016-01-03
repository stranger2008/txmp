<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商品信息</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">商品信息</div>

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	
	<tr>
		<td class="addtab_tit">所属分类<span>&nbsp;</span></td>
		<td>
			${goods.cat_names }
		</td>
	</tr>
	
	<c:forEach items="${infoattrs }" var="iamap">
	<tr>
		<td class="addtab_tit">${iamap.value[0].attr_name }<span>&nbsp;</span></td>
		<td>
			<c:forEach items="${iamap.value }" var="infoattr" varStatus="vs">
				<c:if test="${vs.index != 0 }">,&nbsp;</c:if>
				${infoattr.attr_value }
			</c:forEach>
		</td>
	</tr>
	</c:forEach>
	
	<tr>
		<td class="addtab_tit">商品名称<span>&nbsp;</span></td>
		<td>
			${goods.goods_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商品编号<span>&nbsp;</span></td>
		<td>
			${goods.goods_no }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商品品牌<span>&nbsp;</span></td>
		<td>
			${goods.brand_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">是否为虚拟商品<span>&nbsp;</span></td>
		<td>
			<c:if test="${goods.is_virtual == 0 }">是</c:if>
			<c:if test="${goods.is_virtual == 1 }">否</c:if>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商家名称<span>&nbsp;</span></td>
		<td>
			${goods.cust_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">销售价<span>&nbsp;</span></td>
		<td>
			${goods.sale_price }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">市场价<span>&nbsp;</span></td>
		<td>
			${goods.market_price }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">运费<span>&nbsp;</span></td>
		<td>
			<c:if test="${goods.is_ship == 0 }">免运费</c:if>
			<c:if test="${goods.is_ship == 1 }">${goods.ship_price }</c:if>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">上架时间<span>&nbsp;</span></td>
		<td>
			<fmt:formatDate value="${goods.up_date }" pattern="yyyy-MM-dd" />
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">下架时间<span>&nbsp;</span></td>
		<td>
			<fmt:formatDate value="${goods.down_date }" pattern="yyyy-MM-dd" />
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">当前库存<span>&nbsp;</span></td>
		<td>
			${goods.now_stock }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">提醒库存<span>&nbsp;</span></td>
		<td>
			${goods.warn_stock }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商品关键字<span>&nbsp;</span></td>
		<td>
			${goods.goods_wd }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商品简介<span>&nbsp;</span></td>
		<td>
			${goods.goods_desc }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商品图片<span>&nbsp;</span></td>
		<td>
			<c:forEach items="${imgsmaster }" var="img2" varStatus="var2">
				<c:forEach items="${imgs }" var="img1" varStatus="var1">
					<c:if test="${var2.index==var1.index}">
					<a href="${img2}" target="_blank"><img src="${img1 }" /></a>
					</c:if>
				</c:forEach>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商品详细描述<span>&nbsp;</span></td>
		<td>
			${goods.goods_detail }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商家备注<span>&nbsp;</span></td>
		<td>
			${goods.busi_remark }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">审核状态<span>&nbsp;</span></td>
		<td>
			<c:if test="${goods.info_state == '0'}">未审核</c:if>
			<c:if test="${goods.info_state == '1'}">正常</c:if>
			<c:if test="${goods.info_state == '2'}">审核不通过</c:if>
			<c:if test="${goods.info_state == '3'}">禁用</c:if>
		</td>
	</tr>
	
	<c:if test="${goods.info_state == '2'}">
		<td class="addtab_tit">拒绝原因<span>&nbsp;</span></td>
		<td>
			${goods.no_reason }
		</td>
	</c:if>
</table>

</body>
</html>

