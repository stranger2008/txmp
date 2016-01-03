package com.xingfugo.outapi.movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie/static")
public class MovieStaticController {
	
	@RequestMapping("")
	public String getIndexHotMovieList(ModelMap modelMap){
		return createMovieViewPathName("/index");
	}
	
	@RequestMapping("/areas")
	public String getFirstLevelAreas(){
		return createMovieViewPathName("/areaList");
	}
	
	@RequestMapping("/chgArea")
	public String changeUserSelectedArea(){
		return "redirect:/oupaip/static/movie.action";
	}
	
	@RequestMapping("/detail")
	public String getMovieDetail(){
		return createMovieViewPathName("/detail");
	}
	
	@RequestMapping("/cinema/list")
	public String getCinemaList(){
		return createMovieViewPathName("/cinema/list");
	}
	
	@RequestMapping("/cinema/movieDetail")
	public String getCinemaMovieShowTime(){
		return createMovieViewPathName("/cinema/movie");
	}
	
	@RequestMapping("/cinema/seat")
	public String getCinemaMovieSeat(){
		return createMovieViewPathName("/cinema/seat");
	}
	
	@RequestMapping("/order/preview")
	public String priviewMovieOrder(){
		return createMovieViewPathName("/order/preview");
	}
	
	@RequestMapping("/order/save")
	public String saveMovieOrder(){
		return createMovieViewPathName("/order/ok");
	}
	
	private String createMovieViewPathName(String viewName){
		StringBuffer buf = new StringBuffer("/outapi/movie/static");
		buf.append(viewName);
		return buf.toString();
	}
}
