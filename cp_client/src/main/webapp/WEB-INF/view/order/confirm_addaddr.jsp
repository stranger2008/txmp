<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>添加收货地址</title>
</head>

<body>

    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">提交订单</a> > <a href="javascript:void(0);">添加收货地址</a>
                </div>
            </section>
        </div>
        
    	<!--内容开始-->
        <div class="module">
        	<section class="box_reg margin-center">
                <div id="reg_cont" class="my">
                
                	<style>	
                		.sign_span{
                			margin-top:0px;
                		}
                	</style>
                	
                
                	 <sf:form method="post" action="order/confirm_addrinsert.action" modelAttribute="buyeraddr">
	                	<label><span class="sign_span">姓名</span></label>
	                	<label><sf:input path="cust_name" cssClass="psd"/></label>
	                	<sf:errors path="cust_name" cssClass="error"/>
	                	
	                	<label><span class="sign_span">地区</span></label>
	                	
	                	<span id="selectDivModel"></span>
						<script type="text/javascript">
							showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","");
							$("select[name='select_area_attr']").addClass("selectpsd");
						</script>
						
	                	<sf:errors path="area_attr" cssClass="error"/>
	                	
	                	<label><span class="sign_span">详细地址</span></label>
	                	<label><sf:input path="address" cssClass="psd"/></label>
	                	<sf:errors path="address" cssClass="error"/>
	                	
	                	<label><span class="sign_span">手机</span></label>
	                	<label><sf:input path="cell_phone" cssClass="psd"/></label>
	                	<sf:errors path="cell_phone" cssClass="error"/>
	                	
	                	<label><span class="sign_span">默认地址</span></label>
	                	<label>
	                		<span  style="text-align:left;">
		                	是: <sf:radiobutton path="is_default" value="1" checked="true"/>
		                	否: <sf:radiobutton path="is_default" value="0"/>
		                	</span>
	                	</label>
	                    <label><input type="submit" class="sign_btn" value="提交"></label>
	                    
	                </sf:form>
	                
	                
                </div>
            </section>
        </div>
        

    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
