var Calendar = function(supportPre,supportSelected) {
	var a = this;
	var b = null;
	this.onMonth = 5;
	this.storeValues = {
		showValSuffix : "ShowVal",
		calendarContainerSuffix : "Calendar",
		inputHidenSuffix : "InputHiddenCalendar",
		dateSuffix : "Date",
		selectDaySuffix : "SelectDay",
		relateCalendar : "",
		secondCalendarTag : "",
		calendarType : "",
        canPre :supportPre,            //支持过期时间选择
        canSelect:supportSelected        //支持 年月选择
	};
	this.create = function(day, year, month) {
		var cnweek = [ "&#19968;", "&#20108;", "&#19977;", "&#22235;", "&#20116;", "&#20845;", "&#26085;" ];//[ "1", "2", "3", "4", "5", "6", "7" ]
		var t = 1;
		var today = new Date();
		var C = today.getFullYear();
		var c = today.getMonth() + 1;
		var j = today.getDate();
		var g = "";
		var D = this.addMonths(new Date(), this.onMonth);
		if (this.storeValues.relateCalendar == ""	&& this.storeValues.calendarType == "hotel") {
			D = this.addDays(D, -1);
		}
		if (this.storeValues.relateCalendar != "") {
			g = $("#" + this.storeValues.relateCalendar+ this.storeValues.showValSuffix).html();
			if (g != "" && this.storeValues.calendarType != "") {
				var v = this.addDays(this.parseTime(g, "yyyy-MM-dd"), 1);
				if (v.getTime() > D.getTime()) {
					v = D;
				}
				g = this.formatDate(v.getFullYear(), v.getMonth() + 1, v.getDate());
			}
		}
		if (year != undefined && month != undefined) {
			today.setFullYear(year, month - 1, 1)
		} else {
			year = C;
			month = c;
		}
		var I = day.substring(0, day.length - this.storeValues.calendarContainerSuffix.length);
		var n = this.getEndDateOfMonth(year, month);
		var z = this.offset(year, month, 1);
		var y = this.offset(year, month, n);
		var p = this.weeks(year, month);
		var m = $("#" + day).offset();
		var F = $("<table></table>");
		var E = $('<span class="cal_prev">&lt;&lt;</span>').click(
				function() {
					a.prevMonth(day, year, month)
				});
		var q = $('<span class="cal_next">&gt;&gt;</span>').click(
				function() {
					a.nextMonth(day, year, month)
				});
		var l = $("<thead></thead>");
		var w = $("<tr></tr>");
		var G = $('<td colspan="7"></td>');
        //支持年月选择
        if(this.storeValues.canSelect){
                var yearBegin = (new Date().getFullYear()) - 100;
                var yearEnd =  (new Date().getFullYear()) + 100;
                var $S_Y =  $('<select class="cal_year" style="width:80px;height:auto;"></select>').change(
                            function() {
                                a.yearChanged(day, year, month,$(this).val())
                            });

                for(var i=yearBegin;i<yearEnd;i++){
                    if(year == i){
                       $S_Y.append('<option value="'+i+'" selected="selected">'+i+'</option>');
                    }else
                        $S_Y.append('<option value="'+i+'">'+i+'</option>');
                }

                var $S_M = $('<select class="cal_month" style="width:50px;height:auto;">&lt;&lt;</select>').change(
                            function() {
                                a.monthChanged(day, year, month,$(this).val())
                            });
              for(var i=1;i<=12;i++){
                    if(month == i){
                       $S_M.append('<option value="'+i+'" selected="selected">'+i+'</option>');
                    }else
                        $S_M.append('<option value="'+i+'">'+i+'</option>');
              }

                G.append($('<h3></h3>')
                .append($S_Y)
                .append("&#24180;")
                .append($S_M)
                .append("&#26376;</span>"));//骞存湀
        }else{
          G.append($('<h3></h3>').append(E).append("<span class='mon' style='color:black'>"
                + year +
                "&#24180;"
                + month +
                "&#26376;</span>").append(q));//骞存湀
        }

		l.append(w.append(G));
		var s = $("<tbody></tbody>");
		var H = $("<tr></tr>");
		for ( var r in cnweek) {
			H.append("<th>" + cnweek[r] + "</th>")
		}
		s.append(H);
		for ( var B = 0; B < p; B++) {
			var d = $("<tr></tr>");
			if (B == 0) {
				if (z == 0) {
					for ( var A = 0; A < 6; A++) {
						d.append("<td>&nbsp;</td>")
					}
					d.append(this.append(year, month, t, C, c, j, I, g, D));
					t++
				} else {
					for ( var A = 0; A < z - 1; A++) {
						d.append("<td>&nbsp;</td>")
					}
					for ( var A = z - 1; A < 7; A++) {
						d.append(this.append(year, month, t, C, c, j, I, g, D));
						t++;
					}
				}
			} else {
				if (B == p - 1) {
					if (y == 0) {
						for ( var A = n - 6; A <= n; A++) {
							d.append(this.append(year, month, A, C, c, j, I, g, D))
						}
					} else {
						for ( var A = 0; A < y; A++) {
							d.append(this.append(year, month, t, C, c, j, I, g, D));
							t++
						}
						for ( var A = y; A < 7; A++) {
							d.append("<td>&nbsp;</td>")
						}
					}
				} else {
					var h = t + 7;
					while (t < h) {
						d.append(this.append(year, month, t, C, c, j, I, g, D));
						t++
					}
				}
			}
			s.append(d)
		}
		F.append(l).append(s);
		return F
	};
	this.addMonths = function(date, offset) {
		date.setMonth(date.getMonth() + offset);
		return date;
	};
	this.addDays = function(date, offset) {
		date.setDate(date.getDate() + offset);
		return date;
	};
	this.weeks = function(e, d) {
		var g = this.getEndDateOfMonth(e, d);
		var c = this.offset(e, d, 1);
		if (c == 0) {
			var f = g - 1;
			return parseInt(f / 7) + 1 + (f % 7 == 0 ? 0 : 1)
		} else {
			var f = g - (8 - c);
			return parseInt(f / 7) + 1 + (f % 7 == 0 ? 0 : 1)
		}
	};
	this.append = function(e, l, i, p, c, h, t, f, q) {
		var j = "";
		var o = false;
		var m = this.storeValues[t + this.storeValues.selectDaySuffix];
		if (m != undefined) {
			var n = m.split("-");
			if (n[0] == e && n[1] == l && n[2] == i) {
				o = true
			}
		}
		var g = false;
		if (f != "") {
			var s = f.split("-");
			var k = s[0];
			var d = parseInt(s[1], 10);
			var r = parseInt(s[2], 10);
			if (this.timeCompare(k, d, r, e, l, i) > 0) {
				g = true
			}
		}
		if (this.timeCompare(q.getFullYear(), q.getMonth() + 1, q.getDate(), e,l, i) < 0) {
			g = true;
		}
		if (a.dateChange) {
			g = false;
		}
		if (this.timeCompare(e, l, i, p, c, h) > 0) {
			j = $("<td></td>").bind("mousedown", function() {
				a.selectDay(this, e, l, i, t)
			});
			if (o) {
				if (g) {
					j.attr("class", "disable on")
				} else {
					j.attr("class", "on")
				}
			} else {
				if (g) {
					j.attr("class", "disable")
				}
				else{
					j.attr("class", "txt3")
				}
			}
		} else {
			if (this.timeCompare(e, l, i, p, c, h) == 0) {
				j = $("<td></td>").bind("mousedown", function() {
					a.selectDay(this, e, l, i, t)
				});
				if (o) {
					if (g) {
						j.attr("class", "disable on")
					} else {
						j.attr("class", "on")
					}
				} else {
					if (g) {
						j.attr("class", "disable")
					}
					else{
					j.attr("class", "txt3")
					}
				}
			} else {
				j = $("<td></td>");
				if (a.dateChange) {
					j.bind("mousedown", function() {
						a.selectDay(this, e, l, i, t)
					});
					if (o) {
						j.attr("class", "on")
					}
					else{
						j.attr("class", "txt3")
					}
				} else {
					if (o) {
						j.attr("class", "disable on")
					} else {
						j.attr("class", "disable")
					}
				}
			}
		}
		j.attr("data-day", e + "-" + l + "-" + i);
		j.append(i);
		return j
	};
	this.selectDay = function(l, f, i, k, m) {
		if ($(l).attr("class") != "disable"
				&& $(l).attr("class") != "disable on") {
			var g = m + this.storeValues.selectDaySuffix;
			var j = this.storeValues[g];
			if (j != undefined || j != null) {
				var c = j.split("-");
				var d = c[0] + "-" + c[1] + "-" + c[2];
				$("td[data-day='" + d + "']").removeClass("on");
				this.storeValues[g] = null
			}
			var e = f + "-" + i + "-" + k;
			$(l).addClass("on");
			var h = this.formatDate(f, i, k);
			$("#" + m + this.storeValues.showValSuffix).html(h);
			$("#" + m + this.storeValues.inputHidenSuffix).val(h);
			this.storeValues[g] = h;
			this.hide(m, (m + this.storeValues.calendarContainerSuffix));
			if (this.storeValues.secondCalendarTag != "") {
				this
						.setSecondCalendarVal(
								this.storeValues.secondCalendarTag, e)
			}
		}
		if ($(l).attr("class") != "disable" && a.dateChange) {
			a.dateChange()
		}
	};
	this.hide = function(d, c) {
		$("#" + d + this.storeValues.dateSuffix).removeClass("col").addClass("exp");
		$("#" + c).hide();
		/*$("#" + c).slideUp(500, function() {
			$("#" + c).empty();
		});*/
	};

	this.callContainer = function(day, year, month, position) {
		var c = this.create(day, year, month);
		if (position != undefined) {
			$("#" + day).empty().append(c);
		} else {
			$("#" + day).empty().hide();
			setTimeout(function() {
				$("#" + day).append(c);
				setTimeout(function() {
					$("#" + day).show();
					//$("#" + day).slideDown(200);
				}, 200, day);
			}, 100, day);
		}
	};
	this.timeCompare = function(year1, g, f, d, e, c) {
        if(this.storeValues.canPre){
           return 1;
        }
		if (year1 > d) {
			return 1;
		} else {
			if (year1 == d) {
				if (g > e) {
					return 1;
				} else {
					if (g == e) {
						if (f > c) {
							return 1;
						} else {
							if (f == c) {
								return 0;
							} else {
								return -1;
							}
						}
					} else {
						return -1;
					}
				}
			} else {
				return -1;
			}
		}
	};
	this.setSecondCalendarVal = function(m, k) {
		var h = k.split("-");
		var c = $("#" + m + this.storeValues.showValSuffix);
		var f = c.html();
		var g = -1;
		if (f == "" || f==null) {
			g = 1;
		} else {
			var e = f.split("-");
			g = this.timeCompare(parseInt(h[0]), parseInt(h[1]),parseInt(h[2]), parseInt(e[0]), parseInt(e[1]),parseInt(e[2]));
		}
		if (g >= 0) {
			var l = this.addDays(this.parseTime(k, "yyyy-MM-dd"), 1);//绗簩涓棩鏈熸瘮绗竴涓棩鏈熼粯璁ゅ1澶�
			var j = this.addMonths(new Date(), this.onMonth);
			if (l.getTime() > j.getTime()) {
				l = j;
			}
			var i = this.formatDate(l.getFullYear(), l.getMonth() + 1, l.getDate());
			c.html(i);
			$("#" + m + this.storeValues.inputHidenSuffix).val(i);
		}
	};
	
	this.init = function(h, f, d, g,date) {
		this.onMonth = d;
		this.storeValues.calendarType = g;
        var myDate = new Date();
        if(date){
           myDate = date;
        }
        var month = String(myDate.getMonth()+1);
        var date =  String(myDate.getDate());
        month = month.length==1 ? "0"+month : month;
         date = date.length==1 ? "0"+date : date;
        $("#" + h + a.storeValues.showValSuffix).html(myDate.getFullYear()+"-"+month+"-"+date);
        $("#" + h + a.storeValues.inputHidenSuffix).val(myDate.getFullYear()+"-"+month+"-"+date);
		var c = f.indexOf("_First");
		if (c != -1) {
			this.storeValues.secondCalendarTag = f.substring(0, c)
		} else {
			this.storeValues.relateCalendar = f;
		}
		$("#" + h + a.storeValues.dateSuffix).click(
						function() {
							var n = $(this);
							var s = n.attr("data-tag")+ a.storeValues.calendarContainerSuffix;
							if (n.hasClass("col")) {
								a.hide(h, s);
							} else {
								a.hide(h,a.storeValues.calendarContainerSuffix);
								n.removeClass("exp").addClass("col");
								var o = $.trim($("#" + n.attr("data-tag")+ a.storeValues.showValSuffix).html());
								if (o != "") {
									var k = o.split("-");
									var m = a.formatDate(k[0], k[1], k[2]);
									a.storeValues[n.attr("data-tag")+ a.storeValues.selectDaySuffix] = m;
									a.callContainer(s, k[0], k[1]);
									$("#"+ n.attr("data-tag")+ a.storeValues.inputHidenSuffix).val(m);
								} else {
									a.callContainer(s);
								}
							}
							if (a.storeValues.secondCalendarTag != "") {
								var i = $("#" + a.storeValues.secondCalendarTag+ a.storeValues.dateSuffix);
								if (i.hasClass("col")) {
									i.removeClass("col").addClass("exp");
									a.hide(a.storeValues.secondCalendarTag,a.storeValues.secondCalendarTag+ a.storeValues.calendarContainerSuffix)
								}
							} else {
								if (a.storeValues.relateCalendar != "") {
									var r = $("#"+ a.storeValues.relateCalendar+ a.storeValues.dateSuffix);
									if (r.hasClass("col")) {
										r.removeClass("col").addClass("exp");
										a.hide(a.storeValues.relateCalendar,a.storeValues.relateCalendar+ a.storeValues.calendarContainerSuffix);
									}
								}
							}
						});
	};
	
	this.offset = function(year, month, day) {
		return new Date(year, month - 1, day).getDay();
	};
	this.parseTime = function(str_date, format) {
		var date = new Date();
		if (format == "yyyy-MM-dd") {
			var date_item = str_date.split("-");
			date.setFullYear(parseInt(date_item[0]));
			date.setMonth(parseInt(date_item[1], 10) - 1);
			date.setDate(parseInt(date_item[2], 10));
		}
		return date;
	};
	this.getEndDateOfMonth = function(year, month) {
		if (year == undefined && month == undefined) {
			var c = new Date();
			year = c.getFullYear();
			month = c.getMonth() + 1;
		}
		var h = year;
		var d = month++;
		if (month > 12) {
			d -= 12;
			h++;
		}
		var firstDay = new Date(h, d, 1);
		return (new Date(firstDay.getTime() - 1000 * 60 * 60 * 24)).getDate();
	};
	this.nextMonth = function(day, year, month) {
		if (month == 12) {
			year++;
			month = 1;
		} else {
			month++;
		}
		this.callContainer(day, year, month, "right");
	};
	this.prevMonth = function(day, year, month) {
		if (month == 1) {
			year--;
			month = 12;
		} else {
			month--;
		}
		this.callContainer(day, year, month, "left")
	};
	this.formatDate = function(year, month, day) {
		month = parseInt(month, 10);
		day = parseInt(day, 10);
		return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
	};

    this.yearChanged = function(day,year,month,targetYear){
        if(targetYear>year)
              this.callContainer(day, targetYear, month, "right");
        else
           this.callContainer(day, targetYear, month, "left") ;
    }

    this.monthChanged = function(day,year,month,targetMonth){
         if(targetMonth>month)
              this.callContainer(day, year, targetMonth, "right");
        else
           this.callContainer(day, year, targetMonth, "left") ;
    }
};
