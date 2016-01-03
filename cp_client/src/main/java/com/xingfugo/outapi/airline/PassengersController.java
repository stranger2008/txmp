package com.xingfugo.outapi.airline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.outapi.airline.module.Passengers;
import com.xingfugo.business.outapi.airline.service.PassengerService;
import com.xingfugo.business.outapi.airline.webservice.WsAirSegment;
import com.xingfugo.business.outapi.airline.webservice.WsBookPnr;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.controller.BaseController;

/**
 * @author 乘机人信息管理
 *
 */
@Controller
public class PassengersController extends BaseController {
	 
	@Autowired
	private PassengerService passengerService;
	
	/**
	 * 进入乘机人列表页面
	 */
	@RequestMapping(value="passengers/passengerList",method=RequestMethod.GET)
	public String searchairline(HttpServletRequest request,WsBookPnr aqf,WsAirSegment ws,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(user_id)){
			return "redirect:"+basePath()+"login.action";
		}
		List<Passengers> list= passengerService.passengersListById(Integer.parseInt(user_id));
		model.addAttribute("passengerlist",list);
		return "/outapi/airline/airlinePassengers";
	}
	
	
	/**
	 * 返回订单信息页面
	 */
	@RequestMapping(value="passengers/orderInfo",method=RequestMethod.GET)
	public String orderInfo(HttpServletRequest request,String selectPassengers){
		if(StringUtils.isNotBlank(selectPassengers)){
			SessionUtil.getSession(request).setAttribute("passengers", passengerService.passengersListByIds(selectPassengers));
		}
		return "/outapi/airline/airlineorder";
	}
	
	/**
	 * 进入添加乘机人信息页面
	 */
	@RequestMapping(value="passengers/toaddPassenger",method=RequestMethod.GET)
	public String toaddPassenger(ModelMap model){
		return "/outapi/airline/airlineaddPassenger";
	}
	
	/**
	 * 添加乘机人
	 */
	@RequestMapping(value="passengers/addPassenger",method=RequestMethod.POST)
	public  String addPassenger(HttpServletRequest request,Passengers passenger,ModelMap model){
		passenger.setUser_id(Integer.parseInt(SessionUtil.getString(request,Constants.SESSION_USER_ID)));
		passengerService.insertPassenger(passenger);
		return "redirect:"+basePath()+"passengers/passengerList.action";
	}
	
	/**
	 * @param id
	 * @param model
	 * 删除乘机人
	 */
	@RequestMapping(value="passengers/deletePassenger",method=RequestMethod.GET)
	public String  deletePassenger(String id,ModelMap model){
		if(id==null){
			return "redirect:"+basePath()+"login.action";
		}
		passengerService.deletePassengerById(id);
		return "redirect:"+basePath()+"passengers/passengerList.action";
	}

}
