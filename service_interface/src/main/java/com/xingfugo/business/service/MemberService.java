package com.xingfugo.business.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.MemberDao;
import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.Member;
import com.xingfugo.business.module.Shopconfig;
import com.xingfugo.util.RandomStrUtil;

/**
 * @function 功能 商家信息Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Sat Sep 20 14:01:11 CST 2014
 */

@Service
public class MemberService extends GenericService<Member,String>{

	//店铺状态未关闭
	private static final String SHOPCONFIG_CLOSED_NO = "0";
	//审核通过
	private static final String MEMBER_AUDIT_PASSED = "1";
	//企业-会员类型-资金账户
	private static final String ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT = "1";
	//可用-状态-资金账户
	private static final String ENABLED_STATUS_OF_FUNDACCOUNT = "0";
	
	private MemberDao memberDao;
	
	@Autowired
	public MemberService(MemberDao memberDao) {
		super(memberDao);
		this.memberDao = memberDao;
	}
	
	public MemberService() {
	}
	@Autowired
	private ShopconfigService shopconfigService;
	
	@Autowired
	private LevelinfoService levelinfoService;
	@Autowired
	private FundaccountService fundaccountService;
	
	//根据登录用户名称获取企业信息
	public Map getMemberByUsername(String user_name) {
		Map paramMap = new HashMap();
		paramMap.put("user_name", user_name);
		List list = memberDao.getList(paramMap);
		Map retMap = new HashMap();
		if(list != null && list.size()>0){
			retMap = (Map)list.get(0);
		}
		return retMap;
	}
	
	//修改密码
	public void updatePasswd(String cust_id,String passwd){
		Map map = new HashMap();
		map.put("cust_id", cust_id);
		map.put("passwd", passwd);
		this.memberDao.updatePasswd(map);
	}
	
	/**
	 * 查询商家信息带所属地区名称
	 * @param cust_id
	 * @return
	 * @author 陈显革 2014-09-20
	 */
	public Member selectMemberByIdWithAreaName(String cust_id) {
		return this.memberDao.selectMemberByIdWithAreaName(cust_id);
	}
	
	/**
	 * 审核商家信息
	 * @param member
	 * @author 陈显革 2014-09-22
	 */
	@Transactional
	public void auditMember(Member member) {
		this.memberDao.auditMember(member);
		//审核通过,初始化店铺信息,初始化商家资金账户
		if(MEMBER_AUDIT_PASSED.equals(member.getAudit_status())) {
			Shopconfig shopconfig = new Shopconfig();
			shopconfig.setCust_id(Integer.parseInt(member.getCust_id()));
			shopconfig.setIs_close(SHOPCONFIG_CLOSED_NO);
			shopconfigService.insert(shopconfig);
			
			//初始化资金账号
			Fundaccount fundaccount = new Fundaccount();
			fundaccount.setAccount_no(RandomStrUtil.getNumberRand("18"));
			fundaccount.setCust_id(Integer.parseInt(member.getCust_id()));
			fundaccount.setCust_type(ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT);
			fundaccount.setEnabled(ENABLED_STATUS_OF_FUNDACCOUNT);
			fundaccount.setFreeze_num(BigDecimal.ZERO);
			fundaccount.setFund_num(BigDecimal.ZERO);
			fundaccount.setUse_num(BigDecimal.ZERO);
			fundaccount.setIn_date(new Date());
			fundaccountService.insert(fundaccount);
		}
	}
	
	/**
	 * 管理员新增商家,初始化店铺信息
	 * @param member
	 */
	@Transactional
	public void insertMemberWithShopConfig(Member member) {
		memberDao.insertReturnPk(member);
		String cust_id = member.getCust_id();
		Shopconfig shopconfig = new Shopconfig();
		shopconfig.setCust_id(Integer.parseInt(cust_id));
		shopconfig.setIs_close(SHOPCONFIG_CLOSED_NO);
		shopconfigService.insert(shopconfig);
		
		//初始化资金账号
		Fundaccount fundaccount = new Fundaccount();
		fundaccount.setAccount_no(RandomStrUtil.getNumberRand("18"));
		fundaccount.setCust_id(Integer.parseInt(cust_id));
		fundaccount.setCust_type(ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT);
		fundaccount.setEnabled(ENABLED_STATUS_OF_FUNDACCOUNT);
		fundaccount.setFreeze_num(BigDecimal.ZERO);
		fundaccount.setFund_num(BigDecimal.ZERO);
		fundaccount.setUse_num(BigDecimal.ZERO);
		fundaccount.setIn_date(new Date());
		fundaccountService.insert(fundaccount);
	}
	
	/**
	 * 管理员删除商家,并删除店铺信息
	 * @param cust_ids
	 */
	@Transactional
	public void deleteMemberWithShopConfigAndLevelinfo(String cust_ids) {
		if(cust_ids != null && cust_ids.trim().length()> 0) {
			this.memberDao.delete(cust_ids);
			levelinfoService.deleteByCustid(cust_ids);
			shopconfigService.deleteShopconfigByCustid(cust_ids);
		}
	}
	
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 */
	public int isUsernameExist(String username) {
		return memberDao.isUsernameExist(username);
	}
	
	/**
	 * 判断入驻联系人和联系电话是否同时存在
	 * @param member
	 * @return
	 */
	public int isContactNameAndContactPhoneUsed(Member member) {
		return memberDao.isContactNameAndContactPhoneUsed(member);
	}
	
	/**
	 * 查询入驻进度
	 * @param member
	 * @return
	 */
	public List<Member> selectJoinUsProgress(Member member) {
		return memberDao.selectJoinUsProgress(member);
	}

}

