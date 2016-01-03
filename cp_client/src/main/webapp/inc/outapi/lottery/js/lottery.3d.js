
function Lottery3D(){
    this.maxMulti = 50;
	this.type = 0;//选号类型
    this.balls_fs_dx = [];this.balls_fs_z3 = [];this.balls_fs_z6 = [];
    this.balls_hz_dx = [];this.balls_hz_z3 = [];this.balls_hz_z6 = [];
	this.balls_a = [];//已选择球数组
    this.balls_1 = [];this.balls_2 = [];this.balls_3 = [];
	this.select_balls = [];//产生的选号数组
    this.fs_dx();this.fs_z3();this.fs_z6();
	this.bind_clear_btn();

};


//绑定共用按钮
Lottery3D.prototype.bind_btn = function(){
	var __Ball = this ;
	
	//绑定直选机选1注
	get("btn-random1").onclick = function(){
		__Ball.random_select_dx(1);
	}
	//绑定组3机选1注
	get("random_fs_z3").onclick = function(){
		__Ball.random_select_z3(1);
	}
	//绑定组6机选1注
	get("random_fs_z6").onclick = function(){
		__Ball.random_select_z6(1);
	}


};

//绑定清空上方选号按钮
Lottery3D.prototype.bind_clear_btn = function(){
	var __Ball = this ;
	var btn_id = ["clear_fs_z3","clear_fs_z6"];
	for(var i = 0 ; i < btn_id.length; i ++ ){
		get(btn_id[i]).onclick = function(){
			var t = this.id.split("clear_fs_")[1];
			
			__Ball.clear_select($("#" + t + "ball-list li div.ball"),get("fs_" + t + "_text"));
			
		}
	}
};


//直选随机选择
Lottery3D.prototype.random_select_dx = function(n){
	__Ball = this;
	__Ball.balls_fs_dx = $("#baiball-list li div.ball,#shiball-list li div.ball,#geball-list li div.ball");
	this.select_balls = [];
	__Ball.clear_select(__Ball.balls_fs_dx,get("fs_dx_text"));
	for(var i = 0; i < n; i ++ ){
		var random = Math.floor(Math.random()*1000);
		if(random < 10 ){
			random = "00" + random;
		}
		else if(random < 100){
			random = "0" + random;
		}
		var random_select = new Array;
		random_select = (random + "").split("");
		var balls_0 = $("#baiball-list li div.ball");
		var balls_1 = $("#shiball-list li div.ball");
		var balls_2 = $("#geball-list li div.ball");
		for(var i = 0; i < random_select.length; i++)
		{
			for(var j = 0; j<10; j++)
			{
				if(eval(("balls_"+i))[j].innerHTML==random_select[i])
				{
					eval(("balls_"+i))[j].className = "ball selected" ;
				}
			}
		}
		__Ball.display(get("fs_dx_text"),3,1);
	};
};

//组3随机选择
Lottery3D.prototype.random_select_z3 = function(n){
	__Ball = this;
	this.select_balls = [];
	var balls = $("#z3ball-list li div.ball");
	__Ball.clear_select(balls,get("fs_z3_text"));
	for(var i = 0; i < n; i ++ ){
		var random = Math.floor(Math.random()*10);
		var random1 = Math.floor(Math.random()*10);
		while(random1 == random){
			random1 = Math.floor(Math.random()*10);
		};
		for(var m = 0; m < 10; m++){
			if(random == balls[m].innerHTML || random1 == balls[m].innerHTML){
				balls[m].className= "ball selected" ;
			}
		}
		__Ball.display(get("fs_z3_text"),2,2);
        /*var num;
        if (random < random1) {
            num = random + " " + random + " " + random1 ;
        } else {
            num = random1 + " " + random1 + " " + random ;
        }
		random_select = num.split(" ");
		var item = {
			"type":1,
			"balls":random_select.join(),//所选的球
			"totalstake":1  //注数
		};
		addLotteryItem(item);
		btu();*/
	}
};

//组6随机选择
Lottery3D.prototype.random_select_z6 = function(n){
	__Ball = this;
	var balls = $("#z6ball-list li div.ball");
	__Ball.clear_select(balls,get("fs_z6_text"));
	this.select_balls = [];
	for(var i = 0; i < n; i ++ ){
		var n0 = Math.floor(Math.random()*10);
		var n1 = Math.floor(Math.random()*10);
		var n2 = Math.floor(Math.random()*10);
		while(n0 == n1 || n0 == n2 || n1 == n2){
			n1 = Math.floor(Math.random()*10);
			n2 = Math.floor(Math.random()*10);
		};
		for(var i = 0;i < 10;i++)
		{
			if(n1==balls[i].innerHTML||n2==balls[i].innerHTML||n0==balls[i].innerHTML)
			{
				balls[i].className= "ball selected" ;
			}
		}
		__Ball.display(get("fs_z6_text"),3,1);
	};
};

Lottery3D.prototype.get_zhu = function(id, a) {
    var n_hz;
    var m = 0;
    switch (id) {
        case "fs_z3_balls":
            // n*(n-1)
            n_hz = [0,0,2,6,12,20,30,42,56,72,90];//选择0、1、2...个球时的注数
            break;
        case "fs_z6_balls":
            // n*(n-1)*(n-2)/6
            n_hz = [0,0,0,1,4,10,20,35,56,84,120];
            break;
    }
        return n_hz[a.length];

};

//删除提示球
function remove(ball){
	$(ball).children().remove();
}


//选择号码
Lottery3D.prototype.select_ball = function(id,balls){
	var __Ball = this ;
	var zhu;
	
	for(var i = 0; i < balls.length; i ++ ){
		balls[i].onclick = function(){
			this.className == "ball selected" ? this.className = "ball" :this.className = "ball selected" ;
			__Ball.get_balls(balls);
			var text_id = id.split("_balls")[0] + "_text";
            zhu = __Ball.get_zhu(id,__Ball.balls_a);
			__Ball.display(get(text_id),__Ball.balls_a.length,zhu);
		};/*
		balls[i].ontouchstart = function(){
			var arr = [];
			arr.push("<div class=\"touch\">"+this.innerHTML+"</div>");
			var str = arr.join("");
			$(this).append(str);
			var touchball = this;
			setTimeout(function(){remove(touchball);},1000);
		};
		
		balls[i].ontouchend = function(){
			$(this).children().remove();
		};
		balls[i].ontouchmove = function(){
			$(this).children().remove();
		};
		*/
	}
};

//清除所选号码球
Lottery3D.prototype.clear_select = function(balls,text){
	for(var i = 0; i < balls.length; i ++ ){
		balls[i].className = "ball";
	}
	text.innerHTML = "0个号码，共<span class='red'>0</span>注，共<span class='red'>￥0.00</span>元";

};

//选择文本显示信息
Lottery3D.prototype.display = function(obj,num,zhu){
	obj.innerHTML = num + "个号码，共<span class='red'>" +  zhu + "</span>注，共<span class='red'>￥" + (zhu * 2) + ".00</span>元";
};




//验证所选号码
Lottery3D.prototype.check_select_rule = function(type,n){
	if(this.balls_a.length < n){alertTip("至少选择" + n + "个号码");return false;}
    else if(this.get_zhu(type + "_balls",this.balls_a) * 2 > maxAmount){alertTip("单注金额不能超过" + maxAmount + "元");return false;}
	else{return true;}
};

Lottery3D.prototype.check_select_rule_dx = function(){
    var alert_text = "";
	if(this.balls_1.length < 1){alert_text += "请选择百位号码<br />";}
	if(this.balls_2.length < 1){alert_text += "请选择十位号码<br />";}
	if(this.balls_3.length < 1){alert_text += "请选择个位号码<br />";}
    if(alert_text != ""){alertTip(alert_text);return false;}
    else if(this.balls_1.length * this.balls_2.length * this.balls_3.length * 2 > maxAmount){alertTip("单注金额不能超过" + maxAmount + "元");return false;}
	else{return true;}
};

//得到所选择球
Lottery3D.prototype.get_balls = function(balls){
	var __Ball = this ;
	__Ball.balls_a = [];
	for(var i = 0; i < balls.length; i ++ ){
		if(balls[i].className == "ball selected"){
			var n = balls[i].innerHTML;
			__Ball.balls_a.push(n);
		}
	}
};

Lottery3D.prototype.get_balls_dx = function(){
	var __Ball = this ;
	__Ball.balls_1 = [],__Ball.balls_2 = [],__Ball.balls_3 = [];
	for(var i = 0; i < __Ball.balls_fs_dx.length; i ++ ){
		if(__Ball.balls_fs_dx[i].className == "ball selected"){
			var n = __Ball.balls_fs_dx[i].innerHTML;
			if(i < 10){__Ball.balls_1.push(n);}
			else if(i < 20){__Ball.balls_2.push(n);}
			else if(i < 30){__Ball.balls_3.push(n);}
		}
	}
};



//重复检查
Lottery3D.prototype.repeat = function(type){
	var __Ball = this ;
	var repeat_a = [];
	for(var i = 0; i < __Ball.select_balls.length; i++ ){
        var select_ball = __Ball.select_balls[i].replace(/\s/g, "");
		var str = "";
		var n = 0;
		var r_case;
		for(var j = 0; j < 3; j++ ){
			if(str.indexOf(select_ball.charAt(j)) != -1){n ++ ;};
			str += select_ball.charAt(j);
		}
		if(type == 0){
            r_case = (n == 1 && select_ball.charAt(0) != select_ball.charAt(2));
        }
		else {
            r_case = (n == 0);
        }
		if(r_case){
			var v = select_ball.split("").sort();//排序
			if(repeat_a.toString().indexOf(v.join(" ")) == -1){
				repeat_a.push(__Ball.select_balls[i]);
			}
		}
	}
	__Ball.select_balls = repeat_a;
};

//复式直选
Lottery3D.prototype.fs_dx = function(){
	var __Ball = this ;
    __Ball.balls_fs_dx = $("#baiball-list li div.ball,#shiball-list li div.ball,#geball-list li div.ball");

	//直接选择
    for(var i = 0; i < __Ball.balls_fs_dx.length; i ++ ){
		__Ball.balls_fs_dx[i].onclick = function(){
			this.className == "ball selected" ? this.className = "ball" :this.className = "ball selected" ;
			__Ball.get_balls_dx();
			__Ball.display(get("fs_dx_text"),__Ball.balls_1.length + __Ball.balls_2.length + __Ball.balls_3.length,__Ball.balls_1.length * __Ball.balls_2.length * __Ball.balls_3.length);
		};
		/*
		__Ball.balls_fs_dx[i].ontouchstart = function(){
			var arr = [];
			arr.push("<div class=\"touch\">"+this.innerHTML+"</div>");
			var str = arr.join("");
			$(this).append(str);
			var touchball = this;
			//setTimeout(function(){remove(touchball);},1000);
		};
		
		__Ball.balls_fs_dx[i].ontouchend = function(){
			$(this).children().remove();
		};
		__Ball.balls_fs_dx[i].ontouchmove = function(){
			$(this).children().remove();
		};
		__Ball.balls_fs_dx[i].ontouchcancel = function(){
			$(this).children().remove();
		};*/
	};

 	
	

	function clear_btn_ball(n){
		for(var i = n; i < n + 10; i ++ ){
			__Ball.balls_fs_dx[i].className = "ball";
		}
	};

	get("btn-dx-append").onclick = function(){
		__Ball.confirm_select_dx();

	};
	get("btn-dx-clear").onclick = function(){
		__Ball.clear_select(__Ball.balls_fs_dx,get("fs_dx_text"));
	};

};

//组3复式
Lottery3D.prototype.fs_z3 = function(){
	var __Ball = this ;
    //var balls = $("#fs_z3_balls li div.ball");
	__Ball.balls_fs_z3 = $("#z3ball-list li div.ball");
	this.select_ball("fs_z3_balls",__Ball.balls_fs_z3);
	//组3复式算法
	function fushi_z3(a){
		__Ball.select_balls = [];
		for(var i = 0; i < a.length; i ++ ){
			for(var m = 0; m < a.length; m ++ ){
                if( m < i ){
					__Ball.select_balls.push(a[m] + " " + a[i] + " " + a[i]);
				} else if( m > i ){
					__Ball.select_balls.push(a[i] + " " + a[i] + " " + a[m]);
				}
			}
		}
	};

	//确定选择
	get("btn-z3-append").onclick = function(){
		__Ball.confirm_select("fs_z3");
	};

};


//组6复式
Lottery3D.prototype.fs_z6 = function(){
	var __Ball = this ;
    __Ball.balls_fs_z6 = $("#z6ball-list li div.ball");
	this.select_ball("fs_z6_balls",__Ball.balls_fs_z6);
    //组6复式算法
	function fushi_z6(a){
		__Ball.select_balls = [];
		var n = a.length;
		for(var i = 0 ; i < n; i ++ ){
			for(var j = 0 ; j < n; j ++ ){
				for(var k = 0 ; k < n; k ++ ){
					__Ball.select_balls.push(a[i] + " " + a[j] + " " + a[k]) ;
				}
			}
		};
	    __Ball.repeat(1);
	};

	//确定选择
	get("btn-z6-append").onclick = function(){

		__Ball.confirm_select("fs_z6");
	};

};

//标准直选确定选择(选号拆单)
Lottery3D.prototype.confirm_select_dx = function(){
	var __Ball = this ;
	__Ball.get_balls_dx();
	if(__Ball.check_select_rule_dx()){
		var type = 0;
		var bai = __Ball.balls_1;
		var shi = __Ball.balls_2;
		var ge = __Ball.balls_3;
		for(var i = 0;i < bai.length; i++){
			for(var j = 0;j < shi.length; j++){
				for(var k = 0;k < ge.length; k++){
					var item = {
						"type":type,
						"bai":bai[i],
						"shi":shi[j],
						"ge":ge[k],
						"totalstake":1
					};
					addLotteryItem(item);
				}
			}
		}
		__Ball.clear_select(__Ball.balls_fs_dx,get("fs_dx_text"));
		btu();
	}
}

//标准直选确定选择(选号不拆单)
/*Lottery3D.prototype.confirm_select_dx = function(){
		var __Ball = this ;
		__Ball.get_balls_dx();
		if(__Ball.check_select_rule_dx()){
			var type = 0;
			var item = {
				"type":type,
				"bai":__Ball.balls_1.join(),//百位
				"shi":__Ball.balls_2.join(),//十位
				"ge":__Ball.balls_3.join(),//个位
				"totalstake":__Ball.balls_1.length * __Ball.balls_2.length * __Ball.balls_3.length  //注数
			};
			addLotteryItem(item);
			btu();
		}

};*/

//组3、组6确定选择按钮(选号拆单)
Lottery3D.prototype.confirm_select = function(type){
	var __Ball = this ;
	var balls = [];
	var arr = [];
	var onezhu;
	var ball;
    eval("balls = __Ball.balls_" + type);
	__Ball.get_balls(balls);
	balls = __Ball.balls_a;
	if(type == "fs_z3"){
		if(__Ball.check_select_rule(type,2)){
			for(var m = 0; m < balls.length; m++){
				for(var n = 0; n < balls.length; n++){
					if(m < n){
						var item = {
								"type":1,
								"balls":balls[m] + "," + balls[n] + "," + balls[n],
								"totalstake":1
						};
						addLotteryItem(item);
					}
					else if(m > n){
						var item = {
								"type":1,
								"balls":balls[n] + "," + balls[n] + "," + balls[m],
								"totalstake":1
						};
						addLotteryItem(item);
					}
				}
			}
			__Ball.clear_select($("#z3ball-list li div.ball"),get("fs_z3_text"));
			btu();
		}
	}
	else if(type == "fs_z6"){
		if(__Ball.check_select_rule(type,3)){
			for(var a = 0; a < balls.length; a++){
				for(var b = 0; b < balls.length; b++){
					for(var c = 0; c< balls.length; c++){
						arr.push(balls[a] + "," + balls[b] + "," + balls[c]);
					}
				}
			}
			for(var d = 0; d < arr.length; d++){//重复验证
				ball = arr[d].split(",");
				if(ball[0] < ball[1] && ball[1] < ball[2]){
					var item = {
							"type":2,
							"balls":arr[d],
							"totalstake":1
					};
					addLotteryItem(item);
				}
			}
			__Ball.clear_select($("#z6ball-list li div.ball"),get("fs_z6_text"));
			btu();
		}
	}
}

//组3、组6确定选择按钮(选号不拆单)
/*Lottery3D.prototype.confirm_select = function(type){
	var __Ball = this ;
	var balls = [];
    eval("balls = __Ball.balls_" + type);
	__Ball.get_balls(balls);
	var zhu = __Ball.get_zhu(type+"_balls",__Ball.balls_a);
	switch(type)
	{
		case "fs_z3": type_num = 1;min_select = 2;break;
		case "fs_z6": type_num = 2;min_select = 3;break;
	}
	if(__Ball.check_select_rule(type,min_select)){
		var item = {
			"type":type_num,
			"balls":__Ball.balls_a.join(),//所选的球
			"totalstake":zhu  //注数
		};
		addLotteryItem(item);
		btu();
	}
}*/

