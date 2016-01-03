<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全中心</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=rootPath %>css/public.css" />
<link rel="stylesheet" href="<%=rootPath %>css/product.css" />
<link rel="stylesheet" href="<%=rootPath %>css/account.css" />
<script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
<script src="<%=rootPath %>js/core.js"></script>

</head>

<body>
	<!--头部开始-->
	<%@ include file="/WEB-INF/view/inc/top.jsp"%>
	<!--头部下搜索框-->
	<%@ include file="/WEB-INF/view/user/inc/top.jsp"%>
	<!--nav开始-->
	<%@ include file="/WEB-INF/view/user/inc/nav.jsp"%>

	<!--个人中心-->
	<div class="w1200">
		<div class="usercenter">
			<div class="position">
				<a href="#"><strong>安全中心</strong></a><span></span><a href="#">安全中心</a>
			</div>
			<!--会员中心左边导航-->
			<%@ include file="/WEB-INF/view/user/inc/left.jsp"%>
			<div class="user-info fr">
        	<div class="user-info fr">
        	 <div class="user-info fr">
        	<div class="upd-psd">
            	<h2>修改绑定手机</h2>
                <div class="step step01">
                	<img src="<%=basePath %>inc/images/step3-psd.png">
                	<ul>
                    	<li class="fore">身份验证</li>
                    	<li class="fore">修改绑定手机</li>
                    	<li class="fore-h">完成</li>
                    </ul>
                </div>
                
                <div class="safe-serv">
                	<div class="form-psd2">
                    <p class=" "><b></b>恭喜您，绑定手机修改成功！</p>
                    <a href="<%=basePath %>user/uccenter.action" class=" submit">返回</a>
                    </div>
                </div>
            </div>
        </div>
	</div>

        </div>
	</div>

        </div>
	</div>

		</div>
	</div>
	</div>

	<div class="clearfix"></div>
	<!--底部-->
	<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
</body>
</html>
