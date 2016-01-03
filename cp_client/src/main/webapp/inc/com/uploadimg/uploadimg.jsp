<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<script type="text/javascript" src="<%=basePath %>inc/com/uploadimg/jquery-1.11.1.min.js" ></script>

		<!--2、引入文件上传组件js文件 -->
		<script type="text/javascript" src="<%=basePath %>inc/com/uploadimg/ajaxHtml5FileUpload.js" ></script>
		<link rel="stylesheet" href="<%=basePath %>inc/com/uploadimg/index.css">
		
		<!--3、定义上传信息初始化、上传操， 及上传完成后操作 -->
		<script type="text/javascript">
		
		var maxNum = 0,orgNum = 0;
		
		function canUploadNumber(){
			return maxNum;
		}

		function uploadImg(num,bindObjectId,fileFieldId,moduleName,divid,inputId){
		
			maxNum = num;
			orgNum = num;
		
			if($("#"+inputId).val() != ""){
				showPicInDivid(divid,$("#"+inputId).val(),inputId,num,bindObjectId);
			}
			var setting = {
					//文件上传URL获取地址，必须提供
					uploadUrl: 'http://192.168.1.212:10000/xingfugo.json',
					fileFieldId: fileFieldId,				//上传文件域的ID，默认为“file”
					subDirValue: moduleName, 					//应用对应的模块名称，固定值，不需要用户输入时使用
					//subDirFieldId: moduleName, 			//应用对应的模块名称所在文本域ID，需要用户输入时使用
					maxSelectedFile: canUploadNumber,				//一次最多上传文件数量，默认5个
					//returnAutoUrl: true,				//是否返回自动生成图片的URL，默认不返回
					bindObjectId: bindObjectId,			//文件选择操作绑定的对象ID，不提供时，为fileFieldId自身
					//bindEvent: 'click',				//文件选择操作绑定的事件，默认为“click”事件
					uploadingHTML: '<image src="<%=basePath %>inc/com/uploadimg/loading.gif" height="20" width="20" style="vertical-align:middle;" />',
					//用户选择文件数量超过限制时的处理函数
					exceedMaxSelected: function(max,curSelected){alert('一次最多选择'+max+'个文件，您已选择了'+curSelected+'个！')},
					//上传完成后的处理函数
					done: function(resultObj){
							//上传操作是否失败
							if(resultObj.isErr()){
								//获取失败信息
								alert(resultObj.getErrMsg());
								return;
							}

							//成功上传文件数量：getSuccessAmount()
							//上传后文件URL（多个以逗号分隔）：getUploadedFileURL()
							//alert("成功上传" +resultObj.getSuccessAmount() + "个文件，访问地址为：\r\n" + resultObj.getUploadedFileURL());
							
							var imgnum = resultObj.getSuccessAmount();
							var imgfileUrl = resultObj.getUploadedFileURL();
							if(imgnum > 0){
								showPicInDivid(divid,imgfileUrl,inputId,num,bindObjectId);
								//存储值的隐藏字段处理
								var inputIdVal = $("#"+inputId).val();
								if(inputIdVal != ""){
									$("#"+inputId).val(inputIdVal+","+imgfileUrl);
								}else{
									$("#"+inputId).val(imgfileUrl);
								}
								//上传按钮是否可用
								
								
								maxNum = maxNum - imgnum;
								
								buttonDisable(bindObjectId,inputId);
								
							}
							
							//部分成功情况
							if(resultObj.getErrorAmount() > 0){
								//未成功上传信息（非法扩展名、文件超限、其他原因造成的上传失败）
								alert(resultObj.getErrMsg());
							}
						}
				};
				
				$.doUploadInit(setting);
		}
		
		//根据输入框的内容填充div里的图片显示
		function showPicInDivid(divid,imgfileUrl,inputId,num,bindObjectId){
			var imgfileUrlStr = imgfileUrl.split(",");
			for(var i=0;i<imgfileUrlStr.length;i++){
				var divstr = "<li>";
				divstr += "<img src='"+imgfileUrlStr[i]+"'>";
				divstr += "<div class='uploadimg_img-button'>";
				divstr += "<a href='"+imgfileUrlStr[i]+"' target='_blank'>查看</a>";
				divstr += "<a href='javascript:void(0);' onclick='delUploadImg(this,\""+inputId+"\",\""+bindObjectId+"\");'>删除</a>";
				divstr += "</div>";
				divstr += "</li>";
				$("#"+divid+" ul").append(divstr);
			}
		}
		
		//删除隐藏输入框内的值和HTML元素
		function delUploadImg(obj,inputId,bindObjectId){
			var imgSrc = $(obj).parent().parent().children("img").attr("src");
			var inputIdVal = $("#"+inputId).val();
			if(inputIdVal.indexOf(imgSrc+",") > -1){
				inputIdVal = inputIdVal.replace(imgSrc+",","");
			}else{
				inputIdVal = inputIdVal.replace(imgSrc,"");
			}
			$("#"+inputId).val(inputIdVal);
			$(obj).parent().parent().remove();
			
			maxNum = maxNum + 1;
			
			//上传按钮是否可用
			buttonDisable(bindObjectId,inputId);
		}
		
		function buttonDisable(bindObjectId,inputId){
			var inputIdVal = $("#"+inputId).val();
			var _iiv = inputIdVal.split(",");
			var valCount = 0;
			for(var i=0;i<_iiv.length;i++){
				if(_iiv[i] != ""){
					valCount ++;
				}
			}
			if(valCount >= orgNum){
				$("#"+bindObjectId).attr("disabled",true);
			}else{
				$("#"+bindObjectId).attr("disabled",false);
			}
		}
		
		</script>
		