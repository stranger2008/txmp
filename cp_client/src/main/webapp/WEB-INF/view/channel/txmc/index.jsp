<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/outapi/movie/tag.jsp" %>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String rootPath=path+"/inc/channel/txmc";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="keywords" content="关键字">
<meta name="description" content="网站描述"> 

<meta name="viewport" content="width=device-width, initial-scale=1, target-densitydpi=high-dpi,
 maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link rel="stylesheet" href="<%=rootPath %>/css/index.css" />
	<script src="<%=rootPath %>/js/jquery.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="<%=rootPath %>/js/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/js/jquery.silver_track.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=rootPath %>/js/jquery.silver_track.navigator.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=rootPath %>/js/jquery.silver_track.bullet_navigator.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=rootPath %>/js/jquery.silver_track.responsive_hub_connector.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=rootPath %>/js/script.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=rootPath %>/js/example2_and_3.js" charset="utf-8"></script>



<script type="text/javascript">
$(document).ready(function(){
	$('#serv a').each(function(i) {
		$(this).click(function(){
			switab('#example-2 span','.tealist','on','',i);
			})
        
    });
	$('.item').each(function(){
		$(this).click(function(){
			$('.tealist').hide().eq($(this).index()).show();
		});
	});
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

<title>天下名茶</title>
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
                <a href="<%=basePath %>"><img class="full-img" src="<%=rootPath %>/images/logo-1.png"></a>	
            </div>
        </section>
    </header>
    
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
        
    <div class="module">    
        <section class="track">
            <div class="view-port piclst">
                <div class="slider-container tbl-type" id="example-2">
                    <div class="item">
                    <span class="tbl-cell piclst-spn"><a href="<c:url value="${ad2.link_url}" />"><img class="full-img" src="<c:url value="${ad2.img_path }" />" width="78"><div class="descri">茶道</div><b class="on"></b></a></span>
                    </div>
                    <div class="item">
                    <span class="tbl-cell piclst-spn"><a href="<c:url value="${ad3.link_url}" />"><img class="full-img" src="<c:url value="${ad3.img_path }" />" width="78"><div class="descri">茶具</div><b></b></a></span>
                    </div>
                    <div class="item">
                    <span class="tbl-cell piclst-spn"><a href="<c:url value="${ad4.link_url}" />"><img class="full-img" src="<c:url value="${ad4.img_path }" />" width="78"><div class="descri">茶旅</div><b></b></a></span>
                    </div>
                    <div class="item">
                    <span class="tbl-cell piclst-spn"><a href="<c:url value="${ad5.link_url}" />"><img class="full-img" src="<c:url value="${ad5.img_path }" />" width="78"><div class="descri">茶品</div><b></b></a></span>
                    </div>
                    <div class="item">
                    <span class="tbl-cell piclst-spn"><a href="<c:url value="${ad6.link_url}" />"><img class="full-img" src="<c:url value="${ad6.img_path }" />" width="78"><div class="descri">茶色</div><b class="on"></b></a></span>
                    </div>
                    <div class="item">
                    <span class="tbl-cell piclst-spn"><a href="<c:url value="${ad7.link_url}" />"><img class="full-img" src="<c:url value="${ad7.img_path }" />" width="78"><div class="descri">茶友</div><b></b></a></span>
                    </div>
                    <div class="item">
                    <span class="tbl-cell piclst-spn"><a href="<c:url value="${ad8.link_url}" />"><img class="full-img" src="<c:url value="${ad8.img_path }" />" width="78"><div class="descri">订制</div><b></b></a></span>
                    </div>
                    
                </div>
            </div>
            <div class="pagination">
              <a href="#" class="prev disabled"></a>
              <a href="#" class="next disabled"></a>
            </div>
        </section>
    </div>
   
   



   <!--茶叶列表-->
   <div class="module">
        <section>
            <div class="tealist" style="display:block;">
            	<ul>
            		<c:forEach items="${ad9}" var="ad" >
                	<li>
                        <a href="<c:url value="${ad.link_url}" />">
                            <img src="<c:url value="${ad.img_path }" />" width="103">
                            <div class="tealist-cont"><h3>${ad.title }</h3><p>${ad.adv_desc }</p></div>
                            <span class="arr-b2"></span>
                        </a>
                    </li>
                    </c:forEach>
				</ul>
            </div>
            
            <div class="tealist">
            	<ul>
            		<c:forEach items="${ad10}" var="ad" >
                	<li>
                        <a href="<c:url value="${ad.link_url}" />">
                            <img src="<c:url value="${ad.img_path }" />" width="103">
                            <div class="tealist-cont"><h3>${ad.title }</h3><p>${ad.adv_desc }</p></div>
                            <span class="arr-b2"></span>
                        </a>
                    </li>
                    </c:forEach>
				</ul>
            </div>
            
            <div class="tealist">
            	<ul>
            		<c:forEach items="${ad11}" var="ad" >
                	<li>
                        <a href="<c:url value="${ad.link_url}" />">
                            <img src="<c:url value="${ad.img_path }" />" width="103">
                            <div class="tealist-cont"><h3>${ad.title }</h3><p>${ad.adv_desc }</p></div>
                            <span class="arr-b2"></span>
                        </a>
                    </li>
                    </c:forEach>
				</ul>
            </div>
            
            <div class="tealist">
            	<ul>
            		<c:forEach items="${ad12}" var="ad" >
                	<li>
                        <a href="<c:url value="${ad.link_url}" />">
                            <img src="<c:url value="${ad.img_path }" />" width="103">
                            <div class="tealist-cont"><h3>${ad.title }</h3><p>${ad.adv_desc }</p></div>
                            <span class="arr-b2"></span>
                        </a>
                    </li>
                    </c:forEach>
				</ul>
            </div>
            
            <div class="tealist">
            	<ul>
            		<c:forEach items="${ad13}" var="ad" >
                	<li>
                        <a href="<c:url value="${ad.link_url}" />">
                            <img src="<c:url value="${ad.img_path }" />" width="103">
                            <div class="tealist-cont"><h3>${ad.title }</h3><p>${ad.adv_desc }</p></div>
                            <span class="arr-b2"></span>
                        </a>
                    </li>
                    </c:forEach>
				</ul>
            </div>
            
            <div class="tealist">
            	<ul>
            		<c:forEach items="${ad14}" var="ad" >
                	<li>
                        <a href="<c:url value="${ad.link_url}" />">
                            <img src="<c:url value="${ad.img_path }" />" width="103">
                            <div class="tealist-cont"><h3>${ad.title }</h3><p>${ad.adv_desc }</p></div>
                            <span class="arr-b2"></span>
                        </a>
                    </li>
                    </c:forEach>
				</ul>
            </div>
            
            <div class="tealist">
            	<ul>
            		<c:forEach items="${ad15}" var="ad" >
                	<li>
                        <a href="<c:url value="${ad.link_url}" />">
                            <img src="<c:url value="${ad.img_path }" />" width="103">
                            <div class="tealist-cont"><h3>${ad.title }</h3><p>${ad.adv_desc }</p></div>
                            <span class="arr-b2"></span>
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