var lotteryCategory;
var lotteryObj;
var maxAmount = 20000;// 最大投注金额
var ssqLotteryObj;
var ssq_select_type = [ "标准投注", "复式投注", "胆拖投注" ];

var fc3d_select_type = [ "直选", "组三", "组六" ];
var pl3_select_type = [ "直选", "组三", "组六" ];
var dlt_select_type = [ "标准投注", "胆拖投注", "生肖乐" ];
var xssc_select_type = ["","一星直选","二星直选","三星直选","四星直选","五星直选","","","","","二星组选","大小单双","五星通选","任选一","任选二","三星组三","三星组六","","","","","三星和值","二星直选和值","二星组选和值"];
var k3_select_type = ["","和值","二同号单选","二同号复选","三同号单选","三同号通选","二不同号","三不同号","三连号通选"];

var lotteryCategoryList = {
	"SSQ" : 1, // 双色球
	"3D" : 2, // 3D
	"QLC" : 3, // 七乐彩
	"DLT" : 4, // 大乐透
	"PL3" : 5, // 排列3
	"PL5" : 6, // 排列5
	"QXC" : 7, // 七星彩
	"SXL" : 8, // 生肖乐
	"XSSC": 1002, //新时时彩
	"K3" :  1004,  //快三
	"JCZQ" : 301  //竞彩足球
};

/** 根据类型获得Lottery */
function createLotteryObj(type) {	
	lotteryCategory = type;
	var jsonstr = sessionStorage.getItem("lottery_" + lotteryCategory);
	var xsscInfos = sessionStorage.getItem("xsscInfos");
	var jczq_matchinfo = sessionStorage.getItem("jczq_matchinfo");
	if (!!jsonstr) {
		createObj = function() {
			lotteryObj = JSON.parse(jsonstr);
			if (type == 1002 && !!xsscInfos) {
				var xsscInfosStr = JSON.parse(xsscInfos);
				for(var i=0; i < xsscInfosStr.length; i++) {
					var item = {
							"type":xsscInfosStr[i].type,
							"wan":xsscInfosStr[i].balls5,
							"qian":xsscInfosStr[i].balls4,
							"bai":xsscInfosStr[i].balls3,
							"shi":xsscInfosStr[i].balls2,
							"ge":xsscInfosStr[i].balls1,
							"zu":xsscInfosStr[i].balls0,
							"totalstake":xsscInfosStr[i].count
						};
					addLotteryItem(item);
				}
			    sessionStorage.removeItem("xsscInfos");
			    if(lotteryObj.lottery.length > 1){
			    	sessionStorage.setItem("xsscType", lotteryObj.lottery[lotteryObj.lottery.length-1].type);	
			    }else{
			    	sessionStorage.setItem("xsscType", 5);
			    }
			    			
			}
			return lotteryObj;
		};
		return createObj();
	}else if(!!xsscInfos && type == 1002) {
		createObj = function() {
			lotteryObj = {
					"count" : 0, // 注数计数
					"orderType" : 0, // 追号
					"multiple" : 1, // 倍数
					"lottery" : [],
					"appendissue" : {
						"nums" : 1,
						"shownums" : 5,
						"stopflag" : 1,
						"stopaward" : 0,
						"appendissueinfo" : []
					}
			    };
			var xsscInfosStr = JSON.parse(xsscInfos);
			for(var i=0; i < xsscInfosStr.length; i++) {
				var item = {
						"type":xsscInfosStr[i].type,
						"wan":xsscInfosStr[i].balls5,
						"qian":xsscInfosStr[i].balls4,
						"bai":xsscInfosStr[i].balls3,
						"shi":xsscInfosStr[i].balls2,
						"ge":xsscInfosStr[i].balls1,
						"zu":xsscInfosStr[i].balls0,
						"totalstake":xsscInfosStr[i].count
					};
				addLotteryItem(item);
			}
		    sessionStorage.removeItem("xsscInfos");
		    sessionStorage.setItem("xsscType", lotteryObj.lottery[lotteryObj.lottery.length-1].type);			
			return lotteryObj;
		};
		return createObj();
	}else if (type == 1004){		
		createObj = function() {
			if( jsonstr === null || jsonstr === undefined){
				lotteryObj = new Lottery.K3({});
			}else{				
				lotteryObj = JSON.parse(jsonstr);
			}
			return lotteryObj;
		};
		return createObj();
	}else if (type == 301){
		createObj = function() {
			if(jczq_matchinfo === null || jczq_matchinfo === undefined){
				lotteryObj = new Lottery.Jczq({});
			}else{				
				lotteryObj = JSON.parse(jczq_matchinfo);
			}
			return lotteryObj;
		};
		return createObj();		
	}
	createObj = function() {
		lotteryObj = {
			"count" : 0, // 注数计数
			"orderType" : 0, // 追号
			"multiple" : 1, // 倍数
			"lottery" : [],
			"appendissue" : {
				"nums" : 1,
				"shownums" : 5,
				"stopflag" : 3,
				"stopaward" : 0,
				"appendissueinfo" : []
			}
		};
		return lotteryObj;
	};
	return createObj();
}

/** 创建一个空对象 */
function createNullLotteryObj() {
	createNullObj = function() {
		ssqLotteryObj = {
			"count" : 0, // 注数计数
			"orderType" : 0, // 追号
			"multiple" : 1, // 倍数
			"lottery" : [],
			"appendissue" : {
				"nums" : 1,
				"shownums" : 5,
				"stopflag" : 3,
				"stopaward" : 0,
				"appendissueinfo" : []
			}
		};
		return ssqLotteryObj;
	};
	return createNullObj();
}

function refreshLottery() {
	sessionStorage.setItem("lottery_" + lotteryCategory, JSON
			.stringify(lotteryObj));
}

function addLotteryItem(item) {
//	for (var i in item){
//		alert(i + "||" + item[i]);
//	} 
	if(lotteryCategory == 1004){
		lotteryObj.infos.push(item);
	}else{
		lotteryObj.count += 1;
		item["num"] = lotteryObj.count;
		lotteryObj.lottery.push(item);			
	}	
	refreshLottery();
}

function removeLottery(lotteryCategory) {
	sessionStorage.removeItem("lottery_" + lotteryCategory);
}

/* preHTML页面 */
function precardHtml() {
	var str;
	if (lotteryCategory == 1) { // 双色球
		str = getPreSSQCardHtml(true);
	} else if (lotteryCategory == 2) { // 福彩3D
		str = getPreFC3DCardHtml(true);
	} else if (lotteryCategory == 4) { // 大乐透
		str = getPreDLTCardHtml(true);
	} else if (lotteryCategory == 5) {
		str = getPrePL3CardHtml(true);
	}else if(lotteryCategory==6){ // 排列五
		str = getPrePL5CardHtml(true);
	}else if(lotteryCategory == 7){
		str = getPreQXCCardHtml(true);
	} else if(lotteryCategory == 1002) {
		str = getPreXSSCCardHtml(true);
	}
	$("#cardInfo").html(str);
	$("#cardInfo").css("height", "auto");
	

	
	if (!isstake()) {// 玩家没有任何投注
		
		$("#sub_btn").hide();
		$("#wrapper2").height(1);
		$("#numInfo").height(1);
	} else {
		$("#sub_btn").show();
		if(lotteryObj.lottery.length==1) {
			$("#numInfo").height(90);
			$("#wrapper2").height(85);			
		} else if (lotteryObj.lottery.length==2) {
			$("#numInfo").height(180);
			$("#wrapper2").height(175);
		} else {
			$("#numInfo").height(230);
			var platform = navigator.platform;
			if (platform == "iPod" || platform == "iPhone" || platform == "iPad") {
				$("#numInfo").height(250);
				$("#wrapper2").height(245);
			} else {
				$("#wrapper2").height(223);
			}			
		}
	}
	if(lotteryCategory == 1002 && "距离新开期" == $("#issueNameId_1002").text()) {
		$("#sub_btn").hide();
	}
}

/* 确认HTML页面 */
function confirmcardHtml(isFour) {
	var str;
	if (lotteryCategory == 1) { // 双色球
		str = getPreSSQCardHtml(false, isFour);
	} else if (lotteryCategory == 2) { // 双色球
		str = getPreFC3DCardHtml(false, isFour);
	} else if (lotteryCategory == 4) { // 大乐透
		str = getPreDLTCardHtml(false, isFour);
	} else if (lotteryCategory == 5){
		str = getPrePL3CardHtml(false, isFour);//排列三
	}else if(lotteryCategory == 6){// 排列五
		str = getPrePL5CardHtml(false,isFour);
	}else if(lotteryCategory == 7){
		str = getPreQXCCardHtml(false, isFour);
	}else if(lotteryCategory == 1002) {
		str = getPreXSSCCardHtml(false, isFour);
	}else if (lotteryCategory == 1004) {
		str = getPreK3CardHtml(false, isFour);
	}else if (lotteryCategory == 301) {
		str = getPreJczqCardHtml(false, isFour);
	}
	return str;
}

function getPreJczqCardHtml(show, isFour) {
	var arr = [];
	var elements_1 = (lotteryObj.match.elements[0]).value;
	var elements_2 = (lotteryObj.match.elements[1]).value;
	arr.push("<li class=\"nopadding\"><p>2串1 胜平负过关</p><ul class=\"two-row\">");
	arr.push("<li class=\"w-50\">主队 vs 客队</li>");
	arr.push("<li class=\"w-50 n-b\">投注</li>");
	arr.push("<li class=\"w-50 height75\" style=\"line-height:18px;\">");
	arr.push(elements_1[0].split("-")[0]);
	arr.push("<br/>vs<br/>");
	arr.push(elements_1[0].split("-")[1]);
	if(elements_1.length == 2){
		arr.push("<li class=\"w-50 height75\" style=\"line-height:28px;\">");
	} else if(elements_1.length == 3){
		arr.push("<li class=\"w-50 height75\" style=\"line-height:18px;\">");
	} else {
		arr.push("<li class=\"w-50 height75\" style=\"line-height:55px;\">");
	}
	var str = [];
	for ( var i = 0; i < elements_1.length; i++) {
		str.push(elements_1[i].split("-")[2]);
	}
	arr.push(((str.sort(function(x, y){if (x > y)return -1;if (x < y)return 1;})).join("<br/>")).replace("3", "主胜").replace("1", "平局").replace("0", "客胜"));
	arr.push("</li>");
	arr.push("<li class=\"w-50 height75\" style=\"line-height:18px;\">");
	arr.push(elements_2[0].split("-")[0]);
	arr.push("<br/>vs<br/>");
	arr.push(elements_2[0].split("-")[1]);
	if(elements_2.length == 2){
		arr.push("<li class=\"w-50 height75\" style=\"line-height:28px;\">");
	} else if(elements_2.length == 3){
		arr.push("<li class=\"w-50 height75\" style=\"line-height:18px;\">");
	} else {
		arr.push("<li class=\"w-50 height75\" style=\"line-height:55px;\">");
	}
	str = [];
	for ( var i = 0; i < elements_2.length; i++) {
		str.push(elements_2[i].split("-")[2]);
	}
	arr.push(((str.sort(function(x, y){if (x > y)return -1;if (x < y)return 1;})).join("<br/>")).replace("3", "主胜").replace("1", "平局").replace("0", "客胜"));
	arr.push("</li></ul></li>");
	return arr.join("");
}

/* pre总金额 */
function pretotalHtml() {
	var totalstake = caltotalstake();
	var orderType = $("input[name='orderType']:checked").val();
	$("#totalstakes").html(
			"共" + totalstake + "注，共<span class=\"red\">￥"
					+ caltotalpay(totalstake, orderType) + ".00</span>元");
}

function calsinglePay(totalstake, multiple) {
	return parseInt(multiple) * totalstake * 2;
}

function caltotalpay(totalstake, orderType) {
	return caltotalMulti()*caltotalAppend()*totalstake*2;	
}

/** 是否已经投注 */
function isstake() {
	return lotteryObj.lottery.length != 0;
}

function preAppendHtml() {
	var totalstake = caltotalstake();
	var orderType = $("input[name='orderType']:checked").val();
	var total = 0;
	if (orderType == 0) {
		return parseInt(lotteryObj.multiple) * totalstake * 3;
	} else { // 追号
		var countNum = 0;
		var appendissueinfo = lotteryObj.appendissue.appendissueinfo;
		for ( var i = 0; i < appendissueinfo.length; i++) {
			countNum += parseInt(appendissueinfo[i].multiple);
		}
		return countNum * totalstake * 3;
	}

}
/* 计算总注数 */
function caltotalstake() {
	var count = 0;	
	if(Number(lotteryCategory) == 1004){		
		for ( var i = 0; i < lotteryObj.infos.length; i++) {
			count += Number(lotteryObj.infos[i].count);
		}
	}else if(Number(lotteryCategory) == 301){
		count = lotteryObj.count;
	}else{
		for ( var i = 0; i < lotteryObj.lottery.length; i++) {
			count += Number(lotteryObj.lottery[i].totalstake);
		}
	}		
	return count;
}

/* 计算总倍数*/
function caltotalMulti(){
	var count = 1;
	if(Number(lotteryCategory) == 1004){		
		if(isNaN(lotteryObj.muitl)){
			lotteryObj.muitl = 1;
			refreshLottery();
		}else{
			count = lotteryObj.muitl;
		}
	}else if(Number(lotteryCategory) == 301){
		if(isNaN(lotteryObj.multi)){
			lotteryObj.multi = 1;
		}else{
			count = lotteryObj.multi;
		}
	}else{
		if(isNaN(lotteryObj.multiple)){
			lotteryObj.multiple =1;
			refreshLottery();
		}else{
			count = lotteryObj.multiple;
		}						
	}
	return count;
}

/* 计算总追号数*/
function caltotalAppend(){
	
	var count = 1;
	if(Number(lotteryCategory) == 1004){		
		if(isNaN(Number(lotteryObj.append))){
			lotteryObj.append = 1;
			refreshLottery();
		}else{
			count = lotteryObj.append;
		}
	}else if(Number(lotteryCategory) == 301){
		count = 1;
	}else{
		if(isNaN(Number(lotteryObj.appendissue.nums))){
			lotteryObj.appendissue.nums = 1;
			refreshLottery();
		}else{
			count = lotteryObj.appendissue.nums;
		}						
	}		
	return count;
}

/** 获得双色球的信息 */
function getPreSSQCardHtml(show, isFour) {
	var arr = [];
	var item, ss;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		arr.push("<li>");
		arr.push("<div style=\"padding-right:50px\"><div class=\"red\">");
		if (item.type == 0 || item.type == 1) {// 单式,复式
			ss = item.red.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
		} else if (item.type == 2) {// 胆拖
			ss = item.dan.split(",");
			arr.push("(");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k]);
				if (k != ss.length - 1) {
					arr.push(" ");
				}
			}
			arr.push(") ");
			ss = item.tuo.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
		}
		arr.push("<span class=\"blue\">");
		ss = item.blue.split(",");
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}
		arr.push("</span></div></div>");
		arr.push(ssq_select_type[item.type] + "[" + (item.totalstake) + "注]");// 单式投注[1注]
		if (show) {
			arr.push("<a onclick=\"deleteLotteryItem(" + item.num + ");\" href=\"javascript:void(0)\" class=\"cp-del\">X</a>");
		}
		arr.push("</li>");
		if (isFour && i == 3 &&lotteryObj.lottery.length>4) {
			arr.push("<a href='javascript:showfullInfo()'><li class='ac last'></li></a>");
			break;
		}
	}
	return arr.join("");
}

/**
 * 获取排列三的信息
 * @param show
 * @param isFour
 * @returns
 * 2012-10-25上午10:21:35
 */
function getPrePL3CardHtml(show, isFour) {
	var arr = [];
	var item, ss;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		arr.push("<li>");
		arr.push("<div style=\"padding-right:50px\"><div class=\"red\">");
		if (item.type == 0) {// 直选
			ss = item.bai.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("|<span class=\"red\">");
			ss = item.shi.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("|<span class=\"red\">");
			ss = item.ge.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("</span>");
		} else {
			ss = item.balls.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k]);
				if (k != ss.length - 1) {
					arr.push(" ");
				}
			}
		}

		arr.push("</div></div>");
		
		var touzhu_type = "";
		if(item.type == 0){
			touzhu_type = "直选单式";
		}else if(item.type == 1){
			touzhu_type = "组三单式";
		}else {
			touzhu_type = "组六单式";
		}
		arr.push(touzhu_type + "[" + (item.totalstake) + "注]");
		arr
				.push("<span style='float:right;width=10px;'>&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='float:right'>"
						+ pl3_select_type[item.type] + "</span>");
		if (show) {
//			arr.push("<a ontouchend=\"deleteLotteryItem(" + item.num
//					+ ");\" href=\"javascript:deleteLotteryItem(" + item.num
//					+ ");\" class=\"fc-del\">X</a>");
			arr.push("<a onclick=\"deleteLotteryItem(" + item.num + ");\" href=\"javascript:void(0)\" class=\"fc-del\">X</a>");
		}
		arr.push("</li>");
		if (isFour && i == 3 &&lotteryObj.lottery.length>4) {
			arr
					.push("<a href='javascript:showfullInfo()'><li class='ac last'><img src='../images/dmore.png' width='20' height='15'></li></a>");
			break;
		}
	}
	return arr.join("");
}

/** 获得福彩3D的信息 */
function getPreFC3DCardHtml(show, isFour) {
	var arr = [];
	var item, ss;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		arr.push("<li>");
		arr.push("<div style=\"padding-right:50px\"><div class=\"red\">");
		if (item.type == 0) {// 直选
			ss = item.bai.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("|<span class=\"red\">");
			ss = item.shi.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("|<span class=\"red\">");
			ss = item.ge.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("</span>");
		} else {
			ss = item.balls.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k]);
				if (k != ss.length - 1) {
					arr.push(" ");
				}
			}
		}

		arr.push("</div></div>");
		var touzhu_type = "";
		if(item.type == 0){
			touzhu_type = "直选单式";
		}else if(item.type == 1){
			touzhu_type = "组三单式";
		}else {
			touzhu_type = "组六单式";
		}
		arr.push(touzhu_type + "[" + (item.totalstake) + "注]");
		arr.push("<span style='float:right;width=10px;'>&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='float:right'>"
						+ fc3d_select_type[item.type] + "</span>");
		if (show) {

			arr.push("<a onclick=\"deleteLotteryItem(" + item.num + ");\" href=\"javascript:void(0)\" class=\"fc-del\">X</a>");
		}
		arr.push("</li>");
		if (isFour && i == 3 &&lotteryObj.lottery.length>4) {
			arr.push("<a href='javascript:showfullInfo()'><li class='ac last'><img src='../images/dmore.png' width='20' height='15'></li></a>");
			break;
		}
	}
	return arr.join("");
}

/** ***获得大乐透的信息 ***** */
function getPreDLTCardHtml(show, isFour) {
	var arr = [];
	var item, ss;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		arr.push("<li>");
		arr.push("<div style=\"padding-right:50px\"><div class=\"red\">");

		if (item.type == 0) {// 标准玩法
			ss = item.red.split(","); // 获取红球
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("<span class=\"blue\">");
			ss = item.blue.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("</span>");
		} else if (item.type == 1) { // 胆拖玩法
			ss = item.red_before.split(",");
			arr.push("(");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k]);
				if (k != ss.length - 1) {
					arr.push(" ");
				}
			}
			arr.push(") ");
			ss = item.red_after.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("<span class=\"blue\">");
			
			if(item.blue_before != null && item.blue_before != "") {
				//如果没有后去胆码则不显示 
				arr.push("(");
				ss = item.blue_before.split(",");
				for ( var k = 0; k < ss.length; k++) {
					arr.push(ss[k]);
					if (k != ss.length - 1) {
						arr.push(" ");
					}
				}
				arr.push(") ");
			}
			ss = item.blue_after.split(",");
			for ( var k = 0; k < ss.length; k++) {
				arr.push(ss[k] + " ");
			}
			arr.push("</span>");

		}
		arr.push("</div></div>");
		if (item.type == 0 && item.totalstake == 1) {
			arr.push("标准投注");
		} else if (item.type == 0 && item.totalstake > 1) {
			arr.push("复式投注");
		} else if (item.type == 1) {
			arr.push("胆拖投注");
		}
		arr.push("[" + (item.totalstake) + "注]");
		if (show) {
			arr.push("<a onclick=\"deleteLotteryItem(" + item.num + ");\" href=\"javascript:void(0)\" class=\"cp-del\">X</a>");
		}
		arr.push("</li>");
		if (isFour && i == 3 &&lotteryObj.lottery.length>4) {
			arr.push("<a href='javascript:showfullInfo()'><li class='ac last'><img src='../images/dmore.png' width='20' height='15'></li></a>");
			break;
		}
	}
	return arr.join("");
}

/** 获得排列五的信息 */
function getPrePL5CardHtml(show, isFour) {
	var arr = [];
	var item, ss;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		arr.push("<li>");
		arr.push("<div style=\"padding-right:50px\"><div class=\"red\">");

		arr.push(item.fifth+"|"+item.forth+"|"+item.third+"|"+item.second+"|"+item.first);

		
		arr.push("</div></div>");
		
		var touzhu_type = (item.totalstake==1)?"单式投注":"复式投注";
		arr.push(touzhu_type + "[" + (item.totalstake) + "注]");
		if (show) {
//			arr.push("<a ontouchend=\"deleteLotteryItem(" + item.num
//					+ ");\" href=\"javascript:deleteLotteryItem(" + item.num
//					+ ");\" class=\"cp-del\">X</a>");
			arr.push("<a onclick=\"deleteLotteryItem(" + item.num + ");\" href=\"javascript:void(0)\" class=\"cp-del\">X</a>");
		}
		arr.push("</li>");
		if (isFour && i == 3 &&lotteryObj.lottery.length>4) {
			arr.push("<a href='javascript:showfullInfo()'><li class='ac last'><img src='../images/dmore.png' width='20' height='15'></li></a>");
			break;
		}
	}
	return arr.join("");
}

/**
 * 
 * 获得获得七星彩的信息
 * 
 * @param show
 * @param isFour
 * @returns
 */
function getPreQXCCardHtml(show, isFour) {
	var arr = [];
	var item, ss;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		arr.push("<li>");
		arr.push("<div style=\"padding-right:50px\"><div class=\"red\">");

		ss = item.first.split(',');
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}
		arr.push('| ');

		ss = item.second.split(',');
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}
		arr.push('| ');

		ss = item.third.split(',');
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}
		arr.push('| ');

		ss = item.forth.split(',');
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}
		arr.push('| ');

		ss = item.fifth.split(',');
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}
		arr.push('| ');

		ss = item.sixth.split(',');
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}
		arr.push('| ');

		ss = item.seventh.split(',');
		for ( var k = 0; k < ss.length; k++) {
			arr.push(ss[k] + " ");
		}

		arr.push("</div></div>");
		var touzhu_type = (item.totalstake == 1) ? "单式投注" : "复式投注";
		arr.push(touzhu_type + "[" + (item.totalstake) + "注]");

		if (show) {
//			arr.push("<a ontouchend=\"deleteLotteryItem(" + item.num
//					+ ");\" href=\"javascript:deleteLotteryItem(" + item.num
//					+ ");\" class=\"fc-del\">X</a>");
			arr.push("<a onclick=\"deleteLotteryItem(" + item.num + ");\" href=\"javascript:void(0)\" class=\"fc-del\">X</a>");
		}
		arr.push("</li>");
		if (isFour && i == 3 &&lotteryObj.lottery.length>4) {
			arr
					.push("<a href='javascript:showfullInfo()'><li class='ac last'><img src='../images/dmore.png' width='20' height='15'></li></a>");
			break;
		}
	}
	return arr.join("");
}

function showfullInfo() {
	$("#confirmcardInfo1").hide();
	$("#confirmcardInfo2").show();
}

function getLotteryNumberList_SSQ() {
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		var blue =item.blue.split(",").length;
		if (item.type == 0) {// 单式
			arr.push("1:" + item.red + "|" + item.blue+"*"+item.totalstake);
		} else if (item.type == 1 &&blue<2) {// 红复式
			arr.push("2:" + item.red + "|" + item.blue +"*"+item.totalstake);
		}else if(item.type ==1 &&blue>1){	//	蓝复式
			arr.push("3:" + item.red + "|" + item.blue+"*"+item.totalstake);
		} else if (item.type == 2) {//
			arr.push("4:" + item.dan + "$" + item.tuo + "|" + item.blue);
		}
	}
	return arr;
}

function getLotteryNumberList_3D() {
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		if (item.type == 0) {// 直选
			if (item.totalstake == 1) {
				arr.push("1:" + item.bai + "," + item.shi + "," + item.ge+"*"+item.totalstake);
			} else {
				var bai = item.bai.split(",");
				var shi = item.shi.split(",");
				var ge = item.ge.split(",");
				for ( var b = 0; b < bai.length; b++) {
					for ( var c = 0; c < shi.length; c++) {
						for ( var d = 0; d < ge.length; d++) {
							arr.push("1:" + bai[b] + "," + shi[c] + ","+ ge[d]);
						}
					}
				}
			}
		} else if (item.type == 1) {// 组三
			if (item.totalstake == 1) {
				var balls = item.balls.split(",");
				arr.push("3:" + balls[0] + "," + balls[1] + "," + balls[2]+"*"+item.totalstake);
			} else {
				var balls = item.balls.split(",");
				for ( var m = 0; m < balls.length; m++) {
					for ( var n = 0; n < balls.length; n++) {
						if (m < n) {
							arr.push("3:" + balls[m] + "," + balls[n] + ","+ balls[n]);
						} else if (m > n) {
							arr.push("3:" + balls[n] + "," + balls[n] + ","+ balls[m]);
						}
					}
				}
			}
		} else if (item.type == 2) {// 组6
			var balls_array = [];
			var balls = item.balls.split(",");
			for ( var a = 0; a < balls.length; a++) {
				for ( var j = 0; j < balls.length; j++) {
					for ( var k = 0; k < balls.length; k++) {
						balls_array.push(balls[a] + "," + balls[j] + ","+ balls[k]);
					}
				}
			}
			for ( var o = 0; o < balls_array.length; o++) {// 验证
				var ball = balls_array[o].split(",");
				if (ball[0] < ball[1] && ball[1] < ball[2]) {
					arr.push("4:" + ball[0] + "," + ball[1] + "," + ball[2]);
				}
			}
		}
	}
	return arr;
}
/**
 * @returns {Array}
 */
function getLotteryNumberList_PL3() {
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		if (item.type == 0) {// 直选
			if (item.totalstake == 1) {
				arr.push("0:" + item.bai + "|" + item.shi + "|" + item.ge);
			} else {
				var bai = item.bai.split(",");
				var shi = item.shi.split(",");
				var ge = item.ge.split(",");
				for ( var b = 0; b < bai.length; b++) {
					for ( var c = 0; c < shi.length; c++) {
						for ( var d = 0; d < ge.length; d++) {
							arr
									.push("0:" + bai[b] + "|" + shi[c] + "|"
											+ ge[d]);
						}
					}
				}
			}
		} else if (item.type == 1) {// 组三
			if (item.totalstake == 1) {
				var balls = item.balls.split(",");
				arr.push("1:" + balls[0] + " " + balls[1] + " " + balls[2]);
			} else {
				var balls = item.balls.split(",");
				for ( var m = 0; m < balls.length; m++) {
					for ( var n = 0; n < balls.length; n++) {
						if (m < n) {
							arr.push("1:" + balls[m] + " " + balls[n] + " "
									+ balls[n]);
						} else if (m > n) {
							arr.push("1:" + balls[n] + " " + balls[n] + " "
									+ balls[m]);
						}
					}
				}
			}
		} else if (item.type == 2) {// 组6
			var balls_array = [];
			var balls = item.balls.split(",");
			for ( var a = 0; a < balls.length; a++) {
				for ( var j = 0; j < balls.length; j++) {
					for ( var k = 0; k < balls.length; k++) {
						balls_array.push(balls[a] + " " + balls[j] + " "
								+ balls[k]);
					}
				}
			}
			for ( var o = 0; o < balls_array.length; o++) {// 验证
				var ball = balls_array[o].split(" ");
				if (ball[0] < ball[1] && ball[1] < ball[2]) {
					arr.push("2:" + ball[0] + " " + ball[1] + " " + ball[2]);
				}
			}
		}
	}
	return arr;
}


function getLotteryNumberList_DLT() {
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		var type = item.type;
		if (type == 0) {// 标准投注
			// arr.push("0:" + item.red + "|" + item.blue);
			if (item.totalstake > 1) {
				arr.push("1:" + item.red + "|" + item.blue);
				//console.log("标准复式");
			} else {
				arr.push("0:" + item.red + "|" + item.blue);
				//console.log("标准单式");
			}

		} else if (type == 1) {// 胆拖投注
			if(item.blue_before == null || item.blue_before == "") {
				arr.push("2:" + item.red_before + "$" + item.red_after + "|" + item.blue_after);
			} else {
				arr.push("2:" + item.red_before + "$" + item.red_after + "|"
						+ item.blue_before + "$" + item.blue_after);
			}
		} else if (type == 2) {// 生肖乐
			// arr.push("2:" + item.dan + "$" + item.tuo + "|" + item.blue);
		}
	}
	return arr;
}

function getLotteryNumberList_PL5(){
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		if (item.type == 0) {// 单式
			arr.push("0:" + item.fifth + "|" + item.forth + "|" + 
					item.third + "|" +item.second +"|" +item.first);
		} else if (item.type == 1) {// 复式
			arr.push("1:" + item.fifth + "|" + item.forth + "|" + 
					item.third + "|" +item.second +"|" +item.first);
		}
	}
	return arr;	
}

/**
 */
function getLotteryNumberList_QXC() {
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];
		var type = item.type;
		var str=item.first + "|" + item.second+"|"+ item.third + "|"+ item.forth + "|"+ item.fifth + "|"+ item.sixth + "|"+ item.seventh;
		if (item.totalstake > 1) {
			str="1:"+str;
		} else {
			str="0:"+str;
		}
		arr.push(str);
	}
	return arr;

}

function getLotteryNumberList() {
	var arr = [];
	var item;
	if (lotteryCategory == lotteryCategoryList.SSQ) {
		arr = getLotteryNumberList_SSQ();
	} else if (lotteryCategory == 2) {// 福彩3D
		arr = getLotteryNumberList_3D();
	} else if (lotteryCategory == lotteryCategoryList.DLT) { // 大乐透开始
		arr = getLotteryNumberList_DLT();
	} else if(lotteryCategory == lotteryCategoryList.PL3){
		arr = getLotteryNumberList_PL3();
	} else if(lotteryCategory == lotteryCategoryList.PL5){//排列五
		arr = getLotteryNumberList_PL5();
	} else if (lotteryCategory == lotteryCategoryList.QXC) { // 七星彩

		arr = getLotteryNumberList_QXC();

	} else if (lotteryCategory == lotteryCategoryList.XSSC) {
		arr = getLotteryNumberList_XSSC();
	}else if (lotteryCategory == lotteryCategoryList.K3){
		arr = getLotteryNumberList_K3();
	}else if (lotteryCategory == lotteryCategoryList.JCZQ){
		arr = getLotteryNumberList_JCZQ();
	}
	return arr.join(";");
}

function getLotteryNumberList_JCZQ() {
	var arr = [];
	var elements_1 = (lotteryObj.match.elements[0]).value;
	var elements_2 = (lotteryObj.match.elements[1]).value;
	var temp1 = "";
	var flag = false;
	var str = [];
	for ( var i = 0; i < elements_1.length; i++) {
		str.push(elements_1[i].split("-")[2]);
	}
	var emp1 = (str.sort(function(x, y){if (x > y)return -1;if (x < y)return 1;})).join(",");
	arr.push((lotteryObj.match.elements[0]).key+"-"+elements_1[0].split("-")[0]+"-"+elements_1[0].split("-")[1]+"-"+elements_1[0].split("-")[4]+"-"+emp1+"-"+elements_1[0].split("-")[5]);
	str = [];
	for ( var i = 0; i < elements_2.length; i++) {
		str.push(elements_2[i].split("-")[2]);
	}
	var emp2 = (str.sort(function(x, y){if (x > y)return -1;if (x < y)return 1;})).join(","); 
	arr.push((lotteryObj.match.elements[1]).key+"-"+elements_2[0].split("-")[0]+"-"+elements_2[0].split("-")[1]+"-"+elements_2[0].split("-")[4]+"-"+emp2+"-"+elements_2[0].split("-")[5]);
	return arr;
}

/* 是否超出最大金额限制 */
function isMaxTotalPay(orderType) {
	var totalstake = caltotalstake();
	var talpay = caltotalpay(totalstake, orderType);
	return talpay <= maxAmount;
}


function deleteLotteryItem(num) {
	deleteLotteryItem1(num);
	precardHtml();
	pretotalHtml();

}

function deleteLotteryItem1(num){
	if(lotteryCategory == 1004){
		for ( var i = 0; i < lotteryObj.infos.length; i++) {
			if (i == num) {
				lotteryObj.infos.splice(i, 1);
				break;
			}
		}
	}else{
		for ( var i = 0; i < lotteryObj.lottery.length; i++) {
			if (lotteryObj.lottery[i].num == num) {
				lotteryObj.lottery.splice(i, 1);
				break;
			}
		}
	}
	
	sessionStorage.setItem("lottery_" + lotteryCategory, JSON.stringify(lotteryObj));	
}

function appendIssueShow() {
	var obj = lotteryObj.appendissue;
	$("#stopflag_" + obj.stopflag).attr("checked", true);
	// $("input[name='stopflag']:checked")
	if (obj.stopflag == 2) {
		$("#stopaward").val(obj.stopaward);
	}
	$("#shownums").val(obj.shownums);
	LotteryPlay.init(50);

}

function addAppendIssue(item) {
	lotteryObj.appendissue.nums += 1;
	lotteryObj.appendissue.appendissueinfo.push(item);
}

function getAppendIssue(totalStake) {
	var appendissueinfo = lotteryObj.appendissue.appendissueinfo;
	var arr = [];
	var item;
	for ( var i = 0; i < appendissueinfo.length; i++) {
		item = appendissueinfo[i];
		arr.push(item.issueid + ":" + item.issueName + ":" + item.multiple
				+ ":" + (parseInt(item.multiple) * totalStake * 2));
	}
	return arr.join(";");
}

/* 获得追期数量 */
function isAppendIssueNums() {
	return lotteryObj.appendissue.appendissueinfo.length > 1;
}

var LotteryPlay = {};

LotteryPlay.init = function(maxTimes) {
	LotteryPlay.maxTimes = maxTimes;
	// 追号期次修改默认期次
	var qi = 5;
	get("shownums").value = qi;
	LotteryPlay.zhuihao();
	LotteryPlay.play_switch();

};

LotteryPlay.play_switch = function() {
	if (lotteryObj.appendissue.appendissueinfo.length > 0) {
		$("#shownums").val(lotteryObj.appendissue.shownums);
		var zhuihao_box = LotteryPlay.checkboxs;
		var bei_text = getElementsByClassName(get("zhuihao_con"),
				"common-input");
		var money_text = getElementsByClassName(get("zhuihao_con"), "money");

		var totalstake = caltotalstake();
		// alert(totalstake);return;
		for ( var s = 0; s < zhuihao_box.length; s++) {
			var is_id = s + 1;
			if (get("issue_" + is_id)
					&& get("issue_" + is_id).style.display == "") {
				get("issue_" + is_id).style.display = "none";
			}
		}

		if (lotteryObj.appendissue.appendissueinfo.length == lotteryObj.appendissue.shownums) {
			get("select_all_zhuihao").checked = true;
		}

		for ( var i in lotteryObj.appendissue.appendissueinfo) {
			// alert(lotteryObj.appendissue.appendissueinfo[i].issueid);
			// return;
			var p = 0;
			for ( var j = 0; j < lotteryObj.appendissue.shownums; j++) {
				var is_id = j + 1;
				get("issue_" + is_id).style.display = "";
				// if (zhuihao_box[j].type == "checkbox") {
				var input = bei_text[j + 1];
				var text = money_text[j];
				if (!zhuihao_box[j].checked) {
					p += 1;
					// alert(zhuihao_box[j].value==lotteryObj.appendissue.appendissueinfo[i].issueid);
					if (zhuihao_box[j].value == lotteryObj.appendissue.appendissueinfo[i].issueid) {
						zhuihao_box[j].checked = "checked";
						input.disabled = "";
						input.value = lotteryObj.appendissue.appendissueinfo[i].multiple;
						text.innerHTML = "￥"
								+ calsinglePay(totalstake, input.value) + ".00";
						// break;
					} else {
						input.disabled = "disabled";
						input.value = '';
						text.innerHTML = "￥0.00";
					}
					/*
					 * }else{ input.disabled = "disabled"; input.value = '';
					 */
				}
			}
		}
	} else {
		LotteryPlay.change_issue();
	}

	// get("select_all_zhuihao").checked = true;
	// alert(lotteryObj.appendissue.shownums);
	// alert(LotteryPlay.checkboxs);
};

LotteryPlay.zhuihao = function() {
	LotteryPlay.checkboxs = [];
	/*
	 * if (get("stop_set")) { get("stop_award").onkeyup = function () {
	 * this.value = this.value.replace(/[^0-9.]/g, ''); if (this.value < 1) {
	 * this.value = ""; } }; get("stop_award").onkeydown = function () { if
	 * (this.value < 1) { this.value = ""; } }; get("stop_award").onblur =
	 * function () { this.value = this.value.replace(/[^0-9.]/g, ''); if
	 * (this.value < 1) { this.value = ""; } }; get("stop_set").onclick =
	 * function () { LotteryPlay.disable_set(this.checked); };
	 * LotteryPlay.disable_set(false); }
	 */
	get("shownums").onchange = function() {
		LotteryPlay.change_issue();
	};
	LotteryPlay.zhuihao_select();
};

LotteryPlay.change_issue = function() {
	var n = Number(get("shownums").value);
	if (n < 1) {
		return;
	}
	LotteryPlay.clear_issue();
	var zhuihao_box = LotteryPlay.checkboxs;

	var checkboxs = getElementsByClassName(get("zhuihao_con"), "issue");
	for ( var i = n + 1; i <= zhuihao_box.length; i++) {
		if (get("issue_" + i)) {
			get("issue_" + i).style.display = "none";
		}
	}
	get("select_all_zhuihao").checked = true;
	if (document.all) {
		get("select_all_zhuihao").fireEvent("onclick");
	} else {
		get("select_all_zhuihao").onclick();
	}
};

LotteryPlay.clear_issue = function(ck) {
	var zhuihao_box = LotteryPlay.checkboxs;
	for ( var i = 1; i <= zhuihao_box.length; i++) {
		if (get("issue_" + i)) {
			get("issue_" + i).style.display = "";
		}
	}
	// var check = ck ? ck : "";
	get("select_all_zhuihao").checked = true;
	if (document.all) {
		get("select_all_zhuihao").fireEvent("onclick");
	} else {
		get("select_all_zhuihao").onclick();
	}
};

LotteryPlay.per_bei = function() {
	if (get("multi").value == "") {
		return 0;
	}
	var totalStake = caltotalstake();
	return calsinglePay(totalStake, get("multi").value);
	// return Number(get("total_money").innerHTML.substr(1)) /
	// get("multi").value;
};

LotteryPlay.zhuihao_data = function() {
	var case_money = 0;
	var date_num = 0;
	var moneys = getElementsByClassName(get("zhuihao_con"), "money");
	for ( var i = 0; i < moneys.length; i++) {
		case_money += Number(moneys[i].innerHTML.substr(1));
	}
	var zhuihao_box = getElementsByClassName(get("zhuihao_con"), "issue");
	var bei_text = getElementsByClassName(get("zhuihao_con"), "common-input");
	var money_text = getElementsByClassName(get("zhuihao_con"), "money");
	var sdm = LotteryPlay.per_bei();
	for ( var i = 1; i < zhuihao_box.length; i++) {
		if (zhuihao_box[i].type == "checkbox") {
			var input = bei_text[i];
			var text = money_text[i - 1];
			if (zhuihao_box[i].checked) {
				if (input.value == "") {
					input.value = 1;
				}
				text.innerHTML = "￥" + input.value * sdm + ".00";
				date_num++;
			}
		}
	}
	get("select_all_zhuihao").checked = (date_num == get("shownums").value || date_num == zhuihao_box.length - 1);

	// get("zhuihao_total").innerHTML = "本次投注总金额：<span class='red'>￥" +
	// case_money + ".00</span>元 [" + LotteryPlay.zhu + "注 " + date_num + "期]";
};

LotteryPlay.zhuihao_select = function() {
	var zhuihao_box = getElementsByClassName(get("zhuihao_con"), "issue");

	var bei_text = getElementsByClassName(get("zhuihao_con"), "common-input");
	// var bei_add_btn = getElementsByClassName(get("zhuihao_con"), "add");
	/* var bei_sub_btn = getElementsByClassName(get("zhuihao_con"), "reduce"); */
	var checkboxs = [];
	var inputs = [];
	var bei_adds = [];
	var bei_subs = [];
	for ( var i = 1; i < zhuihao_box.length; i++) {
		checkboxs.push(zhuihao_box[i]);
		LotteryPlay.checkboxs.push(zhuihao_box[i]);
		inputs.push(bei_text[i]);
		// bei_adds.push(bei_add_btn[i]);
		// bei_subs.push(bei_sub_btn[i]);
	}
	for ( var i = 0; i < inputs.length; i++) {
		if (i == 0) {
			inputs[i].disabled = "";
			checkboxs[0].disabled = 'disabled';
		} else {
			inputs[i].disabled = "disabled";
		}
	}

	var moneys = getElementsByClassName(get("zhuihao_con"), "money");
	for ( var i = 0; i < checkboxs.length; i++) {
		checkboxs[i].index = i;
		checkboxs[i].onclick = function() {
			var input = inputs[this.index];
			var money = moneys[this.index];
			if (this.checked) {
				var t = get("zhuihao_beitou").value;
				input.disabled = "";
				t != "" ? input.value = t : input.value = 1;
				var sdm = LotteryPlay.per_bei();
				money.innerHTML = "￥" + input.value * sdm + ".00";
			} else {
				input.value = "";
				input.disabled = "disabled";
				money.innerHTML = "￥0.00";
			}
			LotteryPlay.zhuihao_data();
		};
	}

	get("select_all_zhuihao").onclick = function() {
		var sdm = LotteryPlay.per_bei();
		checkboxs[0].disabled = 'disabled';
		if (this.checked) {
			for ( var i = 0; i < checkboxs.length; i++) {
				var is_id = i + 1;
				if (i + 1 <= get('shownums').value) {
					if (get("issue_" + is_id)
							&& get("issue_" + is_id).style.display == "") {
						if (!checkboxs[i].checked) {
							var input = inputs[i];
							var money = moneys[i];
							checkboxs[i].checked = "checked";
							var t = get("zhuihao_beitou").value;
							input.disabled = "";
							t != "" ? input.value = t : input.value = 1;
							money.innerHTML = "￥" + input.value * sdm + ".00";
						}
					}
				} else {
					if (get("issue_" + is_id)
							&& get("issue_" + is_id).style.display == "") {
						var input = inputs[i];
						var money = moneys[i];
						checkboxs[i].checked = "";
						input.value = "";
						input.disabled = "disabled";
						money.innerHTML = "￥0.00";
					}
				}
			}
		} else {
			for ( var i = 1; i < checkboxs.length; i++) {
				var input = inputs[i];
				var money = moneys[i];
				checkboxs[i].checked = "";
				input.value = "";
				input.disabled = "disabled";
				money.innerHTML = "￥0.00";
			}
		}
		LotteryPlay.zhuihao_data();
	};

	var reg = /^\d{1,2}$/;
	for ( var i = 0; i < inputs.length; i++) {
		inputs[i].index = i;
		// bei_adds[i].index = i;
		// bei_subs[i].index = i;
		/*
		 * inputs[i].onkeyup = function() { input_bei(this);
		 * line_num(this.index, this.value); };
		 */
		/*
		 * inputs[i].onkeydown = function() { if (this.value < 1) { this.value =
		 * "1"; } };
		 */
		inputs[i].onblur = function() {
			input_bei(this);
			line_num(this.index, this.value);
		};
		inputs[i].onkeyup = function() {
			var o = this;
			o.value = o.value.replace(/\D/g, "");
			if (o.value == "0") {
				o.value = "1";
			} else if (o.value > 50) {
				o.value = 50;
			}
		};

		/*
		 * bei_adds[i].onclick = function() { var input = inputs[this.index]; if
		 * (reg.test(input.value) && input.value < LotteryPlay.maxTimes) {
		 * input.value++; line_num(this.index, input.value); } };
		 * bei_subs[i].onclick = function() { var input = inputs[this.index]; if
		 * (reg.test(input.value) && input.value > 1) { input.value--;
		 * line_num(this.index, input.value); } };
		 */
	}
	get("zhuihao_beitou").onkeyup = function() {
		// input_bei(this);

		var o = this;
		o.value = o.value.replace(/\D/g, "");
		if (o.value == "0") {
			o.value = "1";
		} else if (o.value > 50) {
			o.value = 50;
		}
		total_num(this.value);
	};
	/*
	 * get("zhuihao_beitou").onkeydown = function() { if (this.value < 1) {
	 * this.value = "1"; } };
	 */
	get("zhuihao_beitou").onblur = function() {
		input_bei(this);
		total_num(this.value);
	};
	/*
	 * get("zhuihao_beitou_add").onclick = function() { var input =
	 * get("zhuihao_beitou"); if (reg.test(input.value) && input.value <
	 * LotteryPlay.maxTimes) { input.value++; total_num(input.value); } };
	 * get("zhuihao_beitou_sub").onclick = function() { var input =
	 * get("zhuihao_beitou"); if (reg.test(input.value) && input.value > 1) {
	 * input.value--; total_num(input.value); } };
	 */

	function input_bei(o) {
		o.value = o.value.replace(/\D/, "");
		if (isNaN(o.value) || o.value < 1) {
			o.value = "1";
		}
		if (o.value > LotteryPlay.maxTimes) {
			o.value = LotteryPlay.maxTimes;
		}
	}
	function line_num(index, n) {
		var sdm = LotteryPlay.per_bei();
		moneys[index].innerHTML = "￥" + n * sdm + ".00";
		LotteryPlay.zhuihao_data();
	}

	function total_num(n) {
		var sdm = LotteryPlay.per_bei();
		for ( var i = 0; i < inputs.length; i++) {
			if (checkboxs[i].checked) {
				inputs[i].value = n;
				moneys[i].innerHTML = "￥" + n * sdm + ".00";
			}
		}
		LotteryPlay.zhuihao_data();
	}
};

function alertTip(title) {
	tipTitle = title;
	getTip();
}

function noticeTip(title) {
    tipTitle = title;
    getTip(false);
}

// 判断屏幕是否旋转
function orientationChange() {
	if (document.getElementById("tipOuter")) {
		$("#tipOuter").remove();
		getTip();
	}

	if ($("#indexTipOuter").is(":visible")) {
		$("#indexTipOuter").hide();
		getIndexTip();
		return false;
	}

}

function getTip(isNeedConfirm) {

     if(isNeedConfirm == undefined || isNeedConfirm == null) {
         isNeedConfirm = true;
     }


	$("body").find("#tipOuter").remove().end().css("opacity", "0.8").css("overflow", "hidden")
			.append(
					"<div class='tipOuter' id='tipOuter' style=' background-color:rgba(0,0,0,0.8)' ontouchmove='preventMove(event)'>"
							+ "<div class='tip' style='padding:10px;font-size: 14px;border-radius:6px;border:1px solid #b5b5b5;background-color:#eee;color: #848382;' id='tip'>"
							+ "<div class='title' style='float: left;clear: both;padding-top: 0px;'>提示：</div><div class='content'>"
							+ "<div align='center' style='color: #333;clear: both;padding-top: 5px;padding-bottom: 5px;'>"
							+ tipTitle
							+ "</div>"
							+ "</div>"
							+ "<div class='tipbutton'>"
							+ "<div class='buttondiv' align='center'>"
							+ (isNeedConfirm ? "<input type='button' value='确定' id='sure' style='border:1px solid #b40e0e;background:#d62323;background:-webkit-gradient(linear,left top, left bottom,from(#ed3131), to(#be1313));background:-moz-linear-gradient(top, #ed3131, #be1313);color:#fffwidth:50px'/>" : "")
							+ "</div>" + "</div>" + "</div>" + "</div>");
	$("#tipOuter").show();
	var x = $(window).width();
	var y = $(window).height();
	var div_x = $("#tip").width();
	var div_y = $("#tip").height();
	var pos_x = Math.ceil((x - div_x) / 2);
	var pos_y = Math.ceil((y - div_y) / 2);

	$("#tip").css('left', pos_x);
	$("#tip").css('top', pos_y);

	$("#sure").click(function() {
		$("body").css("opacity", "1").css("overflow", "auto");
		$("#tipOuter").remove();
	});

}

function preventMove() {
	event.preventDefault();
}

// 添加事件监听
addEventListener('load', function() {
	orientationChange();
	window.onorientationchange = orientationChange;
});

// 以下两个函数用于检测user-agent,以决定是否显示摇一摇
function check(reg) {
	var ug = navigator.userAgent.toLowerCase();
	return reg.test(ug);
}

function checkBrowser() {
	var ug = navigator.userAgent.toLowerCase();
	
	//客户端内嵌浏览器3.6以后UA修改成：jdapp;android;3.7.0;4.3;357177051784903-400E850A85C1 需要重新适配 
	// 检测是否来自android手机
	var isChrome = check(/android/);
	if (isChrome) {
		var ver = ug.match(/android\s\d{1,}[.\d{1,}]*/);
		var verApp = ug.match(/android;\d{1,}[.\d{1,}]*/);
		if (ver || verApp) {
			return true;
		}
	}

	// 检测safari及是否来自手机
	var isSafari = check(/safari/);
	if (isSafari) {
		var ver = ug.match(/safari\/\d{1,}[.\d{1,}]*/);
		if (ver) {
			var mobile = ug.match(/mobile\/\w+?\s/);
			if (mobile) {
				return true;
			}
		}
	}
	var platform = navigator.platform;

	if (platform == "iPod" || platform == "iPhone" || platform == "iPad") {
		return true;
	}

	return false;
}

// 计算最大倍数
function calMaxMulti(totalstake) {
	return parseInt((maxAmount / 2) / totalstake);
}

// 时时彩确认页面显示
function getPreXSSCCardHtml(show, isFour) {
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.lottery.length; i++) {
		item = lotteryObj.lottery[i];		
		if(item.zu.length > 0) {
			item.zu.sort();
			arr.push("<li>");
			arr.push("<div style=\"padding-right:50px\">");
			arr.push("<span class=\"red\">");
			arr.push(item.zu);
			arr.push("</span></div>");
		} else {
			if(item.type == 11) {
				arr.push("<li><div style=\"padding-right:50px\">");
				var temp = (item.shi).join(",");
				temp = temp.replace("0", "大");
				temp = temp.replace("1", "小");
				temp = temp.replace("2", "单");
				temp = temp.replace("3", "双");
				
				arr.push("<span class=\"red\">");
				arr.push(temp);
				arr.push("</span>");
				
				temp = (item.ge).join(",");
				temp = (temp).replace("0", "大");
				temp = temp.replace("1", "小");
				temp = temp.replace("2", "单");
				temp = temp.replace("3", "双");
				
				arr.push("<span class=\"blue\">|");
				arr.push(temp);
				arr.push("</span>");
				
				arr.push("</div>");
			} else {
				arr.push("<li>");
				arr.push("<div style=\"padding-right:50px\">");
				if (item.wan.length>0) {
					item.wan.sort();
					arr.push("<span class=\"red\">");
					arr.push(item.wan);
					arr.push("</span>");
				} else if (item.type == 13 || item.type == 14){
					arr.push("<span class=\"red\">-</span>");
				}
				if(item.qian.length>0) {
					item.qian.sort();
					if(item.wan.length>0 || item.type == 13 || item.type == 14) {
						arr.push("<span class=\"blue\">|");
					} else {
						arr.push("<span class=\"blue\">");
					}
					arr.push(item.qian);
					arr.push("</span>");
				}else if (item.type == 13 || item.type == 14){
					arr.push("<span class=\"blue\">|-</span>");
				}
				
				if(item.bai.length>0) {
					item.bai.sort();
					if(item.qian.length>0 || item.type == 13 || item.type == 14) {
						arr.push("<span>|");
					} else {
						arr.push("<span>");
					}					
					arr.push(item.bai);
					arr.push("</span>");
				} else if (item.type == 13 || item.type == 14){
					arr.push("<span>|-</span>");
				}
				if (item.shi.length>0) {
					item.shi.sort();
					if(item.bai.length>0 || item.type == 13 || item.type == 14) {
						arr.push("<span class=\"red\">|");
					} else {
						arr.push("<span class=\"red\">");
					}					
					arr.push(item.shi);
					arr.push("</span>");
				}else if (item.type == 13 || item.type == 14) {
					arr.push("<span class=\"red\">|-</span>");
				}
				if(item.ge.length>0) {
					item.ge.sort();
					if(item.shi.length>0 || item.type == 13 || item.type == 14) {
						arr.push("<span class=\"blue\">|");
					} else {
						arr.push("<span class=\"blue\">");
					}
					arr.push(item.ge);
					arr.push("</span>");
				} else if (item.type == 13 || item.type == 14) {
					arr.push("<span class=\"blue\">|-</span>");
				}
				arr.push("</div>");	
			}
		
		}

		// 投注类型分为五星直选等
		var touzhu_type = xssc_select_type[item.type];
		arr.push(touzhu_type + "[" + (item.totalstake) + "注]");
		if (show) {
			arr.push("<a ontouchend=\"deleteLotteryItem(" + item.num
					+ ");\" href=\"javascript:deleteLotteryItem(" + item.num
					+ ");\" class=\"fc-del\">X</a>");
		}
		arr.push("</li>");
		if (isFour && i == 3 &&lotteryObj.lottery.length>4) {
			arr.push("<a href='javascript:showfullInfo()'><li class='ac last'><img src='../images/dmore.png' width='20' height='15'></li></a>");
			break;
		}		
	}
	return arr.join("");
}

function getPreK3CardHtml1(item){
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

function getPreK3CardHtml2(item){
	var sub_type = item.subType;
	if(sub_type === null || sub_type === undefined || sub_type == 0){
		return getPreK3CardHtml1(item);
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

//快三确认页面显示
function getPreK3CardHtml(show, isFour) {
	var arr = [];
	var item;
	for ( var i = 0; i < lotteryObj.infos.length; i++) {		
		item = lotteryObj.infos[i];
		var type = item.type;
		arr.push("<li>");
		arr.push("<div style=\"padding-right:50px\">");
		arr.push("<span class=\"red\">");			
		
		switch (Number(type)) {
		//和值投注
		case 1:
			arr.push(getPreK3CardHtml1(item));
			break;
		//二同号单选
		case 2:
			var _balls2 = item.balls2;
			var _balls3 = item.balls3;
			if (_balls2 ===null || _balls2 === undefined || _balls2.length == 0){
				return;
			}
			for ( var k = 0; k < _balls2.length; k++) {
				arr.push( _balls2[k]);
				if (k != _balls2.length - 1) {
					arr.push(" ");
				}
			}
			if(_balls3.length > 0){
				arr.push("#");
				for ( var k = 0; k < _balls3.length; k++){
					arr.push(_balls3[k]);
					if (k != _balls3.length - 1) {
						arr.push(" ");
					}
				}
			}		
			break;
		//二同号复选
		case 3:
			arr.push(getPreK3CardHtml1(item));
			break;
		//三同号单选
		case 4:
			arr.push(getPreK3CardHtml1(item));
			break;
		//三同号通选
		case 5:
			arr.push("三同号通选");
			break;
		//二不同号(单选、胆拖）
		case 6:
			arr.push(getPreK3CardHtml2(item));			
			break;
		//三不同号(单选，胆拖）
		case 7:
			arr.push(getPreK3CardHtml2(item));
			break;
		//三连号通选
		case 8:
			arr.push("三连号通选");
			break;
		default:
			break;
		}		
		arr.push("</span></div>");	
		arr.push(k3_select_type[type] + "[" + (item.count) + "注]");		
		arr.push("</li>");
		if (isFour && i == 3 && lotteryObj.infos.length>4) {
			arr.push("<a href='javascript:showfullInfo()'><li class='ac last'><img src='../images/dmore.png' width='20' height='15'></li></a>");
			break;
		}		
	}
	return arr.join("");
}

function getLotteryNumberList_XSSC() {
	var arr = [];
	var item;
	for ( var s = 0; s < lotteryObj.lottery.length; s++) {
		item = lotteryObj.lottery[s];
		if(item.zu.length > 0) {
			arr.push(item.type + ":"+ item.zu);
		} else {
			arr.push(item.type + ":"+ (item.wan.length==0? "-":item.wan) + "|" + (item.qian.length==0? "-":item.qian) + "|" + (item.bai.length==0? "-":item.bai) + "|" + (item.shi.length==0? "-":item.shi) + "|" + (item.ge.length==0? "-":item.ge));
		}
	}
	return arr;
}

//拆注
function combine(num, n) {
    var result = [];
    var order = [];
    for (var i = 0; i <= n; i++) {
		// 注意这里order[0]=-1用来作为循环判断标识
        order[i] = i - 1;  
    }
    var count = 0;
    var k = n;
	// 标志找到一个有效组合
    var flag = true;    
    while (order[0] == -1) {
		// 输出符合要求的组合
        if (flag) {   
            var m = [];
            for (var i = 1; i <= n; i++) {
                var nu = num[order[i]];
                m.push(nu);
            }
            result.push(m.join(" "));
            count++;
            flag = false;
        }
		// 在当前位置选择新的数字
        order[k]++; 
		// 当前位置已无数字可选，回溯
        if (order[k] == num.length)   
        {
            order[k--] = 0;
            continue;
        }
		// 更新当前位置的下一位置的数字
        if (k < n)   
        {
            order[++k] = order[k - 1];
            continue;
        }
        if (k == n) {
            flag = true;
        }
    }
    return result;
}

function getLotteryNumberList_K3() {
	var arr = [];
	var item;
	
	for ( var s = 0; s < lotteryObj.infos.length; s++) {
		item = lotteryObj.infos[s];
		var type = item.type;				
		
		switch (Number(type)) {
		//和值投注
		case 1:		
			var _balls1 = item.balls1;
			var info = "";
			for( var i=0;i<_balls1.length;i++){
				info += _balls1[i];
				if (i != _balls1.length - 1) {
					info += "^";
				}
			}
			arr.push("1:3:"+info);
			break;
		//二同号单选 拆注
		case 2:
			var _balls2 = item.balls2;
			var _balls3 = item.balls3;
			if (_balls2 ===null || _balls2 === undefined || _balls2.length == 0){
				return;
			}
			for ( var i = 0; i < _balls2.length; i++) {
				var temp1 = String(_balls2[i]).charAt(0); 
				// 1,2,
				var temp = temp1 + "," + temp1 + ","; 
				for (var j = 0; j < _balls3.length; j++){
					var temp3 = _balls3[j];
					arr.push("2:0:"+temp+temp3);
				}
			}				
			break;
		//二同号复选
		case 3:			
			var _balls1 = item.balls1;
			if (_balls1 ===null || _balls1 === undefined || _balls1.length == 0){
				return;
			}
			for ( var k = 0; k < _balls1.length; k++) {
				var temp1 = String(_balls1[k]).charAt(0);
				var temp = temp1 + "," + temp1 + ","; 
				arr.push("3:0:"+temp+"255");
			}			
			break;
		//三同号单选
		case 4:
			var _balls1 = item.balls1;
			if (_balls1 ===null || _balls1 === undefined || _balls1.length == 0){
				return;
			}
			for ( var k = 0; k < _balls1.length; k++) {
				var temp1 = String(_balls1[k]).charAt(0);
				var temp = temp1 + "," + temp1 + ","+temp1; 
				arr.push("4:0:"+temp);
			}	
			break;
		//三同号通选
		case 5:
			arr.push("5:0:255,255,255");
			break;
		//二不同号(单选、胆拖）
		case 6:
			var sub_type = item.subType;
			//单选拆注
			if(sub_type === null || sub_type === undefined || sub_type == 0){				
				var _balls1 = item.balls1;
				if (_balls1 ===null || _balls1 === undefined || _balls1.length == 0){
					return ;
				}
				var result = combine(_balls1,2);
				for ( var i=0;i<result.length;i++){
					var temp = result[i];
					temp = temp.replace(/\s/g,",");
					arr.push("6:0:"+temp + ",255");
				}
			}else{
			//胆拖				
				var _balls2 = item.balls2;
				var _balls3 = item.balls3;
				if (_balls2 ===null || _balls2 === undefined || _balls2.length == 0){
					return ;
				}				
				for ( var k = 0; k < _balls2.length; k++) {
					var nums = "";
					nums += _balls2[k];
					if (k != _balls2.length - 1) {
						nums += ",";
					}
				}				
				if(_balls3.length > 0){
					nums += "*";
					for ( var k = 0; k < _balls3.length; k++){
						nums += _balls3[k];
						if (k != _balls3.length - 1) {
							nums += ",";
						}
					}
					arr.push("6:2:"+nums);
				}	
				
			}			
			break;
		//三不同号(单选，胆拖）
		case 7:
			var sub_type = item.subType;
			//单选拆注
			if(sub_type === null || sub_type === undefined || sub_type == 0){				
				var _balls1 = item.balls1;
				if (_balls1 ===null || _balls1 === undefined || _balls1.length == 0){
					return ;
				}
				var result = combine(_balls1,3);
				for ( var i=0;i<result.length;i++){
					var temp = result[i];
					temp = temp.replace(/\s/g,",");
					arr.push("7:0:"+temp);
				}
			}else{
			//胆拖				
				var _balls2 = item.balls2;
				var _balls3 = item.balls3;
				if (_balls2 ===null || _balls2 === undefined || _balls2.length == 0){
					return ;
				}
				//胆拖下注后只剩下一个胆问题修复
                var nums = "";
				for ( var k = 0; k < _balls2.length; k++) {
					nums += _balls2[k];
					if (k != _balls2.length - 1) {
						nums += ",";
					}
				}				
				if(_balls3.length > 0){
					nums += "*";
					for ( var k = 0; k < _balls3.length; k++){
						nums += _balls3[k];
						if (k != _balls3.length - 1) {
							nums += ",";
						}
					}
					arr.push("7:2:"+nums);
				}	
			}		
			break;
		//三连号通选
		case 8:
			arr.push("8:0:255,255,255");
			break;
		default:
			break;
		}
	}
	return arr;
}