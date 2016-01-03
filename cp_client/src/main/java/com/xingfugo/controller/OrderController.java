package com.xingfugo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.xingfugo.business.module.Buyeraddr;
import com.xingfugo.business.module.CartItem;
import com.xingfugo.business.module.Goods;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Invoice;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.service.AreaService;
import com.xingfugo.business.service.BuyeraddrService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.UserCartService;
import com.xingfugo.business.common.Constants;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.util.ImgPathUitls;

//订单管理
@Controller
public class OrderController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(OrderController.class.getSimpleName());
	
	private final static String ORDER_ADDRESS_MAP = "order_address_map";
	
	private final static String ORDER_INVOICE_MAP = "order_invoice_map";
	
	private final static String ITEM_CART_LIST = "item_cart_list";
	
	private final static String HASH_CART_LIST = "hash_cart_list";
	
	//订单来源 cp：触屏版
	private final static String ORDER_SRC_CP = "cp";
	
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
	
	//会员中心查看订单详细
	@RequestMapping(value="user/order-{id}",method=RequestMethod.GET)
	public String orderdetail(@PathVariable(value = "id") String id,ModelMap model){
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(id);
		//根据地区ID串获取地区名称串
		String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(goodsorder.getArea_attr());
		String order_state_name = this.commparaService.getParakeyByParacode("order_state", goodsorder.getOrder_state());
		model.addAttribute("order", goodsorder);
		model.addAttribute("area_name_str", area_name_str);
		model.addAttribute("order_state_name", order_state_name);
		return "user/order/order";
	}
	
	//会员中心查看订单列表
	@RequestMapping(value="user/orderlist",method={RequestMethod.POST, RequestMethod.GET})
	public String userOrder(GoodsorderQueryForm goodsorderQueryForm,ModelMap model,String orderType,String state){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(user_id);
		goodsorderQueryForm.setOrder_type(orderType);
		if(state!=null && "0".equals(state))
			goodsorderQueryForm.setOrder_state("0"); //待付款订单
		pageOper(model,goodsorderService.getGoodsOrderListByPage(goodsorderQueryForm));
		model.addAttribute("orderType", orderType);	//在前台根据orderType进行相应显示
		return "user/order/index";
	}
	

	//会员中心订单分类
	@RequestMapping(value="user/userOrderType",method={RequestMethod.POST, RequestMethod.GET})
	public String userOrderType(){
		return "user/order/orderAll";
	}
	
	//会员中心待付款订单
	@RequestMapping(value="user/userOrderPay",method={RequestMethod.POST, RequestMethod.GET})
	public String userOrderPay(){
		return "user/order/orderPay";
	}
	
	/**
	 * 机票订单详情
	 */
	@RequestMapping(value="user/airLineOrder",method={ RequestMethod.GET})
	public String airLineOrder(GoodsorderQueryForm goodsorderQueryForm,ModelMap model, String state){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(user_id);
		if(state!=null && "0".equals(state))
			goodsorderQueryForm.setOrder_state("0"); //待付款订单
		pageOper(model,goodsorderService.getAirLineOrderListByPage(goodsorderQueryForm));
		return "user/order/airline/airlineOrderDetail";
	}
	
	/**
	 * 订单详细
	 */
	@RequestMapping(value="user/airLineOrderInfo",method={ RequestMethod.GET})
	public String airLineOrderInfo(ModelMap model,GoodsorderQueryForm gf){
		model.addAttribute("orderInfo", goodsorderService.getOrderInfoByOrderNo(gf));
		model.addAttribute("gf", gf);
		return "user/order/airline/airlineOrderDetailInfo";
	}
	
	/**
	 * 会员中心电影票订单列表
	 */
	@RequestMapping(value="user/movieOrder",method={ RequestMethod.GET})
	public String movieOrder(GoodsorderQueryForm goodsorderQueryForm,ModelMap model,String state){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(user_id);
		if(state!=null && "0".equals(state))
			goodsorderQueryForm.setOrder_state("0"); //待付款订单
		pageOper(model,goodsorderService.movieOrderListByPage(goodsorderQueryForm));
		return "user/order/movie/movieOrderList";
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
					cartItemHashCodeList.add(Integer.parseInt(hashStr[i].trim()));
				}
			}
			cartList = userCartService.getCartItem(user_id, cartItemHashCodeList);
			SessionUtil.put(getRequest(), HASH_CART_LIST, cartItemHashCodeList);
			SessionUtil.put(getRequest(), ITEM_CART_LIST, cartList);
		}
		return cartList;
	}
	
	@RequestMapping(value="order/order",method=RequestMethod.GET)
	public String order(Invoice invoice,String hashcode,HttpServletRequest request,ModelMap model){
		List cartList = getCartList(hashcode);
		//判断购物车是否为空
		if(cartList == null || cartList.size() == 0){
			return "redirect:"+basePath()+"cartlist.action";
		}
		//选择设置收货地址
		setAddrMap(request,model);
		//设置发票信息
		setInvoiceMap(invoice,request,model);
		//计算商品总金额
		setGoodsTotalPrice(cartList,request,model);
		
		return "order/order";
	}
	
	@RequestMapping(value="order/orderok",method=RequestMethod.GET)
	public String orderok(HttpServletRequest request,ModelMap model){
		
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
		orderMap.put("order_src", ORDER_SRC_CP);
		
		//获取地址信息
		Map addrMap = (Map)SessionUtil.get(request, ORDER_ADDRESS_MAP);
		//获取发票信息
		Invoice invoice = (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP);
		
		
		
		List orderIdList = goodsorderService.insertGoodsOrder(orderMap, addrMap, invoice, cartList);
		
		//成功后清除从购物车中添加到订单中的商品
		SessionUtil.put(request,ITEM_CART_LIST,null);
		userCartService.removeAll(user_id, (List)SessionUtil.get(request, HASH_CART_LIST));
		
		SessionUtil.put(getRequest(), "orderIdList", orderIdList);
		
		return "redirect:"+basePath()+"order/ordersuccess.action";
	}
	
	//立即购买
	@RequestMapping(value="order/buy_instantly",method=RequestMethod.GET)
	public String buyInstantly(CartItem cartItem) {
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
		SessionUtil.put(this.getRequest(), BUY_INSTANTLY_KEY, cartItem);
		
		return "redirect:"+basePath()+"order/confirm_order.action";
	}
	
	@RequestMapping(value="order/confirm_order",method=RequestMethod.GET)
	public String confirmOrder(Invoice invoice, HttpServletRequest request,ModelMap model,HttpServletResponse response){
		Object o = SessionUtil.get(request, BUY_INSTANTLY_KEY);
		if(o == null) {
			return "404";
		}
		CartItem cartItem = (CartItem) o;
		
		//购物车
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem);
		
		
		//选择设置收货地址
		setAddrMap(request,model);
		//设置发票信息
		setInvoiceMap(invoice,request,model);
		//计算商品总金额
		setGoodsTotalPrice(cartList,request,model);
		
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
		return "redirect:"+basePath()+"order/ordersuccess.action";
	}
	
	//跳转到成功页面
	@RequestMapping(value="order/ordersuccess",method=RequestMethod.GET)
	public String ordersuccess(){
		return "order/orderok";
	}
	
	public void setGoodsTotalPrice(List cartList,HttpServletRequest request,ModelMap model){
		BigDecimal gtp = goodsorderService.calcCartTotalPrice(cartList);
		model.addAttribute("goodsTotalPrice", gtp);
	}
	
	@RequestMapping(value="order/invoice",method=RequestMethod.GET)
	public String invoice(HttpServletRequest request,ModelMap model){
		Invoice invoice = new Invoice();
		if(SessionUtil.get(request, ORDER_INVOICE_MAP) != null){
			invoice = (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP);
		}
		model.addAttribute("invoice", invoice);
		return "order/invoice";
	}

	//立即购买 发票信息
	@RequestMapping(value="order/confirm_invoice",method=RequestMethod.GET)
	public String confirmInvoice(HttpServletRequest request,ModelMap model){
		Invoice invoice = new Invoice();
		if(SessionUtil.get(request, ORDER_INVOICE_MAP) != null){
			invoice = (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP);
		}
		model.addAttribute("invoice", invoice);
		return "order/confirm_invoice";
	}
	
	@RequestMapping(value="order/confirm_address",method=RequestMethod.GET)
	public String confirmAddress(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		List addrlist = buyeraddrService.getBuyeraddrByUserId(user_id);
		model.addAttribute("addrlist", addrlist);
		return "order/confirm_address";
	}
	
	//添加
	@RequestMapping(value="order/confirm_addradd",method=RequestMethod.GET)
	public String confirmAddradd(ModelMap model){
		model.addAttribute("buyeraddr", new Buyeraddr());
		return "order/confirm_addaddr";
	}
	
	@RequestMapping(value="order/confirm_addrinsert",method=RequestMethod.POST)
	public String confirmAddrinsert(@Valid Buyeraddr buyeraddr,Errors errors,ModelMap model){
		
		if (errors.hasErrors()){
	           return "order/confirm_addaddr";
		}
		
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		buyeraddrService.insertBuyeraddr(buyeraddr);
		
		Map map = buyeraddr2Map(buyeraddr);
		model.addAttribute("addr", map);
		SessionUtil.put(getRequest(), ORDER_ADDRESS_MAP, map);
		
		return "redirect:"+basePath()+"order/confirm_order.action";
	}
	
	@RequestMapping(value="order/address",method=RequestMethod.GET)
	public String address(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		List addrlist = buyeraddrService.getBuyeraddrByUserId(user_id);
		model.addAttribute("addrlist", addrlist);
		return "order/address";
	}
	
	//添加
	@RequestMapping(value="order/addradd",method=RequestMethod.GET)
	public String addradd(ModelMap model){
		model.addAttribute("buyeraddr", new Buyeraddr());
		return "order/addaddr";
	}
	
	@RequestMapping(value="order/addrinsert",method=RequestMethod.POST)
	public String addrinsert(@Valid Buyeraddr buyeraddr,Errors errors,ModelMap model){
		
		if (errors.hasErrors()){
	           return "order/addaddr";
		}
		
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		buyeraddr.setUser_id(user_id);
		buyeraddrService.insertBuyeraddr(buyeraddr);
		
		Map map = buyeraddr2Map(buyeraddr);
		model.addAttribute("addr", map);
		SessionUtil.put(getRequest(), ORDER_ADDRESS_MAP, map);
		
		return "redirect:"+basePath()+"order/order.action";
	}
	
	public void setInvoiceMap(Invoice invoice,HttpServletRequest request,ModelMap model){
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
		model.addAttribute("invoice", (Invoice)SessionUtil.get(request, ORDER_INVOICE_MAP));
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
	//保存发票信息
	@RequestMapping(value="order/setInvoice",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String setInvoice(Invoice invoice,HttpServletRequest request,ModelMap model){
		
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
		return "1";
	}
	@RequestMapping(value="order/orderinc",method=RequestMethod.GET)
	public String orderinc(Invoice invoice,HttpServletRequest request,ModelMap model){
		String hash = SessionUtil.getString(getRequest(),HASH_CART_LIST);
		if(!hash.equals("") ){			
			hash = hash.substring(1, hash.length()-1);
		}
		List cartList = getCartList(hash);
		//判断购物车是否为空
		if(cartList == null || cartList.size() == 0){
			return "redirect:"+basePath()+"cartlist.action";
		}
		//选择设置收货地址
		setAddrMap(request,model);
		//设置发票信息
		setInvoiceMap(invoice,request,model);
		//计算商品总金额
		setGoodsTotalPrice(cartList,request,model);
		
		return "redirect:"+basePath()+"order/order.action?hashcode="+hash;
	}
	
}
