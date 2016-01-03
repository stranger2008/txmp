package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Fundwithdraw;
import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 资金提现dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Thu Oct 16 16:18:00 CST 2014
 */
public interface FundwithdrawDao extends GenericDao<Fundwithdraw,String>{
	
	/**
	 * 插入提现申请 并获取id
	 * @param fundwithdraw
	 * @return
	 */
	public Integer insertGetId(Fundwithdraw fundwithdraw);
	
	/**
	 * 查询个人会员提现列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public List<Map<String, Object>> getFundwithdrawListOfMemberuserByPage(BasePageForm basePageForm);
	
	/**
	 * 查询商家会员提现列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public List<Map<String, Object>> getFundwithdrawListOfMemberByPage(BasePageForm basePageForm);
	
	/**
	 * 查询商家提现详情
	 * @param trade_id
	 * @return
	 */
	public Map<String, Object> getFundwithdrawDetailOfMemberByTrade_id(Integer trade_id);
	
	/**
	 * 查询个人提现详情
	 * @param trade_id
	 * @return
	 */
	public Map<String, Object> getFundwithdrawDetailOfMemberuserByTrade_id(Integer trade_id);
	
	/**
	 * 审核提现记录
	 * @param fundwithdraw
	 */
	public void auditFundwithdraw(Fundwithdraw fundwithdraw);
}













