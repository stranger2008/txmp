
 var imagepath="inc/com/uploadify_3.2.1";
 
/*
* basepath: 	项目根路径
* fileNumLimitId : 		最多上传文件数的隐藏域id
* uploadifyfile:file类型输入框的ID
* fileQueue:	显示文件上传效果队列的div的Id
* uploadresult：上传文件成功后的文件保存路径的隐藏域 id
* divId	:		显示上传图片div的id
* moduleName :	应用对应的模块名称
*/
 
function uploadImgComponent(basepath,fileNumLimitId ,uploadifyfile,fileQueue,uploadresult,divId ,moduleName){
	 
	var multi = $('#'+fileNumLimitId).val() > 1 ;
    var actionUrl = basepath+'uploadImgs_'+moduleName+'.action';
	$("#"+uploadifyfile).uploadify({
          'swf'            : imagepath+'/uploadify.swf',
          'uploader'       : actionUrl,
          'queueID'        : fileQueue,//文件队列的ID，该ID与存放文件队列的div的ID一致。
          'auto'           : true,//是否自动上传图片
          'multi'          : multi,//是否支持多上传
          'fileSizeLimit'  : 1024*2,//上传文件的大小限制 。
          'fileTypeDesc'   : "gif/jpg/jpeg/png/bmp", //这个属性值必须设置fileExt属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileDesc为“请选择rar doc pdf文件”，打开文件选择框效果
          'fileTypeExts'   : '*.gif;*.jpg;*.jpeg;*.png;*.bmp',//设置可以选择的文件的类型，格式如：'*.doc;*.pdf;*.rar' 。
          'progressData'    : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比
          'buttonText'     : 'Browser',//浏览按钮的文本，默认值：BROWSE 。
          'fileObjName'    : uploadifyfile,  //设置一个名字，在服务器处理程序中根据该名字来取上传文件的数据。默认为Filedata
          'height'         : 20,  //设置浏览按钮的高度 ，默认值：30。
		  'width'          : 60,   //设置浏览按钮的宽度 ，默认值：110。
		  'buttonImage'    : imagepath+'/upload.jpg',  //浏览按钮的图片的路径
		  //'uploadLimit'    : 999,  //最多上传文件数量
		  'removeCompleted' : true,   
		  'removeTimeout'   : 1,
          'onUploadSuccess': function (file, data, response){ 
          		var msg = eval("("+data+")");
          		var upok = msg.success ;
          		//var visitPath = msg.visitpath;
          		if(upok){
	           		addImg(msg.path ,msg.visitpath ,divId ,uploadresult ,uploadifyfile ,fileNumLimitId);  
          		}else{
          			var errorIns = msg.msg;
          			showErrorInfo(errorIns);
          		}
          }
    });
    
    if($("#"+uploadresult).val() !== ''){
			initImage(divId,uploadresult ,uploadifyfile ,fileNumLimitId ,basepath);
	}
}

function uploadBt_IsShow(limitFieldId, resultInputId , fileId){
		var limitNum = $('#'+limitFieldId).val();
		var inputVal = $('#'+resultInputId).val();
		var totalnum = 0;
		if(inputVal!=null && inputVal!=''){
			var files = inputVal.split(',');
			totalnum = files.length;
		}
		if(totalnum<limitNum){
			$('#'+fileId).show();
		}else{
			$('#'+fileId).hide();
		}
}

 function showErrorInfo(errorInfo){
   var errorInfos = errorInfo.split(",");
   var msgs = new Array();
   var i = 0;
   for(i = 0;i<errorInfos.length ;i++){
   		var info = errorInfos[i];
   		 //文件扩展名错误（非法文件）
   		if(info.indexOf('file_ext_error')>=0){
   			msgs.push('文件扩展名错误');
   		}
   		if(info.indexOf('file_exceed_max_size')>=0){
   			msgs.push('文件大小超过最大限制');
   		}
   		if(info.indexOf('file_empty')>=0){
   			msgs.push('未获取到文件');
   		}
   		if(info.indexOf('file_read_failed')>=0){
   			msgs.push('文件读取失败');
   		}
   		if(info.indexOf('upload_failed')>=0){
   			msgs.push('文件上传失败');
   		}
   		if(info.indexOf('img_spec_failed')>=0){
   			msgs.push('创建缩放图失败');
   		}
   		if(info.indexOf('projectName_null')>=0){
   			msgs.push('为获取到文件的所属项目名称');
   		} 
   }
   var error_msg = msgs.join(",");
   alert(error_msg);
 }

	//添加图片路径+
	var addImg = function(imgPath ,visitPath, divId , inputId ,fileFieldId ,fileNumLimitId){
		//图片限制上传个数
		var limitNum = 0;
		if(fileNumLimitId!=null){
			limitNum = $('#'+fileNumLimitId).val();
		}
		
		var $imgInput = $('#'+inputId);
		var imgInputVal = $imgInput.val();
		var imgIndex = 0;
		if(imgInputVal.length>0&&imgInputVal.indexOf(visitPath)!=-1){
			var reger = new RegExp(visitPath,"gm"); 
			imgInputVal = imgInputVal.replace(reger,"");
			reger = new RegExp("//","gm"); 
			imgInputVal = imgInputVal.replace(reger,"/");
		}
		if(imgInputVal){
			var images = imgInputVal.split(',');
			imgIndex = images.length;
		}
		
		if(imgIndex == 0 || limitNum <= 1){
			imgInputVal = '';
		}
		else{
			imgInputVal += ',';
		}
		imgInputVal += imgPath;
		$imgInput.val(imgInputVal);
		 
		showImage(imgPath, imgIndex ,divId, inputId ,fileFieldId  ,fileNumLimitId ,visitPath);
		 
		//uploadBt_IsShow(fileNumLimitId, inputId , fileFieldId);
	};
	
	 
	//删除图片方法
	var delImg = function(liObject ,divId , inputId ,fileFieldId ,fileNumLimitId){
	
		var numInd = 3 + divId.length;
		var imgIndex = $(liObject).attr('id').substring(numInd);
		$('#img' +divId+ imgIndex).remove();
		//从存储图片路径的文本框中去掉删除的图片路径
		var $imgInput = $('#'+inputId);
		var imgInputVal = $imgInput.val();
		if(!imgInputVal){return;}
		
		var images = imgInputVal.split(',');
		if(imgIndex >= images.length){return;}
		images.splice(imgIndex, 1);
		$imgInput.val(images.length == 0 ? '' : images.join(','));
		//重新设置图片所在li标签属性
		$('li', $('#'+divId)).each(function(i, o){
			$(this).attr('id', 'img' +divId+ i);
		});

		//uploadBt_IsShow(fileNumLimitId, inputId , fileFieldId);
	};
	
	//显示图片
	var showImage = function( imgPath, imgIndex ,divId,inputId ,fileFieldId ,fileNumLimitId ,visitBaseUrl){
		//图片限制上传个数
		var limitNum = 0;
		if(fileNumLimitId!=null){
			limitNum = $('#'+fileNumLimitId).val();
		}
		var $imgHtml = $('<li id="img' + divId+imgIndex +'"><img src="'+visitBaseUrl+imgPath+'" width="100" height="100" /><div class="img-button"><a href="'+visitBaseUrl+imgPath+'" target="_blank">查看</a></div></li>');
		var $delImgHtml = $('<a href="javascript:void(0);">删除</a>');
		$imgHtml.find('div').append($delImgHtml);
		if(limitNum <= 1){
			$('#'+divId).html($imgHtml);
		}else{
			$('#'+divId).append($imgHtml);
		}
		$delImgHtml.click(function(){delImg($imgHtml[0] , divId , inputId ,fileFieldId ,fileNumLimitId);});
	};
	
	//初始化显示图片
	var initImage = function(divId,inputId ,fileFieldId ,fileNumLimitId ,basepath){
		var imgBasePath = getImgBasePath(basepath);
		 
		var $imgInput = $('#'+inputId);
		var imgInputVal = $imgInput.val();
		if(imgInputVal.length>0&&imgInputVal.indexOf(imgBasePath)!=-1){
			var reger = new RegExp(imgBasePath,"gm"); 
			imgInputVal = imgInputVal.replace(reger,"");
			reger = new RegExp("//","gm"); 
			imgInputVal = imgInputVal.replace(reger,"/");
			$imgInput.val(imgInputVal);
		}
		var imgCount = 0;
		var images = [];
		if(imgInputVal){
			images = imgInputVal.split(',');
			imgCount = images.length;
		}
		
		if(imgCount == 0){return;}
		
		
		for(var i = 0; i < images.length; i++){
			showImage(images[i], i , divId, inputId ,fileFieldId,fileNumLimitId ,imgBasePath );
		}
		//uploadBt_IsShow(fileNumLimitId, inputId , fileFieldId);
	};

	 //获取图片访问服务器路径
	 function getImgBasePath(basepath){
	 	var path = "";
	 	$.ajax({
	 		url:basepath+"visitImgPath.action",
	 		async : false,
	 		success: function(data){
	 			var info = eval('('+data+')')
	 			path = info.imgBasePath;
	 		}
	 	});
	 	return path;
	 }
	 
	/*
	* 上传文件
	* basepath: 	项目根路径
	* fileNumLimitId : 最多上传文件数的文本框id
	* uploadifyfile:file类型输入框的ID
	* fileQueue:	显示文件上传效果队列的div的Id
	* uploadresult：上传文件成功后的文件保存路径的隐藏域 id
	* divId :　显示文件的Id
	* moduleName：应用对应的模块名称
	*/
	function uploadFileComponent(basepath,fileNumLimitId,uploadifyfile,fileQueue,uploadresult,divId ,moduleName){
	    var multi = $('#'+fileNumLimitId).val() > 1 ;
	    var actionUrl = basepath+'uploadFiles_'+moduleName+'.action';
		$("#"+uploadifyfile).uploadify({
	          'swf'            : imagepath+'/uploadify.swf',
	          'uploader'       : actionUrl,
	          'queueID'        : fileQueue,//文件队列的ID，该ID与存放文件队列的div的ID一致。
	          'auto'           : true,//是否自动上传图片
	          'multi'          : multi,//是否支持多上传
	          'fileSizeLimit'  : 1024*10,//上传文件的大小限制 单位kb 。
	          'fileTypeDesc'   : "doc/xls/rar/zip", //这个属性值必须设置fileExt属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileDesc为“请选择rar doc pdf文件”，打开文件选择框效果
	          'fileTypeExts'   : '*.doc;*.xls;*.rar;*.zip',//设置可以选择的文件的类型，格式如：'*.doc;*.pdf;*.rar' 。
	          'progressData'    : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比
	          'buttonText'     : 'Browser',//浏览按钮的文本，默认值：BROWSE 。
	          'fileObjName'    : uploadifyfile,  //设置一个名字，在服务器处理程序中根据该名字来取上传文件的数据。默认为Filedata
	          'height'         : 20,  //设置浏览按钮的高度 ，默认值：30。
			  'width'          : 60,   //设置浏览按钮的宽度 ，默认值：110。
			  'buttonImage'    : imagepath+'/upload.jpg',  //浏览按钮的图片的路径
			  'removeCompleted' : true,   
		  	  'removeTimeout'   : 1,
			 
	          'onUploadSuccess': function (file, data, response){ 
	          
		          	var info = eval("("+data+")");
	          		var upok = info.success ;
	          		if(upok){
		           		addFilePath(info.path , info.visitpath ,divId, uploadresult ,uploadifyfile,fileNumLimitId );  
	          		}else{
	          			var errorIns = info.msg;
	          			showErrorInfo(errorIns);
	          		}
         				
	          }
	    });
	    
	    if($("#"+uploadresult).val() != ""){
				initFile(divId,uploadresult ,uploadifyfile ,fileNumLimitId ,basepath);
		}
	}
	
	//添加文件路径
	var addFilePath = function(path , visitpath, divId, inputId ,uploadifyfile,fileNumLimitId ){
		//文件限制上传的个数
		var limitNum = 0;
		if(fileNumLimitId!=null){
			limitNum = $('#'+fileNumLimitId).val();
		}
		
		var $fileInput = $('#'+inputId);
		var fileInputVal = $fileInput.val();
		if(fileInputVal.length>0&&fileInputVal.indexOf(visitpath)!=-1){
			var reger = new RegExp(visitpath,"gm"); 
			fileInputVal = fileInputVal.replace(reger,"");
			reger = new RegExp("//","gm"); 
			fileInputVal = fileInputVal.replace(reger,"/");
		}
		
		var fileIndex = 0;
		if(fileInputVal){
			var filepth = fileInputVal.split(',');
			fileIndex = filepth.length;
		}
		
		if(fileIndex == 0 || limitNum <= 1){
			fileInputVal = '';
		}
		else{
			fileInputVal += ',';
		}
		fileInputVal += path;
		$fileInput.val(fileInputVal);
		
		showFile(path, fileIndex ,divId, inputId ,uploadifyfile , fileNumLimitId ,visitpath);
		
		//uploadBt_IsShow(fileNumLimitId, inputId , uploadifyfile);
	};
	

	
	//删除文件方法
	var delFile = function(liObject ,divId , inputId ,uploadifyfile , fileNumLimitId ){
		var numIndex = 2 + divId.length ;
		var fileIndex = $(liObject).attr('id').substring(numIndex);
		$('#fl' +divId+ fileIndex).remove();
		//从存储文件路径的文本框中去掉删除的文件路径
		var $fileInput = $('#'+inputId);
		var fileInputVal = $fileInput.val();
		if(!fileInputVal){return;}
		
		var files = fileInputVal.split(',');
		if(fileIndex >= files.length){return;}
		files.splice(fileIndex, 1);
		$fileInput.val(files.length == 0 ? '' : files.join(','));
		//重新设置文件所在li标签属性
		$('li', $('#'+divId)).each(function(i, o){
			$(this).attr('id', 'fl'+ divId + i);
		});
		//uploadBt_IsShow(fileNumLimitId, inputId , uploadifyfile);
	};
	
	//显示文件
	var showFile = function( filePath, fileIndex ,divId,inputId ,uploadifyfile , fileNumLimitId ,visitBaseUrl ){
		//文件限制上传的个数
		var limitNum = 0;
		if(fileNumLimitId!=null){
			limitNum = $('#'+fileNumLimitId).val();
		}
		
		var filenm = filePath.substring(filePath.lastIndexOf("/")+1);
		var $fileHtml = $('<li id="fl' +divId+ fileIndex +'">'+filenm+' <span style="padding-left:10px"><a href="'+visitBaseUrl+filePath+'" target="_blank">打开</a>&nbsp;&nbsp;&nbsp;&nbsp;</span></li>');
		var $delfileHtml = $('<a href="javascript:void(0);">删除</a>');
		$fileHtml.find('span').append($delfileHtml);
		if(limitNum <= 1){
			$('#'+divId).html($fileHtml);
		}else{
			$('#'+divId).append($fileHtml);
		}
		$delfileHtml.click(function(){delFile($fileHtml[0] , divId , inputId ,uploadifyfile , fileNumLimitId  );});
	};
	
	//初始化显示文件
	var initFile = function(divId,inputId ,uploadifyfile ,fileNumLimitId , basepath){
		var flBasePath = getFileBasePath(basepath);
		var $fileInput = $('#'+inputId);
		var fileInputVal = $fileInput.val();
		
		if(fileInputVal.length>0&&fileInputVal.indexOf(flBasePath)!=-1){
			var reger = new RegExp(flBasePath,"gm"); 
			fileInputVal = fileInputVal.replace(reger,"");
			reger = new RegExp("//","gm"); 
			fileInputVal = fileInputVal.replace(reger,"/");
			$fileInput.val(fileInputVal);
		}
		
		var fileCount = 0;
		var files = [];
		if(fileInputVal){
			files = fileInputVal.split(',');
			fileCount = files.length;
		}
		
		if(fileCount == 0){return;}
		
		for(var i = 0; i < files.length; i++){
			showFile(files[i], i , divId, inputId,uploadifyfile ,fileNumLimitId , flBasePath);
		}
		//uploadBt_IsShow(fileNumLimitId, inputId , uploadifyfile);
	};
	
	 function getFileBasePath(basepath){
	 	var path = "";
	 	$.ajax({
	 		url:basepath+"visitFilePath.action",
	 		async : false,
	 		success: function(data){
	 			var info = eval('('+data+')')
	 			path = info.fileBasePath;
	 		}
	 	});
	 	return path;
	 }