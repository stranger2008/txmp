package com.xingfugo.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Payment;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.PaymentQueryForm;
import com.xingfugo.business.service.PaymentService;

/**
 * @function 功能 支付方式Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 11 10:13:43 CST 2014
 */
 
@Controller
public class PaymentController extends BaseController{
	
	@Autowired
	private PaymentService paymentService;
	
	//列表
	@RequestMapping(value="payment/index")
	public String list(PaymentQueryForm paymentQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = paymentService.getListByPage(paymentQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("paymentQueryForm", paymentQueryForm);
		return "payment/index";
	}
	
	//进入新增
	@RequestMapping(value="payment/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Payment payment = new Payment();
		payment.setSort_no("0");
		model.addAttribute("payment", payment);
		return "payment/add";
	}
	
	//新增
	@RequestMapping(value="payment/add",method=RequestMethod.POST)
	public String insert(@Valid Payment payment,Errors errors,RedirectAttributes rAttr) throws Exception {			
		if(payment.getPay_code() != null && !"".equals(payment.getPay_code())) {
			Map pMap = new HashMap();
			pMap.put("pay_code", payment.getPay_code());
			List payList = paymentService.isPaycodeExist(pMap);
			if(payList != null && payList.size() > 0) {
				errors.rejectValue("pay_code", null, "支付方式编码已存在");
			}
		}
		if(payment.getSort_no() == null || "".equals(payment.getSort_no().trim())) {
			payment.setSort_no("0");
		}
		if (errors.hasErrors()){
	           return "payment/add";
		}
		paymentService.insert(payment);
		operatePrompt(rAttr, "新增支付方式成功");
		return "redirect:"+basePath()+"payment/index.action";
	}
	
	//进入修改
	@RequestMapping(value="payment/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Payment payment = paymentService.getByPk(id);
		model.addAttribute("payment", payment);
		return "payment/update";
	}
	
	//修改
	@RequestMapping(value="payment/update",method=RequestMethod.POST)
	public String update(@Valid Payment payment,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "payment/update";
		}
		paymentService.update(payment);
		operatePrompt(rAttr, "修改支付方式成功");
		return "redirect:"+basePath()+"payment/index.action";
	}
	
	//删除
	@RequestMapping(value="payment/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		paymentService.delete(id);
		operatePrompt(rAttr, "删除支付方式成功");
		return "redirect:"+basePath()+"payment/index.action";
	}
	
}

