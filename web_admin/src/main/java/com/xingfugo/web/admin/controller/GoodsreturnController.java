package com.xingfugo.web.admin.controller;

import java.util.LinkedHashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Goodsreturn;
import com.xingfugo.business.module.query.GoodsreturnQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsreturnService;
import com.xingfugo.web.admin.common.SessionUtil;

//商品退换货
@Controller
public class GoodsreturnController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(GoodsreturnController.class.getSimpleName());
	
	@Autowired
	private GoodsreturnService goodsreturnService;
	
	@Autowired
	private CommparaService commparaService;
	
	private static final String LIST_DAYS_SEARCH = "list_days_search";
	private static final String ORDER_STATE = "goodsreturn_state";
	private static final String GOODSRETURN_TYPE = "goodsreturn_type";
	private static final String DEFAULT_ORDER_STATE = "";
	private static final String DEFAULT_SEARCH_DAYS = "30";
	
	//退换货申请列表
	@RequestMapping(value="goodsreturn/list")
	public String goodslist(GoodsreturnQueryForm goodsreturnQueryForm,ModelMap model){
		String cust_id = SessionUtil.getString(getRequest(),Constants.SESSION_CUST_ID);
		goodsreturnQueryForm.setCust_id(cust_id);
		
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		//状态搜索下拉框
		Map _orderStateMap = this.commparaService.getSelectMapByParacode(ORDER_STATE);
		//退换货类型
		Map _biztypeMap = this.commparaService.getSelectMapByParacode(GOODSRETURN_TYPE);
		//根据状态统计订单数量
		List stateCountList = this.goodsreturnService.getStateCountByCustid(cust_id);
		
		Map infoStateMap = new LinkedHashMap();
		infoStateMap.put("", "全部状态");
		infoStateMap.putAll(_orderStateMap);
		
		Map biztypeMap = new LinkedHashMap();
		biztypeMap.put("", "类型");
		biztypeMap.putAll(_biztypeMap);
		
		if(goodsreturnQueryForm.getSear_days() == null){
			goodsreturnQueryForm.setSear_days(DEFAULT_SEARCH_DAYS);
		}
		
		if(goodsreturnQueryForm.getInfo_state() == null){
			goodsreturnQueryForm.setInfo_state(DEFAULT_ORDER_STATE);
		}
		
		model.put("seardaysMap", seardaysMap);
		model.put("biztypeMap", biztypeMap);
		model.put("infoStateMap", infoStateMap);
		model.put("stateCountList", stateCountList);
		
		pageOper(model,goodsreturnService.getGoodsreturnByPage(goodsreturnQueryForm));
		model.addAttribute("goodsreturnQueryForm",goodsreturnQueryForm);
		return "goodsreturn/list";
	}
	
	//查看退换货详细
	@RequestMapping(value="goodsreturn/detail-{id}")
	public String goodslist(@PathVariable(value = "id") String id,ModelMap model){
		Map goodsreturnMap = this.goodsreturnService.getByPk(id);
		model.addAttribute("goodsreturnMap",goodsreturnMap);
		model.addAttribute("goodsreturn",new Goodsreturn());
		return "goodsreturn/detail";
	}
	
	//退换货审核
	@RequestMapping(value="goodsreturn/audit")
	public String goodslist(@Valid Goodsreturn goodsreturn,Errors errors,String audit_code,ModelMap model,RedirectAttributes redirectAttributes){
		String cust_id = SessionUtil.getString(getRequest(),Constants.SESSION_CUST_ID);
		String trade_id = goodsreturn.getTrade_id();
		String no_reason = goodsreturn.getNo_reason();
		String biz_type = goodsreturn.getBiz_type();
		//审核不通过需要输入不通过理由 0：通过 1：不通过
		if(biz_type.equals("1") && StringUtils.isBlank(no_reason)){
			errors.rejectValue("no_reason", "goodsreturn.no_reason.noblank", "请输入审核不通过理由"); 
		}
		
		Map goodsreturnMap = goodsreturnService.getByPk(trade_id);
		model.addAttribute("goodsreturnMap",goodsreturnMap);
		
		if (errors.hasErrors()){
	           return "goodsreturn/detail";
		}
		goodsreturnService.sellerAudit(goodsreturn, audit_code , cust_id);
		operatePrompt(redirectAttributes,"退换货审核成功");
		return "redirect:"+basePath()+"goodsreturn/detail-"+trade_id+".action";
	}
}
