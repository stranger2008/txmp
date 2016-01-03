/** @author langgu 20131204 created */
/*---------------------------------------------双色球------------------------------------------------*/
// 监听横屏事件
	window.addEventListener('orientationchange', function() {
				setTimeout(function() {
							if ($("#shade").css("display") != "none") {								
								alertPopCenter();
								confirmPopCenter();
							}
						}, 500);
			}, false);
	
	function confirmPopCenter() {
		$('#confirmPop').css({
					left : ($(window).width() - $('#confirmPop').outerWidth()) / 2,
					top : ($(window).height() - 100) / 2 + $(document).scrollTop()
				});

		$("#shade").height($(document).height());
	}
	
	/**
	 * 弹出提示框居中
	 */
	function alertPopCenter() {
		$('#alertPop').css({
					left : ($(window).width() - $('#alertPop').outerWidth()) / 2,
					top : ($(window).height() - 100) / 2 + $(document).scrollTop()
				});

		$("#shade").height($(document).height());
	}

	function _creatSSQNums(item){
		
		// 组装nums 红球1个span 蓝球1个span
		var nums = "<span style=\"padding-right:0px;\">";
		var redball ="";
		// 单式,复式
		if (item.type == 0 || item.type == 1) {
		
			ss = item.red.split(",");
			for ( var k = 0; k < ss.length; k++) {
				nums += ss[k] + " ";	
				redball+=ss[k]+",";			
			}
			
		} 
		// 胆拖
		else if (item.type == 2) {
			ss = item.dan.split(",");
			nums += "(";
			for ( var k = 0; k < ss.length; k++) {
				nums += ss[k] ;
				if (k != ss.length - 1) {
					nums += " ";
				}
			}
			nums += ") ";
			ss = item.tuo.split(",");
			for ( var k = 0; k < ss.length; k++) {
				nums += ss[k] + " ";				
			}
		}
		nums += "</span>";
		nums += _spanBlue();
		ss = item.blue.split(",");
		
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
			if (k != ss.length - 1) {
				nums += " ";
			}
		}
		var blueballs =ss.length;
		var redballs =redball.substring(0, redball.length-1).split(",");
		if(redballs.length>6&&blueballs>1){
				clearAllEvent();
				deleteRow(1)
			return _returnIndexPage("未知彩种，请您返回彩票首页重新购彩！");
		}
		return nums;		
	}

function _creatSSQDesc(item){
	return ssq_select_type[item.type];
}

/*---------------------------------------------福彩3D------------------------------------------------*/

function _create3DNums(item) {	
	
	// 组装nums
	var nums = "<span style=\"padding-right:0px;\">";
	// 直选
	if (item.type == 0) {			
		ss = item.bai.split(",");
        nums += "<span style='color:red;'>"
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];			
		}
		nums += "|</span><span style='color:blue;'>";
		ss = item.shi.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
		}
		nums += "|</span><span style='color:black;'>";
		ss = item.ge.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
		}
        nums += "|</span>";
	} 
	// 非直选
	else {
		ss = item.balls.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
			if (k != ss.length - 1) {
				nums += " ";
			}
		}
	}	
	nums += "</span>";		
	return nums;
}

function _creat3DDesc(item){
	if(item.type == 0){
		return "直选单式";
	}else if(item.type == 1){
		return "组三单式";
	}else {
		return "组六单式";
	}
}

/*---------------------------------------------大乐透------------------------------------------------*/

function _createDLTNums(item) {
	
	// 组装nums
	var nums = "<span style=\"padding-right:0px;\">";
	// 标准玩法
	if (item.type == 0) {
		// 获取红球
		ss = item.red.split(","); 
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k] + " ";
			
		}
		nums += "</span>";
		nums += _spanBlue();
		ss = item.blue.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k] + " " ;
		
		}		
	} 
	// 胆拖玩法
	else if (item.type == 1) { 
		ss = item.red_before.split(",");
		nums += "(";
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
			if (k != ss.length - 1) {
				nums += " ";
			}
		}
		nums += ") ";
		ss = item.red_after.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k]+ " ";			
		}
		nums += "</span>";
		nums += _spanBlue();
		// 如果没有后去胆码则不显示 wangxing
		if(item.blue_before != null && item.blue_before != "") {			
			nums += "(";
			ss = item.blue_before.split(",");
			for ( var k = 0; k < ss.length; k++) {
				nums += ss[k];
				if (k != ss.length - 1) {
					nums += " ";
				}
			}
			nums += ") ";
		}
		ss = item.blue_after.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k] + " ";
		}
	}
	nums += "</span>";	
	return nums;	
}

function _creatDLTDesc(item){
	var ret = "";	
	if (item.type == 0 && item.totalstake == 1) {
		ret = "标准投注";
	} else if (item.type == 0 && item.totalstake > 1) {
		ret = "复式投注";
	} else if (item.type == 1) {
		ret = "胆拖投注";
	}	
	return ret;
}


/*---------------------------------------------排列三------------------------------------------------*/

function _createPL3Nums(item) {
	
	// 组装nums
	var nums = "<span style=\"padding-right:0px;\">";
	// 直选
	if (item.type == 0) {
		ss = item.bai.split(",");
        nums += "<span style='color:red;'>"
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
		}
		nums += "|</span><span style='color:blue;'>";
		ss = item.shi.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
		}
        nums += "|</span><span style='color:#000000;'>";
		ss = item.ge.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
		}
        nums+="</span>"
	}
	// 非直选
	else {
		ss = item.balls.split(",");
		for ( var k = 0; k < ss.length; k++) {
			nums += ss[k];
			if (k != ss.length - 1) {
				nums += " ";
			}
		}
	}
	nums += "</span>";	
	
	return nums;
}


/*---------------------------------------------排列五------------------------------------------------*/

function _createPL5Nums(item) {	
	
	// 组装nums
	var nums = "<span style=\"padding-right:0px;\"><span style='color:red;'>";
	nums += item.fifth+"|</span><span style='color:blue;'>"+item.forth+"|</span><span style='color:#000000;'>"+item.third+"|</span><span style='color:red;'>"+item.second+"|</span><span style='color:blue;'>"+item.first+"</span>";
	nums += "</span>";		
	return nums;
}

function _createPL5Desc(item) {			
	return (item.totalstake == 1) ? "单式投注" : "复式投注";
}

/*---------------------------------------------七星彩------------------------------------------------*/
function _createQXCNums(item) {	
	
	// 组装nums
	var nums = "<span style=\"padding-right:0px;\">";
	ss = item.first.split(',');
    nums += "<span style='color: #ff0000'>"
	for ( var k = 0; k < ss.length; k++) {
		nums += ss[k];
		if (k != ss.length - 1) {
				nums += ",";
			}
	}
	nums += "|</span><span style='color:#0000ff'>";

	ss = item.second.split(',');
	for ( var k = 0; k < ss.length; k++) {
		nums += ss[k] ;
			if (k != ss.length - 1) {
				nums += ",";
			}
	}
	nums += "|</span><span style='color:#000000'>";

	ss = item.third.split(',');
	for ( var k = 0; k < ss.length; k++) {
		nums += ss[k];
			if (k != ss.length - 1) {
				nums += ",";
			}
	}
	nums += "|</span><span style='color: #ff0000'>";

	ss = item.forth.split(',');
	for ( var k = 0; k < ss.length; k++) {
		nums += ss[k];
			if (k != ss.length - 1) {
				nums += ",";
			}
	}
	nums += "|</span><span style='color:#0000ff'>";

	ss = item.fifth.split(',');
	for ( var k = 0; k < ss.length; k++) {
		nums += ss[k];
			if (k != ss.length - 1) {
				nums += ",";
			}
	}
	nums += "|</span><span style='color:#000000'>";

	ss = item.sixth.split(',');
	for ( var k = 0; k < ss.length; k++) {
		nums += ss[k];
			if (k != ss.length - 1) {
				nums += ",";
			}
	}
	nums += "|</span><span style='color: #ff0000'>";;

	ss = item.seventh.split(',');
	for ( var k = 0; k < ss.length; k++) {
		nums += ss[k];
			if (k != ss.length - 1) {
				nums += ",";
			}
	}
	nums += "</span></span>";
		
	return nums;
}

/*---------------------------------------------新时时彩------------------------------------------------*/

function _createXSSCNums(item) {
	
	// 组装nums
	var nums = "";			
	if(item.zu.length > 0) {
		item.zu.sort();
		nums += "<span style=\"padding-right:0px;\">";
		nums += item.zu;	
		nums += "</span>";	
	} else {
		if(item.type == 11) {
			nums += "<span style=\"padding-right:0px;\">";
			var temp = (item.shi).join(",");
			temp = temp.replace("0", "大");
			temp = temp.replace("1", "小");
			temp = temp.replace("2", "单");
			temp = temp.replace("3", "双");
			nums += temp;
			nums += "</span>";
			
			temp = (item.ge).join(",");
			temp = (temp).replace("0", "大");
			temp = temp.replace("1", "小");
			temp = temp.replace("2", "单");
			temp = temp.replace("3", "双");
			
			nums += "<span style=\"color:#00f;padding-right:0px;\">|";
			nums += temp ;	
			nums += "</span>";
		} else {			
			if (item.wan.length>0) {
				
				item.wan.sort();	
				nums += "<span style=\"padding-right:0px;\">";
				nums += item.wan;
				nums += "</span>";
				
			} else if (item.type == 13 || item.type == 14){
				nums += "<span style=\"padding-right:0px;\">";
				nums += "-";
				nums += "</span>";
			}
			if(item.qian.length>0) {
				item.qian.sort();
				if(item.wan.length>0 || item.type == 13 || item.type == 14) {
					nums += "<span style=\"color:#00f;padding-right:0px;\">|";
				} else {
					nums += _spanBlue();
				}
				nums += item.qian;
				nums += "</span>";
			}else if (item.type == 13 || item.type == 14){
				nums += "<span style=\"color:#00f;padding-right:0px;\">|-</span>";
			}
			
			if(item.bai.length>0) {
				item.bai.sort();
				if(item.qian.length>0 || item.type == 13 || item.type == 14) {
					nums += "<span style=\"color:black;padding-right:0px;\">|";
				} else {
					nums += "<span style=\"color:black;padding-right:0px;\">";
				}					
				nums += item.bai;
				nums += "</span>";
			} else if (item.type == 13 || item.type == 14){
				nums += "<span style=\"color:black;padding-right:0px;\">|-</span>";
			}
			if (item.shi.length>0) {
				item.shi.sort();
				if(item.bai.length>0 || item.type == 13 || item.type == 14) {
					nums += "<span style=\"padding-right:0px;\">|";
				} else {
					nums += "<span style=\"padding-right:0px;\">";
				}					
				nums += item.shi;
				nums += "</span>";
			}else if (item.type == 13 || item.type == 14) {
				nums += "<span style=\"padding-right:0px;\">|-</span>";
			}
			if(item.ge.length>0) {
				item.ge.sort();
				if(item.shi.length>0 || item.type == 13 || item.type == 14) {
					nums += "<span style=\"color:#00f;padding-right:0px;\">|";
				} else {
					nums += _spanBlue();
				}
				nums += item.ge;
				nums += "</span>";
			} else if (item.type == 13 || item.type == 14) {
				nums += "<span style=\"color:#00f;padding-right:0px;\">|-</span>";
			}				
		}	
	}
	
	return nums;	
}

function _creatXSSCDesc(item){	
	return xssc_select_type[item.type];
}

/*---------------------------------------------快三------------------------------------------------*/
function _createK3Nums1(item){
	var nums = "";
	var _balls1 = item.balls1;
	if (_balls1 ===null || _balls1 === undefined || _balls1.length == 0){
		return nums;
	}
	for ( var k = 0; k < _balls1.length; k++) {
		nums += _balls1[k];
		if(Number(item.type) == 3){
			nums +="*";
		}
		if (k != _balls1.length - 1) {
			nums += " ";
		}
	}
	return nums;
}

function _createK3Nums2(item){
	var sub_type = item.subType;
	if(sub_type === null || sub_type === undefined || sub_type == 0){
		return _createK3Nums1(item);
	}
	var nums = "";
	var _balls2 = item.balls2;
	var _balls3 = item.balls3;
	if (_balls2 ===null || _balls2 === undefined || _balls2.length == 0){
		return nums;
	}
	nums += "(";
	for ( var k = 0; k < _balls2.length; k++) {
		nums += _balls2[k];
		if (k != _balls2.length - 1) {
			nums += " ";
		}
	}
	nums += ") ";
	if(_balls3.length > 0){		
		for ( var k = 0; k < _balls3.length; k++){
			nums += _balls3[k];
			if (k != _balls3.length - 1) {
				nums += " ";
			}
		}
	}	
	return nums;
}

function _createK3Nums(item) {	
	// 组装nums
	var nums = "";
	var type = item.type;	
	nums += "<span>";
	switch (Number(type)) {
	// 和值投注
	case 1:
		nums += _createK3Nums1(item);
		break;
	// 二同号单选
	case 2:
		var _balls2 = item.balls2;
		var _balls3 = item.balls3;
		if (_balls2 ===null || _balls2 === undefined || _balls2.length == 0){
			return;
		}
		for ( var k = 0; k < _balls2.length; k++) {
			nums += _balls2[k];
			if (k != _balls2.length - 1) {
				nums += " ";
			}
		}
		if(_balls3.length > 0){
			nums +="#";
			for ( var k = 0; k < _balls3.length; k++){
				nums += _balls3[k];
				if (k != _balls3.length - 1) {
					nums += " ";
				}
			}
		}		
		break;
	// 二同号复选
	case 3:
		nums += _createK3Nums1(item);
		break;
	// 三同号单选
	case 4:
		nums += _createK3Nums1(item);
		break;
	// 三同号通选
	case 5:
		nums += "三同号通选";
		break;
	// 二不同号(单选、胆拖）
	case 6:
		nums += _createK3Nums2(item);
		break;
	// 三不同号(单选，胆拖）
	case 7:
		nums += _createK3Nums2(item);
		break;
	// 三连号通选
	case 8:
		nums += "三连号通选";
		break;
	default:
		break;
	}
	
	nums += "</span>";	
	return nums;	
}

function _creatK3Desc(item){		
	return k3_select_type[item.type];
}


/** ****************************************************************************************** */

function _getCreateNumsMethod(){
	
	switch(Number(lotteryCategory))
	{
		case 1:
			return _creatSSQNums;
		  break;
		case 2:
			return _create3DNums;
		  break;
		case 4:
			return _createDLTNums;
		  break;
		case 5:
			return _createPL3Nums;
		  break;
		case 6:
			return _createPL5Nums;
		  break;
		case 7:
			return _createQXCNums;
		  break;
		case 1002:
			return _createXSSCNums;
		  break;
		case 1004:
			return _createK3Nums;
		  break;
		default:
			return  undefined;
	}	
	
}

function _getCreateDescMethod(){
	
	switch(Number(lotteryCategory))
	{
		case 1:
			return _creatSSQDesc;
		  break;
		case 2:
			return _creat3DDesc;
		  break;
		case 4:
			return _creatDLTDesc;
		  break;
		case 5:
			return _creat3DDesc;
		  break;
		case 6:
			return _createPL5Desc;
		  break;
		case 7:
			return _createPL5Desc;
		  break;
		case 1002:
			return _creatXSSCDesc;  
		  break;
		case 1004:
			return _creatK3Desc;
		  break;
		default:
			return  undefined;
	}	
	
}

function _spanBlue(){
	return "<span style=\"color:#00f;padding-right:0px;\">";
}

function _returnIndexPage(message){
	return '<a href="/xfg_cp_client//lottery/lotteryInfo.action?gameid=3" class="link-more">'+message+'</a>';
	
}

/**
 * 设置列表显示类型
 * 
 * @param type
 *            0-显示全部 1-显示前N条
 */
function _setShowType(type){
	sessionStorage.setItem("lottery_pre_show_type",type);
}

/**
 * 获取列表显示类型
 * 
 * @returns 0-显示全部 ; 1-显示前N条
 */
function _getShowType(){
	var ret = sessionStorage.getItem("lottery_pre_show_type");	
	if( ret === undefined || ret === null){		
		_setShowType(0);
		return 0;
	}else{
		return ret;
	}	
}

/**
 * 获取"x注 y元"文本
 * 
 * @param item
 *            lotteryObj.lottery[i]对象
 * @returns {String} 返回"x注 y元"文本
 */
function _createTotalText(item){
	var tempStake = 0;
	var totalMoney = 0;
	if(lotteryCategory == 1004){
		tempStake = Number(item.count);		
	}else{
		tempStake = Number(item.totalstake);
	}	
	if(!isNaN(tempStake)){
		totalMoney = tempStake*2;
	}else{
		tempStake = 0;
	}
	return tempStake + "注 " + totalMoney + "元";
}

/**
 * 创建"更多投注"或者"收起投注列表"的超链接
 * 
 * @returns 返回超链接片段
 */
function _creatMoreOrLessHref(showType){
	var ret = null;
	if (showType == 0){
		ret = '<a href="javascript:void(0)" onclick="changeShowType()" class="link-more">更多投注》</a>';	
	}else{
		ret = '<a href="javascript:void(0)" onclick="changeShowType()" class="link-more">收起投注列表》</a>';	
	}
	return ret;
}

function _notFoundMethod(){
	return undefined;
}

/**
 * 创建选号号码的Div片段
 * 
 * @param nums
 *            选号号码span片段
 * @param desc
 *            选号号码的玩法描述文本
 * @param total
 *            选号号码的注数和价格 "x注 y元"文本
 * @param num
 *            选号号码在loterryObject对象中的位置，用于删除功能的索引
 * @param notAddDel
 *            传1表示不添加删除按钮，非1则添加
 * @returns {String} 返回选号号码的Div片段
 */
function _createDiv(nums,desc,total,num,notAddDel,isShow){
	var strDiv = "";
    if(isShow){
        strDiv += '<div class="new-tbl-type" id="numberrow'+ num +'">';
    }else{
        strDiv += '<div class="new-tbl-type" style="display: none;" id="numberrow'+ num +'">';
    }

	strDiv += '<div class="new-tbl-cell w80">';
	strDiv += '<div class="choose-box">';
	strDiv += '<div class="choose-num">'+nums+'</div>';
	strDiv += '<div class="total"><span>'+desc+'</span><span class="new-fr">'+total+'</span></div>';
	strDiv += '</div></div>'; 
	if( notAddDel === undefined || notAddDel !== 1){
		strDiv += '<div class="new-tbl-cell">'; 
		strDiv += '<a href="javascript:void(0);" onclick="deleteRow('+num+')" class="btn-del">删除</a>'; 
		strDiv += '</div>';
	}	
	strDiv += '</div>';	
	return strDiv;
}
/**
 * 
 * @param item
 *            lotteryObj.lottery[i]对象
 * @param createNumsMethod
 *            生成选号号码span片段的方法名
 * @param createDescMethod
 *            生成选号号码玩法描述文本的方法名
 * @param notAddDel
 *            传1表示不添加删除按钮，不传或非1则添加
 * @returns 返回一行选号号码的DIV片段
 */
function _createDivTemplete(item,num,createNumsMethod,createDescMethod,notAddDel,isShow){
	
	if(createNumsMethod === undefined || createDescMethod === undefined){
		return "";
	}
	
	var nums = createNumsMethod(item);
	
	var desc = createDescMethod(item);
	
	var total = _createTotalText(item);	
	
	return _createDiv(nums,desc,total,num,notAddDel,isShow);
	
}

/** ******************************************public********************************************************* */




/**
 * 改变选号列表显示类型 在"更多投注"和"收起投注列表"之间切换 0-更多投注 1-收起投注列表 改变后需要重新加载页面
 */
function changeShowType(){
	if (_getShowType() == 0){
		_setShowType(1);
        $("#numberInfo .new-tbl-type").each(function(index,item){
            if(index > 4){
                $(item).css("display","none");
            }
        });
        $("#numberInfo .link-more").text("更多投注>>");
	}else{
		_setShowType(0);
        $("#numberInfo .new-tbl-type").each(function(index,item){
            if(index > 4){
                $(item).css("display","");
            }
        });

        $("#numberInfo .link-more").text("收起投注列表>>");
	}
}

/** 确认投注页号码列表 */
function creatPreDivList() {
	var arr = [];
	var item, ss;
	
	var arrLen = null;
	if(lotteryCategory == 1004){
		arrLen = lotteryObj.infos.length;
	}else{
		arrLen = lotteryObj.lottery.length;
	}

	var forCount = 0;
	
	var createNumsMethod = _getCreateNumsMethod();
	var createDescMethod = _getCreateDescMethod();	
	if(createNumsMethod === undefined || createDescMethod === undefined){
		return _returnIndexPage("未知彩种，请您返回彩票首页重新购彩！");
	}
	
	try{
		for ( var i=arrLen-1; i>=0;i--){		
			// 只显示前5条，添加"更多投注"超链接，并跳出循环
            var isShow = true;
			if(arrLen > 5 && forCount >= 5){
                isShow = false;
			}
			var num = 0;
			if(lotteryCategory == 1004){			
				item = lotteryObj.infos[i];
				num = i;
			}else{
				item = lotteryObj.lottery[i];
				num = item.num;
			}
			
			arr.push(_createDivTemplete(item,num,createNumsMethod,createDescMethod,undefined,isShow));
			forCount++;
		}
		// 显示全部号码且列表大于5，添加"收起列表"的超链接
		if(arrLen > 5){
            /*默认设置为隐藏*/
            _setShowType(1);
			arr.push(_creatMoreOrLessHref(0));
		}	
	}catch(e){
		alert(e.message);
	}
	
		
	return arr.join("");
}


function createPreTotalSpan(){
	var zhushu =  caltotalstake();
	var qishu = $("#issue").val();
	var beishu = $("#multi").val();
	var zong = zhushu*qishu*beishu*2;
		
	var span = '总金额:<span class="new-txt-rd2">';
	span += zong;
	span += '元</span><span class="new-fr">';
	span +=	zhushu;
	span += '注';
	span +=	qishu;
	span += '期';
	span +=	beishu;
	span +=	'倍</span>';	
	return span;
}
	
function loadPayTotalInfo(){
	$("#payTotal").html(createPreTotalSpan());
}

function loadListInfo(){
	$("#numberInfo").html(creatPreDivList());	
}

function isEmpty(){
	var item = null;
	if(lotteryCategory == 1004){
		item = lotteryObj.infos;
	}else{
		item = lotteryObj.lottery;
	}
	if( item === null || item === undefined || item.length == 0){
		return true;
	}else{
		return false;
	}
}



/** 加载页面 */
function loadPageInfo(){
	loadBaseInfo();
	loadListInfo();	
	loadPayTotalInfo();
	if(isEmpty()){
		disableButton($("#clear-all"));
	}else{
		enableButton($("#clear-all"),alertTip2);
	}
}

/**
 * 删除1注选号
 * 
 * @param num
 *            选号的
 */
function deleteRow(num){
	deleteLotteryItem1(num);
	//loadPageInfo();
    loadPayTotalInfo();
    if(isEmpty()){
        disableButton($("#clear-all"));
    }else{
        enableButton($("#clear-all"),alertTip2);
    }

    /*刪除当前行*/
    $("#numberrow" + num).remove();
    $("#numberInfo .new-tbl-type").each(function(index,item){
        /*有可能之前隐藏的展示出来*/
        if(index < 5){
            $(item).css("display","");
        }
    });

    /*只剩下五个，隐藏更多*/
    if($("#numberInfo .new-tbl-type").size() < 6){
        $("#numberInfo .link-more").hide();
    }
}



/**
 * 检查注数和总金额是否超过标准
 */
function checkTotal(){
	var totalstake = caltotalstake();
	var multi = $("#multi").val();
	var issue = $("#issue").val();	
	
	if(isNaN(Number(multi)) || multi === null || multi === undefined || multi =="" ){		
		$("#multi").val("1");
		multi=1;
	}else{
		multi = Number(multi);
	}
	if(isNaN(Number(issue))|| issue === null || issue === undefined || issue ==""){
		$("#issue").val("1");
		issue=1;
	}else{
		issue = Number(issue);
	}
	//return false;
	var total = totalstake * multi * issue*2;
	
	if( total > maxAmount){		
		alertTip1('投注金额已经达到'+maxAmount+'元,无法继续选号');
		return false;
	}
	
   if ( (lotteryCategory == 1002 || lotteryCategory == 1004 )&& totalstake > 500) {
	  	alertTip1('投注注数已经达到500注,无法继续选号');
		return false;		   
   }	
   return true;
}

function get_one3D(){
	var onezhu = Math.floor(Math.random()*1000);
	if(onezhu < 10 ){
		onezhu = "00" + onezhu;
	}
	else if(onezhu< 100){
		onezhu = "0" + onezhu;
	}
	onezhu = new String(onezhu);
	var balls = onezhu.split("");
	var item = {
        "type":0,
        "bai":balls[0],
        "shi":balls[1],
        "ge":balls[2],
        "totalstake":1
	};
	addLotteryItem(item);	
}

// 排列3机选1注并放到sessionStorage
function get_onePL3(){
	var onezhu = Math.floor(Math.random()*1000);
	if(onezhu < 10 ){
		onezhu = "00" + onezhu;
	}
	else if(onezhu< 100){
		onezhu = "0" + onezhu;
	}
	onezhu = new String(onezhu);
	var balls = onezhu.split("");
	var item = {
        "type":0,
        "bai":balls[0],
        "shi":balls[1],
        "ge":balls[2],
        "totalstake":1
	};
	addLotteryItem(item);	
}

// 排列5机选1注并放到sessionStorage
function get_onePL5(){
	var onezhu = Math.floor(Math.random()*100000);
	if(onezhu < 10 ){
		onezhu = "0000" + onezhu;
	}
	else if(onezhu< 100){
		onezhu = "000" + onezhu;
	}
	else if(onezhu< 1000){
		onezhu = "00" + onezhu;
	}
	else if(onezhu< 10000){
		onezhu = "0" + onezhu;
	}
	onezhu = new String(onezhu);
	var balls = onezhu.split("");
	var item = {
        "type":0,
        "fifth":balls[0],
        "forth":balls[1],
        "third":balls[2],
        "second":balls[3],
        "first":balls[4],
        "totalstake":1
	};
	addLotteryItem(item);	
}

// 七星彩随机生成一注
function get_oneQXC(){
var str="";
for ( var i = 0; i < 7; i++) {
		var randomNumber=parseInt(Math.random() * 10);
		str+=randomNumber;		
}
	var balls = str.split("");
	var item = {
        "first":balls[0],
		"second":balls[1],
		"third":balls[2],
		"forth":balls[3],
		"fifth":balls[4],
		"sixth":balls[5],
		"seventh":balls[6],
		"totalstake":1
	};
	addLotteryItem(item);	
}

function alertTip1(message){
	alertPopCenter();
	$("#alertPopHtml").html("<center>"+message+"</center>");
	$("#shade").toggle();
	$("#alertPop").toggle();
	
}

function closeAlertTip1(){	
	$("#shade").toggle();
	$("#alertPop").toggle();
}



function alertTip2(){		
	confirmPopCenter();	
	$("#confirmPopHtml").html("<center>是否清空投注列表？</center>");	
	$("#shade").toggle();
	$("#confirmPop").toggle();	
}
// 新时时彩随机生成一注
function get_oneXSSC(){
	if(lotteryObj.lottery.length > 0){
    	sessionStorage.setItem("xsscType", lotteryObj.lottery[lotteryObj.lottery.length-1].type);	
    }else{
    	sessionStorage.setItem("xsscType", 5);
    }
	// 新建时时彩选好信息对象,取得最后一次选号方式
	var info = new Lottery1002.Xssc.info({
		type : sessionStorage.getItem("xsscType")
	});	
	info.generateAll();
	var xsscItem = {
			"type":info.type,
			"wan":info.balls5,
			"qian":info.balls4,
			"bai":info.balls3,
			"shi":info.balls2,
			"ge":info.balls1,
			"zu":info.balls0,
			"totalstake":info.count
		};
	addLotteryItem(xsscItem);
	
	
	
	
}

function getK3_subType(){
	var _subType = 0;
	if(lotteryObj.infos.length !== null && lotteryObj.infos.length !== undefined && lotteryObj.infos.length > 0){
		_subType = lotteryObj.infos[lotteryObj.infos.length-1].subType;
	}	
	return _subType;
}

function get_oneK3(){
	var type = 1;
	var subType = 3;
	if(lotteryObj.infos.length !== null && lotteryObj.infos.length !== undefined && lotteryObj.infos.length > 0){
		type = lotteryObj.infos[lotteryObj.infos.length-1].type;
		if(type != 1){
			subType = 0;
		}
	}	
		
		var info = new Lottery.K3.info({
			type : type,
			subType : subType
		});		
		info.generate();
		addLotteryItem(info);
}
/**
 * 随机1注算法
 */
function addRandom(){
	if(checkTotal()){
		switch(Number(lotteryCategory)){
		 	case 1:
		 		ball = new LotterySSQ();
		 		ball.get_oneFs1(1);
			break;
			case 2:
				get_one3D();
			break;
			case 4:
				ball = new LotteryDLT();
				ball.random_fs(1);
			break;
			case 5:
				get_onePL3();
			break;
			case 6:
				get_onePL5();
			break;
			case 7:
				get_oneQXC();
			break;
		    case 1002:
		    	get_oneXSSC();			
			break;
		    case 1004:
		    	get_oneK3();
		    break;
		}
	}
}

/**
 * 添加手选
 */
function addByUser(){	
	if(checkTotal()){
		window.location.href = $("#addByUserHref").val();
	}
}

/**
 * 倍数输入框keyup事件
 */
function multiEvent(){
	var multi = $("#multi").val();
	multi = multi.replace(/\D/g, "");
	if(multi.length > 0){
		multi = parseInt((9+""+multi)-9*Math.pow(10,multi.length));  
	}	  
	if(multi == "0")
	{
		multi = "1";
	}else if(multi > 50)
	{
		multi = 50;
	}else if(multi < 0)
	{
		multi = 1;
	}
	$("#multi").val(multi);
	if(lotteryCategory == 1004){
		lotteryObj.muitl = multi;
	}else{
		lotteryObj.multiple = multi;				
	}
	refreshLottery();
}

/**
 * 期数输入框keyup事件
 */
function issueEvent(){
	var issue = $("#issue").val();
	issue = issue.replace(/\D/g, "");
	if(issue.length > 0){
		issue = parseInt((9+""+issue)-9*Math.pow(10,issue.length));  
	}	  
	if(issue == "0")
	{
		issue = "1";
	}else if(issue > 30)
	{
		issue = 30;
	}else if(issue < 0)
	{
		issue = 1;
	}
	$("#issue").val(issue);
	if(lotteryCategory == 1004){
		lotteryObj.append = issue;
	}else{
		lotteryObj.appendissue.nums = issue;				
	}
	refreshLottery();
}

function clearAll(){
	if(lotteryCategory == 1004){
		lotteryObj.infos = [];
	}else{
		lotteryObj.lottery = [];
		lotteryObj.count = 0;		
	}
	refreshLottery();
}

function loadBaseInfo(){
	// 追号终止类型 （接口字段（stopflag） 1-中奖即停追 3-不停追
	var appendType = 1;
	// 期数
	var issue = 1;
	// 倍数
	var multi = 1;
	
	if(lotteryCategory == 1004){
		appendType = lotteryObj.appendType;
		issue = lotteryObj.append;
		multi = lotteryObj.muitl;
	}else{
		appendType = lotteryObj.appendissue.stopflag;
		issue = lotteryObj.appendissue.nums;
		multi = lotteryObj.multiple;
	}
	$("#issue").val(issue);
	$("#multi").val(multi);
	$("#appendType").val(appendType);
	if(appendType == 3){
		$("#issueType").removeClass("on");
	}else{
		$("#issueType").removeClass("on");
		$("#issueType").addClass("on");
	}
	
}

function changeIssueType(){
	if($("#appendType").val() == 3){
		$("#appendType").val("1"); 
		$("#issueType").removeClass("on");
		$("#issueType").addClass("on");
	}else{
		$("#appendType").val("3");
		$("#issueType").removeClass("on");
	}
	if(lotteryCategory == 1004){
		lotteryObj.appendType = $("#appendType").val();
	}else{
		lotteryObj.appendissue.stopflag = $("#appendType").val();
	}	
	refreshLottery();
}

function changeIAgreementType(){
	if($("#iAgreementFlag").val() == 0){
		$("#iAgreementFlag").val("1"); 
		$("#iAgreement").removeClass("on");
		$("#iAgreement").addClass("on");
	}else{
		$("#iAgreementFlag").val("0");
		$("#iAgreement").removeClass("on");
	}
}

function checkIAgreement(){
	if($("#iAgreementFlag").val() == 1){
		return true;
	}
	alertTip1("抱歉您未勾选客户端购彩协议");	
	return false;	
} 


function disableButton(btn){
	removeButtonClass(btn);
	btn.addClass("btn-type5");  
	btn.unbind();
}

function enableButton(btn,clickFunction,btnClass){
	removeButtonClass(btn);
	if(btnClass !== null && btnClass !== undefined){
		btn.addClass(btnClass);
	}else{
		btn.addClass("btn-type1"); 
	}	
	btn.unbind();
	btn.bind("click",clickFunction);
}

function removeButtonClass(btn){
	btn.removeClass("btn-type1");
	btn.removeClass("btn-type2");
	btn.removeClass("btn-type5");
}

function clearAllEvent(){	
		disableButton($("#clear-all"));
		clearAll();
		loadPageInfo(); 
}

function submitEvent(){	
	if(!checkIAgreement()){		
		return false;
	}
	var flag = true;
	var jsonstr = sessionStorage.getItem("lottery_" + lotteryCategory);
	if (!!!jsonstr) {
		alertTip1("该号码已投注成功<br />请重新选号");		
		flag =  false;
	}else if(isEmpty()){
		alertTip1("号码为空，请先选号");
		flag =  false;
	}else if(!checkTotal()){
		flag =  false;
	}
 	if(flag){
 		multiEvent();
 	 	issueEvent();	 	 	
 		$("#baseForm").submit();
 	} 	
}

function returnIndexEvent(){
	if(alertTip12("返回首页！")){
		return true;
	}else{
		return false;
	}
}
function alertMessage1(alertText){
	alertPopCenter();
	$("#alertPopHtml").html("<center>" + alertText +"</center>");
	$("#shade").toggle();
	$("#alertPop").toggle();
}
/** **************************** init method ********************************* */
   $(document).ready(function(){
	   
	   var alertMessage = $("#alertMessage").val();
		if(alertMessage!="" &&  alertMessage.length>0){
			alertMessage1(alertMessage);
		}
	   
	   	// 删除用户信息
        sessionStorage.removeItem("lotteryUserInfo");
        // 获取彩种类型
        var type = $("#lotteryCategory").val();        
        // 根据彩种创建LotteryObj对象
        createLotteryObj(type);        
        // 加载页面
        loadPageInfo();
        
        
        // 添加机选按钮绑定onclick事件
        if(type == 1004 && getK3_subType() == 2) {
        	disableButton($("#btn-random1"));        	   
        }else{
        	$("#btn-random1").click(function(){
            	addRandom();
            	loadPageInfo();
            	enableButton($("#clear-all"),alertTip2);
            }); 
        }
        
	 
        // 添加手选按钮绑定onclick事件
        $('#go-on-select').click(function(){
        	addByUser();
        });
		 
		// 切换中奖停追类型按钮绑定onclick事件
		 $("#issueType,#issueType_span").click(function(){
			 changeIssueType();
		 });
		 
		// 勾选同意购彩协议按钮onclick事件
		 $("#iAgreement").click(function(){
			 changeIAgreementType();
		 });
	 
		 // 倍数输入框绑定keyup事件
		 $("#multi").keyup(function(){
			 multiEvent();			 
			 loadPayTotalInfo(); 
			 			 
		 });
		 
		 // 倍数输入框绑定blur事件
		 $("#multi").blur(function(){
			 multiEvent();			 
			 loadPayTotalInfo(); 
		 });
		 
		 // 期数输入框绑定keyup事件
		 $("#issue").keyup(function(){
			 issueEvent();			 
			 loadPayTotalInfo(); 
		 });
		 
		 // 期数输入框绑定blur事件
		 $("#issue").blur(function(){
			 issueEvent();			 
			 loadPayTotalInfo(); 
		 });
		 
		 if($("#canSell").val() == 0  || (alertMessage!="" &&  alertMessage.length>0)){
			 disableButton($("#sub_btn"));
		 }else{
			// 绑定立即投注按钮
			 $("#sub_btn").click(submitEvent);
		 }
		 
		// 确认
			$("#confirmPopTzButton").bind("click", function() {
				$("#shade").toggle();
				$("#confirmPop").toggle();
				clearAllEvent();		
			});
			
		//取消
		$("#confirmPopBqButton").bind("click", function() {
					$("#shade").toggle();
					$("#confirmPop").toggle();
				});
		 
// $(".new-a-back").click(returnIndexEvent);
		 
	});

