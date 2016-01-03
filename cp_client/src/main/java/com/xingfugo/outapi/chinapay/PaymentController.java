package com.xingfugo.outapi.chinapay;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.controller.BaseController;

//无卡支付
@Controller
public class PaymentController extends BaseController{
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	@RequestMapping(value="orderOkNotifyUrl",method={RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String bgReturnUrl(HttpServletRequest request){
		System.out.println("网银支付返回网站关口...");
		String TransDate = null;
		String MerId = null;
		String OrdId = null;
		String TransType = null;
		String TransAmt = null;
		String CuryId = null;
		String ChkValue = null;
		String OrderStatus = null;
		String GateId = null;
		String Priv1 = null;
		String msg = "1";
		//try {
			TransDate = request.getParameter("transdate");
			MerId = request.getParameter("merid");
			OrdId = request.getParameter("orderno");
			TransType = request.getParameter("transtype");
			TransAmt = request.getParameter("amount");
			CuryId = request.getParameter("currencycode");
			OrderStatus = request.getParameter("status");
			ChkValue = request.getParameter("checkvalue");
			Priv1 = request.getParameter("Priv1");

			chinapay.PrivateKey key = new chinapay.PrivateKey();
			chinapay.SecureLink t;
			boolean flag;
			boolean flag1;
			
			flag = key.buildKey("999999999999999", 0,PayUtil.getThisPath()+"PgPubk.key");
			if (flag == false) {
				msg = "build key error!";
			}

			t = new chinapay.SecureLink(key);
			flag1 = t.verifyTransResponse(MerId, OrdId, TransAmt, CuryId,
					TransDate, TransType, OrderStatus, ChkValue);

			if (flag1 == false) {
				System.out.println("交易验证失败");
				msg = "交易验证失败";
			} else {
				
				String order_id = Priv1;
				if(OrderStatus!=null && OrderStatus.equals("1001")){
					//数据库订单状态操作
					goodsorderService.userPay(order_id);
					System.out.println("交易成功");
				}else{
					msg = "状态码："+OrderStatus+"，交易失败";
				}
				
				/* …... 数据库更新等相关处理过程 */
				//验证签名数据通过后，一定要判定交易状态代码是否为"1001"，实现代码请商户自行编写。
				//注意：如果您在提交时同时填写了页面返回地址和后台返回地址，且地址相同，请先做一次数据库查询判断订单状态，以防止重复处理该笔订单
			}
		//} catch (Exception e) {
		//	System.out.println(e.getMessage());
		//	msg = "交易失败";
		//}
		return msg;
	}
	
	//进入支付页面，收集数据，提交网银支付
	@RequestMapping(value="order/payment",method=RequestMethod.GET)
	public String payment(String order_id,ModelMap model){
		if(order_id == null){
			return "404";
		}
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(order_id);
		if(goodsorder == null){
			return "404";
		}
		
		//MerId为ChinaPay统一分配给商户的商户号，15位长度，必填
	    String MerId = PayUtil.MerId;
	    
	    //商户提交给ChinaPay的交易订单号，16位长度，必填
	    //String OrdId = "2014055012023325";
	    //本系统的订单号是17位，所以要减一位
	    String OrdId = order_id.substring(0,order_id.length() - 1);
	    
	    //订单交易金额，12位长度，左补0，必填,单位为分
	    String order_price = goodsorder.getTatal_amount();
	    String TransAmt = dbPrice2payPrice(order_price);
	    
	    //订单交易币种，3位长度，固定为人民币156，必填
	    String CuryId = "156";
	    
	    //订单交易日期，8位长度，必填
	    String TransDate = PayUtil.getOrderDate();
	    
	    //交易类型，4位长度，必填 0001：付款 0002：退款
	    String TransType = "0001";
	    
	    //加密字符串
	    String ChkValue = "";
	    
	    //接口版本号
	    String Version = "20100304";
		
		chinapay.PrivateKey key=new chinapay.PrivateKey();
		chinapay.SecureLink t;
		boolean flag;
		flag=key.buildKey(MerId,0,PayUtil.getThisPath()+"MerPrK_808080061794018_20140619093238.key");
		if (flag==false)
		{
			System.out.println("build key error!");
			return "404";
		}
		t=new chinapay.SecureLink (key);
		
		//由于网银支付接口的订单必须要是16位，但我们系统的订单号是17位
		//即不能用他们的订单号字段，所以用这个备用字段
		String Priv1 = order_id;
		String ClientIp = PayUtil.getClientIp(getRequest());
		
		String MsgBody = MerId+ OrdId+ TransAmt+ CuryId+ TransDate+ TransType + Priv1 + ClientIp;
		ChkValue = t.Sign(MsgBody);
		
		//ChkValue= t.signOrder(MerId, OrdId, TransAmt, CuryId, TransDate, TransType) ;
		
		String BgRetUrl = PayUtil.domain+"orderOkNotifyUrl.action";
		String PageRetUrl = PayUtil.domain+"user/orderlist.action";
		String GateId = "";
		
		Map<String,String> payMap = new HashMap<String,String>();
		payMap.put("MerId", MerId);
		payMap.put("OrdId", OrdId);
		payMap.put("TransAmt", TransAmt);
		payMap.put("CuryId", CuryId);
		payMap.put("TransDate", TransDate);
		payMap.put("TransType", TransType);
		payMap.put("Version", Version);
		payMap.put("BgRetUrl", BgRetUrl);
		payMap.put("PageRetUrl", PageRetUrl);
		payMap.put("GateId", GateId);
		payMap.put("Priv1", Priv1);
		payMap.put("ClientIp", ClientIp);
		payMap.put("ChkValue", ChkValue);
		
		model.addAttribute("payment",payMap);
		
		return "outapi/chinapay/pay";
	}
	
	//把数据库取出的价格格式转换成网银接口的价格格式
	public String dbPrice2payPrice(String order_price){
		String _price = order_price.replace(".", "");
		int _plen = 12 - _price.length();
		StringBuffer zeroSb = new StringBuffer();
		for(int i=0;i<_plen;i++){
			zeroSb.append("0");
		}
		zeroSb.append(_price);
		return zeroSb.toString();
	}
	
	
}
