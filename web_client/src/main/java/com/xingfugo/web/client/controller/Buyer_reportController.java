package com.xingfugo.web.client.controller;

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
import com.xingfugo.web.client.common.SessionUtil;

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
	@RequestMapping(value="user/buyer_report")
	public String list(HttpServletRequest request,Buyer_reportQueryForm buyer_reportQueryForm,ModelMap model) throws Exception {
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		buyer_reportQueryForm.setUser_id(user_id);
		PageResult pageResult = buyer_reportService.getListByPage(buyer_reportQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("buyer_reportQueryForm", buyer_reportQueryForm);
		return "user/buyer_report/index";
	}
	
	//进入新增
	@RequestMapping(value="user/add_buyer_report",method=RequestMethod.GET)
	public String add(String goods_id,String cust_id,ModelMap model,HttpServletRequest request) throws Exception {
		if(StringUtils.isBlank(goods_id) && StringUtils.isBlank(cust_id)){
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		Map buyermap = new HashMap();
		buyermap.put("user_id", user_id);
		buyermap.put("goods_id", goods_id);
		List list = buyer_reportService.getList(buyermap);
		if(list.size()>0)
		{
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		Goods goods = goodsService.getByPk(goods_id);
		if(goods == null){
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		//类型
		Map reportMap = this.commparaService.getSelectMapByParacode(REPORT_TYPE);
		model.addAttribute("reportMap", reportMap);
		
		String cust_name="",goods_name="";
		cust_name = goods.getCust_name();
		goods_name = goods.getGoods_name();
		Buyer_report buyer_report = new Buyer_report();
		model.addAttribute("goods_id", goods_id);
		model.addAttribute("cust_id", cust_id);
		model.addAttribute("cust_name", cust_name);
		model.addAttribute("goods_name", goods_name);
		model.addAttribute("r_type_s",""); //举报类型
		model.addAttribute("buyer_report", buyer_report);
		return "user/buyer_report/add";
	}
	
	//新增
	@RequestMapping(value="user/add_buyer_report",method=RequestMethod.POST)
	public String insert(@Valid Buyer_report buyer_report,Errors errors,HttpServletRequest request,ModelMap model) throws Exception {
		Goods goods = goodsService.getByPk(buyer_report.getGoods_id());
		if(goods == null){
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		//类型
		Map reportMap = this.commparaService.getSelectMapByParacode(REPORT_TYPE);
		model.addAttribute("reportMap", reportMap);
		
		model.addAttribute("goods_id", goods.getGoods_id());
		model.addAttribute("cust_id", goods.getCust_name());
		model.addAttribute("cust_name", goods.getCust_name());
		model.addAttribute("goods_name", goods.getGoods_name());
		model.addAttribute("r_type_s",buyer_report.getR_type());
		
		if (errors.hasErrors()){
	           return "user/buyer_report/add";
		}
		
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		Map buyermap = new HashMap();
		buyermap.put("user_id", user_id);
		buyermap.put("goods_id", buyer_report.getGoods_id());
		List list = buyer_reportService.getList(buyermap);
		if(list.size()>0)
		{
			return "redirect:"+basePath()+"user/buyer_report.action";
		}
		
		buyer_report.setUser_id(user_id);
		buyer_report.setCust_id(goods.getCust_id());
		buyer_report.setStatus("0");
		buyer_reportService.insert(buyer_report);
		return "redirect:"+basePath()+"user/buyer_report.action";
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
		return "user/buyer_report/view";
	}
	
	//修改
	@RequestMapping(value="buyer_report/update",method=RequestMethod.POST)
	public String update(@Valid Buyer_report buyer_report,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "buyer_report/update";
		}
		buyer_reportService.update(buyer_report);
		return "redirect:"+basePath()+"buyer_report/index.action";
	}
	
	//删除
	@RequestMapping(value="buyer_report/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		buyer_reportService.delete(id);
		return "redirect:"+basePath()+"buyer_report/index.action";
	}
	
}

