package com.xingfugo.business.outapi.lottery.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.LotteryOrderDao;
import com.xingfugo.business.dao.OrderhistoryDao;
import com.xingfugo.business.module.Order;
import com.xingfugo.business.module.Orderhistory;
import com.xingfugo.business.outapi.lottery.module.CommonParameters;
import com.xingfugo.business.outapi.lottery.module.CurrentInfoRequest;
import com.xingfugo.business.outapi.lottery.module.CurrentInfoResponse;
import com.xingfugo.business.outapi.lottery.module.DoubleChromosphere;
import com.xingfugo.business.outapi.lottery.module.LotteryAccount;
import com.xingfugo.business.outapi.lottery.module.LotteryOrder;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.OrderService;
import com.xingfugo.util.DateUtils;
import com.xingfugo.util.Md5;
import com.xingfugo.util.TcpUtils;
import com.xingfugo.util.UUIDGenerator;

/**
 * @author wyl
 * 彩票接口
 */
@Service
public class LotteryService {
	
	@Autowired
	private LotteryAccountService lotteryAccountService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderhistoryDao orderhistoryDao;
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private LotteryOrderDao lotteryOrderDao;
	//类型 为彩票
	public static final String ORDER_TYPE_MOVIE = "lottery";
	
	//卖家会员标识
	public static final Integer SALE_CUST_ID = -1;
	/**
	 *当前期信息查询
	 */
	@SuppressWarnings("static-access")
	public CurrentInfoResponse getCurrentInfo(CurrentInfoRequest cr,String ip,String port,String reqCharset,String merchantkey){
		if(cr!=null){
			String md5Date =Md5.getMD5(("merchantnum="+cr.getMerchantnum()+"&gameid="+cr.getGameid()+"&requesttype="+cr.getRequesttype()+"&requesttime"+cr.getRequesttime()+"&merchantkey="+merchantkey).getBytes());
			String reqData="merchantnum="+cr.getMerchantnum()+"&gameid="+cr.getGameid()+"&requesttype="+cr.getRequesttype()+"&requesttime="+cr.getRequesttime()+"&checksum="+md5Date;
			//发送socket请求 返回数据
			TcpUtils tcp =new TcpUtils(ip,port,reqData,reqCharset);
			String rspData =tcp.sendTCPRequest();
			if(StringUtils.isNotBlank(rspData)){
				String crrentInfo[] =rspData.split("&");
				CurrentInfoResponse cre =new CurrentInfoResponse();
				for(int i =0;i<crrentInfo.length;i++){
					  String  filed=crrentInfo[i].split("=")[0];
					  if(filed=="resultcode"||"resultcode".equals(filed)){
						  cre.setResultcode(crrentInfo[i].split("=")[1]);
					  }
					  if(filed=="responsetime"||"responsetime".equals(filed)){
						  cre.setResponsetime(crrentInfo[i].split("=")[1]);
					  }
					  if(filed=="currsalestatus"||"currsalestatus".equals(filed)){
						  cre.setCurrsalestatus(crrentInfo[i].split("=")[1]);
					  }
					  if(filed=="currperiod"||"currperiod".equals(filed)){
						  cre.setCurrperiod(crrentInfo[i].split("=")[1]);
					  }
					  if(filed=="currstarttime"||"currstarttime".equals(filed)){
						  cre.setCurrstarttime(crrentInfo[i].split("=")[1]);
					  }
					  if(filed=="currendtime"||"currendtime".equals(filed)){
						  cre.setCurrendtime(crrentInfo[i].split("=")[1]);
					  }
					  if(filed=="currsalepromotion"||"currsalepromotion".equals(filed)){
						  cre.setCurrsalepromotion(crrentInfo[i].split("=")[1]);
					  }
					  if(filed=="gameid"||"gameid".equals(filed)){
						  cre.setGameid(crrentInfo[i].split("=")[1]);
					  }
						
					}
				return cre;
			}
		}
		return null;
	} 
	
	// 福彩3D获取currendtime减去当前时间
	public long getSubtract(String currendtime){
		if(StringUtils.isNotBlank(currendtime))
		{
			try {
				 long currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.getCurrDateTimeStr()).getTime();
				 long cdtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currendtime).getTime()-2100000;
				 long result= cdtime-currentTime>0?cdtime-currentTime:0;
				 return  result;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	// 下期期号
	public String nextCurrperiod(String nextCurrperiod){
		if(StringUtils.isNotBlank(nextCurrperiod)){
			return  (Integer.parseInt(nextCurrperiod)+1)+"";
		}
		return   null;
	}
	// 距离下期销售时间
	public String nextSaleTime(String nextSaleTime){
		if(StringUtils.isNotBlank(nextSaleTime)){
				  Calendar   c   =   Calendar.getInstance();   
				  Date date =DateUtils.format(nextSaleTime);
		          c.setTime(date);   
		          c.add(Calendar.DATE, 1);
		          Date   newDate   =   c.getTime();
	         return DateUtils.format(newDate)+" 09:00:00";
		}
		return null;
	}
	
	//用户注册
	public String regAccount( LotteryAccount lotteryAccount,String merchantnum,String merchantkey, String ip,String port,String reqCharset){
		String md5Data =Md5.getMD5((
						"merchantnum="+merchantnum
						+"&requesttime="+DateUtils.getCurrDateTimeStr()
						+"&requesttype=2"
						+"&wagercardnum="+lotteryAccount.getWagercardnum()
						+"&certificatetype="+lotteryAccount.getCertificatetype()
						+"&certificatenum="+lotteryAccount.getCertificatenum()
						+"&phonenum="+lotteryAccount.getPhonenum()
						+"&merchantkey="+merchantkey
					).getBytes());
		String reqData= "merchantnum="+merchantnum+
						"&requesttime="+DateUtils.getCurrDateTimeStr()+
						"&requesttype=2"+
						"&wagercardnum="+lotteryAccount.getWagercardnum()+
						"&certificatetype="+lotteryAccount.getCertificatetype()+
						"&certificatenum="+lotteryAccount.getCertificatenum()+
						"&phonenum="+lotteryAccount.getPhonenum()+
						"&checksum="+md5Data;
		TcpUtils tcp =new TcpUtils(ip,port,reqData,reqCharset);
		return tcp.sendTCPRequest();
	}
	
	//开奖查询
	public  String  notice(String gameid,String period,String merchantnum,String merchantkey,String ip,String port,String reqCharset){
		
		if(StringUtils.isNotBlank(gameid)&&StringUtils.isNotBlank(period)){
			
			String md5Data =Md5.getMD5((
					  	"merchantnum="+merchantnum
					  +"&requesttype=11"
					  +"&gameid="+gameid
					  +"&period="+period
					  +"&requesttime="+DateUtils.getCurrDateTimeStr()
					  +"&merchantkey="+merchantkey
				  ).getBytes());
			
			String sendData=
						"merchantnum="+merchantnum
					  +"&requesttype=11"
					  +"&gameid="+gameid
					  +"&period="+period
					  +"&requesttime="+DateUtils.getCurrDateTimeStr()
					  +"&checksum="+md5Data;	
			TcpUtils tcp =new TcpUtils(ip,port,sendData,reqCharset);
			return tcp.sendTCPRequest();
		}
		return null;
	}
	
	 //双色球订单提交
	@SuppressWarnings("unchecked")
	public String submintOrderData(CommonParameters cps,LotteryAccount lotteryAccount,DoubleChromosphere dp,String period,String redandblue,String userId,String currendtime,String merchantnum,String merchantkey,String ip,String port,String reqcharset,String reqCharset){
		//投注密码		
		String wagercardpwd=null;
		//投注卡号
		String wagercardnum =null;
		//查找用户是否已经注册
		LotteryAccount la=lotteryAccountService.getLotteryAccountByUserId(userId);
		
		if(la==null){
			wagercardnum =UUIDGenerator.getUUID().substring(0,12);
			lotteryAccount.setWagercardnum(wagercardnum);
			lotteryAccount.setUser_id(Integer.parseInt(userId));
			//注册用户
			String rspData= this.regAccount(lotteryAccount, merchantnum, merchantkey, ip, port, reqcharset);
			if(StringUtils.isNotBlank(rspData)&&rspData.split("&")[0].contains("001")){//001表示请求成功返回的结果
				lotteryAccount.setWagercardpwd(rspData.split("&")[1].split("\\=")[1]);
				//将用户保存进数据库
				lotteryAccountService.insertAccount(lotteryAccount);
				wagercardpwd =lotteryAccount.getWagercardpwd();
			}else{
				return "用户注册失败!";
			}
		}else{
			wagercardnum =la.getWagercardnum();
			wagercardpwd =la.getWagercardpwd();
		}
		//提交数据
		String statusCode =submintSSQOrder( wagercardnum, wagercardpwd, cps, lotteryAccount, dp, period, redandblue, merchantnum, merchantkey, ip, port, reqcharset, reqCharset);
		//手机号码
		String moblie =(la==null?lotteryAccount.getPhonenum():la.getPhonenum());
		
		Integer  total_amount =0;
		for(String balls:redandblue.split(";")){
			total_amount+=Integer.parseInt(balls.split("\\*")[1]);
			
		} 
		total_amount =total_amount*Integer.parseInt(dp.getBetmultir())*2;
		//设置订单属性
		Order order =setOrder( userId,moblie, new BigDecimal(total_amount));
		//设置lotteryOrder属性
		LotteryOrder lotteryOrder =setlotteryOrder( userId, resultcode(statusCode), period, cps.getGameid(),currendtime, dp.getBoards(), dp.getBetmultir(), total_amount.toString(), redandblue);
		//设置Orderhistory属性
		Orderhistory orderHistory =setOrderhistory(userId);
		//保存订单表，订单历史记录表 ，彩票订单表
		insertOrder(order,lotteryOrder,orderHistory);
		return statusCode;
	}
	//获取返回状态码
	public String resultcode(String statusCode){
		StringBuffer sb =new StringBuffer();
		if(StringUtils.isNotBlank(statusCode)){
			String[] status =statusCode.split(";");
			for(String s:status){
				if(StringUtils.isNotBlank(s)){
					sb.append(s.split(":")[1].split("&")[0].split("\\=")[1]+",");
				}
			}
			return sb.toString();
		}
		return null;
	}
	
	//设置Order属性
	public Order setOrder(String userId,String moblie,BigDecimal total_amount){
		Order o =new Order();
		o.setOrderId(goodsorderService.order_id());
		o.setBuyCustId(Integer.parseInt(userId));
	 	o.setMobile(moblie);
	 	o.setSaleCustId(SALE_CUST_ID);
	 	o.setGoodsAmount(total_amount);
	 	o.setShipFree(new BigDecimal(0.0));//配送费用
	 	o.setTatalAmount(total_amount);
	 	o.setBuyMode("0");//0表示正常购买
	 	o.setOrderTime(DateUtils.getCurrDate());
	 	o.setConsignee("");
	 	o.setAreaAttr("");
	 	o.setAddress("");
		return o;
	}
	//设置lotteryOrder属性
	public LotteryOrder setlotteryOrder(String userId,String resultcode,String period,String gameid,String end_date,String boards,String betmultir,String totalamount,String wagerdata){
		LotteryOrder lo =new LotteryOrder();
		lo.setCust_id(Integer.parseInt(userId));
		lo.setResultcode(resultcode);
		lo.setPeriod(period);
		lo.setGameid(gameid);
		lo.setSingle_date(DateUtils.getCurrDate());
		lo.setEnd_date(DateUtils.format(end_date));
		lo.setBoards(boards);
		lo.setBetmultir(betmultir);
		lo.setTotalamount(totalamount);
		lo.setWagerdata(wagerdata);
		lo.setIn_date(DateUtils.getCurrDate());
		return lo;
	}
	
	//设置Orderhistory属性
	public Orderhistory setOrderhistory(String user_id){
		Orderhistory os =new Orderhistory();
		os.setUser_id(null);
		os.setCust_id(user_id);
		os.setAction_name(goodsorderService.ACTION_BUYER_SUBMIT_ORDER);
		return os;
	}
	
	//设置红球和篮球的 请求格式
	public String submintSSQOrder(String wagercardnum,String wagercardpwd,CommonParameters cps,LotteryAccount lotteryAccount,DoubleChromosphere dp,String period,String redandblue,String merchantnum,String merchantkey,String ip,String port,String reqcharset,String reqCharset){
		
		String[] redAndBlueBalls =redandblue.split(";");
		List<String> redBallsList =new ArrayList<String>();//红复试 
		List<String> blueBallsList =new ArrayList<String>();//蓝复试 
		List<String> list =new ArrayList<String>();//标准投注
		
		for(String ball :redAndBlueBalls){
			if(StringUtils.isNotBlank(ball)){
				if(ball.split("\\:")[0].equals("1")){
					list.add(ball);
				}
				if(ball.split("\\:")[0].equals("2")){
					redBallsList.add(ball);
				}
				if(ball.split("\\:")[0].equals("3")){
					blueBallsList.add(ball);
				}
			}
		}
		StringBuffer sbResult =new StringBuffer();
		if(redBallsList.size()>0){
			sbResult.append("红复试投注返回结果:"+sendRetestSSQData(wagercardnum,wagercardpwd,redBallsList, cps, lotteryAccount, dp, period, merchantnum, merchantkey, ip, port, reqcharset, reqCharset)+";");
		}	
		if(blueBallsList.size()>0){
			sbResult.append("蓝复试投注返回结果:"+sendRetestSSQData(wagercardnum,wagercardpwd,blueBallsList, cps, lotteryAccount, dp, period, merchantnum, merchantkey, ip, port, reqcharset, reqCharset)+";");
		}	
		if(list.size()>0){
			sbResult.append("标准投注返回结果:"+sendStandardSSQData(wagercardnum,wagercardpwd,list, cps, lotteryAccount, dp, period, merchantnum, merchantkey, ip, port, reqcharset, reqCharset)+";");
		}	
		
		return  sbResult.toString();
	}
	
	//组装复试双色球数据格式
	public String  sendRetestSSQData(String wagercardnum,String wagercardpwd,List<String> list,CommonParameters cps,LotteryAccount lotteryAccount,DoubleChromosphere dp,String period,String merchantnum,String merchantkey,String ip,String port,String reqcharset,String reqCharset){
		StringBuffer sbResult =new StringBuffer();		//双色球提交返回的结果
		for(int i =0;i<list.size();i++){
			//双色球属性
			dp.setRedmethod("2");
			dp.setBluemethod("2");
			dp.setBettype(list.get(i).split("\\|")[0].split("\\:")[0]);
			dp.setBlueballs(list.get(i).split("\\|")[1].split("\\*")[0]);
			dp.setRedballs(list.get(i).split("\\|")[0].split("\\:")[1]);
			dp.setBoards("1");
			dp.setTotalamount(Integer.parseInt(list.get(i).split("\\*")[1])*200); // 金额的单位为 "分"
			//公共属性
			cps.setMerchantnum(merchantnum);
			cps.setMerchantserialnum(UUIDGenerator.getUUID());
			cps.setPeriod(period);
			cps.setRequesttime(DateUtils.getCurrDateTimeStr());
			cps.setWagercardnum(wagercardnum);
			cps.setWagercardpwd(wagercardpwd);
			sbResult .append("第"+(i+1)+"注投注返回的结果:"+submitOrder( dp, cps, lotteryAccount ,merchantkey,  ip, port, reqCharset));
		}
		return sbResult.toString();
	}
	
	//组装标准投递双色球数据格式
	public String sendStandardSSQData(String wagercardnum,String wagercardpwd,List<String> list,CommonParameters cps,LotteryAccount lotteryAccount,DoubleChromosphere dp,String period,String merchantnum,String merchantkey,String ip,String port,String reqcharset,String reqCharset){
		StringBuffer sbResult =new StringBuffer();		//双色球提交返回的结果
		
		//大于五注分次发送	
		Integer reqCount =1;
		 if(list.size()>5){
			 reqCount=list.size()%5==0?list.size()/5:list.size()/5+1;
		 }
		 
		 for(int i=0;i<reqCount;i++){
				String[] getArray =new String[5];
				Integer isBigger= list.size()-getArray.length>0?getArray.length:list.size();
				for(int j =0;j<isBigger;j++){
					if(list.size()>0){
						getArray[j] =list.get(0);
						list.remove(0);
					}
				}
				StringBuffer redBall 		=new StringBuffer();
				StringBuffer blueBall 		=new StringBuffer();
				StringBuffer sbRedmethod 	=new StringBuffer();
				StringBuffer sbBluemethod 	=new StringBuffer();
				StringBuffer sbBettype 		=new StringBuffer();
				Integer boards =0;
				for(String b :getArray){
					if(StringUtils.isNotBlank(b)){
						redBall.append(b.split("\\|")[0].split("\\:")[1]) .append(":");
						blueBall.append(b.split("\\|")[1].split("\\*")[0]).append(":");
						sbRedmethod.append("2").append(":");
						sbBluemethod.append("2").append(":");
						boards+=Integer.parseInt(b.split("\\*")[1]);
						sbBettype.append(b.split("\\|")[0].split("\\:")[0]).append(":"); //复试投注方式
					}
				}
				
				//双色球属性
				dp.setRedmethod(sbRedmethod.substring(0, sbRedmethod.length()-1).toString());
				dp.setBluemethod(sbBluemethod.substring(0, sbBluemethod.length()-1).toString());
				dp.setBettype(sbBettype.substring(0, sbBettype.length()-1).toString());
				dp.setBlueballs(blueBall.substring(0, blueBall.length()-1).toString());
				dp.setRedballs(redBall.substring(0, redBall.length()-1).toString());
				dp.setBoards(isBigger.toString());
				dp.setTotalamount(boards*Integer.parseInt(dp.getBetmultir())*200); // 金额的单位为 "分"
				//公共属性
				cps.setMerchantnum(merchantnum);
				cps.setMerchantserialnum(UUIDGenerator.getUUID());
				cps.setPeriod(period);
				cps.setRequesttime(DateUtils.getCurrDateTimeStr());
				cps.setWagercardnum(wagercardnum);
				cps.setWagercardpwd(wagercardpwd);
				
				sbResult .append("第"+i+1+"笔投注返回的结果："+submitOrder( dp, cps, lotteryAccount ,merchantkey,  ip, port, reqCharset));
			}
			return sbResult.toString();
	}
	
	/**
	 * 双色球订单提交
	 */
	public String  submitOrder(DoubleChromosphere doubleChromosphere,CommonParameters cp, LotteryAccount lotteryAccount,String merchantkey, String ip,String port,String reqCharset){
		String md5Data =Md5.getMD5((
						"merchantnum="+cp.getMerchantnum()
						+"&requesttype="+cp.getRequesttype()
						+"&merchantserialnum="+cp.getMerchantserialnum()
						+"&requesttime="+cp.getRequesttime()
						+"&gameid="+cp.getGameid()
						+"&period="+cp.getPeriod()
						+"&wagercardnum="+cp.getWagercardnum()
						+"&wagercardpwd="+cp.getWagercardpwd()
						+"&redmethod="+doubleChromosphere.getRedmethod()
						+"&bluemethod="+doubleChromosphere.getBluemethod()
						+"&boards="+doubleChromosphere.getBoards()
						+"&bettype="+doubleChromosphere.getBettype()
						+"&redballs="+doubleChromosphere.getRedballs()
						+"&blueballs="+doubleChromosphere.getBlueballs()
						+"&betmultir="+doubleChromosphere.getBetmultir()
						+"&totalamount="+doubleChromosphere.getTotalamount()
						+"&merchantkey="+merchantkey).getBytes());
		String reqData ="merchantnum="+cp.getMerchantnum()
						+"&requesttype="+cp.getRequesttype()
						+"&merchantserialnum="+cp.getMerchantserialnum()
						+"&requesttime="+cp.getRequesttime()
						+"&gameid="+cp.getGameid()
						+"&period="+cp.getPeriod()
						+"&wagercardnum="+cp.getWagercardnum()
						+"&wagercardpwd="+cp.getWagercardpwd()
						+"&redmethod="+doubleChromosphere.getRedmethod()
						+"&bluemethod="+doubleChromosphere.getBluemethod()
						+"&boards="+doubleChromosphere.getBoards()
						+"&bettype="+doubleChromosphere.getBettype()
						+"&redballs="+doubleChromosphere.getRedballs()
						+"&blueballs="+doubleChromosphere.getBlueballs()
						+"&betmultir="+doubleChromosphere.getBetmultir()
						+"&totalamount="+doubleChromosphere.getTotalamount()
						+"&checksum="+md5Data;
		TcpUtils tcp =new TcpUtils(ip,port,reqData,reqCharset);
		return tcp.sendTCPRequest();
	}
	
	/**
	 * 福彩3d订单提交
	 */
	public String fucai3dSubmitorder(CommonParameters cp,LotteryAccount lotteryAccount,String fucaiBalls,String userId,String merchantnum,String period,String betmultir,String currendtime,String merchantkey, String ip,String port,String reqCharset){
	   //投注密码		
		String wagercardpwd=null;
		//投注卡号
		String wagercardnum =null;
		//返回结果
		StringBuffer sbResult =new StringBuffer();
		//查找用户是否已经注册
		LotteryAccount la=lotteryAccountService.getLotteryAccountByUserId(userId);
		if(la==null){
			wagercardnum =UUIDGenerator.getUUID().substring(0,12);
			lotteryAccount.setWagercardnum(wagercardnum);
			lotteryAccount.setUser_id(Integer.parseInt(userId));
			//注册用户
			String rspData= this.regAccount(lotteryAccount, merchantnum, merchantkey, ip, port, reqCharset);
			if(StringUtils.isNotBlank(rspData)&&rspData.split("&")[0].contains("001")){//001表示请求成功返回的结果
				lotteryAccount.setWagercardpwd(rspData.split("&")[1].split("\\=")[1]);
				//将用户信息保存数据库
				lotteryAccountService.insertAccount(lotteryAccount);
				wagercardpwd =lotteryAccount.getWagercardpwd();
			}else{
				return sbResult.append("用户注册失败!").toString();
			}
		}else{
			wagercardnum =la.getWagercardnum();
			wagercardpwd =la.getWagercardpwd();
		}
		
		String[] balls =fucaiBalls.split(";");
		List<String>  z3List =new ArrayList<String>();//组三的注数
		List<String>  zList =new ArrayList<String>();//直选的注数
		List<String>  z6List =new ArrayList<String>();//组六的注数
		for(String b:balls){
			if(b.split("\\:")[0].equals("1")){
				zList.add(b);
			}
			if(b.split("\\:")[0].equals("3")){
				z3List.add(b);
			}
			if(b.split("\\:")[0].equals("4")){
				z6List.add(b);
			}
		}
		
		//直选3 投注方式
		if(z3List.size()>0){
			sbResult.append(sendData( z3List, cp, lotteryAccount, period, betmultir,wagercardnum, wagercardpwd, merchantnum , merchantkey, ip, port, reqCharset));
		}
		//直选单式
		if(zList.size()>0){
			sbResult.append(sendData( zList, cp, lotteryAccount, period,betmultir, wagercardnum, wagercardpwd, merchantnum , merchantkey, ip, port, reqCharset));
		}
		//直选6  投注方式
		if(z6List.size()>0){
			sbResult.append(sendData( z6List, cp, lotteryAccount, period,betmultir, wagercardnum, wagercardpwd, merchantnum , merchantkey, ip, port, reqCharset));
		}
		
		String statusCode =sbResult.toString();
		//手机号码
		String moblie =(la==null?lotteryAccount.getPhonenum():la.getPhonenum());
		
		Integer  total_amount =balls.length;
		
		total_amount =total_amount*Integer.parseInt(betmultir)*2;
		//设置订单属性
		Order order =setOrder( userId,moblie, new BigDecimal(total_amount));
		//设置lotteryOrder属性
		LotteryOrder lotteryOrder =setlotteryOrder( userId, resultcode(statusCode), period, cp.getGameid(),currendtime, balls.length+"", betmultir, total_amount.toString(), fucaiBalls);
		//设置Orderhistory属性
		Orderhistory orderHistory =setOrderhistory(userId);
		//保存订单表，订单历史记录表 ，彩票订单表
		insertOrder(order,lotteryOrder,orderHistory);
		
	 return statusCode+"#orderId:"+order.getOrderId()+"#money:"+total_amount;
  }
  
  	/**
	 * 发送3D数据
	 */
	public String sendData(List<String> list,CommonParameters cp,LotteryAccount lotteryAccount,String period,String betmultir,String wagercardnum,String wagercardpwd,String merchantnum ,String merchantkey,String ip,String port,String reqCharset){
		 Integer reqCount =1;
		 if(list.size()>5){
			 reqCount=list.size()%5==0?list.size()/5:list.size()/5+1;
		 }
		 StringBuffer sbResult =new StringBuffer();
		 //大于五注分次请求
		 for(int i=0;i<reqCount;i++){
			 	String[] getArray =new String[5];
				Integer isBigger= list.size()-getArray.length>0?getArray.length:list.size();
				for(int j =0;j<isBigger;j++){
					if(list.size()>0){
						getArray[j] =list.get(0);
						list.remove(0);
					}
				}
				StringBuffer method= new StringBuffer();//选号方式
				StringBuffer bettype= new StringBuffer();//玩法编号
				StringBuffer wagerdata= new StringBuffer();//投注号码
				StringBuffer sbBetmultir =new StringBuffer();//投注倍数
				for(String b :getArray){
					if(StringUtils.isNotBlank(b)){
						sbBetmultir.append(betmultir).append(":");
						method.append("2").append(":");
						bettype.append(b.split("\\:")[0]).append(":"); //复试投注方式
						wagerdata.append(b.split("\\:")[1].split("\\*")[0]) .append(":");
					}
				}
				//计算总金额
				Integer totalamount=(isBigger*Integer.parseInt(betmultir)*200);
				cp.setMerchantnum(merchantnum);
				cp.setMerchantserialnum(UUIDGenerator.getUUID());
				cp.setPeriod(period);
				cp.setRequesttime(DateUtils.getCurrDateTimeStr());
				cp.setWagercardnum(wagercardnum);
				cp.setWagercardpwd(wagercardpwd);
				
				sbResult .append("第"+(i+1)+"笔投注返回的结果:"+send3DData(cp,lotteryAccount,method.substring(0, method.length()-1),isBigger.toString(),bettype.substring(0, bettype.length()-1),wagerdata.substring(0, wagerdata.length()-1),sbBetmultir.substring(0, sbBetmultir.length()-1),totalamount.toString(),merchantkey,ip,port,reqCharset)+";");
		 }
		 return sbResult.toString();
  }
  
	/**
	 * 3d数据提交
	 */
	public String  send3DData(CommonParameters cp,LotteryAccount lotteryAccount,String method,String boards,String bettype,String wagerdata,String betmultir,String totalamount,String merchantkey, String ip,String port,String reqCharset){
		String md5Data =Md5.getMD5((
						"merchantnum="+cp.getMerchantnum()
		  			   +"&requesttype="+cp.getRequesttype()
					   +"&merchantserialnum="+cp.getMerchantserialnum()
					   +"&requesttime="+cp.getRequesttime()
					   +"&gameid="+cp.getGameid()
					   +"&period="+cp.getPeriod()
					   +"&wagercardnum="+cp.getWagercardnum()
					   +"&wagercardpwd="+cp.getWagercardpwd()
					   +"&method="+method	 
					   +"&boards="+boards
					   +"&bettype="+bettype
					   +"&wagerdata="+wagerdata
					   +"&betmultir="+betmultir
					   +"&totalamount="+totalamount	 
					   +"&merchantkey="+merchantkey
					  ).getBytes());
	  
	  String reqData= "merchantnum="+cp.getMerchantnum()
					  +"&requesttype="+cp.getRequesttype()
					  +"&merchantserialnum="+cp.getMerchantserialnum()
					  +"&requesttime="+cp.getRequesttime()
					  +"&gameid="+cp.getGameid()
					  +"&period="+cp.getPeriod()
					  +"&wagercardnum="+cp.getWagercardnum()
					  +"&wagercardpwd="+cp.getWagercardpwd()
					  +"&method="+method
					  +"&boards="+boards
					  +"&bettype="+bettype
					  +"&wagerdata="+wagerdata
					  +"&betmultir="+betmultir
					  +"&totalamount="+totalamount					  +"&checksum="+md5Data;
		 TcpUtils tcp =new TcpUtils(ip,port,reqData,reqCharset);
		return tcp.sendTCPRequest();
  }

/**
 * 七乐彩订单数据提交
 */
 public String qlcsubmitorder(CommonParameters cp,LotteryAccount lotteryAccount,String betmultir,String period,String balls,String userId,String currendtime,String boards,String merchantnum,String merchantkey,String ip,String port,String reqcharset ){
	 if(StringUtils.isNotBlank(period)&&StringUtils.isNotBlank(balls)){
	 	//投注密码		
		String wagercardpwd=null;
		//投注卡号
		String wagercardnum =null;
		//返回结果
		StringBuffer sbResult =new StringBuffer();
		//查找用户是否已经注册
		LotteryAccount la=lotteryAccountService.getLotteryAccountByUserId(userId);
		if(la==null){
			wagercardnum =UUIDGenerator.getUUID().substring(0,12);
			lotteryAccount.setWagercardnum(wagercardnum);
			lotteryAccount.setUser_id(Integer.parseInt(userId));
			//注册用户
			String rspData= this.regAccount(lotteryAccount, merchantnum, merchantkey, ip, port, reqcharset);
			if(StringUtils.isNotBlank(rspData)&&rspData.split("&")[0].contains("001")){//001表示请求成功返回的结果
				lotteryAccount.setWagercardpwd(rspData.split("&")[1].split("\\=")[1]);
				//将用户保存进数据库
				lotteryAccountService.insertAccount(lotteryAccount);
				wagercardpwd =lotteryAccount.getWagercardpwd();
			}else{
				return sbResult.append("用户注册失败!").toString();
			}
		}else{
			wagercardnum =la.getWagercardnum();
			wagercardpwd =la.getWagercardpwd();
		}
		 
		String[] sBalls=balls.split(",");
		List<String> sList =new ArrayList<String>();//标准投注
		List<String> fList =new ArrayList<String>();//复试投注
		for(String s :sBalls){
			if(StringUtils.isNotBlank(s)){
				if(Integer.parseInt(s.split("\\|")[0])>1){
					fList.add(s);
				}else{
					sList.add(s);
				}
			}
		}
		
		if(sList.size()>0){
			sbResult.append(qlcData( cp, wagercardnum, betmultir , wagercardpwd,sList, lotteryAccount, period, balls, merchantnum, merchantkey, ip, port, reqcharset ));
		}
		
		if(fList.size()>0){
			sbResult.append(fsQLCSendData( cp, wagercardnum, betmultir , wagercardpwd, fList, lotteryAccount, period, balls, merchantnum, merchantkey, ip, port, reqcharset ));
		}
		String  resultCode = sbResult.toString();
		
		
		//手机号码
		String moblie =(la==null?lotteryAccount.getPhonenum():la.getPhonenum());
		
		Integer  total_amount =Integer.parseInt(boards)*Integer.parseInt(betmultir)*2;
		//设置订单属性
		Order order =setOrder( userId,moblie, new BigDecimal(total_amount));
		//设置lotteryOrder属性
		LotteryOrder lotteryOrder =setlotteryOrder( userId, resultcode(resultCode), period, cp.getGameid(),currendtime, boards, betmultir, total_amount.toString(), balls);
		//设置Orderhistory属性
		Orderhistory orderHistory =setOrderhistory(userId);
		//保存订单表，订单历史记录表 ，彩票订单表
		insertOrder(order,lotteryOrder,orderHistory);
		return resultCode;
	 }
	 return null;
 }
 
 //组装七乐彩标准投注方式
 public String qlcData(CommonParameters cp,String wagercardnum,String betmultir ,String wagercardpwd,List<String> list,LotteryAccount lotteryAccount,String period,String balls,String merchantnum,String merchantkey,String ip,String port,String reqcharset ){
	 //大于五次分次发送
	 Integer reqCount =1;
	 if(list.size()>5){
		 reqCount=list.size()%5==0?list.size()/5:list.size()/5+1;
	 }
	 StringBuffer sbRestult =new StringBuffer();
	 for(int i=0;i<reqCount;i++){
		 String[] getArray =new String[5];
			Integer isBigger= list.size()-getArray.length>0?getArray.length:list.size();
			for(int j =0;j<isBigger;j++){
				if(list.size()>0){
					getArray[j] =list.get(0);
					list.remove(0);
				}
			}
			StringBuffer method= new StringBuffer();//选号方式
			StringBuffer bettype= new StringBuffer();//玩法编号
			StringBuffer wagerdata= new StringBuffer();//投注号码
			for(String b :getArray){
				if(StringUtils.isNotBlank(b)){
					method.append("2").append(":");
					bettype.append("1").append(":"); //单式投注方式
					wagerdata.append(arrayData(b.split("\\|")[1].split(",")[0])) .append(":");
				}
			}
			//计算总金额
			Integer totalamount=(isBigger*Integer.parseInt(betmultir)*200);
			cp.setMerchantnum(merchantnum);
			cp.setMerchantserialnum(UUIDGenerator.getUUID());
			cp.setPeriod(period);
			cp.setRequesttime(DateUtils.getCurrDateTimeStr());
			cp.setWagercardnum(wagercardnum);
			cp.setWagercardpwd(wagercardpwd);
			
			sbRestult.append("第"+(i+1)+"注七乐彩标准投注返回结果:"+sendQLCData(cp, lotteryAccount, method.substring(0, method.length()-1), isBigger.toString(), bettype.substring(0, bettype.length()-1), wagerdata.substring(0, wagerdata.length()-1), totalamount.toString(), merchantkey,  ip, port, reqcharset)+";");
	 }
	 return sbRestult.toString();
 }
 // 组装七乐彩标复试注方式
 public String fsQLCSendData(CommonParameters cp,String wagercardnum,String betmultir ,String wagercardpwd,List<String> list,LotteryAccount lotteryAccount,String period,String balls,String merchantnum,String merchantkey,String ip,String port,String reqcharset ){
	//复试投注每注都发起请求
	 StringBuffer sbRestult =new StringBuffer();
	 for(int i=0;i<list.size();i++){
		 
		    String wagerdata= arrayData(list.get(i).split("\\|")[1].split(",")[0]);
		    Integer boards =Integer.parseInt(list.get(i).split("\\|")[0]);
			//计算总金额
			Integer totalamount=(boards*Integer.parseInt(betmultir)*200);
			cp.setMerchantnum(merchantnum);
			cp.setMerchantserialnum(UUIDGenerator.getUUID());
			cp.setPeriod(period);
			cp.setRequesttime(DateUtils.getCurrDateTimeStr());
			cp.setWagercardnum(wagercardnum);
			cp.setWagercardpwd(wagercardpwd);
			
			sbRestult.append("第"+(i+1)+"七乐彩复试投注返回结果:"+sendQLCData(cp, lotteryAccount, "2", "1", "2", wagerdata, totalamount.toString(), merchantkey,  ip, port, reqcharset)+";");
	 }
	 return sbRestult.toString();
 }
 
 //切割数组重新组装
 public String arrayData(String data){
		 String[]  aData =data.split(" ");
		 StringBuffer  sbData =new StringBuffer();
		 for(String s :aData){
			 sbData.append(s).append(",");
		 }
		 return sbData.substring(0, sbData.length()-1);
 	}
 	/**
	 * 3d数据提交
	 */
	public String  sendQLCData(CommonParameters cp,LotteryAccount lotteryAccount,String method,String boards,String bettype,String wagerdata,String totalamount,String merchantkey, String ip,String port,String reqCharset){
		String md5Data =Md5.getMD5((
						"merchantnum="+cp.getMerchantnum()
		  			   +"&requesttype="+cp.getRequesttype()
					   +"&merchantserialnum="+cp.getMerchantserialnum()
					   +"&requesttime="+cp.getRequesttime()
					   +"&gameid="+cp.getGameid()
					   +"&period="+cp.getPeriod()
					   +"&wagercardnum="+cp.getWagercardnum()
					   +"&wagercardpwd="+cp.getWagercardpwd()
					   +"&method="+method	 
					   +"&boards="+boards
					   +"&bettype="+bettype
					   +"&wagerdata="+wagerdata
					   +"&totalamount="+totalamount
					   +"&merchantkey="+merchantkey
					  ).getBytes());
	  
	  String reqData= "merchantnum="+cp.getMerchantnum()
					  +"&requesttype="+cp.getRequesttype()
					  +"&merchantserialnum="+cp.getMerchantserialnum()
					  +"&requesttime="+cp.getRequesttime()
					  +"&gameid="+cp.getGameid()
					  +"&period="+cp.getPeriod()
					  +"&wagercardnum="+cp.getWagercardnum()
					  +"&wagercardpwd="+cp.getWagercardpwd()
					  +"&method="+method
					  +"&boards="+boards
					  +"&bettype="+bettype
					  +"&wagerdata="+wagerdata
					  +"&totalamount="+totalamount
					  +"&checksum="+md5Data;
		 TcpUtils tcp =new TcpUtils(ip,port,reqData,reqCharset);
		return tcp.sendTCPRequest();
	}
	
	//快乐8订单提交
	public String klbSubmitOrder(CommonParameters cp,LotteryAccount lotteryAccount,String betmultir,String period,String balls,String userId,String currendtime,String boards,String merchantnum,String merchantkey,String ip,String port,String reqcharset){
		
		 if(StringUtils.isNotBlank(period)&&StringUtils.isNotBlank(balls)){
			 	//投注密码		
				String wagercardpwd=null;
				//投注卡号
				String wagercardnum =null;
				//返回结果
				StringBuffer sbResult =new StringBuffer();
				//查找用户是否已经注册
				LotteryAccount la=lotteryAccountService.getLotteryAccountByUserId(userId);
				if(la==null){
					wagercardnum =UUIDGenerator.getUUID().substring(0,12);
					lotteryAccount.setWagercardnum(wagercardnum);
					lotteryAccount.setUser_id(Integer.parseInt(userId));
					//注册用户
					String rspData= this.regAccount(lotteryAccount, merchantnum, merchantkey, ip, port, reqcharset);
					if(StringUtils.isNotBlank(rspData)&&rspData.split("&")[0].contains("001")){//001表示请求成功返回的结果
						lotteryAccount.setWagercardpwd(rspData.split("&")[1].split("\\=")[1]);
						//将用户保存进数据库
						lotteryAccountService.insertAccount(lotteryAccount);
						wagercardpwd =lotteryAccount.getWagercardpwd();
					}else{
						return sbResult.append("用户注册失败!").toString();
					}
				}else{
					wagercardnum =la.getWagercardnum();
					wagercardpwd =la.getWagercardpwd();
				}
				
				String[] sBalls=balls.split(",");
				List<String> sList =new ArrayList<String>();
				for(String s :sBalls){
					if(StringUtils.isNotBlank(s)){
						sList.add(s);
					}
				}
				if(sList.size()>0){
					sbResult.append("标准投注返回的结果:"+klbData( cp, wagercardnum, betmultir , wagercardpwd,sList, lotteryAccount, period, balls, merchantnum, merchantkey, ip, port, reqcharset )+";");
				}
				
				String  resultCode = sbResult.toString();
				
				
				//手机号码
				String moblie =(la==null?lotteryAccount.getPhonenum():la.getPhonenum());
				
				Integer  total_amount =Integer.parseInt(boards)*Integer.parseInt(betmultir)*2;
				//设置订单属性
				Order order =setOrder( userId,moblie, new BigDecimal(total_amount));
				//设置lotteryOrder属性
				LotteryOrder lotteryOrder =setlotteryOrder( userId, resultcode(resultCode), period, cp.getGameid(),currendtime, boards, betmultir, total_amount.toString(), balls);
				//设置Orderhistory属性
				Orderhistory orderHistory =setOrderhistory(userId);
				//保存订单表，订单历史记录表 ，彩票订单表
				insertOrder(order,lotteryOrder,orderHistory);
				return resultCode;
		 }
		return null;
	}
	
	//快乐8组装要发送的数据
	public String klbData(CommonParameters cp,String wagercardnum,String betmultir ,String wagercardpwd,List<String> list,LotteryAccount lotteryAccount,String period,String balls,String merchantnum,String merchantkey,String ip,String port,String reqcharset ){
		//投注每注都发起请求
		 StringBuffer sbRestult =new StringBuffer();
		 for(int i=0;i<list.size();i++){
			    String wagerdata= arrayData(list.get(i).split("\\|")[1].split(",")[0]);
			    Integer ballLength=list.get(i).split("\\|")[1].split(",")[0].split(" ").length;
				//计算总金额
				Integer totalamount=(Integer.parseInt(betmultir)*200);
				cp.setMerchantnum(merchantnum);
				cp.setMerchantserialnum(UUIDGenerator.getUUID());
				cp.setPeriod(period);
				cp.setRequesttime(DateUtils.getCurrDateTimeStr());
				cp.setWagercardnum(wagercardnum);
				cp.setWagercardpwd(wagercardpwd);
				sbRestult.append("第"+(i+1)+"快乐8投注返回结果："+sendKLBData(cp, lotteryAccount, "2", 1,  ballLength, betmultir, "0", wagerdata, totalamount, merchantkey,  ip, port, reqcharset));
		 }
		 return sbRestult.toString();	
	}
	
	//快乐8发送数据
	public String sendKLBData(CommonParameters cp,LotteryAccount lotteryAccount,String method,Integer boards,Integer spots,String betmultir,String multiplier,String wagerdata,Integer totalamount,String merchantkey, 
							  String ip,String port,String reqCharset){
		String md5Data =Md5.getMD5((
							"merchantnum="+cp.getMerchantnum()
			  			   +"&requesttype="+cp.getRequesttype()
						   +"&merchantserialnum="+cp.getMerchantserialnum()
						   +"&requesttime="+cp.getRequesttime()
						   +"&gameid="+cp.getGameid()
						   +"&period="+cp.getPeriod()
						   +"&wagercardnum="+cp.getWagercardnum()
						   +"&wagercardpwd="+cp.getWagercardpwd()
						   +"&method="+method	 
						   +"&boards="+boards
						   +"&spots="+spots
						   +"&wagerdata="+wagerdata
						   +"&betmultir="+betmultir
						   +"&multiplier="+multiplier
						   +"&totalamount="+totalamount
						   +"&merchantkey="+merchantkey
			  		).getBytes());

		String reqData="merchantnum="+cp.getMerchantnum()
					   +"&requesttype="+cp.getRequesttype()
					   +"&merchantserialnum="+cp.getMerchantserialnum()
					   +"&requesttime="+cp.getRequesttime()
					   +"&gameid="+cp.getGameid()
					   +"&period="+cp.getPeriod()
					   +"&wagercardnum="+cp.getWagercardnum()
					   +"&wagercardpwd="+cp.getWagercardpwd()
					   +"&method="+method	 
					   +"&boards="+boards
					   +"&spots="+spots
					   +"&wagerdata="+wagerdata
					   +"&betmultir="+betmultir
					   +"&multiplier="+multiplier
					   +"&totalamount="+totalamount
					   +"&checksum="+md5Data;
		TcpUtils tcp =new TcpUtils(ip,port,reqData,reqCharset);
		return tcp.sendTCPRequest();
	}
	
	//pk拾订单提交
	public String pksSubmitOrder(CommonParameters cp,LotteryAccount lotteryAccount,String betmultir,String period,String balls,String userId,String currendtime,String boards,String merchantnum,String merchantkey,String ip,String port,String reqcharset){
		
		if(StringUtils.isNotBlank(period)&&StringUtils.isNotBlank(balls)){
		 	//投注密码		
			String wagercardpwd=null;
			//投注卡号
			String wagercardnum =null;
			//返回结果
			StringBuffer sbResult =new StringBuffer();
			//查找用户是否已经注册
			LotteryAccount la=lotteryAccountService.getLotteryAccountByUserId(userId);
			if(la==null){
				wagercardnum =UUIDGenerator.getUUID().substring(0,12);
				lotteryAccount.setWagercardnum(wagercardnum);
				lotteryAccount.setUser_id(Integer.parseInt(userId));
				//注册用户
				String rspData= this.regAccount(lotteryAccount, merchantnum, merchantkey, ip, port, reqcharset);
				if(StringUtils.isNotBlank(rspData)&&rspData.split("&")[0].contains("001")){//001表示请求成功返回的结果
					lotteryAccount.setWagercardpwd(rspData.split("&")[1].split("\\=")[1]);
					//将用户保存进数据库
					lotteryAccountService.insertAccount(lotteryAccount);
					wagercardpwd =lotteryAccount.getWagercardpwd();
				}else{
					return sbResult.append("用户注册失败!").toString();
				}
			}else{
				wagercardnum =la.getWagercardnum();
				wagercardpwd =la.getWagercardpwd();
			}
			
			String[] sBalls=balls.split(";");
			List<String> sList =new ArrayList<String>();
			for(String s :sBalls){
				if(StringUtils.isNotBlank(s)){
					sList.add(s);
				}
			}
			if(sList.size()>0){
				sbResult.append(pksData( cp, wagercardnum, betmultir , wagercardpwd,sList, lotteryAccount, period, balls, merchantnum, merchantkey, ip, port, reqcharset ));
			}
			String  resultCode = sbResult.toString();
			//手机号码
			String moblie =(la==null?lotteryAccount.getPhonenum():la.getPhonenum());
			
			Integer  total_amount =Integer.parseInt(boards)*Integer.parseInt(betmultir)*2;
			//设置订单属性
			Order order =setOrder( userId,moblie, new BigDecimal(total_amount));
			//设置lotteryOrder属性
			LotteryOrder lotteryOrder =setlotteryOrder( userId, resultcode(resultCode), period, cp.getGameid(),currendtime, boards, betmultir, total_amount.toString(), balls);
			//设置Orderhistory属性
			Orderhistory orderHistory =setOrderhistory(userId);
			//保存订单表，订单历史记录表 ，彩票订单表
			insertOrder(order,lotteryOrder,orderHistory);
			return resultCode;
	 }
		return null;
	}
	
	//pk拾订单提交
	public String pksData(CommonParameters cp,String wagercardnum,String betmultir ,String wagercardpwd,List<String> list,LotteryAccount lotteryAccount,String period,String balls,String merchantnum,String merchantkey,String ip,String port,String reqcharset ){
		//投注每注都发起请求
		 StringBuffer sbRestult =new StringBuffer();
		 for(int i=0;i<list.size();i++){
			    String wagerdata= list.get(i).split("\\|")[1];
				//计算总金额
				Integer totalamount=(Integer.parseInt(betmultir)*200);
				cp.setMerchantnum(merchantnum);
				cp.setMerchantserialnum(UUIDGenerator.getUUID());
				cp.setPeriod(period);
				cp.setRequesttime(DateUtils.getCurrDateTimeStr());
				cp.setWagercardnum(wagercardnum);
				cp.setWagercardpwd(wagercardpwd);
				sbRestult.append("第"+(i+1)+"PK拾投注返回结果:"+sendPKSData(cp, lotteryAccount, "2", 1,  1, betmultir,  wagerdata, totalamount, merchantkey,  ip, port, reqcharset)+";");
		 }
		 return sbRestult.toString();	
	}
	
	//pk拾发送数据
	public String sendPKSData(CommonParameters cp,LotteryAccount lotteryAccount,String method,Integer boards,Integer spots,String betmultir,String wagerdata,Integer totalamount,String merchantkey, 
							  String ip,String port,String reqCharset){
		String md5Data =Md5.getMD5((
							"merchantnum="+cp.getMerchantnum()
			  			   +"&requesttype="+cp.getRequesttype()
						   +"&merchantserialnum="+cp.getMerchantserialnum()
						   +"&requesttime="+cp.getRequesttime()
						   +"&gameid="+cp.getGameid()
						   +"&period="+cp.getPeriod()
						   +"&wagercardnum="+cp.getWagercardnum()
						   +"&wagercardpwd="+cp.getWagercardpwd()
						   +"&method="+method
						   +"&bettype=2"
						   +"&boards="+boards
						   +"&spots="+spots
						   +"&wagerdata="+wagerdata
						   +"&betmultir="+betmultir
						   +"&totalamount="+totalamount
						   +"&merchantkey="+merchantkey
			  		).getBytes());

		String reqData="merchantnum="+cp.getMerchantnum()
					   +"&requesttype="+cp.getRequesttype()
					   +"&merchantserialnum="+cp.getMerchantserialnum()
					   +"&requesttime="+cp.getRequesttime()
					   +"&gameid="+cp.getGameid()
					   +"&period="+cp.getPeriod()
					   +"&wagercardnum="+cp.getWagercardnum()
					   +"&wagercardpwd="+cp.getWagercardpwd()
					   +"&method="+method	
					   +"&bettype=2"
					   +"&boards="+boards
					   +"&spots="+spots
					   +"&wagerdata="+wagerdata
					   +"&betmultir="+betmultir
					   +"&totalamount="+totalamount
					   +"&checksum="+md5Data;
		TcpUtils tcp =new TcpUtils(ip,port,reqData,reqCharset);
		return tcp.sendTCPRequest();
	}
	
	@Transactional
	public void insertOrder(Order order, LotteryOrder lotteryOrder,Orderhistory orderHistory){
		
		//等待买家付款
		order.setOrderState(GoodsorderService.STATE_WAIT_BUYER_PAY);
		//订单类型：彩票
		order.setOrderType(ORDER_TYPE_MOVIE);

		//设置同一订单号
		lotteryOrder.setOrder_no(order.getOrderId());
		orderHistory.setOrder_id(order.getOrderId());
		//买家提交订单
		orderHistory.setAction_name(GoodsorderService.ACTION_BUYER_SUBMIT_ORDER);
		orderService.insertSelective(order);
		lotteryOrderDao.insertAccount(lotteryOrder);
		orderhistoryDao.insertOrderhistory(orderHistory);
	}
	
	
	
}
