window.m_prefix="m.jd.com_";
window.m_cityId="cityId";
window.m_userCity="userCity";
window.m_categoryId="categoryId";
window.m_userCityDetail="userCityDetail";
window.m_longitudeLatitude="longitudeLatitude";
window.m_timeout = 1000*20;//超时时间暂定为20秒,考虑到gps环境
window.m_waitText = "&#21162;&#21147;&#21152;&#36733;&#20013;&#46;&#46;&#46;";//努力加载中...
//判断传入的字符串是否为传空
function isNotBlank(val) {
		if (val == undefined || val == null || val == "null" || val.trim() == "" || val.trim().length == 0) {
				return false;
			}
		return true;
};
//判断是否是数字
function isDigital(val) {
	var reg =/^\d$/;
	return reg.test(val);
};
//判断输入位数限制
function textLimit(val,min,max) {
	if(!val) return false;
	var len = val.trim().length;
	if (len < min || len > max ) return false;
	return true;
};
//判断邮箱格式是否正确
function testEmail(val) {
	var reg = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z][a-z.]{2,8}$/;
	return reg.test(val)
};
//查看浏览器是否支持经纬度
function testGeolocation() {
	if (!!navigator.geolocation){
		testGeolocation = function(){
			return true;
		}
		return testGeolocation();
	}
	testGeolocation = function(){
		return false;
	}
	return testGeolocation();
};
//查看浏览器是否支持session以及local存储
function testStorage(){
	return testSessionStorage() || testLocalStorage();
}
//查看浏览器是否支持本地Session存储
function testSessionStorage() {
	if (!!window.sessionStorage){
		testSessionStorage = function () {
			return true;
		}
	} else {
		testSessionStorage = function() {
			return false;
		}
	}
	return testSessionStorage();
};
//查看浏览器是否支持本地Local存储
function testLocalStorage() {
	if (!!window.localStorage){
		testLocalStorage = function () {
			return true;
		}
	} else {
		testLocalStorage = function() {
			return false;
		}
	}
	return testLocalStorage();
};
//转换为json对象    qianxiaozhi
function json(v){
     return eval("("+v+")");
};
//判断是否是手机号码
function testPhone(val){
	var reg = /^1[3|5|8][0-9]\d{8}$/;
	return reg.test(val);
};
//创建spinner对象
function createSpinner(){
	var optss = {
			lines:12,
			length:6,
			width:4,
			radius:10,
			color:'#333',
			speed:1,
			trail:60,
			shadow:false,
			hwaccel:false
		}
	var   temp = new Spinner(optss);
	createSpinner = function(){return temp;}
	return  createSpinner();
}

//本地购物车开始
function addCart(id){
	var array=JSON.parse(localStorage.getItem("cartItem"));
	if(array!=null){
		for(var i=0;i<=array.length;i++){
			if(array[i]!=null&&array[i].id==id){ 
				array[i].num+=1;
				break;
			}else if(i==array.length){
				var obj=new Object();
				obj.id=id;
				obj.num=1;
				array[array.length]=obj;
				break;
			}
		}
	}else{
		array=new Array();
		var obj=new Object();
		obj.id=id;
		obj.num=1;
		array[0]=obj;
	}
	localStorage.setItem("cartItem",JSON.stringify(array));
};

function getCart(){
	return localStorage.getItem("cartItem");
};

function clearCart(){
	localStorage.removeItem("cartItem");
};

function delCart(id,num,sid){
	var array=JSON.parse(localStorage.getItem("cartItem"));
	for(var i=0;i<array.length;i++){
		if(array[i].id==id){
			array.splice(i,1);
			break;
		}
	}
	localStorage.setItem("cartItem",JSON.stringify(array));
	location.href='/cart/remove.action?sid='+sid+'&wareId='+id+'&num='+num;
};

function updateCart(id,num){
	var array=JSON.parse(localStorage.getItem("cartItem"));
	for(var i=0;i<array.length;i++){
		if(array[i].id==id){
			array[i].num=num;
			break;
		}
	}
	localStorage.setItem("cartItem",JSON.stringify(array));
};

function updateToolBar(){
	var array=JSON.parse(localStorage.getItem("cartItem"));
	var wareCount=0;
	if(array!=null){
		for(var i=0;i<array.length;i++){
			wareCount+=Number(array[i].num);
		}
	}
	$("#wareCount").text(wareCount);
};
function syncCart(sid,jump){
	var paraJson=getCart();
	if(paraJson!=null)
	jQuery.get('/cart/update.action?sid='+sid,
    	{updatejson:paraJson},
    		function(data){ 
    		data=eval('('+data+')');
    		if(jump){ //如果需要跳转  
    			location.href='/cart/cart.action?sid='+data.sid;
    		}else{
    			var oriPrice=data.price - data.discount; 
    			var realPrice=oriPrice - data.rePrice;
    			var rePrice=data.rePrice-0.0;
    			$("#cart_oriPrice").text(oriPrice.toFixed(2));
    			$("#cart_rePrice").text(rePrice.toFixed(2));
    			$("#cart_realPrice").text(realPrice.toFixed(2));
    		}
   		});
};
//本地购物车结束

/**
 * 转换请求地址  同TuanUtil
 * @param baseUrl         跳转地址
 * @param sid             sid
 * @param arguments       参数列表 如： ['1','product']
 */
function urlEncode(baseUrl,sid,arguments){
      var url ='';
      var urls = baseUrl.split('.');
      url = url+urls[0];
      if(arguments && arguments.length){
           arguments.forEach(function(v){
               url =url+'-';
               if(v && v!=-1 && v!='-1')
                  url =url+v;
               else
                 url =url+'0';
           });
       }
       url = url+'.'+urls[1];
       if (!!sid) {
    	   if(/\?/.test(url))
    		   url =url+'&sid='+sid;
    	   else
    		   url =url+'?sid='+sid;
       }
      return url;
    }
/**
 * 提交按钮等待条隐藏 
 * @param $submit
 * @param wait
 */
function hideWait($submit,wait){
	wait.stop();
	$submit.hide();
	$submit.next().show();
	$("header").show();
	$("footer").show();
}
/**
 * 提交按钮等待条显示 
 * @param $submit
 * @param wait
 */
function showWait($submit,wait){
	$submit.show();
	$submit.next().hide();
	$("header").hide();
	$("footer").hide();
	wait.spin($submit[0]);
}
/**
 * container 外围容器 slider 滑动容器 count 图片个数
 */
function createPicMove(container, slider, count){
	var $get = function (id) {
		return "string" == typeof id ? document.getElementById(id) : id;
	};

	var Extend = function(destination, source) {
		for (var property in source) {
			destination[property] = source[property];
		}
		return destination;
	}

	var CurrentStyle = function(element){
		return element.currentStyle || document.defaultView.getComputedStyle(element, null);
	}

	var Bind = function(object, fun) {
		var args = Array.prototype.slice.call(arguments).slice(2);
		return function() {
			return fun.apply(object, args.concat(Array.prototype.slice.call(arguments)));
		}
	}
	var Tween = {
		Quart: {
			easeOut: function(t,b,c,d){
				return -c * ((t=t/d-1)*t*t*t - 1) + b;
			}
		},
		Back: {
			easeOut: function(t,b,c,d,s){
				if (s == undefined) s = 1.70158;
				return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
			}
		},
		Bounce: {
			easeOut: function(t,b,c,d){
				if ((t/=d) < (1/2.75)) {
					return c*(7.5625*t*t) + b;
				} else if (t < (2/2.75)) {
					return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
				} else if (t < (2.5/2.75)) {
					return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
				} else {
					return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
				}
			}
		}
	}
	// 容器对象,滑动对象,切换数量
	var SlideTrans = function(container, slider, count, options) {
		this._slider = $get(slider);
		this._container = $get(container);// 容器对象
		this._timer = null;// 定时器
		this._count = Math.abs(count);// 切换数量
		this._target = 0;// 目标值
		this._t = this._b = this._c = 0;// tween参数
		
		this.Index = 0;// 当前索引
		
		this.SetOptions(options);
		
		this.Auto = !!this.options.Auto;
		this.Duration = Math.abs(this.options.Duration);
		this.Time = Math.abs(this.options.Time);
		this.Pause = Math.abs(this.options.Pause);
		this.Tween = this.options.Tween;
		this.onStart = this.options.onStart;
		this.onFinish = this.options.onFinish;
		
		var bVertical = !!this.options.Vertical;
		this._css = bVertical ? "top" : "left";// 方向
		
		// 样式设置
		var p = CurrentStyle(this._container).position;
		p == "relative" || p == "absolute" || (this._container.style.position = "relative");
		this._container.style.overflow = "hidden";
		this._slider.style.position = "absolute";
		
		this.Change = this.options.Change ? this.options.Change :
			this._slider[bVertical ? "offsetHeight" : "offsetWidth"] / this._count;
	};
	SlideTrans.prototype = {
	  // 设置默认属性
	  SetOptions: function(options) {
		this.options = {// 默认值
			Vertical:	true,// 是否垂直方向（方向不能改）
			Auto:		true,// 是否自动
			Change:		0,// 改变量
			Duration:	50,// 滑动持续时间
			Time:		10,// 滑动延时
			Pause:		4000,// 停顿时间(Auto为true时有效)
			onStart:	function(){},// 开始转换时执行
			onFinish:	function(){},// 完成转换时执行
			Tween:		Tween.Quart.easeOut// tween算子
		};
		Extend(this.options, options || {});
	  },
	  // 开始切换
	  Run: function(index) {
		// 修正index
		index == undefined && (index = this.Index);
		index < 0 && (index = this._count - 1) || index >= this._count && (index = 0);
		// 设置参数
		this._target = -Math.abs(this.Change) * (this.Index = index);
		this._t = 0;
		this._b = parseInt(CurrentStyle(this._slider)[this.options.Vertical ? "top" : "left"]);
		this._c = this._target - this._b;
		
		this.onStart();
		this.Move();
	  },
	  // 移动
	  Move: function() {
		clearTimeout(this._timer);
		// 未到达目标继续移动否则进行下一次滑动
		if (this._c && this._t < this.Duration) {
			this.MoveTo(Math.round(this.Tween(this._t++, this._b, this._c, this.Duration)));
			this._timer = setTimeout(Bind(this, this.Move), this.Time);
		}else{
			this.MoveTo(this._target);
			this.Auto && (this._timer = setTimeout(Bind(this, this.Next), this.Pause));
		}
	  },
	  // 移动到
	  MoveTo: function(i) {
		this._slider.style[this._css] = i + "px";
	  },
	  // 下一个
	  Next: function() {
		this.Run(++this.Index);
	  },
	  // 上一个
	  Previous: function() {
		this.Run(--this.Index);
	  },

	  // 停止
	  Stop: function() {
		clearTimeout(this._timer); this.MoveTo(this._target);
	  }
	};
	return new SlideTrans(container, slider, count, { Vertical: false });	// 图片数量更改后需更改此数值
}
function bind(obj,fun){
	var args = Array.prototype.slice.call(arguments).slice(2);
	return function() {
		return fun.apply(obj, args.concat(Array.prototype.slice.call(arguments)));
	}
}
/**
 * 首页位置服务 
 */
function userLocation(id,clickId){
	function getUserLocation(flag,event) {
		// 提示信息 获取位置信息失败<a href=''>更新</a>
		var errorMsg = "&#33719;&#21462;&#22833;&#36133;"
				+ "<a onclick='getUserLocation();' style='color:blue;font-size:0.8125em;padding-left:15px;' "
				+ "href=''>&#26356;&#26032;</a>";
		$("#"+id).html(m_waitText);//努力加载中...
		
		var m_timeout = window.setTimeout(function() {
				$("#"+id).html(errorMsg);
			}, window.m_timeout);
		if (testGeolocation()) {
			navigator.geolocation
					.getCurrentPosition(
							function(position) {
								var latitude = position.coords.latitude;
								var longitude = position.coords.longitude;
								jQuery.get(
												"/tuan/location.json",
												{
													latitude : latitude,
													longitude : longitude
												},bind(this,
												function(flag,data) {
													if (data.status) {
														window.clearTimeout(m_timeout);
														if (data.cityId) {
															var txt = data.location.city
																	+ data.location.subCity;
															var fullname = data.location.fullNames;
															if(testStorage()) {//判断是否支持本地存储
																setCityId(data.cityId)
																setLongitudeLatitude(latitude+ ','+ longitude);
																setUserCity(txt);
																setUserCityDetail(fullname);
															}
															
															fullname = fullname + "<span  style='color:blue;font-size:0.875em;padding-left:15px;' href=''>&#26356;&#26032;</span>"
															txt = txt+ "<span  style='color:blue;font-size:0.875em;padding-left:15px;' href=''>&#26356;&#26032;</span>";
															if(!!flag){
																$("#"+id).html(txt);
															} else {
																$("#"+id).html(fullname);
															}
														} else {
															$("#"+id).html(errorMsg);
														}
													}
												},flag), "json");
							}, function() {
								$("#"+id).html(errorMsg);
								window.clearTimeout(m_timeout);
							});
		}
		event.preventDefault();
	}
	if(!!clickId){
		$("#"+clickId).click(bind(this,getUserLocation,clickId));
	} else {
		$("#"+id).click(bind(this,getUserLocation,clickId));
	}
}
/**
*注册离开页面时回调函数
*/
function registerUnloadEvent($submit,spinner){
	document.body.onunload=function(){
		hideWait($submit,spinner);
	}
}

//搜索框自动完成提示功能
function writeTipVal(wname){
    if(wname != ""){
       $("#keyword").val(wname)
       $("#shelper").html("");
       $("#searchForm").submit();
    }
}
function closeTip(){
    $("#shelper").html("");
}
var old_keyword = "";
function searchTipContent(){
    var keyword = $("#keyword").val().trim();
    if(keyword == ""){
        old_keyword = "";
        $("#shelper").html("");
    }else{
        if(keyword == old_keyword){
        }else{
            keyword = $("#keyword").val().trim();
            old_keyword = keyword;
            jQuery.get("/ware/searchTip.action?v=6", {keyword:keyword}, function(data){
                if(data!=null){
                    $("#shelper").html(data);
                }else{
                    $("#shelper").html("");
                }
            });
        }
    }
    setTimeout("searchTipContent()",500);
}
$(function() {
    $(".search input").attr("autocomplete","off"); // 消除表单中的输入框的缓存
    if(jQuery('#keyword').length > 0){ //判断对象是否存在
        old_keyword = $("#keyword").val().trim();
        searchTipContent();
    }
});
/**
 * 从本地缓存中获取当前用户所在城市ID
 *  
 *  2012-03-20
 * @returns
 */
function getCityId(){
	var result;
	if(testLocalStorage()) {
		result =  window.localStorage.getItem(m_prefix+m_cityId);
	} else if (testSessionStorage()) {
		result =  window.sessionStorage.getItem(m_prefix+m_cityId);
	}
	return result || 0;
}
/**
 * 从本地缓存中获取当前用户所在城市名称
 *  
 *  2012-03-20
 * @returns
 */
function getUserCity(){
	var result;
	if(testLocalStorage()) {
		result =  window.localStorage.getItem(m_prefix+m_userCity);
	} else if (testSessionStorage()) {
		result =  window.sessionStorage.getItem(m_prefix+m_userCity);
	}
	return result || null;
}
/**
 * 获取本地缓存中当前用户所在城市信息
 *  
 *  2012-03-20
 * @returns
 */
function getUserCityDetail(){
	var result ;
	if(testLocalStorage()) {
		result = window.localStorage.getItem(m_prefix+m_userCityDetail);
	} else if (testSessionStorage()) {
		result = window.sessionStorage.getItem(m_prefix+m_userCityDetail);
	}
	return result || null;
}
/**
 * 从本地缓存中获取当前用户所选类别
 *  
 *  2012-03-20
 * @returns
 */
function getCategoryId(){
	var result;
	if(testLocalStorage()) {
		result =  window.localStorage.getItem(m_prefix+m_categoryId);
	} else if (testSessionStorage){
		result =  window.sessionStorage.getItem(m_prefix+m_categoryId);
	}
	return result || 0;
}
/**
 * 获取本地缓存中经纬度
 *  
 *  2012-03-20
 * @returns
 */
function getLongitudeLatitude(){
	var result;
	if(testSessionStorage()) {
		result = window.sessionStorage.getItem(m_prefix+m_longitudeLatitude);
	} 
	return result || null;
}
/**
 * 设置本地缓存中当前用户所在城市ID
 *  
 *  2012-03-20
 * @returns
 */
function setCityId(val){
	if (!!val){ 
		if(testLocalStorage()) {
			window.localStorage.setItem(m_prefix+m_cityId,val);
		} else if (testSessionStorage()) {
			window.sessionStorage.setItem(m_prefix+m_cityId,val);
		}
	}
}
/**
 * 设置本地缓存中当前用户所在城市名称
 *  
 *  2012-03-20
 * @returns
 */
function setUserCity(val){
	if (!!val){
		if(testLocalStorage()) {
			window.localStorage.setItem(m_prefix+m_userCity,val);
		} else if (testSessionStorage()) {
			window.sessionStorage.setItem(m_prefix+m_userCity,val);
		}
	}
}

/**
 * 设置本地缓存中当前用户所在城市信息
 *  
 *  2012-03-20
 * @returns
 */
function setUserCityDetail(val){
	if (!!val){
		if(testLocalStorage()) {
			window.localStorage.setItem(m_prefix+m_userCityDetail,val);
		} else if (testSessionStorage()) {
			window.sessionStorage.setItem(m_prefix+m_userCityDetail,val);
		}
	}
}
/**
 * 设置本地缓存中当前用户所选类别
 *  
 *  2012-03-20
 * @returns
 */
function setCategoryId(val){
	if (!!val){ 
		if(testLocalStorage()) {
			window.localStorage.setItem(m_prefix+m_categoryId,val);
		} else if (testSessionStorage()) {
			window.sessionStorage.setItem(m_prefix+m_categoryId,val);
		}
	}
}
/**
 * 设置本地缓存中经纬度
 *  
 *  2012-03-20
 * @returns
 */
function setLongitudeLatitude(val){
	if (!!val){ 
		if(testSessionStorage()) {
			window.sessionStorage.setItem(m_prefix+m_longitudeLatitude,val);
		} 
	}
}
/**
 * 跳转到团购列表页面
 * 
 * 2012-03-20
 * @param id
 * @param sid
 */
function goTuanIndex(sid){
	var cid,cgid;
	try {
		cid = getCityId();
		cgid = getCategoryId();
	} catch (e){
	}
	!!cid || (cid = 0);
	!!cgid || (cgid = 0);
	var param = [
	             cid,cgid,0,0,1
	             ];
	var url = urlEncode('/tuan/index.html',sid,param);
	return url || '#';
}
/**
 * 价格转换
 * 郭鹏
 * 2012-03-21
 * 形如：192  ----》  192.0
 */
function priceTransform(price){
	try {
		return parseFloat(price).toFixed(1);
	} catch(e) {
		return price;
	}
	return price;
}
/**
 * 计算折扣金额
 * @param real
 */
function getDiscount(market,real){
	var save = (market - real).toString();
	var index = save.indexOf(".");
	if(index > 0) {//如果得到金额位数过长，则截取小数点后两位
  	  save = save.substring(0,save.indexOf(".")+2);
    }
	return save;
}


//解决android 滑动丢失页面动态加载的组件
document.ontouchend=function(){
        $('#hideInput').remove();
        $('#con_more').append('<label style="height:0px;font-size:0pt;" id="hideInput">&nbsp;</label>');
}

