package com.xingfugo.business.dao;

import com.xingfugo.business.module.Fundrecharge;

/**
 * @function 功能 会员资金充值表dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Wed Oct 08 14:29:02 CST 2014
 */
public interface FundrechargeDao extends GenericDao<Fundrecharge,String>{
	
	/**
	 * 根据充值单号查询充值信息
	 * @param order_no
	 * @return
	 */
	public Fundrecharge getFundrechargeByOrder_no(String order_no);
	
	/**
	 * 更新充值状态
	 * @param fundrecharge
	 */
	public void updateFundrechargeStatus(Fundrecharge fundrecharge);
	
}













