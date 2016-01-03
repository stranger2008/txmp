var selectCascadeUrl;

function showSelectCascade(bp_temp,modelName,showDivIdName,fieldName,modelValueStr,modelType){
	selectCascadeUrl = bp_temp+"public/showSelectLink.action?rn="+Math.random();
	showSelectDivModel(modelName,"1111111111",showDivIdName,fieldName,3,modelValueStr,modelType);
}

/**
modelName：模块名称 地区：area 分类：category
upModelId：上级ID，默认为 1111111111
showDivIdName：页面上显示的div id的名称
fieldName：select属性name的名称
totalNum：下拉框总数
modelValueStr：回显的值，用,号分割
*/

function showSelectDivModel(modelName,upModelId,showDivIdName,fieldName,totalNum,modelValueStr,modelType){
	var selectStr = getSelectStr(fieldName,totalNum,modelName,modelType);
	selectStr += "<input type='hidden' name='"+fieldName+"' id='field_"+replacepoint(fieldName)+"'/>";
	$("#"+showDivIdName).html(selectStr);
	$("#"+showDivIdName).css("marginLeft","0px");
	$.ajax({
		type: "get",
		async:false,
		url: selectCascadeUrl+"&model_name="+modelName+"&up_model_id="+upModelId+"&module_type="+modelType,
		success: function(data, textStatus){
			$("#"+replacepoint(fieldName)+"1").append(data);
			for(var i=1;i<=totalNum;i++){
				if(i > 1){
					$("#"+replacepoint(fieldName)+i).empty();
					$("#"+replacepoint(fieldName)+i).append("<option value=''>请选择</option>");
				}
			}
		}
	});
	
	if(modelValueStr != ""){
		var vtempstr = modelValueStr.split(",");
		var tnum = vtempstr.length;
		for(var i=0;i<tnum;i++){
			var thisValue = vtempstr[i].replace(" ","");
			var _upModelId = "";
			if(i == 0){
				_upModelId = upModelId;
			}else{
				_upModelId = vtempstr[i-1];
			}
			$.ajax({
				type: "get",
				async:false,
				url: selectCascadeUrl+"&model_name="+modelName+"&up_model_id="+_upModelId+"&module_type="+modelType,
				success: function(data, textStatus){
					var k = i+1;
					$("#"+replacepoint(fieldName)+k).empty();
					$("#"+replacepoint(fieldName)+k).append("<option value=''>请选择</option>");
					$("#"+replacepoint(fieldName)+k).append(data);
					$("#"+replacepoint(fieldName)+k).find("option[value='"+thisValue+"']").attr("selected",true);
				}
			});
		}
		$("#field_"+replacepoint(fieldName)).val(modelValueStr.replace(" ",""));
	}
}


function setSelectValue(modelName,upModelId,fieldName,totalNum,thisNum ,modelType){
	if(totalNum >= thisNum){
		$.ajax({
			type: "get",
			async:false,
			url: selectCascadeUrl+"&model_name="+modelName+"&up_model_id="+upModelId+"&module_type="+modelType,
			success: function(data, textStatus){
				for(var i=1;i<=totalNum;i++){
					if(i > thisNum){
						$("#"+replacepoint(fieldName)+i).empty();
						$("#"+replacepoint(fieldName)+i).append("<option value=''>请选择</option>");
					}
				}
				$("#"+replacepoint(fieldName)+(thisNum+1)).append(data);
			}
		});
		var fvalue = "";
		for(var i=1;i<=totalNum;i++){
			var _value = $("#"+replacepoint(fieldName)+i).val();
			if(_value != ""){
				fvalue += _value+",";
			}
		}
		$("#field_"+replacepoint(fieldName)).val(fvalue);
	}
}

function getSelectStr(fieldName,totalNum,modelName ,modelType){
	var selectStr = "";
	for(var i=1;i<=totalNum;i++){
		selectStr += "<select name='select_"+fieldName+"' id='"+replacepoint(fieldName)+i+"' style='margin-right:3px;'  onchange=setSelectValue('"+modelName+"',this.value,'"+fieldName+"',"+totalNum+","+i+",'"+modelType+"');  >";
		selectStr += "<option value=''>请选择</option>";
		selectStr += "</select>";
	}
	return selectStr;
}

function replacepoint(fieldName){
	return fieldName.replace(".","_");
}
