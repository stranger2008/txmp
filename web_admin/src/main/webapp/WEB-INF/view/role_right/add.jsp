<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加权限</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<link rel="StyleSheet" href="${pageContext.request.contextPath }/inc/com/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/inc/com/dtree/dtree.js"></script>
<script type="text/javascript">
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
	$("input[name='menu_attr']").attr('checked', false);
	$('#' + id).attr('checked', true);
	cycleParent(id);
}

function cycleParent(id) {
	var pid = $('#' + id).attr('pid');
	var parent = $('input[id="' + pid + '"]').attr('checked', $('#' + id).attr('checked'));
	if(parent && parent.length > 0) {
		parent.each(function(index, element){
			cycleParent(element.id);
		});
	}
}

function changeSysmenu(value) {
	//dtree源文件所在路径,不指明则图片不显示
	var dtreePath = "${pageContext.request.contextPath }/inc/com/dtree";
	var d;
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath }/role_right/getsysmenubysyscode.action',
		data: {syscode: value},
		dataType: 'json',
		success: function(data) {
			if(data == null) {
				$("#dtreeDiv").html('');
				return;
			}
			var d = new dTree('d');
			d.add(1111111111,-1,'菜单树');
			$.each(data,function(entryIndex,entry){
            	d.add(entry['menu_id'], entry['up_menu_id'], '<input type="radio" name="menu_attr" value="' + entry['menu_id'] + '" id="' + entry['menu_id'] + '" pid="' + entry['up_menu_id'] + '" />' + entry['menu_name']);
             });
			$("#dtreeDiv").html(d.toString());
		},
		error: function() {
		}
	});
}
</script>
<style type="text/css">
.dtree div{margin:0; padding:0; border: none;}
table input{vertical-align: middle; margin: 0; padding: 0;}
</style>
</head>
<body>

<div class="position">添加权限</div>

<sf:form method="post" action="role_right/add.action" modelAttribute="role_right">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">权限名称<span>*</span></td>
		<td>
			<sf:input path="right_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="right_name" cssClass="error"/></td></tr>
	<!-- 如果所属后台可选,则启用此处代码 -->
	<!-- 
	<tr>
		<td class="addtab_tit">所属后台<span>*</span></td>
		<td>
			<sf:select path="syscode" onchange="changeSysmenu(this.options[this.options.selectedIndex].value);">
				<c:forEach items="${commparas }" var="commpara">
				<sf:option value="${commpara.para_value }">${commpara.para_key }</sf:option>
				</c:forEach>
			</sf:select>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="syscode" cssClass="error"/></td></tr>
	 -->
	<tr>
		<td class="addtab_tit">所属菜单<span>*</span></td>
		<td>
			<p><a href="javascript: d.openAll();">展开全部</a> | <a href="javascript: d.closeAll();">关闭全部</a></p>
			<div id="dtreeDiv">
			<script type="text/javascript">
			<!--
				var dtreePath = "${pageContext.request.contextPath }/inc/com/dtree";
		
				var d = new dTree('d');
		
				d.add(1111111111,-1,'菜单树');
				<c:forEach items="${sysmenus }" var="sysmenu">
				d.add(${sysmenu.menu_id}, ${sysmenu.up_menu_id}, '<input type="radio" name="menu_attr" value="${sysmenu.menu_id}" id="${sysmenu.menu_id}" pid="${sysmenu.up_menu_id}" <c:if test="${sysmenu.has_right == true}">checked="checked"</c:if> />${sysmenu.menu_name}');
				</c:forEach>
		
				document.write(d);
		
			//-->
			</script>
			</div>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="menu_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">权限URL<span>*</span></td>
		<td>
			<sf:textarea path="url" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="url" cssClass="error"/></td></tr>
	
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
			<sf:hidden path="syscode" />
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>
</sf:form>

</body>
</html>

