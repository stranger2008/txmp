package com.xingfugo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.GoodsReturnHis;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Goodsreturn;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.module.query.GoodsreturnQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsReturnHisService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.GoodsreturnService;
import com.xingfugo.business.service.OrderdetailService;
import com.xingfugo.common.SessionUtil;

//商品退换货
@Controller
public class GoodsreturnController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(GoodsreturnController.class.getSimpleName());
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private GoodsreturnService goodsreturnService;
	
	@Autowired
	private OrderdetailService orderdetailService;
	
	@Autowired
	private GoodsReturnHisService goodsReturnHisService;
	
	@Autowired
	private CommparaService commparaService;
	
	//获取符合条件的退换货商品
	//交易完成7日内支持退换货/退款
	@RequestMapping(value="user/goodsReturn_list",method=RequestMethod.GET)
	public String getGoodsreturnList(GoodsorderQueryForm goodsorderQueryForm,ModelMap model){
		pageOper(model,goodsorderService.getGoodsreturnList(goodsorderQueryForm));
		return "user/goodsreturn/index";
	}
	
	//选择商品退换货
	@RequestMapping(value="user/goodsReturn_selectgoods-{id}",method=RequestMethod.GET)
	public String selectgoods(@PathVariable(value = "id") String id,ModelMap model){
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(id);
		model.addAttribute("order", goodsorder);
		return "user/goodsreturn/selectgoods";
	}
	
	//进入退款页面
	@RequestMapping(value="user/goodsReturn_refund-{id}",method=RequestMethod.GET)
	public String refund(@PathVariable(value = "id") String id,ModelMap model){
		Map param = new HashMap();
		param.put("trade_id", id);
		List<Map<String,Object>> orderdetailList = orderdetailService.getOrderdetailForGoodsreturn(param);
		if(orderdetailList!=null&&orderdetailList.size()>0){
			Map orderdetailMap = orderdetailList.get(0);
			model.addAttribute("orderdetailMap", orderdetailMap);
		}
		Goodsreturn goodsreturn = new Goodsreturn();
		model.addAttribute("goodsreturn", goodsreturn);
		return "user/goodsreturn/refund";
	}
	
	//插入退款记录
	@RequestMapping(value="user/goodsReturn_refund",method=RequestMethod.POST)
	public String refund(@Valid Goodsreturn goodsreturn,String trade_id,Errors errors,ModelMap model){
		if(StringUtils.isBlank(goodsreturn.getCont_desc())){
			errors.rejectValue("cont_desc", "goodsreturn.cont_desc.isblank", "请输入问题描述"); 
		}
		Map param = new HashMap();
		param.put("trade_id", trade_id);
		List<Map<String,Object>> orderdetailList = orderdetailService.getOrderdetailForGoodsreturn(param);
		if(orderdetailList!=null&&orderdetailList.size()>0){
			Map orderdetailMap = orderdetailList.get(0);
			model.addAttribute("orderdetailMap", orderdetailMap);
		}
		if (errors.hasErrors()){
	           return "user/goodsreturn/refund";
		}
		//退款
		goodsreturn.setUser_id(SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID));
		goodsreturnService.applyrefund(goodsreturn);
		return "redirect:"+basePath()+"user/goodsReturn_refundok.action";
	}
	
	//插入退款记录
	@RequestMapping(value="user/goodsReturn_refundok",method=RequestMethod.GET)
	public String refundok(){
		return "user/goodsreturn/refundok";
	}
	
	//退换货进度查看
	@RequestMapping(value="user/goodsReturn_prolist",method=RequestMethod.GET)
	public String goodslist(GoodsreturnQueryForm goodsreturnQueryForm,ModelMap model){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsreturnQueryForm.setUser_id(user_id);
		pageOper(model,goodsreturnService.getGoodsreturnByPage(goodsreturnQueryForm));
		model.addAttribute("g",goodsreturnQueryForm);
		return "user/goodsreturn/prolist";
	}
	
	// 根据退换货标识，得到退货单异动。
	@RequestMapping(value = "user/goodsreturnhis-{link_id}", method = RequestMethod.GET)
	public String showByLinkId(@PathVariable(value = "link_id") String link_id, ModelMap model) {
		List<GoodsReturnHis> goodsReturnHis = goodsReturnHisService.getByLinkId(link_id);
		Goodsreturn goodsreturn = goodsreturnService.getByTradeid(link_id);
		String info_state_name = commparaService.getParakeyByParacode("goodsreturn_state",goodsreturn.getInfo_state());
		model.addAttribute("goodsReturnHis", goodsReturnHis);
		model.addAttribute("info_state_name", info_state_name);
		return "user/goodsreturn/goodsReturnHis";
	}
	
	
}
