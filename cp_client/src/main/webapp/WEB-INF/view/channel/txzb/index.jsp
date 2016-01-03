<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/outapi/movie/tag.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String rootPath=path+"/inc/channel/txzb";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>天下珠宝首页</title>
    <link rel="stylesheet" href="<%=rootPath %>/css/index.css">
	<link rel="stylesheet" href="<%=rootPath %>/css/diamond.css">

	<script src="<%=basePath%>inc/js/jquery-1.8.0.min.js" type="text/javascript"></script>
	<script type="text/javascript">

	$.fn.imgRoll=function(options){
		var _this=$(this);
		var goodsPrev=_this.parent().find(options.prevCls);
		var goodsNext=_this.parent().find(options.nextCls);
		goodsPrev.click(function(){
			var spans=_this.find('span.tblw');
			var lastCls=_this.find('span:first').prop('class');
			if(lastCls.indexOf('tblw')>0) return false;
			spans.first().prev().addClass('tblw');
			spans.last().removeClass('tblw');
			return false;
		});
		goodsNext.click(function(){
			var spans=_this.find('span.tblw');
			var lastCls=_this.find('span:last').prop('class');
			if(lastCls.indexOf('tblw')>0) return false;
			spans.last().next().addClass('tblw');
			spans.first().removeClass('tblw');
			return false;
		});
	};
	
	
	$(document).ready(function(){
	
		$('#tbl-type').imgRoll({
			num: 4,
			margin: 0,
			prevCls: '.prev',
			nextCls: '.next'
		})
	
	});
	function switab(tab,con,c_css,n_css,no){
		$(tab).each(function(i) {
			if(i==no){
				//alert($(this).attr('class'));
				$(this).children('a').children('b').addClass(c_css);
			}
			else{
				$(this).children('a').children('b').removeClass(c_css);
				$(this).children('a').children('b').addClass(n_css);
			}
			if(con){
				$(con).each(function(i) {
					if(i==no){
						$(this).show();
					}
					else{
						$(this).hide();
					} 
	            });
			}
	    });
	}
	</script>
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
                	<c:forEach items="${ad1}" var="ad" >
	                  <li><a href="<c:url value="${ad.link_url}" />"><img src="${ad.img_path}" width="100%" /></a></li>
	                </c:forEach>
                </ul>
            </div>
            <ul class="scroll_position" style="left:48%;" id='scroll_position'>
              	<c:forEach items="${ad1}" var="ad" varStatus="loop" >
              		<li <c:if test="${loop.index == 0}">class="on"</c:if>><a href="javascript:void(0);">${loop.index+1}</a></li>
               	</c:forEach>
            </ul>
          </div>
          <!--scroll-->
        </article>
        <script src='<%=basePath %>inc/js/hhSwipe.js'></script>
    </div>
    
    <!--头部开始-->
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
                <a href="<%=basePath %>"><img class="full-img" src="<%=rootPath %>/images/logo.png"></a>	
            </div>
        </section>
    </header>
    
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
        
    <div class="module">    
        <section>
            <div class="titlst" id="example-2">
                
                <div class="tbl-type" id="tbl-type">         
                    <span class="tbl-cell titlst-spn tblw"><a href="###">黄金系列</a></span>
                    <span class="tbl-cell titlst-spn tblw"><a href="###">白金系列<b></b></a></span>
                    <span class="tbl-cell titlst-spn tblw"><a href="###">钻石系列<b></b></a></span>
                    <span class="tbl-cell titlst-spn tblw"><a href="###">翡翠系列<b></b></a></span>
                    <span class="tbl-cell titlst-spn"><a href="###">银饰品<b></b></a></span>
                    <span class="tbl-cell titlst-spn"><a href="###">其他<b></b></a></span>
                </div>
                <em class="prev"></em>
                <em class="next"></em>
            </div>
        </section>
    </div>
   
   <!--珠宝列表-->
   <div class="module" style="background-color:#f6f6f6;">
	        <section>
	            <div class="dmdlist" style="display:block;">
	            	<div class="tbl-type">
	                	<span class="tbl-cell w50">
	                        <a href="<c:url value="${ad2.link_url}" />">
	                            <img class="full-img" width="160" src="<c:url value="${ad2.img_path }" />">
	                            <div class="full-div dmd-name"><strong>手链</strong></div>
	                        </a>
	                    </span>
	                	<span class="tbl-cell w50">
	                        <a href="<c:url value="${ad3.link_url}" />">
	                            <img class="full-img" width="160" src="<c:url value="${ad3.img_path }" />">
	                            <div class="full-div dmd-name"><strong>戒指</strong></div>
	                        </a>
	                    </span>
					</div>
	                
	            	<div class="tbl-type">
	                	<span class="tbl-cell w50">
	                        <a href="<c:url value="${ad4.link_url}" />">
	                            <img class="full-img" width="160" src="<c:url value="${ad4.img_path }" />">
	                            <div class="full-div dmd-name"><strong>手链</strong></div>
	                        </a>
	                    </span>
	                	<span class="tbl-cell w50">
	                        <a href="<c:url value="${ad5.link_url}" />">
	                            <img class="full-img" width="160" src="<c:url value="${ad5.img_path }" />">
	                            <div class="full-div dmd-name"><strong>戒指</strong></div>
	                        </a>
	                    </span>
	
					</div>
	            </div>
	        </section>
	   </div>        
	</div>
	    

	<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
