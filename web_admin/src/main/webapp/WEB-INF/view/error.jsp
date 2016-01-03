<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>错误页面提示</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<c:if test="${name=='request_param_illegal'}">
	<center>请求参数内容有非法字符&nbsp;<a href="javascript:history.go(-1);">返回</a></center>
</c:if>

</body>
</html>
