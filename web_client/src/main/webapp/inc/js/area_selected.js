$(function () {
//    $("#turn_areas").mouseover(function () {
////    		show_isOpen_areas();
//			
//		}
//	);
    $(".closeBtn").click(function () {
			$("#areas_frame").css("display","none");
		}
	);
});


function show_isOpen_areas(baseHost)
{
	$("#areas_frame").css("display","block");
	var area_selected_ul = $('#area_selected_ul');
	area_selected_ul.html('');
	$.ajax({
	  	type: "get",
		async:false,
		cache:false,
		url: baseHost+"public/showAreasIsOpen.action",
		success: function(data, textStatus){
			var obj = jQuery.parseJSON(data);
			$.each(obj,function(entryIndex,entry){
				area_selected_ul.append('<li><a id="'+entry.area_id +'" onclick="areas_click(\'' + entry.area_id + '\')">' + entry.area_name + '</a></li>');
			});
			area_selected_ul.show();
		}
	});
}


function areas_click(area_id)
{
	var area_name = $('#selected_area_name');
	area_name.html('');
	$.ajax({
	  	type: "GET",
		async:false,
		cache:false,
		url: "public/areasClickChoose.action",
		data: {area_id: area_id},
		success: function(data, textStatus){
			area_name.html(data);
			$("#areas_frame").css("display","none");
		}
	});
}


