<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="http://www.xfgtab.com/taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<%
String path = request.getContextPath();
String basePath =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";	
String web_title = "天下名品触屏版";
%>
<style>
	.error{color:red;}
</style>
<link rel="stylesheet" href="<%=basePath %>inc/css/index.css">
<script type="text/javascript" src="<%=basePath %>inc/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/com/selectCascade/selectlink.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/imgmgr.js"></script>
<link rel="shortcut icon" href="<%=basePath %>favicon.ico" />
<base href="<%=basePath%>">

