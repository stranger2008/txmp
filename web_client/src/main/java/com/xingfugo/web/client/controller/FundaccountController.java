package com.xingfugo.web.client.controller;

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
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.FundaccountService;
import com.xingfugo.business.service.FundhistoryService;
import com.xingfugo.business.service.FundrechargeService;
import com.xingfugo.business.service.FundwithdrawService;
import com.xingfugo.util.RandomStrUtil;
import com.xingfugo.web.client.common.SessionUtil;

/**
 * @function 功能 会员资金表Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:24:02 CST 2014
 */
 
@Controller
public class FundaccountController extends BaseController{
	
	//未完成-充值状态
	private final static String FUNDRECHARGE_STATUS_UNFINISH = "0";
	//个人-会员类型-资金账户
	private static final String PERSONAL_CUST_TYPE_OF_FUNDACCOUNT = "0";
	//可用-状态-资金账户
	private static final String ENABLED_STATUS_OF_FUNDACCOUNT = "0";
	
	//充值错误-账户不正常
	private final static String FUNDRECHARGE_STATUS_ERROR = "1";
	
	//个人-会员类型-资金账户
	private static final String FUNDACCOUNT_TYPE_PERSONAL = "0";
	
	//提现状体-待审核
	private static final String FUNDWITHDRAW_STATUS_WAIT = "0";
	
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
	public String index(ModelMap model) throws Exception {
		String user_id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(user_id), PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
		if(fundaccount == null) {
			//初始化资金账号
			fundaccount = new Fundaccount();
			fundaccount.setAccount_no(RandomStrUtil.getNumberRand("18"));
			fundaccount.setCust_id(Integer.valueOf(user_id));
			fundaccount.setCust_type(PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
			fundaccount.setEnabled(ENABLED_STATUS_OF_FUNDACCOUNT);
			fundaccount.setFreeze_num(BigDecimal.ZERO);
			fundaccount.setFund_num(BigDecimal.ZERO);
			fundaccount.setUse_num(BigDecimal.ZERO);
			fundaccount.setIn_date(new Date());
			fundaccountService.insert(fundaccount);
		}
		model.addAttribute("fundaccount", fundaccount);
		return "fundaccount/index";
	}
	
	//账户余额
	@RequestMapping(value="fundaccount/balance")
	public String balance(FundhistoryQueryForm fundhistoryQueryForm, ModelMap model) throws Exception {
		String user_id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		
		//账户资金
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(user_id), PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
		if(fundaccount == null) {
			//初始化资金账号
			fundaccount = new Fundaccount();
			fundaccount.setAccount_no(RandomStrUtil.getNumberRand("18"));
			fundaccount.setCust_id(Integer.valueOf(user_id));
			fundaccount.setCust_type(PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
			fundaccount.setEnabled(ENABLED_STATUS_OF_FUNDACCOUNT);
			fundaccount.setFreeze_num(BigDecimal.ZERO);
			fundaccount.setFund_num(BigDecimal.ZERO);
			fundaccount.setUse_num(BigDecimal.ZERO);
			fundaccount.setIn_date(new Date());
			fundaccountService.insert(fundaccount);
		}
		//消费记录
		fundhistoryQueryForm.setAccount_no(fundaccount.getAccount_no());
		PageResult pageResult = fundhistoryService.getListByPage(fundhistoryQueryForm);
		
		model.addAttribute("fundaccount", fundaccount);
		model.addAttribute("pageResult", pageResult);
		return "fundaccount/balance";
	}
	
	//充值
	@RequestMapping(value="fundaccount/recharge", method=RequestMethod.GET)
	public String recharge(ModelMap model) throws Exception {
		String user_id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(user_id), PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
		
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
		fundrecharge = fundaccountService.rechargeFundAccount(fundrecharge.getAccount_no(), fund_num);
		if(FUNDRECHARGE_STATUS_ERROR.equals(fundrecharge.getIs_enabled())) {
			errors.rejectValue("fund_num", null, "资金账户不可用,暂不能充值");
		     return "fundaccount/recharge";
		}
		return "redirect:"+basePath()+"fundaccount/recharge-payment.action?order_no=" + fundrecharge.getOrder_no();
	}
	
	@RequestMapping(value="fundaccount/withdraw", method=RequestMethod.GET)
	public String withdraw(ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(this.getRequest(),  Constants.SESSION_USER_ID);
		//个人资金账号
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), FUNDACCOUNT_TYPE_PERSONAL);
		
		Fundwithdraw fundwithdraw = new Fundwithdraw();
		fundwithdraw.setAccount_no(fundaccount.getAccount_no());
		fundwithdraw.setFund_use_num(fundaccount.getUse_num());
		model.addAttribute("fundwithdraw", fundwithdraw);
		
		Map chinapay_banks = this.commparaService.getSelectMapByParacode(true,CHINAPAY_BANK);
		model.addAttribute("chinapay_banks", chinapay_banks);
		
		return "fundaccount/withdraw";
	}
	
	@RequestMapping(value="fundaccount/withdraw", method=RequestMethod.POST)
	public String withdraw(@Valid Fundwithdraw fundwithdraw, Errors errors, ModelMap model) throws Exception {
		if (errors.hasErrors()){
			Map chinapay_banks = this.commparaService.getSelectMapByParacode(true,CHINAPAY_BANK);
			model.addAttribute("chinapay_banks", chinapay_banks);
			return "fundaccount/withdraw";
		}
		String cust_id = SessionUtil.getString(this.getRequest(),  Constants.SESSION_USER_ID);
		//个人资金账号
		Fundaccount fundaccount = fundaccountService.getFundaccountByCustidAndCusttype(Integer.parseInt(cust_id), FUNDACCOUNT_TYPE_PERSONAL);
		
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
		fundwithdraw.setUser_type(FUNDACCOUNT_TYPE_PERSONAL);
		fundwithdraw.setStatus(FUNDWITHDRAW_STATUS_WAIT);
		
		Map<String, Object> result = fundwithdrawService.applyFundwithdrawOfUser(fundwithdraw);
		String resCode = (String) result.get("resCode");
		model.put("statusCode", resCode);
		
		return "fundaccount/withdraw-finished";
	}
	
}

