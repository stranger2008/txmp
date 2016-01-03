package com.xingfugo.web.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Fundwithdraw;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.FundwithdrawQueryForm;
import com.xingfugo.business.service.FundaccountService;
import com.xingfugo.business.service.FundwithdrawService;
import com.xingfugo.web.admin.chinapay.ChinapayPayout;
import com.xingfugo.web.admin.chinapay.ChinapayUtil;
import com.xingfugo.web.admin.common.SessionUtil;

/**
 * @function 功能 资金提现Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Thu Oct 16 16:18:00 CST 2014
 */
 
@Controller
public class FundwithdrawController extends BaseController{
	
	//个人资金账户
	private static final String FUNDACCOUNT_TYPE_PERSONAL = "0";
	//商家资金账户
	private static final String FUNDACCOUNT_TYPE_ENTERPRISE = "1";
	
	//提现审核状态-待审核
	private static final String FUNDACCOUNT_STATUS_UNAUDIT = "0";
	//提现审核状态-拒绝
	private static final String FUNDACCOUNT_STATUS_REJECT = "1";
	//提现审核状态-同意(提现成功)
	private static final String FUNDACCOUNT_STATUS_AGREE = "2";
	
	@Autowired
	private FundwithdrawService fundwithdrawService;
	@Autowired
	private FundaccountService fundaccountService;
	
	//个人资金提现
	@RequestMapping(value="fundwithdraw/memberuser-index")
	public String memberuserIndex(FundwithdrawQueryForm fundwithdrawQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = fundwithdrawService.getFundwithdrawListOfMemberuserByPage(fundwithdrawQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("fundwithdrawQueryForm", fundwithdrawQueryForm);
		return "fundwithdraw/memberuser-index";
	}
	
	//商家资金提现
	@RequestMapping(value="fundwithdraw/member-index")
	public String memberIndex(FundwithdrawQueryForm fundwithdrawQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = fundwithdrawService.getFundwithdrawListOfMemberByPage(fundwithdrawQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("fundwithdrawQueryForm", fundwithdrawQueryForm);
		return "fundwithdraw/member-index";
	}
	
	//查看个人
	@RequestMapping(value="fundwithdraw/memberuser-view",method=RequestMethod.GET)
	public String memberuserView(Integer trade_id, String user, ModelMap model) throws Exception {
		Map<String, Object> fundwithdraw = fundwithdrawService.getFundwithdrawDetailOfMemberuserByTrade_id(trade_id);
		model.addAttribute("fundwithdraw", fundwithdraw);
		return "fundwithdraw/memberuser-view";
	}
	
	//查看商家
	@RequestMapping(value="fundwithdraw/member-view",method=RequestMethod.GET)
	public String memberView(Integer trade_id, String user, ModelMap model) throws Exception {
		Map<String, Object> fundwithdraw = fundwithdrawService.getFundwithdrawDetailOfMemberByTrade_id(trade_id);
		model.addAttribute("fundwithdraw", fundwithdraw);
		return "fundwithdraw/member-view";
	}
	
	//审批个人
	@RequestMapping(value="fundwithdraw/memberuser-audit",method=RequestMethod.GET)
	public String memberuserAudit(Integer trade_id, String user, ModelMap model) throws Exception {
		Map<String, Object> fundwithdrawmap = fundwithdrawService.getFundwithdrawDetailOfMemberuserByTrade_id(trade_id);
		Fundwithdraw fundwithdraw = new Fundwithdraw();
		fundwithdraw.setTrade_id((Integer)fundwithdrawmap.get("trade_id"));
		model.addAttribute("fundwithdrawmap", fundwithdrawmap);
		model.addAttribute("fundwithdraw", fundwithdraw);
		return "fundwithdraw/memberuser-audit";
	}
	
	//审批商家
	@RequestMapping(value="fundwithdraw/member-audit",method=RequestMethod.GET)
	public String memberAudit(Integer trade_id, String user, ModelMap model) throws Exception {
		Map<String, Object> fundwithdrawmap = fundwithdrawService.getFundwithdrawDetailOfMemberByTrade_id(trade_id);
		Fundwithdraw fundwithdraw = new Fundwithdraw();
		fundwithdraw.setTrade_id((Integer)fundwithdrawmap.get("trade_id"));
		model.addAttribute("fundwithdrawmap", fundwithdrawmap);
		model.addAttribute("fundwithdraw", fundwithdraw);
		return "fundwithdraw/member-audit";
	}
	
	//审批(商家)
	@RequestMapping(value="fundwithdraw/member-audit",method=RequestMethod.POST)
	public String memberAudit(Fundwithdraw fundwithdraw, Errors errors, RedirectAttributes rAttr, ModelMap model) throws Exception {
		Integer trade_id = fundwithdraw.getTrade_id();
		if(trade_id == null) {
			operatePrompt(rAttr, "获取信息失败,请刷新后重试");
			return "redirect:" + basePath() + "fundwithdraw/member-index.action";
		}
		String status = fundwithdraw.getStatus();
		if((!FUNDACCOUNT_STATUS_AGREE.equals(status)) && (!FUNDACCOUNT_STATUS_REJECT.equals(status))) {
			operatePrompt(rAttr, "审核状态不正确,请刷新后重试");
			return "redirect:" + basePath() + "fundwithdraw/member-index.action";
		}
		String remark = fundwithdraw.getRemark();
		if(FUNDACCOUNT_STATUS_REJECT.equals(status) && (remark == null || remark.trim().length() == 0)) {
			errors.rejectValue("remark", null, "备注不能为空");
		}
		if (errors.hasErrors()){
			Map<String, Object> fundwithdrawmap = fundwithdrawService.getFundwithdrawDetailOfMemberByTrade_id(trade_id);
			model.addAttribute("fundwithdrawmap", fundwithdrawmap);
			return "fundwithdraw/member-audit";
		}
		String user_id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		if(user_id == null) {
			return "login";
		}
		Fundwithdraw fundwithdraw1 = fundwithdrawService.getByPk(String.valueOf(trade_id));
		
		if(!FUNDACCOUNT_STATUS_UNAUDIT.equals(fundwithdraw1.getStatus())) {
			operatePrompt(rAttr, "审核状态不正确,请刷新后重试");
			return "redirect:" + basePath() + "fundwithdraw/member-index.action";
		}
		
		String alertMsg = "";
		
		if(FUNDACCOUNT_STATUS_AGREE.equals(status)) {
			ChinapayPayout payout = new ChinapayPayout();
			payout.setMerSeqId(fundwithdraw1.getOrder_no());
			payout.setCardNo(fundwithdraw1.getCard_no());
			payout.setUserName(fundwithdraw1.getUsr_name());
			payout.setOpenBank(fundwithdraw1.getOpen_bank());
			payout.setProv(fundwithdraw1.getProv());
			payout.setCity(fundwithdraw1.getCity());
			payout.setTransAmt(ChinapayUtil.transAmt2ChinapayTransAmt(fundwithdraw1.getTrans_amt()));
			payout.setSubBank(fundwithdraw1.getSub_bank());
			
			String responseCode = ChinapayUtil.toChinapayPayout(ChinapayUtil.getChinapayPayout(payout));
			
			
			if("0300".equals(responseCode)) {
				String resCode = fundwithdrawService.auditMemberFundwithdraw(fundwithdraw, Integer.parseInt(user_id));
				//同意并成功
				if(FUNDACCOUNT_STATUS_AGREE.equals(status) && "0200".equals(resCode)) {
					alertMsg = "审核成功";
				} else {
					alertMsg = "审核失败";
				}
			} else {
				if("0301".equals(responseCode)) {
					alertMsg = "创建商家签名私钥错误,审核失败";
				} else if("0302".equals(responseCode)) {
					alertMsg = "创建银联签名公钥失败,审核失败";
				} else if("0303".equals(responseCode)) {
					alertMsg = "签名数据不一致,审核失败";
				} else if("0304".equals(responseCode)) {
					alertMsg = "交易字段长度、格式错误,审核失败";
				} else if("0305".equals(responseCode)) {
					alertMsg = "验签错误,审核失败";
				} else if("0306".equals(responseCode)) {
					alertMsg = "手续费计算出错,审核失败";
				} else if("0307".equals(responseCode)) {
					alertMsg = "商户备付金帐户金额不足,审核失败";
				} else if("0308".equals(responseCode)) {
					alertMsg = "操作拒绝,审核失败";
				} else if("0309".equals(responseCode)) {
					alertMsg = "重复交易,审核失败";
				} else {
					alertMsg = "未知错误,审核失败";
				}
			}
		} else {
			String resCode = fundwithdrawService.auditMemberFundwithdraw(fundwithdraw, Integer.parseInt(user_id));
			//同意并成功
			if(FUNDACCOUNT_STATUS_AGREE.equals(status) && "0200".equals(resCode)) {
				alertMsg = "审核成功";
			} else {
				alertMsg = "审核失败";
			}
		}
		
		operatePrompt(rAttr, alertMsg);
		return "redirect:" + basePath() + "fundwithdraw/member-index.action";
	}
	
	//审批(个人)
	@RequestMapping(value="fundwithdraw/memberuser-audit",method=RequestMethod.POST)
	public String memberuserAudit(Fundwithdraw fundwithdraw, Errors errors, RedirectAttributes rAttr, ModelMap model) throws Exception {
		Integer trade_id = fundwithdraw.getTrade_id();
		if(trade_id == null) {
			operatePrompt(rAttr, "获取信息失败,请刷新后重试");
			return "redirect:" + basePath() + "fundwithdraw/memberuser-index.action";
		}
		String status = fundwithdraw.getStatus();
		if((!FUNDACCOUNT_STATUS_AGREE.equals(status)) && (!FUNDACCOUNT_STATUS_REJECT.equals(status))) {
			operatePrompt(rAttr, "审核状态不正确,请刷新后重试");
			return "redirect:" + basePath() + "fundwithdraw/memberuser-index.action";
		}
		String remark = fundwithdraw.getRemark();
		if(FUNDACCOUNT_STATUS_REJECT.equals(status) && (remark == null || remark.trim().length() == 0)) {
			errors.rejectValue("remark", null, "备注不能为空");
		}
		if (errors.hasErrors()){
			Map<String, Object> fundwithdrawmap = fundwithdrawService.getFundwithdrawDetailOfMemberuserByTrade_id(trade_id);
			model.addAttribute("fundwithdrawmap", fundwithdrawmap);
			return "fundwithdraw/memberuser-audit";
		}
		String user_id = SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID);
		if(user_id == null) {
			return "login";
		}
		Fundwithdraw fundwithdraw1 = fundwithdrawService.getByPk(String.valueOf(trade_id));
		if(!FUNDACCOUNT_STATUS_UNAUDIT.equals(fundwithdraw1.getStatus())) {
			operatePrompt(rAttr, "审核状态不正确,请刷新后重试");
			return "redirect:" + basePath() + "fundwithdraw/memberuser-index.action";
		}
		String alertMsg = "";
		
		if(FUNDACCOUNT_STATUS_AGREE.equals(status)) {
			ChinapayPayout payout = new ChinapayPayout();
			payout.setMerSeqId(fundwithdraw1.getOrder_no());
			payout.setCardNo(fundwithdraw1.getCard_no());
			payout.setUserName(fundwithdraw1.getUsr_name());
			payout.setOpenBank(fundwithdraw1.getOpen_bank());
			payout.setProv(fundwithdraw1.getProv());
			payout.setCity(fundwithdraw1.getCity());
			payout.setTransAmt(ChinapayUtil.transAmt2ChinapayTransAmt(fundwithdraw1.getTrans_amt()));
			payout.setSubBank(fundwithdraw1.getSub_bank());
			
			String responseCode = ChinapayUtil.toChinapayPayout(ChinapayUtil.getChinapayPayout(payout));
			
			
			if("0300".equals(responseCode)) {
				String resCode = fundwithdrawService.auditMemberuserFundwithdraw(fundwithdraw, Integer.parseInt(user_id));
				//同意并成功
				if(FUNDACCOUNT_STATUS_AGREE.equals(status) && "0200".equals(resCode)) {
					alertMsg = "审核成功";
				} else {
					alertMsg = "审核失败";
				}
			} else {
				if("0301".equals(responseCode)) {
					alertMsg = "创建商家签名私钥错误,审核失败";
				} else if("0302".equals(responseCode)) {
					alertMsg = "创建银联签名公钥失败,审核失败";
				} else if("0303".equals(responseCode)) {
					alertMsg = "签名数据不一致,审核失败";
				} else if("0304".equals(responseCode)) {
					alertMsg = "交易字段长度、格式错误,审核失败";
				} else if("0305".equals(responseCode)) {
					alertMsg = "验签错误,审核失败";
				} else if("0306".equals(responseCode)) {
					alertMsg = "手续费计算出错,审核失败";
				} else if("0307".equals(responseCode)) {
					alertMsg = "商户备付金帐户金额不足,审核失败";
				} else if("0308".equals(responseCode)) {
					alertMsg = "操作拒绝,审核失败";
				} else if("0309".equals(responseCode)) {
					alertMsg = "重复交易,审核失败";
				} else {
					alertMsg = "未知错误,审核失败";
				}
			}
		} else {
			String resCode = fundwithdrawService.auditMemberFundwithdraw(fundwithdraw, Integer.parseInt(user_id));
			//同意并成功
			if(FUNDACCOUNT_STATUS_AGREE.equals(status) && "0200".equals(resCode)) {
				alertMsg = "审核成功";
			} else {
				alertMsg = "审核失败";
			}
		}
		
		operatePrompt(rAttr, alertMsg);
		return "redirect:" + basePath() + "fundwithdraw/memberuser-index.action";
	}
	
}

