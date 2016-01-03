<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>
<title>机票预订-填写联系人信息</title>
</head>

<body>

<header>
	<div class="header margin-center">
    	<div class="return-btn"><a href="<%=basePath %>contact/contactList.action"><img src="<%=basePath %>/inc/outapi/airline/images/return.png" /></a></div><span class="header-txt2" onclick="javascript:location.href='<%=basePath %>contact/contactList.action'">返回</span>
    </div>
</header>

<section>
	<form action="<%=basePath %>contact/addContact.action"  id="orderInfo" name="orderInfo" method="POST">
	<div class="airticket">
    <div class="arkt-tit"><h2>填写联系人信息</h2></div>
	<div class="arkt-cont">
		<ul class="air-ul">
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">姓   名：</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt"  id="linkman" name ="linkman" ><span class="star">*</span></div>
                    </div>
                </div>
            </li>
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">手机号码</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt" id="linkphone" name ="linkphone"    maxlength="30"><span class="star">*</span></div>
                    </div>
                </div>
            </li>
            <li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">联系邮箱</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt" id="linkmail" name ="linkmail"><span class="star">*</span></div>
                    </div>
                </div>
            </li>
             <li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">备用电话</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt" iid="otherLinkMethod" name ="otherLinkMethod" ></div>
                    </div>
                </div>
            </li>
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">默认联系人</span>
                        <div class="city-txt tbl-cell">
	                        <input type="radio" class="rdo" id="rdo3" name="defaultlinkman"  value="0" checked="true"/>
	                        <span class="rdo-ct">是</span>
	                        <input type="radio" class="rdo"   id="rdo4" name="defaultlinkman" value="1"/>
	                        <span class="rdo-ct">否</span>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <div class="tbl-type">
            <div class="tbl-cell plr10"><input type="submit" class="sign_btn" value="确定" /></div>
            <div class="tbl-cell plr10"><input type="button" class="cal-btn" value="取消"  name ="goBack" id="goBack" onclick="javascript:location.href='<%=basePath %>contact/contactList.action'"/></div>
        </div>
    </div>
    </form>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
        
</body>
</html>
