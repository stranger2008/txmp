<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看店铺信息</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">查看店铺信息</div>

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">店铺名称<span>&nbsp;</span></td>
		<td>
			${shopconfig.shop_name }&nbsp;
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">店铺logo<span>&nbsp;</span></td>
		<td>
			<c:set value="${fn:split(shopconfig.shop_logo, ',') }" var="imgs" />
			<c:forEach items="${imgs }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }" target="_blank"><img src="${img }" style="width: 100px; height: 100px;" /></a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">店铺简介<span>&nbsp;</span></td>
		<td>
			${shopconfig.shop_intro }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">经营范围<span>&nbsp;</span></td>
		<td>
			${shopconfig.busi_range }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">联系人<span>&nbsp;</span></td>
		<td>
			${shopconfig.contant_man }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">手机<span>&nbsp;</span></td>
		<td>
			${shopconfig.mobile }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">固定电话<span>&nbsp;</span></td>
		<td>
			${shopconfig.phone }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">电子邮箱<span>&nbsp;</span></td>
		<td>
			${shopconfig.email }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">qq<span>&nbsp;</span></td>
		<td>
			${shopconfig.qq }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">msn<span>&nbsp;</span></td>
		<td>
			${shopconfig.msn }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">所属地区<span>&nbsp;</span></td>
		<td>
			${shopconfig.area_attr_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">详细地址<span>&nbsp;</span></td>
		<td>
			${shopconfig.address }
		</td>
	</tr>
	
</table>

</body>
</html>

