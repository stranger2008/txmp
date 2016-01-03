package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.FundrechargeDao;
import com.xingfugo.business.module.Fundrecharge;

/**
 * @function 功能 会员资金充值表Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:29:02 CST 2014
 */

@Service
public class FundrechargeService extends GenericService<Fundrecharge,String>{

	private FundrechargeDao fundrechargeDao;
	
	public FundrechargeService() {}
	
	@Autowired
	public FundrechargeService(FundrechargeDao fundrechargeDao) {
		super(fundrechargeDao);
		this.fundrechargeDao = fundrechargeDao;
	}
	
	/**
	 * 根据充值单号查询充值信息
	 * @param order_no
	 * @return
	 */
	public Fundrecharge getFundrechargeByOrder_no(String order_no) {
		return fundrechargeDao.getFundrechargeByOrder_no(order_no);
	}

}

