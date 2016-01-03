package com.xingfugo.outapi.airline;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.lean.redis.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Api_air_orderdetail;
import com.xingfugo.business.outapi.airline.module.Airports;
import com.xingfugo.business.outapi.airline.module.AvailableFlightWithPriceAndCommisionReply;
import com.xingfugo.business.outapi.airline.module.Contacts;
import com.xingfugo.business.outapi.airline.module.JourneySheet;
import com.xingfugo.business.outapi.airline.module.Passengers;
import com.xingfugo.business.outapi.airline.module.WsFlightWithPriceAndCommision;
import com.xingfugo.business.outapi.airline.module.query.AvailableFlightWithPriceAndCommisionQueryForm;
import com.xingfugo.business.outapi.airline.service.AirLineService;
import com.xingfugo.business.outapi.airline.service.AvailableFlightWithPriceService;
import com.xingfugo.business.outapi.airline.service.ReadAirLineProperties;
import com.xingfugo.business.outapi.airline.service.ReadAirPortXml;
import com.xingfugo.business.outapi.airline.webservice.CreateOrderByPassengerReply;
import com.xingfugo.business.outapi.airline.webservice.CreateOrderByPassengerRequest;
import com.xingfugo.business.outapi.airline.webservice.CreateOrderByPassengerService10;
import com.xingfugo.business.outapi.airline.webservice.CreateOrderByPassengerServiceImpl10Service;
import com.xingfugo.business.outapi.airline.webservice.WsAirSegment;
import com.xingfugo.business.outapi.airline.webservice.WsBookPassenger;
import com.xingfugo.business.outapi.airline.webservice.WsBookPnr;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.controller.BaseController;
import com.xingfugo.util.Md5;

/**
 * @author wyl 机票接口管理
 */
@Controller
public class AirLineController extends BaseController {

	@Autowired
	private AvailableFlightWithPriceService afw;
	// 查询请求路径
	private static final String URL = "http://ws.tongyedns.com:8000/ltips/services/getAvailableFlightWithPriceAndCommisionServiceRestful1.0/getAvailableFlightWithPriceAndCommision";
	// 授权账户
	private static final String NAME = ReadAirLineProperties.NAME;
	// 授权秘钥
	private static final String PWD = ReadAirLineProperties.PWD;
	
	 @SuppressWarnings("unused")
	private static Airports airports=ReadAirPortXml.airports;
	
	@Autowired
	private AirLineService airLineService;
	@Autowired
	private GoodsorderService goodsorderService;

	private JedisService jedisService = new JedisService();
	/**
	 * @return 进入航班查询页面
	 */
	@RequestMapping(value = "airLine/searchairline", method = RequestMethod.GET)
	public String searchairline() {
		return "/outapi/airline/airline";
	}

	/**
	 * @return 查找航班
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "airLine/dosearch", method={RequestMethod.POST, RequestMethod.GET})
	public String dosearch(AvailableFlightWithPriceAndCommisionQueryForm aqf,
			ModelMap model) {
		if (aqf == null) {
			return "/outapi/airline/searchAir";
		}

		Map map = new HashMap();
		map.put("agencyCode", NAME);
		map.put("sign", Md5.getMD5((NAME + aqf.getDstAirportCode() + "0" + "0"+ "0" + "0" + aqf.getOrgAirportCode() + PWD).getBytes()));
		map.put("orgAirportCode", aqf.getOrgAirportCode());
		map.put("dstAirportCode", aqf.getDstAirportCode());
		map.put("date", aqf.getDate());
		map.put("onlyAvailableSeat", "0");
		map.put("onlyNormalCommision", "0");
		map.put("onlyOnWorkingCommision", "0");
		map.put("onlySelfPNR", "0");

		// 根据查询条件查询机票
		AvailableFlightWithPriceAndCommisionReply aReply = afw.getAvailableFlightWithPriceAndCommision(URL, map);
		model.addAttribute("aReply", aReply);
		return "/outapi/airline/airlinelist";

	}

	/**
	 * @param aqf
	 * @param ws
	 * @param model
	 * @return 进入订单信息填写页面 保存选择的航班信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "airLine/order", method = RequestMethod.GET)
	public String orderInfo(HttpServletRequest request, WsBookPnr aqf,
			WsAirSegment ws, String policyId,
			WsFlightWithPriceAndCommision wwc, ModelMap model) {
		List<WsAirSegment> list = new ArrayList<WsAirSegment>();
		list.add(ws);
		aqf.setSegments(list);
		SessionUtil.getSession(request).setAttribute("wwc", wwc);
		SessionUtil.getSession(request).setAttribute("policyId", policyId);
		SessionUtil.getSession(request).setAttribute("ws", ws);
		SessionUtil.getSession(request).setAttribute("aqf", aqf);
		AirPort ap =new AirPort();
		if(ws!=null){
			Set depCodeSet=jedisService.SETS.smembers(ws.getDepCode());
			Set arrCodeSet=jedisService.SETS.smembers(ws.getArrCode());
			ap.setArrAirPort(arrCodeSet.toString());
			ap.setDstAirPort(depCodeSet.toString());
		}
		SessionUtil.getSession(request).setAttribute("airPort", ap);
		return "/outapi/airline/airlineorder";
	}

	/**
	 * 提交订单
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "airLine/submitOrder", method = RequestMethod.POST)
	public String submitOrder(HttpServletRequest request,Api_air_orderdetail ao, ModelMap model) throws Exception {
		// 封装请求参数
		WsBookPnr wbp = (WsBookPnr) SessionUtil.getSession(request).getAttribute("aqf");
		// 乘客信息
		List<Passengers> passenger = (List) SessionUtil.getSession(request).getAttribute("passengers");
		String policyId = (String) SessionUtil.getSession(request).getAttribute("policyId");

		List<WsBookPassenger> li = afw.getPassenger(passenger);
		wbp.setPassengers(li);
		Contacts contacts = (Contacts) SessionUtil.getSession(request).getAttribute("contacts");
		// 生成订单号
		String orderId = goodsorderService.order_id();
		// 构建CreateOrderByPassengerRequest 对象
		CreateOrderByPassengerRequest cr = buildCreateOrderByPassengerRequest(policyId, contacts, wbp, orderId);
		// webservice 请求提交订单
		CreateOrderByPassengerServiceImpl10Service serviceImpl = new CreateOrderByPassengerServiceImpl10Service();
		CreateOrderByPassengerService10 service = serviceImpl.getCreateOrderByPassengerServiceImpl10Port();
		CreateOrderByPassengerReply reply = service.createOrderByPassenger(cr);
		if (reply.getReturnCode().equals("S")) {
				// 航班信息
				JourneySheet js = (JourneySheet) SessionUtil.getSession(request).getAttribute("JourneySheet");
				String userId = SessionUtil.getString(request,Constants.SESSION_USER_ID);
				// 订单总金额
				double toltalMoney  = (wbp.getFuelTax() + wbp.getAirportTax() + wbp.getParPrice())* li.size();
				// 联系电话
				String mobile = contacts.getLinkphone();
				String journeysheet_id = js == null ? null : js.getJourneysheet_id().toString();
				// 乘客信息
				airLineService.insertOrderInfo(ao, contacts.getContact_id().toString(), journeysheet_id, passenger, userId,toltalMoney, mobile, orderId);
				SessionUtil.getSession(request).removeAttribute("contacts");
				SessionUtil.getSession(request).removeAttribute("passengers");
				SessionUtil.getSession(request).removeAttribute("JourneySheet");
				return "redirect:"+basePath()+"airline/ok.action?orderId="+orderId+"&toltalMoney="+toltalMoney;
			} else {
				return "redirect:"+basePath()+"airline/fail.action?message="+java.net.URLEncoder.encode(reply.getReturnMessage(),"UTF-8");
		}

	}
	//提交成功跳转页面
	@RequestMapping(value = "airline/ok", method = RequestMethod.GET)
	public String orderOk(String orderId,String toltalMoney,ModelMap model){
		//订单成功页面显示信息
		model.addAttribute("orderId", orderId);
		model.addAttribute("toltalMoney", toltalMoney);
		return "/outapi/airline/order/ok";
	}
	//提交失败跳转页面
	@RequestMapping(value = "airline/fail", method = RequestMethod.GET)
	public String orderFail(String message,ModelMap model) throws UnsupportedEncodingException{
		model.addAttribute("returnMessage", java.net.URLDecoder.decode(message, "UTF-8"));
		return "/outapi/airline/order/failed";
	}
	// 构建CreateOrderByPassengerRequest 对象
	private CreateOrderByPassengerRequest buildCreateOrderByPassengerRequest(
			String policyId, Contacts contacts, WsBookPnr wbp, String orderId) {

		CreateOrderByPassengerRequest cr = new CreateOrderByPassengerRequest();
		cr.setAgencyCode(NAME);
		cr.setSign(Md5.getMD5((NAME + policyId + PWD).getBytes()));
		cr.setPolicyId(policyId);
		cr.setLinkMan(contacts.getLinkman());
		cr.setLinkPhone(contacts.getLinkphone());
		cr.setOtherLinkMethod(contacts.getOtherLinkMethod());
		cr.setNotifiedUrl("http://www.m.txmp.com.cn");
		cr.setPaymentReturnUrl("http://www.m.txmp.com.cn");
		cr.setOutOrderNo(orderId);
		cr.setAllowSwitchPolicy(0);
		cr.setNeedSpeRulePolicy(0);
		cr.setB2CClientPay("0");
		cr.setB2CProfit("0");
		cr.setPnrInfo(wbp);
		return cr;
	}
	
	public class AirPort{
		private String dstAirPort;
		private String arrAirPort;
		public String getDstAirPort() {
			return dstAirPort;
		}
		public void setDstAirPort(String dstAirPort) {
			this.dstAirPort = dstAirPort;
		}
		public String getArrAirPort() {
			return arrAirPort;
		}
		public void setArrAirPort(String Port) {
			this.arrAirPort = Port;
		}
	}
}
