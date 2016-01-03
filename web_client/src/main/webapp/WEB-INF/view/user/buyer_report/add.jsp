<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>举报管理</title>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
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
            <div class="add-count">
                <h1>举报管理</h1>
				<div id="ImportBox">
					<sf:form method="post" action="user/add_buyer_report.action" modelAttribute="buyer_report">
					   <table width="100%" border="0" cellspacing="0" cellpadding="0">
					   	<tbody>
					   	  <tr>
					   	  	<td class="Su">被举报商家：</td><td colspan="2">
					   	  	<sf:hidden path="goods_id" value="${goods_id}"/>
					   	  	<a href="<%=basePath%>shop/${cust_id}.action" target="_blank">
							${cust_name}
    						</a></td>
    					 </tr>
    					  <tr>
    					  	<td class="Su">相关宝贝：</td><td colspan="2">
					   	  	<a id="baobeiURL" href="<%=basePath%>goods/${goods_id}.action" target="_blank">
							${goods_name}
							</a></td>
    					 </tr>
    					  <tr>
    					  	<td class="Su"><font color="#ff4545">*</font>举报类型：</td>
					   	  	<td colspan="2">
					   	  		<ul >
					   	  			<c:forEach items="${reportMap}" var="item">
						   	  			<li style="white-space:nowrap">
		    							  <c:choose>  
											  <c:when test="${item.key eq r_type_s }">
											      <input type="radio" name="r_type" value="${item.key}" checked="checked"/>${item.value}
											  </c:when>  
											  <c:otherwise>
											   	  <input type="radio" name="r_type" value="${item.key}"/>${item.value}
											  </c:otherwise>  
										  </c:choose>
	    							   </li>
    							    </c:forEach>
					   	  		</ul>
					   	  		<em><sf:errors path="r_type" cssClass="error" cssStyle="color:#ff4545;"/></em>
					   	  	</td>
    					 </tr>
    					 <tr>
                    		<td class="Su" valign="top" style="vertical-align:middle;"><font color="#ff4545">*</font>描述：</td>
                    		<td class="Su1">
    							<sf:textarea path="r_desc" cssStyle="width:450px;height:100px;" />
    							<em><sf:errors path="r_desc" cssClass="error" cssStyle="color:#ff4545;"/></em>
    						</td>
    						<td id="descr">请准确描述您的举报理由，以便客服及时作出判断。500汉字内</td>
    					</tr>
   					 	<tr>
			                <td class="Su">凭证：</td>
			                <td >
				                <input type="hidden" name="imgNumLimit" id="imgNumLimit" value="5"/>
								<sf:hidden path="img_evidence" />
								<input id="file_adv" name="upload_file" type="file"/>
								<em><sf:errors path="img_evidence" cssClass="error" cssStyle="color:#ff4545;"/></em>
								
								<div id="fileQueue"></div>
								<div class="img-lst"><ul id="imgView"></ul></div>
								<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
						        <script>
						        	$(function(){
						        		uploadImgComponent('<%=basePath%>','imgNumLimit','file_adv','fileQueue','img_evidence','imgView','buyer_complaint');
						        	})
						        </script>
			                </td>
			                <td>gif/jpg/jpeg格式，凭证3张以内</td>  
			            </tr>
    					<tr>
                    		<td colspan="3" align="center"><input value="提交" type=submit id="submitForm" /></td>
    					</tr>
					   	</tbody>
					   </table>
					</sf:form>
				</div>
				<div id="BottomBox" align="center">请客观地向天下名品反映您所遇到的真实情况，以共同维护一个诚信和公平的购物环境。</div>
            </div> 
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
