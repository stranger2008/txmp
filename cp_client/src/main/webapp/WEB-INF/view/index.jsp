<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title><%=web_title %></title>
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
    
    <header class="module">
	    <section class="top margin-center">
	        <div class="top_a clearfix">
	            <a href="<%=basePath %>areaselect.action" class="loc fl">
	            	<c:if test="${!empty sessionScope.session_area_name}">
	               		${sessionScope.session_area_name}
	               	</c:if>
	               	<c:if test="${empty sessionScope.session_area_name}" >
	               		地区
	               	</c:if>
	            </a>
	            <a href="<%=basePath %>getapp.action" class="upl fr">APP</a>
	        </div>
	        <div class="logo_m margin-center">
	            <a href="<%=basePath %>"><img src="<%=basePath %>inc/images/logo_m.png"></a>	
	        </div>
	    </section>
	</header>
    
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    
    <!--内容开始-->
    <div class="module">
        <section>
            <div class="category margin-center">
                <ul class="category-menu tbl-type">    
                    <li class="tbl-cell"><a href="<%=basePath %>category.action" class="btn ass_btn"><span>分类</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>trainTieyou.action" class="btn mine_btn"><span>火车票</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>movie.action" class="btn tic_btn"><span>电影</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>order/qmfweb-phoneRecharge.action" class="btn recha_btn"><span>充值</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>order/qmfweb-water.action" class="btn pay_btn"><span>水费</span></a></li>
                </ul>
                <ul class="category-menu tbl-type">
                    <li class="tbl-cell"><a href="<%=basePath %>lottery/index.action" class="btn lot_btn"><span>彩票</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>airLine/searchairline.action" class="btn flight_btn"><span>机票</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>order/qmfweb-power.action" class="btn hotel_btn"><span>电费</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>order/qmfweb-creditCard.action" class="btn repay_btn"><span>还款</span></a></li>
                    <li class="tbl-cell"><a href="<%=basePath %>order/qmfweb-main.action" class="btn more_btn"><span>更多</span></a></li>
                </ul>
            </div>
        </section>
    </div>
    
    <div class="module">    
        <section>
            <div class="seller_adv clearfix">
				<ul class="tbl-type">
                    <li class="tbl-cell w33"><a href="${advlist152[0].link_url}"><img class="full-img" src="${advlist152[0].img_path}" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="${advlist152[1].link_url}"><img class="full-img" src="${advlist152[1].img_path}" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="${advlist152[2].link_url}"><img class="full-img" src="${advlist152[2].img_path}" width="106"></a></li>
                </ul>
				<ul class="tbl-type">
                    <li class="tbl-cell w33"><a href="${advlist152[3].link_url}"><img class="full-img" src="${advlist152[3].img_path}" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="${advlist152[4].link_url}"><img class="full-img" src="${advlist152[4].img_path}" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="${advlist152[5].link_url}"><img class="full-img" src="${advlist152[5].img_path}" width="106"></a></li>
                </ul>
            </div>
        </section>
    </div>
    
   
   
   <!--新品上架-->
   <div class="module">
        <section>
            <div class="newpro gray_bg margin-center">
                <h2 class="clearfix"><span class="title fl">新品上架</span><a href="<%=basePath %>goodslist.action?label=2" class="more fr"></a></h2>
				<table width="100%" cellpadding="0" cellspacing="4">
                    <tr>
                        <td>
                        	<c:forEach items="${advlist153}" var="adv">
                        		<a href="${adv.link_url}" class="newpro-l"><img class="full-img" src="${adv.img_path}"></a>
                        	</c:forEach>
                        </td>
                        <td>
                        	<c:forEach items="${advlist154}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r"><img src="${adv.img_path}" class="full-img"></a>
                            </c:forEach>
                            <c:forEach items="${advlist155}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r pos-b"><img src="${adv.img_path}" class="full-img"></a>
                            </c:forEach>
                        </td>
                    </tr>
				</table>
            </div>
        </section>
   </div>        

   <!--超级划算-->
   <div class="module">
        <section>
            <div class="newpro margin-center">
                <h2 class="clearfix"><span class="title fl">超级划算</span><a href="<%=basePath %>goodslist.action?label=0" class="more fr"></a></h2>
               <table width="100%" cellpadding="0" cellspacing="4">
                    <tr>
                        <td>
                        	<c:forEach items="${advlist156}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r"><img src="${adv.img_path}" class="full-img"></a>
                            </c:forEach>
                            <c:forEach items="${advlist157}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r pos-b"><img src="${adv.img_path}" class="full-img"></a>
                        	</c:forEach>
                        </td>
                        <td>
                        	<c:forEach items="${advlist158}" var="adv">
                        		<a href="${adv.link_url}" class="newpro-l"><img class="full-img" src="${adv.img_path}"></a>
                        	</c:forEach>
                        </td>
                    </tr>
				</table>
            </div>
        </section>
   </div>        

   <!--持续热卖-->
   <div class="module">
        <section>
            <div class="newpro gray_bg margin-center">
                <h2 class="clearfix"><span class="title fl">持续热卖</span><a href="<%=basePath %>goodslist.action?label=1" class="more fr"></a></h2>
                <table width="100%" cellpadding="0" cellspacing="4">
                    <tr>
                        <td>
                        	<c:forEach items="${advlist159}" var="adv">
                        		<a href="${adv.link_url}" class="newpro-l"><img class="full-img" src="${adv.img_path}"></a>
                        	</c:forEach>
                        </td>
                        <td>
                        	<c:forEach items="${advlist160}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r"><img src="${adv.img_path}" class="full-img"></a>
                            </c:forEach>
	                    	<c:forEach items="${advlist161}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r pos-b"><img src="${adv.img_path}" class="full-img"></a>
                        	</c:forEach>
                        </td>
                    </tr>
				</table>
            </div>
        </section>
   </div>        


   <!--强力推荐-->
   <div class="module">
        <section>
            <div class="newpro margin-center">
                <h2 class="clearfix"><span class="title fl">强力推荐</span><a href="<%=basePath %>goodslist.action?label=2" class="more fr"></a></h2>
                <table width="100%" cellpadding="0" cellspacing="4">
                    <tr>
                        <td>
                        	<c:forEach items="${advlist163}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r"><img src="${adv.img_path}" class="full-img"></a>
                            </c:forEach>
	                    	<c:forEach items="${advlist164}" var="adv">
                            	<a href="${adv.link_url}" class="newpro-r pos-b"><img src="${adv.img_path}" class="full-img"></a>
                        	</c:forEach>
                        </td>
                        <td>
                        	<c:forEach items="${advlist162}" var="adv">
                        	<a href="${adv.link_url}" class="newpro-l"><img class="full-img" src="${adv.img_path}"></a>
                        	</c:forEach>
                        </td>
                    </tr>
				</table>
            </div>
        </section>
   </div>        



	<!--热销排行-->
   <div class="module">
        <section>
            <div class="assess">
                <h2 class="title">热销排行</h2>
                <ul>
                
                	<c:forEach items="${pageResult.list}" var="goods">
                	
                    <li class="tbl-type">
                        <div class="tbl-cell">
                        	<c:if test="${!empty goods.img_path}">
	                   			<a href="<%=basePath %>goods/${goods.goods_id}.action" class="ass_l">
	                    			<img class="full-img" src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,90,90)" width="258"/>
	                    		</a>
	                   		</c:if>
                        </div>
                        <div class="tbl-cell ass_r">
                            <p><a href="<%=basePath %>goods/${goods.goods_id}.action" class="buyer">${goods.goods_name}</a></p>
                            <p class="goods-intro">${goods.goods_desc}</p>
                            <p class="goods-price">￥${goods.sale_price}</p>
                        </div>
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
