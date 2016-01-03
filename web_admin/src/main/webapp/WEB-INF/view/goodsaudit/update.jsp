<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改商品</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript">
	//审核不通过状态值
	var GOODS_STATUS_REJECT = '2';
	function showNoReason(type) {
		if(type) {
			$('#no_reason_titile').html('原因<span>*</span>');
			$('#no_reason_ta').html('<textarea name="no_reason" id="no_reason" class="txt4x1"></textarea>');
			$('#no_reason_error').html('');
		} else {
			$('#no_reason_titile').html('');
			$('#no_reason_ta').html('');
			$('#no_reason_error').html('');
		}
	}
	
	$(document).ready(function() {
		$('#submit_btn').click(function() {
			var info_state = $('input[name="info_state"]:checked').val();
			if(info_state == GOODS_STATUS_REJECT) {
				if($('#no_reason').val().trim() == null || $('#no_reason').val().trim() == '' || $('#no_reason').val().length > 100) {
					$('#no_reason_error').html('<span style="color:red;">原因不能为空,且长度不能大于100</span>');
					return;
				}
			}
			$('#info_form').submit();
		});
	});

</script>
</head>
<body>

<div class="position">修改商品</div>

<sf:form method="post" id="info_form" action="goods-audit/update.action" modelAttribute="goods">

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
	
	<c:if test="${goods.no_reason != null && goods.no_reason != '' }">
		<tr>
		<td class="addtab_tit">上次拒绝原因<span>&nbsp;</span></td>
		<td>
			${goods.no_reason }
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td class="addtab_tit">审核<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="info_state" value="1" checked="checked" onclick="showNoReason(false)" />通过
            <sf:radiobutton path="info_state" value="2" onclick="showNoReason(true)" />不通过
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="info_state" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit" id="no_reason_titile">
			<c:if test="${goods.info_state == '2' }">
				原因<span>*</span>
			</c:if></td>
		<td id="no_reason_ta">
			<c:if test="${goods.info_state == '2' }">
				<textarea name="no_reason" id="no_reason" class="txt4x1"></textarea>
			</c:if>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td id="no_reason_error"></td></tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="no_reason" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="goods_id"/>
			<button class="tab_btn" id="submit_btn" type="button" />提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

