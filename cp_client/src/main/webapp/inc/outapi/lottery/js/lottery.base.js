
function get(id){return document.getElementById(id);}

function getElementsByClassName(parentElement, className) {
    var elements = (parentElement || document.body).getElementsByTagName("*");
    var result = [];
    className = new RegExp("(^|\\s)" + className + "(\\s|\x24)");
    var element;
    for (var i = 0; element = elements[i]; i++) {
        if (className.test(element.className)) {
            result.push(element);
        }
    }
    return result;
}

function randomSelect(arr, selectQuantity) {
    var result = [];
    for (var i = 0; i < selectQuantity; i++) {
        var idx = Math.floor(Math.random() * arr.length);
        result[result.length] = arr[idx];
        arr.splice(idx, 1);
    }
    return result;
}

//计算倒计时
function LotteryTimer(canSell, time, id,callback,awardPool) {
    this.canSell = canSell;
    this.time = time;
    this.id = id;
    this.callback = callback;
    if(awardPool===undefined){
    	this.awardPool = 0;
    }else{
    	this.awardPool =awardPool;
    }
    this.timer = null;
}

LotteryTimer.prototype.start = function(o) {
    if (o.time > 0) {
        if (o.canSell){
            o.setRemainTime(o.time, o.id,o.awardPool);
        }
        o.time -= 1000;
        o.timer = setTimeout(function() {
            o.start(o);
        }, 1000);
    }else{
        o.callback();
    }
};

LotteryTimer.prototype.clear = function() {
    clearTimeout(this.timer);
};

LotteryTimer.prototype.setRemainTime = function(time, id, awardPool) {
	//彩种类型
	var _id = id.substring(5);
	
    var tt = time / 1000;
    var seconds = parseInt(tt % 60);
    var minutes = parseInt((tt / 60) % 60);
    var hours = parseInt((tt / 60 / 60) % 24);
    var days = parseInt(tt / 60 / 60 / 24);

    seconds < 10 ? seconds = "0" + seconds : seconds = seconds;
    minutes < 10 ? minutes = "0" + minutes : minutes = minutes;
    
    var timeHtml = "";
    if(days>0){
    	timeHtml += "<b>" + days + "</b><em>天</em>";
    }
    if(hours>0 || days>0){
    	timeHtml += "<b>" + hours + "</b><em>小时</em>";
    }   
    if(minutes>0 || days>0 ||  hours>0){
    	timeHtml += "<b>" + minutes + "</b><em>分</em>";
    }    
    timeHtml += "<b>" + seconds + "</b><em>秒</em>";
    
    
    $("#" + id).html(timeHtml);
    
     
    //新时时彩和快三单独处理 "距离新开期"倒计时则去掉标红  否则倒计时小于3分钟标红 
    if (_id == "1002" || _id == "1004"){
    	//去标红
    	if(("距离新开期：" == $("#issueNameId_"+_id).text())) {
    		var bb = $("#"+id+" b");
    		var ss = $("#"+id+" em");
    		$.each(bb, function(i,n) {
    			$(n).removeClass("red");
    		});  
    		$.each(ss, function(i,n) {
    			$(n).removeClass("red");
    		});     		
    	}else if (hours==0&&minutes < 3) {    		
    		var bb = $("#"+id+" b");
    		var ss = $("#"+id+" em");
    		$.each(bb, function(i,n) {
    			$(n).addClass("red");
    		});
    		$.each(ss, function(i,n) {
    			$(n).addClass("red");
    		});    		
        }    	
    }else if (_id =="1" || _id=="4" || _id=="7"){
    	//双色球  每周二、四、日  19:40 
    	//大乐透  每周一、三、六 19:40   	
    	//七星彩  每周二、五、日 19:40    	
    	if(days == 0  && ( hours<19 || ( hours == 19 && minutes <=40 )) ){
    		$("#watermark_"+_id).html('<span class="tip"><span class="bg-arr"></span><span>今日开奖</span></span>');
    	}else if (!(awardPool===undefined) && awardPool >= 100000000){
    		$("#watermark_"+_id).html('<span class="tip"><span class="bg-arr"></span><span>奖池过亿</span></span>');    		
    	}else{
    		$("#watermark_"+_id).html('');
    	}    	
    }
};

function isUrl(s) {
    var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    return regexp.test(s);
}
function trim(str) {
    //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function htmltagfilter(value) {
   value = value.replace(/<\/?[^>]*>/g,''); //去除HTML tag
   value = value.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
   value = value.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
   return value;
}

function canSellCheck(canSell, statusDesc) {
	if (statusDesc == '') {
		statusDesc = "<br /><div>很抱歉，该彩种暂停销售，开售时间另行通知，祝您好运！</div><br/>点击 <a href=\"http://caipiao.jd.com\"><span style=\"font-size:16pt\">这里</span></a> 返回彩票首页";
	}
	if(!canSell){
		jQuery.jdThickBox({
			width:500,
			height:200,
			type:"text",
			source:statusDesc,
			title:"停止销售"
		});
	}
}

function endIssueMessage(issueName) {
    statusDesc = "<div><s></s><ul><li><span>"+issueName+"</span>期已截止投注！</li><li>请您等候下期再进行投注！</li></ul></div> <div class='btn'><a href=\"#\" onclick='jdThickBoxclose()'>确定</a></div>";
    jQuery.jdThickBox({
        width:300,
        height:100,
        type:"text",
        source:statusDesc,
        title:"温馨提示"
    });
}
