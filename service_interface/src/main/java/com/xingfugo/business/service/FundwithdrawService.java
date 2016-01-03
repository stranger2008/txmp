package com.xingfugo.business.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.FundwithdrawDao;
import com.xingfugo.business.module.Fundwithdraw;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

/**
 * @function 功能 资金提现Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Thu Oct 16 16:18:00 CST 2014
 */

@Service
public class FundwithdrawService extends GenericService<Fundwithdraw,String>{
	

	
	//提现审核状态-拒绝
	private static final String FUNDACCOUNT_STATUS_REJECT = "1";
	//提现审核状态-同意(提现成功)
	private static final String FUNDACCOUNT_STATUS_AGREE = "2";

	@Autowired
	private FundaccountService fundaccountService;
	
	private FundwithdrawDao fundwithdrawDao;
	
	public FundwithdrawService() {}
	
	@Autowired
	public FundwithdrawService(FundwithdrawDao fundwithdrawDao) {
		super(fundwithdrawDao);
		this.fundwithdrawDao = fundwithdrawDao;
	}

	/**
	 * 商家申请提现(冻结资金)
	 * @param fundwithdraw
	 * @return
	 */
	@Transactional
	public Map<String, Object> applyFundwithdrawOfMember(Fundwithdraw fundwithdraw) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		
		String accountNo = fundwithdraw.getAccount_no();
		BigDecimal freezeFundNum = fundwithdraw.getTrans_amt();
		String actionDesc = "申请提现, 冻结资金¥" + freezeFundNum + ", 流水号: " + fundwithdraw.getOrder_no();
		Integer cust_id = fundwithdraw.getUser_id();
		String resCode = this.fundaccountService.freezeMemberFundaccount(accountNo, freezeFundNum, actionDesc, cust_id);
		resmap.put("resCode", resCode);
		if("0200".equals(resCode)) {
			fundwithdrawDao.insertGetId(fundwithdraw);
			Integer trade_id = fundwithdraw.getTrade_id();
			resmap.put("trade_id", trade_id);
		}
		return resmap;
	}
	
	/**
	 * 个人申请提现(冻结资金)
	 * @param fundwithdraw
	 * @return
	 */
	@Transactional
	public Map<String, Object> applyFundwithdrawOfUser(Fundwithdraw fundwithdraw) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		
		String accountNo = fundwithdraw.getAccount_no();
		BigDecimal freezeFundNum = fundwithdraw.getTrans_amt();
		String actionDesc = "申请提现, 资金冻结¥" + freezeFundNum + "元, 流水号: " + fundwithdraw.getOrder_no();
		Integer user_id = fundwithdraw.getUser_id();
		String resCode = this.fundaccountService.freezeUserFundaccount(accountNo, freezeFundNum, actionDesc, user_id);
		resmap.put("resCode", resCode);
		if("0200".equals(resCode)) {
			fundwithdrawDao.insertGetId(fundwithdraw);
			Integer trade_id = fundwithdraw.getTrade_id();
			resmap.put("trade_id", trade_id);
		}
		return resmap;
	}
	
	/**
	 * 查询个人会员提现列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public PageResult getFundwithdrawListOfMemberuserByPage(BasePageForm basePageForm) {
		List<Map<String, Object>> list = fundwithdrawDao.getFundwithdrawListOfMemberuserByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		return result;
	}
	
	/**
	 * 查询商家会员提现列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public PageResult getFundwithdrawListOfMemberByPage(BasePageForm basePageForm) {
		List<Map<String, Object>> list = fundwithdrawDao.getFundwithdrawListOfMemberByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		return result;
	}
	
	/**
	 * 查询商家提现详情
	 * @param trade_id
	 * @return
	 */
	public Map<String, Object> getFundwithdrawDetailOfMemberByTrade_id(Integer trade_id) {
		return fundwithdrawDao.getFundwithdrawDetailOfMemberByTrade_id(trade_id);
	}
	
	/**
	 * 查询个人提现详情
	 * @param trade_id
	 * @return
	 */
	public Map<String, Object> getFundwithdrawDetailOfMemberuserByTrade_id(Integer trade_id) {
		return fundwithdrawDao.getFundwithdrawDetailOfMemberuserByTrade_id(trade_id);
	}
	
	/**
	 * 
	 * @param fundwithdraw
	 * @param user_id
	 * @return
	 */
	@Transactional
	public String auditMemberFundwithdraw(Fundwithdraw fundwithdraw, Integer audit_user_id) {
		//流水号
		final Integer trade_id = fundwithdraw.getTrade_id();
		//状态
		final String status = fundwithdraw.getStatus();
		//备注
		final String remark = fundwithdraw.getRemark();
		//状态不正确
		
		fundwithdraw = this.getByPk(String.valueOf(trade_id));
		if(fundwithdraw == null) {
			return "0000";//失败
		}
		
		String account_no = fundwithdraw.getAccount_no();
		String order_no = fundwithdraw.getOrder_no();
		BigDecimal trans_amt = fundwithdraw.getTrans_amt();
		String actionDesc = null;
		
		//返回码
		String resCode = null;
		//拒绝
		if(FUNDACCOUNT_STATUS_REJECT.equals(status)) {
			actionDesc = "提现申请失败,资金解冻¥" + trans_amt + "元,流水号: " + order_no;
			resCode = fundaccountService.unfreezeMemberFundaccount(account_no, trans_amt, actionDesc, null);//audit_user_id无作用
		} else if(FUNDACCOUNT_STATUS_AGREE.equals(status)) {
			actionDesc = "提现申请通过,资金支出¥" + trans_amt + "元,流水号: " + order_no;
			resCode = fundaccountService.payByFreezeNumMemberFundaccount(account_no, trans_amt, actionDesc, null);
		}
		if("0200".equals(resCode)) {
			
			////////////去银联支付/////////////////////
			
			////////////去银联支付/////////////////////
			
			fundwithdraw.setStatus(status);
			fundwithdraw.setRemark(remark);
			fundwithdraw.setAudit_user_id(audit_user_id);
			fundwithdraw.setFinish_date(new Date());
			this.fundwithdrawDao.auditFundwithdraw(fundwithdraw);
		}
		
		return resCode;
	}
	
	/**
	 * 
	 * @param fundwithdraw
	 * @param user_id
	 * @return
	 */
	@Transactional
	public String auditMemberuserFundwithdraw(Fundwithdraw fundwithdraw, Integer audit_user_id) {
		//流水号
		final Integer trade_id = fundwithdraw.getTrade_id();
		//状态
		final String status = fundwithdraw.getStatus();
		//备注
		final String remark = fundwithdraw.getRemark();
		//状态不正确
		
		fundwithdraw = this.getByPk(String.valueOf(trade_id));
		if(fundwithdraw == null) {
			return "0000";//失败
		}
		
		String account_no = fundwithdraw.getAccount_no();
		String order_no = fundwithdraw.getOrder_no();
		BigDecimal trans_amt = fundwithdraw.getTrans_amt();
		String actionDesc = null;
		Integer user_id = fundwithdraw.getUser_id();
		
		//返回码
		String resCode = null;
		//拒绝
		if(FUNDACCOUNT_STATUS_REJECT.equals(status)) {
			actionDesc = "提现申请失败,资金解冻¥" + trans_amt + "元,流水号: " + order_no;
			resCode = fundaccountService.unfreezeUserFundaccount(account_no, trans_amt, actionDesc, null);
		} else if(FUNDACCOUNT_STATUS_AGREE.equals(status)) {
			actionDesc = "提现申请通过,资金支出¥" + trans_amt + "元,流水号: " + order_no;
			resCode = fundaccountService.payByFreezeNumUserFundaccount(account_no, trans_amt, actionDesc, null);
		}
		if("0200".equals(resCode)) {
			
			////////////去银联支付/////////////////////
			
			////////////去银联支付/////////////////////
			
			fundwithdraw.setStatus(status);
			fundwithdraw.setRemark(remark);
			fundwithdraw.setAudit_user_id(audit_user_id);
			fundwithdraw.setFinish_date(new Date());
			this.fundwithdrawDao.auditFundwithdraw(fundwithdraw);
		}
		
		return resCode;
	}
	
}

