<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>用户中心</title>
</head>

<body>

    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
        
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="<%=basePath %>user/goodsReturn_list.action">退换货</a>
                </div>
            </section>
        </div>
        
   
   <div class="module">
    <section>
    
    	<sf:form method="post" action="user/goodsReturn_refund.action" modelAttribute="goodsreturn">
    
    	<div class="cont mt5">
            <div class="order">
                <div class="order-section">
                    <div class="order-cont">
                        <ul class="orderDet-ul">
                            <li>
                            	<div class="orderDet-box">
                                    <p class="orderDet-cw">
                                    	<span>订单编号：${orderdetailMap.order_id }</span>
                                    </p>
                                </div>
                            </li>
                            <li>
                            	<div class="orderDet-box gray_bg">
                            	
	                            		<div class="clearfix" style="margin-bottom:3px;">
	                                        <span class="fl list_img">
	                                        	<c:if test="${!empty orderdetailMap.img_path}">
				                        			<a href="<%=basePath %>goods/${orderdetailMap.goods_id}.action" target="_blank">
					                        			<img src="<h:imgSubstr imgpath="${orderdetailMap.img_path}"/>" onload="DrawImage(this,90,90)"/>
					                        		</a>
				                        		</c:if>
	                                        </span>
	                                        <div class="product_info">
	                                            <a href="<%=basePath %>goods/${orderdetailMap.goods_id}.action" target="_blank" class="product-h4"><b>${orderdetailMap.goods_name}</b></a>
	                                            <span class="cart-price">价格：<em>￥${orderdetailMap.order_price}</em></span><br/>
	                                            <span class="cart-price">数量：<em>${orderdetailMap.order_num}</em></span>
	                                        </div>
	                                    </div>
                            	
                                    
                                </div>
                            </li>
                            <li>
                            	<div class="orderDet-box">
                                    <p>问题描述（必填）</p>
                                    <p>
                                    	<sf:textarea path="cont_desc" cols="10" rows="3" placeHolder="请输入问题描述" cssStyle="width:100%;padding:5px;"/> 
                                    	<sf:errors path="cont_desc" cssClass="error" />
                                    </p>
                                    <p>上传图片（不超过3张）</p>
                                    <p>
                                    	<%@ include file="/inc/com/uploadimg/uploadimg.jsp" %>
                                    	<input id="fileBtn" type="button" name="fileBtn" value="选择图片" />
										<input id="file1" type="file" name="file" multiple="multiple" style="display:none;" />
										<div class="uploadimg_img-lst" id="uploadPreview">
								            <ul></ul>
								        </div>
								        <sf:hidden path="img_path" id="img_path"/>
								        <script>
								        	uploadImg("3","fileBtn","file1","goodsreturn","uploadPreview","img_path");
								        </script>
                                    </p>
                             	</div>
                            </li>
							<li>
								<sf:hidden path="trade_id" value="${orderdetailMap.trade_id}"/>
								<sf:hidden path="biz_type" value="2"/><!-- 退款 -->
								<sf:hidden path="order_id" value="${orderdetailMap.order_id}"/>
								<sf:hidden path="goods_id" value="${orderdetailMap.goods_id}"/>
								<input type="submit" class="sign_btn" value="提交">
							</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        </sf:form>
    </section>
</div>
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>

    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
<script src="<%=basePath %>inc/js/imgmgr.js"></script>
