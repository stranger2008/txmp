
function LotterySSQ(){
    this.maxMulti = 50;
    this.row = 0; //行记录数
    this.separator = "|";
    this.tuomaType = true;  //判断红色（胆码、拖码）
    this.reds1_dt = []; // 选中的红球胆码
    this.reds2_dt = []; // 选中的红球拖码
    this.blues_dt = []; // 选中的蓝球
    this.total_dt = 1;
}

LotterySSQ.prototype.getAllRedBall = function(){
	return ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33"];
};

LotterySSQ.prototype.getAllBlueBall = function(){
	return ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"];
};

//胆拖玩法
LotterySSQ.prototype.dantuo = function(){
	var __Ball = this;
    this.dt_type();
	//红球胆码
	for(var i = 0; i < __Ball.red_balls1_dt.length; i ++ ){
		__Ball.red_balls1_dt[i].index = i;
		__Ball.red_balls1_dt[i].onclick = function(){
            __Ball.select_ball_dt(this, "dt_red1");
			__Ball.display_info();
		}
	}

	//红球拖码
	/*for(var i = 0; i < __Ball.red_balls2_dt.length; i ++ ){
		__Ball.red_balls2_dt[i].index = i;
		__Ball.red_balls2_dt[i].onclick = function(){
			__Ball.select_ball_dt(this, "dt_red2");
			__Ball.display_info();
		}
	}*/

	//蓝球
	for(var i = 0; i < __Ball.blue_balls_dt.length; i ++ ){
		__Ball.blue_balls_dt[i].index = i;
		__Ball.blue_balls_dt[i].onclick = function(){
			__Ball.select_ball_dt(this, "dt_blue");
			__Ball.display_info();
		}
	}

	//清空上方选号
	get("btn-clear-dt").onclick = function(){
		$('#dantuo_msg').css('display','none');
        $('#jd_title').hide();
        __Ball.clear_all();
		__Ball.display_info();
	};

	//确定选择
	get("btn-append-dt").onclick = function(){
		__Ball.confirm_select_dt();		
	};

    /*get("btn-reselect-dt").onclick = function() {
        __Ball.clear_all();
        __Ball.display_info();
        __Ball.after_edit_dt();
    };*/

    get("danma").onclick = function(){
        get(this.id).className = 'curr curr-f';
        get('tuoma').className = '';
        get('red_ga').innerHTML = '胆码';
        get('gray').innerHTML = '至少选择1个号码，最多选择5个号码';
        $('#jd_title').hide();
        __Ball.danma();
    }

    get("tuoma").onclick = function(){
        get(this.id).className = 'curr curr-r';
        get('danma').className = '';
        get('red_ga').innerHTML = '拖码';
        get('gray').innerHTML = '至少选择2个号码';
        $('#jd_title').hide();
        __Ball.tuoma();
    }
};

LotterySSQ.prototype.danma = function(){
//    alert('拖码='+this.reds2_dt.length+'     胆码='+this.reds1_dt.length);
    if(this.tuomaType==false){
        for(var i = 0; i < this.reds2_dt.length; i ++ ){
            this.reds2_dt[i].className == "ball selected" ? this.reds2_dt[i].className = "ball dantuo" :this.reds2_dt[i].className = "ball selected" ;
        }
        for(var i = 0; i < this.reds1_dt.length; i ++ ){
            this.reds1_dt[i].className == "ball dantuo" ? this.reds1_dt[i].className = "ball selected" :this.reds1_dt[i].className = "ball dantuo" ;
        }
    }
    this.tuomaType = true;
}

LotterySSQ.prototype.tuoma = function(){
//    alert('胆码='+this.reds1_dt.length+'     拖码='+this.reds2_dt.length);
    if(this.tuomaType==true){
        for(var i = 0; i < this.reds1_dt.length; i ++ ){
            this.reds1_dt[i].className == "ball selected" ? this.reds1_dt[i].className = "ball dantuo" :this.reds1_dt[i].className = "ball selected" ;
        }
        for(var i = 0; i < this.reds2_dt.length; i ++ ){
            this.reds2_dt[i].className == "ball dantuo" ? this.reds2_dt[i].className = "ball selected" :this.reds2_dt[i].className = "ball dantuo" ;
        }
    }
    this.tuomaType = false;
}
//清空所有球
LotterySSQ.prototype.clear_all = function(){
	for(var i = 0; i < 33; i ++ ){
		this.red_balls1_dt[i].className = "ball";
		if(i < 16){
			this.blue_balls_dt[i].className = "ball";
		}
	}
};

//去除红球重复
LotterySSQ.prototype.trim_repeat = function(obj,index){
	
	
    if(obj[index].className == "ball dantuo"){
        var str = '';
        if ($('#red_ga').html()=='胆码'){
            str = '拖码';
        }else{
            str = '胆码';
        }
        alertTip('此号码已被'+str+'选中!');
    }
};

//选择双色球
LotterySSQ.prototype.select_ball_dt = function(ball,type){
//    ball.className == "ball selected" ? ball.className = "ball" :ball.className = "ball selected" ;
	
	
	if(get('danma').className != '' && this.red_balls1_dt[ball.index].className == "ball" && this.reds1_dt.length >= 5 && ball.parentNode.parentNode.id == "dt-redball-list1"){
    	alertTip("最多选择5个红球胆码!");
    }
	//选中胆码
	else if(ball.parentNode.parentNode.id == "dt-redball-list1" && ball.className == "ball" && get('danma').className != '' && caltotalstake()*2+currentMoney_dt(this.reds1_dt.length+1,this.reds2_dt.length,this.blues_dt.length)>maxAmount ){
		alertTip("总投注金额不能超过"+maxAmount+"元!");
	}
	//撤销胆码
	else if(ball.parentNode.parentNode.id == "dt-redball-list1" && ball.className == "ball selected" && get('danma').className != '' && caltotalstake()*2+currentMoney_dt(this.reds1_dt.length-1,this.reds2_dt.length,this.blues_dt.length)>maxAmount ){
		alertTip("总投注金额不能超过"+maxAmount+"元!");
	}
	//选中拖码
	else if(ball.parentNode.parentNode.id == "dt-redball-list1" && ball.className == "ball" && get('tuoma').className != '' && caltotalstake()*2+currentMoney_dt(this.reds1_dt.length,this.reds2_dt.length+1,this.blues_dt.length)>maxAmount ){
		alertTip("总投注金额不能超过"+maxAmount+"元!");
	}
	//选中蓝球
	else if(ball.parentNode.parentNode.id == "dt-blueball-list" && ball.className == "ball" && caltotalstake()*2+currentMoney_dt(this.reds1_dt.length,this.reds2_dt.length,this.blues_dt.length+1)>maxAmount ){
		alertTip("总投注金额不能超过"+maxAmount+"元!");
	}
	else{
	if(ball.className == "ball selected"){
        ball.className = "ball";
    }else if(ball.className == "ball"){
        ball.className = "ball selected";
    }
    switch(type){
		case "dt_red1" :
		this.trim_repeat(this.red_balls2_dt,ball.index);
		break;
		
		case "dt_blue" :
            $('#jd_title').hide();
		break;
	}
	}
};


//当前投注金额（胆拖玩法）
function currentMoney_dt(danma_ball,tuoma_ball,blue_ball){
	var total = 0;
	var x = 1;
	var c = 6 - danma_ball;
	total = n = tuoma_ball;
	for(var i = 1; i < c; i ++ ){
		total *= ( -- n );
	}

	for(var i = c; i > 1; i -- ){
		x = x * i;
	}
	total = Math.ceil(total / x * blue_ball) * 2;
	return total;
}


//显示选择信息
LotterySSQ.prototype.display_info = function(){
	this.num_balls();
	this.get_zhu();

	get("dantuo_text").innerHTML = (this.reds1_dt.length + this.reds2_dt.length) + "个红球(" + this.reds1_dt.length + "个胆码," + this.reds2_dt.length + "个拖码)，" + this.blues_dt.length + "个蓝球，共<span id='total-zhu-dt'>" + this.total_dt + "</span>注，共<span class='red' id='total-money-dt'>￥" + (this.total_dt * 2) + ".00</span>元";
};

LotterySSQ.prototype.get_zhu = function(){
    var total = 0;
	var n1 = this.reds1_dt.length;
	var n2 = this.reds2_dt.length;
	var n3 = this.blues_dt.length;
	if(n1 > 0 && n1 < 6 && n2 > 1 && (n1 + n2) > 6 && (n1 + n2) < 34 && n3 >0){

		var x = 1;
		var c = 6 - n1;
		total = n = n2;
		for(var i = 1; i < c; i ++ ){
			total *= ( -- n );
		}

		for(var i = c; i > 1; i -- ){
			x = x * i;
		}
		this.total_dt = Math.ceil(total / x * n3);
	}else{
        this.total_dt = 0;
    }
};

//胆拖接口
LotterySSQ.prototype.dt_type = function(){
    /*this.reds1_dt = []; // 选中的红球胆码
    this.reds2_dt = []; // 选中的红球拖码
    this.blues_dt = []; // 选中的蓝球
    this.total_dt = 1;*/
    this.red_balls1_dt = $("#dt-redball-list1 li div.ball");
    this.red_balls2_dt = $("#dt-redball-list1 li div.ball");
//    this.red_balls2_dt = $("#dt-redball-list2 li div.ball");
    this.blue_balls_dt = $("#dt-blueball-list li div.ball");

};

//复式选号
LotterySSQ.prototype.fs = function(){

	var __Ball = this;

	//绑定各种按键事件
	this.fs_type();
//	get("random_red").onclick = function(){__Ball.random_select(get('randomnum-red').value,0);};
	get("randomnum-red").onchange = function(){__Ball.random_select(get('randomnum-red').value,0);};
//	get("random_blue").onclick = function(){__Ball.random_select(get('randomnum-blue').value,1);};
	get("randomnum-blue").onchange = function(){__Ball.random_select(get('randomnum-blue').value,1);};

//	get("btn-clear-red").onclick = function(){__Ball.clear_ball($("#redball-list"));__Ball.display();}; //清空
//	get("btn-clear-blue").onclick = function(){__Ball.clear_ball($("#blueball-list"));__Ball.display();};   //清空
	get("btn-clear").onclick = function(){
		$('#fs_msg').css('display','none');
        $("#randomnum-red").val(0);
        $("#randomnum-blue").val(0);
        __Ball.clear_ball($("#redball-list"));
        __Ball.clear_ball($("#blueball-list"));
        __Ball.display();
    };

    /*get("btn-reselect").onclick = function() {    //重新选号
        __Ball.clear_ball($("#redball-list"));
        __Ball.clear_ball($("#blueball-list"));
        __Ball.display();
        __Ball.after_edit();
    };*/

    //添加列表框
    get("btn-append").onclick = function(){
        __Ball.confirm_select();
        __Ball.total_num();
    };

    //机选
    get("btn-random1").onclick = function(){
    	$('#fs_msg').css('display','none');
    	$("#randomnum-red").val(6);
    	$("#randomnum-blue").val(1);
        __Ball.random_fs(1);
        __Ball.random_select(6,0);
        __Ball.random_select(1,1);
//        __Ball.total_num();
    };
	/*get("btn-random5").onclick = function(){__Ball.random_fs(5);__Ball.total_num();};
    get("btn-random10").onclick = function(){__Ball.random_fs(10);__Ball.total_num();};*/
//	get("btn-allclear").onclick = function(){if($("#num tbody tr").length > 0 && confirm("确定删除全部吗？")){__Ball.del_all();__Ball.total_num();}};
    
//	get("btn-getdata").onclick = function(){__Ball.get_data();};

	this.beitou();  //倍数
//    this.beitou_calc();

};

//转换为页面上的投注号码显示
LotterySSQ.prototype.set_data=function(){
	/*var datas=get("lotteryNumber").value;
	if(datas!=""){
	var arr=datas.split(";");
	for(var i=0;i<arr.length;i++){
        var num = arr[i].split(":");
		this.row++;
        if (num[0] == "2") {
            var dt_num = num[1];
            if(num[1].indexOf("$") != -1) {
                dt_num = "(" + num[1].replace("$", ")");
            }
            $("#num tbody").append("<tr id='" + this.row + "'><td width='100'>" + switch_id(num[0]) + "</td><td width='250' class='al'><div title=\"" + dt_num + "\">" + dt_num + "</div></td><td width='110'>[" + num[2] +"注 " + num[2]*2 + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select_dt("+ this.row + ")'>修改</a><a href='javascript:' onclick='ball.del_select_dt("+ this.row + ")'>删除</a></td></tr>");
        } else {
            $("#num tbody").append("<tr id='" + this.row + "'><td width='100'>" + switch_id(num[0]) + "</td><td width='250' class='al'><div title=\"" + num[1] + "\">" + num[1] + "</div></td><td width='110'>[" + num[2] +"注 " + num[2]*2 + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select("+ this.row + ")'>修改</a><a href='javascript:' onclick='ball.del_select("+ this.row + ")'>删除</a></td></tr>");
        }
	}
	this.total_num();
	}

    function switch_id(obj){
		switch(obj) {
            case "0":
                return "单式投注";
            case "1":
                return "复式投注";
            case "2":
                return "胆拖投注";
            default:
                return "其他";
        }
	}*/
};


//获取一注并放到session
LotterySSQ.prototype.get_oneFs = function(lotteryCategory){
	this.get_oneFs1(lotteryCategory);
	precardHtml();
	pretotalHtml();
};

LotterySSQ.prototype.get_oneFs1 = function(lotteryCategory){
	var allRedBall = this.getAllRedBall();
    var allBlueBall = this.getAllBlueBall();
    var randomRed = randomSelect(allRedBall, 6).sort();
    var randomBlue = randomSelect(allBlueBall, 1)[0];
    var item = {
        "type":0,
        "red":randomRed.join(),
        "blue":randomBlue,
        "totalstake":1
    };
    addLotteryItem(item);
};

//转化所选号码并附到隐藏域用以提交
LotterySSQ.prototype.get_data = function(){
	/*var datas = [];
    var num = 0;
	$("#num tbody tr").each(function(){
        var tds = $(this).children("td");
        var zhuNum = parseInt(tds.eq(2).text().split("注")[0].split("[")[1]);
		datas.push(switch_type(tds.eq(0).text()) + ":" + tds.eq(1).text().split("[")[0] + ":" + zhuNum);
        num += zhuNum;
    });

	get("lotteryNumber").value = datas.join(";");
    get("totalStake").value = num;
    get("totalFee").value = num * 2 * get("multi").value;

    function switch_type(obj){
		switch(obj) {
            case "单式投注":
                return 0;
            case "复式投注":
                return 1;
            case "胆拖投注":
                return 2;
            default:
                return -1;
        }
	}*/
};

//复式投注查询
LotterySSQ.prototype.fs_search = function(){
	var __Ball = this;
	//绑定各种按键事件
	this.type = "fs_search";
	this.fs_type();
	get("confirm_select").onclick = function(){__Ball.confirm_select();};
	get("clear_all").onclick = function(){__Ball.clear_ball(get("red_balls"));__Ball.clear_ball(get("blue_balls"));__Ball.display();};

};


//复式投注接口
LotterySSQ.prototype.fs_type = function(){
	this.red_num = 0;//复式投注方式的红球个数
	this.blue_num = 0;//复式投注方式的蓝球个数
	this.red_balls = $("#redball-list li div.ball");
	this.blue_balls = $("#blueball-list li div.ball");
	this.fs_bind();

};

LotterySSQ.prototype.fs_bind = function(){

	var __Ball = this;
	for(var i = 0; i < 33; i ++ ){
		__Ball.red_balls[i].onclick = function(){__Ball.select_ball(this, "fs_red");__Ball.display();};//复式投注红球
		if(i < 16){
			__Ball.blue_balls[i].onclick = function(){__Ball.select_ball(this, "fs_blue");__Ball.display();};//复式投注蓝球
		}
	}
};

//选择双色球
LotterySSQ.prototype.select_ball = function(ball,type){
	
	var __Ball = this;
	
	switch(type){

		//复式投注红球
		case "fs_red" :
		if(ball.className == "ball selected"){ball.className = "ball";__Ball.red_num --; } 
		else if(caltotalstake()*2+currentMoney(parseInt(this.red_num)+1,this.blue_num)>maxAmount){
			alertTip("总投注金额不能超过"+maxAmount+"元!");
		}
		else if(this.red_num >= 16) {
			alertTip("最多选择16个红球!");
		}
		else{ball.className = "ball selected";__Ball.red_num ++;}
		break;

		//复式投注蓝球
		case "fs_blue" :
		if(ball.className == "ball selected"){ball.className = "ball";__Ball.blue_num --; }
		else if(caltotalstake()*2+currentMoney(this.red_num,parseInt(this.blue_num)+1)>maxAmount){
			alertTip("总投注金额不能超过"+maxAmount+"元!");
		}
		else{ball.className = "ball selected";__Ball.blue_num ++;}
		break;
	}
};


//当前投注金额（标准玩法）
function currentMoney(redball,blueball){
	var total = n = redball;
	for(var i = 1; i < 6; i ++ ){
		total *= ( -- n );
	}
	var totalmoney = Math.ceil(total / 720 * blueball) *2;
	return totalmoney;
}


//显示已选择双色球信息
LotterySSQ.prototype.display = function(){

		if(this.red_num > 5 && this.red_num < 17 && this.blue_num > 0){//通过检验才进行注数计算

			var total = n = this.red_num;
			for(var i = 1; i < 6; i ++ ){
				total *= ( -- n );
			}
			this.total = Math.ceil(total / 720 * this.blue_num);

		}
		else{ this.total = 0 ;}

		get("fs_text").innerHTML = this.red_num + "个红球，" + this.blue_num + "个蓝球，共<span id='total-zhu'>" + this.total + "</span>注，共<span class='red' id='total-money'>￥" + this.total*2 + ".00</span>元";

};

//复式投注随机选择
LotterySSQ.prototype.random_select = function(s,color){

    var result;
	if(color == 0){
		if(currentMoney(s,this.blue_num)+caltotalstake()*2>maxAmount){
			alertTip("总投注金额不能超过"+maxAmount+"元!");
			$("#randomnum-red").val(0);			
		}
		else{
			result = randomSelect(this.getAllRedBall(), s);
			this.clear_ball($("#redball-list"));
			for(var i =0; i < s; i ++ ){
				this.red_balls[parseInt(result[i],10)-1].className = "ball selected";
			}
			this.red_num = s;
			this.display();
		}
	}else{
		
		if(currentMoney(this.red_num,s)+caltotalstake()*2>maxAmount){
			alertTip("总投注金额不能超过"+maxAmount+"元!");
			$("#randomnum-blue").val(0);
		}
		else{
			result = randomSelect(this.getAllBlueBall(), s);
			this.clear_ball($("#blueball-list"));
			for(var i =0; i < s; i ++ ){
				this.blue_balls[parseInt(result[i],10)-1].className = "ball selected";
			}
			this.blue_num = s;
			this.display();
		}
	}

};


LotterySSQ.prototype.random_fs = function(n){

	for(var k = 0; k < n; k++){
        var allRedBall = this.getAllRedBall();
        var allBlueBall = this.getAllBlueBall();
        var randomRed = randomSelect(allRedBall, 6).sort();
        var randomBlue = randomSelect(allBlueBall, 1)[0];

        this.row++;
        var num = randomRed.join(" ") + this.separator + randomBlue;
//        $("#num tbody").append("<tr id='" + this.row + "'><td width='100'>单式投注</td><td width='250' class='al'><div title=\"" + num + "\">" + num + "</div></td><td width='110'>[1注 2元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select("+ this.row + ")'>修改</a><a href='javascript:' onclick='ball.del_select("+ this.row + ")'>删除</a></td></tr>");
	}
};

/*
复式投注规则

红球:至少选择6个，最多20个
蓝球:至少选择1个
*/
LotterySSQ.prototype.check_rule_fs = function(){
		this.fs_alert_text = "";
		if(this.red_num < 6){this.fs_alert_text += "红球区至少选择6个号码<br />";}
		if(this.red_num > 16){this.fs_alert_text += "红球区最多选择16个号码<br />";}
		if(this.blue_num < 1 ){this.fs_alert_text += "蓝球区至少选择1个号码<br />"}
        if(this.total*2 > maxAmount){this.fs_alert_text += "单注金额不能超过" + maxAmount + "元<br />"}
		if(this.fs_alert_text != "" ){return false;}
		else{return true;}
};

/*
胆拖投注规则
*/
LotterySSQ.prototype.check_rule_dt = function(){
    this.dt_alert_text = "";
	if(this.reds1_dt.length < 1){this.dt_alert_text += "至少选择1个红球胆码<br />";}
	if(this.reds1_dt.length > 5){this.dt_alert_text += "最多选择5个红球胆码<br />";}
	if(this.reds2_dt.length < 2){this.dt_alert_text += "至少选择2个红球拖码<br />";}
	if((this.reds1_dt.length + this.reds2_dt.length) < 7 ){this.dt_alert_text += "红球胆码加拖码不能少于7个<br />";}
	if((this.reds1_dt.length + this.reds2_dt.length) > 20 ){this.dt_alert_text += "红球胆码加拖码不能大于20个<br />";}
	if(this.blues_dt.length < 1){this.dt_alert_text += "至少选择1个蓝球<br />";}
    if(this.total_dt*2 > maxAmount){this.dt_alert_text += "单注金额不能超过" + maxAmount + "元<br />"}
	if(this.dt_alert_text != ""){return false;}
    else{return true;}
};




//确定选择
LotterySSQ.prototype.confirm_select = function(){

		if(this.check_rule_fs()){
			$('#fs_msg').css('display','none');
				var reds = [];
				var blues = [];
				for(var i =0; i < 33; i ++ ){
					if(this.red_balls[i].className == "ball selected"){reds.push(this.red_balls[i].innerHTML)};
					if(i < 16){
						if(this.blue_balls[i].className == "ball selected"){blues.push(this.blue_balls[i].innerHTML)};
					}
				}
			if(!this.type){

                var type = (reds.length > 6 || blues.length > 1) ? 1:0;
                var item = {
                    "type":type,
                    "red":reds.join(),
                    "blue":blues.join(),
                    "totalstake":this.total
                };
                addLotteryItem(item);
                btu();
                /*alert(sessionStorage.getItem('lottery_1'));*/

                /*this.row++;
                var type = (reds.length > 6 || blues.length > 1) ? "复式投注" : "单式投注";
                var num = reds.join(" ") + this.separator + blues.join(" ");
                $("#num tbody").append("<tr id='" + this.row + "'><td width='100'>" + type + "</td><td width='250' class='al'><div title=\"" + num + "\">" + num + "</div></td><td width='110'>["+ this.total +"注 " + this.total*2 + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select("+ this.row + ")'>修改</a><a href='javascript:' onclick='ball.del_select("+ this.row + ")'>删除</a></td></tr>");*/
			}

            this.clear_ball($("#redball-list"));
            this.clear_ball($("#blueball-list"));
            this.display();
		}else{
            alertTip(this.fs_alert_text);
            //$('#fs_msg').css('display','block');
            //$('#fs_msg').html(this.fs_alert_text);
        }

};

LotterySSQ.prototype.confirm_select_dt = function(){
    this.num_balls();

	if(this.check_rule_dt()){
		$('#dantuo_msg').css('display','none');
        this.row++;

        var bt1 = [];
		var bt2 = [];
		var bt3 = [];
		for(var i = 0; i < this.reds1_dt.length; i ++ ){
			bt1.push(this.reds1_dt[i].innerHTML);
		}
		for(var i = 0; i < this.reds2_dt.length; i ++ ){
			bt2.push(this.reds2_dt[i].innerHTML);
		}
		for(var i = 0; i < this.blues_dt.length; i ++ ){
			bt3.push(this.blues_dt[i].innerHTML);
		}

        var item = {
            "type":2,
            "dan":bt1.join(),
            "tuo":bt2.join(),
            "blue":bt3.join(),
            "totalstake": this.total_dt
        };
        
        addLotteryItem(item);
        btu();
        
        /*var num = "(" + bt1.join(" ") +  ")" + bt2.join(" ") + this.separator + bt3.join(" ");
        $("#num tbody").append("<tr id='" + this.row + "'><td width='100'>胆拖投注</td><td width='250' class='al'><div title=\"" + num + "\">" + num + "</div></td><td width='110'>[" + this.total_dt + "注 " + (this.total_dt * 2) + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select_dt("+ this.row + ")'>修改</a><a href='javascript:' onclick='ball.del_select_dt("+ this.row + ")'>删除</a></td></tr>");*/
    	this.total_num();
		this.clear_all();
		this.display_info();

	}else{
		alertTip(this.dt_alert_text);
		//$('#dantuo_msg').css('display','block');
		//$('#dantuo_msg').html(this.dt_alert_text);
	}
};

LotterySSQ.prototype.num_balls = function(){
    var __Ball = this;
	this.reds1_dt = [];
	this.reds2_dt = [];
	this.blues_dt = [];

    if (__Ball.tuomaType){
        for(var i = 0; i < 33; i ++ ){
            if(__Ball.red_balls1_dt[i].className == "ball selected"){
                this.reds1_dt.push(__Ball.red_balls1_dt[i]);
            }else if(__Ball.red_balls1_dt[i].className == "ball dantuo"){
                this.reds2_dt.push(__Ball.red_balls1_dt[i]);
            }
        }
    }
    
    if (__Ball.tuomaType==false){
        for(var i = 0; i < 33; i ++ ){
            if(__Ball.red_balls1_dt[i].className == "ball selected"){
                this.reds2_dt.push(__Ball.red_balls1_dt[i]);
            }else if(__Ball.red_balls1_dt[i].className == "ball dantuo"){
                this.reds1_dt.push(__Ball.red_balls1_dt[i]);
            }
        }
    }

    /*for(var i = 0; i < 33; i ++ ){
        if(__Ball.red_balls1_dt[i].className == "ball selected"){
            if (__Ball.tuomaType){
                this.reds1_dt.push(__Ball.red_balls1_dt[i]);
            }else{
                this.reds2_dt.push(__Ball.red_balls1_dt[i]);
            }
        }
	}*/

	/*for(var i = 0; i < 33; i ++ ){
		if(__Ball.red_balls2_dt[i].className == "ball selected"){this.reds2_dt.push(__Ball.red_balls2_dt[i])}
	}*/

	for(var i = 0; i < 16; i ++ ){
		if(__Ball.blue_balls_dt[i].className == "ball selected"){this.blues_dt.push(__Ball.blue_balls_dt[i])}
	}

};

//倍投显示
LotterySSQ.prototype.total_num = function(){
    /*return;*/
	/*var num = 0;
    var multi = get("multi").value;
	var n = $("#num tbody tr").length;
	if(!isNaN(multi) && multi > 0 && n > 0){
        $("#num tbody tr").each(function(){
            num += parseInt($(this).children("td").eq(2).text().split("注")[0].split("[")[1]);
        });
		get("total_zhushu").innerHTML = "注数：" + num + "注";
		get("total_money").innerHTML = "￥" + num * 2 * multi + ".00";
        get("base_total").innerHTML = "本次投注总金额：<span class='red'>￥" + num * 2 * multi + ".00</span>元 [" + num + "注 "+ multi +"倍]";
	}else{
		get("total_zhushu").innerHTML = "注数：0注";
		get("total_money").innerHTML = "￥0.00";
        get("base_total").innerHTML = "本次投注总金额：<span class='red'>￥0.00</span>元 [0注 0倍]";
	}

    LotteryPlay.otherTotal(num);*/
};

//倍投
LotterySSQ.prototype.beitou = function(){
	var __Ball = this;
	get("multi").onkeyup = function(){
        this.value = this.value.replace(/\D/, "");
        if (isNaN(this.value) || this.value < 1) {
            this.value = "1";
        }
        if (this.value > __Ball.maxMulti) {
            this.value = __Ball.maxMulti;
        }
        __Ball.total_num(this.value);
	};
    get("multi").onkeydown = function(){
        if (this.value < 1) {
            this.value = "1";
        }
	};
};

//倍投加减
LotterySSQ.prototype.beitou_calc = function(){
	var __Ball = this;
    var reg = /^\d{1,2}$/;
	get("btn-multi-add").onclick = function(){

				if(reg.test(get("multi").value) && get("multi").value < __Ball.maxMulti){
                    get("multi").value++;
					__Ball.total_num(get("multi").value);
				}
	};
    get("btn-multi-sub").onclick = function(){

				if(reg.test(get("multi").value) && get("multi").value > 1){
                    get("multi").value--;
					__Ball.total_num(get("multi").value);
				}
	};
};


//清除已选择球
LotterySSQ.prototype.clear_ball = function(obj){

	var ball = obj.find("li div.ball");
	for(var i =0; i < ball.length; i ++ ){
		ball[i].className = "ball";
	}

	switch(obj[0].id){

		case "redball-list" : this.red_num = 0; break;
		case "blueball-list" : this.blue_num = 0; break;

	}
};



LotterySSQ.prototype.del_all = function(){
	$("#num tbody").empty();
    this.row = 0;
};

LotterySSQ.prototype.del_select = function(id){
    if ($("#num tbody tr#" + id).hasClass("sonor")) {
        this.after_edit();
    }
    $("#num tbody tr#" + id).remove();
    this.total_num();
};

LotterySSQ.prototype.del_select_dt = function(id){
    if ($("#num tbody tr#" + id).hasClass("sonor")) {
        this.after_edit_dt();
    }
    $("#num tbody tr#" + id).remove();
    this.total_num();
};

LotterySSQ.prototype.edit_select = function(id){
    this.clear_ball($("#redball-list"));
    this.clear_ball($("#blueball-list"));
    // 点击标准页签
    $("#fs_select").click();
    var pos = $("#select").offset().top;
    $("html,body").animate({scrollTop:pos}, 500);

    var datas = $("#num tbody tr#" + id).children("td").eq(1).text().split("[")[0].split(this.separator);
    var reds = datas[0].split(" ");
    var blues = datas[1].split(" ");

    for (var i = 0; i < reds.length; i++) {
        // 十进制
        var index = parseInt(reds[i], 10);
        if (index > 0)
            this.select_ball(this.red_balls[index - 1], "fs_red");
    }
    for (var j = 0; j < blues.length; j++) {
        var index = parseInt(blues[j], 10);
        if (index > 0)
            this.select_ball(this.blue_balls[index - 1], "fs_blue");
    }
    this.display();
    this.before_edit(id);
};

LotterySSQ.prototype.edit_select_dt = function(id){
    this.clear_all();
    // 点击胆拖投注页签
    $("#dantuo_select").click();
    var pos = $("#select").offset().top;
    $("html,body").animate({scrollTop:pos}, 500);

    var datas = $("#num tbody tr#" + id).children("td").eq(1).text().split("[")[0].split(this.separator);
    var reddatas = datas[0].substr(1).split(")");
    var reds1 = reddatas[0].split(" ");
    var reds2 = reddatas[1].split(" ");
    var blues = datas[1].split(" ");

    for (var i = 0; i < reds1.length; i++) {
        // 十进制
        var index = parseInt(reds1[i], 10);
        if (index > 0)
            this.select_ball_dt(this.red_balls1_dt[index - 1], "dt_red1");
    }
    for (var i = 0; i < reds2.length; i++) {
        // 十进制
        var index = parseInt(reds2[i], 10);
        if (index > 0)
            this.select_ball_dt(this.red_balls2_dt[index - 1], "dt_red2");
    }
    for (var j = 0; j < blues.length; j++) {
        var index = parseInt(blues[j], 10);
        if (index > 0)
            this.select_ball_dt(this.blue_balls_dt[index - 1], "dt_blue");
    }
    this.display_info();
    this.before_edit_dt(id);
};

//确定修改
LotterySSQ.prototype.confirm_edit = function(id){

		if(this.check_rule_fs()){
				var reds = [];
				var blues = [];
				for(var i =0; i < 33; i ++ ){
					if(this.red_balls[i].className == "ball selected"){reds.push(this.red_balls[i].innerHTML)};
					if(i < 16){
						if(this.blue_balls[i].className == "ball selected"){blues.push(this.blue_balls[i].innerHTML)};
					}
				}

			if(!this.type){
                var type = (reds.length > 6 || blues.length > 1) ? "复式投注" : "单式投注";
                var num = reds.join(" ") + this.separator + blues.join(" ");
                if ($("#num tbody tr#" + id).length > 0) {
                    $("#num tbody tr#" + id).html("<td width='100'>" + type + "</td><td width='250' class='al'><div title=\"" + num + "\">" + num + "</div></td><td width='110'>["+ this.total +"注 " + this.total*2 + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select("+ id + ")'>修改</a><a href='javascript:' onclick='ball.del_select("+ id + ")'>删除</a></td>");
                } else {
                    this.row++;
                    $("#num tbody").append("<tr id='" + this.row + "'><td width='100'>" + type + "</td><td width='250' class='al'><div title=\"" + num + "\">" + num + "</div></td><td width='110'>["+ this.total +"注 " + this.total*2 + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select("+ this.row + ")'>修改</a><a href='javascript:' onclick='ball.del_select("+ this.row + ")'>删除</a></td></tr>");
                }

			}

                this.clear_ball($("#redball-list"));
		        this.clear_ball($("#blueball-list"));
		        this.display();
                this.after_edit();

			}
		else{
			alertTip(this.fs_alert_text);}

};

LotterySSQ.prototype.before_edit = function(id){
    $("#num tbody tr:gt(-1)").removeClass("sonor");
    $("#num tbody tr#" + id).addClass("sonor");
    // 确认修改按钮的绑定事件
    get("btn-append").innerHTML = "确认修改";
    $("#btn-clear").hide();
    $("#btn-reselect").show();
    var __Ball = this;
    get("btn-append").onclick = function() {
        __Ball.confirm_edit(id);
        __Ball.total_num();
    };
};

LotterySSQ.prototype.after_edit = function(){
    // 确认修改后还原事件
    get("btn-append").innerHTML = "添加到号码篮";
    $("#btn-reselect").hide();
    $("#btn-clear").show();
    var __Ball = this;
    get("btn-append").onclick = function() {
        __Ball.confirm_select();
        __Ball.total_num();
    };
};

//确定修改
LotterySSQ.prototype.confirm_edit_dt = function(id){
    this.num_balls();

    if(this.check_rule_dt()){

        var bt1 = [];
		var bt2 = [];
		var bt3 = [];
		for(var i = 0; i < this.reds1_dt.length; i ++ ){
			bt1.push(this.reds1_dt[i].innerHTML);
		}
		for(var i = 0; i < this.reds2_dt.length; i ++ ){
			bt2.push(this.reds2_dt[i].innerHTML);
		}
		for(var i = 0; i < this.blues_dt.length; i ++ ){
			bt3.push(this.blues_dt[i].innerHTML);
		}

        var num = "(" + bt1.join(" ") +  ")" + bt2.join(" ") + this.separator + bt3.join(" ");
        if ($("#num tbody tr#" + id).length > 0) {
            $("#num tbody tr#" + id).html("<td width='100'>胆拖投注</td><td width='250' class='al'><div title=\"" + num + "\">" + num + "</div></td><td width='110'>[" + this.total_dt + "注 " + (this.total_dt * 2) + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select_dt("+ id + ")'>修改</a><a href='javascript:' onclick='ball.del_select_dt("+ id + ")'>删除</a></td>");
        } else {
            this.row++;
            $("#num tbody").append("<tr id='" + this.row + "'><td width='100'>胆拖投注</td><td width='250' class='al'><div title=\"" + num + "\">" + num + "</div></td><td width='110'>[" + this.total_dt + "注 " + (this.total_dt * 2) + "元]</td><td align='center' width='100'><a class='alert-ball' href='javascript:' onclick='ball.edit_select_dt("+ this.row + ")'>修改</a><a href='javascript:' onclick='ball.del_select_dt("+ this.row + ")'>删除</a></td></tr>");
        }
    	this.total_num();
		this.clear_all();
		this.display_info();
        this.after_edit_dt();

	}else{alertTip(this.dt_alert_text);}
};

LotterySSQ.prototype.before_edit_dt = function(id){
    $("#num tbody tr:gt(-1)").removeClass("sonor");
    $("#num tbody tr#" + id).addClass("sonor");
    // 确认修改按钮的绑定事件
    get("btn-append-dt").innerHTML = "确认修改";
    $("#btn-clear-dt").hide();
    $("#btn-reselect-dt").show();
    var __Ball = this;
    get("btn-append-dt").onclick = function() {
        __Ball.confirm_edit_dt(id);
        __Ball.total_num();
    };
};

LotterySSQ.prototype.after_edit_dt = function(){
    // 确认修改后还原事件
    get("btn-append-dt").innerHTML = "添加到号码篮";
    $("#btn-reselect-dt").hide();
    $("#btn-clear-dt").show();
    var __Ball = this;
    get("btn-append-dt").onclick = function() {
        __Ball.confirm_select_dt();
        __Ball.total_num();
    };
};

//获取一注并放到session
LotterySSQ.prototype.get_oneFs = function(lotteryCategory){
    var allRedBall = this.getAllRedBall();
    var allBlueBall = this.getAllBlueBall();
    var randomRed = randomSelect(allRedBall, 6).sort();
    var randomBlue = randomSelect(allBlueBall, 1)[0];

    var item = {
        "type":0,
        "red":randomRed.join(),
        "blue":randomBlue,
        "totalstake":1
    };
    addLotteryItem(item);
	precardHtml();
	pretotalHtml();
}

//机选count注并放到session
LotterySSQ.prototype.get_fiveOrTenFs = function(count){
    for(var i = 0; i < count; i++) {
        var allRedBall = this.getAllRedBall();
        var allBlueBall = this.getAllBlueBall();    	
    	var randomRed = randomSelect(allRedBall, 6).sort();
    	var randomBlue = randomSelect(allBlueBall, 1)[0];
	    var item = {
	        "type":0,
	        "red":randomRed.join(),
	        "blue":randomBlue,
	        "totalstake":1
	    };
	    ssqLotteryObj.count += 1;
	    item["num"] = ssqLotteryObj.count;
	    ssqLotteryObj.lottery.push(item);
    }
    $("#cue_span").html("换换手气？点\"重选\"按钮重新机选" + count + "注");
    if (count == 5) {
    	$("#rd_btn_reRandom10").hide();
    	$("#rd_btn_reRandom5").show();
    }
    if (count == 10) {
    	$("#rd_btn_reRandom5").hide();
    	$("#rd_btn_reRandom10").show();
    }    
    randomCardHtml(count);
    randomtotalHtml();
}

/** 获得双色球的信息 */
function getRandomSSQCardHtml(count) {
	var arr = [];
	var item, ss;
	for ( var i = 0; i < ssqLotteryObj.lottery.length; i++) {
		item = ssqLotteryObj.lottery[i];
		arr.push("<li class=\"ss-lst-li\"><span class=\"new-tbl-type\">");
		arr.push("<span class=\"new-tbl-cell pop-num\"><a onclick=\"deleteRandomLotteryItem(" + count + "," + item.num + ");\" href=\"javascript:void(0)\" class=\"new-btn-close\">close</a></span>");
		ss = item.red.split(",");
		for ( var k = 0; k < ss.length; k++) {
			arr.push("<span class=\"new-tbl-cell pop-num\">" + ss[k] + "</span>");
		}
		ss = item.blue.split(",");
		for ( var k = 0; k < ss.length; k++) {
			arr.push("<span class=\"new-tbl-cell pop-num2\">" + ss[k] + "</span>");
		}
		arr.push("</li>");
	}
	return arr.join("");
}

/* 计算总注数 */
function calssqtotalstake() {
	var count = 0;
	for ( var i = 0; i < ssqLotteryObj.lottery.length; i++) {
		count += ssqLotteryObj.lottery[i].totalstake;
	}
	return count;
}

/* pre总金额 */
function randomtotalHtml() {
	var totalstake = calssqtotalstake();
	$("#random_text").html(
			"共" + totalstake + "注，共<span class=\"red\">￥"
					+ totalstake * 2 + ".00</span>元");
}

function randomCardHtml(count) {
    var str = getRandomSSQCardHtml(count);
    if (count == 5) {
    	$("#randomInfo5").html(str);
    } else {
    	$("#randomInfo10").html(str);
    }
	if (ssqLotteryObj.lottery.length == 0) {// 玩家没有任何投注
		$("#rd_btn_append").hide();
	} else {
		$("#rd_btn_append").show();
	}	
	//$("#randomInfo").css("height", "246px");
	// 当前显示行数
//	if (ssqLotteryObj.lottery.length > 5) {
//		$("#randomInfo").css("height", "246px");
//		h = $("#randomInfo").offset().top;
//		lastIndex = ssqLotteryObj.lottery.length -1;
//		var items=$("#randomInfo li");
//		var fisrtItem=items.eq(0);
//		var lastItem=items.eq(lastIndex);
//		document.getElementById("randomInfo").addEventListener("touchstart",
//				function(event) {
//					scrollStartPos = this.scrollTop + event.touches[0].pageY;
//					event.preventDefault();
//				}, false);
//
//		document.getElementById("randomInfo").addEventListener("touchmove",
//				function(event) {
//					this.scrollTop = scrollStartPos - event.touches[0].pageY;
//					event.preventDefault();
//					
//					var top=$(this).offset().top;
//				}, false);
//	} else {
//		$("#randomInfo").css("height", "auto");
//	}	
}

function deleteRandomLotteryItem(count,num) {
	for ( var i = 0; i < ssqLotteryObj.lottery.length; i++) {
		if (ssqLotteryObj.lottery[i].num == num) {
			ssqLotteryObj.lottery.splice(i, 1);
			break;
		}
	}
    randomCardHtml(count);
    randomtotalHtml();
}
