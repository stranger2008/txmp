;(function(window, undefined){
	var document = window.document, navigator = window.navigator, location = window.location;
	
	var jdMobile = (function() {
		
		var class2type = {};
		
		var DOMContentLoaded;
		
		var jdm = function(selector){
			return new jdm.fn.init(selector);
		};
		
		jdm.fn = jdm.prototype = {
				constructor : jdm,
				init : function(selector){
					if(!selector) {
						return this;
					}
					if (typeof selector === 'string') {
						this.context = this[0] = jdm.find(selector);
						this.length = 1;
						return this;
					}
					if(selector.nodeType) {
						this.context = this[0] = selector;
						this.length = 1;
						return this;
					}
					if (jdm.type(selector) === 'htmlcollection') {
						this.context = this[0] = selector;
						for (var i = 0; i< selector.length; i++) {
							this[i] = selector[i];
						}
						this.length = selector.length;
						return this;
					}
					if (typeof selector === 'function') {
						jdm.bindReady(selector);
					}
				},
				createEle : function(type){
					return document.createElement(type);
				},
				css : function(name,value) {
					var target = this[0];
					if (value === undefined) {
						return document.defaultView ? (document.defaultView.getComputedStyle(target,null).getPropertyValue(name)) : (document.currentStyle.getPropertyValue(name));
					}
					target.style.setProperty(name,value,'important');
					return this;
				},
				delCss : function(name) {
					var target = this[0];
					if(jdm.isNull(name)) return;
					target.style.removeProperty(name);
					return this;
				},
				show : function () {
					return this.css('display','block');
				},
				hide : function(){
					return this.css('display','none');
				},
				attr : function(name, value) {
					var target = this[0];
					if(jdm.isNull(name)) return;
					if(value == undefined) {
						return target.getAttribute(name);
					}
					target.setAttribute(name,value);
					return this;
				},
				delAttr : function(name) {
					var target = this[0];
					if (jdm.isNull(name)) return;
					target.removeAttribute(name);
					return this;
				},
				val : function(value) {
					var target = this[0];
					if(value == undefined) {
						return target.value;
					}
					target.value = value;
					return this;
				},
				html : function(value) {
					var target = this[0];
					if(value == undefined) {
						return target.innerHTML;
					}
					target.innerHTML = value;
					return this;
				},
				addHtml : function(value){
					var target = this[0];
					target.innerHTML += value;
					return this;
				},
				text : function(value) {
					var target = this[0];
					
					if(value == undefined) {
						return (jdm.type(target.innerText) === 'string' ? target.innerText : target.textContent);
					}
					if (jdm.type(target.innerText) === 'string') {
						target.innerText = value;
					} else {
						target.textContent = value;
					}
					return this;
				},
				hasClass : function(className) {
					var target = this[0];
					if( jdm.isNull(className)) return;
					
					var clazz = this.attr('class');
					if(jdm.isNull(clazz)) return false;
					
					clazz = clazz.split(' ');
					for (var len = clazz.length,i = 0; i < len ; i++){
						if (jdm.trim(clazz[i]) == className)	return true;
					}
					return false;
				},
				addClass : function(className) {
					var target = this[0];
					if(jdm.isNull(className)) return;
					
					if ( ! this.hasClass(className) ) {
						var clazz = this.attr('class');
						clazz +=" "+className;
						this.attr('class',clazz);
					}
					return this;
				},
				delClass : function(className) {
					var target = this[0];
					if(jdm.isNull(className)) return;
					
					var clazz = this.attr('class');
					if(jdm.isNull(clazz)) return;
					
					clazz = clazz.split(' ');
					for (var len = clazz.length,i = 0; i < len ; i++){
						if (clazz[i] == className)	{
							clazz.splice(i,1);
						}
					}
					this.attr('class',clazz.join(' '));
					return this;
				}
		};
		
		jdm.fn.init.prototype = jdm.fn;
		
		jdm.extend = jdm.fn.extend = function() {
			var options, name, src, copy, clone, target = arguments[0] || {}, deep = false, i=1,length = arguments.length;
			if ( typeof target === 'boolean') {
				deep = target;
				target = arguments[1] || {};
				i = 2;
			}
			if( typeof target !== "object") {
				target = {};
			}
			if (length === i) {
				target = this;
				--i;
			}
			for (; i < length; i++) {
				if ((options = arguments[i])) {
					for(name in options ) {
						src = target[name];
						copy = options[name];
						if(target === copy) {
							continue;
						}
						if (deep && copy && ( isArrays = jdm.isArray(copy) || jdm.isObject(copy) )) {
							if (isArrays) {
								isArrays = false;
								clone = src && jdm.isArray(src) ? src : [];
							} else {
								clone = src && jdm.isObject(src) ? src : {};
							}
							
							target[name] = jdm.extend(deep,clone,copy);
						} else if(copy !== undefined) {
							target[name] = copy;
						}
					}
				}
			}
			
			return target;
		}
		//获取关系节点
		jdm.fn.extend({
			parent : function() {
				return jdm(this[0].parentElement);
			},
			next : function(){
				return jdm(this[0].nextElementSibling);
			},
			before : function() {
				return jdm(this[0].previousElementSibling);
			},
			children : function(){
				return jdm(this[0].children);
			},
			appendChild : function(child){
				this[0].appendChild(child);
			}
		});
		//动画组件
		jdm.fn.extend({
			queue : [],
			animateObj : function(target,time,mode,start,curr,end){
				var obj = {};
				obj.target = target;
				obj.time = time;
				obj.mode = mode;
				obj.start = obj.curr = start;
				obj.end = end;
				
				return obj;
			},
			animate : function(prop,time,mode,callback) {
				
			}
		});
		//事件函数
		jdm.fn.extend({
			bind : function(type,handler){
				for (var i = 0; i < this.length; i++){
					var element = this[i];
					if (element.addEventListener) {
						element.addEventListener(type,handler,false);
					} else if (element.attachEvent) {
						element.attachEvent('on'+type,handler);
					} else {
						element['on'+type] = handler;
					}
				}
				return this;
			},
			unbind : function(type,handler){
				for (var i = 0; i < this.length; i++){
					var element = this[i];
					if (element.removeEventListener) {
						element.removeEventListener(type,handler,false);
					} else if (element.detachEvent) {
						element.detachEvent('on'+type,handler);
					} else {
						element['on'+type] = null;
					}
				}
				return this;
			},
			click : function(handler){
				return this.bind('click',handler);
			},
			unclick : function(handler){
				return this.unbind('click',handler);
			},
			focus : function (handler) {
				return this.bind('focus',handler);
			},
			unfocus : function (handler) {
				return this.unbind('focus',handler);
			},
			blur : function(handler){
				return this.bind('blur',handler);
			},
			unblur : function(handler){
				return this.unbind('blur',handler);
			},
			change : function(handler) {
				return this.bind('change',handler);
			},
			unchange : function(handler) {
				return this.unbind('change',handler);
			}
		}); 
		
		jdm.extend({
			isFunction : function(obj) { return jdm.type(obj) === "function"; },
			isObject : function(obj) { return jdm.type(obj) === "object"; },
			isWindow : function(obj) { return jdm.type(obj) === "window"; },
			isArray : Array.isArray || function(obj) { return jdm.type(obj) === "array"; },
			isNull : function(val) { if (val == '' || val == undefined || val == 'null') return true; return false; },
			isBlank : function (val) { return jdm.isNull(jdm.trim(val)); }, 
			isNotBlank : function(val) { return !jdm.isBlank(val); }, 
			eval : window.eval,
			trim : function(val) { if(jdm.isNull(val)) return ''; return val.toString().replace(/^\s+/, "").replace(/\s+$/, ""); },
			isEmptyObject : function(obj) {
				for(var name in obj ) {
					return false;
				}
				return true;
			},
			isPlainObject : function(obj) {
				if (obj.nodeType || jdm.isWindow(obj)) {
					return false;
				}
				return true;
			},
			supportAttr : function(element, attribute) {
				var elem = document.createElement(element);
				if (attribute in elem) 
					return true;
				return false;
			},
			find : function(selector) {
				if(jdm.isNull(selector)) return; 
				if (typeof selector !== 'string') 
					return selector; 
				if (selector.indexOf('#') == 0) 
					selector = selector.substring(1); 
				return document.getElementById(selector); 
			},
			type : function(obj) {
				return obj == null ? String(obj) : class2type[document.toString.call(obj)] || "object";
			},
			each : function(obj, callback) {
				var name,i = 0, length = obj.length, isObj = (length === undefined || jdm.isFunction(obj));
				if(isObj) {
					for(name in obj ) {
						if(callback.call(obj[name], name, obj[name]) === false) {
							break;
						}
					}
				} else {
					for(; i < length; ) {
						if(callback.call(obj[i], i, obj[i++]) === false) {
							break;
						}
					}
				}
			},
			scrollTop : function() {
				var scrolltop;
				if (document.compatMode == 'CSS1Compat') {
					//TODO 发现一个问题，在手机浏览器中，compatMode=CSS1Compat，但是documentElement.scrollTop值为0，无法获取正确值，但是通过body则可以获取 ，因此：
					try {
						scrolltop = document.body.scrollTop == 0 ? document.documentElement.scrollTop : document.body.scrollTop;
					}catch(ex){
						scrolltop = document.documentElement.scrollTop;
					}
				} else {
					scrolltop = document.body.scrollTop;
				}
				return scrolltop;
			},
			clientWidth : function() {
				var pageWidth;
				if (document.compatMode == 'CSS1Compat') {
					pageWidth = document.documentElement.clientWidth;
				} else {
					pageWidth = document.body.clientWidth;
				}
				return pageWidth;
			}(),
			clientHeight : function() {
				var pageHeight;
				if (document.compatMode == 'CSS1Compat') {
					try {
						pageHeight = document.body.clientHeight == 0 ? document.documentElement.clientHeight : document.body.clientHeight;
					}catch(ex){
						pageHeight = document.documentElement.clientHeight;
					}
				} else {
					pageHeight = document.body.clientHeight;
				}
				return pageHeight;
			}()
		});
		
		jdm.extend({
			ajax : function(type, url, mode, parameter, callback) {
				var xhr = jdm.createAjax(),param;
				if (jdm.type(parameter) !== 'function') {
					param = jdm.parseParam(parameter);
				}
				url += (url.indexOf('?') == -1 ? '?' : '&');
				xhr.onreadystatechange = function(){
					if (xhr.readyState == 4) {
						if((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304) {
							try {
								var data = window.eval('('+xhr.responseText+')');
								callback(data);
							} catch (ex) {
								try {
									callback(xhr.responseText);
								} catch(ex){
									alert(ex);
								}
							}
						}
					}
				};
				xhr.open(type,url+param,mode);
				xhr.send(null);
			},
			parseParam : function(param) {
				if(jdm.isNull(param)) return;
				var params = '';
				try {
					if (typeof param === 'string')
						param = window.eval('('+param+")");
				} catch (e) {
					return;
				}
				for (var prop in param) {
					params += prop+"="+param[prop]+"&";
				}
				
				params = params.substring(0,params.length-1);
				
				return params;
			},
			createAjax : function() {
				if (typeof XMLHttpRequest !== "undefined") {
					return new XMLHttpRequest();
				} else if (typeof ActivieXObject !== "undefined"){
					if (typeof arguments.callee.activeXString !== 'string') {
						var versions = ["MSXML2.XMLHttp.6.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp"];
						for (var i=0,len=versions.length;i<len;i++) {
							try {
								var xhr = new ActiveXObject(versions[i]);
								arguments.callee.activeXString=versions[i];
								return xhr;
							} catch(ex){}
						}
						return new ActiveXObject(arguments.callee.activeXString);
					}
				}
				throw new Error("No XHR object support");
			}
		});
		
		jdm.extend({
			isReady : false,
			readyList : [],
			ready : function() {
				var len =  jdm.readyList.length;
				if ( !jdm.isReady && len > 0) {
					for (var i = 0; i < len; i++) {
						jdm.readyList[i]();
					}
				}
				jdm.isReady = true;
			},
			bindReady : function(fn) {
				if(document.readyState === "complete" || document.readyState === 'interactive') {
					return setTimeout(fn, 1);
				}
				jdm.readyList[jdm.readyList.length] = fn;
				if(document.addEventListener) {
					document.addEventListener("DOMContentLoaded", DOMContentLoaded, false);
					window.addEventListener("load", jdm.ready, false);
				} else if(document.attachEvent) {
					document.attachEvent("onreadystatechange", DOMContentLoaded);
					window.attachEvent("onload", jdm.ready);
				}
			},
			getTarget : function (event) {
				return event.target || event.srcElement;
			}
		});
		
		jdm.each("Boolean Number String Function Array Date RegExp Object Null HTMLCollection Window".split(" "), function(i, name) {
			class2type["[object " + name + "]"] = name.toLowerCase();
		});
		
		if(document.addEventListener) {
			DOMContentLoaded = function() {
				document.removeEventListener("DOMContentLoaded", DOMContentLoaded, false);
				jdm.ready();
			};
		} else if(document.attachEvent) {
			DOMContentLoaded = function() {
				if(document.readyState === "complete") {
					document.detachEvent("onreadystatechange", DOMContentLoaded);
					jdm.ready();
				}
			};
		}
		return jdm;
	})();
	
	window.jdMobile = window.jdm = jdMobile;
	
})(window);