	//默认允许上传图片数
	var permitTotalImage = 5;
	//能上传的图片个数
	var canUploadNumber = function(){return permitTotalImage};
	
	/**
	* 文件上传调用方法
	* num : 上传图个数
	* fileFieldId ：上传文件文本框id
	* moduleName : 上传存储的文件夹名称
	* divId: 显示图片列表的div
	* inputId : 存储图片路径的隐藏域id
	*/
	uploadImg = function(num , fileFieldId ,moduleName ,divId ,inputId ){
		permitTotalImage = num;
		
		if($("#"+inputId).val() != ""){
			initImage(divId,inputId ,fileFieldId);
		}
		var setting = {
			//文件上传URL获取地址，必须提供
			uploadUrl: 'http://192.168.1.212:10000/xingfugo.json',
			subDirValue: moduleName,  //应用对应的模块名称，固定值，不需要用户输入时使用
			fileFieldId: fileFieldId ,//上传文件域的ID，默认为“file”
			maxSelectedFile: canUploadNumber, //一次最多上传文件数量，默认5个
			buttonText: '选择图片',
			exceedMaxSelected: function(permitTotalImage,curSelected){
										if(permitTotalImage <= 0){
											window.alert('您上传的图片数量已达上限，不能再上传！');
											return;
										}
										window.alert('您最多还可以再上传'+permitTotalImage+'张图片！');
									},
			swf: 'inc/com/uploadify/uploadify.swf',
			onSWFReady: function(){
							if(permitTotalImage <= 0){
								$('#' + fileFieldId).uploadify('disable', true);
							}
						},
			//上传完成后的处理函数
			done: function(resultObj){
						if(resultObj.isErr()){
							window.alert(resultObj.getErrMsg());
							return;
						}
						if(permitTotalImage > 0){
						addImg(resultObj.getUploadedFileURL() ,divId ,inputId , fileFieldId);
						}
				}
		};
		$.doUploadInit(setting);
		
	}
 
	//添加图片路径+
	var addImg = function(imgPath , divId , inputId ,fileFieldId){
		var $imgInput = $('#'+inputId);
		var imgInputVal = $imgInput.val();
		var imgIndex = 0;
		if(imgInputVal){
			var images = imgInputVal.split(',');
			imgIndex = images.length;
		}
		
		if(imgIndex == 0){
			imgInputVal = '';
		}
		else{
			imgInputVal += ',';
		}
		imgInputVal += imgPath;
		$imgInput.val(imgInputVal);
		 
		showImage(imgPath, imgIndex ,divId, inputId ,fileFieldId);
		permitTotalImage--;
		if(permitTotalImage <= 0){
			$('#' + fileFieldId).uploadify('disable', true);
		}
	};
	
	 
	//删除图片方法
	var delImg = function(liObject ,divId , inputId ,fileFieldId){
		var imgIndex = $(liObject).attr('id').substring(3);
		$('#img' + imgIndex).remove();
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
			$(this).attr('id', 'img' + i);
		});

		permitTotalImage++ ;
		if(permitTotalImage > 0){
			$('#' + fileFieldId).uploadify('disable', false);
		}
	};
	
	//显示图片
	var showImage = function( imgPath, imgIndex ,divId,inputId , fileFieldId){
		var $imgHtml = $('<li id="img' + imgIndex +'"><img src="'+imgPath+'" width="100" height="100" /><div class="img-button"><a href="'+imgPath+'" target="_blank">查看</a></div></li>');
		var $delImgHtml = $('<a href="javascript:void(0);">删除</a>');
		$imgHtml.find('div').append($delImgHtml);
		$('#'+divId).append($imgHtml);
		$delImgHtml.click(function(){delImg($imgHtml[0] , divId , inputId,fileFieldId);});
	};
	
	//初始化显示图片
	var initImage = function(divId,inputId , fileFieldId){
		var $imgInput = $('#'+inputId);
		var imgInputVal = $imgInput.val();
		var imgCount = 0;
		var images = [];
		if(imgInputVal){
			images = imgInputVal.split(',');
			imgCount = images.length;
		}
		
		permitTotalImage -= imgCount;
		if(imgCount == 0){return;}
		
		for(var i = 0; i < images.length; i++){
			showImage(images[i], i , divId, inputId ,fileFieldId);
		}
		
	};
	 
	
