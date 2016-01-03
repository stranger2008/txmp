<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <style type="text/css">
    	.selectpsd{ width:100%;font-size:14px; padding:4px 0;margin-top:5px;color:#666; border:1px solid #d7d7d7;}
    </style>
    <title>我的收货地址</title>
	<script type="text/javascript">	
    	//	
		$(function () {
		    $("#addBuyerAddr").click(function () {
		    	$.ajax({
					type: "get",
					async:false,
					url: "user/testBuyerAddrNum.action",
					success: function(data, textStatus){
						if(data > 9){
							alert("收货地址最多创建10个！");
						}else{
							location.href='<%=basePath %>user/addradd.action';
						}
					}
				});
		    });
		})
		//		
	</script>
  </head>
  
  <body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--头部下搜索框-->
<%@ include file="/WEB-INF/view/user/inc/top.jsp" %>		 
<!--nav开始-->
<%@ include file="/WEB-INF/view/user/inc/nav.jsp" %>
<!--个人中心-->
<div class="w1200">
	<div class="usercenter">
    	<div class="position">
        	<a href="#"><strong>我的天下名品</strong></a><span></span><a href="#">账户安全</a>
        </div>
        
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
        <div class="user-info fr">
                   <!--收货地址管理-->
            <div class="add-count">
                <h1>我的收货地址</h1>
                <p class="add-site">
                    <a href="javascript:void(0)" id="addBuyerAddr">新增收货地址</a>您已创建<span>${addrNum }</span>个收货地址，最多创建<span>10</span>个
                </p>
                <c:set var="addrNum"></c:set>
                <c:forEach items="${addrlist}" var="addr" varStatus="status">
                <c:set var="addrNum" value="${status.index+1}"></c:set>
                <div class="add-item">
                    <p class="add-name">${addr.cust_name}</p>
                    <dl class="add-msg">
                        <dt>
                            <span>收货人：</span>${addr.cust_name}<br/>
                            <span>所在地区：</span>${addr.area_name_str}<br/>
                            <span>详细地址：</span>${addr.address}<br/>
                            <span>手机：</span>${addr.cell_phone}
                        </dt>
                        <dd style="width:135px;">
                            <c:if test="${addr.is_default=='1'}"><em>当前默认地址</em><br/></c:if>
                            <a href="<%=basePath%>user/addredit-${addr.addr_id}.action">修改</a>&nbsp;|&nbsp;<a href="<%=basePath%>user/addrdel-${addr.addr_id}.action">删除</a>
                        </dd>
                    </dl>
                </div>
               </c:forEach>
            </div> 
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
