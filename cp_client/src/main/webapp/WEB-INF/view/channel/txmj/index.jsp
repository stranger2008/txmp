<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/outapi/movie/tag.jsp" %>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet" href="<c:url value="/inc/channel/txmj/css/index.css" />">
<script type="text/javascript" src="<c:url value="/inc/channel/txmj/js/jquery.min.js" />"></script>
<title>天下名酒</title>
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
        <script src='<c:url value="/inc/channel/txmj/js/hhSwipe.js" />'></script>
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
	            <a href="<%=basePath %>"><img src="<c:url value="/inc/channel/txmj/images/logo_w.png" />"></a>	
	        </div>
	    </section>
	</header>
    
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    <div class="module">    
        <section>
            <div class="seller_adv clearfix">
            	<ul class="tbl-type">
                    <li class="tbl-cell w33"><a href="<c:url value="${ad2.link_url}" />"><img class="full-img" src="${ad2.img_path }" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="<c:url value="${ad3.link_url}" />"><img class="full-img" src="${ad3.img_path }" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="<c:url value="${ad4.link_url}" />"><img class="full-img" src="${ad4.img_path }" width="106"></a></li>
                </ul>
            	<ul class="tbl-type">
                    <li class="tbl-cell w33"><a href="<c:url value="${ad5.link_url}" />"><img class="full-img" src="${ad5.img_path }" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="<c:url value="${ad6.link_url}" />"><img class="full-img" src="${ad6.img_path }" width="106"></a></li>
                    <li class="tbl-cell w33"><a href="<c:url value="${ad7.link_url}" />"><img class="full-img" src="${ad7.img_path }" width="106"></a></li>
                </ul>
            </div>
        </section>
    </div>
   
   
   <!--新品上架-->
   <div class="module">
        <section>
            <div class="newpro gray_bg margin-center">
                <h2 class="clearfix"><span class="title fl">新品上架</span></h2>
                <div class="tbl-type">
                    <div class="tbl-cell w50"><a href="<c:url value="${ad8.link_url}" />" class="newpro-l ml10 mr4"><img class="full-img" src="${ad8.img_path }" width="151"></a></div>
                    <div class="tbl-cell w50">
                        <a href="<c:url value="${ad9.link_url}" />" class="newpro-r mr10"><img src="${ad9.img_path }" class="full-img" width="151"></a>
                        <a href="<c:url value="${ad10.link_url}" />" class="newpro-r mr10 pos-b"><img src="${ad10.img_path }" class="full-img"></a>
                    </div>
                </div>
            </div>
        </section>
   </div>        

   <!--超级划算-->
   <div class="module">
        <section>
            <div class="newpro margin-center">
                <h2 class="clearfix"><span class="title fl">超级划算</span></h2>
                <div class="tbl-type">
                    <div class="tbl-cell w50">
                        <a href="<c:url value="${ad11.link_url}" />" class="newpro-r ml10 mr4"><img src="${ad11.img_path }" class="full-img" width="151"></a>
                        <a href="<c:url value="${ad12.link_url}" />" class="newpro-r ml10 mr4 pos-b"><img src="${ad12.img_path }" class="full-img" width="151"></a>
                    </div>
                    <div class="tbl-cell w50"><a href="<c:url value="${ad13.link_url}" />" class="newpro-l mr10"><img class="full-img" src="${ad13.img_path }" width="151"></a></div>
                </div>
            </div>
        </section>
   </div>        

   <!--持续热卖-->
   <div class="module">
        <section>
            <div class="newpro gray_bg margin-center">
                <h2 class="clearfix"><span class="title fl">持续热卖</span></h2>
                <div class="tbl-type">
                    <div class="tbl-cell w50"><a href="<c:url value="${ad14.link_url}" />" class="newpro-l ml10 mr4"><img class="full-img" src="${ad14.img_path }" width="160"></a></div>
                    <div class="tbl-cell w50">
                        <a href="<c:url value="${ad15.link_url}" />" class="newpro-r mr10"><img src="${ad15.img_path }" class="full-img" width="160"></a>
                        <a href="<c:url value="${ad16.link_url}" />" class="newpro-r mr10 pos-b"><img src="${ad16.img_path }" class="full-img" width="160"></a>
                    </div>
                </div>
            </div>
        </section>
   </div>        


   <!--强力推荐-->
   <div class="module">
        <section>
            <div class="newpro margin-center">
                <h2 class="clearfix"><span class="title fl">强力推荐</span></h2>
                <div class="tbl-type">
                    <div class="tbl-cell w50">
                        <a href="<c:url value="${ad17.link_url}" />" class="newpro-r ml10 mr4"><img src="${ad17.img_path }" class="full-img" width="160"></a>
                        <a href="<c:url value="${ad18.link_url}" />" class="newpro-r ml10 mr4 pos-b"><img src="${ad18.img_path }" class="full-img" width="160"></a>
                    </div>
                    <div class="tbl-cell w50"><a href="<c:url value="${ad19.link_url}" />" class="newpro-l mr10"><img class="full-img" src="${ad19.img_path }" width="160"></a></div>
                </div>
            </div>
        </section>
   </div>        
    
</div>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
