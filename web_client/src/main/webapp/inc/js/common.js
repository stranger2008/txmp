function topKeySearch(basePath){
	var val = $(".box_search .sear_t").val();
	if(val != ""){
		window.location.href=basePath+"goodslist.action?key="+val;
	}
}