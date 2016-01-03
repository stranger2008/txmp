package com.xingfugo.business.outapi.movie.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.outapi.movie.module.Area;
import com.xingfugo.business.outapi.movie.module.AreaCinemaShowTimeReq;
import com.xingfugo.business.outapi.movie.module.AreaCinemaShowTimeReqBody;
import com.xingfugo.business.outapi.movie.module.AreaCinemaShowTimeRes;
import com.xingfugo.business.outapi.movie.module.AreaListRes;
import com.xingfugo.business.outapi.movie.module.AreaMovieShowTimeReq;
import com.xingfugo.business.outapi.movie.module.AreaMovieShowTimeReqBody;
import com.xingfugo.business.outapi.movie.module.AreaMovieShowTimeRes;
import com.xingfugo.business.outapi.movie.module.CinemaInfoReq;
import com.xingfugo.business.outapi.movie.module.CinemaInfoReqBody;
import com.xingfugo.business.outapi.movie.module.CinemaInfoRes;
import com.xingfugo.business.outapi.movie.module.CinemaMovieShowTime;
import com.xingfugo.business.outapi.movie.module.CinemaMovieShowTimeReq;
import com.xingfugo.business.outapi.movie.module.CinemaMovieShowTimeReqBody;
import com.xingfugo.business.outapi.movie.module.CinemaMovieShowTimeRes;
import com.xingfugo.business.outapi.movie.module.CinemaReq;
import com.xingfugo.business.outapi.movie.module.CinemaReqBody;
import com.xingfugo.business.outapi.movie.module.CinemaRes;
import com.xingfugo.business.outapi.movie.module.CinemaSeat;
import com.xingfugo.business.outapi.movie.module.CinemaSeatReq;
import com.xingfugo.business.outapi.movie.module.CinemaSeatReqBody;
import com.xingfugo.business.outapi.movie.module.CinemaSeatRes;
import com.xingfugo.business.outapi.movie.module.CommingMovieRes;
import com.xingfugo.business.outapi.movie.module.EmptyBodyReq;
import com.xingfugo.business.outapi.movie.module.HotMovieReq;
import com.xingfugo.business.outapi.movie.module.HotMovieReqBody;
import com.xingfugo.business.outapi.movie.module.HotMovieRes;
import com.xingfugo.business.outapi.movie.module.MovieDetailReq;
import com.xingfugo.business.outapi.movie.module.MovieDetailReqBody;
import com.xingfugo.business.outapi.movie.module.MovieDetailRes;
import com.xingfugo.business.outapi.movie.module.OrderDetailQueryReq;
import com.xingfugo.business.outapi.movie.module.OrderDetailQueryReqBody;
import com.xingfugo.business.outapi.movie.module.OrderDetailQueryRes;
import com.xingfugo.business.outapi.movie.module.OrderListQueryReq;
import com.xingfugo.business.outapi.movie.module.OrderListQueryReqBody;
import com.xingfugo.business.outapi.movie.module.OrderListQueryRes;
import com.xingfugo.business.outapi.movie.module.OrderPayConfirmReq;
import com.xingfugo.business.outapi.movie.module.OrderPayConfirmReqBody;
import com.xingfugo.business.outapi.movie.module.OrderPayConfirmRes;
import com.xingfugo.business.outapi.movie.module.RowSeat;
import com.xingfugo.business.outapi.movie.module.SMSVoucherQueryReq;
import com.xingfugo.business.outapi.movie.module.SMSVoucherQueryReqBody;
import com.xingfugo.business.outapi.movie.module.SMSVoucherQueryRes;
import com.xingfugo.business.outapi.movie.module.SMSVoucherResendReq;
import com.xingfugo.business.outapi.movie.module.SMSVoucherResendReqBody;
import com.xingfugo.business.outapi.movie.module.SMSVoucherResendRes;
import com.xingfugo.business.outapi.movie.module.Seat;
import com.xingfugo.business.outapi.movie.module.SeatTicketOrder;
import com.xingfugo.business.outapi.movie.module.SeatTicketOrderReq;
import com.xingfugo.business.outapi.movie.module.SeatTicketOrderReqBody;
import com.xingfugo.business.outapi.movie.module.SeatTicketOrderRes;
import com.xingfugo.util.Md5;

@Service
public class MovieService {
	private static Logger LOG = LoggerFactory.getLogger(MovieService.class);
	
	@Autowired
	private HttpClientService httpClientService;
	
	public int getMaxSelectedSeat(){
		return httpClientService.getMaxSelectedSeat();
	}
	public String getMovieSupplierId(){
		//影片服务供应商ID，系统所有
		return "-1";
	}
	public Area getClientArea(){
		Area clientArea = new Area();
		//TODO 通过客户端位置或IP信息，获取用户所在城市 北京地区
		clientArea.setLevel(1);
		clientArea.setNo("110100");
		clientArea.setName("北京市");
		return clientArea;
	}
	
	//所有地区列表
	public AreaListRes getAllAreas(){
		EmptyBodyReq req = httpClientService.createEmptyBodyReq("getAreaList");
		AreaListRes areaListRes = httpClientService.post(req, AreaListRes.class);
		return areaListRes;
	}
	
	//地区列表
	public AreaListRes getSubAreas(String parentId){
		AreaListRes areaListRes = getAllAreas();
		List<Area> allAreaList = areaListRes.getBody().getAreaList();
		List<Area> areaList = new ArrayList<Area>();
		//获取一级地区列表
		if(StringUtils.isBlank(parentId)){
			for(Area area : allAreaList){
				if(area.getLevel() == 2){
					areaList.add(area);
				}
			}
			
			areaListRes.getBody().setAreaList(areaList);
			return areaListRes;
		}
		
		//获取下级子地区
		for(Area area : allAreaList){
			if(parentId.equals(area.getParentNo())){
				areaList.add(area);
			}
		}
		
		areaListRes.getBody().setAreaList(areaList);
		return areaListRes;
	}
	
	//地区信息
	public Area getAreaById(String areaNo){
		AreaListRes areaListRes = getAllAreas();
		List<Area> allAreaList = areaListRes.getBody().getAreaList();
		for(Area area : allAreaList){
			if(areaNo.equals(area.getNo())){
				return area;
			}
		}
			
		return null;
	}
	
	//热映影片列表
	public HotMovieRes getHotMovie(String areaNo, String cinemaNo){
		HotMovieReq req = new HotMovieReq();
		req.setHead(httpClientService.createReqHeader("getHotFilms"));

		HotMovieReqBody body = new HotMovieReqBody();
		body.setAreaNo(areaNo.trim());
		body.setCinemaNo(StringUtils.isBlank(cinemaNo) ? "" : cinemaNo.trim());
		req.setBody(body);
		
		HotMovieRes hotMovieRes = httpClientService.post(req, HotMovieRes.class);
		return hotMovieRes;
	}
	
	//影片详细信息
	public MovieDetailRes getMovieDetail(String filmNo, boolean isParater){
		MovieDetailReq req = new MovieDetailReq();
		req.setHead(httpClientService.createReqHeader("getFilmInfo"));
		
		MovieDetailReqBody body = new MovieDetailReqBody();
		body.setFilmNo(filmNo.trim());
		body.setIsParater(isParater ? 1 : 0);
		req.setBody(body);
		
		MovieDetailRes movieDetailRes = httpClientService.post(req, MovieDetailRes.class);
		return movieDetailRes;
	}
	
	//即将上映电影
	public CommingMovieRes getComminMovie(){
		EmptyBodyReq req = httpClientService.createEmptyBodyReq("getComingFilms");
		CommingMovieRes commingMovieRes = httpClientService.post(req, CommingMovieRes.class);
		return commingMovieRes;
	}
	
	//地区影片排期
	public AreaMovieShowTimeRes getAreaMovieShowTime(String areaNo, String filmNo){
		AreaMovieShowTimeReq req = new AreaMovieShowTimeReq();
		req.setHead(httpClientService.createReqHeader("getShowTimeByAreaNoFilmNo"));
		
		AreaMovieShowTimeReqBody body = new AreaMovieShowTimeReqBody();
		body.setAreaNo(areaNo.trim());
		body.setFilmNo(filmNo.trim());
		req.setBody(body);
		
		AreaMovieShowTimeRes areaMovieShowTimeRes = httpClientService.post(req, AreaMovieShowTimeRes.class);
		return areaMovieShowTimeRes;
	}
	
	//地区影院排期
	public AreaCinemaShowTimeRes getAreaCinemaShowTime(String areaNo, String cinemaNo){
		AreaCinemaShowTimeReq req = new AreaCinemaShowTimeReq();
		req.setHead(httpClientService.createReqHeader("getShowTimeByAreaNoCinemaNo"));
		
		AreaCinemaShowTimeReqBody body = new AreaCinemaShowTimeReqBody();
		body.setAreaNo(areaNo.trim());
		body.setCinemaNo(cinemaNo.trim());
		req.setBody(body);
		
		AreaCinemaShowTimeRes areaCinemaShowTimeRes = httpClientService.post(req, AreaCinemaShowTimeRes.class);
		return areaCinemaShowTimeRes;
	}
	
	//影院影片排期
	public CinemaMovieShowTimeRes getCinemaMovieShowTime(String cinemaNo, String filmNo){
		CinemaMovieShowTimeReq req = new CinemaMovieShowTimeReq();
		req.setHead(httpClientService.createReqHeader("getShowTimeByCinemaNoFilmNo"));
		
		CinemaMovieShowTimeReqBody body = new CinemaMovieShowTimeReqBody();
		body.setCinemaNo(cinemaNo.trim());
		body.setFilmNo(filmNo.trim());
		req.setBody(body);
		
		CinemaMovieShowTimeRes cinemaMovieShowTimeRes = httpClientService.post(req, CinemaMovieShowTimeRes.class);
		List<CinemaMovieShowTime> cinemaMovieShowTimeList = cinemaMovieShowTimeRes.getBody().getCinemaMovieShowTimeList();
		if(cinemaMovieShowTimeList != null && !cinemaMovieShowTimeList.isEmpty()){
			for(CinemaMovieShowTime cinemaMovieShowTime : cinemaMovieShowTimeList){
				cinemaMovieShowTime.setCinemaNo(cinemaNo);
				cinemaMovieShowTime.setFilmNo(filmNo);
			}
		}
		
		return cinemaMovieShowTimeRes;
	}
	
	//影院列表
	public CinemaRes getTheaterList(String areaNo, String filmNo){
		CinemaReq req = new CinemaReq();
		req.setHead(httpClientService.createReqHeader("getCinemas"));
		
		CinemaReqBody body = new CinemaReqBody();
		body.setAreaNo(areaNo.trim());
		body.setFilmNo(filmNo == null ? "" : filmNo.trim());
		req.setBody(body);
		
		CinemaRes cinemaRes = httpClientService.post(req, CinemaRes.class);
		return cinemaRes;
	}
	
	//影院详情
	public CinemaInfoRes getCinemaInfo(String cinemaNo){
		CinemaInfoReq req = new CinemaInfoReq();
		req.setHead(httpClientService.createReqHeader("getCinemaInfo"));
		
		CinemaInfoReqBody body = new CinemaInfoReqBody();
		body.setCinemaNo(cinemaNo.trim());
		req.setBody(body);
		
		CinemaInfoRes cinemaInfoRes = httpClientService.post(req, CinemaInfoRes.class);
		cinemaInfoRes.getBody().setCinemaNo(cinemaNo);
		return cinemaInfoRes;
	}

	@Deprecated
	public String getSeatSelectUrl(String cityNo, String seqNo, String price, boolean pFlag){
		StringBuffer buf = new StringBuffer(httpClientService.getMovieSeatSelectURL());
		buf.append("?appKey=").append(httpClientService.getAppKey());
		buf.append("&tradaId=createWebSeatOrder");
		buf.append("&seqNo=").append(seqNo);
		buf.append("&Price=").append(price);
		buf.append("&cityNo=").append(cityNo);
		buf.append("&pFlag=").append(pFlag ? 1 : 0);
		buf.append("&url=").append(httpClientService.getSeatCallBackURL(true));
		buf.append("&sign=").append(httpClientService.getSeatValidCode(seqNo,price, cityNo, pFlag));
		return buf.toString();
	}
	
	//获取影院内影片场次的座位信息
	public CinemaSeatRes getCinemaSeat(String seqNo){
		CinemaSeatReq req = new CinemaSeatReq();
		req.setHead(httpClientService.createReqHeader("getSeat"));
		
		CinemaSeatReqBody body = new CinemaSeatReqBody();
		body.setSeqNo(seqNo.trim());
		req.setBody(body);
		
		CinemaSeatRes cinemaSeatRes = httpClientService.post(req, CinemaSeatRes.class);
		return cinemaSeatRes;
	}
	
	public List<RowSeat> toRowSeat(CinemaSeatRes cinemaSeatRes){
		List<CinemaSeat> cinemaSeatList = cinemaSeatRes.getBody().getCinemaSeatList();
		if(cinemaSeatList == null || cinemaSeatList.isEmpty()){
			return Collections.emptyList();
		}
		
		int maxSeatPerLine = 0;
		List<RowSeat> rowSeatList = new ArrayList<RowSeat>(cinemaSeatList.size());
		for(CinemaSeat cinemaSeat : cinemaSeatList){
			String[] col = cinemaSeat.getColumnNo().split(",");
			if(maxSeatPerLine < col.length){
				maxSeatPerLine = col.length;
			}
		}
		
		for(CinemaSeat cinemaSeat : cinemaSeatList){
			RowSeat rowSeat = new RowSeat();
			rowSeat.setRowNo(cinemaSeat.getRowNo());
			rowSeat.setImgRow(cinemaSeat.getSeatImgRow());
			
			String[] col = cinemaSeat.getColumnNo().split(",");
			String[] type = cinemaSeat.getSeatType().split(",");
			String[] status = cinemaSeat.getSeatStatus().split(",");
			//String[] no = cinemaSeat.getSeatNo().split(",");
			int colLen = col.length;
			int typeLen = type.length;
			int statusLen = status.length;
			
			List<Seat> seatList = new ArrayList<Seat>(maxSeatPerLine);
			for(int i = 0; i < maxSeatPerLine; i++){
				Seat seat = new Seat();
				seat.setLocNo(cinemaSeat.getLocNo());
				
				seat.setColNo(i < colLen ? col[i] : "ZL");
				seat.setStatus(i < statusLen ? status[i] : null);
				seat.setType(i < typeLen ? type[i] : "3");
				//seat.setNo(no[i]);
				
				seatList.add(seat);
			}
			
			rowSeat.setSeatList(seatList);
			rowSeatList.add(rowSeat);
		}
		
		return rowSeatList;
	}
	
	//下选座票订单 
	public SeatTicketOrderRes getSeatTicketOrder(String mobile, int orderType,
			String actNo, String cinemaNo, String filmNo, String seqNo,
			String hallNo, String price, String seats, String cityNo,
			boolean isCustomPrice) {
		SeatTicketOrderReq req = new SeatTicketOrderReq();
		req.setHead(httpClientService.createReqHeader("createSeatTicketOrder"));
		
		SeatTicketOrderReqBody body = new SeatTicketOrderReqBody();
		body.setMobile(mobile.trim());
		body.setOrderType(orderType);
		body.setActNo(actNo == null ? "" : actNo.trim());
		body.setCinemaNo(cinemaNo.trim());
		body.setFilmNo(filmNo.trim());
		body.setSeqNo(seqNo.trim());
		body.setHallNo(hallNo.trim());
		body.setPrice(price.trim());
		body.setSeats(seats.trim());
		body.setCityNo(cityNo.trim());
		body.setIsCustomPrice(isCustomPrice ? "1" : "0");
		String sign = orderReqSign(body);
		body.setSign(sign);
		req.setBody(body);
		
		SeatTicketOrderRes seatTicketOrderRes = httpClientService.post(req, SeatTicketOrderRes.class);
		return seatTicketOrderRes;
	}
	
	private String orderReqSign(SeatTicketOrderReqBody body){
		StringBuffer buf = new StringBuffer();
		buf.append(body.getMobile());
		buf.append(body.getOrderType());
		buf.append(body.getCinemaNo());
		buf.append(body.getFilmNo());
		buf.append(body.getSeqNo());
		buf.append(body.getHallNo());
		buf.append(body.getPrice());
		buf.append(httpClientService.getAppCode());
		LOG.debug("订单Sign:{}", buf.toString());
		return Md5.getMD5(buf.toString().getBytes());
	}
	
	//验证订单返回信息
	public boolean validOrderSign(String mobile, SeatTicketOrder seatTicketOrder){
		return seatTicketOrder.validSign(httpClientService.getAppCode(), mobile);
	}
	
	//查询订单列表 
	public OrderListQueryRes getOrderList(String mobile, String startDate, String endDate){
		OrderListQueryReq req = new OrderListQueryReq();
		req.setHead(httpClientService.createReqHeader("qryOrders"));
		
		OrderListQueryReqBody body = new OrderListQueryReqBody();
		body.setMobile(mobile.trim());
		body.setStartDate(startDate.trim());
		body.setEndDate(endDate.trim());
		req.setBody(body);
		
		OrderListQueryRes orderListQueryRes = httpClientService.post(req, OrderListQueryRes.class);
		return orderListQueryRes;
	}
	
	//查询订单详情 
	public OrderDetailQueryRes getOrderDetail(String orderNo){
		OrderDetailQueryReq req = new OrderDetailQueryReq();
		req.setHead(httpClientService.createReqHeader("qryOrderDetail"));
		
		OrderDetailQueryReqBody body = new OrderDetailQueryReqBody();
		body.setOrderNo(orderNo.trim());
		req.setBody(body);
		
		OrderDetailQueryRes orderDetailQueryRes = httpClientService.post(req, OrderDetailQueryRes.class);
		return orderDetailQueryRes;
	}
	
	//支付确认
	public OrderPayConfirmRes orderPayConfirm(String mobile, String orderNo, String orderPrice,
			String payment){
		OrderPayConfirmReq req = new  OrderPayConfirmReq();
		req.setHead(httpClientService.createReqHeader("confirmOrder"));
		
		OrderPayConfirmReqBody body = new OrderPayConfirmReqBody();
		body.setMobile(mobile.trim());
		body.setOrderNo(orderNo.trim());
		body.setOrderPrice(orderPrice);
		body.setPayment(payment);
		body.setSign(orderPayConfirmSign(body));
		req.setBody(body);
		
		OrderPayConfirmRes orderPayConfirmRes = httpClientService.post(req, OrderPayConfirmRes.class);
		return orderPayConfirmRes;
	}
	
	private String orderPayConfirmSign(OrderPayConfirmReqBody body){
		StringBuffer buf = new StringBuffer();
		buf.append(body.getMobile());
		buf.append(body.getOrderNo());
		buf.append(body.getOrderPrice());
		buf.append(httpClientService.getAppCode());
		LOG.debug("支付确认Sign:{}", buf.toString());
		return Md5.getMD5(buf.toString().getBytes());
	}
	
	//凭证短信查询
	public SMSVoucherQueryRes smsVoucherQuery(String orderNo){
		SMSVoucherQueryReq req = new  SMSVoucherQueryReq();
		req.setHead(httpClientService.createReqHeader("queryVoucherSMS"));
		
		SMSVoucherQueryReqBody body = new SMSVoucherQueryReqBody();
		body.setOrderNo(orderNo.trim());
		body.setSign(smsVoucherQuerySign(orderNo, req.getHead().getTimeStamp()));
		req.setBody(body);
		
		SMSVoucherQueryRes smsVoucherQueryRes = httpClientService.post(req, SMSVoucherQueryRes.class);
		return smsVoucherQueryRes;
	}
	
	private String smsVoucherQuerySign(String orderNo, String nowTime){
		StringBuffer buf = new StringBuffer();
		buf.append(orderNo);
		buf.append(nowTime);
		buf.append(httpClientService.getAppCode());
		LOG.debug("凭证短信查询Sign:{}", buf.toString());
		return Md5.getMD5(buf.toString().getBytes());
	}
	
	//凭证短信重发
	public SMSVoucherResendRes smsVoucherResend(String voucherNo, String mobileNo, Integer sendType){
		SMSVoucherResendReq req = new  SMSVoucherResendReq();
		req.setHead(httpClientService.createReqHeader("voucherSend"));
		
		SMSVoucherResendReqBody body = new SMSVoucherResendReqBody();
		body.setSendType(sendType);
		body.setMobileNo(mobileNo.trim());
		body.setVoucherNo(voucherNo.trim());
		req.setBody(body);
		
		SMSVoucherResendRes smsVoucherResendRes = httpClientService.post(req, SMSVoucherResendRes.class);
		return smsVoucherResendRes;
	}

}
