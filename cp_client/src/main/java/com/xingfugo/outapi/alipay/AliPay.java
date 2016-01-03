package com.xingfugo.outapi.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingfugo.business.outapi.alipay.util.AlipayNotify;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.outapi.movie.MovieController;
//支付宝支付订单
@Controller
public class AliPay {
	private static Logger LOG = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private GoodsorderService goodsorderService;

	//使用支付宝支付订单
	@RequestMapping(value="alipay/updateOrder")
	public void aliPay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		PrintWriter out = response.getWriter();
		response.setHeader("Content-type","application/json;charset=UTF-8");
		out = response.getWriter();
		if(AlipayNotify.verify(params)){//验证成功
			if(trade_status.equals("TRADE_FINISHED")){
				Integer  aliAayState= goodsorderService.unionPayOrder(out_trade_no);
				LOG.debug("通知状态{}", aliAayState);
			} else if (trade_status.equals("TRADE_SUCCESS")){
				
			}
			out.println("success");	//请不要修改或删除
		}else{//验证失败
			out.println("fail");
		}
		
	}
}
