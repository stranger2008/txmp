(function($){
	var __fileUploadURL = '';
	var __fileUploadURLLoaded = false;
	
	var loadFileUploadURL = function(fileUploadURLRequest, loadCallBack) {
		if(__fileUploadURLLoaded){return;}
		$.ajax({
			url : fileUploadURLRequest, 
			success: function(res){
				var xmlDoc = eval('('+res+')');
				if(xmlDoc.code == 1){__fileUploadURL = xmlDoc.data;__fileUploadURLLoaded = true;}
				if(!loadCallBack){return;}
				loadCallBack({
					isErr: function(){return (xmlDoc.code == 0);},
					getErrMsg: function(){if(data.code == 0){return xmlDoc.errs[0].msg;}},
					getFileUploadURL: function(){return xmlDoc.data;}
				});
			},
			async:false
		});	
	};
	
	var uploadHtml5Files = function(setting){
		if(!__fileUploadURLLoaded){
			return false;
		}
		
		var $fileList = $('#' + setting.fileFieldId);
		var selectedFileAmount = 0;
		try{
			selectedFileAmount = $fileList[0].files.length;
		} catch(e){}
		
		if(selectedFileAmount == 0){
			setting.noneFileSelected();
			return false;
		}
		
		var canSelectedFileAmount = setting.maxSelectedFile();
		if(selectedFileAmount > canSelectedFileAmount){
			setting.exceedMaxSelected(canSelectedFileAmount, selectedFileAmount);
			return false;
		}
        
        var formData = new FormData();
        if(setting.subDirValue){
	        formData.append('sub', setting.subDirValue);
        }
        else{
        	if(setting.subDirFieldId){
		        formData.append('sub', $('#' + setting.subDirFieldId).val());
        	}
        	else{
		        formData.append('sub', '');
        	}
        }
        formData.append('autoUrl', setting.returnAutoUrl);
        for(var i = 0; i < selectedFileAmount; i++){
        	formData.append('file', $fileList[0].files[i]);
        }
        
		createUploading(setting);
		
		$.ajax({
			url: __fileUploadURL,
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false
		}).done(function(res){
			if(setting.clearFileField){
				var fileElement = $('#' + setting.fileFieldId);
				$(fileElement).blur();
				$(fileElement).val('');
			}
			
			removeUploading(setting);
			var xmlDoc = eval('(' +res+')');
			
			var result = {
				isErr: function(){return (xmlDoc.code == 0);},
				getErrMsg: function(){
					var errMsg = '';
					if(xmlDoc.code == 0){
						$.each(xmlDoc.errs, function(i, e){
        					if(i > 0){
        						errMsg += ',';
        					}
        			
        					errMsg += e.msg;
        					if(xmlDoc.data && xmlDoc.data[e.code]){
        						errMsg += ('[' + xmlDoc.data[e.code].join(',') + ']');
        					}
        				});
        				
        				return errMsg;
					}

					var appended = false;					
					if(xmlDoc.data['upload.illegal_file']){
						errMsg += '非法文件：';
						errMsg += xmlDoc.data['upload.illegal_file'].join(',');
						appended = true;
					}
					
					if(xmlDoc.data['upload.file_exceed_max_size']){
						if(appended){errMsg += ',';}
						errMsg += '超尺寸文件：';
						errMsg += xmlDoc.data['upload.file_exceed_max_size'].join(',');
						appended = true;
					}
					
					if(xmlDoc.data['upload.failed']){
						if(appended){errMsg += ',';}
						errMsg += '上传失败文件：';
						errMsg += xmlDoc.data['upload.failed'].join(',');
					}
					
					return errMsg;
				},
				getSuccessAmount: function(){return xmlDoc.data.success ? xmlDoc.data.success.length : 0},
				getErrorAmount: function(){return selectedFileAmount - this.getSuccessAmount()},
				getUploadedFileURL: function(){return this.getSuccessAmount() > 0 ? xmlDoc.data.success.join(',') : '';}
			};
			
			setting.done(result);
			return true;
		});
	};
	
	var createUploading = function(setting){
		if(!setting.uploadingHTML && !setting.uploadingImage){
			return;
		}
		
		var $obj;
		if(setting.bindObjectId){
			$obj = $('#' + setting.bindObjectId);
		}else if(setting.fileFieldId){
			$obj = $('#' + setting.fileFieldId);
		}
		
		if($obj.length == 0){
			return;
		}
		
		var loadingImage = false;
		if(setting.uploadingImage){
			loadingImage = true;
		}
		
		var uploadingId = 'uploading_' + setting.fileFieldId;
		var html = '<span id="'+uploadingId+'">';
		if(loadingImage){
			html += ('<img src="' + setting.uploadingImage + '" /></span>');
			$obj.after(html);
		}
		else{
			html += '</span>';
			$obj.after(html);
			$('#'+uploadingId).append(setting.uploadingHTML);
		}
	};
	
	var removeUploading = function(setting){
		var uploadingId = 'uploading_' + setting.fileFieldId;
		var $uploading = $('#'+uploadingId);
		$uploading.remove();
	};
	
	$.doUploadInit = function(userSetting){
		var defaultSetting = {
			uploadUrl: '',
			fileFieldId: 'file',
			bindObjectId: '',
			bindEvent: 'click',
			subDirValue: '', 
			subDirFieldId: '', 
			maxSelectedFile: function(){return 5;},
			noneFileSelected: function(){},
			exceedMaxSelected: function(){},
			done: function(){},
			clearFileField: true,
			returnAutoUrl: false,
			uploadingHTML: '',
			uploadingImage: ''
		};
	
		var setting = $.extend(defaultSetting, userSetting);
		loadFileUploadURL(setting.uploadUrl);
		var userBindObjectId = setting.bindObjectId;
		if(setting.bindObjectId && setting.bindObjectId != setting.fileFieldId){
			$('#' + setting.bindObjectId).bind(setting.bindEvent, function(){
				$('#' + setting.fileFieldId).trigger('click');
			});
		}
	
		$('#' + setting.fileFieldId).bind('change', function(){
			uploadHtml5Files(setting);
		});
	};
})(jQuery);

