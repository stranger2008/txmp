<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改角色管理</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<link rel="StyleSheet" href="${pageContext.request.contextPath }/inc/com/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/inc/com/dtree/dtree.js"></script>
<script type="text/javascript">
function cascade(id) {
	cascadeParents(id);
	cascadeChildren(id);
}

function cascadeChildren(id) {
	var children = $('input[pid="' + id + '"]').attr('checked', $('#' + id).attr('checked'));
	if(children && children.length > 0) {
		children.each(function(index, element){
			cascadeChildren(element.id);
		});
	}
}

function cascadeParents(id) {
	var isChecked = $('#' + id).attr('checked');
	if(!isChecked) {
		return;
	}
	var pid = $('#' + id).attr('pid');
	var parent = $('input[id="' + pid + '"]').attr('checked', $('#' + id).attr('checked'));
	if(parent && parent.length > 0) {
		parent.each(function(index, element){
			cascadeParents(element.id);
		});
	}
}
function showTree(showDiv){
	if(showDiv=='admTree'){
		$("#sthTree").css("display","none");
		$("#admTree").css("display","block");
	}else if(showDiv='sthTree'){
		$("#admTree").css("display","none");
		$("#sthTree").css("display","block");
	}
}
</script>
<style type="text/css">
table input{vertical-align:middle;}
</style>
</head>
<body>

<div class="position">修改角色管理</div>

<sf:form method="post" action="role/update.action" modelAttribute="role">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">角色名称<span>*</span></td>
		<td>
			<sf:input path="role_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="role_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">菜单权限<span>*</span></td>
		<td>
			<a href="javascript:showTree('admTree');">商城后台</a>
			<a href="javascript:showTree('sthTree');">静态化系统</a>
			<div id = "admTree">
			<p><a href="javascript: d.openAll();">展开全部</a> | <a href="javascript: d.closeAll();">关闭全部</a></p>
			<script type="text/javascript">
			//<!--
				var dtreePath = "${pageContext.request.contextPath }/inc/com/dtree";
		
				var d = new dTree('d');
		
				d.add(1111111111,-1,'菜单树');
				<c:forEach items="${sysmenuAdmin }" var="sysmenu">
				d.add(${sysmenu.menu_id}, ${sysmenu.up_menu_id}
				, '<input type="checkbox" name="menu_right" value="${sysmenu.menu_id}" id="${sysmenu.menu_id}" onclick="cascade(this.id);" pid="${sysmenu.up_menu_id}" <c:if test="${sysmenu.has_right == true}">checked="checked"</c:if> />${sysmenu.menu_name}&nbsp;&nbsp;&nbsp;<c:forEach items="${sysmenu.role_rights}" var="role_right"><input type="checkbox" name="oper_right" value="${role_right.right_id}" id="${role_right.right_id}" onclick="cascadeParents(this.id);" pid="${sysmenu.menu_id}" <c:if test="${role_right.has_right == true}">checked="checked"</c:if> />${role_right.right_name }&nbsp;&nbsp;</c:forEach>');
				</c:forEach>
		
				document.write(d);
		
			//-->
			</script>
			</div>
			<div id = "sthTree" style="display: none;">
				<p><a href="javascript: d1.openAll();">展开全部</a> | <a href="javascript: d1.closeAll();">关闭全部</a></p>
				<script type="text/javascript">
					var d1 = new dTree('d1');
			
					d1.add(1111111111,-1,'菜单树');
					<c:forEach items="${sysmenuStatic }" var="sysmenu">
					d1.add(${sysmenu.menu_id}, ${sysmenu.up_menu_id}
					, '<input type="checkbox" name="menu_right" value="${sysmenu.menu_id}" id="${sysmenu.menu_id}" onclick="cascade(this.id);" pid="${sysmenu.up_menu_id}" <c:if test="${sysmenu.has_right == true}">checked="checked"</c:if> />${sysmenu.menu_name}&nbsp;&nbsp;&nbsp;<c:forEach items="${sysmenu.role_rights}" var="role_right"><input type="checkbox" name="oper_right" value="${role_right.right_id}" id="${role_right.right_id}" onclick="cascadeParents(this.id);" pid="${sysmenu.menu_id}" <c:if test="${role_right.has_right == true}">checked="checked"</c:if> />${role_right.right_name }&nbsp;&nbsp;</c:forEach>');
					</c:forEach>
			
					document.write(d1);
			
				</script>
			</div>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="menu_right" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">备注<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="remark" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="remark" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="role_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

