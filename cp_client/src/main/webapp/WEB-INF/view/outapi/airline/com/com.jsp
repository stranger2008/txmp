<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<meta name="author" content="">
<Meta http-equiv="Expires" Content="Wed, 26 Feb 1997 09:21:57 GMT">
<meta http-equiv="Last-Modified" content="Wed, 26 Feb 1997 09:21:57 GMT">
<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate,max-age=0,post-check=0, pre-check=0,false">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta name="viewport"	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">



<link rel="stylesheet" href="<%=basePath %>/inc/outapi/airline/css/airline.css">
<script type="text/javascript" src="<%=basePath %>inc/js/jquery-1.4.4.min.js"></script>
<base href="<%=basePath%>">
