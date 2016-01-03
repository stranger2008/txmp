<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>地区子站-<%=web_title %></title>
</head>

<body>


<div class="margin-center" style="max-width:640px;">

    <!--banner开始-->
    <div class="banner margin-center">
        <article>
          <!--scroll-->
          <div class="scroll relative">
            <div class="scroll_box" id="scroll_img">
                <ul class="scroll_wrap">
                  <c:forEach items="${advlist151}" var="adv">
                  <li><a href="${adv.link_url}"><img src="${adv.img_path}" width="100%" /></a></li>
                  </c:forEach>
                </ul>
            </div>
            <ul class="scroll_position" style="left:48%;" id='scroll_position'>
              <c:forEach items="${advlist151}" var="adv" varStatus="status">
              <li<c:if test="${status.index+1 == 1}"> class="on"</c:if>><a href="javascript:void(0);">${status.index+1}</a></li>
              </c:forEach>
            </ul>
          </div>
          <!--scroll-->
        </article>
        <script src='<%=basePath %>inc/js/hhSwipe.js'></script>
    </div>
    
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    
    <!--内容开始-->
    <div class="module">
        <section>
            <div class="category margin-center">
                <ul class="category-menu tbl-type">                       
                    <li class="tbl-cell"><a href="<%=basePath %>category.action" class="btn ass_btn"><span>分类</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>user/uccenter.action" class="btn mine_btn"><span>我的</span></a></li>
                    <li class="tbl-cell"><a href="#" class="btn tic_btn"><span>票务</span></a></li>
                    <li class="tbl-cell"><a href="#" class="btn recha_btn"><span>充值</span></a></li>
                    <li class="tbl-cell"><a href="#" class="btn pay_btn"><span>缴费</span></a></li>
                </ul>
                <ul class="category-menu tbl-type">
                    <li class="tbl-cell"><a href="#" class="btn lot_btn"><span>彩票</span></a></li>
                    <li class="tbl-cell"><a href="#" class="btn flight_btn"><span>机票</span></a></li>
                    <li class="tbl-cell"><a href="#" class="btn hotel_btn"><span>酒店</span></a></li>
                    <li class="tbl-cell"><a href="#" class="btn repay_btn"><span>还款</span></a></li>
                    <li class="tbl-cell"><a href="#" class="btn more_btn"><span>更多</span></a></li>
                </ul>
            </div>
        </section>
    </div>
    
    
    
    
    
    <!--新品上架-->
       <div class="module">
            <section>
                <div class="newpro pd7 gray_bg">
                	<h2 class="clearfix"><span class="title fl">新品上架</span><a href="<%=basePath %>goodslist.action?label=2" class="more fr"></a></h2>
                    <ul class="all-goods-lst clearfix">
                    
                    	<c:forEach items="${pageResult1.list}" var="goods">
	                        
	                        <li>
	                            <a href="<%=basePath %>goods/${goods.goods_id}.action" class=" ">
	                                <span class="goods-area-img">
	                                	<c:if test="${!empty goods.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,140,140)"/>
		                        		</c:if>
	                                </span>
	                                <div class="img-box">
	                                    <p>${goods.goods_name}</p>
	                                    <span class="red-pr">¥${goods.sale_price}</span>
	                                </div>
	                            </a>
	                        </li>
	                        
                        </c:forEach>
                        
                    </ul>
                </div>
            </section>
       </div>        

       <!--超级划算-->
       <div class="module">
            <section>
                <div class="newpro pd7">
                	<h2 class="clearfix"><span class="title fl">超级划算</span><a href="<%=basePath %>goodslist.action?label=0" class="more fr"></a></h2>
                    <ul class="all-goods-lst clearfix">
                        <c:forEach items="${pageResult2.list}" var="goods">
	                        
	                        <li>
	                            <a href="<%=basePath %>goods/${goods.goods_id}.action" class=" ">
	                                <span class="goods-area-img">
	                                	<c:if test="${!empty goods.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,140,140)"/>
		                        		</c:if>
	                                </span>
	                                <div class="img-box">
	                                    <p>${goods.goods_name}</p>
	                                    <span class="red-pr">¥${goods.sale_price}</span>
	                                </div>
	                            </a>
	                        </li>
	                        
                        </c:forEach>
                    </ul>

                </div>
            </section>
       </div>        


       <!--持续热卖-->
       <div class="module">
            <section>
                <div class="newpro pd7 gray_bg">
                	<h2 class="clearfix"><span class="title fl">持续热卖</span><a href="<%=basePath %>goodslist.action?label=1" class="more fr"></a></h2>
                    <ul class="all-goods-lst clearfix">
                        <c:forEach items="${pageResult3.list}" var="goods">
	                        
	                        <li>
	                            <a href="<%=basePath %>goods/${goods.goods_id}.action" class=" ">
	                                <span class="goods-area-img">
	                                	<c:if test="${!empty goods.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,140,140)"/>
		                        		</c:if>
	                                </span>
	                                <div class="img-box">
	                                    <p>${goods.goods_name}</p>
	                                    <span class="red-pr">¥${goods.sale_price}</span>
	                                </div>
	                            </a>
	                        </li>
	                        
                        </c:forEach>
                    </ul>
                </div>
            </section>
       </div>        


       <!--强力推荐-->
       <div class="module">
            <section>
                <div class="newpro pd7">
                	<h2 class="clearfix"><span class="title fl">强力推荐</span><a href="<%=basePath %>goodslist.action?label=3" class="more fr"></a></h2>
                    <ul class="all-goods-lst clearfix">
                        <c:forEach items="${pageResult4.list}" var="goods">
	                        
	                        <li>
	                            <a href="<%=basePath %>goods/${goods.goods_id}.action" class=" ">
	                                <span class="goods-area-img">
	                                	<c:if test="${!empty goods.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,140,140)"/>
		                        		</c:if>
	                                </span>
	                                <div class="img-box">
	                                    <p>${goods.goods_name}</p>
	                                    <span class="red-pr">¥${goods.sale_price}</span>
	                                </div>
	                            </a>
	                        </li>
	                        
                        </c:forEach>
                    </ul>
                </div>
            </section>
       </div>        
    
    
</div>
    
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    
</body>
</html>
