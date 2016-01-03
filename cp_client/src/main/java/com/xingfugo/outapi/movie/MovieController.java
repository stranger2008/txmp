package com.xingfugo.outapi.movie;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Order;
import com.xingfugo.business.module.Orderhistory;
import com.xingfugo.business.module.User;
import com.xingfugo.business.outapi.movie.module.AbstractRes;
import com.xingfugo.business.outapi.movie.module.Area;
import com.xingfugo.business.outapi.movie.module.AreaCinemaShowTime;
import com.xingfugo.business.outapi.movie.module.AreaCinemaShowTimeRes;
import com.xingfugo.business.outapi.movie.module.AreaListRes;
import com.xingfugo.business.outapi.movie.module.AreaMovieShowTime;
import com.xingfugo.business.outapi.movie.module.AreaMovieShowTimeRes;
import com.xingfugo.business.outapi.movie.module.AreaTheater;
import com.xingfugo.business.outapi.movie.module.Cinema;
import com.xingfugo.business.outapi.movie.module.CinemaInfo;
import com.xingfugo.business.outapi.movie.module.CinemaInfoRes;
import com.xingfugo.business.outapi.movie.module.CinemaMovieShowTime;
import com.xingfugo.business.outapi.movie.module.CinemaMovieShowTimeRes;
import com.xingfugo.business.outapi.movie.module.CinemaRes;
import com.xingfugo.business.outapi.movie.module.CinemaSeatRes;
import com.xingfugo.business.outapi.movie.module.HotMovieRes;
import com.xingfugo.business.outapi.movie.module.MovieDetail;
import com.xingfugo.business.outapi.movie.module.MovieDetailRes;
import com.xingfugo.business.outapi.movie.module.OrderPayConfirmRes;
import com.xingfugo.business.outapi.movie.module.RowSeat;
import com.xingfugo.business.outapi.movie.module.SMSVoucherQueryOrderInfoRes;
import com.xingfugo.business.outapi.movie.module.SMSVoucherQueryRes;
import com.xingfugo.business.outapi.movie.module.SMSVoucherQueryResBody;
import com.xingfugo.business.outapi.movie.module.SMSVoucherQueryVoucherInfoRes;
import com.xingfugo.business.outapi.movie.module.SMSVoucherResendRes;
import com.xingfugo.business.outapi.movie.module.SeatTicketOrder;
import com.xingfugo.business.outapi.movie.module.SeatTicketOrderRes;
import com.xingfugo.business.outapi.movie.module.VoucherInfo;
import com.xingfugo.business.outapi.movie.module.local.MovieOrder;
import com.xingfugo.business.outapi.movie.module.local.MovieOrderDetail;
import com.xingfugo.business.outapi.movie.module.local.MovieSMSVoucher;
import com.xingfugo.business.outapi.movie.service.MovieOrderService;
import com.xingfugo.business.outapi.movie.service.MovieService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.UserService;

@Controller
@RequestMapping("/movie")
@SessionAttributes( { "movie_user_selected_area", 
	"movie_first_level_areas","movie_user_selected_movie", 
	"movie_user_selected_cinema","movie_cinema_movie_show_time"})
public class MovieController {
	private static Logger LOG = LoggerFactory.getLogger(MovieController.class);
	private static final int MOVIE_ORDER_PAY_SECONDS = 900;
	private static final String SESSION_KEY_MOVIE_SEAT_TICKET_ORDER = "movie_seat_ticket_order";
	private static final String SESSION_KEY_MOVIE_PAY_REMAIN_SECONDS = "movie_pay_remain_seconds";
	private static final String SESSION_KEY_MOVIE_ORDER_TIME = "movie_order_time";
	private static final String SESSION_KEY_MOVIE_USER_SELECTED_MOVIE = "movie_user_selected_movie";
	private static final String SESSION_KEY_MOVIE_USER_SELECTED_CINEMA = "movie_user_selected_cinema";
	private static final String SESSION_KEY_MOVIE_CINEMA_MOVIE_SHOW_TIME = "movie_cinema_movie_show_time";
	private static final SimpleDateFormat DATA_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieOrderService movieOrderService;
	
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("movie_first_level_areas")
	public List<Area> getFirstLevelAreas(){
		AreaListRes firstLevelAreaListRes = movieService.getSubAreas(null);
		List<Area> firstLevelAreas = firstLevelAreaListRes.getBody().getAreaList();
		LOG.debug("获取{}个一级城市", firstLevelAreas.size());
		return firstLevelAreas;
	}

	@ModelAttribute("movie_user_selected_area")
	public Area getClientArea(){
		Area area = movieService.getClientArea();
		LOG.debug("获取用户所在城市:{}", area.getNo());
		return area;
	}
	
	//指定城市的热映电影列表
	@RequestMapping("")
	public String getIndexHotMovieList(ModelMap modelMap, 
			@ModelAttribute("movie_user_selected_area") Area userSelectedArea){
		LOG.debug("用户选择的城市:{}", userSelectedArea.getNo());
		HotMovieRes hotMovieRes  = movieService.getHotMovie(userSelectedArea.getNo(), null);
		handleErr(hotMovieRes, modelMap);
		modelMap.addAttribute("hotMovieList", hotMovieRes.getBody().getHotMovieList());
		return createMovieViewPathName("/index");
	}
	
	//城市选择列表
	@RequestMapping("/areas")
	public String getFirstLevelAreas(@ModelAttribute("movie_first_level_areas") List<Area> firstLevelAreaList){
		return createMovieViewPathName("/areaList");
	}
	
	//改变城市
	@RequestMapping("/chgArea")
	public String changeUserSelectedArea(String areaNo, ModelMap modelMap,
			@ModelAttribute("movie_first_level_areas") List<Area> firstLevelAreaList,
			@ModelAttribute("movie_user_selected_area") Area userSelectedArea){
		if(areaNo.equals(userSelectedArea.getNo())){
			LOG.debug("用户选择的城市与之前相同:{}", areaNo);
			return "redirect:/movie.action";
		}
		
		Area selectedArea = null;
		if(!firstLevelAreaList.isEmpty()){
			Area area = null;
			for(int i = 0; i < firstLevelAreaList.size(); i++){
				area = firstLevelAreaList.get(i);
				if(areaNo.equals(area.getNo())){
					selectedArea = area;
					break;
				}
			}
		} else {
			selectedArea = movieService.getAreaById(areaNo);
		}
		
		userSelectedArea.setNo(selectedArea.getNo());
		userSelectedArea.setName(selectedArea.getName());
		userSelectedArea.setLevel(selectedArea.getLevel());
		userSelectedArea.setParentNo(selectedArea.getParentNo());
		
		LOG.debug("用户目前选择的城市:{}", userSelectedArea.getNo());
		return "redirect:/movie.action";
	}
	
	//电影详情
	@RequestMapping("/detail")
	public String getMovieDetail(String filmNo, ModelMap modelMap, HttpSession session){
		MovieDetail movieDetail = getUserSelectedMovie(filmNo, session);
		modelMap.addAttribute("movie", movieDetail);
		return createMovieViewPathName("/detail");
	}
	
	private MovieDetail getUserSelectedMovie(String filmNo, HttpSession session){
		MovieDetail userSelectedMovie = (MovieDetail)session.getAttribute(SESSION_KEY_MOVIE_USER_SELECTED_MOVIE);
		if(userSelectedMovie == null || !userSelectedMovie.getFilmNo().equals(filmNo)){
			MovieDetailRes movieDetailRes = movieService.getMovieDetail(filmNo, true);
			userSelectedMovie = movieDetailRes.getBody();
			session.setAttribute(SESSION_KEY_MOVIE_USER_SELECTED_MOVIE, userSelectedMovie);
		}
		
		return userSelectedMovie;
	}
	
	private CinemaInfo getUserSelectedCinema(String cinemaNo, HttpSession session){
		CinemaInfo userSelectedCinema = (CinemaInfo)session.getAttribute(SESSION_KEY_MOVIE_USER_SELECTED_CINEMA);
		if(userSelectedCinema == null || !userSelectedCinema.getCinemaNo().equals(cinemaNo)){
			CinemaInfoRes cinemaInfoRes = movieService.getCinemaInfo(cinemaNo);
			userSelectedCinema = cinemaInfoRes.getBody();
			session.setAttribute(SESSION_KEY_MOVIE_USER_SELECTED_CINEMA, userSelectedCinema);
		}
		
		return userSelectedCinema;
	}
	
	@SuppressWarnings(value = {"unchecked"})
	private List<CinemaMovieShowTime> getCinemaMovieShowTimeList(String cinemaNo, String filmNo, HttpSession session){
		List<CinemaMovieShowTime> cinemaMovieShowTimeList = (List<CinemaMovieShowTime>)session.getAttribute(SESSION_KEY_MOVIE_CINEMA_MOVIE_SHOW_TIME);
		if(cinemaMovieShowTimeList == null || cinemaMovieShowTimeList.isEmpty() || 
				!cinemaMovieShowTimeList.get(0).getCinemaNo().equals(cinemaNo) ||
				!cinemaMovieShowTimeList.get(0).getFilmNo().equals(filmNo)){
			CinemaMovieShowTimeRes cinemaMovieShowTimeRes = movieService.getCinemaMovieShowTime(cinemaNo, filmNo);
			cinemaMovieShowTimeList = cinemaMovieShowTimeRes.getBody().getCinemaMovieShowTimeList();
			
			Collections.sort(cinemaMovieShowTimeList, new Comparator<CinemaMovieShowTime>(){
				public int compare(CinemaMovieShowTime cm1, CinemaMovieShowTime cm2) {
					int i = cm1.getShowDate().compareTo(cm2.getShowDate());
					if(i == 0){
						return cm1.getShowTime().compareTo(cm2.getShowTime());
					}
					return i;
				}
			});
			
			session.setAttribute(SESSION_KEY_MOVIE_CINEMA_MOVIE_SHOW_TIME, cinemaMovieShowTimeList);
		}
		
		return cinemaMovieShowTimeList;
	}
	
	//指定影片在指定城市内的影院排期情况
	@RequestMapping("/cinema/list")
	public String getCinemaList(String filmNo, ModelMap modelMap,  
			@ModelAttribute("movie_user_selected_area") Area userSelectedArea){
		AreaMovieShowTimeRes areaMovieShowTimeRes = movieService.getAreaMovieShowTime(userSelectedArea.getNo(), filmNo);
		boolean ok = handleErr(areaMovieShowTimeRes, modelMap);
		if(!ok){
			List<AreaMovieShowTime> areaMovieShowTimeList = areaMovieShowTimeRes.getBody().getAreaMovieShowTimeList();
			SortedMap<String, AreaMovieShowTime> map = new TreeMap<String, AreaMovieShowTime>() ;
			String key = null;
			AreaMovieShowTime val;
			for(AreaMovieShowTime item : areaMovieShowTimeList){
				key = item.getAreaNo() + "_" + item.getCinemaNo();
				val = map.get(key);
				if(val == null){
					map.put(key, item);
				}
				else{
					//取同个影院中价格最低的排期项
					if(Double.parseDouble(item.getSalePrice()) < Double.parseDouble(val.getSalePrice())){
						map.put(key, item);
					}
				}
			}
			
			areaMovieShowTimeList = new ArrayList<AreaMovieShowTime>(map.size());
			for(Map.Entry<String, AreaMovieShowTime> me : map.entrySet()){
				areaMovieShowTimeList.add(me.getValue());
	        }
			
			modelMap.addAttribute("areaMovieShowTimeList", areaMovieShowTimeList);
			modelMap.addAttribute("filmNo", filmNo);
		}
		
		return createMovieViewPathName("/cinema/list");
	}
	
	//指定城市内的影院列表
	@RequestMapping("/cinema/theaterList")
	public String getTheaterList(ModelMap modelMap,  
			@ModelAttribute("movie_user_selected_area") Area userSelectedArea){
		CinemaRes cinemaRes = movieService.getTheaterList(userSelectedArea.getNo(), null);
		boolean err = handleErr(cinemaRes, modelMap);
		if(err){
			return createMovieViewPathName("/cinema/theaterList");
		}
		
		List<Cinema> cinemaList = cinemaRes.getBody().getCinemaList();
		if(cinemaList == null || cinemaList.isEmpty()){
			modelMap.addAttribute("areaTheaterList", Collections.emptyList());
			return createMovieViewPathName("/cinema/theaterList");
		}
		
		List<AreaTheater> areaTherterList = new ArrayList<AreaTheater>();
		for(Cinema cinema : cinemaList){
			AreaTheater areaTheater = cinema2AreaTheater(cinema);
			if(!areaTherterList.contains(areaTheater)){
				areaTherterList.add(areaTheater);
				continue;
			}
			
			for(int i = 0; i < areaTherterList.size(); i++){
				AreaTheater tempAreaTheater = areaTherterList.get(i);
				if(tempAreaTheater.equals(areaTheater)){
					tempAreaTheater.addTheater(cinema);
					break;
				}
			}
		}
		
		modelMap.addAttribute("areaTheaterList", areaTherterList);
		return createMovieViewPathName("/cinema/theaterList");
	}
	
	private AreaTheater cinema2AreaTheater(Cinema cinema){
		AreaTheater areaTheater = new AreaTheater();
		areaTheater.setNo(cinema.getAreaNo());
		areaTheater.setName(cinema.getAreaName());
		areaTheater.addTheater(cinema);
		return areaTheater;
	}
	
	//指定影院的影片列表
	@RequestMapping("/cinema/movieList")
	public String getTheaterList(String cinemaNo, ModelMap modelMap,  
			@ModelAttribute("movie_user_selected_area") Area userSelectedArea,
			HttpSession session){
		CinemaInfo cinemaInfo = getUserSelectedCinema(cinemaNo, session);
		modelMap.addAttribute("cinema", cinemaInfo);
		modelMap.addAttribute("cinemaNo", cinemaNo);
		
		AreaCinemaShowTimeRes areaCinemaShowTimeRes = movieService.getAreaCinemaShowTime(userSelectedArea.getNo(), cinemaNo);
		boolean err = handleErr(areaCinemaShowTimeRes, modelMap);
		if(err){
			return createMovieViewPathName("/cinema/movieList");
		}
		
		List<AreaCinemaShowTime> areaCinemaShowTimeList = areaCinemaShowTimeRes.getBody().getAreaCinemaShowTimeList();
		if(areaCinemaShowTimeList == null || areaCinemaShowTimeList.isEmpty()){
			modelMap.addAttribute("areaCinemaShowTimeList", Collections.emptyList());
			return createMovieViewPathName("/cinema/movieList");
		}
		
		List<AreaCinemaShowTime> tempAreaCinemaShowTimeList = new ArrayList<AreaCinemaShowTime>();
		for(AreaCinemaShowTime acst : areaCinemaShowTimeList){
			if(tempAreaCinemaShowTimeList.contains(acst)){
				continue;
			}
			
			tempAreaCinemaShowTimeList.add(acst);
		}
		
		modelMap.addAttribute("areaCinemaShowTimeList", tempAreaCinemaShowTimeList);
		return createMovieViewPathName("/cinema/movieList");
	}
	
	//指定影院内指定影片的排期情况
	@RequestMapping("/cinema/movieDetail")
	public String getCinemaMovieDetail(String cinemaNo, String filmNo, ModelMap modelMap, HttpSession session){
		List<CinemaMovieShowTime> cinemaMovieShowTimeList = getCinemaMovieShowTimeList(cinemaNo, filmNo, session);
		modelMap.addAttribute("cinemaMovieShowTimeList", cinemaMovieShowTimeList);

		MovieDetail movieDetail = getUserSelectedMovie(filmNo, session);
		modelMap.addAttribute("movie", movieDetail);
		
		CinemaInfo cinemaInfo = getUserSelectedCinema(cinemaNo, session);
		modelMap.addAttribute("cinema", cinemaInfo);
		
		modelMap.addAttribute("cinemaNo", cinemaNo);
		modelMap.addAttribute("filmNo", filmNo);
		
		return createMovieViewPathName("/cinema/movie");
	}
	
	//指定影院指定影片指定场次内的座位情况
	@RequestMapping("/cinema/seat")
	public String getCinemaMovieSeat(String cinemaNo,
			String filmNo, String seqNo, ModelMap modelMap, HttpSession session) {
		
		//获取影院电影的排期信息
		List<CinemaMovieShowTime> cinemaMovieShowTimeList = getCinemaMovieShowTimeList(cinemaNo, filmNo, session);
		for(int i = 0; i < cinemaMovieShowTimeList.size(); i++){
			CinemaMovieShowTime item = cinemaMovieShowTimeList.get(i);
			if(seqNo.equals(item.getSeqNo())){
				modelMap.addAttribute("cinemaMovieShowTime", item);
				break;
			}
		}
		
		//座位信息
		CinemaSeatRes cinemaSeatRes = movieService.getCinemaSeat(seqNo);
		if(!handleErr(cinemaSeatRes, modelMap)){
			List<RowSeat> rowSeatList = movieService.toRowSeat(cinemaSeatRes);
			modelMap.addAttribute("rowSeatList", rowSeatList);
		}
		
		//电影信息
		MovieDetail movieDetail = getUserSelectedMovie(filmNo, session);
		modelMap.addAttribute("movie", movieDetail);
		
		//影院信息
		CinemaInfo cinemaInfo = getUserSelectedCinema(cinemaNo, session);
		modelMap.addAttribute("cinema", cinemaInfo);
		
		modelMap.addAttribute("cinemaNo", cinemaNo);
		modelMap.addAttribute("filmNo", filmNo);
		modelMap.addAttribute("maxSelectedSeat", movieService.getMaxSelectedSeat());
		
		return createMovieViewPathName("/cinema/seat");
	}
	
	@RequestMapping("/order/preview")
	public String priviewMovieOrder(){
		return createMovieViewPathName("/order/preview");
	}
	
	//为指定场次的影片下选座订单
	@RequestMapping(value="/order/save")
	public String saveMovieOrder(
			String cinemaNo, String filmNo, String seqNo,
			String hallNo, String seats, Integer amount,
			ModelMap modelMap, HttpSession session,
			RedirectAttributes redirectAttributes){
		String hallName = null;
		String price = null;
		//获取影院电影的排期信息
		List<CinemaMovieShowTime> cinemaMovieShowTimeList = getCinemaMovieShowTimeList(cinemaNo, filmNo, session);
		for(int i = 0; i < cinemaMovieShowTimeList.size(); i++){
			CinemaMovieShowTime item = cinemaMovieShowTimeList.get(i);
			if(seqNo.equals(item.getSeqNo())){
				hallName = item.getHallName();
				price = item.getSalePrice();
				break;
			}
		}
		
		String user_id = (String)session.getAttribute(Constants.SESSION_USER_ID);
		User loginUser = userService.getMemberUserById(Integer.parseInt(user_id));
		String mobile = loginUser.getCellphone();
		int orderType = 1;
		String actNo = null;
		boolean isCustomPrice = false;
		CinemaInfo cinemaInfo = getUserSelectedCinema(cinemaNo, session);
		String cityNo = cinemaInfo.getAreaNo();
		SeatTicketOrderRes seatTicketOrderRes = movieService
				.getSeatTicketOrder(mobile, orderType, actNo, cinemaNo, filmNo,
						seqNo, hallNo, price, seats, cityNo, isCustomPrice);
		
		session.setAttribute("cinemaNo", cinemaNo);
		session.setAttribute("filmNo", filmNo);
		session.setAttribute("seqNo", seqNo);
		
		if(handleErr(seatTicketOrderRes, modelMap)){
			redirectAttributes.addFlashAttribute("errMsg", modelMap.get("errMsg"));
			return "redirect:/movie/order/failed.action";
		}
		
		SeatTicketOrder seatTicketOrder = seatTicketOrderRes.getBody();
		//本地数据库存储操作
		String movieOrderNo = seatTicketOrder.getOrderNo();
		String cust_id = movieService.getMovieSupplierId();
		//订单主表信息
		Order order = new Order();
		order.setOrderId(movieOrderNo);
		order.setBuyCustId(Integer.parseInt(user_id));
		order.setSaleCustId(Integer.parseInt(cust_id));
		order.setConsignee("");
		order.setAreaAttr("");
		order.setAddress("");
		order.setMobile(mobile);
		BigDecimal unitPrice = new BigDecimal(price);
		BigDecimal totalAmount = unitPrice.multiply(BigDecimal.valueOf(amount)).setScale(2, BigDecimal.ROUND_HALF_UP);
		order.setGoodsAmount(totalAmount);
		order.setTatalAmount(totalAmount);
		Date orderTime = null;
		try {
			orderTime = DATA_FORMATTER.parse(seatTicketOrder.getOrderTime());
		} catch (ParseException e) {
			orderTime = new Date();
		}
		order.setOrderTime(orderTime);
		
		MovieDetail movieDetail = getUserSelectedMovie(filmNo, session);
		
		//电影票订单详情
		MovieOrderDetail movieOrderDetail = new MovieOrderDetail();
		movieOrderDetail.setOrderId(movieOrderNo);
		movieOrderDetail.setAllMoney(seatTicketOrder.getOrderPrice());
		movieOrderDetail.setGoodsAmount(amount);
		movieOrderDetail.setCinemaNo(seatTicketOrder.getCinemaNo());
		movieOrderDetail.setCinemaName(cinemaInfo.getCinemaName());
		movieOrderDetail.setFilmNo(seatTicketOrder.getFilmNo());
		movieOrderDetail.setFilmName(movieDetail.getFilmName());
		movieOrderDetail.setHallNo(hallNo);
		movieOrderDetail.setHallName(hallName);
		movieOrderDetail.setGoodsId(seatTicketOrder.getCinemaNo());
		movieOrderDetail.setGoodsName(cinemaInfo.getCinemaName());
		movieOrderDetail.setSeats(seats);
		movieOrderDetail.setSeqNo(seqNo);
		movieOrderDetail.setSinglePrice(price);
		//订单历史记录
		Orderhistory orderHistory = new Orderhistory();
		orderHistory.setOrder_id(movieOrderNo);
		orderHistory.setUser_id(user_id);
		orderHistory.setCust_id(cust_id);
		//订单信息保存本地数据库
		movieOrderService.insertOrder(order, movieOrderDetail, orderHistory);
		
		//订单开始时间（用于客户端支付计时）
		session.setAttribute(SESSION_KEY_MOVIE_ORDER_TIME, System.currentTimeMillis());
		session.setAttribute(SESSION_KEY_MOVIE_PAY_REMAIN_SECONDS, MOVIE_ORDER_PAY_SECONDS);
		session.setAttribute(SESSION_KEY_MOVIE_SEAT_TICKET_ORDER, seatTicketOrder);
		return "redirect:/movie/order/ok.action";
	}
	
	//订单成功提交页面
	@RequestMapping("/order/ok")
	public String movieOrderOK(HttpSession session){
		Long orderTime = (Long)session.getAttribute(SESSION_KEY_MOVIE_ORDER_TIME);
		if(orderTime != null){
			long nowTime = System.currentTimeMillis();
			int payRemainSeconds = (int)(nowTime - orderTime)/1000;
			if( payRemainSeconds >= MOVIE_ORDER_PAY_SECONDS){
				session.removeAttribute(SESSION_KEY_MOVIE_ORDER_TIME);
				session.removeAttribute(SESSION_KEY_MOVIE_PAY_REMAIN_SECONDS);
				session.removeAttribute(SESSION_KEY_MOVIE_SEAT_TICKET_ORDER);
			}
			else{
				session.setAttribute(SESSION_KEY_MOVIE_PAY_REMAIN_SECONDS, MOVIE_ORDER_PAY_SECONDS - payRemainSeconds);
			}
		}
		
		return createMovieViewPathName("/order/ok");
	}
	
	//订单提交失败页面
	@RequestMapping("/order/failed")
	public String movieOrderFailed(){
		return createMovieViewPathName("/order/failed");
	}
	
	//影片订单列表查询
	@RequestMapping("/order/list")
	public String queryMovieOrderList(){
		
		return createMovieViewPathName("/order/list");
	}
	
	//影片订单详细信息
	@RequestMapping("/order/detail/{orderNo}")
	public String getMovieOrderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap){
		MovieOrder movieOrder = movieOrderService.selectOrder(orderNo, true);
		modelMap.addAttribute("movieOrder", movieOrder);
		return createMovieViewPathName("/order/detail");
	}
	
	

	//订单支付确认
	@RequestMapping("/order/payConfirm/{orderNo}")
	@ResponseBody
	public Result movieOrderPayConfirm(@PathVariable("orderNo") String orderNo, ModelMap modelMap,
			HttpSession session){
		MovieOrder movieOrder = movieOrderService.selectOrder(orderNo, false);
		String mobile = movieOrder.getOrder().getMobile();
		BigDecimal totalAmount = movieOrder.getOrder().getTatalAmount();
		String orderPrice = totalAmount.toString();
		String payment = orderPrice;
		OrderPayConfirmRes orderPayConfirmRes = movieService.orderPayConfirm(mobile, orderNo, orderPrice, payment);
		Result result = res2Result(orderPayConfirmRes);
		if(result.ok()){
			session.removeAttribute(SESSION_KEY_MOVIE_ORDER_TIME);
			session.removeAttribute(SESSION_KEY_MOVIE_PAY_REMAIN_SECONDS);
			session.removeAttribute(SESSION_KEY_MOVIE_SEAT_TICKET_ORDER);
			
			Order order = new Order();
			order.setOrderId(orderNo);
			//更新订单为交易成功状态
			order.setOrderState(GoodsorderService.STATE_TRADE_SUCCESS);
			
			//订单历史记录
			String cust_id = String.valueOf(movieOrder.getOrder().getSaleCustId());
			String user_id = String.valueOf(movieOrder.getOrder().getBuyCustId());
			Orderhistory orderHistory = new Orderhistory();
			orderHistory.setUser_id(user_id);
			orderHistory.setCust_id(cust_id);
			orderHistory.setAction_name(GoodsorderService.ACTION_BUYER_PAYMENT);
			
			movieOrderService.updateOrder(order, orderHistory);
		}
		
		return result;
	}
	
	//凭证短信查询
	@RequestMapping("/order/smsVoucher/{orderNo}")
	@ResponseBody
	public Result querySmsVoucher(@PathVariable("orderNo") String orderNo, ModelMap modelMap){
		SMSVoucherQueryRes smsVoucherQueryRes = movieService.smsVoucherQuery(orderNo);
		Result result = res2Result(smsVoucherQueryRes);
		if(result.ok()){
			SMSVoucherQueryResBody body = smsVoucherQueryRes.getBody();
			SMSVoucherQueryOrderInfoRes orderInfo = body.getSmsVoucherQueryOrderInfoRes();
			SMSVoucherQueryVoucherInfoRes voucherInfoRes = body.getSmsVoucherQueryVoucherInfoRes();
			VoucherInfo voucherInfo = voucherInfoRes.getVoucherInfoList().get(0);
			
			//记录凭证信息
			MovieSMSVoucher movieSMSVoucher = new MovieSMSVoucher();
			movieSMSVoucher.setOrderId(orderInfo.getOrderNo());
			movieSMSVoucher.setOrderStatus(orderInfo.getOrderStatus());
			Date orderTime = null;
			try {
				orderTime = DATA_FORMATTER.parse(orderInfo.getOrderTime());
			} catch (ParseException e) {
			}
			movieSMSVoucher.setOrderTime(orderTime);
			movieSMSVoucher.setPayStatus(orderInfo.getPayStatus());
			movieSMSVoucher.setPosition(orderInfo.getPosition());
			movieSMSVoucher.setVoucherNo(voucherInfo.getVoucherNo());
			movieSMSVoucher.setVoucherId(voucherInfo.getVoucherId());
			movieSMSVoucher.setBarcodeImage(voucherInfo.getBarcodeImage());
			movieSMSVoucher.setUseStatus(voucherInfo.getUseStatus());
			movieSMSVoucher.setSmsContent(voucherInfo.getSmsContent());
			movieSMSVoucher.setMmsContent(voucherInfo.getMmsContent());
			Date sendTime = null;
			try {
				sendTime = DATA_FORMATTER.parse(voucherInfo.getSendTime());
			} catch (ParseException e) {
			}
			movieSMSVoucher.setSendTime(sendTime);
			movieSMSVoucher.setSendStatus(voucherInfo.getSendStatus());
			movieSMSVoucher.setSendType(voucherInfo.getSendType());
			
			movieOrderService.insertOrderVoucher(movieSMSVoucher);
		}
		
		return result;
	}
	
	//凭证重发
	@RequestMapping("/order/smsVoucher/resend/{orderNo}")
	@ResponseBody
	public Result smsVoucherReSend(@PathVariable("orderNo") String orderNo, Integer type, ModelMap modelMap){
		MovieOrder movieOrder = movieOrderService.selectOrder(orderNo, false);
		MovieSMSVoucher movieSMSVoucher = movieOrderService.selectOrderVocher(orderNo);
		Integer sendType = 4;
		if(type != null && (type >= 1 && type <= 4)){
			sendType = type;
		}
		SMSVoucherResendRes smsVoucherResendRes = movieService
				.smsVoucherResend(movieSMSVoucher.getVoucherId(), movieOrder
						.getOrder().getMobile(), sendType);
		Result result = res2Result(smsVoucherResendRes);
		return result;
	}
	
	private class Result{
		private int code = 0;
		private String msg = null;
		
		public Result(int code, String msg){
			this.code = code;
			this.msg = msg;
		}
		
		public int getCode(){
			return code;
		}
		
		public String getMsg(){
			return msg;
		}
		
		public boolean ok(){
			return (code == 1);
		}
	}
	
	private Result res2Result(AbstractRes res){
		Result result = null;
		String msg = res.getHead().getErrMsg();
		if(!"0".equals(res.getHead().getErrCode())){
			//失败
			result = new Result(0, msg);
		}
		else{
			//成功
			result = new Result(1, msg);
		}
		
		return result;
	}
	
	private boolean handleErr(AbstractRes res, ModelMap modelMap){
		if(!"0".equals(res.getHead().getErrCode())){
			String errMsg = res.getHead().getErrMsg();
			if(!errMsg.startsWith("温馨提示")){
				errMsg = ("温馨提示：" + errMsg);
			}
			modelMap.addAttribute("errMsg", errMsg);
			return true;
		}
		
		return false;
	}
	
	private String createMovieViewPathName(String viewName){
		StringBuffer buf = new StringBuffer("/outapi/movie");
		buf.append(viewName);
		return buf.toString();
	}
}
