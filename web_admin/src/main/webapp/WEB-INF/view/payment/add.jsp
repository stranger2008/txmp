<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加支付方式</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加支付方式</div>

<sf:form method="post" action="payment/add.action" modelAttribute="payment">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	
	<tr>
		<td class="addtab_tit">支付方式编码<span>*</span></td>
		<td>
			<sf:input path="pay_code"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="pay_code" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">支付方式名称<span>*</span></td>
		<td>
			<sf:input path="pay_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="pay_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">支付方式描述<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="pay_desc" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="pay_desc" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否启用<span>*</span></td>
		<td>
			<sf:radiobutton path="enabled" value="1" checked="true"/>是 &nbsp;&nbsp;&nbsp;&nbsp;
            <sf:radiobutton path="enabled" value="0"/>否
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="enabled" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">排序<span>&nbsp;</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">支持客户端<span>&nbsp;</span></td>
		<td>
			<input type="checkbox" name="client_attr" value="android" style="height:12px;" />手机客户端
			<input type="checkbox" name="client_attr" value="cp" style="height:12px;" />触屏版
			<input type="checkbox" name="client_attr" value="web" style="height:12px;" />Web版
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="client_attr" cssClass="error"/></td></tr>
		
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

