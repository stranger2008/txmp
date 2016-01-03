package com.xingfugo.business.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.xingfugo.business.dao.Api_air_orderdetailDao;
import com.xingfugo.business.dao.GoodsDao;
import com.xingfugo.business.dao.GoodsStockHistoryMapper;
import com.xingfugo.business.dao.GoodsorderDao;
import com.xingfugo.business.dao.GoodsreturnDao;
import com.xingfugo.business.dao.LotteryOrderDao;
import com.xingfugo.business.dao.OrderdetailDao;
import com.xingfugo.business.dao.OrderhistoryDao;
import com.xingfugo.business.dao.SellerGoodsMapper;
import com.xingfugo.business.module.CartItem;
import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.Fundhistory;
import com.xingfugo.business.module.Goods;
import com.xingfugo.business.module.GoodsStockHistory;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Inc_shipinfo;
import com.xingfugo.business.module.Invoice;
import com.xingfugo.business.module.Order_goods_desc;
import com.xingfugo.business.module.Orderdetail;
import com.xingfugo.business.module.Orderhistory;
import com.xingfugo.business.module.SimpleGoodsCartItem;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.outapi.movie.dao.MovieOrderDetailMapper;
import com.xingfugo.business.outapi.movie.module.OrderDetail;
import com.xingfugo.business.outapi.movie.module.local.MovieOrderDetail;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.PageResultBuilder;


@Service
public class GoodsorderService{
	private static SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private GoodsorderDao goodsorderDao;
	@Autowired
	private OrderdetailDao orderdetailDao;
	@Autowired
	private OrderhistoryDao orderhistoryDao;
	@Autowired
	private Api_air_orderdetailDao api_air_orderdetailDao;
	@Autowired
	private LotteryOrderDao lotteryOrderDao;
	@Autowired
	private MovieOrderDetailMapper movieOrderDetailMapper;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private SellerGoodsService sellerGoodsService;
	@Autowired
	private SellerGoodsMapper sellerGoodsMapper;
	@Autowired
	private GoodsStockHistoryMapper goodsStockHistoryMapper;
	
	@Autowired
	private AreaService areaService;
	
	//物流运送信息Service层业务
	@Autowired
	private Inc_shipinfoService inc_shipinfoService;
	//退换货dao接口
	@Autowired
	private GoodsreturnDao goodsreturnDao;
	
	@Autowired
	private CommparaService commparaService;
	@Autowired
	private FundaccountService fundaccountService;
	@Autowired
	private FundhistoryService fundhistoryService;
	
	//0：等待买家付款；1：等待卖家发货；2：等待买家确认收货；3：交易关闭；4：交易成功； 5:申请退货
	//状态常量
	public static final String STATE_WAIT_BUYER_PAY = "0";
	public static final String STATE_WAIT_SALER_SHIP = "1";
	public static final String STATE_WAIT_BUYER_RECEIPT = "2";
	public static final String STATE_TRADE_CLOSE = "3";
	public static final String STATE_TRADE_SUCCESS = "4";
	public static final String STATE_RETURN = "5";
	//动作常量
	public static final String ACTION_SELLER_DELIVER = "商家发货";
	public static final String ACTION_BUYER_PAYMENT = "买家付款";
	public static final String ACTION_BUYER_RECEIPT = "买家确认收货";
	public static final String ACTION_BUYER_CANCEL = "买家手动取消订单";
	public static final String ACTION_BUYER_SUBMIT_ORDER = "买家提交订单";
	
	
	//定时任务常量
	private static final String UPDATE_UNPAY_TIME = "1"; //代表1天，24小时
	private static final String UPDATE_UNRECEIPT_TIME = "7"; //代表7天
	
	private static final String TIP_UNPAY_ORDER_AUTO_CLOSE = "24小时内未付款交易自动关闭";
	private static final String TIP_UNRECEIPT_BUYER_AUTO_RECEIPT = "7天内系统自动确认收货";
	
	
	private static final String KEY_ORDER_DETAIL_LIST = "orderdetails";
	
	private static final String ORDER_TYPE = "goods"; //实体商品类型
	//7天内完成的订单允许退换货
	private static final String RETURN_ALLOW_TIME = "7";
	//物流公司字段编码
	private static final String SHIP_TYPE = "ship_id";
	//0:表示未删除的订单
	private static final String IS_DEL = "0";

	//个人-会员类型-资金账户
	private static final String FUNDACCOUNT_TYPE_PERSONAL = "0";
	//资金账户章台可用
	private static final String FUNDACCOUNT_STATUS_ENABLED = "0";
	//资金交易历史-支付订单
	private static final String FUNDHISTORY_ACTION_TYPE_PAYORDER = "2";
	//资金交易历史-支付订单描述
	private static final String FUNDHISTORY_ACTION_TYPE_DESC_PAYORDER = "订单支付";
	/**
	 * 设置订单状态并添加历史记录
	 * @param order
	 */
	public void updateState(Goodsorder order,Orderhistory orderhistory){
		Map<String,String> map = new HashMap<String,String>();
		map.put("order_id", order.getOrder_id());
		String orderSate = order.getOrder_state();
		map.put("order_state", orderSate);
		//修改订状态
		goodsorderDao.updateOrderState(map);
		
		//添加历史记录
		orderhistoryDao.insertOrderhistory(orderhistory);
	}
	
	
	//获取退换货符合条件的订单
	public PageResult getGoodsreturnList(GoodsorderQueryForm goodsorderQueryForm) {
//		goodsorderQueryForm.setOrder_state(STATE_TRADE_SUCCESS);
		String state = STATE_TRADE_SUCCESS+","+STATE_RETURN;
		goodsorderQueryForm.setOrder_state(state);
		goodsorderQueryForm.setReturn_time(RETURN_ALLOW_TIME);
		goodsorderQueryForm.setOrder_type(ORDER_TYPE);
		PageResult result = getGoodsOrderListByPage(goodsorderQueryForm);
		//获取已经申请的退货记录
		Map<String,List<Integer>> returnInfo = getReturnInfo(goodsorderQueryForm.getUser_id());
		//设置返回列表信息
		List list = result.getList();
		if(list != null) {
			for(Map<String, Object>  goodsOrder : (List<Map<String, Object>>) list) {
				String order_goods_desc = (String) goodsOrder.get("order_goods_desc");
				String order_id = (String) goodsOrder.get("order_id");
				List<Integer> goodsIds = returnInfo.get(order_id);
				if(order_goods_desc != null) {
					JSONArray array = JSONArray.fromObject(order_goods_desc);
			        //List<Order_goods_desc> orders = JSONArray.toList(array, Order_goods_desc.class);
			        List<Order_goods_desc> orders = JSONArray.toList(array, new Order_goods_desc(), new JsonConfig());
			        for(Order_goods_desc order: orders){
			        	
			        	String img = order.getImg();
			        	if(img!=null&&img.indexOf(",")>0){
			        		img = img.substring(0,img.indexOf(","));
			        	}
			        	order.setImg(ImgPathUitls.filterImagePath_spec(img,70,70));
			        	if(goodsIds!=null && goodsIds.contains(order.getId())){
			        		order.setCanApply(false);
			        	}else{
			        		order.setCanApply(true);
			        	}
			        }
					goodsOrder.put("ordergoods", orders);
				}
			}
		}
		return result ;
	}
	/**
	 * 获取已经申请过的退换货记录信息
	 * @param user_id 买家表示
	 * @return
	 */
	private Map<String,List<Integer>> getReturnInfo(String user_id){
		Map<String,List<Integer>> result = new HashMap<String,List<Integer>>();
		List returngoods = goodsreturnDao.getOrderAndGoodsId(user_id);
		if(returngoods==null||returngoods.isEmpty()){
			return result;
		}
		for(Object obj : returngoods){
			Map info = (Map)obj; 
			String orderId = (String)info.get("order_id");
			int goods_id = (Integer)info.get("goods_id");
			if(result.containsKey(orderId)){
				List<Integer> vals = result.get(orderId);
				if(!vals.contains(goods_id)){
					vals.add(goods_id);
					result.put(orderId, vals);
				}
			}else{
				List<Integer> vals = new ArrayList<Integer>();
				vals.add(goods_id);
				result.put(orderId, vals);
			}
		}
		return result;
	}
	
	//根据cust_id统计出订单状态各自的数量
	public List getOrderCountByCustid(Integer cust_id){
		Map map = new HashMap();
		map.put("cust_id", cust_id);
		return this.goodsorderDao.getOrderCount(map);
	}
	
	//商家发货，修改数据库状态为 2：等待买家确认收货；
	@Transactional
	public void sellerDeliver(String order_id,String cust_id){
		Goodsorder goodsorder = getGoodsorderByOrderid(order_id);
		String order_state = goodsorder.getOrder_state();
		String sale_cust_id = goodsorder.getSale_cust_id();
		if(goodsorder != null && order_state.equals(STATE_WAIT_SALER_SHIP) && cust_id.equals(sale_cust_id)){
			Map<String,String> map = new HashMap<String,String>();
			map.put("order_id", order_id);
			map.put("order_state", STATE_WAIT_BUYER_RECEIPT);
			goodsorderDao.sellerDeliver(map);
			//插入历史记录
			Orderhistory orderhistory = new Orderhistory();
			orderhistory.setOrder_id(order_id);
			orderhistory.setAction_name(ACTION_SELLER_DELIVER);
			orderhistory.setUser_id(null);
			this.orderhistoryDao.insertOrderhistory(orderhistory);
		}
	}
	
	@Transactional
	public void sellerDeliver(String order_id,String cust_id, Inc_shipinfo inc_shipinfo){
		Goodsorder goodsorder = getGoodsorderByOrderid(order_id);
		String order_state = goodsorder.getOrder_state();
		String sale_cust_id = goodsorder.getSale_cust_id();
		if(goodsorder != null && order_state.equals(STATE_WAIT_SALER_SHIP) && cust_id.equals(sale_cust_id)){
			Map<String,String> map = new HashMap<String,String>();
			map.put("order_id", order_id);
			map.put("order_state", STATE_WAIT_BUYER_RECEIPT);
			goodsorderDao.sellerDeliver(map);
			
			//物流公司名称
			String ship_name = commparaService.getParakeyByParacode(SHIP_TYPE, inc_shipinfo.getShip_id());
			inc_shipinfoService.insert(inc_shipinfo);
			
			//插入历史记录
			Orderhistory orderhistory = new Orderhistory();
			orderhistory.setOrder_id(order_id);
			orderhistory.setAction_name(ACTION_SELLER_DELIVER + ", 快递公司:" + ship_name + ", 运单号:" + inc_shipinfo.getShip_no());
			orderhistory.setUser_id(null);
			this.orderhistoryDao.insertOrderhistory(orderhistory);
		}
		
	}
	
	//用户付款成功修改数据库状态为 1：等待卖家发货；
	@Transactional
	public void userPay(String order_id){
		//根据订单找出订单信息
		Goodsorder goodsorder = getGoodsorderByOrderid(order_id);
		//获取订单当前状态
		String order_state = goodsorder.getOrder_state();
		//当订单状态为0：等待买家付款，执行数据库操作
		if(order_state != null && order_state.equals(STATE_WAIT_BUYER_PAY)){
			//把订单状态修改为 1：等待卖家发货
			Map<String,String> map = new HashMap<String,String>();
			map.put("order_id", order_id);
			map.put("order_state", STATE_WAIT_SALER_SHIP);
			goodsorderDao.userPay(map);
			//插入历史记录
			Orderhistory orderhistory = new Orderhistory();
			orderhistory.setOrder_id(order_id);
			orderhistory.setAction_name(ACTION_BUYER_PAYMENT);
			orderhistory.setUser_id(null);
			this.orderhistoryDao.insertOrderhistory(orderhistory);
		}
	}
	
	/**
	 * 资金账户支付订单
	 * @param user_id 用户id
	 * @param order_id 订单id
	 * @return 交易状态码
	 * @author 陈显革
	 */
	@Transactional
	public String payGoodsorderByFundaccount(String user_id, String order_id) {
		Goodsorder goodsorder = goodsorderDao.getByPk(order_id);
		if(goodsorder == null) {
			return "0101";//订单不存在
		}
		//订单状态
		String order_state = goodsorder.getOrder_state();
		if(!STATE_WAIT_BUYER_PAY.equals(order_state)) {
			return "0102";//订单状态不正确
		}
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.valueOf(user_id), FUNDACCOUNT_TYPE_PERSONAL);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		//订单总价
		BigDecimal total_price = new BigDecimal(goodsorder.getTatal_amount());
		if(total_price.compareTo(BigDecimal.ZERO) < 0) {
			return "0103";
		}
		BigDecimal fund_use_num = fundaccount.getUse_num();//资金账户可用余额
		if(total_price.compareTo(fund_use_num) > 0) {
			return "0203";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = total_price.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//支付方总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		fund_num = fund_num.subtract(trade_fund_num);
		
		//支付方可用余额
		BigDecimal use_fund_num = fundaccount.getUse_num();
		use_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_fund_num = use_fund_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setFund_num(fund_num);
		fundaccount.setUse_num(use_fund_num);
		fundaccountService.updateFund_numAndUse_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_PAYORDER);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(trade_fund_num);
		from_fundhistory.setIn_date(new Date());
		from_fundhistory.setUser_id(Integer.valueOf(user_id));
		from_fundhistory.setReason("支付订单, 订单号: " + order_id);
		fundhistoryService.insert(from_fundhistory);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("order_id", order_id);
		map.put("order_state", STATE_WAIT_SALER_SHIP);
		goodsorderDao.userPay(map);
		//插入历史记录
		Orderhistory orderhistory = new Orderhistory();
		orderhistory.setOrder_id(order_id);
		orderhistory.setAction_name(ACTION_BUYER_PAYMENT);
		orderhistory.setUser_id(null);
		this.orderhistoryDao.insertOrderhistory(orderhistory);
		
		return "0100";//订单支付成功
	}
	
	//买家确认收货
	//0：等待买家付款；1：等待卖家发货；2：等待买家确认收货；3：交易关闭；4：交易成功；
	@Transactional
	public int confirmReceipt(String order_id,String user_id){
		Goodsorder goodsorder = getGoodsorderByOrderid(order_id);
		String orderstate = goodsorder.getOrder_state();
		String buy_cust_id = goodsorder.getBuy_cust_id();
		//订单状态为(2：等待买家确认收货)时，且当前操作人是该订单的买家，才能确认收货并修改此订单状态为（4：交易成功；）
		if(orderstate.equals(STATE_WAIT_BUYER_RECEIPT) && buy_cust_id.equals(user_id)){
			//买家确认收货
			Map<String,String> map = new HashMap<String,String>();
			map.put("order_id", order_id);
			map.put("order_state", STATE_TRADE_SUCCESS);
			goodsorderDao.userReceipt(map);
			//操作历史记录
			Orderhistory orderhistory = new Orderhistory();
			orderhistory.setOrder_id(order_id);
			orderhistory.setAction_name(ACTION_BUYER_RECEIPT);
			orderhistory.setUser_id(user_id);
			this.orderhistoryDao.insertOrderhistory(orderhistory);
			return 0;
		}else{
			return 1;
		}
	}
	
	 //订单支付修改订单支付状态
	@Transactional
	public  Integer unionPayOrder(String order_id){
		Goodsorder goodsorder = getGoodsorderByOrderid(order_id);
		if(goodsorder!=null){
			String orderstate = goodsorder.getOrder_state();
			if(orderstate.equals(STATE_WAIT_BUYER_PAY)){
				//修改订单状态
				Map<String,String> map = new HashMap<String,String>();
				if(goodsorder.getOrder_type().equals("goods")){
					map.put("order_state", STATE_WAIT_SALER_SHIP);
				}else{
					map.put("order_state", STATE_TRADE_SUCCESS);
				}
				map.put("order_id", order_id);
				goodsorderDao.updateOrderState(map);
				//操作历史记录
				Orderhistory orderhistory = new Orderhistory();
				orderhistory.setOrder_id(order_id);
				orderhistory.setAction_name(ACTION_BUYER_PAYMENT);
				this.orderhistoryDao.insertOrderhistory(orderhistory);
				return 1;
			}
		}
		return 0;
	}
	
	
	
	
	
	//买家会员未付款状态下，可取消订单
	@Transactional
	public int cancelOrder(String order_id,String user_id){
		Goodsorder goodsorder = getGoodsorderByOrderid(order_id);
		String orderstate = goodsorder.getOrder_state();
		String buy_cust_id = goodsorder.getBuy_cust_id();
		//订单状态为(0：等待买家付款)时，可取消变成交易关闭
		if(orderstate.equals(STATE_WAIT_BUYER_PAY) && buy_cust_id.equals(user_id)){
			Map<String,String> map = new HashMap<String,String>();
			map.put("order_id", order_id);
			map.put("order_state", STATE_TRADE_CLOSE);
			goodsorderDao.updateOrderState(map);
			//操作历史记录
			Orderhistory orderhistory = new Orderhistory();
			orderhistory.setOrder_id(order_id);
			orderhistory.setAction_name(ACTION_BUYER_CANCEL);
			orderhistory.setUser_id(user_id);
			this.orderhistoryDao.insertOrderhistory(orderhistory);
			//库存增加
			List orderdetails = orderdetailDao.getOrderdetailList(order_id);
			
			if(orderdetails == null) {
				return 0;
			}
			//批量更新库存记录
			List<GoodsStockHistory> goodsStockHistorys = new ArrayList<GoodsStockHistory>();
			//批量更新库存记录
			List<SimpleGoodsCartItem> batchGoodsList = new ArrayList<SimpleGoodsCartItem>();
			for(int i=0; i<orderdetails.size(); i++) {
				Map<String, Object> goods = (Map<String, Object>) orderdetails.get(i);
				Integer goods_id = (Integer) goods.get("goods_id");
				Integer order_num = (Integer) goods.get("order_num");
				//
				SimpleGoodsCartItem sgci = new SimpleGoodsCartItem();
				sgci.setGoods_id(goods_id);
				sgci.setAmount(order_num);
				batchGoodsList.add(sgci);
				
				GoodsStockHistory goodsStockHistory = new GoodsStockHistory();
				//增加商品库存变化记录
				goodsStockHistory.setGoodsId(goods_id);
				goodsStockHistory.setOrderNo(order_id);
				goodsStockHistory.setChangeAmount(order_num);
				goodsStockHistory.setChangeType(true);
				goodsStockHistory.setChangeReason((short)1);
				goodsStockHistory.setChangeDesc("买家取消订单,订单号:" + order_id);
				goodsStockHistorys.add(goodsStockHistory);
			}
			//先更新记录
			goodsStockHistoryMapper.batchInsert(goodsStockHistorys);
			//再更新库存
			goodsDao.batchUpdateStockAndSaledNumByRollback(batchGoodsList);
			
			return 0;
		}else{
			return 1;
		}
	}
	
	//根据订单号获取订单历史记录信息
	public List getOrderhistoryListByOrderid(String order_id){
		return orderhistoryDao.getOrderhistoryList(order_id);
	}
	
	/**
	 * 取得实体商品订单详情
	 * @param order_id
	 * @return
	 */
	public Goodsorder getGoodsorderByOrderid(String order_id){
		Goodsorder goodsorder = goodsorderDao.getGoodsorderByOrderid(order_id);
		
		String area_name = areaService.getAreaAttrNameByAreaIdAttr(goodsorder.getArea_attr(), "1");
		goodsorder.setArea_name(area_name);
		
		List orderdetailList = orderdetailDao.getOrderdetailList(order_id);
		
		//商品图片url处理
		ImgPathUitls.filterImagePath_spec(orderdetailList,70,70);
		
		goodsorder.setOrderdetails(orderdetailList);
		return goodsorder;
	}
	
	/**
	 * 取得机票订单详情
	 * @param order_id
	 * @return
	 */
	public Goodsorder getAirlineGoodsorderByOrderid(String order_id) {
		Goodsorder goodsorder = goodsorderDao.getGoodsorderByOrderid(order_id);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_id", order_id);
		map.put("hasContactDetail", "1");
		map.put("hasPassengersDetail", "1");
		map.put("hasJourneysheetDetail", "1");
		
		List orderdetailList = api_air_orderdetailDao.getList(map);
		goodsorder.setOrderdetails(orderdetailList);
		return goodsorder;
	}
	
	/**
	 * 电影票订单详情
	 * @param order_id
	 * @return
	 */
	public Goodsorder getMovieGoodsorderByOrderid(String order_id) {
		Goodsorder goodsorder = goodsorderDao.getGoodsorderByOrderid(order_id);
		
		MovieOrderDetail movieOrderDetail = movieOrderDetailMapper.selectByOrderId(order_id);
		List orderdetailList = new ArrayList();
		orderdetailList.add(movieOrderDetail);
		
		goodsorder.setOrderdetails(orderdetailList);
		return goodsorder;
	}
	
	/**
	 * 彩票订单详情
	 * @param order_id
	 * @return
	 */
	public Goodsorder getLotteryGoodsorderByOrderid(String order_id) {
		Goodsorder goodsorder = goodsorderDao.getGoodsorderByOrderid(order_id);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_no", order_id);
		List orderdetailList = lotteryOrderDao.getList(map);
		
		goodsorder.setOrderdetails(orderdetailList);
		return goodsorder;
	}
	
	//会员中心机票订单
	public PageResult getAirLineOrderListByPage(GoodsorderQueryForm goodsorderQueryForm){
		List<Map<String, Object>> list = goodsorderDao.getAirLineorderByPage(goodsorderQueryForm);
		if(list == null || list.size() == 0){
			return new PageResult();
		}
		PageResult result = PageResultBuilder.build(goodsorderQueryForm.getPg(), list);
		return result;
	}
	//会员中心彩票列表
	public PageResult getLotteryOrderListByPage(GoodsorderQueryForm goodsorderQueryForm){
		List<Map<String, Object>> list = goodsorderDao.getLotteryOrderListByPage(goodsorderQueryForm);
		if(list == null || list.size() == 0){
			return new PageResult();
		}
		PageResult result = PageResultBuilder.build(goodsorderQueryForm.getPg(), list);
		return result;
	}
	
	//会员中心电影票订单列表
	@SuppressWarnings("unchecked")
	public PageResult movieOrderListByPage(GoodsorderQueryForm goodsorderQueryForm){
		List<Map<String, Object>> list = goodsorderDao.movieOrderListByPage(goodsorderQueryForm);
		if(list == null || list.size() == 0){
			return new PageResult();
		}
		PageResult result = PageResultBuilder.build(goodsorderQueryForm.getPg(), list);
		return result;
	} 
	
	/**
	 * 通过订单号获取订单详情
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrderInfoByOrderNo(GoodsorderQueryForm gf){
		if(gf!=null){
			return 	goodsorderDao.getOrderInfoByOrderNo(gf);
		}
		return null;
	}
	
	//会员中心查询订单
	public PageResult getGoodsOrderListByPage(GoodsorderQueryForm goodsorderQueryForm){
		List<Map<String, Object>> list = goodsorderDao.getGoodsorderByPage(goodsorderQueryForm);
		//TODO 以下方法体待删除
		if(list == null || list.size() == 0){
			return new PageResult();
		}
		
		String area_attr, area_name;
		//存储订单号变量
		int size = list.size();
		List order_ids = new ArrayList(size);
		
		for(Map<String, Object> map : list){
			area_attr = (String)map.get("area_attr");
			area_name = areaService.getAreaAttrNameByAreaIdAttr(area_attr, "1");
			map.put("area_name", area_name);
			
			String order_id = "";
			if(map.get("order_id") != null){
				order_id = map.get("order_id").toString();
			}
			order_ids.add(order_id);
		}
		 
		//根据订单号串 找出所有的订单详细列表
		List orderdetailList = orderdetailDao.getOrderdetailByOrderIds(order_ids);
		
		Iterator itr = list.iterator();
		
		//把找出的订单详细列表并入到订单列表中，根据订单号匹配并入
		List resultList = new ArrayList(size);
		while(itr.hasNext()){
			Map ordMap = (HashMap)itr.next();
			String order_id = "";
			if(ordMap.get("order_id") != null){
				order_id = ordMap.get("order_id").toString();
			}
			
			List childList = new ArrayList();
			Iterator childIt = orderdetailList.iterator();
			while(childIt.hasNext()){
				Map childCatMap = (HashMap)childIt.next();
				String _order_id = "";
				if(childCatMap.get("order_id") != null){
					_order_id = childCatMap.get("order_id").toString();
				}
				
				if(order_id.equals(_order_id)){
					ImgPathUitls.filterImagePath_spec(childCatMap,"70_70" );
					childList.add(childCatMap);
					//childIt.remove();
				}
			}
			
			//将订单详细列表添加到订单对象中
			ordMap.put(KEY_ORDER_DETAIL_LIST, childList);
			
			resultList.add(ordMap);
		}
		
		PageResult result = PageResultBuilder.build(goodsorderQueryForm.getPg(), list);
		return result;
	}
	@Transactional
	public List<String> insertGoodsOrder(Map orderMap,Map addrMap,Invoice invoice,List cartList) {
		return insertGoodsOrder(orderMap, addrMap, invoice, cartList, false);
	}
	
	//把商品里的商品id，name，img转换成订单表里的order_goods_desc
	public String getOrderGoodsDescByCartList(List cartList){
		List goodsIdList = new ArrayList();
		Iterator cartItr = cartList.iterator();
		while(cartItr.hasNext()){
			CartItem cartItem = (CartItem)cartItr.next();
			goodsIdList.add(cartItem.getGoods().getGoods_id());
		}
		
		List goodsList = this.goodsDao.getGoodsDetailListByIds(goodsIdList);
		Iterator goodsItr = goodsList.iterator();
		List<Order_goods_desc> order_goods_descList = new ArrayList<Order_goods_desc>();
		while(goodsItr.hasNext()){
			Map goodsMap = (Map)goodsItr.next();
			Order_goods_desc order_goods_desc = new Order_goods_desc();
			String goods_name = "",img_path = "";
			Integer goods_id = 0;
			if(goodsMap.get("goods_id") != null){
				goods_id = Integer.parseInt( goodsMap.get("goods_id").toString() );
			}
			if(goodsMap.get("goods_name") != null){
				goods_name = goodsMap.get("goods_name").toString();
			}
			if(goodsMap.get("img_path") != null){
				img_path = goodsMap.get("img_path").toString();
			}
			order_goods_desc.setId(goods_id);
			order_goods_desc.setName(goods_name);
			order_goods_desc.setImg(img_path);
			order_goods_descList.add(order_goods_desc);
		}
		JSONArray jsonArray = JSONArray.fromObject(order_goods_descList);
		return jsonArray.toString();
	}
	
	/**
	 * 
	 * @param addrMap 收货地址信息
	 * @param invoice 发票信息
	 * @param cartList 购物车信息
	 */
	@Transactional
	public List<String> insertGoodsOrder(Map orderMap,Map addrMap,Invoice invoice,List cartList, boolean returnOrderPrice) {
		if(CollectionUtils.isEmpty(cartList)){
			return Collections.EMPTY_LIST;
		}
		
		//记录购物车中商家总数信息
		//根据不同的商家拆分订单
		List custList = new ArrayList();
		splitToCustList(cartList,custList);
		
		List batchOrderList = new ArrayList();
		List batchOrderdetailList = new ArrayList();
		List batchOrderhistoryList = new ArrayList();
		List<SimpleGoodsCartItem> batchGoodsList = new ArrayList<SimpleGoodsCartItem>();
		
		//生成的订单ID列表
		List<String> orderIdList = new ArrayList<String>();
		//买家ID
		String user_id = String.valueOf(orderMap.get("buy_cust_id"));
		
		//订单来源类型
		String order_src = "";
		if(orderMap.get("order_src") != null){
			order_src = orderMap.get("order_src").toString();
		}
		
		
		//根据拆分的订单准备数据插入数据库
		Iterator custItr = custList.iterator();
		while(custItr.hasNext()){
			CustOrder custOrder = (CustOrder)custItr.next();
			//卖家ID
			String cust_id = custOrder.getCust_id();
			//订单号
			String order_id = order_id();
			//购物车
			List _cartList = custOrder.getCartList();
			
			/////////////////////////////////订单主题表数据准备开始/////////////////////////////////////////////
			
			Goodsorder goodsorder = new Goodsorder();
			//设置通用字段信息，如买家ID
			setOrderMap(goodsorder,orderMap);
			//设置发票信息
			setInvoice(goodsorder,invoice);
			//设置收货人信息
			setAddress(goodsorder,addrMap);
			goodsorder.setOrder_id(order_id);
			//卖家标识
			goodsorder.setSale_cust_id(cust_id);
			goodsorder.setOrder_state(STATE_WAIT_BUYER_PAY);
			//商品总金额
			Float goods_amount = getCartTotalPrice(_cartList);
			//订单总金额，暂时先等于商品总金额，运费、优惠等计算暂不计算在内
			Float tatal_amount = goods_amount;
			goodsorder.setGoods_amount(String.valueOf(goods_amount));
			goodsorder.setTatal_amount(String.valueOf(tatal_amount));
			goodsorder.setOrder_src(order_src);
			//设置order json商品信息
			goodsorder.setOrder_goods_desc(getOrderGoodsDescByCartList(_cartList));
			//0:表示未删除的订单
			goodsorder.setIs_del(IS_DEL);
			//返回信息中是否包含订单价格
			BigDecimal totalMoney = new BigDecimal(goods_amount);
			totalMoney = totalMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
			orderIdList.add(returnOrderPrice ? (order_id + ":" + totalMoney.toPlainString()) : order_id);
			/////////////////////////////////订单主题表数据准备结束/////////////////////////////////////////////
			
			batchOrderList.add(goodsorder);
			
			//订单历史记录orderhistory信息数据准备
			readyOrderhistoryData(user_id, order_id,batchOrderhistoryList);
			
			//订单详细orderdetail信息数据准备
			readyOrderdetailData(_cartList,order_id,batchOrderdetailList);
			
		}
		
		//插入订单详细信息
		orderdetailDao.batchInsertOrderdetail(batchOrderdetailList);
		//插入订单主表信息
		goodsorderDao.batchInsertGoodsorder(batchOrderList);
		//插入订单历史记录信息
		orderhistoryDao.batchInsertOrderhistory(batchOrderhistoryList);
		
		//商品库存和销量信息数据准备
		readyGoodsStockAndSaledNumData(cartList, batchGoodsList);
		//批量更新库存记录
		List<GoodsStockHistory> goodsStockHistorys = new ArrayList<GoodsStockHistory>();
		for(Orderdetail od : (List<Orderdetail>)batchOrderdetailList) {
			GoodsStockHistory goodsStockHistory = new GoodsStockHistory();
			//增加商品库存变化记录
			goodsStockHistory.setGoodsId(Integer.parseInt(od.getGoods_id()));
			goodsStockHistory.setOrderNo(od.getOrder_id());
			goodsStockHistory.setChangeAmount(-1 * Integer.parseInt(od.getOrder_num()));
			goodsStockHistory.setChangeType(false);
			goodsStockHistory.setChangeReason((short)0);
			goodsStockHistory.setChangeDesc("买家购买商品,订单号:" + od.getOrder_id());
			goodsStockHistorys.add(goodsStockHistory);
		}
		goodsStockHistoryMapper.batchInsert(goodsStockHistorys);
		
		//批量更新商品库存和销量
		goodsDao.batchUpdateStockAndSaledNum(batchGoodsList);
		
		return orderIdList;
	}
	
	//订单历史记录orderhistory信息数据准备
	public void readyOrderhistoryData(String user_id, String order_id,List batchOrderhistoryList){
		Orderhistory orderhistory = new Orderhistory();
		orderhistory.setUser_id(user_id);
		orderhistory.setOrder_id(order_id);
		orderhistory.setAction_name(ACTION_BUYER_SUBMIT_ORDER);
		batchOrderhistoryList.add(orderhistory);
	}
	
	//订单详细orderdetail信息数据准备
	public void readyOrderdetailData(List cartList,String order_id,List batchOrderdetailList){
		Iterator cartItr = cartList.iterator();
		while(cartItr.hasNext()){
			CartItem cartItem = (CartItem)cartItr.next();
			Orderdetail orderdetail = new Orderdetail();
			orderdetail.setOrder_id(order_id);
			orderdetail.setGoods_id(String.valueOf(cartItem.getGoods().getGoods_id()));
			orderdetail.setOrder_price(cartItem.getGoods().getSale_price());
			orderdetail.setOrder_num(String.valueOf(cartItem.getAmount()));
			orderdetail.setGoods_attr(cartItem.getGoods_param());
			batchOrderdetailList.add(orderdetail);
		}
	}
	
	//商品库存和销量信息数据准备
	public void readyGoodsStockAndSaledNumData(List cartList, List<SimpleGoodsCartItem> goodsItemList){
		Iterator cartItr = cartList.iterator();
		int index = 0;
		while(cartItr.hasNext()){
			CartItem cartItem = (CartItem)cartItr.next();
			SimpleGoodsCartItem goodsItem = new SimpleGoodsCartItem();
			goodsItem.setGoods_id(cartItem.getGoods().getGoods_id());
			index = goodsItemList.indexOf(goodsItem);
			if(index >= 0){
				SimpleGoodsCartItem listGoodsItem = goodsItemList.get(index);
				listGoodsItem.setAmount(listGoodsItem.getAmount() + cartItem.getAmount());
			}
			else{
				goodsItem.setAmount(cartItem.getAmount());
				goodsItemList.add(goodsItem);
			}
		}
	}
	
	//根据不同的商家拆分订单
	public void splitToCustList(List cartList,List custList){
		Iterator itr = cartList.iterator();
		while(itr.hasNext()){
			CartItem cartItem = (CartItem)itr.next();
			String cust_id = cartItem.getGoods().getCust_id();
			CustOrder _custOrder = isExistCust(custList,cust_id);
			if(_custOrder != null){
				CustOrder custOrder = _custOrder;
				custOrder.getCartList().add(cartItem);
			}else{
				CustOrder custOrder = new CustOrder();
				custOrder.setCust_id(cust_id);
				custOrder.getCartList().add(cartItem);
				custList.add(custOrder);
			}
		}
	}
	
	public String order_id(){
		StringBuffer buf = new StringBuffer(17);
		buf.append(DATE_FORMATER.format(new Date()));

		double random = Math.random();
		int randomInt = (int)(random * 1000);
		if(randomInt < 10){
			buf.append("00").append(randomInt);
			return buf.toString();
		}
		
		if(randomInt < 100){
			buf.append("0").append(randomInt);
			return buf.toString();
		}
		
		buf.append(randomInt);
		return buf.toString();
	}
	
	//判断cust_id是否存在
	public CustOrder isExistCust(List<CustOrder> custList,String cust_id){
		CustOrder ret = null;
		for(CustOrder custOrder:custList){
			String _cust_id = custOrder.getCust_id();
			if(_cust_id.equals(cust_id)){
				ret = custOrder;
				break;
			}
		}
		return ret;
	}
	
	//对未付款、且已经过去24小时的订单执行交易关闭操作
	public void updateUnpayOrder(){
		//1天，代表24小时
		Map<String,String> map = new HashMap<String,String>();
		map.put("time", UPDATE_UNPAY_TIME);
		map.put("old_state", STATE_WAIT_BUYER_PAY);
		map.put("new_state", STATE_TRADE_CLOSE);
		map.put("action_name", TIP_UNPAY_ORDER_AUTO_CLOSE);
		//找出复合条件的订单
		List unpayList = this.goodsorderDao.getUnpayOrderGoods(map);
		//回滚商品库存和销售量
		if(unpayList != null && unpayList.size()>0){
			doUnpayOrder(unpayList,map);
		}
	}
	
	@Transactional
	public void doUnpayOrder(List unpayList,Map map){
		this.orderhistoryDao.insertUpdateUnpayOrder(map);
		this.goodsorderDao.updateUnpayOrder(map);
		
		//批量更新库存记录
		List<GoodsStockHistory> goodsStockHistorys = new ArrayList<GoodsStockHistory>();
		//批量更新库存记录
//		List<SimpleGoodsCartItem> batchGoodsList = new ArrayList<SimpleGoodsCartItem>();
		for(int i=0; i<unpayList.size(); i++) {
			Map<String, Object> goods = (Map<String, Object>) unpayList.get(i);
			String order_id = (String) goods.get("order_id");
			Integer goods_id = (Integer) goods.get("goods_id");
			Integer order_num = (Integer) goods.get("amount");
			//
//			SimpleGoodsCartItem sgci = new SimpleGoodsCartItem();
//			sgci.setGoods_id(goods_id);
//			sgci.setAmount(order_num);
//			batchGoodsList.add(sgci);
			
			GoodsStockHistory goodsStockHistory = new GoodsStockHistory();
			//增加商品库存变化记录
			goodsStockHistory.setGoodsId(goods_id);
			goodsStockHistory.setOrderNo(order_id);
			goodsStockHistory.setChangeAmount(order_num);
			goodsStockHistory.setChangeType(true);
			goodsStockHistory.setChangeReason((short)1);
			goodsStockHistory.setChangeDesc("买家逾期未支付订单,系统取消订单,订单号:" + order_id);
			goodsStockHistorys.add(goodsStockHistory);
		}
		//先更新记录
		goodsStockHistoryMapper.batchInsert(goodsStockHistorys);
		
		this.goodsDao.batchUpdateStockAndSaledNumByRollback(unpayList);
		
		
		//再更新库存
//		goodsDao.batchUpdateStockAndSaledNumByRollback(batchGoodsList);
		
	}
	
	//对已发货、未确认收货、且过去7天的订单执行自动确认收货操作，交易成功 
	@Transactional
	public void updateUnReceiptOrder(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("time", UPDATE_UNRECEIPT_TIME);
		map.put("old_state", STATE_WAIT_BUYER_RECEIPT);
		map.put("new_state", STATE_TRADE_SUCCESS);
		map.put("action_name", TIP_UNRECEIPT_BUYER_AUTO_RECEIPT);
		this.orderhistoryDao.insertUpdateUnReceiptOrder(map);
		this.goodsorderDao.updateUnReceiptOrder(map);
	}
	
	public void setOrderMap(Goodsorder goodsorder,Map orderMap){
		//买家ID
		goodsorder.setBuy_cust_id(orderMap.get("buy_cust_id")!=null?orderMap.get("buy_cust_id").toString():"");
	}
	
	//设置收货地址
	public void setAddress(Goodsorder goodsorder,Map addrMap){
		goodsorder.setConsignee(addrMap.get("cust_name")!=null?addrMap.get("cust_name").toString():"");
		goodsorder.setArea_attr(addrMap.get("area_attr")!=null?addrMap.get("area_attr").toString():"");
		goodsorder.setMobile(addrMap.get("cell_phone")!=null?addrMap.get("cell_phone").toString():"");
		goodsorder.setAddress(addrMap.get("address")!=null?addrMap.get("address").toString():"");
	}
	
	//发票对象转订单实体对象
	public void setInvoice(Goodsorder goodsorder,Invoice invoice){
		goodsorder.setInvoice_type(invoice.getInvoice_type());
		goodsorder.setInvoice_top(invoice.getInvoice_top());
		goodsorder.setCompany_name(invoice.getCompany_name());
		goodsorder.setIdent_number(invoice.getIdent_number());
		goodsorder.setRegis_address(invoice.getRegis_address());
		goodsorder.setRegis_tel(invoice.getRegis_tel());
		goodsorder.setOpen_bank(invoice.getOpen_bank());
		goodsorder.setBank_account(invoice.getBank_account());
		goodsorder.setInvoice_content(invoice.getInvoice_content());
	}
	
	public Float getCartTotalPrice(List cartList){
		if(cartList == null || cartList.size() == 0){
			return Float.valueOf(0L);
		}
		Iterator itr = cartList.iterator();
		Float totalAmount = new Float(0L);
		while(itr.hasNext()){
			CartItem cartItem = (CartItem)itr.next();
			Goods _goods = cartItem.getGoods();
			if(_goods != null && _goods.getSale_price() != null){
				Float price = Float.valueOf(_goods.getSale_price());
				totalAmount += price*(cartItem.getAmount());
			}
		}
		return totalAmount;
	}
	
	/**
	 * 计算购物车总价格
	 * @param cartList
	 * @return
	 */
	public BigDecimal calcCartTotalPrice(List<CartItem> cartList){
		if(cartList == null || cartList.size() == 0){
			return BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		BigDecimal total_price = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		for(CartItem ci : cartList) {
			Goods goods = ci.getGoods();
			if(goods != null && goods.getSale_price() != null) {
				total_price = total_price.add(new BigDecimal(goods.getSale_price()).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(ci.getAmount()).setScale(0, BigDecimal.ROUND_HALF_UP)) );
			}
		}
		
		return total_price;
	}
	
	
	/**
	 * 传入订单号进行付款操作 及状态修改，并插入订单历史信息
	 * @param orderNo
	 */
	@Transactional
	public void updateOrderAndPayStatus(String orderNo,Orderhistory orderhistory){
		Map<String,String> map = new HashMap<String, String>();
		map.put("order_id", orderNo);
		map.put("pay_state", "1");
		map.put("order_state", "1");
		goodsorderDao.updateOrderAndPayStatus(map);
		
		orderhistoryDao.insertOrderhistory(orderhistory);
		
	}
	
	/**
	 * 取得订单列表(分页)--管理员后台
	 * @param goodsorderQueryForm
	 * @return
	 */
	public PageResult getListByPage(GoodsorderQueryForm goodsorderQueryForm){
		//转换排序编码为具体的排序字段信息
		List<Map<String, Object>> list = goodsorderDao.getGoodsorderByPage(goodsorderQueryForm);
		PageResult result = PageResultBuilder.build(goodsorderQueryForm.getPg(), list);
		return result;
	}
	
	/**
	 * 查询订单详细信息(后台管理员)
	 * @param order_id
	 * @return
	 * @author 陈显革
	 * @date 2014-09-23
	 */
	public Map<String, Object> selectGoodsorderDetailById(String order_id) {
		return goodsorderDao.selectGoodsorderDetailById(order_id);
	}
	
	public Goodsorder getByPk(String order_id) {
		return goodsorderDao.getByPk(order_id);
	}
	
	/**
	 * 查询各类订单数量
	 * @param map (cust_id: 商家标识, order_states: 订单状态)
	 * @return
	 * @author 陈显革
	 * @date 2014-09-26
	 */
	public List<Map<String, Object>> selectOrderCountGroupByOrderState(Map<String,String> map) {
		return goodsorderDao.selectOrderCountGroupByOrderState(map);
	}


	public void cancellist(String id) {
		goodsorderDao.cancellist(id);
		
	}


	public List cancellists(String is_del) {
		
		return goodsorderDao.cancellists(is_del);
		
	}


	public void returncancel(String id) {
		goodsorderDao.returncancel(id);
		
	}


	public void removecancel(String id) {
		goodsorderDao.removecancel(id);
		
	}


	public List getlistByOrderid(String order_id) {
		return goodsorderDao.getlistByOrderid(order_id);
	}

}

class CustOrder{
	private String cust_id;
	private List cartList;
	
	public CustOrder(){
		cartList = new ArrayList();
	}
	
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public List getCartList() {
		return cartList;
	}
	public void setCartList(List cartList) {
		this.cartList = cartList;
	}
}
