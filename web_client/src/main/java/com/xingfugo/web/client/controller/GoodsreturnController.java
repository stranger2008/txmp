package com.xingfugo.web.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Buyeraddr;
import com.xingfugo.business.module.GoodsReturnAddr;
import com.xingfugo.business.module.GoodsReturnHis;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Goodsreturn;
import com.xingfugo.business.module.Inc_shipinfo;
import com.xingfugo.business.module.Orderhistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.module.query.GoodsreturnQueryForm;
import com.xingfugo.business.service.BuyeraddrService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsReturnAddrService;
import com.xingfugo.business.service.GoodsReturnHisService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.GoodsreturnService;
import com.xingfugo.business.service.Inc_shipinfoService;
import com.xingfugo.business.service.OrderdetailService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.client.common.SessionUtil;

//商品退换货
@Controller
public class GoodsreturnController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(GoodsreturnController.class.getSimpleName());
	
	private static final String INFO_STATE_CODE="goodsreturn_state";
	//退货
	private static final String GOODS_RETURN_VAL="1";
	//退换货类型--字典编码
	private static final String RETURN_TYPE_CODE="goodsreturn_type";
	//订单 -- 申请退货 状态
	private static final String GOODS_RETURN_STATE="5";
	//申请退换货
	public static final String ACTION_BUYER_RETURN_ORDER = "买家申请退货/换货/维修";
	//查询时间段的字典编码
	private static final String LIST_DAYS_SEARCH = "list_days_search";
	//会员类型 0：买家 1：卖家
	private static final String USER_TYPE_BUYER = "0" ,USER_TYPE_SELLER = "1" ;
	//4：等待买家确认
	private static final String WAIT_BUYER_CONFIRM = "4";
	
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
	/**
	 * 收货地址服务
	 */
	@Autowired
	private BuyeraddrService buyeraddrService;
	/**
	 * 退换货收货地服务
	 */
	@Autowired
	private GoodsReturnAddrService goodsReturnAddrService;
	/**
	 * 物流运送信息Service层业务
	 */
	@Autowired
	private Inc_shipinfoService inc_shipinfoService;
	/**
	 * 获取符合条件的退换货商品
	 * 交易完成7日内支持退换货/退款
	 */
	@RequestMapping(value="user/goodsReturn_list")
	public String getGoodsreturnList(GoodsorderQueryForm goodsorderQueryForm,ModelMap model){
		String userId = (String)SessionUtil.get(this.getRequest(), Constants.SESSION_USER_ID);
		goodsorderQueryForm.setUser_id(userId);
		PageResult pageResult = goodsorderService.getGoodsreturnList(goodsorderQueryForm);
		pageOper(model,pageResult);
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		model.put("seardaysMap", seardaysMap);
		
		return "user/goodsreturn/index";
	}
	
	/**
	 * 查看退换货/退款记录
	 */
	@RequestMapping(value="user/goodsReturn_look")
	public String returnList_look(GoodsreturnQueryForm goodsreturnQueryForm,ModelMap model){
		//获取登录用户id
		String userId = (String)SessionUtil.get(this.getRequest(), Constants.SESSION_USER_ID);
		goodsreturnQueryForm.setUser_id(userId);
		PageResult pageResult = goodsreturnService.getGoodsreturnByPage(goodsreturnQueryForm);
		pageOper(model,pageResult);
		//时间搜索下拉框
		Map seardaysMap = this.commparaService.getSelectMapByParacode(LIST_DAYS_SEARCH);
		model.put("seardaysMap", seardaysMap);
		//状态下拉框数据
		Map infoStateMap = commparaService.getSelectMapByParacode(INFO_STATE_CODE);
		model.addAttribute("infoStateMap",infoStateMap);
		return "user/goodsreturn/returnList";
	}
	
	/**
	 * 查看退款记录
	 * @param goodsreturnQueryForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value="user/goodsReturnMoney_look")
	public String returnMoney_look(GoodsreturnQueryForm goodsreturnQueryForm,ModelMap model){
		String userId = (String)SessionUtil.get(this.getRequest(), Constants.SESSION_USER_ID);
		goodsreturnQueryForm.setUser_id(userId);
		//设置业务类型为退货
		goodsreturnQueryForm.setBiz_type(GOODS_RETURN_VAL);
		//查询退货业务列表信息
		PageResult pageResult = goodsreturnService.getGoodsreturnByPage(goodsreturnQueryForm);
		pageOper(model,pageResult);
		Map infoStateMap = commparaService.getSelectMapByParacode(INFO_STATE_CODE);
		model.addAttribute("infoStateMap",infoStateMap);
		return "user/goodsreturn/returnMoneyList";
	}
	
	/**
	 * 选择商品退换货
	 * @param id
	 * @param model
	 * @return
	 
	@RequestMapping(value="user/goodsReturn_selectgoods-{id}",method=RequestMethod.GET)
	public String selectgoods(@PathVariable(value = "id") String id,ModelMap model){
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(id);
		model.addAttribute("order", goodsorder);
		return "user/goodsreturn/selectgoods";
	}
	*/
	
	/**
	 * 进入退换货申请页面
	 */
	@RequestMapping(value="user/goodsReturn_refund-{id}",method=RequestMethod.GET)
	public String gorefund(@PathVariable(value = "id") String goods_id,String orderId ,ModelMap model){
		Goodsreturn goodsreturn = new Goodsreturn();
		model.addAttribute("goodsreturn", goodsreturn);
		
		Map param = new HashMap();
		param.put("order_id", orderId);
		param.put("goods_id", goods_id);
		List<Map<String,Object>> orderdetailList = orderdetailService.getOrderdetailForGoodsreturn(param);
		Map orderdetailMap = new HashMap();
		if(orderdetailList!=null&&orderdetailList.size()>0){
			orderdetailMap = orderdetailList.get(0);
		}
		String img  = (String)orderdetailMap.get("img_path");
		if(img!=null &img.indexOf(",")>0){
			img = img.substring(0,img.indexOf(","));
			img = ImgPathUitls.filterImagePath(img);
		}
		orderdetailMap.put("img_path", img);
		
		model.addAttribute("orderdetailMap", orderdetailMap);
		
		//从数据字典获取退换货类
		Map infoStateMap = commparaService.getSelectMapByParacode(RETURN_TYPE_CODE);
		model.addAttribute("returnTypeMap",infoStateMap);
		
		setPageInfo(model);
		
		return "user/goodsreturn/refund";
	}
	
	/**
	 * 为申请退换货页面设置参数值
	 */
	private void setPageInfo(ModelMap model){
		
		String userId = (String)SessionUtil.get(this.getRequest(), Constants.SESSION_USER_ID);
		//获取默认收获地址
		Buyeraddr defultAddr = buyeraddrService.getDefaultBuyeraddr(userId);
		model.addAttribute("defultAddr",defultAddr);
	}
	/**
	 * 插入退款记录
	 * @param goodsreturn
	 * @param trade_id 详细订单的Id标识
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="user/goodsReturn_refund",method=RequestMethod.POST)
	public String refund(@Valid Goodsreturn goodsreturn,String trade_id,Errors errors,ModelMap model){
		if(StringUtils.isBlank(goodsreturn.getCont_desc())){
			errors.rejectValue("cont_desc", "goodsreturn.cont_desc.isblank", "请输入问题描述"); 
		}
		if(goodsreturn.getCont_desc()!=null&&goodsreturn.getCont_desc().length()>500){
			errors.rejectValue("cont_desc", "goodsreturn.cont_desc.overMaxLength", "长度需要在0和500之间"); 
		}
		if(StringUtils.isBlank(goodsreturn.getApplyProof())){
			errors.rejectValue("cont_desc", "goodsreturn.applayProof.isblank", "请选择申请凭证"); 
		}
		GoodsReturnAddr grAddr = goodsreturn.getGoodsReturnAddr();
		if(grAddr!=null){
			String name = grAddr.getName();
			if(StringUtils.isBlank(name)){
				errors.rejectValue("goodsReturnAddr.name", "goodsReturnAddr.name.isblank", "请输收获人姓名"); 
			}else if(name.length()>20){
				errors.rejectValue("goodsReturnAddr.name", "goodsReturnAddr.name.toolong", "收获人姓名长度需要在0和20之间");
			}
			String phone = grAddr.getPhone();
			String regex = "^1\\d{10}$";
			if(StringUtils.isBlank(phone)){
				errors.rejectValue("goodsReturnAddr.phone", "goodsReturnAddr.phone.isblank", "请输手机号码"); 
			}else if(!Pattern.matches(regex, phone)){
				errors.rejectValue("goodsReturnAddr.phone", "goodsReturnAddr.phone.error", "手机号码不正确");
			}
			String addr_sttr = grAddr.getArea_attr();
			if(StringUtils.isBlank(addr_sttr)){
				errors.rejectValue("goodsReturnAddr.area_attr", "goodsReturnAddr.area_attr.isblank", "请选择收获地区"); 
			} 
			String addr = grAddr.getAddr();
			if(StringUtils.isBlank(addr)){
				errors.rejectValue("goodsReturnAddr.addr", "goodsReturnAddr.addr.isblank", "请收获地址详细信息"); 
			}else if(addr.length()> 100){
				errors.rejectValue("goodsReturnAddr.addr", "goodsReturnAddr.addr.error", "地址详细信息长度需要在0和100之间");
			}
		}
		Map param = new HashMap();
		param.put("trade_id",trade_id);
		List<Map<String,Object>> orderdetailList = orderdetailService.getOrderdetailForGoodsreturn(param);
		if (orderdetailList==null || orderdetailList.size()<=0){
			setPageInfo(model);
	        return "user/goodsreturn/refund";
		}
		Map orderdetailMap = orderdetailList.get(0);
		model.addAttribute("orderdetailMap", orderdetailMap);
		
		//从数据字典获取退换货类
		Map infoStateMap = commparaService.getSelectMapByParacode(RETURN_TYPE_CODE);
		model.addAttribute("returnTypeMap",infoStateMap);
		int num = goodsreturn.getApplyNum();
		int buyNum = (Integer)orderdetailMap.get("order_num");
		if(num>buyNum){
			errors.rejectValue("applyNum", "goodsreturn.applyNum.error", "提单数量不能大于您购买的数量");
		}
		if (errors.hasErrors()){
			setPageInfo(model);
	        return "user/goodsreturn/refund";
		}
		String userId = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsreturn.setUser_id(userId);
		//退换货
		goodsreturn.getGoodsReturnAddr().setUser_id(userId);
		boolean updOk = goodsreturnService.applyrefund(goodsreturn);
		if(updOk){
			//修改订单状态
			Goodsorder order = new Goodsorder();
			String orderId = goodsreturn.getOrder_id();
			order.setOrder_id(orderId);
			order.setOrder_state(GOODS_RETURN_STATE);
			
			Orderhistory orderhistory = new Orderhistory();
			orderhistory.setOrder_id(orderId);
			
			orderhistory.setUser_id(userId);
			//orderhistory.setCust_id(null);
			String actionNm = ACTION_BUYER_RETURN_ORDER;
			String type = goodsreturn.getBiz_type();
			String typeNm = (String) infoStateMap.get(type);
			actionNm = actionNm+"("+typeNm+"申请)";
			orderhistory.setAction_name(actionNm);
			goodsorderService.updateState(order ,orderhistory);
			
		}
		return "redirect:"+basePath()+"user/goodsReturn_refundok.action";
	}
	
	//进入退换货申请成功页面
	@RequestMapping(value="user/goodsReturn_refundok",method=RequestMethod.GET)
	public String refundok(){
		return "user/goodsreturn/refundok";
	}
	
	//退换货进度查看
	@RequestMapping(value="user/goodsReturn_prolist",method=RequestMethod.GET)
	public String goodslist(String trade_id,ModelMap model,RedirectAttributes redirectAttributes){
		//查询商品退换货信息
		Map goodsreturn = goodsreturnService.getByPk(trade_id);
		model.addAttribute("goodsreturn", goodsreturn);
		if(goodsreturn==null){
			redirectAttributes.addFlashAttribute("promptMessage", "没有查找到退换货申请单");
			return "redirect:"+basePath()+"user/goodsReturn_list.action";
		}
		//查询商品换货维修返回，地址信息（即买家地址）
		GoodsReturnAddr returnAddr = goodsReturnAddrService.getByLinkId(trade_id , USER_TYPE_BUYER);
		model.addAttribute("returnAddr", returnAddr);
		//查询商品退换货动态信息
		List<GoodsReturnHis> returnHisList = goodsReturnHisService.getByLinkId(trade_id);
		model.addAttribute("returnHisList", returnHisList);
		String info_state = (String)goodsreturn.get("info_state");
		if(WAIT_BUYER_CONFIRM.equals(info_state)){
			Inc_shipinfo shipInfo = inc_shipinfoService.getByLinkId(trade_id, USER_TYPE_SELLER);
			model.addAttribute("shipInfo", shipInfo);
			model.addAttribute("goodsreturnObj", new Goodsreturn());
		}
		return "user/goodsreturn/prolist";
	}
	
	/**
	 * 根据退换货标识，得到退货单异动。
	 
	@RequestMapping(value = "user/goodsreturnhis-{link_id}", method = RequestMethod.GET)
	public String showByLinkId(@PathVariable(value = "link_id") String link_id, ModelMap model) {
		List<GoodsReturnHis> goodsReturnHis = goodsReturnHisService.getByLinkId(link_id);
		Goodsreturn goodsreturn = goodsreturnService.getByTradeid(link_id);
		String info_state_name = commparaService.getParakeyByParacode("goodsreturn_state",goodsreturn.getInfo_state());
		model.addAttribute("goodsReturnHis", goodsReturnHis);
		model.addAttribute("info_state_name", info_state_name);
		return "user/goodsreturn/goodsReturnHis";
	}
	*/
	
	/**
	 * 买家确认收货
	 */
	@RequestMapping(value="user/goodsReturn_receive")
	public String confirmReceive_buyer(Goodsreturn goodsreturnObj,Errors errors ,ModelMap model,RedirectAttributes redirectAttributes){
		String trade_id = goodsreturnObj.getTrade_id();
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);;
		if(user_id==null||"".equals(user_id)){
			redirectAttributes.addFlashAttribute("promptMessage", "没有获取到时登录用户信息");
			return "redirect:"+basePath()+"user/goodsReturn_prolist.action?trade_id="+trade_id ;
		}
		boolean ok = goodsreturnService.buyerReceive(goodsreturnObj ,user_id );
		if(ok){
			redirectAttributes.addFlashAttribute("promptMessage", "确认收货操作成功");
		}else{
			redirectAttributes.addFlashAttribute("promptMessage", "确认收货操作失败");
		}
		return "redirect:"+basePath()+"user/goodsReturn_prolist.action?trade_id="+trade_id ;
	}
}
