package com.xingfugo.web.client.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Goodseval;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Member;
import com.xingfugo.business.module.Sellereval;
import com.xingfugo.business.service.GoodsevalService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.business.service.OrderdetailService;
import com.xingfugo.business.service.SellerevalService;
import com.xingfugo.util.ImgPathUitls;

/**
 * @function 功能 商品评价Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Sat Oct 11 10:58:25 CST 2014
 */

@Controller
public class GoodsevalController extends BaseController {

	@Autowired
	private GoodsevalService goodsevalService;
	private final static String ORDER_ADDRESS_MAP = "order_address_map";

	private final static String ORDER_INVOICE_MAP = "order_invoice_map";

	private final static String ITEM_CART_LIST = "item_cart_list";

	private final static String HASH_CART_LIST = "hash_cart_list";

	private static final String LIST_DAYS_SEARCH = "list_days_search";
	private static final String ORDER_STATE = "order_state";
	private static final String DEFAULT_ORDER_STATE = "";
	private static final String DEFAULT_SEARCH_DAYS = "30";

	@Autowired
	private GoodsorderService goodsorderService;

	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private SellerevalService sellerevalService;


	// 显示待评价列表
	@RequestMapping(value = "goodseval/index-{id}-{cust_id}")
	public String list(ModelMap model, @PathVariable(value = "id")
	String order_id,@PathVariable(value = "cust_id")
	String cust_id,HttpServletRequest request) throws Exception {
		// 获取商品订单表信息
		List goodslists = goodsorderService.getlistByOrderid(order_id);
		
		Goodsorder goodsorder = goodsorderService.getByPk(order_id);
		
		// 获取商品订单详细表信息
		List<Map<String, Object>> orderdetail = orderdetailService.getGoodsorderevaldetail(order_id);
		ImgPathUitls.filterImagePath_spec(orderdetail,70,70);
		// 根据订单号获取
		setGoodsToGoodsOrder(goodslists, orderdetail);
		
		//商家评价
		List<Map<String, Object>> sellereval = orderdetailService.getsellereval(order_id);
		model.addAttribute("sellereval", sellereval);
		
		//商家信息显示
		Member salelist = memberService.getByPk(cust_id);
		model.addAttribute("salelist", salelist);
		model.addAttribute("cust_id", cust_id);
		model.addAttribute("goodsorder", goodsorder);
		
		// 获取商品id
		model.addAttribute("goodslists", goodslists);
		return "goodseval/index";
	}

	//
	public void setGoodsToGoodsOrder(List goodslists, List orderdetail) {
		if (goodslists == null || orderdetail == null) {
			return;
		}

			Iterator itr = goodslists.iterator();
			while (itr.hasNext()) {
				Map aboutusMap = (Map) itr.next();
				String orderid = "";
				if (aboutusMap.get("order_id") != null) {
					orderid = aboutusMap.get("order_id").toString();
					aboutusMap.put("orderdetailList", getOrderdetailList(
							orderid, orderdetail));
					
					
				}
			}
	}

	//
	public List getOrderdetailList(String orderid, List orderdetail) {
		
		
		Iterator itr = orderdetail.iterator();
		List sonList = new ArrayList();
		List<Goodseval> evalgoods_ids = new ArrayList<Goodseval>();
		while (itr.hasNext()) {
			Goodseval eval = new Goodseval();
			Map aboutusMap = (Map) itr.next();
			String goods_id = "";
			if (aboutusMap.get("goods_id") != null) {
				goods_id = aboutusMap.get("goods_id").toString();
			}
			sonList.add(aboutusMap);
		}
		return sonList;
	}
		
	// 提交保存商品评价
	@RequestMapping(value = "goodseval/saveeval", method = RequestMethod.POST)
	@ResponseBody
	public String saveeval(String id, RedirectAttributes rAttr,
			HttpServletRequest request) throws Exception {
		String goods_id = request.getParameter("goods_id");
		String order_id = request.getParameter("order_id");
		String buy_cust_id = request.getParameter("buy_cust_id");
		String sale_cust_id = request.getParameter("sale_cust_id");
		String g_eval = request.getParameter("g_eval");
		String eval_memt = request.getParameter("eval_memt");
		
		Map param = new HashMap();
		param.put("goods_id", goods_id);
		param.put("order_id", order_id);
		
		if(g_eval.equals("") || eval_memt.equals("10至200字以内") || g_eval == null || eval_memt == null){
			return "0";
		}
		List goodsevallist = goodsevalService.getdetailByGoodsidAndOrderid(param);
		if(goodsevallist.size() > 0){
			return "0";
		}
		//
		Goodseval goodseval = new Goodseval();
		goodseval.setBuy_cust_id(buy_cust_id);
		goodseval.setGoods_id(goods_id);
		goodseval.setOrder_id(order_id);
		goodseval.setSellert_id(sale_cust_id);
		int goods_score = Integer.parseInt(g_eval)*20;
		goodseval.setGoods_score(String.valueOf(goods_score));
		if(g_eval.equals("1")){
			goodseval.setG_eval("0");
		}else if(g_eval.equals("2") || g_eval.equals("3") || g_eval.equals("4")){
			goodseval.setG_eval("1");
		}else if(g_eval.equals("5")){
			goodseval.setG_eval("2");
		}
		goodseval.setG_comment(eval_memt);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		goodseval.setEval_date(df.format(new Date()));
		goodseval.setGood_num("0");
		goodsevalService.insert(goodseval);

		return goods_id;
	}

	// 查看商品评价
	@RequestMapping(value = "goodseval/checkview" , method = RequestMethod.POST , produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String checkview(ModelMap model,HttpServletRequest request) throws Exception {

		String goods_id = request.getParameter("goods_id");
		String order_id = request.getParameter("order_id");
		// 根据goods_id、order_id获取商品评价信息
		Map param = new HashMap();
		param.put("order_id", order_id);
		param.put("goods_id", goods_id);
		List goodsevallist = goodsevalService.getdetailByGoodsidAndOrderid(param);

		JSONArray jsonArray = JSONArray.fromObject(goodsevallist);
		return jsonArray.toString();
	}

	// 删除评价
	@RequestMapping(value = "goodseval/deleteeval", method = RequestMethod.POST)
	@ResponseBody
	public String delete(ModelMap model,HttpServletRequest request) throws Exception {
		String goods_id = request.getParameter("goods_id");
		String order_id = request.getParameter("order_id");
		// 根据goods_id、order_id获取商品评价信息
		Map param = new HashMap();
		param.put("order_id", order_id);
		param.put("goods_id", goods_id);
		goodsevalService.deletedetailByGoodsidAndOrderid(param);

		return "0";
	}
	
	// 保存商家评价
	@RequestMapping(value = "goodseval/salesaveeval", method = RequestMethod.POST)
	@ResponseBody
	public String salesaveeval(String id, RedirectAttributes rAttr,
			HttpServletRequest request) throws Exception {
		String order_id = request.getParameter("order_id");
		String buy_cust_id = request.getParameter("buy_cust_id");
		String sale_cust_id = request.getParameter("cust_id");
		String p_eval = request.getParameter("p_eval");
		String sp_eval = request.getParameter("sp_eval");
		String ser_eval = request.getParameter("ser_eval");
		
		Sellereval hasEvalseller = sellerevalService.getByPk(order_id);
		if(hasEvalseller != null){
			return "0";
		}
		//
		Sellereval sellereval = new Sellereval();
		sellereval.setOrder_id(order_id);
		sellereval.setCust_id(sale_cust_id);
		sellereval.setEval_cust_id(buy_cust_id);
		sellereval.setPackage_score_eval(p_eval);
		sellereval.setSpeed_score_eval(sp_eval);
		sellereval.setService_score_eval(ser_eval);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-MM-ss");
		sellereval.setEval_time(df.format(new Date()));
		sellerevalService.insert(sellereval);

		return "1";
	}
}
