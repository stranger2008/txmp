<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>${goods.goods_name}-商品详细-<%=web_title %></title>
<link rel="stylesheet" href="<%=basePath%>inc/css/product.css" />
<script type="text/javascript">
	//商品信息切换
   $(function(){
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
   });
   
   //选择商品属性
	function selectAttr(index, attr_val ,num,obj){
		$("input[name='attr_value"+index+"']").val(attr_val);
		$(obj).addClass("goods-p-color-on");
		$("#p_"+index+"  span").each(function(i,v){
			if(i!=num){
				$(v).removeClass("goods-p-color-on");
			} 
		});
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
	 	location.href="<%=basePath%>order/buy_instantly.action?goods.goods_id=${goods.goods_id}&amount=1&goods_param="+s.substring(0,s.length-1);	
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
		location.href=" <%=basePath%>cartadd.action?goods.goods_id=${goods.goods_id}&amount=1&goods_param="+s.substring(0,s.length-1);				 
	 }
   };
</script>
</head>

<body>
<div class="w480">
    
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    <div class="content">
    
    <!--当前位置开始-->
    <div class="module">
        <section>
            <div class="position mt5">
                <a href="<%=basePath %>">首页</a>
                 <c:forEach items="${posTreeList}" var="category">
                 	> <a href="<%=basePath %>goodslist.action?cat_id=${category.cat_id}">${category.cat_name}</a> 
                 </c:forEach>
            </div>
        </section>
    </div>
    
    <!--内容开始-->
    <div class="module">
        <section class="box_product mt10 mt5">
            <div class="product">
                <div class="pro_bg">
                	<div class="pro_img">
                		
                		<c:if test="${!empty goods.img_path}">
                		
							<article>
	                          <!--scroll-->
	                          <div class="scroll relative">
	                            <div class="scroll_box" id="scroll_img">
	                                <ul class="scroll_wrap">
	                                
	                                	<c:set value="${fn:split(goods.img_path,',')}" var="names" />
										<c:forEach items="${names}" var="name">
											<li><a href="${name}"><img src="${name}" width="100%" /></a></li>
										</c:forEach>
										
	                                </ul>
	                            </div>
	                            <ul class="scroll_position" id='scroll_position'>
	                            
	                            	<c:forEach items="${names}" var="name" varStatus="status">
										<li <c:if test="${status.index+1 == 1 }"> class="on"</c:if>><a href="javascript:void(0);">${status.index+1 }</a></li>
									</c:forEach>
	                              
	                            </ul>
	                          </div>
	                          <!--scroll-->
	                      </article>
	                    <script src='<%=basePath %>inc/js/hhSwipe.js'></script>
	                    
	                    </c:if>

                	</div>
                	</div>
                <p class="pro_tit mt10">${goods.goods_name}</p>
                <div class="pro_do">
                    <div class="pro_price mt10">
                    	<span class="num_Pri">市场价￥${goods.market_price}</span>
                    	<span class="happy">名品价<em>￥${goods.sale_price}</em></span>
                    	<a href="javascript:void(0);" onclick="addCollect()" class="clt-star"><span></span></a>
                    	<script type="text/javascript">
		                	function addCollect(){
		                		$.ajax({
									type: "get",
									url: "<%=basePath %>collectadd.action?info_id=${goods.goods_id}&info_type=0&rdm="+Math.random(),
									success: function(data, textStatus){
										if(data == "1"){
											alert("收藏成功");
										}else if(data == "2"){
											alert("您已收藏该商品");
										}else if(data == "3"){
											//alert("请您先登录，再收藏");
											if(confirm("请您先登录，再收藏，是否现在登录？")){
												window.location = "<%=basePath %>login.action";
											}
										}
									}
								});
		                	}
		                </script>
                    </div>
                    
                    <!--商品介绍-->
                    <div class="pro_info">
                        <table class="pro_tab" cellpadding="0" cellspacing="0" width="100%">
                            <tr><th colspan="2">
                            	<span>商品介绍</span>
                            	<a href="<%=basePath %>gasklist.action?goods_id=${goods.goods_id}" class="btn-chat fr">
                            		<span class="icon-chat"></span>购买咨询（<span id="buyConsult">0</span>）
                            	</a>
                            	<script>
                            		function reloadConsultNum(){
	                            		$.ajax({
											type: "get",
											async:false,
											url: "<%=basePath %>getGoodsConsultNum-${goods.goods_id}.action",
											success: function(data, textStatus){
												$("#buyConsult").html(data);
											}
										});
									}
									reloadConsultNum();
                            	</script>
                            </th></tr>
                            <tr><td colspan="2">
                            	<c:out value="${goods.goods_desc}" escapeXml="false"/>
                            </td></tr>
                            <!-- 
                            <tr><td colspan="2">商品编号：<b>1050861587</b></td></tr>
                            <tr><td colspan="2">店铺：<b>妮琪贝瑞旗舰店</b></td></tr>
                            <tr><td colspan="2">上架时间：<b>2013-10-31 11:24:40</b></td></tr>
                            <tr><td colspan="2">商品毛重：<b>500.00g</b></td></tr>
                            
                            <tr><td>商品产地：<b>中国大陆</b></td><td>尺码：<b>S，M，L</b></td></tr>
                            <tr><td>花型：<b>纯色</b></td><td>厚薄：<b>普通</b></td></tr>
                            <tr><td>材质：<b>腈纶/化纤</b></td><td>颜色：<b>白色</b></td></tr>
                            <tr><td>衣长：<b>常规款(51-65CM)</b></td><td>领型：<b>V领</b></td></tr>
                            <tr><td>版式：<b>修身</b></td><td>袖长：<b>长袖</b></td></tr>
                             -->
                        </table>
                    </div>

                    <!--参数选择-->
                    <c:if test="${! empty goodsAttrs }">
		                   <div class="para_sele mt10">
		                       <h2>参数选择</h2>
		                       <table class="para_tab" cellpadding="0" cellspacing="10">
			                       <c:forEach items="${goodsAttrs}" var="gattrs" varStatus="status">
										<c:if test="${gattrs.attr_type==3&&gattrs.is_must==1}">
										 <tr id="p_${status.index}"><td>${gattrs.attr_name}
											
											<c:if test="${!empty gattrs.attrValue}">
											   ：<c:forEach items="${gattrs.attrValue}" var="maps" varStatus="child_status">
												<span class="goods-p-color <c:if test="${child_status.first}">goods-p-color-on</c:if>" onclick="selectAttr('${status.index}','${gattrs.attr_name}.${maps.attr_value}','${child_status.index}',this);"> 
													 ${maps.attr_value}<em></em>
												</span>
												</c:forEach>
											</c:if>
											<input name="attr_value${status.index}" value="${gattrs.attr_name}.${gattrs.attrValue[0].attr_value}" type="hidden" vType="rdattr_value" />
											</td></tr>
										</c:if>
									</c:forEach>
		                       </table>
		                   </div>
					</c:if>
	         <!--商品详情-->
			<div class="pro_info">
				<div class="goods-details">

				<!--商品详情类别切换-->
				<div class="goods-d-tab">
					<a class="active">商品详情<span></span> </a>
					<a>商品参数<span></span> </a>
				</div> 
                    <!--商品详情类别对应切换内容-->
					<div class="goods-d-show" id="goods-d-show">
						<!--商品详情-->
						<div>
						<br />
							<c:if test="${empty goods.goods_detail}">
								此商品暂无商品详情
							</c:if>	
							<c:if test="${goods.goods_detail != null}">
								${goods.goods_detail}
							</c:if>	
						</div>
	
						<!--商品参数-->
						<div style="display: none">
						<br />
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
					</div>
				</div></div>
				
                    <p class="btn-area">
                        <span class="tbl-type">
                            <span class="tbl-cell w50"><a href="<%=basePath %>shop/${goods.cust_id}.action" class="contact">进入店铺</a></span>
                        </span>
                    </p>
                    
                    
                    
                    <p class="btn-area">
                        <span class="tbl-type" style="position:fixed; bottom:0; z-index:10; display:table; margin-left:-5px;">
                        	<c:if test="${now lt goods.up_date }">
                        		<span class="tbl-cell w50">
                            		<a href="javascript:void(0);" class="cannotbuynow">未上架</a>
                           		</span>
                        	</c:if>
                        	<c:if test="${now gt goods.up_date && now gt goods.down_date }">
                        		<span class="tbl-cell w50">
                            		<a href="javascript:void(0);" class="cannotbuynow">已下架</a>
                           		</span>
                        	</c:if>
                        	
                        	<c:if test="${now gt goods.up_date && now lt goods.down_date && goods.now_stock == 0 }">
                        		<span class="tbl-cell w50">
                            		<a href="javascript:void(0);" class="cannotbuynow">库存不足</a>
                           		</span>
                        	</c:if>
                        	
                        	<c:if test="${now gt goods.up_date && now lt goods.down_date && goods.now_stock != 0 }">
                        		<span class="tbl-cell w50">
	                            	<a href="javascript:addCard();" class="cart-btn">加入购物车</a>
	                            </span>
	                            <span class="tbl-cell w50">
	                            	<a href="javascript:buyNow();" class="cart-btn buynow">立即购买</a>
	                            </span>
                        	</c:if>
                            
                        </span>
                    </p>

                </div>
            </div>
            
            <!--据浏览猜你喜欢-->
            <div class="like clearfix">
            
                <h2>
                    <span>据浏览猜你喜欢</span>
                </h2>
                
                <div class="like_cont margin-center">
                    <ul>
                    
                    	<c:forEach items="${guessList}" var="goods">
	                    	
	                        <li>
	                            <a href="<%=basePath %>goods/${goods.goods_id}.action" class="like-img">
	                            	<c:if test="${!empty goods.img_path}">
		                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,90,90)"/>
	                        		</c:if>
	                            </a>
	                            <p class="goods-name"><a href="<%=basePath %>goods/${goods.goods_id}.action" style="font-size:12px;">${goods.goods_name}</a></p>
	                            <p>￥${goods.sale_price}</p>
	                        </li>
	                        
                        </c:forEach>
                    
                    </ul>

                </div>
            </div>
        </section>
    </div>
            
    
</div>
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    
</div>
    


</body>
</html>
