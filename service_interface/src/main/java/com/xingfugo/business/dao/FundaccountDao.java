package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

/**
 * @function 功能 会员资金表dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Wed Oct 08 14:24:02 CST 2014
 */
public interface FundaccountDao extends GenericDao<Fundaccount,String>{
	
	/**
	 * 根据用户/企业id查询资金账户
	 * @param cust_id
	 * @return
	 */
	public Fundaccount getFundaccountByCustidAndCusttype(Map<String, Object> pmap);
	
	/**
	 * 更新账户总金额和可用金额
	 * @param fundaccount
	 */
	public void updateFund_numAndUse_numOfFundaccount(Fundaccount fundaccount);
	
	/**
	 * 更新账户总金额和冻结金额
	 * @param fundaccount
	 */
	public void updateFund_numAndFreeze_numOfFundaccount(Fundaccount fundaccount);
	
	/**
	 * 更新账户可用金额和可用
	 * @param fundaccount
	 */
	public void updateUse_numAndFreeze_numOfFundaccount(Fundaccount fundaccount);
	
	/**
	 * 更新账户总金额,可用金额,冻结金额
	 * @param fundaccount
	 */
	public void updateFund_numAndUse_numAndFreeze_numOfFundaccount(Fundaccount fundaccount);
	
	/**
	 * 更新账户状态
	 * @param fundaccount
	 */
	public void updateAccountStatus(Fundaccount fundaccount);
	
	/**
	 * 查询个人会员资金列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public List<Map<String, Object>> getFundaccountListOfMemberuserByPage(BasePageForm basePageForm);
	
	/**
	 * 查询企业会员资金列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public List<Map<String, Object>> getFundaccountListOfMemberByPage(BasePageForm basePageForm);
	
}













