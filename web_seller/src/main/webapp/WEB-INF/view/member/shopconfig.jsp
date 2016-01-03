<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商铺资料修改</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<%@ include file="/WEB-INF/view/inc/uploadify/resource.jsp" %>
</head>
<body>

<div class="position">商铺资料修改</div>

<sf:form method="post" action="member/shopconfig.action" modelAttribute="shopconfig">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">店铺名称<span>*</span></td>
		<td>
			<sf:input path="shop_name" maxlength="50" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shop_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">店铺LOGO<span>*</span></td>
		<td>
			<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="1"/>
			<sf:hidden path="shop_logo"/>
			<input id="file" name="file" type="file" />
			<div id="fileQueue"></div>
			<div class="img-lst"><ul id="imgView"></ul></div>
	        <script>
	        	$(function(){
	        		uploadImgComponent('<%=basePath%>','imgNumLimit','file','fileQueue','shop_logo','imgView','shopconfig');
	        	})
	        </script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shop_logo" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">店铺简介<span>*</span></td>
		<td>
			<sf:textarea path="shop_intro" cssClass="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shop_intro" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">经营范围<span>*</span></td>
		<td>
			<sf:input path="busi_range" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="busi_range" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">联系人<span>*</span></td>
		<td>
			<sf:input path="contant_man"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="contant_man" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit">手机<span>&nbsp;</span></td>
		<td>
			<sf:input path="mobile"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="mobile" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">电话<span>&nbsp;</span></td>
		<td>
			<sf:input path="phone"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="phone" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">邮箱<span>&nbsp;</span></td>
		<td>
			<sf:input path="email" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="email" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">QQ<span>&nbsp;</span></td>
		<td>
			<sf:input path="qq" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="qq" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">MSN<span>&nbsp;</span></td>
		<td>
			<sf:input path="msn" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="msn" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">地区<span>&nbsp;</span></td>
		<td>
			<span id="selectDivModel"></span>
			<script type="text/javascript">
				showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","${shopconfig.area_attr}");
			</script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="area_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">详细地址<span>&nbsp;</span></td>
		<td>
			<sf:input path="address" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="address" cssClass="error"/></td></tr>
	
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
