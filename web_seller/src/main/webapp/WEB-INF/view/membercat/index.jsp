<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<link rel="stylesheet" href="<%=basePath %>inc/com/jquery-treetable/css/jquery.treetable.css" />
<title>分类管理</title>
<script type="text/javascript">	
 
</script>
</head>
<body>

<div class="position">分类管理</div>

<div class="searchdiv">
	<ul></ul>
</div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" onclick="add_kind();">添加分类</a>
	<a href="javascript:void(0);" onclick="saveMembercat();">保存更改</a>
</div>

<div class="category_box">
	<div class="edit_table_title">
    	<ul>
    	 	<li class="expand">&nbsp;</li>
        	<li class="sort_name">分类名称</li>
            <li class="add_sort">添加子分类</li>
            <li class="is_display">是否显示</li>
            <li class="sort_no">排序</li>
            <li class="in_date">创建时间</li>
            <li class="remove">删除</li>
        </ul>
    </div>
    <div id="content">

    </div>           
</div>

<script>
	//获取数据库信息
   $(document).ready(function () {  	         
      getMembercatList();
   });
	
	function getMembercatList()
    {
    	$.ajax({
	  	type: "get",
		async:false,
		cache:false,
		url: "<%=basePath%>public/membercat/getMembercatList.action",
		success: function(data, textStatus){
			data = data.substring(0,data.length-1);
			var cat = data.split("|");
			for(var i=0;i<cat.length;i++)
			{
				var cat_s = cat[i].split(">");
      			var parentid="big_"+Math.floor(Math.random()*240000);
				for(var j=0;j<cat_s.length;j++)
				{
					if(cat_s[j]!="")
					{
						var cat_arr = cat_s[j].split(",");
						if(j==0)
						{
							var pdivindex=$(".pdiv").length;   //索引是从0开始
							var html="<div class=\"pdiv\">";
          					html+="<div class=\"edit_table_li "+"mm_"+parentid+"\"><ul>";
          					html+="<li class=\"expand\"><input type=\"hidden\" id=\""+parentid+"_expand_show\" value=\"1\"/><input onclick=\"toexpand('"+parentid+"');\" id=\""+parentid+"_expand\"  name=\""+parentid+"_expand\" type=\"button\" class=\"toexpand_btn\"/></li>";
          					html+="<li class=\"sort_name\"><input value=\""+cat_arr[1]+"\" type=\"text\" class=\"sort_name_text border\" maxlength=\"30\"/></li>";
          					html+="<li class=\"add_sort\"><input type=\"hidden\" id=\"cat_id\" value=\""+cat_arr[0]+"\"><a id=\""+parentid+"_add_sort\" onclick=\"add_sort('"+parentid+"',"+i+");\" href=\"javascript:void(0);\">添加子分类</a></li>";
          					html+="<li class=\"is_display\"><input value=\""+cat_arr[2]+"\" type=\"hidden\"/>"+"<span id=\"is_display_text\" onclick=\"hide_display("+pdivindex+","+cat_arr[0]+");\" >"+(cat_arr[2]=="0"?"是":"否")+"</span>"+"</li>";
          					html+="<li class=\"sort_no\"><input type=\"text\" class=\"sort_no_text\" value=\""+cat_arr[3]+"\" maxlength=\"3\"/></li>";
          					html+="<li class=\"in_date\">"+cat_arr[4]+"</li>";
          					html+="<li class=\"remove\"><input id=\""+pdivindex+"_remove\" onclick=\"delbig("+pdivindex+","+cat_arr[0]+");\" type=\"button\" class=\"remove_btn\" value=\"\" /></li>";
          					html+="</ul></div>";
          					$("#content").append(html);
						}
						else
						{
							var sonid= Math.floor(Math.random()*240000);
					        var shtml="";
					        shtml+="<div class=\"edit_table_li_2  "+parentid+"_son_div_class\"><ul class=\""+"small_"+sonid+"\">";
					  		shtml+="<li class=\"expand\">&nbsp;</li>";
					        shtml+="<li class=\"sort_name\"><input value=\""+cat_arr[1]+"\" type=\"text\" class=\"sort_name_text_2 border\" maxlength=\"30\"/></li>";
					        shtml+="<li class=\"add_sort\"><input type=\"hidden\" id=\"cat_id\" value=\""+cat_arr[0]+"\">&nbsp;</li>";
					        shtml+="<li class=\"is_display\"><input value=\""+cat_arr[2]+"\" type=\"hidden\"/>"+"<span id=\"is_display_text\" onclick=\"hide_display_s("+i+","+j+","+cat_arr[0]+");\">"+(cat_arr[2]=="0"?"是":"否")+"</span>"+"</li>";
					        shtml+="<li class=\"sort_no\"><input type=\"text\" class=\"sort_no_text\" value=\""+cat_arr[3]+"\" maxlength=\"3\"/></li>";
					        shtml+="<li class=\"in_date\">"+cat_arr[4]+"</li>";
					        shtml+="<li class=\"remove\"><input id=\""+i+"_remove\" onclick=\"del("+i+","+(j-1)+","+cat_arr[0]+");\" type=\"button\" class=\"remove_btn\" value=\"\" /></li>";
					        shtml+="</ul></div>";
					        $("#"+parentid+"_add_sort").parent().parent().parent().parent().append(shtml);
						}
					}
				}
			}
		}
	  });
    }
    
	//添加大类
	function add_kind()
    {
      var pdivindex=$(".pdiv").length;   //索引是从0开始
      if(pdivindex < 20)
      {
      	var now = new Date();
	  	var date = (now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate());
      	var id="big_"+Math.floor(Math.random()*240000);
      	var html="<div class=\"pdiv\">";
        html+="<div class=\"edit_table_li "+"mm_"+id+"\"><ul>";
        html+="<li class=\"expand\"><input type=\"hidden\" id=\""+id+"_expand_show\" value=\"1\"/><input onclick=\"toexpand('"+id+"');\" id=\""+id+"_expand\"  name=\""+id+"_expand\" type=\"button\" class=\"toexpand_btn\"/></li>";
        html+="<li class=\"sort_name\"><input value=\"\" type=\"text\" class=\"sort_name_text border\" maxlength=\"30\"/></li>";
        html+="<li class=\"add_sort\"><input type=\"hidden\" id=\"cat_id\" value=\"0\"><a id=\""+id+"_add_sort\" onclick=\"add_sort('"+id+"',"+pdivindex+");\" href=\"javascript:void(0);\">添加子分类</a></li>";
        html+="<li class=\"is_display\"><input value=\"0\" type=\"hidden\"/><span id=\"is_display_text\" onclick=\"hide_display("+pdivindex+");\">是</span></li>";
        html+="<li class=\"sort_no\"><input type=\"text\" class=\"sort_no_text\" value=\"0\" maxlength=\"3\"/></li>";
        html+="<li class=\"in_date\"><input value=\""+date+"\" type=\"text\" class=\"in_date_text\" readonly=\"true\"/></li>";
        html+="<li class=\"remove\"><input id=\""+pdivindex+"_remove\" onclick=\"delbig("+pdivindex+");\" type=\"button\" class=\"remove_btn\" value=\"\" /></li>";
        html+="</ul></div>";
        $("#content").append(html);
       }
       else
       	alert("大分类只允许添加20个");
    }
    //添加小类
     function add_sort(id,index)
     {
     	var divcount=$(".pdiv").eq(index).find(".edit_table_li_2").length;
     	if(divcount < 20)
     	{
	        var sid= Math.floor(Math.random()*240000);
	        var row=sub_div(id,index,"small_"+sid);
	        $("#"+id+"_add_sort").parent().parent().parent().parent().append(row);
        }
        else
        	alert("小分类只允许添加20个");
     }
     
      function sub_div(parentid,parentindex,sonid)
      {
    	var now = new Date();
 	  	var date = (now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate());
        var divcount=$(".pdiv").eq(parentindex).find(".edit_table_li_2").length;
        var shtml="";
        shtml+="<div class=\"edit_table_li_2  "+parentid+"_son_div_class\"><ul class=\""+sonid+"\">";
  		shtml+="<li class=\"expand\">&nbsp;</li>";
        shtml+="<li class=\"sort_name\"><input value=\"\" type=\"text\" class=\"sort_name_text_2 border\" maxlength=\"30\"/></li>";
        shtml+="<li class=\"add_sort\"><input type=\"hidden\" id=\"cat_id\" value=\"0\">&nbsp;</li>";
        shtml+="<li class=\"is_display\"><input value=\"0\" type=\"hidden\"/><span id=\"is_display_text\" onclick=\"hide_display_s("+parentindex+","+(divcount+1)+");\">是</span></li>";
        shtml+="<li class=\"sort_no\"><input type=\"text\" class=\"sort_no_text\" value=\"0\" maxlength=\"3\"/></li>";
        shtml+="<li class=\"in_date\"><input value=\""+date+"\" type=\"text\" class=\"in_date_text\" readonly=\"true\"/></li>";
        shtml+="<li class=\"remove\"><input id=\""+divcount+"_remove\" onclick=\"del("+parentindex+","+divcount+");\" type=\"button\" class=\"remove_btn\" value=\"\" /></li>";
        shtml+="</ul></div>";
        return shtml;
      }
      //展开分类
      function toexpand(id)
      {
        if(document.getElementById(id+"_expand_show").value=="0")
        {
           $("."+id+"_son_div_class").show();
           document.getElementById(id+"_expand_show").value="1";
           document.getElementById(id+"_expand").style.background="url(<%=basePath %>inc/com/jquery-treetable/images/drop_down.png) no-repeat";
        }
        else
        {
            $("."+id+"_son_div_class").hide();
            document.getElementById(id+"_expand_show").value="0";
            document.getElementById(id+"_expand").style.background="url(<%=basePath %>inc/com/jquery-treetable/images/drop_up.png) no-repeat";
        }
      }
     //删除大分类
      function delbig(index,cat_id)
      {
         var l=$(".pdiv").eq(index).children().nextAll(".edit_table_li_2").length;
         if(l==0)
         {
         	var result = confirm('确认删除吗?');  
    		if(result){
    			if(cat_id != undefined && cat_id!="")
    	  		{
    	  			delCat_id(cat_id); //删除分类
    	  		}
    	  		document.getElementById('content').innerHTML=""; 
        		getMembercatList();
    		}
         }
         else
         {
             alert("还有子类别，不允许删除！");
         }
      }
      //删除小分类
      function del(pindex,index,cat_id)
      {
          var sondivcount=$(".pdiv").eq(pindex).children(".edit_table_li_2").length;
          var result = confirm('确认删除吗?');
    	  if(result)
    	  {
    	  	if(cat_id != undefined && cat_id!="")
    	  	{
    	  		delCat_id(cat_id); //删除分类
    	  	}
             document.getElementById('content').innerHTML=""; 
        	 getMembercatList();
          }
      }
      
      //ajax 删除
      function delCat_id(cat_id)
      {
      	$.ajax({
			type: "get",
			async:false,
			url: "<%=basePath%>membercat/delete.action?cat_id="+cat_id,
			success: function(data, textStatus){
			}
		});
      }
      
       //提交前判断
      function test_all()
      {
      	 var row_len = $("#content").children(".pdiv").length;
      	 var cfmNum = new RegExp("^[0-9]*$");
      	 var cfmWords=new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"); 
         for(var i=0;i<row_len;i++)
         {
         	var  small_cat_len  = $("#content").children(".pdiv")[i].children.length;
         	for(var j=0;j<small_cat_len;j++)
         	{
         		var sort_name  = $("#content").children(".pdiv")[i].children[j].children[0].children[1].children[0].value.replace(/\s+/g,"");      	
          		var sort_no    = $("#content").children(".pdiv")[i].children[j].children[0].children[4].children[0].value.replace(/\s+/g,"");
          		if(sort_name==""){
          			$("#content").children(".pdiv")[i].children[j].children[0].children[1].children[0].focus();
        			alert("请输入分类名称");
        			return false;
    			}
    			if(cfmWords.test(sort_name)){
		    		$("#content").children(".pdiv")[i].children[j].children[0].children[1].children[0].focus();
        			alert("分类名称不得带有特殊字符");
        			return false;
    			}
          		if(sort_no==""){
          			$("#content").children(".pdiv")[i].children[j].children[0].children[4].children[0].focus();
        			alert("请输入排序编号");
        			return false;
    			}
		    	if(!cfmNum.test(sort_no)){
		    		$("#content").children(".pdiv")[i].children[j].children[0].children[4].children[0].value="0";
		    		$("#content").children(".pdiv")[i].children[j].children[0].children[4].children[0].focus();
        			alert("请输入正确排序编号");
        			return false;
    			}
         	}
       	}
      	return true;
      }
      
      //保存
      function saveMembercat()
      {
      	  if(test_all())
      	  {
	      	  var cat="";
	          var row_len = $("#content").children(".pdiv").length;
	          for(var i=0;i<row_len;i++)
	          {
	          	var  small_cat_len  = $("#content").children(".pdiv")[i].children.length;
	          	for(var j=0;j<small_cat_len;j++)
	          	{
	          		var sort_name  = $("#content").children(".pdiv")[i].children[j].children[0].children[1].children[0].value;
	          		if(sort_name!="")
	          		{
	          			var cat_id = $("#content").children(".pdiv")[i].children[j].children[0].children[2].children[0].value;
		          		var is_display = $("#content").children(".pdiv")[i].children[j].children[0].children[3].children[0].value;
		          		var sort_no    = $("#content").children(".pdiv")[i].children[j].children[0].children[4].children[0].value;
		          		cat= cat + cat_id + "," + sort_name + "," + is_display +"," + sort_no;
		          		if(j < small_cat_len-1)
		          			cat = cat + "-";
		         	}
	          	}
	          	cat = cat +"|";
	           }          
	           $.ajax({
					type: "get",
					async:false,
					url: "<%=basePath%>membercat/addUpdateMembercat.action?cat="+encodeURI(encodeURI(cat)),
					success: function(data, textStatus){
						alert("分类保存修改成功!");
					}
				});
				document.getElementById('content').innerHTML="";
				getMembercatList();
		  }
      }
      
      
      //大分类隐藏、显示
      function hide_display(index,cat_id)
      {
      	var is_display = $("#content").children(".pdiv")[index].children[0].children[0].children[3].children[0].value;
      	if(is_display == 0)
      	{
      		$("#content").children(".pdiv")[index].children[0].children[0].children[3].children[0].value=1;
      		$("#content").children(".pdiv")[index].children[0].children[0].children[3].children[1].innerHTML="否";
      	}
      	else
      	{
      		$("#content").children(".pdiv")[index].children[0].children[0].children[3].children[0].value=0;
      		$("#content").children(".pdiv")[index].children[0].children[0].children[3].children[1].innerHTML="是";
      	}
      	if(cat_id != undefined && cat_id!="")
    	{
      	  ajax_hide_display(cat_id,is_display);
		}
      }
     //小分类隐藏、显示
     function hide_display_s(i,j,cat_id)
     {
      	var is_display = $("#content").children(".pdiv")[i].children[j].children[0].children[3].children[0].value;
      	if(is_display == 0)
      	{
      		$("#content").children(".pdiv")[i].children[j].children[0].children[3].children[0].value=1;
      		$("#content").children(".pdiv")[i].children[j].children[0].children[3].children[1].innerHTML="否";
      	}
      	else
      	{
      		$("#content").children(".pdiv")[i].children[j].children[0].children[3].children[0].value=0;
      		$("#content").children(".pdiv")[i].children[j].children[0].children[3].children[1].innerHTML="是";
      	}
      	if(cat_id != undefined && cat_id!="")
    	{
      	  ajax_hide_display(cat_id,is_display);
		}
      }
      //ajax 显示、隐藏
      function ajax_hide_display(cat_id,is_display)
      {
      	$.ajax({
			 type: "get",
			 async:false,
			 url: "<%=basePath%>membercat/shoHiddenCat.action?cat_id="+cat_id+"&is_display="+is_display,
			 success: function(data, textStatus){				
			 }
		  });
      }
     

</script>            
</body>
</html>

