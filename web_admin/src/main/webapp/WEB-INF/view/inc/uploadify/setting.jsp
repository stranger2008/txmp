<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	var setting = {
		uploadUrl: 'http://192.168.1.212:10000/xingfugo.json',
		subDirValue: '${param.subDirValue}',
		fileFieldId: '${param.fileFieldId}',
		maxSelectedFile: ${param.maxSelectedFileFunc},
		buttonText: '选择图片',
		exceedMaxSelected: ${param.exceedMaxSelectedFunc},
		swf: '<%=basePath %>inc/js/uploadify/uploadify.swf',
		onSWFReady: ${param.onSWFReadyFunc},
		done: ${param.uploadDoneFunc}
	};
	$.doUploadInit(setting);