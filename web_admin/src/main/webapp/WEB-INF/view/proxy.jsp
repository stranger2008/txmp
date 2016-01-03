<%@ page language="java" session="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>文件上传结果页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=basePath %>inc/js/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//调用上传文件页面的函数，用于处理上传结果信息
		parent.parent.handleUploadResult(eval('(${data})'));
	});
	</script>
  </head>
  <body>
  </body>
</html>
