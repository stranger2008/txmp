package com.xingfugo.outapi.unionpay;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.outapi.unionpay.UnionPayService;
import com.xingfugo.outapi.movie.MovieController;


@Controller
public class UnionPayController {
	private static Logger LOG = LoggerFactory.getLogger(MovieController.class);
	
	private static final String NOTIFY_URL="http://119.161.187.240/xfg_cp_client/unionpay/unionPayNotify.action?";
	@Autowired
	private UnionPayService unionPayService;
	//支付通知
	@SuppressWarnings("unchecked")
	@RequestMapping(value="unionpay/unionPayNotify",method=RequestMethod.GET)
	public String unionPayNotify(HttpServletRequest request,ModelMap model){
		Map requestParams = request.getParameterMap();
		Integer rspCode =null;
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String code = (String) iter.next();
			//支付成功
			if(code=="0"||"0".equals(code)){
				rspCode =0;
				break;
			}
			//取消支付
			if(code=="-1"||"-1".equals(code)){
				rspCode =-1;  
				break;
			}
			//支付失败
			if(code=="1"||"1".equals(code)){
				rspCode =1;
				break;
			}
		}
		model.addAttribute("rspCode",rspCode);
		return "unionpay";
	}
	//确认支付
	@RequestMapping("unionpay/unionPayOrder")
	public String payMovieOrder( String orderNo, ModelMap modelMap) throws UnsupportedEncodingException{
		Map<String,String> map= unionPayService.buildMap(orderNo);//组装支付请求参数
		String rspPaycod =unionPayService.unionPay(map);//获取交易流水号
		 if(StringUtils.isNotBlank(rspPaycod)&&rspPaycod.contains("respCode=00")){ //00表示获取成功
			 modelMap.addAttribute("paydata",unionPayService.buildPay(rspPaycod, NOTIFY_URL,true));
			 return "orderPay";
		 }else{
			 return "/order/failed";
		 }
	}
	//支付通知
	@RequestMapping(value="unionpay/updateOrder",method=RequestMethod.POST)
	public void updateOrder(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer updateStatus=unionPayService.updateOrder(request.getParameterMap(), request.getParameter("transStatus"), request.getParameter("reqReserved"));
		if(updateStatus==1){
			PrintWriter out = response.getWriter();
			try {
				response.setHeader("Content-type","application/json;charset=UTF-8");
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.write("success");
		}
		LOG.debug("通知状态{}", updateStatus);
	}
}
