package com.xingfugo.web.client.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Buyeraddr;
import com.xingfugo.business.module.CartItem;
import com.xingfugo.business.module.Goods;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Invoice;
import com.xingfugo.business.module.Order_goods_desc;
import com.xingfugo.business.module.PaymentOrder;
import com.xingfugo.business.module.UserCart;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.service.AreaService;
import com.xingfugo.business.service.BuyeraddrService;
import com.xingfugo.business.service.CartService;
import com.xingfugo.business.service.CollectService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.GoodsevalService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.OrderdetailService;
import com.xingfugo.business.service.UserCartService;
import com.xingfugo.business.service.VisitorCartService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.client.common.CartCookieUtil;
import com.xingfugo.web.client.common.SessionUtil;

//订单管理
@Controller
public class OrderController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(OrderController.class.getSimpleName());
	
	private final static String ORDER_ADDRESS_MAP = "order_address_map";
	
	private final static String ORDER_INVOICE_MAP = "order_invoice_map";
	
	private final static String ITEM_CART_LIST = "item_cart_list";
	
	private final static String HASH_CART_LIST = "hash_cart_list";
	
	private static final String LIST_DAYS_SEARCH = "list_days_search";
	private static final String ORDER_STATE = "order_state";
	private static final String DEFAULT_ORDER_STATE = "";
	private static final String DEFAULT_SEARCH_DAYS = "30";
	
	//实体商品
	private static final String ORDER_TYPE_REAL = "goods";
	//机票
	private static final String ORDER_TYPE_AIRLINE = "airline";
	//电影
	private static final String ORDER_TYPE_MOVIE = "movie";
	//彩票
	private static final String ORDER_TYPE_LOTTERY = "lottery_6";

	//付款方式-银联
	private static final String PAYMENT_WAY_BANK = "0";
	//付款方式-资金账户
	private static final String PAYMENT_WAY_FUNDACCOUNT = "1";
	
	//0:未删除订单
	private static final String IS_DEL = "0";
	
	/**
	 * 用户立即购买商品信息sessionKey
	 */
	private static final String BUY_INSTANTLY_KEY = "buy_instantly";
	
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private BuyeraddrService buyeraddrService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private GoodsorderService goodsorderService;
	@Autowired
	private CommparaService commparaService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsevalService goodsevalService;
	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private VisitorCartService visitorCartService;
	
	@Autowired
	private CollectService collectService;
	
	private CartService cartService;
	
	//会员中心查看订单历史记录
	@RequestMapping(value="user/orderhis-{id}",method=RequestMethod.GET)
	public String orderhis(@PathVariable(value = "id") String id,ModelMap model){
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(id);
		List orderhistoryList = goodsorderService.getOrderhistoryListByOrderid(id);
		String order_state_name = this.commparaService.getParakeyByParacode("order_state", goodsorder.getOrder_state());
		model.addAttribute("orderhis", orderhistoryList);
		model.addAttribute("order", goodsorder);
		model.addAttribute("order_state_name", order_state_name);
		return "user/order/history";
	}
	
	
	//买家会员确认收货，订单状态变为交易成功
	@RequestMapping(value="user/confirmReceipt-{id}",method=RequestMethod.GET)
	public String confirmReceipt(@PathVariable(value = "id") String id){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderService.confirmReceipt(id, user_id);
		return "redirect:"+basePath()+"user/orderlist.action";
	}
	
	//买家会员未付款状态下，可取消订单
	@RequestMapping(value="user/cancelOrder-{id}",method=RequestMethod.GET)
	public String cancelOrder(@PathVariable(value = "id") String id){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderService.cancelOrder(id, user_id);
		return "redirect:"+basePath()+"user/orderlist.action";
	}
	//买家会员未付款状态下和交易完成，可删除订单
	@RequestMapping(value="user/cancellist-{id}",method=RequestMethod.GET)
	public String cancellist(@PathVariable(value = "id") String id){
		//删除订单  将is_del设为1
		goodsorderService.cancellist(id);
		return "redirect:"+basePath()+"user/orderlist.action";
	}
	
	//还原回收站删除的订单，is_del为0的数据
	@RequestMapping(value="user/returncancel-{id}",method=RequestMethod.GET)
	public String returncancel(@PathVariable(value = "id") String id,ModelMap model){
		//显示订单  is_del为1的数据
		goodsorderService.returncancel(id);
		return "redirect:"+basePath()+"user/deletelist.action";
	}
	//彻底删除回收站的订单，is_del为2的数据
	@RequestMapping(value="user/remove-{id}",method=RequestMethod.GET)
	public String remove(@PathVariable(value = "id") String id,ModelMap model){
		//显示订单  is_del为1的数据
		goodsorderService.removecancel(id);
		return "redirect:"+basePath()+"user/deletelist.action";
	}
	
	//会员中心查看订单详细
	@RequestMapping(value="user/order-{id}",method=RequestMethod.GET , produces = "text/plain;charset=UTF-8")
	public String orderdetail(@PathVariable(value = "id") String id,ModelMap model, RedirectAttributes rAttr){
		Goodsorder goodsorder = goodsorderService.getByPk(id);
		if(goodsorder == null) {
			rAttr.addFlashAttribute("promptMessage", "订单不存在");
			return "redirect:"+basePath()+"user/orderlist.action";
		}
		if(ORDER_TYPE_REAL.equals(goodsorder.getOrder_type())) {
			goodsorder = goodsorderService.getGoodsorderByOrderid(id);
		} else if(ORDER_TYPE_AIRLINE.equals(goodsorder.getOrder_type())) {
			goodsorder = goodsorderService.getAirlineGoodsorderByOrderid(id);
		} else if(ORDER_TYPE_MOVIE.equals(goodsorder.getOrder_type())) {
			goodsorder = goodsorderService.getMovieGoodsorderByOrderid(id);
		} else if(ORDER_TYPE_LOTTERY.equals(goodsorder.getOrder_type())) {
			goodsorder = goodsorderService.getLotteryGoodsorderByOrderid(id);
		}
		//根据地区ID串获取地区名称串
//		String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(goodsorder.getArea_attr());
//		String order_state_name = this.commparaService.getParakeyByParacode("order_state", goodsorder.getOrder_state());
		//订单历史记录，（订单跟踪）
		List orderhistoryList = goodsorderService.getOrderhistoryListByOrderid(id);
		model.addAttribute("order", goodsorder);
		model.addAttribute("orderhis",orderhistoryList);
//		model.addAttribute("area_name_str", area_name_str);
//		model.addAttribute("order_state_name", order_state_name);
		return "user/order/order";
	}
	
	//会员中心查看订单列表
	@RequestMapping(value="user/orderlist",method={RequestMethod.POST, RequestMethod.GET})
	public String userOrder(GoodsorderQueryForm goodsorderQueryForm,ModelMap model,HttpServletResponse response){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(user_id);
		//显示未删除的订单
		goodsorderQueryForm.setIs_del("0");
		PageResult pageResult = goodsorderService.getGoodsOrderListByPage(goodsorderQueryForm);
		List result = pageResult.getList();
		if(result != null) {
			for(Map<String, Object> bean : (List<Map<String, Object>>) result) {
				//把订单商品串转换成Order_goods_desc对象,并处理图片路径
				String order_goods_desc = (String) bean.get("order_goods_desc");
				
				if(order_goods_desc != null) {
					//订单商品
					List<Order_goods_desc> ordergoods = new ArrayList<Order_goods_desc>();
					
					JSONArray jsonArray = JSONArray.fromObject(order_goods_desc);
					for(int i=0; i<jsonArray.size(); i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						Order_goods_desc ogd = (Order_goods_desc) JSONObject.toBean(jsonObject, Order_goods_desc.class);
						ogd.setImg(ImgPathUitls.filterImagePath_spec(ogd.getImg(), 70, 70));
						ordergoods.add(ogd);
					}
					bean.put("ordergoods", ordergoods);
				}
				String order_id = (String) bean.get("order_id");
				//订单列表中评价完 显示查看评价
				List orderdetailList = orderdetailService.getOrderdetailList(order_id);
				
				Map param = new HashMap();
				param.put("order_id", order_id);
				List goodsevallist = goodsevalService.getdetailByGoodsidAndOrderid(param);
				if(orderdetailList.size() == goodsevallist.size()){
					bean.put("haseval", 1);
				}else{
					bean.put("haseval", 0);
				}
				
			}
		}
//		if(result != null) {
//			for(Map<String, Object> bean : (List<Map<String, Object>>) result) {
//				String order_goods_desc = (String) bean.get("order_goods_desc");
//				if(order_goods_desc != null) {
//					List<Map<String, Object>> beans = this.parseJSON2List(order_goods_desc);
//					ImgPathUitls.filterImagePath(beans);
//					bean.put("ordergoods", beans);
//				}
//			}
//		}
		pageOper(model, pageResult);
		
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		//状态搜索下拉框
		Map _orderStateMap = this.commparaService.getSelectMapByParacode(ORDER_STATE);
		
		Map orderStateMap = new LinkedHashMap();
		orderStateMap.put("", "全部状态");
		orderStateMap.putAll(_orderStateMap);
		
		if(goodsorderQueryForm.getSear_days() == null){
			goodsorderQueryForm.setSear_days(DEFAULT_SEARCH_DAYS);
		}
		
		if(goodsorderQueryForm.getOrder_state() == null){
			goodsorderQueryForm.setOrder_state(DEFAULT_ORDER_STATE);
		}
		
		model.put("seardaysMap", seardaysMap);
		model.put("orderStateMap", orderStateMap);
		
		//猜你喜欢
//		List goodsList = goodsService.getGoodsOfUserLikeWeGuess();
//		model.addAttribute("userLikeGoods", goodsList);
		
		return "user/order/index";
	}
	
	//会员中心查看订单列表
	@RequestMapping(value="user/deletelist",method={RequestMethod.POST, RequestMethod.GET})
	public String deletelist(GoodsorderQueryForm goodsorderQueryForm,ModelMap model,String is_del){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(user_id);
		// 显示已删除的订单
		goodsorderQueryForm.setIs_del("1");
		PageResult pageResult = goodsorderService.getGoodsOrderListByPage(goodsorderQueryForm);
		List result = pageResult.getList();
		if(result != null) {
			for(Map<String, Object> bean : (List<Map<String, Object>>) result) {
				//把订单商品串转换成Order_goods_desc对象,并处理图片路径
				String order_goods_desc = (String) bean.get("order_goods_desc");
				
				if(order_goods_desc != null) {
					//订单商品
					List<Order_goods_desc> ordergoods = new ArrayList<Order_goods_desc>();
					
					JSONArray jsonArray = JSONArray.fromObject(order_goods_desc);
					for(int i=0; i<jsonArray.size(); i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						Order_goods_desc ogd = (Order_goods_desc) JSONObject.toBean(jsonObject, Order_goods_desc.class);
						ogd.setImg(ImgPathUitls.filterImagePath(ogd.getImg()));
						ordergoods.add(ogd);
					}
					bean.put("ordergoods", ordergoods);
				}
			}
		}
//		if(result != null) {
//			for(Map<String, Object> bean : (List<Map<String, Object>>) result) {
//				String order_goods_desc = (String) bean.get("order_goods_desc");
//				if(order_goods_desc != null) {
//					List<Map<String, Object>> beans = this.parseJSON2List(order_goods_desc);
//					ImgPathUitls.filterImagePath(beans);
//					bean.put("ordergoods", beans);
//				}
//			}
//		}
		
		pageOper(model, pageResult);
		
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		//状态搜索下拉框
		Map _orderStateMap = this.commparaService.getSelectMapByParacode(ORDER_STATE);
		
		Map orderStateMap = new LinkedHashMap();
		orderStateMap.put("", "全部状态");
		orderStateMap.putAll(_orderStateMap);
		
		if(goodsorderQueryForm.getSear_days() == null){
			goodsorderQueryForm.setSear_days(DEFAULT_SEARCH_DAYS);
		}
		
		if(goodsorderQueryForm.getOrder_state() == null){
			goodsorderQueryForm.setOrder_state(DEFAULT_ORDER_STATE);
		}
		
		model.put("seardaysMap", seardaysMap);
		model.put("orderStateMap", orderStateMap);
		
		//猜你喜欢
//		List goodsList = goodsService.getGoodsOfUserLikeWeGuess();
//		model.addAttribute("userLikeGoods", goodsList);
		
		return "user/order/index";
	}
	
	//获取通过hashcode得到的购物车list
	public List getCartList(String hashcode){
//		List cartList = (List)SessionUtil.get(getRequest(), ITEM_CART_LIST);
//		if(cartList!=null && cartList.size()>0){
//			return cartList;
//		}
		List cartList = null;
		List<Integer> cartItemHashCodeList = new ArrayList<Integer>();
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		if(!StringUtils.isBlank(hashcode)){
			String hashStr[] = hashcode.split(",");
			for(int i=0;i<hashStr.length;i++){
				if(!hashStr[i].equals("")){
					cartItemHashCodeList.add(Integer.parseInt(hashStr[i]));
				}
			}
			cartList = userCartService.getCartItem(user_id, cartItemHashCodeList);
			SessionUtil.put(getRequest(), HASH_CART_LIST, cartItemHashCodeList);
			SessionUtil.put(getRequest(), ITEM_CART_LIST, cartList);
		}
		return cartList;
	}
	
	@RequestMapping(value="order/order",method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String order(Invoice invoice,String hashcode,HttpServletRequest request,ModelMap model,HttpServletResponse response){
		List cartList = getCartList(hashcode);
		//判断购物车是否为空
		if(cartList == null || cartList.size() == 0){
			return "redirect:"+basePath()+"cartlist.action";
		}
		
		//选择设置收货地址
		setAddrMap(request,model);
		
		//设置发票信息
		setDefaultInvoiceMap(request,model);
		
		//计算商品总金额
		setGoodsTotalPrice(cartList,request,model);
		//收货地址
		address(request,model);
		
		addradd(model);
		
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		UserCart userCart = userCartService.getUserCart(user_id);
		BigDecimal total_price = goodsorderService.calcCartTotalPrice(cartList);
		//计算商品总价
		model.addAttribute("goodsTotalPrice", total_price);
		model.addAttribute("cartlist", cartList);
		model.addAttribute("cartsize", userCart.size());
		
//		lookCar(response,model);
		return "order/order";
	}
	
	@RequestMapping(value="order/confirm_order",method=RequestMethod.GET)
	public String confirmOrder(CartItem cartItem, HttpServletRequest request,ModelMap model,HttpServletResponse response){
		
		Integer goods_id = cartItem.getGoods().getGoods_id();
		if(goods_id == null) {
			return "404";
		}
		Goods goods = goodsService.getByPk(String.valueOf(goods_id));
		if(goods == null) {
			return "404";
		}
		goods.setImg_path(ImgPathUitls.filterImagePath(goods.getImg_path()));
		cartItem.setGoods(goods);
		
		//把立即购买放进session
		SessionUtil.put(request, BUY_INSTANTLY_KEY, cartItem);
		//购物车
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem);
		
		
		//选择设置收货地址
		setAddrMap(request,model);
		
		//设置发票信息
		setDefaultInvoiceMap(request,model);
		
		//收货地址
		address(request,model);
		
		addradd(model);
		
		BigDecimal total_price = goodsorderService.calcCartTotalPrice(cartList);
		//计算商品总价
		model.addAttribute("goodsTotalPrice", total_price);
		model.addAttribute("cartsize", cartList.size());
		
		return "order/confirm_order";
	}
	
	@RequestMapping(value="order/confirm_order_ok",method=RequestMethod.GET)
	public String confirmOrderOk(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		Object o = SessionUtil.get(request, BUY_INSTANTLY_KEY);
		if(o == null) {
			return "404";
		}
		CartItem cartItem = (CartItem) o;
		Integer goods_id = cartItem.getGoods().getGoods_id();
		if(goods_id == null || cartItem.getAmount() < 1) {
			return "404";
		}
		Goods goods = goodsService.getByPk(String.valueOf(goods_id));
		if(goods == null) {
			return "404";
		}
		goods.setImg_path(ImgPathUitls.filterImagePath(goods.getImg_path()));
		cartItem.setGoods(goods);
		//购物车
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem);
		
		BigDecimal total_price = goodsorderService.calcCartTotalPrice(cartList);
		
		//判断收货地址是否为空
		if(SessionUtil.get(request, ORDER_ADDRESS_MAP) == null){
			return "redirect:"+basePath()+"cartlist.action";
		}
		
		//判断发票是否为空
		if(SessionUtil.get(request, ORDER_INVOICE_MAP) == null){
			return "redirect:"+basePath()+"cartlist.action";
		}
		
		//订单基本信息封装
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		Map orderMap = new HashMap();
		orderMap.put("buy_cust_id", user_id);
		
		//获取地址信息
		Map addrMap = (Map)SessionUtil.get(request, ORDER_ADDRESS_MAP);
		//获取发票信息
		Invoice invoice = (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP);
		
		List orderIdList = goodsorderService.insertGoodsOrder(orderMap, addrMap, invoice, cartList);
		
		//成功后清除从购物车中添加到订单中的商品
		SessionUtil.put(request, BUY_INSTANTLY_KEY, null);
//		userCartService.removeAll(user_id, (List)SessionUtil.get(request, HASH_CART_LIST));
		return "redirect:"+basePath()+"order/ordersuccess.action?goodsTotalPrice="+total_price+"&order_id="+orderIdList.get(0);
	}
	
	//查看购物车商品
	public void lookCar(HttpServletResponse response,ModelMap model){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		UserCart userCart = userCartService.getUserCart(user_id);
		List cartList = userCart.getCartItems();
		BigDecimal total_price = goodsorderService.calcCartTotalPrice(cartList);
//		Float gtp = goodsorderService.getCartTotalPrice(cartList);
		//计算商品总价
		model.addAttribute("goodsTotalPrice", total_price);
		model.addAttribute("cartlist", cartList);
		model.addAttribute("cartsize", userCart.size());
		
	}
	
	
	@RequestMapping(value="order/orderok",method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String orderok(HttpServletRequest request,ModelMap model,String goodsTotalPrice){
		
		//判断购物车是否为空
		List cartList = (List)SessionUtil.get(getRequest(), ITEM_CART_LIST);
		
		if(cartList == null || cartList.size() == 0){
			return "redirect:"+basePath()+"cartlist.action";
		}
		
		//判断收货地址是否为空
		if(SessionUtil.get(request, ORDER_ADDRESS_MAP) == null){
			return "redirect:"+basePath()+"cartlist.action";
		}
		
		//判断发票是否为空
		if(SessionUtil.get(request, ORDER_INVOICE_MAP) == null){
			return "redirect:"+basePath()+"cartlist.action";
		}
		
		//订单基本信息封装
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		Map orderMap = new HashMap();
		orderMap.put("buy_cust_id", user_id);
		
		//获取地址信息
		Map addrMap = (Map)SessionUtil.get(request, ORDER_ADDRESS_MAP);
		//获取发票信息
		Invoice invoice = (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP);
		
		List orderIdList = goodsorderService.insertGoodsOrder(orderMap, addrMap, invoice, cartList);
		
		//成功后清除从购物车中添加到订单中的商品
		SessionUtil.put(request,ITEM_CART_LIST,null);
		userCartService.removeAll(user_id, (List)SessionUtil.get(request, HASH_CART_LIST));
		Goodsorder goodsorder = goodsorderService.getByPk((String) (orderIdList.get(0)));
		return "redirect:"+basePath()+"order/ordersuccess.action?goodsTotalPrice="+goodsorder.getTatal_amount()+"&order_id="+orderIdList.get(0);
	}
	
	//跳转到成功页面
	@RequestMapping(value="order/ordersuccess",method=RequestMethod.GET)
	public String ordersuccess(String goodsTotalPrice,ModelMap model,String order_id){
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setOrder_id(order_id);
		paymentOrder.setTotal_price(goodsTotalPrice);
		paymentOrder.setPayment_way(PAYMENT_WAY_BANK);//银联支付
		model.addAttribute("paymentOrder", paymentOrder);
		return "order/orderok";
	}
	
	public void setGoodsTotalPrice(List cartList,HttpServletRequest request,ModelMap model){
		BigDecimal total_price = goodsorderService.calcCartTotalPrice(cartList);
//		Float gtp = goodsorderService.getCartTotalPrice(cartList);
		model.addAttribute("goodsTotalPrice", total_price);
		int i=0;
	}
	
	@RequestMapping(value="order/invoice",method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String invoice(HttpServletRequest request,ModelMap model){
		Invoice invoice = new Invoice();
		if(SessionUtil.get(request, ORDER_INVOICE_MAP) != null){
			invoice = (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP);
		}
		model.addAttribute("invoice", invoice);
		return "order/invoice";
	}
	
	public void address(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		List addrlist = buyeraddrService.getBuyeraddrByUserId(user_id);
		Map addressMap = new HashMap();
		for(int i =0;i<addrlist.size();i++){
			Map map =(Map) addrlist.get(i);
			if(map.get("is_default").equals("1")){
				addressMap=map;
				break;
			}
		}
		SessionUtil.put(request, ORDER_ADDRESS_MAP, addressMap);
		model.addAttribute("addrlist", addrlist);
	}
	
	//添加
	public void addradd(ModelMap model){
		model.addAttribute("buyeraddr", new Buyeraddr());
	}
	
	@RequestMapping(value="order/addrinsert",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addrinsert(Buyeraddr buyeraddr,Errors errors,ModelMap model){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		return String.valueOf(buyeraddrService.insertBuyeraddr(buyeraddr));
	}
	
	//删除收货地址
	@RequestMapping(value="order/deleteaddress",method=RequestMethod.GET)
	@ResponseBody
	public String cartedit(String address_id,HttpServletResponse response){
		buyeraddrService.deleteBuyeraddr(address_id);
		return String.valueOf("success");
	}
	
	//保存发票信息
	@RequestMapping(value="order/setInvoice",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String setInvoiceMap(Invoice invoice,HttpServletRequest request,ModelMap model){
		
		String invoice_type = request.getParameter("invoice_type");
		String ident_number = request.getParameter("ident_number");
		String regis_address = request.getParameter("regis_address");
		String regis_tel = request.getParameter("regis_tel");
		String open_bank =  request.getParameter("open_bank");
		String bank_account = request.getParameter("bank_account");
		String invoice_top = request.getParameter("invoice_top");
		String company_name = request.getParameter("company_name");
		String invoice_content = request.getParameter("invoice_content");
		
		invoice.setBank_account(bank_account);
		invoice.setCompany_name(company_name);
		invoice.setIdent_number(ident_number);
		invoice.setInvoice_content(invoice_content);
		invoice.setInvoice_top(invoice_top);
		invoice.setInvoice_type(invoice_type);
		invoice.setOpen_bank(open_bank);
		invoice.setRegis_address(regis_address);
		invoice.setRegis_tel(regis_tel);
		
		if(invoice != null && invoice.getInvoice_type() != null){
			SessionUtil.put(request, ORDER_INVOICE_MAP, invoice);
		}else{
			if(SessionUtil.get(request, ORDER_INVOICE_MAP) == null){
				//invoice_type：2 默认不要发票
				Invoice _invoice = new Invoice();
				_invoice.setInvoice_type("2");
				_invoice.setInvoice_top("个人");
				_invoice.setInvoice_content("明细");
				SessionUtil.put(request, ORDER_INVOICE_MAP, _invoice);
			}
		}
		return String.valueOf("success");
	}
	//设置默认发票
	public void setDefaultInvoiceMap(HttpServletRequest request,ModelMap model){
		if(SessionUtil.get(request, ORDER_INVOICE_MAP) == null){
			//invoice_type：2 默认不要发票
			Invoice _invoice = new Invoice();
			_invoice.setInvoice_type("2");
			_invoice.setInvoice_top("个人");
			_invoice.setInvoice_content("明细");
			SessionUtil.put(request, ORDER_INVOICE_MAP, _invoice);
		}
		model.addAttribute("invoice", (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP));
	}
	
	//用户选择收货地址
	@RequestMapping(value="order/getUserAddress-{id}",method=RequestMethod.GET)
	@ResponseBody
	public String getUserAddress(@PathVariable(value = "id")String id,HttpServletRequest request){
		Buyeraddr buyeraddr = buyeraddrService.getByPk(id);
		Map map = buyeraddr2Map(buyeraddr);
		SessionUtil.put(request, ORDER_ADDRESS_MAP, map);
		return String.valueOf("success");
	}
	//选择设置收货地址
	//第一次从选择中构造数据，构造完放到session中
	//后面如果不选择直接从session中获取
	public void setAddrMap(HttpServletRequest request,ModelMap model){
		String addr_id = request.getParameter("addr_id");
		if(addr_id != null && !addr_id.equals("")){
			Buyeraddr buyeraddr = buyeraddrService.getByPk(addr_id);
			Map map = buyeraddr2Map(buyeraddr);
			model.addAttribute("addr", map);
			SessionUtil.put(request, ORDER_ADDRESS_MAP, map);
		}else{
			//直接从session里取
			if(SessionUtil.get(request, ORDER_ADDRESS_MAP) != null){
				model.addAttribute("addr", (Map)SessionUtil.get(request, ORDER_ADDRESS_MAP));
			}else{
				//第一次进来取数据库默认地址
				String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
				Buyeraddr buyeraddr = buyeraddrService.getDefaultBuyeraddr(user_id);
				Map map = buyeraddr2Map(buyeraddr);
				model.addAttribute("addr", map);
				SessionUtil.put(request, ORDER_ADDRESS_MAP, map);
			}
		}
	}
	
	//对象转MAP
	public Map buyeraddr2Map(Buyeraddr buyeraddr){
		if(buyeraddr == null){
			return new HashMap();
		}
		String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(buyeraddr.getArea_attr());
		Map<String,String> map = new HashMap<String,String>();
		map.put("area_attr", buyeraddr.getArea_attr());
		map.put("area_name_str", area_name_str);
		map.put("cust_name", buyeraddr.getCust_name());
		map.put("cell_phone", buyeraddr.getCell_phone());
		map.put("addr_id", String.valueOf(buyeraddr.getAddr_id()));
		map.put("address", buyeraddr.getAddress());
		return map;
	}
	
	//未付款订单
	@RequestMapping(value="user/nonPayment",method={RequestMethod.POST, RequestMethod.GET})
	public String nonPayment(GoodsorderQueryForm goodsorderQueryForm,ModelMap model){
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		//状态搜索下拉框
		Map _orderStateMap = this.commparaService.getSelectMapByParacode(ORDER_STATE);
		Map orderStateMap = new LinkedHashMap();
		orderStateMap.put("", "全部状态");
		orderStateMap.putAll(_orderStateMap);
		
		model.put("seardaysMap", seardaysMap);
		model.put("orderStateMap", orderStateMap);
		
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(user_id);
		goodsorderQueryForm.setOrder_state("0");
		PageResult pageResult = goodsorderService.getGoodsOrderListByPage(goodsorderQueryForm);
		List result = pageResult.getList();
		if(result != null) {
			for(Map<String, Object> bean : (List<Map<String, Object>>) result) {
				//把订单商品串转换成Order_goods_desc对象,并处理图片路径
				String order_goods_desc = (String) bean.get("order_goods_desc");
				
				if(order_goods_desc != null) {
					//订单商品
					List<Order_goods_desc> ordergoods = new ArrayList<Order_goods_desc>();
					
					JSONArray jsonArray = JSONArray.fromObject(order_goods_desc);
					for(int i=0; i<jsonArray.size(); i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						Order_goods_desc ogd = (Order_goods_desc) JSONObject.toBean(jsonObject, Order_goods_desc.class);
						ogd.setImg(ImgPathUitls.filterImagePath_spec(ogd.getImg(),70,70));
						ordergoods.add(ogd);
					}
					bean.put("ordergoods", ordergoods);
				}
			}
		}
		pageOper(model, pageResult);
		return "user/order/index";
	}
	
	//待确认收货订单
	@RequestMapping(value="user/notToConfirmReceipt",method={RequestMethod.POST, RequestMethod.GET})
	public String notToConfirmReceipt(GoodsorderQueryForm goodsorderQueryForm,ModelMap model){
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		//状态搜索下拉框
		Map _orderStateMap = this.commparaService.getSelectMapByParacode(ORDER_STATE);
		Map orderStateMap = new LinkedHashMap();
		orderStateMap.put("", "全部状态");
		orderStateMap.putAll(_orderStateMap);
		
		model.put("seardaysMap", seardaysMap);
		model.put("orderStateMap", orderStateMap);
		
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(user_id);
		goodsorderQueryForm.setOrder_state("2");
		PageResult pageResult = goodsorderService.getGoodsOrderListByPage(goodsorderQueryForm);
		List result = pageResult.getList();
		if(result != null) {
			for(Map<String, Object> bean : (List<Map<String, Object>>) result) {
				//把订单商品串转换成Order_goods_desc对象,并处理图片路径
				String order_goods_desc = (String) bean.get("order_goods_desc");
				
				if(order_goods_desc != null) {
					//订单商品
					List<Order_goods_desc> ordergoods = new ArrayList<Order_goods_desc>();
					
					JSONArray jsonArray = JSONArray.fromObject(order_goods_desc);
					for(int i=0; i<jsonArray.size(); i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						Order_goods_desc ogd = (Order_goods_desc) JSONObject.toBean(jsonObject, Order_goods_desc.class);
						ogd.setImg(ImgPathUitls.filterImagePath_spec(ogd.getImg(),70,70));
						ordergoods.add(ogd);
					}
					bean.put("ordergoods", ordergoods);
				}
			}
		}
		pageOper(model, pageResult);
		return "user/order/index";
	}
	
	//订单--立即支付
	@RequestMapping(value="user/topaymentorder",method=RequestMethod.GET)
	public String toPaymentOrder(@Valid PaymentOrder paymentOrder, Errors errors, RedirectAttributes rAttr, ModelMap model) {
		if (errors.hasErrors()){
			return "order/orderok";
		}
		String order_id = paymentOrder.getOrder_id();
		String payment_way = paymentOrder.getPayment_way();
		if(payment_way == null) {
			paymentOrder.setPayment_way(PAYMENT_WAY_FUNDACCOUNT);
		}
		if(PAYMENT_WAY_FUNDACCOUNT.equals(payment_way)) {//资金账户
			String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
			String respCode = goodsorderService.payGoodsorderByFundaccount(user_id, order_id);
			if("0201".equals(respCode) || "0202".equals(respCode)) {//资金账户状态不可用
				errors.rejectValue("respInfo", null, "资金账户状态不可用,请选择其他支付方式");
			} else if("0203".equals(respCode)) {//可用余额不足
				errors.rejectValue("respInfo", null, "资金账户可用余额不足,请选择其他支付方式");
			}
			if (errors.hasErrors()){
				return "order/orderok";
			}
			model.put("order_id", order_id);
			if("0100".equals(respCode)) {//支付成功
				return "order/order-payment-success";
			}
			return "order/order-status-exception";
		} else if(PAYMENT_WAY_BANK.equals(payment_way)) {//银联
			return "redirect:"+basePath()+"order/payment.action?order_id=" + order_id;
		}
		return "order/order-status-exception";
	}
	
	/**
	 * json串转list<map>
	 * @param jsonStr
	 * @return
	 * @author 陈显革
	 */
	private static List<Map<String, Object>> parseJSON2List(String jsonStr){
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
        	JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
	}
	
	/**
	 * json串转map
	 * @param jsonStr
	 * @return
	 * @author 陈显革
	 */
	private static Map<String, Object> parseJSON2Map(String jsonStr){
		Map<String, Object> map = new HashMap<String, Object>();
		//最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
        	Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
            	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                	JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
            	map.put(k.toString(), v);
            }
        }
        return map;
	}
	
}
