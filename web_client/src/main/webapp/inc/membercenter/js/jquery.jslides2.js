/**
 * jQuery jslides 1.1.0
 *
 * http://www.cactussoft.cn
 *
 * Copyright (c) 2009 - 2013 Jerry
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */
$(function(){
	var numpic = $('#slides2 li').size()-1;
	var nownow = 0;
	var inout = 0;
	var TT = 0;
	var SPEED = 5000;


	$('#slides2 li').eq(0).siblings('li').css({'display':'none'});


	var ulstart = '<ul id="pagination2">',
		ulcontent = '',
		ulend = '</ul>';
	ADDLI();
	var pagination2 = $('#pagination2 li');
	var pagination2width = $('#pagination2').width();
	$('#pagination2').css('margin-left',(470-pagination2width))
	
	pagination2.eq(0).addClass('current')
		
	function ADDLI(){
		//var lilicount = numpic + 1;
		for(var i = 0; i <= numpic; i++){
			ulcontent += '<li>' + '<a href="#">' + (i+1) + '</a>' + '</li>';
		}
		
		$('#slides2').after(ulstart + ulcontent + ulend);	
	}

	pagination2.on('click',DOTCHANGE)
	
	function DOTCHANGE(){
		
		var changenow = $(this).index();
		
		$('#slides2 li').eq(nownow).css('z-index','900');
		$('#slides2 li').eq(changenow).css({'z-index':'800'}).show();
		pagination2.eq(changenow).addClass('current').siblings('li').removeClass('current');
		$('#slides2 li').eq(nownow).fadeOut(400,function(){$('#slides2 li').eq(changenow).fadeIn(500);});
		nownow = changenow;
	}
	
	pagination2.mouseenter(function(){
		inout = 1;
	})
	
	pagination2.mouseleave(function(){
		inout = 0;
	})
	
	function GOGO(){
		
		var NN = nownow+1;
		
		if( inout == 1 ){
			} else {
			if(nownow < numpic){
			$('#slides2 li').eq(nownow).css('z-index','900');
			$('#slides2 li').eq(NN).css({'z-index':'800'}).show();
			pagination2.eq(NN).addClass('current').siblings('li').removeClass('current');
			$('#slides2 li').eq(nownow).fadeOut(400,function(){$('#slides2 li').eq(NN).fadeIn(500);});
			nownow += 1;

		}else{
			NN = 0;
			$('#slides2 li').eq(nownow).css('z-index','900');
			$('#slides2 li').eq(NN).stop(true,true).css({'z-index':'800'}).show();
			$('#slides2 li').eq(nownow).fadeOut(400,function(){$('#slides2 li').eq(0).fadeIn(500);});
			pagination2.eq(NN).addClass('current').siblings('li').removeClass('current');

			nownow=0;

			}
		}
		TT = setTimeout(GOGO, SPEED);
	}
	
	TT = setTimeout(GOGO, SPEED); 

})