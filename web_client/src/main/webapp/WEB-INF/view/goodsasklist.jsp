<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品咨询-<%=web_title %></title>
</head>

<body>
    
   <%@ include file="/WEB-INF/view/inc/top.jsp" %>
   
   <!--当前位置开始-->
	<div class="module">
	    <section>
	        <div class="position mt5" style="display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;overflow:hidden;">
	            <a href="<%=basePath %>">首页</a> 
	            > 
	            <a href="javascript:void(0);">购买咨询</a> 
	            > 
	            <a href="<%=basePath %>goods/${goods.goods_id}.action">${goods.goods_name}</a>
	        </div>
	    </section>
	</div>
	
	
	<div class="module">
    <section>
    	<div class="mt5">
            <div class="ask-center">
                <div class="ask-section">
                
                <form action="gasklist.action" method="get">
                
                	<c:forEach items="${pageResult.list}" var="gask">
	                   	<a href="javascript:void(0);">
	                    	<ul class="ask-ul">
	                            <li class="ask-tit">
	                                <span>问：</span><span>${gask.c_content }</span>
	                                <br />
	                                <span class="gray">${gask.user_name }</span><span class="gray fr">${gask.c_date }</span>
	                            </li>
	                            <c:if test="${!empty gask.re_content }">
	                            	<li class="answer">
		                                <span>答：</span><span>${gask.re_content }</span>
		                            </li>
	                            </c:if>
	                        </ul>
	                    </a>
	                </c:forEach>
	                
	                <c:if test="${empty pageResult.list}">
	                	暂无咨询
	                </c:if>
	                
	                <c:if test="${!empty pageResult.list}">
	                	${pageBar }
	                </c:if>
	                
	                <input type="hidden" name="goods_id" value="${goods.goods_id }"/>
	            </form>
	            
	            <c:if test="${!empty goods.goods_id}">
	                
	                <br/>
	                
	                <div class="question">
	                    <div class="question-cont">
	                        <div class="txt-area">
	                            <textarea cols="10" rows="3" name="c_content" id="c_content" placeholder="请您输入问题描述"></textarea>
	                        </div>
	                        <div class="tbl-type mt10">
	                            <span class="tbl-cell w100"><span class="ipt-span"><input type="text" name="check_code" id="check_code" placeholder="请输入验证码" class="cd-ipt" /></span></span>
	                            <span class="tbl-cell">
	                                <%@ include file="/WEB-INF/view/inc/imgcode.jsp" %>
	                            </span>
	                        </div>
	                        <div><input type="button" id="goodsAskSub" class="sign_btn" value="提交"></div>
	                    </div>
	                </div>
	                
	                <script>
	                	$("#goodsAskSub").click(function(){
	                		$.ajax({
								type: "post",
								url: "<%=basePath %>goodsaskadd.action",
								data: {c_content:$("#c_content").val(),cust_id:"${goods.cust_id }",goods_id:"${goods.goods_id}",check_code:$("#check_code").val()},
								success: function(data, textStatus){
									if(data == "2"){
										alert("请登录后留言");
										window.location.href='<%=basePath %>login.action?redirect_page='+window.location.href;
									}else if(data == "3"){
										alert("请输入留言内容");
										$("#c_content").focus();
									}else if(data == "4"){
										alert("请输入验证码");
										$("#check_code").focus();
									}else if(data == "5"){
										alert("验证码输入不正确");
									}else{
										alert("评论成功");
										window.location.reload();
									}
								}
							});
	                	});
	                </script>
	                
	            </c:if>
                
                </div>
    
            </div>
        </div>       
    </section>
</div>
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
