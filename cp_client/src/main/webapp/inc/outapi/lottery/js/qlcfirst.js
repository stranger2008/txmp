(function(e) {
    var t = e.$,
    n = e.qh360cp;
    n.mobile.lotType = n.lottery.lotType.qlc,
    n.mobile.price = n.lottery.price,
    n.mobile.maxMoney = n.lottery.maxMoney,
    n.mobile.LOT_METHOD = 0,
    n.mobile.LOT_SELECTMAX = 5,
    n.mobile.cart = {
        group: 0,
        code: "",
        count: 0
    },
    n.mobile.selectCodeInit = function() {
        var r = t(".pick-box"),
        i = t(".del"),
        s = t(".plays"),
        o = t(".sel-pick"),
        u = t("#reflash"),
        a = t("#navtit"),
        f = t(".pick-info a"),
        l = t(".top-date"),
        c = t(".all"),
        h = t("#addcart"),
        p = t("#bets");
        r.on(n.lottery.tap, "span",
        function() {
            var r = t(this),
            i = n.mobile.LOT_METHOD;
            r.toggleClass("selected");
            if (i === 2) {
                var s = t("#rd"),
                o = t("#rt"),
                u = r.parent().parent().attr("id"),
                a = t.trim(r.text()) * 1 - 1;
                if (u == "rd") {
                    if (s.find(".selected").length > 6) {
                        r.removeClass("selected"),
                        e.alert("\u80c6\u7801\u6700\u591a\u53ef\u90096\u4e2a");
                        return
                    }
                    o.find("span").eq(a).removeClass("selected")
                } else u == "rt" && s.find("span").eq(a).removeClass("selected")
            }
            n.mobile.count()
        }),
        l.on(n.lottery.tap,
        function() {
            var e = t("#qlchistory").find("p"),
            n = t(".top-arr"),
            r = "top-arr-on";
            n.hasClass(r) ? (n.removeClass(r), e.hide().eq(0).show().parent().css("height", 22)) : (n.addClass(r), e.show().parent().css("height", 110))
        }),
        t("#navtit").on(n.lottery.tap,
        function() {
            var e = t(".head-arr"),
            r = t(".pop-box2"),
            i = "head-arron",
            s = t(".btn-menu"),
            o = "btn-menu-on",
            u = "none";
            s.hasClass(o) && s.trigger(n.lottery.tap),
            e.hasClass(i) ? (e.removeClass(i), r.addClass(u)) : (e.addClass(i), r.removeClass(u))
        }),
        i.on(n.lottery.tap,
        function() {
            var e = t(this),
            r = e.attr("dir"),
            i = t(".pick-" + r);
            i.find(".selected").removeClass("selected"),
            n.mobile.count()
        }),
        c.on(n.lottery.tap,
        function() {
            var e = t(this),
            r = t.trim(e.text()),
            i = t("#rt").find("span"),
            s = t("#rd").find(".selected");
            if (/(\u9009)/g.test(r)) {
                e.text("\u5168 \u6e05"),
                i.addClass("selected");
                var o = t.map(s,
                function(e) {
                    return t.trim(t(e).text()) * 1 - 1
                });
                for (var u = 0; u < o.length; u++) i.eq(o[u]).removeClass("selected")
            } else e.text("\u5168 \u9009"),
            i.removeClass("selected");
            n.mobile.count()
        }),
        s.on(n.lottery.tap,
        function() {
            var e = t(this),
            r = t(".head-arr"),
            i = t(".pop-box2"),
            s = t(".btn-pop"),
            o = e.attr("action") * 1,
            u = t(".pick"),
            a = t("#pdeal").find("p"),
            f = t("#game"),
            l = e.attr("txt");
            s.removeClass("btn-pop-on").eq(o).addClass("btn-pop-on"),
            r.removeClass("head-arron"),
            i.addClass("none"),
            u.addClass("none"),
            a.addClass("none"),
            n.mobile.LOT_METHOD = o,
            u.eq(o).removeClass("none"),
            a.eq(o).removeClass("none"),
            f.text(l),
            o === 1 ? n.mobile.getRandgroup(5) : (t("#pdeal").find("strong").text(0), t(".pick-box").find(".selected").removeClass("selected"))
        }),
        o.on("change",
        function() {
            var e = t(this).val() * 1;
            n.mobile.getRandgroup(e)
        }),
        u.on(n.lottery.tap,
        function() {
            n.mobile.getRandgroup(o.val() * 1)
        }),
        t(".btn-menu").on(n.lottery.tap,
        function() {
            var e = t("#tools"),
            r = t(this),
            i = "btn-menu-on",
            s = t(".pop-box2"),
            o = t("#navtit"),
            u = "none";
            s.hasClass(u) || o.trigger(n.lottery.tap),
            r.hasClass(i) ? (e.addClass(u), r.removeClass(i)) : (e.removeClass(u), r.addClass(i))
        }),
        h.on({
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
        p.on({
            click: function() {
                var r = n.mobile.cart.group || 0,
                i = t(".pick").eq(n.mobile.LOT_METHOD).find("ul"); ! r || i.find(".selected").length > 0 || n.mobile.LOT_METHOD === 1 ? n.mobile.pickBall(t(this)) : e.location.href = "/xfg_cp_client/lottery/qlcorder.action"
            },
            touchstart: function() {
                t(this).addClass("btntap")
            },
            touchend: function() {
                t(this).removeClass("btntap")
            }
        })
    },
    n.mobile.pickBall = function(r) {
        var i = n.mobile.cart.code || "",
        s = 0,
        o = t("#pdeal").find("strong"),
        u = n.mobile.LOT_METHOD,
        a = r.attr("id");
        if (u === 0) {
            var f = t("#r").find(".selected"),
            l = f.length;
            s = o.eq(1).text() * 1;
            if (l < 7) {
                e.alert("\u8bf7\u81f3\u5c11\u9009\u62e97\u4e2a\u53f7\u7801");
                return
            }
            d = t.map(f,
            function(e) {
                return t.trim(t(e).text())
            }),
            i += s + "|" + d.join(" ") + ";",
            n.mobile.cart.group += 1
        } else if (u === 1) {
            var c = t("#myrnd").find("li"),
            h,
            p = n.mobile.LOT_SELECTMAX,
            d = [];
            for (var v = 0; v < p; v++) h = c.eq(v).find("span"),
            d = t.map(h,
            function(e) {
                return t.trim(t(e).text())
            }),
            i += "1|" + d.join(" ") + ";",
            n.mobile.cart.group += 1;
            s = o.eq(3).text() * 1
        } else {
            var m = t("#rd").find(".selected"),
            g = t("#rt").find(".selected");
            s = o.eq(7).text() * 1;
            if (m.length < 1) {
                e.alert("\u80c6\u7801\u81f3\u5c11\u90091\u4e2a");
                return
            }
            if (g.length < 1) {
                e.alert("\u62d6\u7801\u81f3\u5c11\u90091\u4e2a");
                return
            }
            if (m.length + g.length < 7) {
                e.alert("\u80c6\u7801\u52a0\u62d6\u7801\u81f3\u5c117\u4e2a");
                return
            }
            var y = t.map(m,
            function(e) {
                return t.trim(t(e).text())
            }),
            b = t.map(g,
            function(e) {
                return t.trim(t(e).text())
            });
            i += s + "|" + (y.join(" ") + "$" + b.join(" ")) + ";",
            n.mobile.cart.group += 1
        }
        n.mobile.cart.code = i,
        n.mobile.cart.count += s;
        var w = n.mobile.cart.code.replace(/;$/g, ""),
        s = n.mobile.cart.count;
        n.localstorage.setItem(n.mobile.storageName, w),
        n.localstorage.setItem(n.mobile.storageMulName, s),
        a == "addcart" ? (t("#group").text(n.mobile.cart.group), n.mobile.clearAll()) : e.location.href = "/lottery/qlcorder.action"
    },
    n.mobile.clearAll = function() {
        var e = n.mobile.LOT_METHOD;
        e === 1 ? n.mobile.getRandgroup(n.mobile.LOT_SELECTMAX) : (t("#pdeal").find("strong").text(0), t(".pick").eq(n.mobile.LOT_METHOD).find(".selected").removeClass("selected"), e === 2 && t(".all").text("\u5168 \u9009"))
    },
    n.mobile.countDownInit = function() {
        var e = t(".time"),
        r = e.attr("issale") * 1,
        i = e.attr("endtime"),
        s = t("#countdowm");
        r ? n.mobile.getServerTimes(function(e) {
            if (e * 1 > 0) {
                var t = i * 1 - e * 1;
                t <= 0 && s.html("\u672c\u671f\u5df2\u622a\u6b62")
            }
        }) : s.html("\u6682\u505c\u9500\u552e")
    },
    n.mobile.AsynDownData = function() {
        var e = "/int/qlcindex/?",
        r = t(".time"),
        i = t("#navtit"),
        s = t("#actQH"),
        o = t("#countdowm"),
        u = t("#qlchistory");
        i.html('\u4e03\u4e50\u5f69-<cite id="game">\u81ea\u9009</cite><span class="head-arr"><em></em></span>');
        var a = n.localstorage.getItem(n.mobile.storageName),
        f = ("0" + n.localstorage.getItem(n.mobile.storageMulName)) * 1;
        a && (n.mobile.cart.code = a + ";", n.mobile.cart.group = a.split(/;/g).length, n.mobile.cart.count = f),
        t("#group").text(n.mobile.cart.group),
        t.getJSON(e + "rnd=" + Math.random(),
        function(e) {
            if (e) {
                var i = e.info,
                a = e.blist;
                r.attr({
                    issue: i.issue,
                    issale: i.IsOpen,
                    endtime: i.EndTime * 1 - i.DsTimeSpace * 1
                }),
                s.text(t.trim(i.issue)),
                n.mobile.countDownInit(),
                o.html("\u622a\u6b62\u65f6\u95f4\uff1a" + i.etstr);
                var f = [];
                t.each(a,
                function(e, t) {
                    f.push("<p>\u7b2c" + t.issue + '\u671f\u5f00\u5956\uff1a<strong class="red">' + t.number.split(/\+/g)[0] + '</strong> + <strong class="blue">' + t.number.split(/\+/g)[1] + "</strong></p>")
                }),
                u.html(f.join(""))
            }
        })
    },
    n.mobile.getRandgroup = function(e) {
        var r = [],
        i;
        n.mobile.LOT_SELECTMAX = e;
        for (var s = 0; s < e; s++) {
            i = n.number.random({
                min: 1,
                max: 30,
                size: 7,
                repeat: 0,
                sort: 1
            })[0],
            r.push("<li>"),
            r.push('<div class="kj-ball">');
            for (var o = 0; o < 7; o++) r.push('<span class="redball">' + i[o] + "</span> ");
            r.push("</div>"),
            r.push("</li>")
        }
        t("#myrnd").html(r.join("")),
        n.mobile.count()
    },
    n.mobile.count = function() {
        var e, r = t("#pdeal").find("strong"),
        i = n.mobile.LOT_METHOD;
        if (i === 0) {
            e = 1;
            var s = t("#r").find(".selected").length;
            e = n.number.combo(s, 7),
            r.eq(0).text(s),
            r.eq(1).text(e),
            r.eq(2).text(e * n.mobile.price)
        } else if (i === 1) e = n.mobile.LOT_SELECTMAX || 5,
        r.eq(3).text(e),
        r.eq(4).text(e * n.mobile.price);
        else {
            var o = t("#rd").find(".selected").length,
            u = t("#rt").find(".selected").length;
            e = n.number.combo(u, 7 - o),
            r.eq(5).text(o),
            r.eq(6).text(u),
            r.eq(7).text(e),
            r.eq(8).text(e * n.mobile.price)
        }
    },
    t(function() {
        n.mobile.storageName = "storage" + n.mobile.lotType,
        n.mobile.storageMulName = "storage" + n.mobile.lotType + "mul",
        n.mobile.AsynDownData(),
        n.mobile.selectCodeInit()
    })
})(window);