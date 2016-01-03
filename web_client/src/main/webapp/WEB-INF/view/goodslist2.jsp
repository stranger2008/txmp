<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/public.css" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/product.css" />
		<title>商品列表页</title>
	</head>
	<body>

		<!--头部开始-->
		<%@ include file="/WEB-INF/view/inc/top.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/search.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/nav.jsp" %>
		<form action="goodslist.action">
			<!--nav开始-->
			<div class="w1200">
				<div class="pro-head clearfix">
					<div class="position-b fl">
						<!-- 
						<a href="#">全部结果</a><span>></span><a href="#">"酒"</a>
						 -->
						<c:forEach items="${posTreeList}" var="item" varStatus="status">
							<span style="width:100px;"> 
								<a href="<%=basePath%>goodslist.action?cat_id=${item.cat_id}"  <c:if test="${status.last}">style="color:red;"</c:if> >
									${item.cat_name}
								</a>
								<c:if test="${!status.last}"> > </c:if>
							</span>
						</c:forEach>
					</div>
					<div class="search-pro fl">
						<input type="text" class="searpro-txt" value="在结果中搜索" />
						<b></b>
					</div>
				</div>
				<table class="sp-wrap" cellpadding="0" cellspacing="0" width="100%">
					<tr id="searchTr" <c:if test='${g.brand_name==null||g.brand_name==""}'>style="display:none"</c:if>>
						<td class="sp-key">
							已选择:
						</td>
						<td class="sp-value">
							<div class="sp-sel">
								<span>${g.brand_name} </span><b onclick="javascript:deleteSearch();"></b>
							</div>
						</td>
					</tr>
					<c:forEach items="${goodsAttr}" var="gattrs">
						<tr>
							<td class="sp-key">
								${gattrs.attr_name}:
							</td>
							<td class="sp-value">
								<c:forEach items="${gattrs.attrValue}" var="maps">
										<a href="<%=basePath%>goodslist.action?cat_id=${g.cat_id}&trade_id=${maps['trade_id']}&attr_id=${maps.attr_id }&brand_name=${gattrs.attr_name}.${maps['attr_value']}">${maps['attr_value']}</a> 
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="sp-key">
							品牌:
						</td>
						<td class="sp-value">
							<c:forEach items="${goodsBands}" var="goodsBands">
								<a href="<%=basePath%>goodslist.action?brand_id=${goodsBands.brand_id}&cat_id=${g.cat_id}&brand_name=${goodsBands.brand_name}">${goodsBands.brand_name}</a>
							</c:forEach>
						</td>
					</tr>
					<!-- 
					<tr>
						<td class="sp-key noborder">
							价格:
						</td>
						<td class="sp-value noborder">
							<a href="javascript:findGoodsByPrice(0,199);">0-199</a>
							<a href="javascript:findGoodsByPrice(200,399);">200-399</a>
							<a href="javascript:findGoodsByPrice(400,699);">400-699</a>
							<a href="javascript:findGoodsByPrice(700,1199);">700-1199</a>
							<a href="javascript:findGoodsByPrice(1200,2699);">1200-2699</a><a href="javascript:findGoodsByPrice(2700,3099);">2700-3099</a>
							<a href="javascript:findGoodsByPrice(3100,'');">3100以上</a>
							<span class="pri-ipt">
								<input type="text" class="pri-txt" id="minPrice" value="${g.minPrice}"/>
								<em>-</em> 
								<input type="text" class="pri-txt" id="maxPrice" value="${g.maxPrice }"/> 
								<input type="button" value="确定" class="pri-btn" onclick="javascript:searchGoodsByPrice();" /> 
							</span>
						</td>
					</tr>
					 -->
				</table>

				<div class="promain">
					<div class="promain-head">
						<span> 
							<a <c:if test="${g.pg.sortCode==null}" >class="a-hover" </c:if> href="<%=basePath%>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&brand_name=${g.brand_name}">综合<b></b> </a> 
						</span>
						<span>
							<a <c:if test="${g.pg.sortCode==11}" >class="a-hover" </c:if> href="<%=basePath%>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=11&brand_name=${g.brand_name}">人气<b></b> </a> 
						</span>
						<span>
							<a <c:if test="${g.pg.sortCode==0}">class="a-hover" </c:if> href="<%=basePath%>goodslist.action?label=${g.label}&pg.sortCode=0&cat_id=${g.cat_id}&brand_name=${g.brand_name}">新品<b></b> </a> 
						</span>
						<span> 
							
							<c:choose>
								<c:when test="${g.pg.sortCode==20}">
									<a class="a-hover" href="<%=basePath%>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=21&brand_name=${g.brand_name}">价格<b></b> </a>
								</c:when>
								<c:when test="${g.pg.sortCode==21}">
									<a class="a-hover" href="<%=basePath%>goodslist.action?label=${g.label}&pg.sortCode=20&cat_id=${g.cat_id}&brand_name=${g.brand_name}">价格<b></b> </a>
								</c:when>
								<c:otherwise>
									<a <c:if test="${g.pg.sortCode==20 || g.pg.sortCode==21}"> class="a-hover"</c:if> href="<%=basePath%>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=20&brand_name=${g.brand_name}">价格<b></b> </a>
								</c:otherwise>
							</c:choose>
						</span>

					</div>
					<div class="goodslist">
						<ul class="clearfix">
							<c:forEach items="${pageResult.list}" var="goods">
								<li class="gd-hover">
									<div class="goods-img">
										<span class="fl list_img"> <c:if test="${!empty goods.img_path}">
												<a href="<%=basePath%>goods/${goods.goods_id}.action"> <img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,240,240)" /> </a>
											</c:if> </span>
									</div>
									<div class="goods-name">
										<a href="<%=basePath%>goods/${goods.goods_id}.action" class="product-h4" title="${goods.goods_name}"><b> <c:choose>
													<c:when test="${fn:length(goods.goods_name) > 35}">
														<c:out value="${fn:substring(goods.goods_name, 0, 35)}" />
													</c:when>
													<c:otherwise>
														<c:out value="${goods.goods_name}" />
													</c:otherwise>
												</c:choose> </b> </a>
									</div>
									<div class="goods-price">
										¥${goods.sale_price}
										<span>¥${goods.market_price}</span>
									</div>
								</li>
							</c:forEach>
						</ul>
						<div class="add_more margin-center">
							${pageBar}
						</div>
					</div>

				</div>
			</div>


			<div class="w1200">
				<div class="clearfix">
					<!--热销产品 热门推荐-->
					<div class="hotsell">
						<h2 class="hotsell-h2">
							<ul>
								<li class="hover">
									<a href="###">热销产品</a>
								</li>
							</ul>
						</h2>
						<div class="hotsell-cont">
						
							<c:forEach items="${saleGoods}" var="goods">
								<div class="hot-box">
									<span class="img-like"><a href="<%=basePath%>goods/${goods.goods_id}.action"> <img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,240,240)" /> </a> </span>
									<div class="ll">
										<p>
											<a href="<%=basePath%>goods/${goods.goods_id}.action" class="product-h4" title="${goods.goods_name}"><b> <c:choose>
													<c:when test="${fn:length(goods.goods_name) > 20}">
														<c:out value="${fn:substring(goods.goods_name, 0, 20)}" />
													</c:when>
													<c:otherwise>
														<c:out value="${goods.goods_name}" />
													</c:otherwise>
												</c:choose> </b> </a>
										</p>
										<div class="red">
											¥${goods.sale_price}
										</div>
									</div>
								</div>
							</c:forEach>	
						
						</div>
					</div>
				</div>
			</div>
			<input name="cat_id" value="${g.cat_id}" type="hidden" />
			<input name="label" value="${g.label}" type="hidden" />
			<input name="pg.sortCode" value="${g.pg.sortCode}" type="hidden" />
		</form>

		<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
		<script >
		 function deleteSearch(){
		 	 location.href="<%=basePath%>goodslist.action?cat_id=${g.cat_id}";
		 }
		 
		   //文本框限输入整数            
           $("input[id^='minPrice']").keyup(function () {
               var e = $(this).event || window.event;
               var code = parseInt(e.keyCode);
               if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {
                 
               } else{
               	$("#minPrice").val(0);
               }
           });
			//文本框限输入整数            
           $("input[id^='maxPrice']").keyup(function () {
               var e = $(this).event || window.event;
               var code = parseInt(e.keyCode);
               if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {
                 
               } else{
               	$("#maxPrice").val(199);
               }
           });
           
           //通过价格区间查找商品
           function searchGoodsByPrice(){
	           	var minPrice =$("#minPrice").val();
	           	var maxPrice =$("#maxPrice").val();
           		if(minPrice.length>0&&maxPrice.length>0){
           			location.href="<%=basePath%>goodslist.action?cat_id=${g.cat_id}&maxPrice="+maxPrice+"&minPrice="+minPrice;
           		}else{
           			alert("请输入查询价格!");
           		}
           }
           
           function findGoodsByPrice(minPrice,maxPrice){
          	 	location.href="<%=basePath%>goodslist.action?cat_id=${g.cat_id}&maxPrice="+maxPrice+"&minPrice="+minPrice;
           }
           
            // 按商品属性查找商品
            $("input[vType='rdattr_value']").bind("click", function () {
              	  var s=''; 
				  $('input[vType="rdattr_value"]:checked').each(function(){ 
				    s+=$(this).val()+';'; 
				  });
				  alert(s);
            });
		</script>
	</body>
</html>
