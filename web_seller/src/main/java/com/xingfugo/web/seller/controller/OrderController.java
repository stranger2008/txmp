package com.xingfugo.web.seller.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Inc_shipinfo;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.service.AreaService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.Inc_shipinfoService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.seller.common.SessionUtil;

//订单
@Controller
public class OrderController extends BaseController{
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CommparaService commparaService;
	//物流运送信息Service层业务
	@Autowired
	private Inc_shipinfoService inc_shipinfoService;
	
	private static final String LIST_DAYS_SEARCH = "list_days_search";
	private static final String ORDER_STATE = "order_state";
	private static final String DEFAULT_ORDER_STATE = "";
	private static final String DEFAULT_SEARCH_DAYS = "30";
	//物流公司字段编码
	private static final String SHIP_TYPE = "ship_id";
	
	//物流信息用户类型-卖家
	private static final String INC_SHIPINFO_USER_TYPE_SELLER = "1";
	
	//等待卖家发货
	private static final String GOODSORDER_STATE_SEND = "1";
	//等待买家确认
	private static final String GOODSORDER_STATE_CONFIRM = "2";
	//交易成功
	private static final String GOODSORDER_STATE_SUCCESS = "4";
	//申请退货
	private static final String GOODSORDER_STATE_RETURN = "5";
	//评价成功
	private static final String GOODSORDER_STATE_EVALUATE = "6";
	
	//有物流信息的订单类型
	private static List<String> GOODSORDER_STATE_HAS_SHIP_INFO = new ArrayList<String>();
	
	static {
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_CONFIRM);
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_SUCCESS);
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_RETURN);
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_EVALUATE);
	}
	
	//订单列表
	@RequestMapping(value="order/list")
	public String list(GoodsorderQueryForm goodsorderQueryForm,ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		goodsorderQueryForm.setCust_id(cust_id);
		
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		//状态搜索下拉框
		Map _orderStateMap = this.commparaService.getSelectMapByParacode(ORDER_STATE);
		//根据状态统计订单数量
		List orderCountList = this.goodsorderService.getOrderCountByCustid(Integer.parseInt(cust_id));
		
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
		model.put("orderCountList", orderCountList);
		pageOper(model,goodsorderService.getGoodsOrderListByPage(goodsorderQueryForm));
		model.addAttribute("goodsorderQueryForm", goodsorderQueryForm);
		return "order/list";
	}
	
	//查看订单详细
	@RequestMapping(value="order/detail-{id}",method=RequestMethod.GET)
	public String detail(@PathVariable(value = "id") String id,ModelMap model){
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(id);
		//根据地区ID串获取地区名称串
		String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(goodsorder.getArea_attr());
		String order_state_name = this.commparaService.getParakeyByParacode("order_state", goodsorder.getOrder_state());
		//物流信息
		Inc_shipinfo inc_shipinfo = new Inc_shipinfo();
		String order_state = goodsorder.getOrder_state();
		if(GOODSORDER_STATE_HAS_SHIP_INFO.contains(order_state)) {
			Map<String, String> rmap = new HashMap<String, String>();
			rmap.put("hasShipName", "1");
			rmap.put("link_id", id);
			List<Map<String, Object>> inc_shipinfos = inc_shipinfoService.getList(rmap);
			if(inc_shipinfos == null || inc_shipinfos.size() != 0) {
				inc_shipinfo.setTrade_id(inc_shipinfos.get(0).get("trade_id") == null ? null : String.valueOf(inc_shipinfos.get(0).get("trade_id")));
				inc_shipinfo.setShip_id(inc_shipinfos.get(0).get("ship_id") == null ? null : (String)inc_shipinfos.get(0).get("ship_id"));
				inc_shipinfo.setLink_id(inc_shipinfos.get(0).get("link_id") == null ? null : (String)inc_shipinfos.get(0).get("link_id"));
				inc_shipinfo.setShip_no(inc_shipinfos.get(0).get("ship_no") == null ? null : (String)inc_shipinfos.get(0).get("ship_no"));
				inc_shipinfo.setShip_img(inc_shipinfos.get(0).get("ship_img") == null ? null : ImgPathUitls.filterImagePath((String)inc_shipinfos.get(0).get("ship_img")));
				inc_shipinfo.setShip_desc(inc_shipinfos.get(0).get("ship_desc") == null ? null : (String)inc_shipinfos.get(0).get("ship_desc"));
				inc_shipinfo.setUser_id(inc_shipinfos.get(0).get("user_id") == null ? null : String.valueOf(inc_shipinfos.get(0).get("user_id")));
				inc_shipinfo.setUser_type(inc_shipinfos.get(0).get("user_type") == null ? null : (String)inc_shipinfos.get(0).get("user_type"));
				inc_shipinfo.setIn_date(inc_shipinfos.get(0).get("in_date") == null ? null : (Date)inc_shipinfos.get(0).get("in_date"));
				inc_shipinfo.setShip_name(inc_shipinfos.get(0).get("ship_name") == null ? null : (String)inc_shipinfos.get(0).get("ship_name"));
			}
		}
		//等待卖家发货
		if(GOODSORDER_STATE_SEND.equals(order_state)) {
			//物流公司下拉框
			Map shipTypeMap = this.commparaService.getSelectMapByParacode(SHIP_TYPE);
			model.addAttribute("shipTypeMap",shipTypeMap);
		}
		model.addAttribute("inc_shipinfo", inc_shipinfo);
		
		model.addAttribute("order", goodsorder);
		model.addAttribute("area_name_str", area_name_str);
		model.addAttribute("order_state_name", order_state_name);
		return "order/detail";
	}
	
	//商家发货
	@RequestMapping(value="order/deliver-{id}",method=RequestMethod.POST)
	public String deliver(@PathVariable(value = "id") String id, @Valid Inc_shipinfo inc_shipinfo,Errors errors,RedirectAttributes rAttr,ModelMap model){
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		if (errors.hasErrors()){
			Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(id);
			//根据地区ID串获取地区名称串
			String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(goodsorder.getArea_attr());
			String order_state_name = this.commparaService.getParakeyByParacode("order_state", goodsorder.getOrder_state());
			
			Map shipTypeMap = this.commparaService.getSelectMapByParacode(SHIP_TYPE);
			model.addAttribute("shipTypeMap",shipTypeMap);
			model.addAttribute("order", goodsorder);
			model.addAttribute("area_name_str", area_name_str);
			model.addAttribute("order_state_name", order_state_name);
			
	    	return "order/detail";
		}
		inc_shipinfo.setLink_id(id);
		inc_shipinfo.setUser_id(SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID));
		inc_shipinfo.setUser_type(INC_SHIPINFO_USER_TYPE_SELLER);
		this.goodsorderService.sellerDeliver(id, cust_id, inc_shipinfo);
		return "redirect:"+basePath()+"order/detail-"+id+".action";
	}
}
