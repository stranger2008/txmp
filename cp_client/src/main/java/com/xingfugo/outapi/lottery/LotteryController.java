package com.xingfugo.outapi.lottery;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.outapi.lottery.module.CommonParameters;
import com.xingfugo.business.outapi.lottery.module.CurrentInfoRequest;
import com.xingfugo.business.outapi.lottery.module.CurrentInfoResponse;
import com.xingfugo.business.outapi.lottery.module.DoubleChromosphere;
import com.xingfugo.business.outapi.lottery.module.LotteryAccount;
import com.xingfugo.business.outapi.lottery.service.LotteryAccountService;
import com.xingfugo.business.outapi.lottery.service.LotteryService;
import com.xingfugo.business.outapi.unionpay.UnionPayService;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.controller.BaseController;
import com.xingfugo.util.DateUtils;

//彩票业务
@Controller
public class LotteryController extends BaseController{
	// 请求IP
	private static final  String IP="223.72.255.168";
	// 端口
	private static final  String PORT="8888";
	// 报文交换字符编码
	private static final  String REQCHARSET="utf-8";
	// 授权商户编号
	private static final  String MERCHANTNUM="8049";
	//授权Key
	private static final  String MERCHANTKEY="8049";
	
	@Autowired
	private  LotteryService lotteryService;
	
	@Autowired
	private  LotteryAccountService lotteryAccountService;
	@Autowired
	private UnionPayService unionPayService;
	
	//彩票首页
	@RequestMapping(value="lottery/index",method=RequestMethod.GET)
	public String index(){
		return "outapi/lottery/index";
	}
	
	//开奖公告
	@RequestMapping(value="lottery/notice",method=RequestMethod.GET)
	public String notice(ModelMap model,String period){
		
		CurrentInfoRequest  cr =new CurrentInfoRequest();
		cr.setMerchantnum(MERCHANTNUM);
		cr.setRequesttime(DateUtils.getCurrDateTimeStr());
		cr.setRequesttype("1");
		cr.setGameid("4");
		
		CurrentInfoResponse qlc=	lotteryService.getCurrentInfo(cr, IP, PORT, REQCHARSET,  MERCHANTKEY);//七乐彩查询请求
		
		if(qlc!=null){
			//七乐彩开奖号码
			model.addAttribute("qlc", lotteryService.notice(cr.getGameid(),  (Integer.parseInt(qlc.getCurrperiod())-1)+"", MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET));
			model.addAttribute("qlcInfo",qlc);
		}
		cr.setGameid("6");
		CurrentInfoResponse fc3d=	lotteryService.getCurrentInfo(cr, IP, PORT, REQCHARSET,  MERCHANTKEY);//3D查询请求
		if(fc3d!=null){
			//福彩3D开奖号码
			model.addAttribute("fc3d",lotteryService.notice(cr.getGameid(), (Integer.parseInt(fc3d.getCurrperiod())-1)+"", MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET));
			model.addAttribute("fc3dInfo",fc3d);
		}
		cr.setGameid("3");
		CurrentInfoResponse ssq=	lotteryService.getCurrentInfo(cr, IP, PORT, REQCHARSET,  MERCHANTKEY);//双色球查询请求
		if(ssq!=null){
			//双色球开奖号码
			model.addAttribute("ssq", lotteryService.notice(cr.getGameid(),(Integer.parseInt(ssq.getCurrperiod())-1)+"", MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET));
			model.addAttribute("ssqInfo",ssq);
		}
		cr.setGameid("2");
		CurrentInfoResponse pks=	lotteryService.getCurrentInfo(cr, IP, PORT, REQCHARSET,  MERCHANTKEY);//PK10查询请求
		if(pks!=null){
			//pk拾开奖号码
			model.addAttribute("pks", lotteryService.notice(cr.getGameid(),(Integer.parseInt(pks.getCurrperiod())-1)+"", MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET));
			model.addAttribute("pksInfo",pks);
		}
		cr.setGameid("1");
		CurrentInfoResponse klb=	lotteryService.getCurrentInfo(cr, IP, PORT, REQCHARSET,  MERCHANTKEY);//快乐8查询请求
		if(klb!=null){
			//快乐8开奖号码
			model.addAttribute("klb", lotteryService.notice(cr.getGameid(),(Integer.parseInt(klb.getCurrperiod())-1)+"", MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET));
			model.addAttribute("klbInfo",klb);
		}
		return "outapi/lottery/notice";
	}
	
	//采种介绍
	@RequestMapping(value="lottery/desc",method=RequestMethod.GET)
	public String desc(){
		return "outapi/lottery/desc";
	}
	
	//帮助中心
	@RequestMapping(value="lottery/help",method=RequestMethod.GET)
	public String help(){
		return "outapi/lottery/help";
	}
	
	//进入彩票购买页面 
	@RequestMapping(value="lottery/lotteryInfo",method=RequestMethod.GET)
	public String  lotteryInfo(String gameid,ModelMap model){
		if(StringUtils.isNotBlank(gameid)){
			CurrentInfoRequest  cr =new CurrentInfoRequest();
			cr.setGameid(gameid);
			cr.setMerchantnum(MERCHANTNUM);
			cr.setRequesttime(DateUtils.getCurrDateTimeStr());
			cr.setRequesttype("1");
			CurrentInfoResponse crep=	lotteryService.getCurrentInfo(cr, IP, PORT, REQCHARSET,  MERCHANTKEY);//查询请求
			model.addAttribute("crep",crep);
			switch(Integer.parseInt(gameid)){
				case 1:
					return "outapi/lottery/klb/klb";
				case 2:
					return "outapi/lottery/pks/pks";
				case 3:
					long getTime =lotteryService.getSubtract(crep.getCurrendtime());
					if(getTime==0){
						return "outapi/lottery/index";
					}
					//开奖倒计时停售
					model.addAttribute("time",getTime);
					return "outapi/lottery/ssq/doubleChromosphere";
				case 4:
					return "outapi/lottery/qlc/qlc";
				case 6:
					//开奖倒计时停售
					model.addAttribute("time",lotteryService.getSubtract(crep.getCurrendtime()));
					//下期期号
					model.addAttribute("nextCurrperiod",lotteryService.nextCurrperiod(crep.getCurrperiod()));
					// 距离下期销售时间
					model.addAttribute("nextSaleTime",lotteryService.nextSaleTime(crep.getCurrstarttime()));
					return "outapi/lottery/3d/fucai3d";
				}
		}
		return "outapi/lottery/index";
	}
	//进入福彩3D订单信息页面 
	@RequestMapping(value="lottery/fucai3dOrderInfo",method=RequestMethod.POST)
	public String fucai3dOrderInfo(){
		return "outapi/lottery/3d/fucai3dOrderInfo";
	}
	
	// 进入福彩3D订单提交页面
	@RequestMapping(value="lottery/fucai3dorder",method=RequestMethod.GET)
	public String fucai3dOrder(HttpServletRequest request,ModelMap model){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		LotteryAccount lotteryAccount=null;
		if(StringUtils.isNotBlank(userId)){
			lotteryAccount =lotteryAccountService.getLotteryAccountByUserId(userId);
		}
		model.addAttribute("lotteryAccount",lotteryAccount);
		return "outapi/lottery/3d/fucai3dOrder";
	}
	
	// 福彩3D订单提交页面
	@RequestMapping(value="lottery/fucai3dSubmitorder",method=RequestMethod.POST)
	public String fucai3dSubmitorder(HttpServletRequest request,CommonParameters cps,LotteryAccount lotteryAccount,ModelMap model,String period,String fucaiBalls,String betmultir,String currendtime) throws UnsupportedEncodingException{
		 String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		 if(StringUtils.isBlank(userId)){
			return "redirect:"+basePath()+"lottery/fucai3dOrderInfo.action";
		 }
		 String statusCode= lotteryService.fucai3dSubmitorder(cps, lotteryAccount, fucaiBalls, userId, MERCHANTNUM, period,betmultir,currendtime, MERCHANTKEY,  IP, PORT, REQCHARSET);
		 if(statusCode.contains("001")){//001表示订单提交成功
			 
			 String[] rspCode=statusCode.split("#");
			 String orderNumber =rspCode[1].split("\\:")[1];//订单号
			 Map<String,String> map= unionPayService.buildMap(orderNumber);//组装支付请求参数
			 String rspPaycod =unionPayService.unionPay(map);//获取交易流水号
			 
			 if(StringUtils.isNotBlank(rspPaycod)&&rspPaycod.contains("respCode=00")){ //00表示获取成功
				 model.addAttribute("paydata",unionPayService.buildPay(rspPaycod, "http://119.161.187.240/xfg_cp_client/notify_url.jsp",true));
				 return "orderPay";
			 }else{
				 return "outapi/lottery/3d/fucai3dOrderInfo";
			 }
		 }
		 return "outapi/lottery/3d/fucai3dOrderInfo";
	}
	
	// 进入双色球订单页面
	@RequestMapping(value="lottery/doubleChromosphereOrder",method=RequestMethod.GET)
	public String doubleChromosphereOrder(){
		return "outapi/lottery/ssq/doubleChromosphereOrder";
	}
	
	// 进入双色球订单确认页面
	@RequestMapping(value="lottery/doubleChromosphereorderInfo",method=RequestMethod.GET)
	public String  doubleChromosphereOrderInfo(HttpServletRequest request,ModelMap model){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		LotteryAccount lotteryAccount=null;
		if(StringUtils.isNotBlank(userId)){
			lotteryAccount =lotteryAccountService.getLotteryAccountByUserId(userId);
		}
		model.addAttribute("lotteryAccount",lotteryAccount);
		return "outapi/lottery/ssq/doubleChromosphereOrderInfo";
	}

	//提交订单 进入双色球支付页面 
	@RequestMapping(value="lottery/payOrder",method=RequestMethod.POST)
	public String payOrder(HttpServletRequest request,CommonParameters cps,LotteryAccount lotteryAccount,DoubleChromosphere dp,ModelMap model,String period,String redandblue,String currendtime){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(userId)){
			return "redirect:"+basePath()+"lottery/fucai3dOrderInfo.action";
		}
		 //提交订单
		String statusCode= lotteryService.submintOrderData(cps, lotteryAccount, dp, period, redandblue, userId,currendtime, MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET, REQCHARSET);
		 if(!statusCode.contains("001")){ //001表示订单提交成功 
			 return "outapi/lottery/ssq/doubleChromosphereOrderInfo";
		 }
		return "orderPay";
		
	}
	
	//进入七乐彩订单确认页面
	@RequestMapping(value="lottery/qlcorder",method=RequestMethod.GET)
	public String qlcOrder(HttpServletRequest request,ModelMap model){
			String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
			LotteryAccount lotteryAccount=null;
			if(StringUtils.isNotBlank(userId)){
				lotteryAccount =lotteryAccountService.getLotteryAccountByUserId(userId);
			}
			model.addAttribute("lotteryAccount",lotteryAccount);
			return 	"outapi/lottery/qlc/qlcOrder";
	}
	
	//七乐彩提交订单
	@RequestMapping(value="lottery/qlcsubmitorder",method=RequestMethod.POST)
	public String qlcsubmitorder(ModelMap model,CommonParameters cp,LotteryAccount lotteryAccount,HttpServletRequest request,String betmultir,String period,String ballList,String currendtime,String boards){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(userId)){
			return "redirect:"+basePath()+"lottery/fucai3dOrderInfo.action";
		}
		String statusCode =lotteryService.qlcsubmitorder(cp, lotteryAccount, betmultir, period, ballList, userId,currendtime,boards, MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET);
		 if(statusCode.contains("001")){ //001表示订单提交成功 
			 return "orderPay";
		 }
			return "redirect:"+basePath()+"lottery/qlcorder.action";
	}
	
	//进入PK拾订单确认页面
	@RequestMapping(value="lottery/pksorder",method=RequestMethod.GET)
	public String pksOrder(HttpServletRequest request,ModelMap model){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		LotteryAccount lotteryAccount=null;
		if(StringUtils.isNotBlank(userId)){
			lotteryAccount =lotteryAccountService.getLotteryAccountByUserId(userId);
		}
		model.addAttribute("lotteryAccount",lotteryAccount);
		return 	"outapi/lottery/pks/pksOrder";
		
	}
	

	//进入pk拾订单确认页面
	@RequestMapping(value="lottery/pksSubmitOrder",method=RequestMethod.POST)
	public String pksSubmitOrder(ModelMap model,CommonParameters cp,LotteryAccount lotteryAccount,HttpServletRequest request,String betmultir,String period,String ballList,String currendtime ,String boards){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(userId)){
			return "redirect:"+basePath()+"lottery/fucai3dOrderInfo.action";
		}
		String statusCode =lotteryService.pksSubmitOrder(cp, lotteryAccount, betmultir, period, ballList, userId, currendtime,boards,MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET);
		
		System.out.println(statusCode);
		if(statusCode.contains("001")){ //001表示订单提交成功 
			 return "orderPay";
		 }
		return "redirect:"+basePath()+"lottery/pksorder.action"; 
	}
	
	//进入快乐8订单确认页面
	@RequestMapping(value="lottery/klborder",method=RequestMethod.GET)
	public String klbOrder(HttpServletRequest request,ModelMap model){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		LotteryAccount lotteryAccount=null;
		if(StringUtils.isNotBlank(userId)){
			lotteryAccount =lotteryAccountService.getLotteryAccountByUserId(userId);
		}
		model.addAttribute("lotteryAccount",lotteryAccount);
		return 	"outapi/lottery/klb/klbOrder";
	}
	
	//进入快乐8订单确认页面
	@RequestMapping(value="lottery/klbSubmitOrder",method=RequestMethod.POST)
	public String klbSubmitOrder(ModelMap model,CommonParameters cp,LotteryAccount lotteryAccount,HttpServletRequest request,String betmultir,String period,String ballList,String currendtime,String boards){
		String userId =SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(userId)){
			return "redirect:"+basePath()+"lottery/fucai3dOrderInfo.action";
		}
		String statusCode =lotteryService.klbSubmitOrder(cp, lotteryAccount, betmultir, period, ballList, userId,currendtime,boards,MERCHANTNUM, MERCHANTKEY, IP, PORT, REQCHARSET);
		if(statusCode.contains("001")){ //001表示订单提交成功 
			 return "orderPay";
		 }
		return "redirect:"+basePath()+"lottery/klborder.action"; 
	}
	
	
	
}
