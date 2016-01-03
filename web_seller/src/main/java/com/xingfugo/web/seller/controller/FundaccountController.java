package com.xingfugo.web.seller.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.Fundrecharge;
import com.xingfugo.business.module.Fundwithdraw;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.FundhistoryQueryForm;
import com.xingfugo.business.module.query.FundwithdrawQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.FundaccountService;
import com.xingfugo.business.service.FundhistoryService;
import com.xingfugo.business.service.FundrechargeService;
import com.xingfugo.business.service.FundwithdrawService;
import com.xingfugo.util.RandomStrUtil;
import com.xingfugo.web.seller.common.SessionUtil;

/**
 * @function 功能 会员资金表Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:24:02 CST 2014
 */
 
@Controller
public class FundaccountController extends BaseController{
	
	//未完成-充值状态
	private final static String FUNDRECHARGE_STATUS_UNFINISH = "0";
	//企业-会员类型-资金账户
	private static final String FUNDACCOUNT_TYPE_ENTERPRISE = "1";
	
	//提现状体-待审核
	private static final String FUNDWITHDRAW_STATUS_WAIT = "0";
	//可用-状态-资金账户
	private static final String ENABLED_STATUS_OF_FUNDACCOUNT = "0";
	
	//银联支付代付银行
	private static final String CHINAPAY_BANK = "chinapay_bank";
	
	@Autowired
	private FundaccountService fundaccountService;
	@Autowired
	private FundhistoryService fundhistoryService;
	@Autowired
	private FundrechargeService fundrechargeService;
	@Autowired
	private FundwithdrawService fundwithdrawService;
	@Autowired
	private CommparaService commparaService;
	
	//账户总览
	@RequestMapping(value="fundaccount/index")
	public String index(FundhistoryQueryForm fundhistoryQueryForm, ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(this.getRequest(),  Constants.SESSION_CUST_ID);
		if(cust_id == null) {
			return "login";
		}
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), FUNDACCOUNT_TYPE_ENTERPRISE);
		
		if(fundaccount == null) {
			//初始化资金账号
			fundaccount = new Fundaccount();
			fundaccount.setAccount_no(RandomStrUtil.getNumberRand("18"));
			fundaccount.setCust_id(Integer.parseInt(cust_id));
			fundaccount.setCust_type(FUNDACCOUNT_TYPE_ENTERPRISE);
			fundaccount.setEnabled(ENABLED_STATUS_OF_FUNDACCOUNT);
			fundaccount.setFreeze_num(BigDecimal.ZERO);
			fundaccount.setFund_num(BigDecimal.ZERO);
			fundaccount.setUse_num(BigDecimal.ZERO);
			fundaccount.setIn_date(new Date());
			fundaccountService.insert(fundaccount);
		}
		
		//消费记录
		fundhistoryQueryForm.setAccount_no(fundaccount.getAccount_no());
		fundhistoryQueryForm.setCust_type(fundaccount.getCust_type());
		PageResult pageResult = fundhistoryService.getListByPage(fundhistoryQueryForm);
		
		//提现记录
		FundwithdrawQueryForm fundwithdrawQueryForm = new FundwithdrawQueryForm();
		fundwithdrawQueryForm.setUser_id(Integer.parseInt(cust_id));
		PageResult fundwithdraws = fundwithdrawService.getFundwithdrawListOfMemberByPage(fundwithdrawQueryForm);
		
		model.addAttribute("fundaccount", fundaccount);
		model.addAttribute("pageResult", pageResult);
//		model.addAttribute("fundwithdraws", fundwithdraws);
		return "fundaccount/index";
	}
	
	//账户余额
	@RequestMapping(value="fundaccount/balance")
	public String balance(FundhistoryQueryForm fundhistoryQueryForm, ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(this.getRequest(),  Constants.SESSION_CUST_ID);
		
		//账户资金
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), FUNDACCOUNT_TYPE_ENTERPRISE);
		
		if(fundaccount == null) {
			//初始化资金账号
			fundaccount = new Fundaccount();
			fundaccount.setAccount_no(RandomStrUtil.getNumberRand("18"));
			fundaccount.setCust_id(Integer.parseInt(cust_id));
			fundaccount.setCust_type(FUNDACCOUNT_TYPE_ENTERPRISE);
			fundaccount.setEnabled(ENABLED_STATUS_OF_FUNDACCOUNT);
			fundaccount.setFreeze_num(BigDecimal.ZERO);
			fundaccount.setFund_num(BigDecimal.ZERO);
			fundaccount.setUse_num(BigDecimal.ZERO);
			fundaccount.setIn_date(new Date());
			fundaccountService.insert(fundaccount);
		}
		
		//消费记录
		fundhistoryQueryForm.setAccount_no(fundaccount.getAccount_no());
		fundhistoryQueryForm.setCust_type(fundaccount.getCust_type());
		PageResult pageResult = fundhistoryService.getListByPage(fundhistoryQueryForm);
		
		model.addAttribute("fundaccount", fundaccount);
		model.addAttribute("pageResult", pageResult);
		return "fundaccount/balance";
	}
	
	//充值
	@RequestMapping(value="fundaccount/recharge", method=RequestMethod.GET)
	public String recharge(ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(this.getRequest(),  Constants.SESSION_CUST_ID);
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), FUNDACCOUNT_TYPE_ENTERPRISE);
		
		Fundrecharge fundrecharge = new Fundrecharge();
		fundrecharge.setAccount_no(fundaccount.getAccount_no());
		model.addAttribute("fundrecharge", fundrecharge);
		return "fundaccount/recharge";
	}
	
	//充值
	@RequestMapping(value="fundaccount/recharge", method=RequestMethod.POST)
	public String recharge(@Valid Fundrecharge fundrecharge, Errors errors, RedirectAttributes rAttr) throws Exception {
		BigDecimal fund_num = fundrecharge.getFund_num();
		BigDecimal zero = new BigDecimal(0);
		if(fund_num != null && fund_num.compareTo(zero) == 0) {
			errors.rejectValue("fund_num", null, "只能填写大于0的金额");
		}
		if (errors.hasErrors()){
	        return "fundaccount/recharge";
		}
		
		fundrecharge.setOrder_no(RandomStrUtil.getNumberRand("16"));
		fundrecharge.setIs_enabled(FUNDRECHARGE_STATUS_UNFINISH);
		fundrecharge.setIn_date(new Date());
		fundrechargeService.insert(fundrecharge);
		return "redirect:"+basePath()+"fundaccount/recharge-payment.action?order_no=" + fundrecharge.getOrder_no();
	}
	
	@RequestMapping(value="fundaccount/withdraw", method=RequestMethod.GET)
	public String withdraw(ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(this.getRequest(),  Constants.SESSION_CUST_ID);
		//企业资金账号
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), FUNDACCOUNT_TYPE_ENTERPRISE);
		
		Fundwithdraw fundwithdraw = new Fundwithdraw();
		fundwithdraw.setAccount_no(fundaccount.getAccount_no());
		fundwithdraw.setFund_use_num(fundaccount.getUse_num());
		model.addAttribute("fundwithdraw", fundwithdraw);
		
		Map chinapay_banks = this.commparaService.getSelectMapByParacode(true,CHINAPAY_BANK);
		model.addAttribute("chinapay_banks", chinapay_banks);
		
		return "fundaccount/withdraw";
	}
	
	@RequestMapping(value="fundaccount/withdraw", method=RequestMethod.POST)
	public String withdraw(@Valid Fundwithdraw fundwithdraw, Errors errors, RedirectAttributes rAttr, ModelMap model) throws Exception {
		if (errors.hasErrors()){
			Map chinapay_banks = this.commparaService.getSelectMapByParacode(true,CHINAPAY_BANK);
			model.addAttribute("chinapay_banks", chinapay_banks);
			
			return "fundaccount/withdraw";
		}
		String cust_id = SessionUtil.getString(this.getRequest(),  Constants.SESSION_CUST_ID);
		//企业资金账号
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), FUNDACCOUNT_TYPE_ENTERPRISE);
		
		if(fundwithdraw.getTrans_amt().compareTo(fundaccount.getUse_num()) > 0) {
			errors.rejectValue("trans_amt", null, "最多可以提取" + fundaccount.getUse_num() + "元");
		}
		if (errors.hasErrors()){
			fundwithdraw.setAccount_no(fundaccount.getAccount_no());
			fundwithdraw.setFund_use_num(fundaccount.getUse_num());
			
			Map chinapay_banks = this.commparaService.getSelectMapByParacode(true,CHINAPAY_BANK);
			model.addAttribute("chinapay_banks", chinapay_banks);
			return "fundaccount/withdraw";
		}
		fundwithdraw.setAccount_no(fundaccount.getAccount_no());
		fundwithdraw.setOrder_no(RandomStrUtil.getNumberRand("16"));
		fundwithdraw.setUser_id(Integer.valueOf(cust_id));
		fundwithdraw.setUser_type(FUNDACCOUNT_TYPE_ENTERPRISE);
		fundwithdraw.setStatus(FUNDWITHDRAW_STATUS_WAIT);
		
		Map<String, Object> result = fundwithdrawService.applyFundwithdrawOfMember(fundwithdraw);
		String resCode = (String) result.get("resCode");
		if("0200".equals(resCode)) {
			operatePrompt(rAttr, "资金提现申请成功");
		} else {
			operatePrompt(rAttr, "资金提现申请失败,错误码: " + resCode);
		}
		
		return "redirect:"+basePath()+"fundaccount/index.action";
	}
	
}

