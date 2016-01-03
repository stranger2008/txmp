package com.xingfugo.web.admin.controller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Buyer_complaint;
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

/**
 * @function 功能 举报记录信息Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Mon Oct 20 11:43:07 CST 2014
 */
 
@Controller
public class Buyer_reportController extends BaseController{
	
	@Autowired
	private Buyer_reportService buyer_reportService;
	
	@Autowired
	private CommparaService commparaService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private MemberService memberService;
	
	//类型
	private static final String REPORT_TYPE = "report_type";
	
	//列表
	@RequestMapping(value="buyer_report/index")
	public String list(Buyer_reportQueryForm buyer_reportQueryForm,ModelMap model) throws Exception {
		//类型
		Map reportMap = this.commparaService.getSelectMapByParacode(true,REPORT_TYPE);
		model.addAttribute("reportMap", reportMap);
		
		PageResult pageResult = buyer_reportService.getListByPage(buyer_reportQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("buyer_reportQueryForm", buyer_reportQueryForm);
		return "buyer_report/index";
	}
	
	//进入新增
	@RequestMapping(value="buyer_report/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Buyer_report buyer_report = new Buyer_report();
		model.addAttribute("buyer_report", buyer_report);
		return "buyer_report/add";
	}
	
	//新增
	@RequestMapping(value="buyer_report/add",method=RequestMethod.POST)
	public String insert(@Valid Buyer_report buyer_report,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "buyer_report/add";
		}
		buyer_reportService.insert(buyer_report);
		operatePrompt(rAttr, "新增举报记录信息成功");
		return "redirect:"+basePath()+"buyer_report/index.action";
	}
	
	//查看举报详细信息
	@RequestMapping(value="buyer_report/view",method=RequestMethod.GET)
	public String view(String id,ModelMap model,Buyer_report buyer_report_s) throws Exception {
		Buyer_report buyer_report = buyer_reportService.getByPk(id);
		if(buyer_report == null){
			return "redirect:"+basePath()+"buyer_report/index.action";
		}
		String goods_id="",goods_name="",cust_id="",cust_name="",img_evidence="";
		goods_id = buyer_report.getGoods_id();
		if(StringUtils.isBlank(goods_id) ){
			return "redirect:"+basePath()+"buyer_report/index.action";
		}
		Goods goods = goodsService.getByPk(goods_id);
		if(goods == null){
			return "redirect:"+basePath()+"buyer_report/index.action";
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
		model.addAttribute("buyer_report", buyer_report);
		model.addAttribute("buyer_report_s", buyer_report_s);
		return "buyer_report/view";
	}
	
	//运营商处理结果
	@RequestMapping(value="buyer_report/view",method=RequestMethod.POST)
	public String update(@Valid Buyer_report buyer_report,Errors errors,RedirectAttributes rAttr) throws Exception {
		String report_id = buyer_report.getReport_id();
		if(StringUtils.isBlank(report_id)){
			operatePrompt(rAttr, "运营商处理信息提交失败");
			return "redirect:"+basePath()+"buyer_report/index.action";
		}
		Buyer_report buyer_report_s = buyer_reportService.getByPk(report_id);
		if(buyer_report_s == null){
			operatePrompt(rAttr, "运营商处理信息提交失败");
			return "redirect:"+basePath()+"buyer_complaint/index.action";
		}
		buyer_report_s.setRemark(buyer_report.getRemark());
		buyer_report_s.setStatus("1");
		buyer_reportService.update(buyer_report_s);
		operatePrompt(rAttr, "运营商处理结果信息已提交");
		return "redirect:"+basePath()+"buyer_report/view.action?id="+report_id;
	}
	
	//删除
	@RequestMapping(value="buyer_report/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		buyer_reportService.delete(id);
		operatePrompt(rAttr, "删除举报记录信息成功");
		return "redirect:"+basePath()+"buyer_report/index.action";
	}
	
}

