package com.xingfugo.web.seller.chinapay;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.module.Fundrecharge;
import com.xingfugo.business.service.FundaccountService;
import com.xingfugo.business.service.FundrechargeService;
import com.xingfugo.web.seller.controller.BaseController;

//无卡支付
@Controller
public class PaymentController extends BaseController{
	
	@Autowired
	private FundrechargeService fundrechargeService;
	@Autowired
	private FundaccountService fundaccountService;
	
	//充值支付
	@RequestMapping(value="fundaccount/recharge-payment",method=RequestMethod.GET)
	public String rechargePayment(String order_no,ModelMap model){
		if(order_no == null){
			return "404";
		}
		
		//查询充值记录
		Fundrecharge fundrecharge = fundrechargeService.getFundrechargeByOrder_no(order_no);
		if(fundrecharge == null){
			return "404";
		}
		
		//商户提交给ChinaPay的交易订单号，16位长度，必填
	    //订单交易金额，12位长度，左补0，必填,单位为分
	    BigDecimal fund_num = fundrecharge.getFund_num();
	    //交易金额
	    String TransAmt = dbPrice2payPrice(fund_num);
	    //根目录
	    String rootPath = this.getRequest().getRequestURL().toString().replace("fundaccount/recharge-payment.action", "");
	    
	    //业务操作url
	    String BgRetUrl = rootPath + "rechargeOkNotifyUrl.action";
	    
	    //返回页面url
	    String PageRetUrl = rootPath + "fundaccount/index.action";
	    
	    //银联支付信息
		Map<String,String> payMap =  PayUtil.getPayMap(TransAmt, order_no, PayUtil.getClientIp(this.getRequest()), BgRetUrl, PageRetUrl);
		if(PayUtil.PAYMENT_INFO_IS_NORMAL_FALSE.equals(payMap.get(PayUtil.PAYMENT_INFO_IS_NORMAL))) {
			return "404";
		}
		
		model.addAttribute("payment",payMap);
		
		return "chinapay/pay";
	}
	
	//充值成功
	@RequestMapping(value="rechargeOkNotifyUrl",method={RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String rechargeOkNotifyUrl(HttpServletRequest request){
		System.out.println("网银支付返回网站关口...");
		String TransDate = null;
		String MerId = null;
		String OrdNo = null;
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
			OrdNo = request.getParameter("orderno");
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
			flag1 = t.verifyTransResponse(MerId, OrdNo, TransAmt, CuryId,
					TransDate, TransType, OrderStatus, ChkValue);

			if (flag1 == false) {
				System.out.println("交易验证失败");
				msg = "交易验证失败";
			} else {
				//充值单号
				String order_no = Priv1;
				if(OrderStatus!=null && OrderStatus.equals("1001")){
					//资金充值
					fundaccountService.rechargeFundaccountOK(order_no);
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
	
	//把数据库取出的BigDecimal格式的价格格式转换成网银接口的价格格式
	public String dbPrice2payPrice(BigDecimal dbPrice) {
		if(dbPrice == null) {
			return dbPrice2payPrice("0");
		}
		dbPrice = dbPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		return dbPrice2payPrice(dbPrice.toString());
	}
	
}
