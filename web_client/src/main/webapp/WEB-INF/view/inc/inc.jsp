<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="h" uri="http://www.xfgtab.com/taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String path2 = request.getContextPath();
String rootPath = path2+"/inc/membercenter/";

String webname = "天下名品商城";

%>
<script type="text/javascript" src="<%=basePath %>inc/js/imgmgr.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/selectlink.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/area_selected.js"></script>
<link href="<%=basePath %>/favicon.ico" rel="shortcut icon"/>
<base href="<%=basePath%>">
