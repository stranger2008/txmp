package com.xingfugo.web.client.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.CartItem;
import com.xingfugo.business.module.UserCart;
import com.xingfugo.business.service.CartService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.UserCartService;
import com.xingfugo.business.service.VisitorCartService;
import com.xingfugo.web.client.common.CartCookieUtil;
import com.xingfugo.web.client.common.SessionUtil;

//购物车
@Controller
public class ShoppingCartController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(ShoppingCartController.class.getSimpleName());
	
	private final static String ITEM_CART_LIST = "item_cart_list";
	
	private final static String HASH_CART_LIST = "hash_cart_list";
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private VisitorCartService visitorCartService;
	
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private GoodsService goodsService;
	
	private CartService cartService;
	
	//登录后用userCartService
	//游客用visitorCartService
	public CartService getCartService(){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		if(user_id.equals("")){
			cartService = visitorCartService;
		}else{
			cartService = userCartService;
		}
		return cartService;
	}
	
	//得到购物车的key，游客的或登录会员的
	public String getCartKey(HttpServletResponse response){
		String key = "";
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		if(user_id.equals("")){
			key = CartCookieUtil.getVisitorCartName(visitorCartService.redisExpiredSeconds(),getRequest(),response);
		}else{
			key = user_id;
		}
		return key;
	}
	
	
	//添加购物车商品
	@RequestMapping(value="cartadd",method=RequestMethod.GET)
	//@ResponseBody
	public String cartadd(CartItem cartItem,HttpServletResponse response){
		addCartItem(cartItem,response);
		return "redirect:"+basePath()+"cartlist.action";
	}
	
	//商品详细页直接购买
	@RequestMapping(value="directBuy",method=RequestMethod.GET)
	//@ResponseBody
	public String directBuy(CartItem cartItem,HttpServletResponse response){
		addCartItem(cartItem,response);
		return "redirect:"+basePath()+"order/order.action";
	}
	
	public void addCartItem(CartItem cartItem,HttpServletResponse response){
		getCartService().add(getCartKey(response), cartItem.getGoods().getGoods_id(), cartItem.getGoods_param(), cartItem.getAmount());
	}
	
	//清空购物车
	@RequestMapping(value="cartclear",method=RequestMethod.GET)
	public String cartclear(HttpServletResponse response){
		getCartService().empty(getCartKey(response));		
		return "redirect:"+basePath()+"cartlist.action";
	}
	
	//删除商品
	//hashcode为 商品ID+商品属性的 hashcode值
	@RequestMapping(value="cartdel",method=RequestMethod.GET)
	public String cartdel(String hashcode,HttpServletResponse response){
		List<Integer> cartItemHashCodeList = (List<Integer>)SessionUtil.get(getRequest(), HASH_CART_LIST);
		
		if(StringUtils.isNotBlank(hashcode)){
			 String[] hs=hashcode.split(",");
			for(int i=0;i<hs.length;i++){
				getCartService().remove(getCartKey(response), Integer.parseInt(hs[i]));
				if(cartItemHashCodeList != null) {
					for(Integer cihc : cartItemHashCodeList) {
						if(cihc.intValue() == Integer.parseInt(hs[i])) {
							cartItemHashCodeList.remove(cihc);
							break;
						}
					}
				}
			}
		}
		//修改session中购物车
		SessionUtil.put(getRequest(), HASH_CART_LIST, cartItemHashCodeList);
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		SessionUtil.put(getRequest(), ITEM_CART_LIST, userCartService.getCartItem(user_id, cartItemHashCodeList));
		
		return "redirect:"+basePath()+"cartlist.action";
	}
	
	//修改商品数量
	@RequestMapping(value="cartedit",method=RequestMethod.GET)
	@ResponseBody
	public String cartedit(int hashcode,int num,HttpServletResponse response){
		getCartService().modify(getCartKey(response), hashcode, num);
		//回显商品总价
		UserCart userCart = getCartService().getUserCart(getCartKey(response));
		List cartList = userCart.getCartItems();
		BigDecimal total_price = goodsorderService.calcCartTotalPrice(cartList);
//		Float gtp = goodsorderService.getCartTotalPrice(cartList);
		int goodsNum = userCart.goodsNum();
		String result = "{tatalPrice:'"+total_price.toString()+"',totalNum:"+goodsNum+"}";
		return result;
	}

	//购物车列表数量
	@RequestMapping(value="cartlistsize",method=RequestMethod.GET)
	@ResponseBody
	public String cartlistsize(ModelMap model,HttpServletResponse response){
		UserCart userCart = getCartService().getUserCart(getCartKey(response));
		List cartList = userCart.getCartItems();
		String cartsize = String.valueOf(userCart.goodsNum());
		return cartsize;
	}
	
	//查看购物车列表
	@RequestMapping(value="cartlist",method=RequestMethod.GET)
	public String catlist(ModelMap model,HttpServletResponse response){
		UserCart userCart = getCartService().getUserCart(getCartKey(response));
		List cartList = userCart.getCartItems();
//		Float gtp = goodsorderService.getCartTotalPrice(cartList);
		
		BigDecimal total_price = goodsorderService.calcCartTotalPrice(cartList);
		//计算商品总价
		model.addAttribute("goodsTotalPrice", total_price);
		model.addAttribute("cartlist", cartList);
		model.addAttribute("cartsize", userCart.goodsNum());
		
		//猜你喜欢
		List guessList = goodsService.getGoodsOfUserLikeWeGuess();
		model.addAttribute("guessList", guessList);
		return "cartlist";
	}
	
}
