package com.xingfugo.web.seller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Buyer_report;
import com.xingfugo.business.module.Goods;
import com.xingfugo.business.module.Member;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.Buyer_reportQueryForm;
import com.xingfugo.business.service.Buyer_reportService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.seller.common.SessionUtil;

/**
 * @function 功能 举报记录信息
 * @author 创建人 陈晓艺
 * @date 创建日期 Mon Oct 20 11:43:07 CST 2014
 */
 
@Controller
public class Buyer_reportController extends BaseController{
	
	@Autowired
	private Buyer_reportService buyer_reportService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CommparaService commparaService;
	
	@Autowired
	private MemberService memberService;
	
	//类型
	private static final String REPORT_TYPE = "report_type";
	
	//列表
	@RequestMapping(value="buyer_report/list")
	public String list(HttpServletRequest request,Buyer_reportQueryForm buyer_reportQueryForm,ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		buyer_reportQueryForm.setCust_id(cust_id);
		PageResult pageResult = buyer_reportService.getListByPage(buyer_reportQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("buyer_reportQueryForm", buyer_reportQueryForm);
		return "buyer_report/index";
	}
	
	
	
	//查看举报信息
	@RequestMapping(value="buyer_report/view-{report_id}",method=RequestMethod.GET)
	public String view(@PathVariable(value = "report_id") String report_id,ModelMap model) throws Exception {
		Buyer_report buyer_report = buyer_reportService.getByPk(report_id);
		if(buyer_report == null){
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		String goods_id="",goods_name="",cust_id="",cust_name="",img_evidence="";
		goods_id = buyer_report.getGoods_id();
		if(StringUtils.isBlank(goods_id) ){
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		Goods goods = goodsService.getByPk(goods_id);
		if(goods == null){
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		
		goods_name = goods.getGoods_name();
		cust_id = goods.getCust_id();
		Member member = memberService.getByPk(cust_id);
		cust_name = member.getCust_name();
		
		img_evidence = buyer_report.getImg_evidence();
		if(StringUtils.isNotBlank(img_evidence)){
			buyer_report.setImg_evidence(ImgPathUitls.filterImagePath(img_evidence));
		}
		
		//举报类型
		String r_type = this.commparaService.getParakeyByParacode(REPORT_TYPE,buyer_report.getR_type());
		model.addAttribute("r_type", r_type);
		
		model.addAttribute("goods_name", goods_name);
		model.addAttribute("cust_name", cust_name);
		model.addAttribute("cust_id", cust_id);
		
		model.addAttribute("buyer_report", buyer_report);
		return "buyer_report/view";
	}
	
	
}

