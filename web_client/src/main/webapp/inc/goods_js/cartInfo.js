
	$(function (){
		var tempTotalnum = 0;
		var tempTotalMoney = 0.00;
		$(":checkbox[name='itemCheck']").each(function(i,v){
			var selPrice_str = $("#itemprice"+i).text();
			var index = selPrice_str.indexOf("¥");
			var end = selPrice_str.lastIndexOf("\n");
			var selPrice = 0;
			if(end==-1){
				selPrice = selPrice_str.substring(index+1);
			}else{
				selPrice = selPrice_str.substring(index+1,end);
			}
			var price = parseFloat(selPrice)
			
			var num_str = $("#goodsOneNum"+i).val();
			var num = parseInt(num_str);
			$(this).click(function (){
				var cur_totalNum = $("#tatalGoods").text();
				var cur_totalPrice = $("#goodsTotalPrice_inp").val();
				
				if($(this).attr("checked")){
					var cur_tp = parseFloat(cur_totalPrice)+price ;
					var cut_nm = parseInt(cur_totalNum)+num;
					$("#goodsTotalPrice_inp").val(cur_tp);
					$("#goodsTotalPrice").text(formatCurrency(cur_tp));
					$("#totalMoney").text(formatCurrency(cur_tp));
					$("#tatalGoods").text(cut_nm);
					var allchecked = true;
					$(":checkbox[name='itemCheck']").each(function (){
					 allchecked = allchecked && $(this).attr("checked");
					});
					$(":checkbox[name='iptCheckbox']").attr("checked",allchecked);
				}else{
					var cur_tp = parseFloat(cur_totalPrice)-price ;
					var cut_nm = parseInt(cur_totalNum)-num;
					$("#goodsTotalPrice_inp").val(cur_tp);
					$("#goodsTotalPrice").text(formatCurrency(cur_tp));
					$("#totalMoney").text(formatCurrency(cur_tp));
					$("#tatalGoods").text(cut_nm);
					$(":checkbox[name='iptCheckbox']").attr("checked",false)
				}
			});
			if($(this).attr("checked")){
					tempTotalMoney = tempTotalMoney + price ;
					tempTotalnum += num;
					$("#goodsTotalPrice_inp").val(tempTotalMoney);
					$("#goodsTotalPrice").text(formatCurrency(tempTotalMoney));
					$("#totalMoney").text(formatCurrency(tempTotalMoney));
					$("#tatalGoods").text(tempTotalnum);
			}
		});
		
		$(":checkbox[name='iptCheckbox']").click(function(){
			if($(this).attr("checked")){
				var cur_tp = 0 ;
				var cut_nm = 0;
				$(":checkbox[name='itemCheck']").each(function(i,v){
					var selPrice_str = $("#itemprice"+i).text();
					var index = selPrice_str.indexOf("¥");
					var end = selPrice_str.lastIndexOf("\n");
					var selPrice = 0;
					if(end==-1){
						selPrice = selPrice_str.substring(index+1);
					}else{
						selPrice = selPrice_str.substring(index+1,end);
					}
					var price = parseFloat(selPrice)
					cur_tp += price ;
					
					var num_str = $("#goodsOneNum"+i).val();
					var num = parseInt(num_str);
					cut_nm += num;
				});
				$("#goodsTotalPrice_inp").val(cur_tp);
				$("#goodsTotalPrice").text(formatCurrency(cur_tp));
				$("#totalMoney").text(formatCurrency(cur_tp));
				$("#tatalGoods").text(cut_nm);
			}else{
				$("#goodsTotalPrice_inp").val(0);
				$("#goodsTotalPrice").text(formatCurrency(0));
				$("#totalMoney").text(formatCurrency(0));
				$("#tatalGoods").text(0);
			}
		});
	});
	
	function formatCurrency(x)  
	{  
		var f_x = parseFloat(x);  
		if (isNaN(f_x))  
		{  
			return false;  
		}  
		
		var f_x = Math.round(x*100)/100;  
		
		var s_x = f_x.toString();  
		
		var pos_decimal = s_x.indexOf('.');  
		
		if (pos_decimal < 0)  
		{  
			pos_decimal = s_x.length;  
			s_x += '.';  
		}  
		
		while (s_x.length <= pos_decimal + 2)  
		{  
			s_x += '0';  
		}  
		return "¥"+s_x;  
	
	}  