<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商品咨询回复</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">商品咨询</div>

<sf:form method="post" action="goodsask/reply.action" modelAttribute="goodsaskreply">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">咨询内容<span>&nbsp;</span></td>
		<td>
			<sf:hidden path="c_content"/>
			<script>
				document.write($("#c_content").val());
			</script>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">回复内容<span>*</span></td>
		<td>
			<sf:textarea path="re_content" cssClass="txt4x1"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="re_content" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="trade_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>
