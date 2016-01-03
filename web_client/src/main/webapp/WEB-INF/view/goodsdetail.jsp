<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/public.css" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/product.css" />
		<script type="text/javascript" src="<%=basePath%>inc/js/core.js"></script>
		<script src="<%=basePath %>inc/jqzoom_ev-2.3/js/jquery.jqzoom-core.js" type="text/javascript"></script>
		<link rel="stylesheet" href="<%=basePath %>inc/jqzoom_ev-2.3/css/jquery.jqzoom.css" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				$.ajax({
					type: 'get',
					url: 'showSelecteval.action?g_eval=3&&goods_id='+${goods_id},
					cache:false,
					success: function(data, textStatus){
					var obj = jQuery.parseJSON(data);
					var str = "";
					for(var i=0;i<obj.length;i++){
						str += "<div> <dl class='goods-d-detail'> <dt> <img src='<%=basePath%>inc/images/tx.png' /> <br/>" + obj[i].user_name + "</dt> <dd> " ;
						if(obj[i].goods_score/20==1){
							str += "<p class='goods-d-level'> " +
									"<span class='serve-l-red '></span>  "+
									"<span class='serve-l-red serve-l-ash'></span>" +
									"<span class='serve-l-red serve-l-ash'></span>" +
									"<span class='serve-l-red serve-l-ash'></span>" +
									"<span class='serve-l-red serve-l-ash'></span> </p>";
						}else if(obj[i].goods_score/20==2){
							str +="<p class='goods-d-level'> " +
									"<span class='serve-l-red '></span>  "+
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red serve-l-ash'></span>" +
									"<span class='serve-l-red serve-l-ash'></span>" +
									"<span class='serve-l-red serve-l-ash'></span> </p> ";
						}else if(obj[i].goods_score/20==3){
							str +="<p class='goods-d-level'> " +
									"<span class='serve-l-red '></span>  "+
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red serve-l-ash'></span>" +
									"<span class='serve-l-red serve-l-ash'></span> </p> ";
						}else if(obj[i].goods_score/20==4){
							str +="<p class='goods-d-level'> " +
									"<span class='serve-l-red '></span>  "+
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red serve-l-ash'></span> </p> ";
						}else if(obj[i].goods_score/20==5){
							str +="<p class='goods-d-level'> " +
									"<span class='serve-l-red '></span>"+
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red '></span>" +
									"<span class='serve-l-red '></span> </p> ";
						} 
																
						str += "<div class='goods-d-content'> 心得： <span>" + obj[i].g_comment +" </span>";
																		
						if(obj[i].isZan == null ){
							str+= "<p class='goods-d-c-btn'> <a class='goods-d-useful' id='" +obj[i].ge_tradeid+ "' onclick='btn_good("+obj[i].good_num+", " + obj[i].ge_tradeid + ")'>赞（"+ obj[i].good_num +"）</a> </p> </div>";
						}else if(obj[i].isZan == 'true'){
							str+= "<p class='goods-d-c-btn'> <a class='goods-d-useful'>已赞（"+ obj[i].good_num +"）</a> </p> </div>";
						}
						str+= "<div class='goods-d-seller'> 卖家:" + obj[i].shop_name + 
								"<br /> " + obj[i].eval_date + "</div> </dd> </dl> </div>";
						}
						$("#goods-d-detail").html(str);
						}
					});
			});
		    $(function(){
		    	 //商品信息切换
		        $(".goods-d-tab > a").each(function(i,v){
		        	$(v).click(function(){
		        		$(v).addClass("active");
		        		$(".goods-d-show > div").each(function (k,d){
		        			if(i==k){
		        				$(d).show();
		        			}else{
		        				$(d).hide();
		        			}
		        		});
		        		$(".goods-d-tab > a").each(function(j,a){
		        			if(i!=j){
		        				$(a).removeClass("active");
		        			}
		        		})
		        	});
		        });
		        //购买商品输入数量限制
		        //文本框限输入整数            
	            $("input[id^='goodsNum']").keyup(function () {
	                var e = $(this).event || window.event;
	                var code = parseInt(e.keyCode);
	                if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {
	                  if($("#goodsNum").val()*1>$("#now_stock").val()*1){
	                	    $("#goodsNum").val($("#now_stock").val());
	                	}
	                } else{
	                		$("#goodsNum").val(1);
	                }
	            });
		         
		    	var imgs = '${goods.img_path}';
		    	var img_n = imgs.split(",").length;
		    	/*
		        $('#imgRoll1').imgRoll({
		            num: 5,
		            margin: 4
		        });
		        */
		    });
		    
		</script>
		<title>${goods.goods_name}-<%=webname%></title>
	</head>

	<body>
		<%@ include file="/WEB-INF/view/inc/top.jsp"%>

		<%@ include file="/WEB-INF/view/user/inc/top.jsp"%>

		<%@ include file="/WEB-INF/view/inc/nav.jsp"%>



		<div class="w1200">
			<div class="position">
				<a href="/">首页</a>
				<c:forEach items="${posTreeList}" var="category">><a
						href="<%=basePath%>goodslist.action?cat_id=${category.cat_id}">${category.cat_name}</a>
				</c:forEach>
			</div>
			<div class="goodsdetail">
				<!--左侧图片展示-->
				<div class="fl">
					<div id="_img_show">
					<c:if test="${!empty goods.img_path}">
						<c:set value="${fn:split(goods.imagePath_70,',')}" var="imgs70" />
						<c:set value="${fn:split(goods.imagePath_350,',')}" var="imgs350" />
						<c:set value="${fn:split(goods.img_path,',')}" var="imgs" />
						<a href="${imgs[0] }" class="jqzoom" >
							<img src="${imgs350[0] }" />
						</a>
						<script type="text/javascript">
							//图片缩放
							$('.jqzoom').jqzoom({
								zoomType: 'standard',
								lens:true,
								preloadImages: false,
								alwaysOn:false
						    });
						</script>
					</c:if>
					</div>
					<!--图片滚动展示-->
					<div class="goods-imgs" id="imgRoll1">
						<a class="goods-prev"></a>
						<div class="goods-imgs-list">
							<ul class="scroll_wrap">
								
								<c:forEach items="${imgs70}" var="img" varStatus="status">
									<li>
										<img src="${img}" id="samllImg${status.index }" smallimage="${imgs350[status.index] }" largeimage="${imgs[status.index] }" onclick="showImg('samllImg${status.index }');" />
									</li>
								</c:forEach>
							</ul>
						</div>
						<a class="goods-next"></a>
						<ul class="scroll_position" id='scroll_position'>
							
						</ul>
						<!--<li style="float:right;color:#999999;"><a href="<%=basePath%>user/add_buyer_report.action?goods_id=${goods.goods_id}&cust_id=${goods.cust_id}" target="_blank" style="color:#999999;">举报</a></li>-->
					</div>
					<script type="text/javascript">
						 function DY_scroll(wraper,prev,next,img,speed,or)
							 { 
							  var wraper = $(wraper);
							  var prev = $(prev);
							  var next = $(next);
							  var img = $(img).find('ul');
							  var w = img.find('li').outerWidth(true);
							  var s = speed;
							  next.click(function()
							       {
							        img.animate({'margin-left':-w},function()
							                  {
							                   img.find('li').eq(0).appendTo(img);
							                   img.css({'margin-left':0});
							                   });
							        });
							  prev.click(function()
							       {
							        img.find('li:last').prependTo(img);
							        img.css({'margin-left':-w});
							        img.animate({'margin-left':0});
							        });
							  if (or == true)
							  {
							   ad = setInterval(function() { next.click();},s*1000);
							   wraper.hover(function(){clearInterval(ad);},function(){ad = setInterval(function() { next.click();},s*1000);});
							
							  }
							 }
							  DY_scroll('.goods-imgs','.goods-prev','.goods-next','.goods-imgs-list',3,false);// true为自动播放，不加此参数或false就默认不自动
					</script>
				</div>
				<!--中间价格-->
				<div class="goods-descri fl">
					<h2>
						${goods.goods_name}
					</h2>
					<p class="goods-p">
						${goods.goods_desc}
					</p>
					<div class="goods-num">
						<p>
							<span>商品编码：<em>${goods.goods_no}</em> </span>
						</p>
						<p>
							<span>名 品 价：<em class="price-n"><i>¥</i>${goods.sale_price}</em>
							</span><span> 市场价：<em class="price-o">¥${goods.market_price}</em>
							</span>
						</p>
					</div>
					<c:if test="${! empty goodsAttrs }">
						<div class="goods-p-msg">
							<c:forEach items="${goodsAttrs}" var="gattrs" varStatus="status">
								<c:if test="${gattrs.attr_type==3&&gattrs.is_must==1}">
									<p id="p_${status.index}">
									${gattrs.attr_name}
									<c:if test="${!empty gattrs.attrValue}">
									   :<c:forEach items="${gattrs.attrValue}" var="maps" varStatus="child_status">
										<span class="goods-p-color <c:if test="${child_status.first}">goods-p-color-on</c:if>" onclick="selectAttr('${status.index}','${gattrs.attr_name}.${maps.attr_value}','${child_status.index}',this);"> 
											 ${maps.attr_value}
										</span>
										</c:forEach>
									</c:if>
									<input name="attr_value${status.index}" value="${gattrs.attr_name}.${gattrs.attrValue[0].attr_value}" type="hidden" vType="rdattr_value" />
									</p>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<div class="goods-num" id="product-num">
						<div style="display: none;">
							温馨提示：
							<p>
								运 费：
								<select>
									<option>
										北京市 东城区
									</option>
								</select>
								<span class="freight"><strong>现货</strong>运费：¥6.00</span>
							</p>
						</div>
						<p>
							<span>数 量： <em class="add-num"
								onclick="javascript:setBuyGoodsNum('0');">-</em> <input
									class="input-num" value="1" type="text" id="goodsNum" /> <input
									value="${goods.now_stock}" id="now_stock" type="hidden" /> <em
								class="add-num" onclick="javascript:setBuyGoodsNum('1');">+</em>
							</span>（库存）${goods.now_stock}件
						</p>
					</div>
					<c:if test="${goods.isdown==0 && goods.now_stock>0}">
						<div class="goods-action">
							<a class="now-buy" onclick="javascript:buyNow();" id="buyNow"></a>
							<a style="width: 162px;" class="add-cart" id="addGoods" onclick="javascript:addCard();"></a>
							<a class="add-house" onclick="javascript:addCollect();"></a>
						</div>
					</c:if>
					<c:if test="${goods.isdown==1 || goods.now_stock<=0}">
						 <div class="goods-action">
			                <a class="now-buy now-buy-gray"></a>
			                <a style="width: 162px;" class="add-cart add-cart-gray"></a>
			                <a class="add-house" onclick="javascript:addCollect();"></a>
			            </div>
					</c:if>
					<div class="goods-share">
						<c:if test="${goods.isdown==1}">
							商品已下架
						</c:if>
						<c:if test="${goods.isdown==0}">
								<span class="fl">告诉小伙伴：</span>
								<div class="bdsharebuttonbox fl"><A class=bds_more href="#" data-cmd="more"></A><A class=bds_qzone title=分享到QQ空间 href="#" data-cmd="qzone"></A><A class=bds_tsina title=分享到新浪微博 href="#" data-cmd="tsina"></A><A class=bds_tqq title=分享到腾讯微博 href="#" data-cmd="tqq"></A><A class=bds_renren title=分享到人人网 href="#" data-cmd="renren"></A><A class=bds_weixin title=分享到微信 href="#" data-cmd="weixin"></A></div>
								<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
						</c:if>
					</div>
				</div>
				<!--右侧商家-->
				<div class="goods-seller">
					<ul class="goods-s-item">
						<li style="color: #005ea7">
							<c:forEach items="${memberinfo}" var="info" varStatus="status">
								<c:if test="${status.index == 0}">
									<span style="color: #5d5d5d">卖&nbsp;&nbsp;&nbsp;&nbsp;家：</span>${info.shop_name }
								</c:if>
							</c:forEach>
						</li>
						<li style="border-bottom-style: dotted">
							<p>
								评分明细&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
							<c:if test="${empty stareval}">
								<div class="grade-detail">
								包装满意评分：
									<span class="grade-v">0分</span><span class="rose"></span>
								<br />
								送货速度评分：
									<span class="grade-v">0分</span><span class="rose"></span>
								<br />
								服务态度评分：
									<span class="grade-v">0分</span><span class="rose"></span>
							</div>
							</c:if>
							<c:if test="${!empty stareval}">
								<c:forEach items="${stareval}" var="star" varStatus="status">
									<div class="grade-detail">
										包装满意评分：
											<span class="grade-v">${star.psum }分</span><span class="rose"></span>
										<br />
										送货速度评分：
											<span class="grade-v">${star.spsum }分</span><span class="rose"></span>
										<br />
										服务态度评分：
											<span class="grade-v">${star.sesum }分</span><span class="rose"></span>
									</div>
								</c:forEach>
							</c:if>
							<div class="serve-online">
								<span></span>
								<a>在线客服</a>
								<a href="<%=basePath%>shop/${goods.cust_id}.action">进入店铺</a>
							</div>
						</li>
						<c:forEach items="${memberinfo}" var="info" varStatus="status">
							<c:if test="${status.index == 0}">								
							<li
								style="color: #5d5d5d; line-height: 24px; padding-right: 10px;">
								公司名称：
								<span>${info.cust_name }</span>
								<br />
								所在地：${info.address }
								<br />
								联系电话：${info.phone }
							</li>
							<li style="padding: 10px 0 10px 12px">
								<a class="grade-s-house" href="javascript:void(0);" onclick="javascript:addShopCollect();">收藏店铺</a>
							</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
		<div class="clearfix"></div>

		<!--商品详情-->
		<div class="w1200">
			<div class="goods-details">

				<!--商品详情类别切换-->
				<div class="goods-d-tab">
					<a>商品详情<span></span> </a>
					<a>商品参数<span></span> </a>
					<a class="active">商品评价<span></span> </a>
					<a>售后服务<span></span> </a>
				</div>

				<!--商品详情类别对应切换内容-->
				<div class="goods-d-show" id="goods-d-show">

					<!--商品详情-->
					<br />
					<div style="display: none">
						<c:if test="${empty goods.goods_detail}">
							此商品暂无商品详情
						</c:if>	
						<c:if test="${goods.goods_detail != null}">
							${goods.goods_detail}
						</c:if>	
					</div>

					<!--商品参数-->
					<br />
					<div style="display: none">
						<c:if test="${empty goodsAttrs}">
							此商品暂无商品参数
						</c:if>	
						<c:if test="${!empty goodsAttrs}">	
							<c:forEach items="${goodsAttrs}" var="goodsAttrs">
								<c:out value="${goodsAttrs.attr_name}"></c:out>
								<c:if test="${!empty goodsAttrs.attrValue}">
								    :<c:forEach items="${goodsAttrs.attrValue}" var="maps">
										${maps['attr_value']}
									</c:forEach>
								</c:if>
								<br></br>
	
							</c:forEach>
						</c:if>
					</div>

					<!--商品评价-->

					<div class="goods-discuss">
					<!-- 开始 -->
						<div class="goods-dpandect">
							<div class="goods-d-chance">
								<span class="goods-d-c-v"> <strong>${best}%</strong> <br />好评度 </span>
								<div class="goods-d-ratio">
									<p>
										<em>好评</em><span><span class="goods-d-r-red"
											style="width: ${best }%"></span> </span>${best }%
									</p>
									<p>
										<em>中评</em><span><span class="goods-d-r-red"
											style="width: ${better }%"></span> </span>${better }%
									</p>
									<p>
										<em>差评</em><span><span class="goods-d-r-red" 
										style="width: ${bad }%"></span> </span>${bad }%
									</p>
								</div>
							</div>
							<div class="goods-d-hot">
			                    <img src="<%=rootPath%>images/code-p.png" width="90" height="90"/>
			                    <p>
			                       <img src="<%=rootPath%>images/appstore.png"/><br/>
			                       <img src="<%=rootPath%>images/android.png"/><br/>
			                            下载天下名品手机客户端更有购物优惠哦！
			                    </p>
			                </div>							
			                <div class="goods-d-write">
			                        您可对已购的商品进行评价<br/>
			                        <a>发表评价</a><br/>
			                        1积分=0.5元&nbsp;<span class="a-lan">评论规则</span>
			                </div>
						</div>
						<!-- 结束 -->
						<div class="goods-d-list">
							<!-- 开始 -->
							<c:if test="${empty evallist}">							
								<p class="goods-d-type" id="goods-d-type">
									<span class="active"><input type="radio" name="discuss"
											checked="checked" /> 全部评价（0）</span>
									<span><input type="radio" name="discuss" /> 好评（0）</span>
									<span><input type="radio" name="discuss" /> 中评（0）</span>
									<span><input type="radio" name="discuss" /> 差评（0）</span>
									<br />
									<br />
									此商品暂无商品评价
								</p>
							</c:if>
							<c:if test="${!empty evallist}">
								<c:forEach items="${evallist}" var="eval" varStatus="status">	
									<c:if test="${status.index == 0}">									
										<p class="goods-d-type" id="goods-d-type">
											<span class="active"><input id="alleval" type="radio" name="discuss" checked="checked" onclick="showSelecteval(3)"/> 全部评价（${memsum }）</span>
											<span><input type="radio" name="discuss" onclick="showSelecteval(2)"/> 好评（${bestnum }）</span>
											<span><input type="radio" name="discuss" onclick="showSelecteval(1)"/> 中评（${betternum }）</span>
											<span><input type="radio" name="discuss" onclick="showSelecteval(0)"/> 差评（${badnum }）</span>
										</p>
										<script type="text/javascript">
										function showSelecteval(v){
											$.ajax({
												type: 'get',
												url: 'showSelecteval.action?g_eval='+v+'&&goods_id='+${eval.goods_id},
												cache:false,
												success: function(data, textStatus){
													var obj = jQuery.parseJSON(data);
													var str = "";
													for(var i=0;i<obj.length;i++){
														str += "<div> <dl class='goods-d-detail'> <dt> <img src='<%=basePath%>inc/images/tx.png' /> <br/>" + obj[i].user_name + "</dt> <dd> " ;
																if(obj[i].goods_score/20==1){
																	str += "<p class='goods-d-level'> " +
																			"<span class='serve-l-red '></span>  "+
																			"<span class='serve-l-red serve-l-ash'></span>" +
																			"<span class='serve-l-red serve-l-ash'></span>" +
																			"<span class='serve-l-red serve-l-ash'></span>" +
																			"<span class='serve-l-red serve-l-ash'></span> </p>";
																}else if(obj[i].goods_score/20==2){
																	str +="<p class='goods-d-level'> " +
																			"<span class='serve-l-red '></span>  "+
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red serve-l-ash'></span>" +
																			"<span class='serve-l-red serve-l-ash'></span>" +
																			"<span class='serve-l-red serve-l-ash'></span> </p> ";
																}else if(obj[i].goods_score/20==3){
																	str +="<p class='goods-d-level'> " +
																			"<span class='serve-l-red '></span>  "+
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red serve-l-ash'></span>" +
																			"<span class='serve-l-red serve-l-ash'></span> </p> ";
																}else if(obj[i].goods_score/20==4){
																	str +="<p class='goods-d-level'> " +
																			"<span class='serve-l-red '></span>  "+
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red serve-l-ash'></span> </p> ";
																} 
																else if(obj[i].goods_score/20==5){
																	str +="<p class='goods-d-level'> " +
																			"<span class='serve-l-red '></span>"+
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red '></span>" +
																			"<span class='serve-l-red '></span> </p> ";
																} 
																
																str += "<div class='goods-d-content'> 心得： <span>" + obj[i].g_comment +" </span>";
																		
																if(obj[i].isZan == null){
																	str+= "<p class='goods-d-c-btn'> <a id='" +obj[i].ge_tradeid+ "' class='goods-d-useful' onclick='btn_good("+obj[i].good_num+", " + obj[i].ge_tradeid +")'>赞（"+ obj[i].good_num +"）</a> </p> </div>";
																}else if(obj[i].isZan == 'true' ){
																	str+= "<p class='goods-d-c-btn'> <a class='goods-d-useful'>已赞（"+ obj[i].good_num +"）</a> </p> </div>";
																}
																str+= "<div class='goods-d-seller'> 卖家:" + obj[i].shop_name + 
																		"<br /> " + obj[i].eval_date + "</div> </dd> </dl> </div>";
													}
													$("#goods-d-detail").html(str);
												}
											});
										}
									</script>
									<script type="text/javascript">
														function btn_good(num, id){
															$.ajax({
																type: 'get',
																url: 'setGoodNumCookie.action?trade_id='+ id +'&&goods_id='+${eval.goods_id}+'&&goodnum='+num,
																cache:false,
																success: function(data){
																	if(data ==1){
																		$('#' + id).html('已赞（' + (parseInt(num)+1) + ')');
																		$('#' + id).attr("onclick","");
																	}
																}
															});
														}
													</script>
									</c:if>						
								</c:forEach>
							</c:if>
							<!-- 结束 -->
							<!-- 开始 -->
							<div id="goods-d-detail">
								
								
							</div>
							<!-- 结束 -->
						</div>
					</div>

					<!--售后服务-->
					<br />
					<div style="display: none">
						此商品暂无售后服务
					</div>
				</div>

			</div>
		</div>

		<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
		<script>
		 
		 	function setBuyGoodsNum(flag){
		 		var goodsNum=$("#goodsNum").val()*1,stock =$("#now_stock").val()*1;
		 		if(flag=='0'){
		 			--goodsNum;
		 		}
		 		if(flag=='1'){
		 			++goodsNum;
		 		}
		 		if(goodsNum>stock){
		 			goodsNum=stock;
		 		}
		 		if(goodsNum<1){
		 			goodsNum=1;
		 		}
		 		$("#goodsNum").val(goodsNum);
		 	}
		
            //立即购买
             function buyNow(){
             	 var selAll = true;
            	 var s=''; 
				 $('input[vType="rdattr_value"]').each(function(){ 
				 	var curval = $(this).val();
				  	if(curval==null || curval==''){
				  		selAll = false;
				  		return ;
				  	}
				    s+=curval+';'; 
				 });
				 if(!selAll){
				 	alert("请选择属性");
				 }else{
				 	location.href="<%=basePath%>order/confirm_order.action?goods.goods_id=${goods.goods_id}&amount="+$("#goodsNum").val()+"&goods_param="+s.substring(0,s.length-1);	
				  }
                
            };
            
            function addCard(){
            	 var s=''; 
            	 var selAll = true;
				  $('input[vType="rdattr_value"]').each(function(){ 
				 	var curval = $(this).val();
				  	if(curval==null || curval==''){
				  		selAll = false;
				  		return ;
				  	}
				    s+=curval+';'; 
				  });
				 if(!selAll){
				 	alert("请选择属性");
				 }else{
					location.href=" <%=basePath%>cartadd.action?goods.goods_id=${goods.goods_id}&amount="+$("#goodsNum").val()+"&goods_param="+s.substring(0,s.length-1);				 
				 }
            };
            
            //商品收藏
            function addCollect(){
				$.ajax({
				type: "get",
				url: "<%=basePath%>collectadd.action?info_id=${goods.goods_id}&info_type=0",
					success: function(data, textStatus){
						if(data == "1"){
							alert("收藏成功");
						}else if(data == "2"){
							alert("您已收藏该商品");
						}else if(data == "3"){
							alert("请您先登录，再收藏");
						}
					}
				});
			}
			
			//商家收藏
            function addShopCollect(){
				$.ajax({
				type: "get",
				url: "<%=basePath%>collectadd.action?info_id=${goods.cust_id}&info_type=1",
					success: function(data, textStatus){
						if(data == "1"){
							alert("收藏成功");
						}else if(data == "2"){
							alert("您已收藏该商家");
						}else if(data == "3"){
							alert("请您先登录，再收藏");
						}
					}
				});
			}
			
			function changeBigImg(img){
				$("#bigImg")[0].src=img.src;
				DrawImage($("#bigImg")[0],427,427);
			}
			
			function showImg(img_id) {
				var bigImg = $('#' + img_id).attr('largeimage');
				var smaImg = $('#' + img_id).attr('smallimage');
				var show_img_str = '<a id="img_show" href="' + bigImg + '" class="jqzoom" >'
						+ '<img src="' + smaImg + '" /></a>';
				$('#_img_show').html(show_img_str);
				$('.jqzoom').jqzoom({
				            zoomType: 'standard',
				            lens:true,
				            preloadImages: false,
				            alwaysOn:false
				        });
			}
			
		 
			//选择商品属性
			function selectAttr(index, attr_val ,num,obj){
				$("input[name='attr_value"+index+"']").val(attr_val);
				$(obj).addClass("goods-p-color-on");
				$("#p_"+index+" > span").each(function(i,v){
					if(i!=num){
						$(v).removeClass("goods-p-color-on");
					} 
				});
			}
		 </script>
	</body>
</html>
