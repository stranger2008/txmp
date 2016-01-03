<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 	<div id="areas_frame" class="areas_frame" style="display:none;">
        <div class="areas_select" >
            <a href="javascript:void(0)" id="close6" class="closeBtn">关闭</a>
            <ul id="area_selected_ul"></ul>
        </div>
    </div>
	<div class="topnav" id="daohang2">
			<div class="w1200">
				<dl class="home-area fl">
					<dt>
						<b></b>
						<strong id="selected_area_name">
						<c:if test="${empty sessionScope.session_selected_area_name}">
		                		城市		                		
		                </c:if>
						<c:if test="${!empty sessionScope.session_selected_area_name}">
		                		${sessionScope.session_selected_area_name}		                		
		                </c:if>
						</strong>
						<a href="javascript:void(0)" id="turn_areas">[切换城市]</a>
					</dt>
					
			       
        
				</dl>
				<ul class="fl col">
					<li>
						<b></b><a href="###" class="col-a">收藏天下名品</a>
					</li>
				</ul>
				<ul class="fr topn-r">
					<li>
						您好！欢迎光临天下名品商城！
						<span> 
							<c:if test="${!empty sessionScope.session_user_name}">
		                		<a href="<%=basePath %>user/uccenter.action">${sessionScope.session_user_name}</a>
		                		<a href="<%=basePath %>logout.action">[退出]</a>
		                	</c:if>
		                	<c:if test="${empty sessionScope.session_user_name}">
		                		<a href="<%=basePath %>login.action">[登录]</a></span>
			                	<span>|</span>
			                	<span><a href="<%=basePath %>signup.action">[注册]</a>
		                	</c:if>
		                	
		                		
						</span>
					</li>
					<li>
						<s></s>
						<a href="<%=basePath %>user/orderlist.action">我的订单</a>
					</li>
					<li class="tab0 dropdown0">
						<s></s>
						<i></i>
						<a href="###" class="app tablink arwlink">天下名品APP</a>
						<b></b>

						<ul>
							<li class="pop0">
								<div class="code-p fl">
									<a href="###"><img src="<%=basePath %>inc/images/code-p.png">
									</a>
								</div>
								<div class="phone fl">
									<a href="#">手机客户端</a>
									<a href="###"><img src="<%=basePath %>inc/images/app-btn.png">
									</a>
									<a href="###"><img src="<%=basePath %>inc/images/and-btn.png">
									</a>
								</div>
							</li>
						</ul>

					</li>
					<li class="tab0 dropdown1">
						<s></s>
						<a href="###" class="tablink tablink">公告</a>
						<b></b>


						<ul>
							<li class="pop1">
								<a href="#">校园之星</a>
								<a href="#">校园之星</a>
								<a href="#">校园之星</a>
							</li>
						</ul>

					</li>
					<li class="tab0 dropdown2">
						<s></s>
						<a href="###" class="tablink tablink">网站导航</a>
						<b></b>

						<ul>
							<li class="pop2">
								<h3>
									<strong>特色栏目</strong>
								</h3>
								<div class="nav-ta">
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
									<a href="#">校园之星</a>
								</div>
							</li>
						</ul>

					</li>
				</ul>
			</div>
		</div>