package com.xingfugo.business.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.GoodsReturnAddrDao;
import com.xingfugo.business.dao.GoodsReturnHisDao;
import com.xingfugo.business.dao.GoodsreturnDao;
import com.xingfugo.business.dao.Inc_shipinfoDao;
import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.GoodsReturnAddr;
import com.xingfugo.business.module.GoodsReturnHis;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Goodsreturn;
import com.xingfugo.business.module.Inc_shipinfo;
import com.xingfugo.business.module.Orderhistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsreturnQueryForm;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.PageResultBuilder;
import com.xingfugo.util.RandomStrUtil;


@Service
public class GoodsreturnService{

	@Autowired
	private GoodsreturnDao goodsreturnDao;
	
	@Autowired
	private GoodsReturnHisDao goodsReturnHisDao;
	@Autowired
	private GoodsReturnAddrDao goodsReturnAddrDao;
	//物流运送信息dao层业务接口
	@Autowired
	private Inc_shipinfoDao inc_shipinfoDao;
	//订单信息service业务
	@Autowired
	private GoodsorderService goodsorderService;
	@Autowired
	private CommparaService commparaService;
	@Autowired
	private FundaccountService fundaccountService;
	
	//退换货动作常量
	private static final String REFUND_APPLY_NAME = "买家申请退款",REFUND_APPLY_CODE = "refund_apply";
	private static final String SELLER_AUDIT_NAME = "卖家审核",SELLER_AUDIT_CODE = "seller_audit";
	private static final String SELLER_RECEIVE_NAME = "卖家确认收货",SELLER_RECEIVE_CODE = "seller_receive";
	private static final String SELLER_RESEND_NAME = "卖家发货",SELLER_RESEND_CODE = "seller_resend";
	private static final String SELLER_REFUND_NAME = "卖家退款",SELLER_REFUND_CODE = "seller_refund";
	private static final String BUYER_RECEIVE_NAME = "买家确认收货",BUYER_RECEIVE_CODE = "buyer_receive";
	
	//0：换货 1：退货 2：维修
	private static final String WEIXIU = "2",HUANHUO = "0",TUIHUO="1";
	
	//未审核 0：待审核 1：审核不通过 2：等待买家发货 3：等待卖家发货 4：等待买家确认 5：交易完成 6：等待卖家退款
	private static final String WEISHENHE = "0",NOPASS = "1",WAIT_BUYER_SHIP = "2",WAIT_SELLER_SHIP = "3",
	WAIT_BUYER_CONFIRM = "4",TRADE_SUCESS = "5",WAIT_SELLER_SETFEE = "6";
	
	// 卖家审核
	private static final String AUDIT_YES = "0",AUDIT_NO = "1";
	//卖家处理 2 : 确认收货
	private static final String HANDLE_REC = "2";
	
	//会员类型 0：买家 1：卖家
	private static final String USER_TYPE_BUYER = "0",USER_TYPE_SELLER = "1";
	
	//会员操作退换货历史userid 记录为-1 表示不记录userid
	private static final int MEM_USER_NONE = -1;
	//订单 -- 4：交易成功状态
	public static final String STATE_TRADE_SUCCESS = "4";
	//修改订单状态的操作
	public static final String ORDER_REFUND_SUCCESS_OPE = "交易成功";
	//退换货类型--字典编码
	private static final String RETURN_TYPE_CODE="goodsreturn_type";

	//个人-会员类型-资金账户
	private static final String PERSONAL_CUST_TYPE_OF_FUNDACCOUNT = "0";
	//企业-会员类型-资金账户
	private static final String ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT = "1";
	
	//退款-资金移动表
	private final static String FUNDHISTORY_ACTION_TYPE_REFUND = "3";
	//资金异动,原因
	private final static String FUNDHISTORY_ACTION_DESC_REFUND = "退货退款";
	
	//卖家审核退换货申请
	@Transactional
	public void sellerAudit(Goodsreturn goodsreturn , String audit_code ,String cust_id){
		String trade_id = goodsreturn.getTrade_id();
		String _info_state = getGoodsreturnInfostate(trade_id);
		String info_state = "";
		//若状态不是未审核，直接退出方法
		if(!_info_state.equals(WEISHENHE)){
			return;
		}
		
		//审核通过 == 等待买家发货
		String operInfo = "";
		GoodsReturnAddr returnAddr = null;
		if(audit_code.equals(AUDIT_YES)){
			info_state = WAIT_BUYER_SHIP;
			operInfo = "通过";
			returnAddr = goodsreturn.getGoodsReturnAddr();
			returnAddr.setUser_id(cust_id);
			returnAddr.setUser_type(USER_TYPE_SELLER);
			returnAddr.setLink_id(trade_id);
		}
		if(audit_code.equals(AUDIT_NO)){
			info_state = NOPASS;
			operInfo = "不通过";
		}
		goodsreturn.setInfo_state(info_state);
		//修改退换货状态
		int num = goodsreturnDao.updateInfo(goodsreturn);
		//插入历史信息
		GoodsReturnHis goodsReturnHis = new GoodsReturnHis();
		goodsReturnHis.setLink_id(trade_id);
		goodsReturnHis.setOper_name(SELLER_AUDIT_NAME+operInfo);
		goodsReturnHis.setOper_code(SELLER_AUDIT_CODE);
		goodsReturnHis.setUser_id(Integer.parseInt(cust_id));
		goodsReturnHisDao.insert(goodsReturnHis);
		//插入收货地址信息
		if(num>0&&returnAddr!=null&&audit_code.equals(AUDIT_YES)){
			//插入退换货地址信息
			goodsReturnAddrDao.insert(returnAddr);
		}
	}
	
	//获取当前退换货状态
	private String getGoodsreturnInfostate(String trade_id){
		Goodsreturn goodsreturn = goodsreturnDao.getByTradeid(trade_id);
		if(goodsreturn==null){
			return "";
		}
		return goodsreturn.getInfo_state();
	}
	
	//插入申请
	public void insert(Goodsreturn goodsreturn){
		this.goodsreturnDao.insert(goodsreturn);
	}
	
	//申请退/换货/维修( 插入申请)
	@Transactional
	public boolean applyrefund(Goodsreturn goodsreturn){
		
		String trade_id = RandomStrUtil.getNumberRand("15");
		goodsreturn.setTrade_id(trade_id);
		goodsreturn.setInfo_state(WEISHENHE);
		int num = this.goodsreturnDao.insert(goodsreturn);
		//退换货历史
		GoodsReturnHis goodsReturnHis = new GoodsReturnHis();
		goodsReturnHis.setLink_id(goodsreturn.getTrade_id());
		goodsReturnHis.setOper_name(REFUND_APPLY_NAME);
		goodsReturnHis.setOper_code(REFUND_APPLY_CODE);
		//会员操作退换货历史记录为-1表示不记录userid
		goodsReturnHis.setUser_id(MEM_USER_NONE);
		//插入退换货历史
		num = this.goodsReturnHisDao.insert(goodsReturnHis);
		
		//退换货收货地址信息	
		GoodsReturnAddr returnAddress = goodsreturn.getGoodsReturnAddr();
		returnAddress.setLink_id(trade_id);
		returnAddress.setUser_id(goodsreturn.getUser_id());
		returnAddress.setUser_type(USER_TYPE_BUYER);
		//插入退换货地址信息
		goodsReturnAddrDao.insert(returnAddress);
		return num > 0 ;
	}
	
	public PageResult getGoodsreturnByPage(GoodsreturnQueryForm goodsreturnQueryForm){
		List list = goodsreturnDao.getGoodsreturnByPage(goodsreturnQueryForm);
		//商品图片url处理
//		ImgPathUitls.filterImagePath(list,"goods_img_path");
		ImgPathUitls.filterImagePath_spec(list, "goods_img_path", "70_70");
		PageResult result = PageResultBuilder.build(goodsreturnQueryForm.getPg(), list);
		return result;
	}
	
	public List getStateCountByCustid(String cust_id){
		Map map = new HashMap();
		map.put("cust_id", cust_id);
		return this.goodsreturnDao.getStateCount(map);
	}
	
	/**
	 * 查询各种状态的退货单的数量
	 * @param map(cust_id商家id, info_states退货单状态)
	 * @return
	 */
	public List getStateCount(Map<String, Object> map) {
		return this.goodsreturnDao.getStateCount(map);
	}
	

	public Map getByPk(String trade_id){
		Map map = this.goodsreturnDao.getByPk(trade_id);
		ImgPathUitls.filterImagePath(map);
		return map;
	}
	
	public Goodsreturn getByTradeid(String tradeid)
	{
	  return goodsreturnDao.getByTradeid(tradeid);
	}

	/**
	 * 卖家确认收货
	 * @param goodsreturn 退换货申请对象
	 * @param handle_code (动作标志)
	 * @cust_id 操作人id
	 * @return
	 */
	@Transactional
	public boolean sellerConfirmReceive(Goodsreturn goodsreturn , String handle_code,String cust_id){
		String biz_type = goodsreturn.getBiz_type();
		//卖家确认收货并且为退货单，修改状态为 6：等待卖家退款
		if(HANDLE_REC.equals(handle_code)&&TUIHUO.equals(biz_type)){
			goodsreturn.setInfo_state(WAIT_SELLER_SETFEE);
		}
		//卖家确认收货并且不是退货单，修改状态为 3：等待卖家发货
		if(HANDLE_REC.equals(handle_code)&&!TUIHUO.equals(biz_type)){
			goodsreturn.setInfo_state(WAIT_SELLER_SHIP);
		}
 
		if(goodsreturn.getInfo_state()==null){
			return false;
		}
		int num = goodsreturnDao.updateInfo(goodsreturn);
		
		//退换货历史
		GoodsReturnHis goodsReturnHis = new GoodsReturnHis();
		goodsReturnHis.setLink_id(goodsreturn.getTrade_id());
		goodsReturnHis.setOper_name(SELLER_RECEIVE_NAME);
		goodsReturnHis.setOper_code(SELLER_RECEIVE_CODE);
		goodsReturnHis.setUser_id(Integer.parseInt(cust_id));
		//插入退换货历史
		num = this.goodsReturnHisDao.insert(goodsReturnHis);
		return num>0;
	}
	/**
	 * 卖家确认发货（换货和维修的申请）
	 * @param goodsreturn 退换货申请对象
	 * @param inc_shipinfo
	 * @param handle_code
	 * @param cust_id
	 * @return
	 */
	@Transactional
	public boolean sellerResend(Goodsreturn goodsreturn ,Inc_shipinfo inc_shipinfo ,String cust_id){
		String trade_id =goodsreturn.getTrade_id();
		String biz_type = goodsreturn.getBiz_type();
		String _info_state = getGoodsreturnInfostate(trade_id);
		//卖家确认发货并且不是退货单，修改状态为 4：等待买家确认
		if(_info_state.equals(WAIT_SELLER_SHIP)&&!TUIHUO.equals(biz_type)){
			goodsreturn.setInfo_state(WAIT_BUYER_CONFIRM);
		}
		if(goodsreturn.getInfo_state()==null){
			return false;
		}
		//修改换货维修申请状态
		int num = goodsreturnDao.updateInfo(goodsreturn);
		
		//退换货历史
		GoodsReturnHis goodsReturnHis = new GoodsReturnHis();
		goodsReturnHis.setLink_id(trade_id);
		goodsReturnHis.setOper_name(SELLER_RESEND_NAME);
		goodsReturnHis.setOper_code(SELLER_RESEND_CODE);
		goodsReturnHis.setUser_id(Integer.parseInt(cust_id));
		//插入退换货历史
		num = this.goodsReturnHisDao.insert(goodsReturnHis);
		
		//插入物流信息
		inc_shipinfo.setUser_id(cust_id);
		inc_shipinfo.setUser_type(USER_TYPE_SELLER);
		inc_shipinfoDao.insert(inc_shipinfo);
		return num>0;
	}


	/**
	 * 
	 * @param goodsreturn
	 * @param cust_id
	 * @return -3 信息错误; -2 资金账户状态不正常; -1 可用余额不足; 0 交易失败; 1 交易成功
	 */
	@Transactional
	public int sellerRefundMoney(Goodsreturn goodsreturn ,String cust_id){
		String trade_id =goodsreturn.getTrade_id();
		String biz_type = goodsreturn.getBiz_type();
		String _info_state = goodsreturn.getInfo_state();
		//卖家确认退款 ，修改状态为 5：交易完成
		if(_info_state.equals(WAIT_SELLER_SETFEE)&& TUIHUO.equals(biz_type)){
			goodsreturn.setInfo_state(TRADE_SUCESS);
		}
		if(goodsreturn.getInfo_state()==null){
			return -3;
		}
		
		Fundaccount from = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT);
		Fundaccount to = fundaccountService.getFundaccountByCustidAndCusttype((Integer)goodsreturnDao.getByPk(trade_id).get("user_id"), PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
		BigDecimal trade_fund_num = goodsreturn.getRefundMoney();
		int ret = fundaccountService.tradeFundAccount(from.getAccount_no(), to.getAccount_no(), trade_fund_num, FUNDHISTORY_ACTION_TYPE_REFUND, "退货退款,订单号: " + goodsreturn.getOrder_id());
		
		if(ret != 1) {
			return ret;
		}
		
		//修改换货维修申请状态
		int num = goodsreturnDao.updateInfo(goodsreturn);
		
		//退换货历史
		GoodsReturnHis goodsReturnHis = new GoodsReturnHis();
		goodsReturnHis.setLink_id(trade_id);
		goodsReturnHis.setOper_name(SELLER_REFUND_NAME);
		goodsReturnHis.setOper_code(SELLER_REFUND_CODE);
		goodsReturnHis.setUser_id(Integer.parseInt(cust_id));
		//插入退换货历史
		num = this.goodsReturnHisDao.insert(goodsReturnHis);
	
		if(goodsreturn.getOrder_id()==null||goodsreturn.getGoods_id()==null){
			goodsreturn = goodsreturnDao.getByTradeid(trade_id);
		}
		//同一订单是否有未完成的退换货申请
		int uncomp = goodsreturnDao.hasUncomplete(goodsreturn);
		if(uncomp==0){
			//从数据字典获取退换货类
			String bizTypeNm = commparaService.getParakeyByParacode(RETURN_TYPE_CODE, goodsreturn.getBiz_type());
			String actionNm = ORDER_REFUND_SUCCESS_OPE+"("+bizTypeNm+")";
			//修改订单状态
			updateOrderState(goodsreturn,actionNm,cust_id,null);
		}
		return 1;
	}
	
	/**
	 * 买家确认收货
	 * @return
	 */
	@Transactional
	public boolean buyerReceive(Goodsreturn goodsreturn ,String user_id){
		String trade_id =goodsreturn.getTrade_id();
		String biz_type = goodsreturn.getBiz_type();
		String _info_state = goodsreturn.getInfo_state();
		//卖家确认发货并且不是退货单，修改状态为 5：交易完成
		if(_info_state.equals(WAIT_BUYER_CONFIRM)&& !TUIHUO.equals(biz_type)){
			goodsreturn.setInfo_state(TRADE_SUCESS);
		}
		if(goodsreturn.getInfo_state()==null){
			return false;
		}
		//修改换货维修申请状态
		int num = goodsreturnDao.updateInfo(goodsreturn);
		
		//退换货历史
		GoodsReturnHis goodsReturnHis = new GoodsReturnHis();
		goodsReturnHis.setLink_id(trade_id);
		goodsReturnHis.setOper_name(BUYER_RECEIVE_NAME);
		goodsReturnHis.setOper_code(BUYER_RECEIVE_CODE);
		//会员操作退换货历史userid 记录为-1 表示不记录userid
		goodsReturnHis.setUser_id(MEM_USER_NONE);
		//插入退换货历史
		num = this.goodsReturnHisDao.insert(goodsReturnHis);
		
		if(goodsreturn.getOrder_id()==null||goodsreturn.getGoods_id()==null){
			goodsreturn = goodsreturnDao.getByTradeid(trade_id);
		}
		//同一订单是否有未完成的退换货申请
		int uncomp = goodsreturnDao.hasUncomplete(goodsreturn);
		if(uncomp==0){
			//从数据字典获取退换货类
			String bizTypeNm = commparaService.getParakeyByParacode(RETURN_TYPE_CODE, goodsreturn.getBiz_type());
			String actionNm = ORDER_REFUND_SUCCESS_OPE+"("+bizTypeNm+")";
			//修改订单状态
			updateOrderState(goodsreturn,actionNm,null,user_id);
		}
		return num>0;
	}
	
	private void updateOrderState(Goodsreturn goodsreturn,String actionNm,String cust_id,String user_id){
		
		//修改订单状态
		Goodsorder order = new Goodsorder();
		String orderId = goodsreturn.getOrder_id();
		order.setOrder_id(orderId);
		order.setOrder_state(STATE_TRADE_SUCCESS);
		
		Orderhistory orderhistory = new Orderhistory();
		orderhistory.setOrder_id(orderId);
		if(!StringUtils.isEmpty(user_id)){
			orderhistory.setUser_id(user_id);
		}
		if(!StringUtils.isEmpty(cust_id)){
			orderhistory.setCust_id(cust_id);
		}
		//orderhistory.setCust_id(null);
		orderhistory.setAction_name(actionNm);
		goodsorderService.updateState(order ,orderhistory);
	}
}
