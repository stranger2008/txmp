package com.xingfugo.business.outapi.nocardpay.service;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.module.Order;
import com.xingfugo.business.outapi.nocardpay.pay.NoCardPay;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.OrderService;

/**
 * @author wyl
 *	无卡支付
 */
@Service
public class NoCardPayService {
		
		private  static String M_KEY;
		
		private static String POST_LOGIN;
		
		private static String POST_PAY;
		
		private static String USERNAME;
		
		private static String  USERPWD;
		//读取文件名称
		private static final String CONF_FILE_NAME = "noCardPay.properties";
		
		//授权秘钥
		private static final String KEY_M_KEY ="m_key";
		//登录地址
		private static final String KEY_POST_LOGIN="post_login";
		//交易地址
		private static final String KEY_POST_PAY="post_pay";
		//登录名称
		private static final String KEY_USERNAME="username";
		//密码
		private static final String KEY_USERPWD="userpwd";
		@Autowired
		private OrderService orderService;
		@Autowired
		private GoodsorderService goodsorderService;

		/**
		 * @param cartType 	支付方式
		 * @param trk		支付卡号
		 * @param despin 	支付密码
		 * @param sfzh		身份证号
		 * @param name		姓名
		 * @param phone		电话
		 * @param cvn2		信用卡背后三位数
		 * @param yymm 		办卡日期
		 * @param orderNo	订单号
		 * @return
		 * 无卡支付交易
		 * @throws Exception 
		 * @throws org.json.JSONException
		 */
		public String  noCardPay(String cartType ,String trk,String despin,String sfzh,String name,String phone,String cvn2,String yymm,String orderNo) throws JSONException, Exception{
			
			if(StringUtils.isBlank(cartType)){
				return "支付类型不能为空";
			}
			if(StringUtils.isBlank(trk)){
				return "卡号不能为空";
			}
			if(StringUtils.isBlank(despin)){
				return "密码不能为空";
			}
			if(StringUtils.isBlank(sfzh)){
				return "身份证号码不能为空";
			}
			boolean jjk=cartType.equals("1")?false:true;
			if(!jjk){
				if(StringUtils.isBlank(cvn2)){
					return "信用卡背后签名不能为空";
				}
				if(StringUtils.isBlank(yymm)){
					return "日期不能为空";
				}
			}
			
			//判断订单号码
			if(StringUtils.isBlank(orderNo)){
				return "订单号不能为空";
			}
			Order order =orderService.selectByPrimaryKey(orderNo);
			//获取订单金额
			String money=null; 
			if(order!=null){
				money =order.getGoodsAmount().toString();
			 }else{
				return "没有查到该订单！";
			}
			
			//交易完成后短信提醒
			String smsPhone=phone,sms="您尾号"+trk.substring(trk.length()-4,trk.length())+"的卡于[jysj]消费"+money+"元，交易[jyjg] ，易流水号为[jylsh]";
			//获取token 验证
			NoCardPay noCardPay =  NoCardPay.NewObj(M_KEY, POST_LOGIN, POST_PAY, USERNAME,USERPWD);
			if(noCardPay.GetTokenIsFailure())
			{
				StringBuilder result=new StringBuilder();
				boolean rst=false;
				if(jjk){
					rst = noCardPay.Pay("BJWK",orderNo, trk, despin, money, "", "",  phone, sfzh, "", smsPhone, sms,  result,"");
				  }else{
					rst = noCardPay.Pay("BJWK",orderNo, trk, "", money, cvn2, yymm, phone, sfzh, name, smsPhone, sms,  result,"");
				} 
	            if(rst)
	            {
	            	JSONObject jsonObj = new JSONObject(result.toString());
	            	String status = jsonObj.getString("status");
	            	if("1".equals(status))
	            	 {
	            		 String __rst = jsonObj.getString("result");
	            		 String serialnumber = jsonObj.getString("serialnumber");
	            		 if(__rst.isEmpty() || "-1".equals(__rst.split("_")[0]))
	            		 {
	            			 //进行冲正
	            			 if(jjk){
	            				 rst = noCardPay.Pay("BJWKCZ",orderNo, trk, despin, money, "", "",  phone, sfzh, "", smsPhone, sms,  result,serialnumber);
	            			 }else {
	            				 rst = noCardPay.Pay("BJWKCZ",orderNo, trk, "", money, cvn2, yymm, phone, sfzh, name, smsPhone, sms,  result,serialnumber);
	            			 }
	            			 return"支付失败冲正返回"+rst+"--"+result.toString();    
	            		 }else{
	            			 if("0".equals(__rst.split("_")[0])&&"00".equals(__rst.split("_")[9])){
	            				Integer payStatus=goodsorderService.unionPayOrder(orderNo);
	            				if(payStatus==1){
	            					return "交易成功!";
	            				}else{
	            					return "支付成功！订单状态修改失败！";
	            				}
	            			 }else{
	            				 return "交易失败:"+rst+"--"+result.toString();
	            			 }
	            		 }    
	            	 }else{
	            		 return"交易失败！"+jsonObj.getString("msg");
	            	 }
	            }
			}
			return "登录验证失败！交易失败！";
		}
		
		static {
	        try {
				InputStream fis = NoCardPayService.class.getClassLoader().getResourceAsStream(CONF_FILE_NAME);
				PropertyResourceBundle props = new PropertyResourceBundle(fis);
				M_KEY = props.getString(KEY_M_KEY);
				POST_LOGIN = props.getString(KEY_POST_LOGIN);
				POST_PAY = props.getString(KEY_POST_PAY);
				USERNAME = props.getString(KEY_USERNAME);
				USERPWD = props.getString(KEY_USERPWD);
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
	    }
}
