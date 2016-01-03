package com.xingfugo.outapi.nocardpay;


import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xingfugo.business.outapi.nocardpay.service.NoCardPayService;
import com.xingfugo.controller.BaseController;

/**
 * @author wyl
 *	无卡支付
 */
@Controller
public class NoCardPayController extends BaseController{
	
	private static Logger LOG = LoggerFactory.getLogger(NoCardPayController.class);
	
	@Autowired
	private NoCardPayService noCardPayService;
	//跳转支付页面
	@RequestMapping(value="nocardpay/toPayPage",method=RequestMethod.GET)
	public String toPayPage(String orderNo,ModelMap model){
		model.addAttribute("orderNo", orderNo);
		return "nocardpay/nocardpay";
	}
	
	/**
	 * 确认付款
	 * @throws Exception 
	 * @throws JSONException 
	 */
	@RequestMapping(value="nocardpay/payOrder")
	public String payOrder(String cartType ,String trk,String despin,String sfzh,String name,String phone,String cvn2,String yymm,String orderNo,ModelMap model) throws JSONException, Exception{
		String payRestult =noCardPayService.noCardPay(cartType, trk, despin, sfzh, name, phone, cvn2, yymm, orderNo);
		LOG.debug("交易返回的状态{}", payRestult);
		model.addAttribute("payRestult", payRestult);
		return "nocardpay/ok";
	}
	
	/**
	 * 填写卡号信息
	 */
	@RequestMapping(value="nocardpay/cardInfo")
	public String cardInfo(String card,String cardNo,ModelMap model,String orderNo){
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("cartType", card);
		
		if(card.equals("1")||card=="1"){
			return "nocardpay/xyCard";
		}else{
			return "nocardpay/jjCard";
		}
	}
	
}
