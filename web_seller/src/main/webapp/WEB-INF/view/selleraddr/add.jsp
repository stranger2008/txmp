<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加发货地址</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加发货地址</div>

<sf:form method="post" action="selleraddr/add.action" modelAttribute="selleraddr">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">收货人姓名<span>*</span></td>
		<td>
			<sf:input path="cust_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cust_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">省/市区<span>*</span></td>
		<td>
			<span id="selectDivModel"></span>
						<script type="text/javascript">
							$(function(){
								showSelectCascade('<%=basePath %>','area','selectDivModel','area_attr','${selleraddr.area_attr}');
							});
						</script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="area_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">地址<span>*</span></td>
		<td>
			<sf:input path="address" size="60" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="address" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">手机号码<span>*</span></td>
		<td>
			<sf:input path="cell_phone"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cell_phone" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">邮政编码<span>*</span></td>
		<td>
			<sf:input path="post_code"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="post_code" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否默认<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="is_default" value="0"/>否&nbsp;&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="is_default" value="1"/>是
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="is_default" cssClass="error"/></td></tr>
	
	
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

