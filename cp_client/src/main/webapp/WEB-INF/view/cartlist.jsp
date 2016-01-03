<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>购物车-<%=web_title %></title>
<script type="text/javascript" src="<%=basePath%>inc/goods_js/cartInfo.js"></script>
</head>

<body>
    
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    
<c:if test="${!empty cartlist}">
    
<div class="module">
    <section>
    	<div class="">
            <div class="cart mt10 mt5">
                <div class="cart-section">
                    <div class="my-cart">
                        <span><input type="checkbox" class="ipt-chk" id="iptCheckbox" checked /></span>
                        <span class="num-red">共<span id="tatalGoods">${cartsize }</span>件商品</span>
                        <span class="fr buy-btn">
                        	<a href="<%=basePath %>cartclear.action">清空购物车</a>
                            <span>|</span>
                            <a href="<%=basePath %>">继续购买</a>
                            <span>|</span>
                            <a href="javascript:goOrder();">去结算</a>
                        </span>
                    </div>
                    <div class="list_cont mt5">
                    <ul>
                    
                    	<c:forEach items="${cartlist}" var="item" varStatus="status">
						 
	                        <li style="overflow:visible;">
	                        	<div class="clearfix">
	                                <span class="cart-chk"><input type="checkbox"  id="ch_${status.index}"  name="itemCheck" value="${item.hash_code}" checked/></span>
	                                <span class="fl cart-img">
	                                	<a href="<%=basePath %>goods/${item.goods.goods_id}.action">
	                                		<c:if test="${!empty item.goods.img_path}">
				                        		<img src="<h:imgSubstr imgpath="${item.goods.img_path}"/>" onload="DrawImage(this,90,90)"/>
			                        		</c:if>
	                                	</a>
	                                </span>
	                                
	                                <div class="product_info">
	                                    <a href="<%=basePath %>goods/${item.goods.goods_id}.action" class="product-h4"><b>${item.goods.goods_name}${item.goods_param }</b></a>
	                                    <div>
	                                        <!-- <span class="cart-id">编号：<em>1105758342</em></span> -->
	                                        <span class="cart-price">价格：<em id="itemprice${status.index}">￥${item.goods.sale_price}</em></span>
	                                    </div>
	                                    <span>
	                                    	数量：<button type="button" class="cart-add" onclick="setGoodsNum(-1,${status.index},'${item.hash_code}')"/>-</button>
	                                    	<input type="text" class="cart-num" maxlength="4" value="${item.amount}" id="goodsOneNum${status.index}" onkeyup="setGoodsNum(0,${status.index},'${item.hash_code}');"/>
	                                    	<button type="button" class="cart-add" onclick="setGoodsNum(1,${status.index},'${item.hash_code}')">+</button>
	                                    </span>
	                                </div>
	                            </div>
	                            <p class="clt-btn"><a href="<%=basePath %>cartdel.action?id=${item.goods.goods_id}&hashcode=${item.hash_code}" class="clt-del">删除</a></p>
	                        </li>
						 
						</c:forEach>
						
						<script>
							function goOrder(){
								var hashVal = "";
								$("input[name='itemCheck']").each(function(){
									if($(this).attr("checked") == true){
										hashVal = hashVal + $(this).val()+",";
									}
								});
								if(hashVal == ""){
									alert("请选择商品再结算");
								}else{
									window.location.href="<%=basePath %>order/order.action?hashcode="+hashVal;
								}
							}
						
						
							function setGoodsNum(flag,indx,hashcode){
								var gnum = $("#goodsOneNum"+indx).val()*1;
								if(flag){
									var goodsNum =${cartsize}
									if(gnum*1 > 1000){
										gnum = 1000;
									}
									if(flag == -1){
										if(gnum > 1){
											--gnum;
										}
									}else{
										if(gnum < 1000){
											++gnum;
										}
									}
								} 
								$("#goodsOneNum"+indx).val(gnum);
								
								$.ajax({
									type: "get",
									url: "<%=basePath%>cartedit.action?hashcode="+hashcode+"&num="+gnum+"&rdm="+Math.random(),
									success: function(data, textStatus){
										var ck = $("#ch_"+indx).attr("checked");
										if(ck){
											var result = eval("("+data+")");
											$("#goodsTotalPrice").html(formatCurrency(result.tatalPrice));
											$("#tatalGoods").html(result.totalNum);
											$("#goodsTotalPrice_inp").val(result.tatalPrice);
										}
									}
								});
							}
						</script>
                 
                    </ul>
                    </div>
                    <div class="pay-tip">
                    	<!-- <p>原始金额：￥<span>4410.0</span>返现：￥<span>50.0</span></p> -->
                    	<input id="goodsTotalPrice_inp" type="hidden" value="${goodsTotalPrice }">
                    	<p>商品总价：<span class="red-pri" id="goodsTotalPrice">
                    	<!-- ￥${goodsTotalPrice}-->
                    	<fmt:formatNumber type="number" pattern="￥0.00" value="${goodsTotalPrice}" maxFractionDigits="2"/> 
                    	</span></p>
                    </div>
                    <div class="go-pay"><input type="button" class="sign_btn" onclick="goOrder();" value="去结算"></div>
                </div>
				
            </div>
        </div>
    </section>
</div>

</c:if>

<c:if test="${empty cartlist}">

<div class="emptyCart margin-center">
	<span class="emptyCart-tip">购物车空的，赶紧去购物吧</span>
    <div class="go-pay"><input type="button" class="sign_btn" value="去购物" onclick="window.location.href='<%=basePath %>';"></div>
</div>

</c:if>

    
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    

</body>
</html>
