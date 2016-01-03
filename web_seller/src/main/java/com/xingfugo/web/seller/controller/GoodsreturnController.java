package com.xingfugo.web.seller.controller;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.xingfugo.business.module.Inc_shipinfo;
import com.xingfugo.business.module.Selleraddr;
import com.xingfugo.business.module.query.GoodsreturnQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsreturnService;
import com.xingfugo.business.service.OrderdetailService;
import com.xingfugo.business.service.SelleraddrService;
import com.xingfugo.web.seller.common.SessionUtil;

//商品退换货
@Controller
public class GoodsreturnController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(GoodsreturnController.class.getSimpleName());
	//退换货服务
	@Autowired
	private GoodsreturnService goodsreturnService;
	//数据字典服务接口
	@Autowired
	private CommparaService commparaService;
	//商家地址服务接口
	@Autowired
	private SelleraddrService selleraddrService;
	
	@Autowired
	private OrderdetailService orderdetailService;

	private static final String LIST_DAYS_SEARCH = "list_days_search";
	private static final String ORDER_STATE = "goodsreturn_state";
	private static final String GOODSRETURN_TYPE = "goodsreturn_type";
	//物流公司字段编码
	private static final String SHIP_TYPE = "ship_id";
	private static final String DEFAULT_ORDER_STATE = "";
	private static final String DEFAULT_SEARCH_DAYS = "30";
	 
	//3：等待卖家发货
	private static final String WAIT_SELLER_SHIP = "3";
	//0：换货 1：退货 2：维修
	private static final String TUIHUO="1";
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
		String biz_type = goodsreturnQueryForm.getBiz_type();
		if(biz_type!=null){
			String biz_type_nm = (String)_biztypeMap.get(biz_type);
			model.put("biz_type_name", biz_type_nm);
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
		String cust_id = SessionUtil.getString(getRequest(),Constants.SESSION_CUST_ID);
		Map goodsreturnMap = this.goodsreturnService.getByPk(id);
		model.addAttribute("goodsreturnMap",goodsreturnMap);
		model.addAttribute("goodsreturn",new Goodsreturn());
		//获取商家默认地址
		Selleraddr selleraddr = selleraddrService.getDefaultBuyeraddr(cust_id);
		model.addAttribute("selleraddr",selleraddr);
		//状态
		String state = (String)goodsreturnMap.get("info_state");
		if(WAIT_SELLER_SHIP.equals(state)){
			model.addAttribute("inc_shipinfo",new Inc_shipinfo());
			//物流公司下拉框
			Map shipTypeMap = this.commparaService.getSelectMapByParacode(SHIP_TYPE);
			model.addAttribute("shipTypeMap",shipTypeMap);
		}
		Object biz_type = goodsreturnMap.get("biz_type");
		if(biz_type!=null&&TUIHUO.equals(biz_type.toString())){
			Object numStr = goodsreturnMap.get("applyNum");
			int num = (Integer)numStr ;
			Map param = new HashMap();
			param.put("order_id", goodsreturnMap.get("order_id"));
			param.put("goods_id", goodsreturnMap.get("goods_id"));
			List<Map<String,Object>> orderdetailList = orderdetailService.getOrderdetailForGoodsreturn(param);
			if(orderdetailList!=null && orderdetailList.size()>0){
				Map orderdetailMap = orderdetailList.get(0);
				Object price_obj = orderdetailMap.get("order_price");
				BigDecimal  price = (BigDecimal )price_obj;
				goodsreturnMap.put("refundMoney", price.doubleValue()*num);
				goodsreturnMap.put("order_price", price_obj);
			}
			 
		}
		return "goodsreturn/detail";
	}
	
	//退换货审核（商家审核）
	@RequestMapping(value="goodsreturn/audit")
	public String goodslist(@Valid Goodsreturn goodsreturn,Errors errors,String audit_code,ModelMap model,RedirectAttributes redirectAttributes){
		String cust_id = SessionUtil.getString(getRequest(),Constants.SESSION_CUST_ID);
		String trade_id = goodsreturn.getTrade_id();
		//审核不通过需要输入不通过理由 0：通过 1：不通过
		if("1".equals(audit_code) && StringUtils.isBlank(goodsreturn.getNo_reason())){
			errors.rejectValue("no_reason", "goodsreturn.no_reason.noblank", "请输入审核不通过理由"); 
		}
		
		if (errors.hasErrors()){
			Map goodsreturnMap = goodsreturnService.getByPk(trade_id);
			model.addAttribute("goodsreturnMap",goodsreturnMap);
	        return "goodsreturn/detail";
		}
		goodsreturnService.sellerAudit(goodsreturn , audit_code, cust_id);
		operatePrompt(redirectAttributes,"退换货审核成功");
		return "redirect:"+basePath()+"goodsreturn/detail-"+trade_id+".action";
	}
	
	/**
	 * 处理退换货信息（ 商家确认收货）
	 * @param goodsreturn
	 * @param hanle_code
	 * @param errors
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="goodsreturn/handle")
	public String goodsreturnHandle(Goodsreturn goodsreturn ,String hanle_code,Errors errors,ModelMap model,RedirectAttributes redirectAttributes){
		String trade_id = goodsreturn.getTrade_id();
		if(hanle_code==null){
			operatePrompt(redirectAttributes,"操作失败");
		}else{
			String cust_id = SessionUtil.getString(getRequest(),Constants.SESSION_CUST_ID);
			goodsreturnService.sellerConfirmReceive(goodsreturn ,hanle_code ,cust_id );
		}
		
		return "redirect:"+basePath()+"goodsreturn/detail-"+trade_id+".action";
	}
	
	/**
	 * 处理退换货信息(商家发货)
	 * @param goodsreturn
	 * @param inc_shipinfo
	 * @param model
	 * @param errors
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="goodsreturn/resend")
	public String reSend(@Valid Inc_shipinfo inc_shipinfo,String biz_type  ,ModelMap model,Errors errors,RedirectAttributes redirectAttributes){
		//获取退换货申请单号
		String trade_id = inc_shipinfo.getLink_id();
		if(inc_shipinfo.getShip_desc()!=null&&inc_shipinfo.getShip_desc().length()>600){
			errors.rejectValue("ship_desc", "inc_shipinfo.ship_desc.overMaxlength", "长度需要在0和600之间"); 
		}
		if(inc_shipinfo.getShip_img()!=null&&inc_shipinfo.getShip_img().length()>600){
			errors.rejectValue("ship_img", "inc_shipinfo.ship_img.overMaxlength", "长度需要在0和600之间"); 
		}
		if (errors.hasErrors()){
			//物流公司下拉框
			Map shipTypeMap = this.commparaService.getSelectMapByParacode(SHIP_TYPE);
			model.addAttribute("shipTypeMap",shipTypeMap);
			Map goodsreturnMap = goodsreturnService.getByPk(trade_id);
			model.addAttribute("goodsreturnMap",goodsreturnMap);
	        return "goodsreturn/detail";
		}
		Goodsreturn goodsreturn = new Goodsreturn();
		goodsreturn.setTrade_id(trade_id);
		goodsreturn.setBiz_type(biz_type);
		String cust_id = SessionUtil.getString(getRequest(),Constants.SESSION_CUST_ID);
		inc_shipinfo.setUser_id(cust_id);
		//修改退换货申请单状态，插入历史记录，添加物流信息
		goodsreturnService.sellerResend(goodsreturn ,inc_shipinfo,cust_id );
		
		return "redirect:"+basePath()+"goodsreturn/detail-"+trade_id+".action";
	}
	/**
	 * 商家退款
	 * @param goodsreturn
	 * @param errors
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="goodsreturn/sellerRefund")
	public String sellerRefund(@Valid Goodsreturn goodsreturn,Errors errors ,ModelMap model,RedirectAttributes redirectAttributes){
		String trade_id = goodsreturn.getTrade_id();

		String cust_id = SessionUtil.getString(getRequest(),Constants.SESSION_CUST_ID);
		int ret = goodsreturnService.sellerRefundMoney(goodsreturn ,cust_id );
		if(ret == 1){
			operatePrompt(redirectAttributes,"退款成功");
		}else if(ret == -3){
			operatePrompt(redirectAttributes,"信息错误,退款操作失败");
		} else if(ret == -2) {
			operatePrompt(redirectAttributes,"资金账户状态不可用,退款操作失败");
		} else if(ret == -1) {
			operatePrompt(redirectAttributes,"资金账户余额不足,退款操作失败");
		} else if(ret == 0) {
			operatePrompt(redirectAttributes,"系统错误,退款操作失败");
		}
		return "redirect:"+basePath()+"goodsreturn/detail-"+trade_id+".action";
	}
}
