(function(e) {

    var t = e.$,
    n = e.qh360cp;
  
    n.mobile.lotType =  n.lottery.lotType.sscjx,
    n.mobile.price = n.lottery.price,
    n.mobile.maxMoney = n.lottery.maxMoney,
    n.mobile.buyPercent = "0.05",
    n.mobile.balls = 0,
    n.mobile.baodiPerc = 0,
    n.mobile.Callbacks = function() {
        t("#pay_buy").trigger("click")
    },
    n.mobile.countDownInit = function() {},
    n.mobile.codeViewInit = function() {
        var e = n.string.filterScript(n.string.getUrlParam("luck")),
        r = /^1\|(\d{2}[\s ]{1}){6}(\d{2})$/g;
        e && r.test(e) && (n.localstorage.setItem(n.mobile.storageName, e), n.localstorage.setItem(n.mobile.storageMulName, 1));
        var i = n.localstorage.getItem(n.mobile.storageMulName) * 1,
        s = n.localstorage.getItem(n.mobile.storageName).split(";"),
        o = [],
        u = s.length,
        a,
        f = t("#mycart"),
        m =0,
        l = '<li code="{code}" count="{count}"><a herf="javascript:;" class="cp-cls">X</a>{name}\uff1a<strong class="red">{red}</strong></li>',
        c = "";
        $("#ballList").val(n.localstorage.getItem(n.mobile.storageName));
        if (i) {
        
            for (var h = 0; h < u; h++) {
                a = s[h].split("|");
                if(a!=null&&a!=""){
                	
                	m+=parseInt(1);
                	c = l.replace("{code}", s[h]).replace("{count}",a[0].split("&")[0]).replace("{name}", (h+1)).replace("{red}", a[1]),
                	o.push(c)
                }
            }
            f.html(o.join(""))
        } else f.html('<li class="nocode">\u60a8\u8fd8\u6ca1\u6709\u9009\u62e9\u4efb\u4f55\u53f7\u7801\uff01</li>');
        n.mobile.count()
        n.mobile.balls =m;
        n.mobile.price =m*2;
        
        $("#boards").val(n.mobile.balls);
        $("#ownCount").html(n.mobile.balls);
        $("#ownMoney").html(n.mobile.price);
    },
    n.mobile.codeConfInit = function() {
        t("#mycart").on(n.lottery.tap, ".cp-cls",
        function() {
            var e = t(this).parent(),
            r = e.attr("code"),
            i = e.attr("count") * 1,
            s,
            o = t("#mycart");
            n.mobile.balls-=1;
            s = (n.localstorage.getItem(n.mobile.storageName) + ";").replace(r+";", "").replace(/^;|;$/g, ""),
            $("#ballList").val(s);
            n.localstorage.setItem(n.mobile.storageName, s),
            n.localstorage.setItem(n.mobile.storageMulName, n.localstorage.getItem(n.mobile.storageName).split(";").length-1),
            e.remove(),
            n.mobile.count("bd"),
            o.find("li").length === 0 && o.html('<li class="nocode"> \u60a8\u8fd8\u6ca1\u6709\u9009\u62e9\u4efb\u4f55\u53f7\u7801\uff01</li>')
             $("#ownCount").html(n.mobile.balls);
       		 $("#ownMoney").html(n.mobile.balls*2);
        }),
        t("#tools").on(n.lottery.tap, ".btn2",
          function() {
            var e = t(this).attr("act"),
            r = 1,
            i = t("#mycart"),
            s = i.find(".nocode"),
            o,
            u = "",
            a = '<li code="{code}" count="{count}"><a herf="javascript:;" class="cp-cls">X</a>\u5355\u5f0f\uff1a<strong class="red">{red}</strong></li>';
           var allBall =$("#ballList").val();
            if (/rnd\d{1}/.test(e)) {
                r = e.replace(/rnd(\d{1})/, "$1") * 1,
                s.length === 1 && s.remove();
                for (var f = 0; f < r; f++) {
                    o = n.number.random({
                        min: 1,
                        max: 30,
                        size: 7,
                        repeat: 0,
                        sort: 1
                    })[0].join(" ");
                    var l = "1|" + o;
                    u += a.replace("{code}", o).replace("{count}", 1).replace("{red}", o),
                    n.localstorage.setItem(n.mobile.storageName, (n.localstorage.getItem(n.mobile.storageName) + ";" + l).replace(/^;|;$/g, "")),
                    n.localstorage.setItem(n.mobile.storageMulName, ("0" + n.localstorage.getItem(n.mobile.storageMulName)) * 1 + 1)
                    
                    if(allBall.length>0){
                   	 	$("#ballList").val(allBall+=(','+l));
                    }else{
                    	$("#ballList").val(allBall+=(l));
                    }
                   
                }
                i.append(u)
            } else{
            	i.html('<li class="nocode">\u60a8\u8fd8\u6ca1\u6709\u9009\u62e9\u4efb\u4f55\u53f7\u7801\uff01</li>'),
	            n.localstorage.removeItem(n.mobile.storageMulName),
	            n.localstorage.removeItem(n.mobile.storageName),
	            t("#meBD").val(0),
	            t(".mycoop").find("strong").eq(2).html(0);
	            n.mobile.count("bd")
	            $("#ballList").val("");
            }
        }),
        n.mobile.minRGcheck = function(e, t) {},
        t("#title,#content").on({}),
        t("#meRG").on({}),
        t("#meBD").on({})
    },
    n.mobile.AsynDownData = function() { },
    n.mobile.count = function(e) {},
    n.mobile.uiInit = function() { 
    	  t(".ipt-6").not("#meRG,#meBD").on({
            keyup: function() {
            	var e = t(this).val().replace(/[^\d]/g, "") || 1
            	r = 10 ;
                r = r == 0 ? 1e5: r,
                e <= 0 ? e = 1 : e > r && (e = r),
                t(this).val(e);
               	$("#ownMoney").html(n.mobile.balls*2*t(this).val());
            }
        })
    
    },
    n.mobile.submitInit = function() {  },
    n.mobile.postData = function() { },
    t(function() {
        n.mobile.storageName = "storage" + n.mobile.lotType,
        n.mobile.storageMulName = "storage" + n.mobile.lotType + "mul",
        n.mobile.AsynDownData(),
        n.mobile.codeConfInit(),
        n.mobile.uiInit(),
        n.mobile.submitInit(),
        n.mobile.codeViewInit()
    })
})(window);