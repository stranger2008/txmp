<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改基本设置</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript">
function isNum(){
	var type = $("#var_type").val();
    if(type!=1){
   		$("#value_error_info").text("");
     	$("#value_error_info").removeClass("error");
    	return true;
    }else{ 
	    var num=/^([0-9]*)$/;
	    if(num.test($("#var_value").val())){
	    	$("#value_error_info").text("");
	     	$("#value_error_info").removeClass("error");
	    	return true;
	    }else{
	     	$("#value_error_info").text("变量值必须是数字！");
	     	$("#value_error_info").addClass("error");
	    	return false;
	    }
    }
  }

function goUpdate(){
	if(isNum()){//sysconfig
		$("#updateForm").submit();
	}
}
</script>
</head>
<body>

<div class="position">修改基本设置</div>

<sf:form id="updateForm" method="post" action="sysconfig/update.action" modelAttribute="sysconfig">
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">变量名称<span>&nbsp;</span></td>
		<td>${sysconfig.var_name}
			<sf:hidden path="var_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="var_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">变量值<span>&nbsp;</span></td>
		<td>
		<!-- 文本 -->
		<c:if test="${sysconfig.var_type==0 }">
		<sf:input path="var_value" cssClass="w400"/>
		</c:if>
		<!-- 数字 -->
		<c:if test="${sysconfig.var_type==1 }">
		<sf:input path="var_value" onblur="isNum();" cssClass="w400"/>
		</c:if>
		<!-- 布尔 -->
		<c:if test="${sysconfig.var_type==2 }"> 
		<sf:radiobutton path="var_value" value="0"/>是&nbsp;&nbsp;&nbsp;&nbsp;
		<sf:radiobutton path="var_value" value="1"/>否
		</c:if>
		<!-- 多行文本 -->
		<c:if test="${sysconfig.var_type==3 }">
		<sf:textarea path="var_value" cssClass="txt4x1"/>
		</c:if>
		<!-- 图片 -->
		<c:if test="${sysconfig.var_type==4 }">
		<sf:input path="var_value" cssClass="w400"/>
		</c:if>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td>
	<td id="value_error_info"><sf:errors path="var_value" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">变量说明<span>&nbsp;</span></td>
		<td>
			<sf:input path="var_desc" cssClass="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="var_desc" cssClass="error"/></td></tr>
	
	<tr style="display:none;">
		<td class="addtab_tit">所属组<span>&nbsp;</span></td>
		<td>
			<sf:hidden path="var_group"/>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="var_id"/>
			<button class="tab_btn" type="button" onclick="goUpdate();"/>提交</button>
			<!-- 
			<button class="tab_btn" type="button" onclick="history.back();"/>返回</button>
			 -->
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

