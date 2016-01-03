package com.xingfugo.web.admin.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.service.AreaService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.web.admin.common.SessionUtil;

//订单
@Controller
public class OrderController extends BaseController{
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CommparaService commparaService;
	
	private static final String LIST_DAYS_SEARCH = "list_days_search";
	private static final String ORDER_STATE = "order_state";
	private static final String DEFAULT_ORDER_STATE = "";
	private static final String DEFAULT_SEARCH_DAYS = "30";
	
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
		model.addAttribute("order", goodsorder);
		model.addAttribute("area_name_str", area_name_str);
		model.addAttribute("order_state_name", order_state_name);
		return "order/detail";
	}
	
	//商家发货
	@RequestMapping(value="order/deliver-{id}",method=RequestMethod.GET)
	public String deliver(@PathVariable(value = "id") String id,ModelMap model){
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		this.goodsorderService.sellerDeliver(id, cust_id);
		return "redirect:"+basePath()+"order/detail-"+id+".action";
	}
}
