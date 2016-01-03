(function(e) {
    var t = e.$,
    n = e.qh360cp;
    n.mobile.lotType = n.lottery.lotType.sscjx,
    n.mobile.price = n.lottery.price,
    n.mobile.kjfreq = 2e3,
    n.mobile.ylfreq = 5e3,
    n.mobile.issuefreq = 2e3,
    n.mobile.chartissuefreq = 1e4,
    n.mobile.getBounsTimer = null,
    n.mobile.getType = "",
    n.mobile.sumpks ='',
    n.mobile.sortNum ='',
    n.mobile.sortNums='',
    n.mobile.clearCacheUrl_dev = "/int/xsscindex/",
    n.mobile.qtopbetlistUrl_dev = "/int/qtopbetlist",
    n.mobile.qmissedanaUrl_dev = "/int/qmissedana",
    n.mobile.getkpkjcodeUrl_dev = "/int/getkpkjcode",
    n.mobile.qcurissueUrl_dev = "/int/qcurissue",
    n.mobile.getlossstatUrl_dev = "/int/getlossstat/",
    n.mobile.getchartUrl_dev = n.lottery.APIChart + "/zst/qmchartdata",
    n.mobile.getchartqkjUrl_dev = n.lottery.APIChart + "/zst/qkj",
    n.mobile.play = "normal",
    n.mobile.sortNo = "",
    n.mobile.isHelp = 0,
    n.mobile.touchSlider = null,
    n.mobile.distance,
    n.mobile.box,
    n.mobile.cart = {
        group: 0,
        code: "",
        count: 0
    },
    n.mobile.title = {
        "5D": "猜冠军",
        "5T": "猜冠亚军",
        "4D": "猜前三名",
        "3D": "猜前四名",
        F3: "猜前五名",
        F6: "猜前六名",
        "2D": "猜前七名",
        F2: "猜前八名",
        "1D": "猜前九名",
        R1: "猜前十名",
        R2: "",
        DD: ""
    },
    n.mobile.desc = {
        "5D": "猜冠军：猜开奖号码的第一位，奖金10元。",
        "5T": "猜冠亚军：猜开奖号码的前二位，按位置猜中1-2位，奖金2-55元。",
        "4D": "猜开奖号码的前三位，按位置猜中1-3位，奖金2-160元。",
        "3D": "猜开奖号码的前四位，按位置猜中1-4位，奖金2-350元。",
        F3:   "猜开奖号码的前五位，按位置猜中1-5位，奖金2-500元。",
        F6:   "猜开奖号码的前六位，按位置猜中1-6位，奖金2-10000元。",
        "2D": "猜开奖号码的前七位，按位置猜中1-7位，奖金2-20000元。",
        F2:   "猜开奖号码的前八位，按位置猜中1-8位，奖金2-40000元。",
        "1D": "猜开奖号码的前九位，按位置猜中1-9位，奖金2-80000元。",
        R1:   "猜前十名：猜开奖号码的前十位，全部位置猜中，奖金888,888元，全不中也得2元。",
        R2:   "",
        DD:   ""
    },
    n.mobile.jjprice = {
        DD: 4,
        R1: 11,
        R2: 116,
        "5T": 20460,
        "5D": 116e3,
        "4D": 1e4,
        "3D": 1160,
        "2D": 116,
        "1D": 11,
        F2: 58,
        F3: 385,
        F6: 190,
        Z3: 385,
        Z6: 190,
        Z2: 58,
        "5T3": 30
    },
    n.mobile.dxds = {
        "\u5927": 0,
        "\u5c0f": 1,
        "\u5355": 2,
        "\u53cc": 3
    },
    n.mobile.ItemID = {
        "3D": 27033,
        F3: 27405,
        F6: 27463,
        "2D": 27032,
        F2: 27042,
        "1D": 1005,
        R1: 1001,
        R2: 27222,
        DD: 27462
    },
    n.mobile.des = {
        zhi: "",
        zhu3: "",
        zhi2: "",
        zhu2: "",
        zhi1: "",
        dd: ""
    },
    n.mobile.initData = function(){
    	//alert("sdss");
    },
    n.mobile.AsynDownData = function() {
        var e = t("#setting"),
        r = t("#navtit"),
        i = n.string.getUrlParam("pt") || e.attr("pt") || "5D",
        s = "btn-pop-on",
        o = t("#container"),
        u = n.localstorage.getItem(n.mobile.storageName),
        a = ("0" + n.localstorage.getItem(n.mobile.storageMulName)) * 1,
        f = '<p>{cur}\u671f\u5f00\u5956\uff1a<strong class="red">{number}</strong></p>';
        u && (n.mobile.cart.code = u + ";", n.mobile.cart.group = u.split(/;/g).length, n.mobile.cart.count = a),
       
        o.find("a[play='" + i + "']").addClass(s),
        r.html('PK拾-<cite id="game">' + n.mobile.title[i] + '</cite><span class="head-arr"><em></em></span>'),
        t("#group").text(n.mobile.cart.group),
        t.getJSON(n.mobile.clearCacheUrl_dev + "?r=" + t.now(),
        function(r) {
            if (r) {
                var s = r.info,
                o = r.blist,
                u, a, l, c, h = [];
                e.attr({
                    endtime: s.EndTime,
                    prevtime: s.DsTimeSpace,
                    issale: s.IsOpen
                }),
                n.mobile.distance = s.issue,
                n.mobile.countDownInit(s.EndTime, s.DsTimeSpace);
                for (var p = 0; p < o.length; p++) c = o[p].issue,
                a = c.replace(/^\d{7}/g, "") * 1,
                u = n.mobile.distance.replace(/^\d{7}/g, "") * 1,
                a = a = a < 10 ? "0" + a: a,
                l = o[p].number.split("").join(" "),
                u = u = u < 10 ? "0" + u: u,
                p === 0 ? (n.mobile.kjQi = c, t("#fq1").text(a), t("#fq2").text(u), t("#fcode").text(l)) : h.push(f.replace(/{cur}/g, a).replace(/{number}/g, l));
                n.mobile.defaults(i),
                t("#kjlist").html(h.join(""))
            }
        })
    },
    n.mobile.defaults = function(e) {
        var r = t(".pick-box"),
       
        i = t("#setting"),
        s = t("#selected"),
        o = t("#detxt"),
        u = "none",
        a = "btn-pop-on",
        f;
        i.attr("pt", e) || "F6",
        s.attr("href", "/xssc/cart/?pt=" + e);
        if (n.mobile.play == "loss") f = r.eq(4);
        else if (/^DD$/.test(e)) f = r.eq(3);
        else if (/^F\d{1}$/.test(e)) f = r.eq(2);
        else {
            f = r.eq(1);
            var l = f.find("dl"),
            c = f.find(".line-3");
            l.addClass(u),
            c.addClass(u);
            if (/[F|D]/.test(e)) {
                var h = e.replace(/[F|D]/, "") * 1;
                for (var p = 5 - h; p < 5; p++) p < 4 && c.eq(p).removeClass(u),
                l.eq(p).removeClass(u)
            } else l.removeClass(u),
            	c.removeClass(u)
        }
        if(/^5D$/.test(e)){
        	for(var i =2;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        }else{
        	for(var i =2;i<=10;i++){
        		$("#dl"+i).removeAttr("hidden");
        		$("#line"+i).show();
        	}
        }
         
        if(/^5T$/.test(e)){
        	for(var i =3;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        } 
        
        if(/^4D$/.test(e)){
        	for(var i =4;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        } 
        
        if(/^3D$/.test(e)){
        	for(var i =5;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        } 
        
        if(/^F3$/.test(e)){
        	for(var i =6;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        } 
        
        if(/^F6$/.test(e)){
        	for(var i =7;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        }
        
        if(/^2D$/.test(e)){
        	for(var i =8;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        }
        if(/^F2$/.test(e)){
        	for(var i =9;i<=10;i++){
        		$("#dl"+i).attr("hidden",'');
        		$("#line"+i).hide();
        	}
        }
        
        if(/^1D$/.test(e)){
       		$("#dl10").attr("hidden",'');
       		$("#line10").hide();
        }
        
        n.mobile.play == "charts" && r.eq(0).removeClass(u),o.html(n.mobile.desc[e]),
        n.mobile.play != "loss" && (n.mobile.box = f.find("dl").not("." + u).find("ul"), n.mobile.updataMiss(n.mobile.kjQi))
    },
    n.mobile.dealPlus = function(e, r) {
        var i = t("#setting").attr("pt"),
        s = t("#detxt");
        if (/^(DD|2D|F2|F6)$/.test(i)) {
            var o = e.replace(/^\d{7}/g, "") * 1,
            u = (new Date(("20" + e.replace(/\d{3}$/g, "")).replace(/(\d{4})(\d{2})(\d{2})/g, "$1/$2/$3"))).getDay();
            u === 0 || u === 6 ? (n.mobile.jjprice.DD = 5, n.mobile.jjprice["2D"] = 140, n.mobile.jjprice.F2 = 70, n.mobile.jjprice.F6 = 230, n.mobile.desc.DD = '\u7ade\u731c\u5f00\u5956\u53f7\u5341\u4f4d\u3001\u4e2a\u4f4d\u7684\u5c5e\u6027\uff0c\u52a0\u5956<strong class="red">1</strong>\u5143\uff0c\u5956\u91d1<strong class="red">5</strong>\u5143', n.mobile.desc["2D"] = '\u6309\u4f4d\u7ade\u731c\u5f00\u5956\u53f7\u7801\u540e\u4e24\u4f4d\u6570\u5b57\uff0c\u52a0\u5956<strong class="red">24</strong>\u5143\uff0c\u5956\u91d1<strong class="red">140</strong>\u5143', n.mobile.desc.F2 = '\u7ade\u731c\u5f00\u5956\u53f7\u540e\u4e24\u4f4d\uff0c\u987a\u5e8f\u4e0d\u9650(\u542b\u5bf9\u5b50\u53f7)\uff0c\u52a0\u5956<strong class="red">12</strong>\u5143\uff0c\u5956\u91d1<strong class="red">70</strong>\u5143', n.mobile.desc.F6 = '\u81f3\u5c11\u9009\u62e93\u4e2a\u53f7\u7801\uff0c\u52a0\u5956<strong class="red">40</strong>\u5143\uff0c\u5956\u91d1<strong class="red">230</strong>\u5143') : o >= 55 && o <= 84 ? (n.mobile.jjprice.DD = 5, n.mobile.jjprice["2D"] = 140, n.mobile.jjprice.F2 = 70, n.mobile.jjprice.F6 = 230, n.mobile.desc.DD = '\u7ade\u731c\u5f00\u5956\u53f7\u5341\u4f4d\u3001\u4e2a\u4f4d\u7684\u5c5e\u6027\uff0c\u52a0\u5956<strong class="red">1</strong>\u5143\uff0c\u5956\u91d1<strong class="red">5</strong>\u5143', n.mobile.desc["2D"] = '\u6309\u4f4d\u7ade\u731c\u5f00\u5956\u53f7\u7801\u540e\u4e24\u4f4d\u6570\u5b57\uff0c\u52a0\u5956<strong class="red">24</strong>\u5143\uff0c\u5956\u91d1<strong class="red">140</strong>\u5143', n.mobile.desc.F2 = '\u7ade\u731c\u5f00\u5956\u53f7\u540e\u4e24\u4f4d\uff0c\u987a\u5e8f\u4e0d\u9650(\u542b\u5bf9\u5b50\u53f7)\uff0c\u52a0\u5956<strong class="red">12</strong>\u5143\uff0c\u5956\u91d1<strong class="red">70</strong>\u5143', n.mobile.desc.F6 = '\u81f3\u5c11\u9009\u62e93\u4e2a\u53f7\u7801\uff0c\u52a0\u5956<strong class="red">40</strong>\u5143\uff0c\u5956\u91d1<strong class="red">230</strong>\u5143') : (n.mobile.jjprice.DD = 4, n.mobile.jjprice["2D"] = 116, n.mobile.jjprice.F2 = 58, n.mobile.jjprice.F6 = 190, n.mobile.desc.DD = "\u7ade\u731c\u5f00\u5956\u53f7\u5341\u4f4d\u3001\u4e2a\u4f4d\u7684\u5c5e\u6027\uff0c\u5956\u91d14\u5143", n.mobile.desc["2D"] = "\u6309\u4f4d\u7ade\u731c\u5f00\u5956\u53f7\u7801\u540e\u4e24\u4f4d\u6570\u5b57\uff0c\u5956\u91d1116\u5143", n.mobile.desc.F2 = "\u7ade\u731c\u5f00\u5956\u53f7\u540e\u4e24\u4f4d\uff0c\u987a\u5e8f\u4e0d\u9650(\u542b\u5bf9\u5b50\u53f7)\uff0c\u5956\u91d158\u5143", n.mobile.desc.F6 = "\u81f3\u5c11\u9009\u62e93\u4e2a\u53f7\u7801\uff0c\u5956\u91d1190\u5143"),
            s.html(n.mobile.desc[i])
        }
    },
    n.mobile.selectCodeInit = function() {
        new t.touchSlider(".filt-popc", {
            wrap: ".filt-popc-cont",
            trigger: ".filt-popc-nav",
            duration: 500
        }),
        t(".pick-box").on(
       	 n.lottery.tap, "li",
        	function() {
	            o = "selected",
	            u = t(this),
	            
	            j=n.mobile.getType,
            	j=j.length==0?"5D":n.mobile.getType,
	            u.toggleClass(o);
	           if(/^5D$/.test(j)){
		    		var balls= $("#dl1").find(".selected");
		    		$("#count").html(balls.length);
		    		n.mobile.sumpks =balls.length;
		    		$("#price").html(balls.length*2);
	           }
	           if(/^5T$/.test(j)){
	            var count,price,ball1,ball2 =0;
		           for(var i =1;i<=2;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0){
		    			count =ball1*ball2;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	           
	           
	            if(/^4D$/.test(j)){
	               var count, ball2=0;
	               var price,ball1,ball3 =0;
		           for(var i =1;i<=3;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0){
		    			count =ball1*ball2*ball3;
		    			price =count*2;
		    		}
		    		
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	            if(/^3D$/.test(j)){
	               var count,price,ball1,ball2,ball3,ball4 =0;
		           for(var i =1;i<=4;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl4'){
		           	  	ball4=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0&&ball4!=0){
		    			count =ball1*ball2*ball3*ball4;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	            if(/^F3$/.test(j)){
	               var count,price,ball1,ball2,ball3,ball4,ball5 =0;
		           for(var i =1;i<=5;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl4'){
		           	  	ball4=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl5'){
		           	  	ball5=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0&&ball4!=0&&ball5!=0){
		    			count =ball1*ball2*ball3*ball4*ball5;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	           if(/^F6$/.test(j)){
	               var count,price,ball1,ball2,ball3,ball4,ball5,ball6 =0;
		           for(var i =1;i<=6;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl4'){
		           	  	ball4=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl5'){
		           	  	ball5=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl6'){
		           	  	ball6=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0&&ball4!=0&&ball5!=0&&ball6!=0){
		    			count =ball1*ball2*ball3*ball4*ball5*ball6;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	           
	           if(/^2D$/.test(j)){
	               var count,price,ball1,ball2,ball3,ball4,ball5,ball6,ball7=0;
		           for(var i =1;i<=7;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl4'){
		           	  	ball4=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl5'){
		           	  	ball5=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl6'){
		           	  	ball6=balls.length;
		           	  }
		           	   if(balls.length>0&&("dl"+i)=='dl7'){
		           	  	ball7=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0&&ball4!=0&&ball5!=0&&ball6!=0&&ball7!=0){
		    			count =ball1*ball2*ball3*ball4*ball5*ball6*ball7;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	           if(/^F2$/.test(j)){
	               var count,price,ball1,ball2,ball3,ball4,ball5,ball6,ball7,ball8=0;
		           for(var i =1;i<=8;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl4'){
		           	  	ball4=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl5'){
		           	  	ball5=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl6'){
		           	  	ball6=balls.length;
		           	  }
		           	  if(balls.length>0&&("dl"+i)=='dl7'){
		           	  	ball7=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl8'){
		           	  	ball8=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0&&ball4!=0&&ball5!=0&&ball6!=0&&ball7!=0&&ball8!=0){
		    			count =ball1*ball2*ball3*ball4*ball5*ball6*ball7*ball8;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	           if(/^1D$/.test(j)){
	               var count,price,ball1,ball2,ball3,ball4,ball5,ball6,ball7,ball8,ball9=0;
		           for(var i =1;i<=9;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl4'){
		           	  	ball4=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl5'){
		           	  	ball5=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl6'){
		           	  	ball6=balls.length;
		           	  }
		           	  if(balls.length>0&&("dl"+i)=='dl7'){
		           	  	ball7=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl8'){
		           	  	ball8=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl9'){
		           	  	ball9=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0&&ball4!=0&&ball5!=0&&ball6!=0&&ball7!=0&&ball8!=0&&ball9!=0){
		    			count =ball1*ball2*ball3*ball4*ball5*ball6*ball7*ball8*ball9;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	           if(/^R1$/.test(j)){
	               var count,price,ball1,ball2,ball3,ball4,ball5,ball6,ball7,ball8,ball9,ball10=0;
		           for(var i =1;i<=10;i++){
		           	  var balls= $("#dl"+i).find(".selected");
		           	  if(balls.length>0&&("dl"+i)=='dl1'){
		           	  	ball1=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl2'){
		           	  	ball2=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl3'){
		           	  	ball3=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl4'){
		           	  	ball4=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl5'){
		           	  	ball5=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl6'){
		           	  	ball6=balls.length;
		           	  }
		           	  if(balls.length>0&&("dl"+i)=='dl7'){
		           	  	ball7=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl8'){
		           	  	ball8=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl9'){
		           	  	ball9=balls.length;
		           	  }	
		           	  if(balls.length>0&&("dl"+i)=='dl10'){
		           	  	ball10=balls.length;
		           	  }	
		           }
		    		if(ball1!=0&&ball2!=0&&ball3!=0&&ball4!=0&&ball5!=0&&ball6!=0&&ball7!=0&&ball8!=0&&ball9!=0&&ball10!=0){
		    			count =ball1*ball2*ball3*ball4*ball5*ball6*ball7*ball8*ball9*ball10;
		    			price =count*2;
		    		}
		    		$("#count").html(count);
		    		n.mobile.sumpks =count;
		    		$("#price").html(price);
	           }
	           
	             
	            
        }),
        t("#myloss").on(n.lottery.tap, "div",
	        function() {
	            var e = t(this),
	            r = "num-box-on";
	            e.hasClass(r) ? e.removeClass(r) : e.addClass(r),
	            n.mobile.count()
	        }),
        t(".pop-box2").on("click", "a",
        	function(e) {
	            e.preventDefault();
	            var r = t(this),
	            i = r.attr("play"),
	            s = t("#setting"),
	            o = t("#detxt"),
	            u = t("#count"),
	            a = t("#price"),
	            f = t("#calcu"),
	            l = t(".line-3"),
	            c = t("#chartscd"),
	            h = t("#myhelp"),
	            p = t("#myparam"),
	            d = t(".pick-b"),
	            v = r.parent().parent().attr("action"),
	            m = t("#game"),
	            g = t("#star_d,#star_f,#star_dd"),
	            y = t("#loss"),
	            b = t("#charts"),
	            w = "btn-pop-on",
	            E = "none",
	            S = "fix";
	            n.mobile.play = v,
	            t(".btn-pop").removeClass(w),
	            r.addClass(w),
	            t(".head-arr").removeClass("head-arron"),
	            t(".pop-box2").addClass("spill"),
	            s.attr("pt", i),
	            m.text(n.mobile.title[i]),
	            t(".pick-box").addClass(E),
	            v != "charts" ? (s.parent().removeClass(E), c.addClass(E), h.removeClass(E), o.removeClass(E), p.addClass(E), d.removeClass("fix")) : (s.parent().addClass(E), c.removeClass(E), p.removeClass(E), h.addClass(E), o.addClass(E), d.addClass("fix"));
	            switch (n.mobile.play) {
	            case "normal":
	                g.removeClass(E);
	                break;
	            case "loss":
	                y.removeClass(E);
	                var x = t("#lossr");
	                if (/^R\d{1}$/.test(i)) {
	                    var T = [];
	                    x.removeClass(E);
	                    if (i == "R1") {
	                        var N = ["\u4e07", "\u5343", "\u767e", "\u5341", "\u4e2a"];
	                        for (var C = 0; C < N.length; C++) T.push('<option value="100' + (C + 1) + '">' + N[C] + "\u4f4d</option>")
	                    } else T.push('<option value="27222">\u4e07\u767e\u4f4d</option><option value="27223">\u4e07\u5341\u4f4d</option>								  <option value="27224">\u4e07\u4e2a\u4f4d</option>								  <option value="27225">\u5343\u767e\u4f4d</option>								  <option value="27226">\u5343\u5341\u4f4d</option>								  <option value="27227">\u5343\u4e2a\u4f4d</option>								  <option value="27228">\u767e\u5341\u4f4d</option>								  <option value="27229">\u767e\u4e2a\u4f4d</option>');
	                    x.html(T.join(""))
	                } else x.html("").addClass(E);
	                n.mobile.getmyplayLoss("init");
	                break;
	            case "charts":
	                g.removeClass(E),
	                b.removeClass(E),
	                t("#chartsH2").text(n.mobile.title[i]);
	                var k, L = /^F(\d{1})$/g,
	                A = /^(\d{1})[DT]$/g,
	                O = /^(DD)$/g;
	                L.test(i) ? k = 3 : A.test(i) ? (k = i.replace(A, "$1") * 1, k < 3 && (k = 3)) : k = 1,
	                n.mobile.touchSlider ? n.mobile.touchSlider.reset({
	                    steps: 0,
	                    left: 0,
	                    curIndex: 0,
	                    len: k
	                }) : n.mobile.touchSlider = new t.touchSlider(".in-entry", {
	                    wrap: ".chart-tab",
	                    trigger: ".xq-nav-chart",
	                    duration: 500,
	                    len: k,
	                    callback: function() {
	                        n.mobile.getCharts()
	                    }
	                }),
	                n.mobile.clearTemplate(),
	                n.mobile.getCharts()
	            }
	             n.mobile.getType =i,
	            n.mobile.defaults(i),
	            n.mobile.clear(u, a, f, o, i)
	          
        }),
        t("#navtit").on(n.lottery.tap,
        function() {
            var e = t(".head-arr"),
	            r = t(".pop-box2"),
	            i = "head-arron",
	            s = t(".btn-menu"),
	            o = "btn-menu-on",
	            u = "spill";
	            s.hasClass(o) && s.trigger(n.lottery.tap),
	            e.hasClass(i) ? (e.removeClass(i), r.addClass(u)) : (e.addClass(i), r.removeClass(u))
        }),
        t(".top-date").on(n.lottery.tap,
        function() {
            var e = t("#kjlist"),
            n = e.find("p"),
            r = t(".top-arr"),
            i = "top-arr-on",
            s = t("#setting"),
            o = "none";
            r.hasClass(i) ? (r.removeClass(i), n.addClass(o), e.css("height", 0), s.css("height", 22)) : (r.addClass(i), n.removeClass(o), e.css("height", 88), s.css("height", 110))
        }),
        t(".btn-chart").on(n.lottery.tap,
        function() {
            n.mobile.goChart.call(null, "F6", 2)
        }),
        t(".btn-menu").on(n.lottery.tap,
	        function() {
	            var e = t("#tools"),
	            r = t(this),
	            i = "btn-menu-on",
	            s = "spill",
	            o = t(".pop-box2"),
	            u = t("#navtit");
	            o.hasClass(s) || u.trigger(n.lottery.tap),
	            r.hasClass(i) ? (e.addClass(s), r.removeClass(i)) : (e.removeClass(s), r.addClass(i))
	        }),
        t("#addcart").on({
            click: function() {
                n.mobile.pickBall(t(this))
            },
            touchstart: function() {
                t(this).addClass("btn-addnmu-tap")
            },
            touchend: function() {
                t(this).removeClass("btn-addnmu-tap")
            }
        }),
        t("#bets").on({
           click: function() {
             	 n.mobile.pickBall(t(this)); 
             	 e.location.href = "pksorder.action";
            },
            touchstart: function() {
                t(this).addClass("btntap")
            },
            touchend: function() {
                t(this).removeClass("btntap")
            }
        }),
        t("#myhandle").on(n.lottery.tap,
        function() {
            var e = function() {
                var e = t("#mytools"),
                r = t("#arrows"),
                i = t("#myhelpr");
                e.hasClass("none") ? (n.mobile.isHelp = 1, r.removeClass("arr-on"), e.removeClass("none"), i.removeClass("none"), n.mobile.myHelper()) : (e.addClass("none"), i.addClass("none"), r.addClass("arr-on"), n.mobile.isHelp = 0)
            };
            n.cookie.get("Q") ? e() : n.lightBox.login(e)
        }),
        t("#lossmore").on("click",
	        function() {
	            var e = t("#lossmore"),
	            r = e.attr("total"),
	            i = e.attr("curpage");
	            i++,
	            i > r ? i = r: (e.text("\u6b63\u5728\u83b7\u53d6..."), e.attr("curpage", i), n.mobile.getmyplayLoss("next"))
	        }),
        t("#lossr").on("change",
	        function() {
	            var e = t(this),
	            r = e.val(),
	            i = t("#lossmore");
	            i.attr("curpage", 1),
	            n.mobile.getmyplayLoss("init")
	        });
        if (n.string.getHashModelName(0) == "play") {
            var r = n.string.getHashActionName(0),
            i = "F6",
            s = 2;
            if (r == "chart") {
                var o = n.string.getHashActionName(1),
                u = n.string.getHashModelName(1);
                u == "pt" && o && /^(5T|[12345]D|F[236]|DD)$/g.test(o) && (i = o),
                setTimeout(function() {
                    n.mobile.goChart.call(null, i, s)
                },400)
            } else r == "loss" && (s = 1, setTimeout(function() {
                n.mobile.goChart.call(null, i, s)
            },400))
        }
    },
    n.mobile.goChart = function(e, n) {
        var r = t(".filt-popc-entry").find("ul").eq(n),
        i = t(".filt-popc-nav").find("li").eq(n);
        i.trigger("click"),
        r.find("a[play='" + e + "']").trigger("click")
    },
    n.mobile.pickBall = function(r) {
	        var i = t("#setting").attr("pt") || "F6",s = r.attr("id");
            var v = t(".pick-box"),
            u = ["\u4e07", "\u5343", "\u767e", "\u5341", "\u4e2a"],
            m = 0,
            g,
            y = 0,
            b = "",
            w = t("#count").text() * 1,
            E = t(".pick-box").find("dl").find("ul"),
            S = E.length,
            j=n.mobile.getType,
            j=j.length==0?"5D":n.mobile.getType,
            x = /^(R)\d{1}$/.test(i);
           	var m = t("#setting");
           	
           	if(/^5D$/.test(j)&&r!=null){
					var ary=new Array();		    	
		    		var balls= $("#dl1").find(".selected");
		    		if(balls.length>0){
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +="5D|"+rVal+";";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	return;
		    		}
	        }else if(/^5T$/.test(j)){
		    	b+="5T|";
				var ary=new Array();		    	
		    	for(var i =1;i<=2;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码2！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("第一名和第二名有重复的号码！");
					return ;
				}
	        }else if(/^4D$/.test(j)){
	        	b+="4D|";
				var ary=new Array();		    	
		    	for(var i =1;i<=3;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码3！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        }else if(/^3D$/.test(j)){
	        	b+="3D|";
				var ary=new Array();		    	
		    	for(var i =1;i<=4;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码4！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        
	        }else if(/^F3$/.test(j)){
	        	b+="F3|";
				var ary=new Array();		    	
		    	for(var i =1;i<=5;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码5！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        }else if(/^F6$/.test(j)){
	        	b+="F6|";
				var ary=new Array();		    	
		    	for(var i =1;i<=6;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码6！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        
	        }else if(/^2D$/.test(j)){
	        	b+="2D|";
				var ary=new Array();		    	
		    	for(var i =1;i<=7;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码7！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        }else if(/^F2$/.test(j)){
	        	b+="F2|";
				var ary=new Array();		    	
		    	for(var i =1;i<=8;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码8！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        
	        }else if(/^1D$/.test(j)){
	        	b+="1D|";
				var ary=new Array();		    	
		    	for(var i =1;i<=9;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码！");
		    		 	return;
		    		}
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        }else if(/^R1$/.test(j)){
	        	b+="R1|";
				var ary=new Array();		    	
		    	for(var i =1;i<=10;i++){
		    		var balls= $("#dl"+i).find(".selected");
		    		if(balls.length>0){
		    			b+="#"+i+"*";
		    			balls.each(
			    			function(e) {
					    		 var rVal=$(this).find("span").html()
					    		 if(rVal.length>0){
					    		 	 ary.push(rVal);
						    		 b +=rVal+",";
					    		 }
		                	}
	                 	);
		    		}else{
		    			b="";
		    		 	alert("请选择号码10！");
		    		 	return;
		    		}
        		}
        		
        		if(ary.length>10){
        			alert("选择号码的数量不能大于10个");
					return ;
        		}
				if(n.mobile.checkArray(ary)){
					b="";
					alert("选择的号码有重复！");
					return ;
				}
	        }
	        
	       	b=b.substring(0,b.length-1);
	        b=n.mobile.sumpks+"&"+b+";";
	        b=n.mobile.sortNo(b)
           n.mobile.cart.code += b,
           n.mobile.cart.count += w,
           n.mobile.cart.group += 1;
       	var O =n.localstorage.getItem(n.mobile.storageMulName);
       	if(O==null||O==''||O.length<1){
       		O=0;
       	}
        var b = n.mobile.cart.code.replace(/;$/g, ""),
        O =parseInt(O)+parseInt(n.mobile.sumpks);
        
        n.localstorage.setItem(n.mobile.storageName, b),
        n.localstorage.setItem(n.mobile.storageMulName, O),
        s == "addcart" ? (t("#group").text(n.mobile.cart.group), n.mobile.clear(t("#count"), t("#price"), t("#calcu"))) : e.location.href = "/xssc/cart/?pt=" + i
        n.mobile.clearAll;
    },
    n.mobile.sortNo =function(data){
    		var rVal ='';
    		var ary  =data.split("|")[0].split("&")[1];
    		if(ary=='5D'){
    			 rVal =data.split("&")[1];
    		}
    		if(ary=="5T"){
    			var temp1=data.split("|")[1].split("*")[1].split("#")[0].split(",");
    			var temp2=data.split("|")[1].split("*")[2];
    			var ary1= n.mobile.sortNum(temp1);
    			var ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1));
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						rVal+="5T|"+(ary1[i]+','+ary2[j])+";";
					}
				}
    		}
    		if(ary=="4D"){
    			var temp1=data.split("|")[1].split("*")[1].split("#")[0].split(",");
    			var temp2=data.split("|")[1].split("*")[2].split("#")[0];
    			var temp3=data.split("|")[1].split("*")[3];
    			var ary1= n.mobile.sortNum(temp1);
    			var ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1));
    			var ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1));
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						for(var k=0;k<ary3.length;k++){
							rVal+="4D|"+(ary1[i]+','+ary2[j] +","+ary3[k])+";";
						}
					}
				}
    		}
    		if(ary=="3D"){
    			var temp1=data.split("|")[1].split("*")[1].split("#")[0].split(","),
    			 temp2=data.split("|")[1].split("*")[2].split("#")[0],
    			 temp3=data.split("|")[1].split("*")[3].split("#")[0],
    			 temp4=data.split("|")[1].split("*")[4],
    			 ary1= n.mobile.sortNum(temp1),
    			 ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1)),
    			 ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1)),
    			 ary4=n.mobile.sortNums(temp4.substring(0,temp4.length-1));
    			
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						for(var k=0;k<ary3.length;k++){
							for(var y=0;y<ary4.length;y++){
							
								rVal+="3D|"+(ary1[i]+','+ary2[j] +","+ary3[k]+","+ary4[y])+";";
							}
						}
					}
				}
    		}
    		if(ary=="F3"){
    		
    			var temp1=data.split("|")[1].split("*")[1].split("#")[0].split(","),
    			 temp2=data.split("|")[1].split("*")[2].split("#")[0],
    			 temp3=data.split("|")[1].split("*")[3].split("#")[0],
    			 temp4=data.split("|")[1].split("*")[4].split("#")[0],
    			 temp5=data.split("|")[1].split("*")[5],
    			 ary1= n.mobile.sortNum(temp1),
    			 ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1)),
    			 ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1)),
    			 ary4=n.mobile.sortNums(temp4.substring(0,temp4.length-1)),
    			 ary5=n.mobile.sortNums(temp5.substring(0,temp5.length-1));
    			
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						for(var k=0;k<ary3.length;k++){
							for(var y=0;y<ary4.length;y++){
								for(var o=0;o<ary5.length;o++){
									rVal+="F3|"+(ary1[i]+','+ary2[j] +","+ary3[k]+","+ary4[y]+","+ary5[o])+";";
								}
							}
						}
					}
				}
    		}
    		if(ary=="F6"){
    			
    			var  temp1=data.split("|")[1].split("*")[1].split("#")[0].split(","),
	    			 temp2=data.split("|")[1].split("*")[2].split("#")[0],
	    			 temp3=data.split("|")[1].split("*")[3].split("#")[0],
	    			 temp4=data.split("|")[1].split("*")[4].split("#")[0],
	    			 temp5=data.split("|")[1].split("*")[5].split("#")[0],
	    			 temp6=data.split("|")[1].split("*")[6],
	    			 ary1= n.mobile.sortNum(temp1),
	    			 ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1)),
	    			 ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1)),
	    			 ary4=n.mobile.sortNums(temp4.substring(0,temp4.length-1)),
	    			 ary5=n.mobile.sortNums(temp5.substring(0,temp5.length-1)),
	    			 ary6=n.mobile.sortNums(temp6.substring(0,temp6.length-1));
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						for(var k=0;k<ary3.length;k++){
							for(var y=0;y<ary4.length;y++){
								for(var o=0;o<ary5.length;o++){
									for(var p=0;p<ary6.length;p++){
										rVal+="F6|"+(ary1[i]+','+ary2[j] +","+ary3[k]+","+ary4[y]+","+ary5[o]+","+ary6[p])+";";
									}
									
								}
							}
						}
					}
				}
    		}
    		if(ary=="2D"){
    		
    			var  temp1=data.split("|")[1].split("*")[1].split("#")[0].split(","),
	    			 temp2=data.split("|")[1].split("*")[2].split("#")[0],
	    			 temp3=data.split("|")[1].split("*")[3].split("#")[0],
	    			 temp4=data.split("|")[1].split("*")[4].split("#")[0],
	    			 temp5=data.split("|")[1].split("*")[5].split("#")[0],
	    			 temp6=data.split("|")[1].split("*")[6].split("#")[0],
	    			 temp7=data.split("|")[1].split("*")[7],
	    			 ary1= n.mobile.sortNum(temp1),
	    			 ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1)),
	    			 ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1)),
	    			 ary4=n.mobile.sortNums(temp4.substring(0,temp4.length-1)),
	    			 ary5=n.mobile.sortNums(temp5.substring(0,temp5.length-1)),
	    			 ary6=n.mobile.sortNums(temp6.substring(0,temp6.length-1)),
	    			 ary7=n.mobile.sortNums(temp7.substring(0,temp7.length-1));
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						for(var k=0;k<ary3.length;k++){
							for(var y=0;y<ary4.length;y++){
								for(var o=0;o<ary5.length;o++){
									for(var p=0;p<ary6.length;p++){
										for(var r=0;r<ary7.length;r++){
											rVal+="2D|"+(ary1[i]+','+ary2[j] +","+ary3[k]+","+ary4[y]+","+ary5[o]+","+ary6[p]+","+ary7[r])+";";
										}
									}
								}
							}
						}
					}
				}
				
    		}
    		if(ary=="F2"){
    		
    			var  temp1=data.split("|")[1].split("*")[1].split("#")[0].split(","),
	    			 temp2=data.split("|")[1].split("*")[2].split("#")[0],
	    			 temp3=data.split("|")[1].split("*")[3].split("#")[0],
	    			 temp4=data.split("|")[1].split("*")[4].split("#")[0],
	    			 temp5=data.split("|")[1].split("*")[5].split("#")[0],
	    			 temp6=data.split("|")[1].split("*")[6].split("#")[0],
	    			 temp7=data.split("|")[1].split("*")[7].split("#")[0],
	    			 temp8=data.split("|")[1].split("*")[8],
	    			 ary1= n.mobile.sortNum(temp1),
	    			 ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1)),
	    			 ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1)),
	    			 ary4=n.mobile.sortNums(temp4.substring(0,temp4.length-1)),
	    			 ary5=n.mobile.sortNums(temp5.substring(0,temp5.length-1)),
	    			 ary6=n.mobile.sortNums(temp6.substring(0,temp6.length-1)),
	    			 ary7=n.mobile.sortNums(temp7.substring(0,temp7.length-1)),
	    			 ary8=n.mobile.sortNums(temp8.substring(0,temp8.length-1));
	    			 
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						for(var k=0;k<ary3.length;k++){
							for(var y=0;y<ary4.length;y++){
								for(var o=0;o<ary5.length;o++){
									for(var p=0;p<ary6.length;p++){
										for(var r=0;r<ary7.length;r++){
											for(var q=0;q<ary8.length;q++){
												rVal+="F2|"+(ary1[i]+','+ary2[j] +","+ary3[k]+","+ary4[y]+","+ary5[o]+","+ary6[p]+","+ary7[r]+","+ary8[q])+";";
											}
										}
									}
								}
							}
						}
					}
				}
    			
    		}
    		if(ary=="1D"){
    		
    			var  temp1=data.split("|")[1].split("*")[1].split("#")[0].split(","),
	    			 temp2=data.split("|")[1].split("*")[2].split("#")[0],
	    			 temp3=data.split("|")[1].split("*")[3].split("#")[0],
	    			 temp4=data.split("|")[1].split("*")[4].split("#")[0],
	    			 temp5=data.split("|")[1].split("*")[5].split("#")[0],
	    			 temp6=data.split("|")[1].split("*")[6].split("#")[0],
	    			 temp7=data.split("|")[1].split("*")[7].split("#")[0],
	    			 temp8=data.split("|")[1].split("*")[8].split("#")[0],
	    			 temp9=data.split("|")[1].split("*")[9];
    			
    			var  ary1= n.mobile.sortNum(temp1);
	    			 ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1)),
	    			 ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1)),
	    			 ary4=n.mobile.sortNums(temp4.substring(0,temp4.length-1)),
	    			 ary5=n.mobile.sortNums(temp5.substring(0,temp5.length-1)),
	    			 ary6=n.mobile.sortNums(temp6.substring(0,temp6.length-1)),
	    			 ary7=n.mobile.sortNums(temp7.substring(0,temp7.length-1)),
	    			 ary8=n.mobile.sortNums(temp8.substring(0,temp8.length-1)),
	    			 ary9=n.mobile.sortNums(temp9.substring(0,temp9.length-1));
    			
    			for(var i =0;i<ary1.length;i++){
					for(var j=0;j<ary2.length;j++){
						for(var k=0;k<ary3.length;k++){
							for(var y=0;y<ary4.length;y++){
								for(var o=0;o<ary5.length;o++){
									for(var p=0;p<ary6.length;p++){
										for(var r=0;r<ary7.length;r++){
											for(var q=0;q<ary8.length;q++){
												for(var u=0;u<ary9.length;u++){
													rVal+="1D|"+(ary1[i]+','+ary2[j] +","+ary3[k]+","+ary4[y]+","+ary5[o]+","+ary6[p]+","+ary7[r]+","+ary8[q]+","+ary9[u])+";";
												}
											}
										}
									}
								}
							}
						}
					}
				}
    		}
    		if(ary=="R1"){
    			var temp1=data.split("|")[1].split("*")[1].split("#")[0].split(","),
    			 temp2=data.split("|")[1].split("*")[2].split("#")[0],
    			 temp3=data.split("|")[1].split("*")[3].split("#")[0],
    			 temp4=data.split("|")[1].split("*")[4].split("#")[0],
    			 temp5=data.split("|")[1].split("*")[5].split("#")[0],
    			 temp6=data.split("|")[1].split("*")[6].split("#")[0],
    			 temp7=data.split("|")[1].split("*")[7].split("#")[0],
    			 temp8=data.split("|")[1].split("*")[8].split("#")[0],
    			 temp9=data.split("|")[1].split("*")[9].split("#")[0],
    			 temp10=data.split("|")[1].split("*")[10];
    			
    			var  ary1= n.mobile.sortNum(temp1);
	    			 ary2=n.mobile.sortNums(temp2.substring(0,temp2.length-1)),
	    			 ary3=n.mobile.sortNums(temp3.substring(0,temp3.length-1)),
	    			 ary4=n.mobile.sortNums(temp4.substring(0,temp4.length-1)),
	    			 ary5=n.mobile.sortNums(temp5.substring(0,temp5.length-1)),
	    			 ary6=n.mobile.sortNums(temp6.substring(0,temp6.length-1)),
	    			 ary7=n.mobile.sortNums(temp7.substring(0,temp7.length-1)),
	    			 ary8=n.mobile.sortNums(temp8.substring(0,temp8.length-1)),
	    			 ary9=n.mobile.sortNums(temp9.substring(0,temp9.length-1)),
	    			 ary10=n.mobile.sortNums(temp10.substring(0,temp10.length-1));
    			rVal+="R1|"+ary1+","+ary2+","+ary3+","+ary4+","+ary5+","+ary6+","+ary7+","+ary8+","+ary9+","+ary10+";";
    		}
    		return rVal;
    		
    },
    n.mobile.sortNums =function(data){
    
    	var arys =data.split(",");
    	var arytemp =new Array();
    	for(var i =0;i<arys.length;i++){
    		if(arys[i]!=null&&arys[i]!=''){
    			arytemp.push(arys[i]);
    		}	
    	}
    	return arytemp;
    
    },
    n.mobile.sortNum =function(data){
    
    	var arys =data;
    	var arytemp =new Array();
    	for(var i =0;i<arys.length;i++){
    		if(arys[i]!=null&&arys[i]!=''){
    			arytemp.push(arys[i]);
    		}	
    	}
    	return arytemp;
    
    },
    n.mobile.checkArray = function(ary) {
        	//判断数据重复
			var nary=ary.sort();
			 for(var i=0;i<ary.length;i++){
			 if (nary[i]==nary[i+1]){
			 	return true;
			 }
			}
			return false;
    },
    n.mobile.clear = function(e, r, i) {
        var s = t(".pick-box").find("dl").find("ul"),
        o = "selected",
        u = "num-box-on",
        a = t("#myloss");
        n.mobile.play == "loss" ? a.find("." + u).removeClass(u) : s.find("." + o).removeClass(o),
        e.text(0),
        r.text(0),
        i.html("")
    },
    n.mobile.getmyplayLoss = function(e, r) {},
    n.mobile.getBouns = function(e) {},
    n.mobile.dealActsue = function(e) {},
    n.mobile.checkIssue = function(e) {
        var t = e.Issue == n.mobile.distance;
        return ! t
    },
    n.mobile.getActIssue = function(e) {},
    n.mobile.countDownInit = function(e, r) {},
    n.mobile.calcu = function(e, r, i) { },
    n.mobile.count = function() {
    	
    },
    n.mobile.clearTemplate = function() {
        var e = 3,
        n, r, i = t("#setting").attr("pt");
        for (var s = 0; s < e; s++) n = t("#hd" + (s + 1)),
        r = t("#bd" + (s + 1)),
        s == 0 ? n.html('<tr><td colspan="12">loading...</td></tr>') : i == "DD" ? n.html("") : n.html('<tr><td colspan="12">loading...</td></tr>'),
        r.html("")
    },
    n.mobile.getChartNum = function(e, n) { },
    n.mobile.pollingCharts = function(e) {
        t.ajax({
            url: n.mobile.getchartqkjUrl_dev + "?lotId=" + n.mobile.lotType + "&issue=" + e + "&r=" + t.now(),
            dataType: "jsonp",
            success: function(t) {
                t && (t[0].Issue == t.preIssue ? n.mobile.getCharts() : setTimeout(function() {
                    n.mobile.pollingCharts.apply(null, [e])
                },
                n.mobile.chartissuefreq))
            }
        })
    },
    n.mobile.getCharts = function() {},
    n.mobile.updataMiss = function(e) {},
    t(function() {
        n.mobile.storageName = "storage" + n.mobile.lotType,
        n.mobile.storageMulName = "storage" + n.mobile.lotType + "mul",
        n.mobile.AsynDownData(),
        n.mobile.selectCodeInit()
    })
})(window);