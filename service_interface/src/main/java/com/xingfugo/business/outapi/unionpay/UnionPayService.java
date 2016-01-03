package com.xingfugo.business.outapi.unionpay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.module.Order;
import com.xingfugo.business.outapi.unionpay.conf.UpmpConfig;
import com.xingfugo.business.outapi.unionpay.service.UpmpService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.OrderService;


/**
 * @author wyl
 *	用户支付
 */
@Service
public class UnionPayService {
	
	@Autowired
	private OrderService orderService;
	
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	//订单推送请求接口实例类文件
	public  String unionPay(Map<String, String> map){
		Map<String, String> resp = new HashMap<String, String>();
		return UpmpService.trade(map, resp);
	}
	
	//拼装Map
	public  Map<String,String> buildMap(String orderNumber){
		String orderAmount=null;
		if(StringUtils.isNotBlank(orderNumber)){
			orderAmount =getOrderMoneyByOrderId(orderNumber).toString();
		}
	     // 请求要素
		Map<String, String> req = new HashMap<String, String>();
		req.put("version", UpmpConfig.VERSION);// 版本号
		req.put("charset", UpmpConfig.CHARSET);// 字符编码
		req.put("transType", "01");// 交易类型
		req.put("merId", UpmpConfig.MER_ID);// 商户代码
		req.put("backEndUrl", UpmpConfig.MER_BACK_END_URL);// 通知URL
		req.put("frontEndUrl", UpmpConfig.MER_FRONT_END_URL);// 前台通知URL(可选)
		req.put("orderDescription", "");// 订单描述(可选)
		req.put("orderTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// 交易开始日期时间yyyyMMddHHmmss
		req.put("orderTimeout", "");// 订单超时时间yyyyMMddHHmmss(可选)
		req.put("orderNumber", orderNumber);//订单号(商户根据自己需要生成订单号)
		req.put("orderAmount", orderAmount);// 订单金额
        req.put("orderCurrency", "156");// 交易币种(可选)
        req.put("reqReserved", orderNumber);// 请求方保留域(可选，用于透传商户信息)
        return req;
	} 
	//通过订单号查找订单金额
	public   Integer getOrderMoneyByOrderId(String orderNo){
		if(StringUtils.isNotBlank(orderNo)){
			Order order =orderService.selectByPrimaryKey(orderNo);
			if(order!=null){
				return (order.getGoodsAmount().multiply(new BigDecimal(100))).intValue();
			}
		}
		return null;
	}
	
	//返回流水号
	public  String buildTn(String trade){
		if(StringUtils.isNotBlank(trade)&&trade.contains("respCode=00")){
			return trade.split("&")[1].split("\\=")[1];
		}
		return null;
	}
	
	//订单支付字符串拼装
	@SuppressWarnings("deprecation")
	public  String   buildPay(String tn,String resultURL,boolean usetestmode) throws UnsupportedEncodingException{
		tn=tn.split("&")[1].split("\\=")[1];
		String paydata = "tn="+tn+",resultURL="+resultURL+",usetestmode=false";
		paydata = new String(Base64.encodeBase64(paydata.getBytes("utf-8")));
		paydata = URLEncoder.encode(paydata, "utf-8");
		return paydata;
	}
	
	
	//交易完成返回的状态成功则修改订单支付状态
	public Integer updateOrder(Map map,String transStatus,String orderNo){
		
		Map<String,String> params = new HashMap<String,String>();
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) map.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		if(UpmpService.verifySignature(params)){// 服务器签名验证成功
			//获取通知返回参数
			if (null != transStatus && transStatus.equals("00")) {
			    // 交易处理成功
				return goodsorderService.unionPayOrder(orderNo);
			} else {
				return 2;
			}
		}else{// 服务器签名验证失败
			return  3;
		}
	}
	
	
}
