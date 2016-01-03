<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/outapi/movie/tag.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
      
<link rel="stylesheet" href="<c:url value="/inc/outapi/movie/css/movie.css" />">
<script type="text/javascript" src="<c:url value="/inc/outapi/movie/js/jquery.min.js" />"></script>
<title>电影票-电影详细页</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/static/header.jsp" %>
<section>
	<div class="movie">
        <div class="movie-bg relative">
            <div class="movieDet clearfix">
                <div class="movieDet-img fl"><img class="full-img" src="<c:url value="/inc/outapi/movie/images/movieDet-img.jpg" />" /></div>
                <div class="movieDet-cont fl">
                    <h2>明日边缘</h2>
                    <ul>
                        <li>上映时间：2014.05.06</li>
                        <li>类型：言情｜历史</li>
                        <li>导演：道格·里曼</li>
                        <li>主演：汤姆·克鲁斯 | 艾米莉·布朗特
                                  | 诺亚·泰勒</li>
                    </ul>
                    <a href="<c:url value="/movie/static/cinema/list.action"/> " class="seat-btn">选座订票</a>
                </div>
            </div>
            <div class="bar"></div>
        </div>
        <div class="movieInfo">
        	<h2 class="movieInfo-tit"><span>电影剧情</span></h2>
            <div class="movieInfo-cont">
            	<p>华纳兄弟根据日本漫画《杀戮轮回》（All You Need Is Kill）改编的电影，原定邀请布拉德·皮特（Brad Pitt）主演，现在这部影片的名字定为《你我皆凡人》（We Are Mortals），同时还传汤姆·克鲁斯（Tom Cruise）正在商谈主演。<br />

　　《杀戮轮回》由日本作家樱坂洋（Hiroshi Sakurazaka）创作，故事就好比是“《土拨鼠日》遇上《星河舰队》”、也很像游戏：一种叫做“GITAI”的神秘生物袭击地球，士兵桐谷启二（Keiji Kiriya）加入与GITAI作战的统合防疫军。
首次出战就战死的他，由于某种不明原因，竟然重获生命，回到出战前的那天早晨。这种奇怪的现象不断重复，把桐谷卷入诡异的时间轮回。但当他轮回到第158次的时候，在战场上与一位女性重逢……他不知道这个女人究竟是让自己摆脱轮回的关键呢、还是带他走向最终的死亡？</p>
			<div class="" style="width:22px; height:15px; margin:10px auto;"><a href="#"><img src="<c:url value="/inc/outapi/movie/images/more.png" />" /></a></div>
            </div>	
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
