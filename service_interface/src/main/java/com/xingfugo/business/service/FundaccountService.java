package com.xingfugo.business.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.FundaccountDao;
import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.Fundhistory;
import com.xingfugo.business.module.Fundrecharge;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;
import com.xingfugo.util.RandomStrUtil;

/**
 * @function 功能 会员资金表Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:24:02 CST 2014
 */

@Service
public class FundaccountService extends GenericService<Fundaccount,String>{

	//未完成-充值状态
	private final static String FUNDRECHARGE_STATUS_UNFINISH = "0";
	
	//充值完成
	private final static String FUNDRECHARGE_STATUS_FINISH = "1";//未完成-充值状态
	
	//充值错误-账户不正常
	private final static String FUNDRECHARGE_STATUS_ERROR = "0";
	
	//充值-资金移动表
	private final static String FUNDHISTORY_ACTION_TYPE_RECHARGE = "0";
	//提现-资金移动表
	private final static String FUNDHISTORY_ACTION_TYPE_CASH = "1";
	//退款-资金移动表
	private final static String FUNDHISTORY_ACTION_TYPE_REFUND = "3";
	//订单支付-资金移动表
	private final static String FUNDHISTORY_ACTION_TYPE_ORDER = "2";
	//资金异动,原因
	private final static String FUNDHISTORY_REASON_RECHARGE = "充值";
	//资金异动,原因
	private final static String FUNDHISTORY_REASON_REFUND = "退货退款";
	//资金账户可用
	private final static String ENABLE_STATUS_OF_FUNDACCOUNT = "0";
	//资金账户不可用
	private final static String DISABLE_STATUS_OF_FUNDACCOUNT = "1";
	
	//个人-会员类型-资金账户
	private static final String PERSONAL_CUST_TYPE_OF_FUNDACCOUNT = "0";
	//企业-会员类型-资金账户
	private static final String ENTERPRISE_CUST_TYPE_OF_FUNDACCOUNT = "1";
	
	//资金账户章台可用
	private static final String FUNDACCOUNT_STATUS_ENABLED = "0";
	
	//资金异动类型-支付
	private static final String FUNDHISTORY_ACTION_TYPE_PAY = "2";
	//资金异动类型-提现
	private static final String FUNDHISTORY_ACTION_TYPE_WITHDRAW = "1";

	private FundaccountDao fundaccountDao;
	
	public FundaccountService() {}
	
	@Autowired
	public FundaccountService(FundaccountDao fundaccountDao) {
		super(fundaccountDao);
		this.fundaccountDao = fundaccountDao;
	}

	@Autowired
	private FundhistoryService fundhistoryService;
	@Autowired
	private FundrechargeService fundrechargeService;
	
	/**
	 * 根据用户/企业id查询资金账户
	 * @param cust_id
	 * @param cust_type
	 * @return
	 */
	public Fundaccount getFundaccountByCustidAndCusttype(Integer cust_id, String cust_type) {
		if(cust_id == null) {
			return null;
		}
		if(cust_type == null || cust_type.trim().length() == 0) {
			return null;
		}
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("cust_id", cust_id);
		pmap.put("cust_type", cust_type);
		return fundaccountDao.getFundaccountByCustidAndCusttype(pmap);
	}
	
	/**
	 * 去充值
	 * @param account_no
	 * @param fund_num
	 * @return
	 */
	public Fundrecharge rechargeFundAccount(final String account_no, final BigDecimal fund_num) {
		Fundrecharge fundrecharge = new Fundrecharge();
		fundrecharge.setAccount_no(account_no);
		
		//账户状态不正常
		Fundaccount fundaccount = fundaccountDao.getByPk(account_no);
		if(fundaccount == null || !ENABLE_STATUS_OF_FUNDACCOUNT.equals(fundaccount.getEnabled())) {
			fundrecharge.setIs_enabled(FUNDRECHARGE_STATUS_ERROR);
			return fundrecharge;
		}
		
		fundrecharge.setFund_num(fund_num.setScale(2, BigDecimal.ROUND_HALF_UP));
		fundrecharge.setOrder_no(RandomStrUtil.getNumberRand("16"));
		fundrecharge.setIs_enabled(FUNDRECHARGE_STATUS_UNFINISH);
		fundrecharge.setIn_date(new Date());
		fundrechargeService.insert(fundrecharge);
		return fundrecharge;
	}
	
	/**
	 * 充值成功,更新资金账户状态,添加充值记录
	 * @param order_no
	 */
	public void rechargeFundAccountSuccess(final String order_no) {
		Fundrecharge fundrecharge = fundrechargeService.getFundrechargeByOrder_no(order_no);
		
		//资金账号
		final String account_no = fundrecharge.getAccount_no();
		//充值金额
		final BigDecimal recharge_num = fundrecharge.getFund_num().setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//更新充值状态
		fundrecharge.setIs_enabled(FUNDRECHARGE_STATUS_FINISH);
		fundrechargeService.update(fundrecharge);
		
		//更新账户总额和可用金额
		Fundaccount fundaccount = fundaccountDao.getByPk(account_no);
		
		//总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		fund_num = fund_num.add(recharge_num);
		
		//可用余额
		BigDecimal use_num = fundaccount.getUse_num();
		use_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_num = use_num.add(recharge_num);
		
		fundaccount.setFund_num(fund_num);
		fundaccount.setUse_num(use_num);
		fundaccountDao.updateFund_numAndUse_numOfFundaccount(fundaccount);
		
		//资金异动表
		Fundhistory fundhistory = new Fundhistory();
		fundhistory.setAccount_no(account_no);
		fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_RECHARGE);
		fundhistory.setBalance(fund_num);
		fundhistory.setFund_in(recharge_num);
		fundhistory.setFund_out(BigDecimal.ZERO);
		fundhistory.setIn_date(new Date());
		if(PERSONAL_CUST_TYPE_OF_FUNDACCOUNT.equals(fundaccount.getCust_type())) {
			//个人会员
			fundhistory.setUser_id(fundaccount.getCust_id());
		} else {
			//商家
			fundhistory.setCust_id(fundaccount.getCust_id());
		}
		fundhistory.setReason(FUNDHISTORY_REASON_RECHARGE);
		fundhistoryService.insert(fundhistory);
	}
	
	/**
	 * 个人资金账户支付
	 * @param fundAccountNo 资金账户
	 * @param payFundNum 支付金额
	 * @param actionDesc 支付描述
	 * @param user_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String payByUseNumUserFundaccount(final String accountNo, final BigDecimal payFundNum, final String actionDesc, final Integer user_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_use_num = fundaccount.getUse_num();//资金账户可用余额
		if(payFundNum.compareTo(fund_use_num) > 0) {
			return "0203";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = payFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		fund_num = fund_num.subtract(trade_fund_num);
		
		//账户可用余额
		BigDecimal use_fund_num = fundaccount.getUse_num();
		use_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_fund_num = use_fund_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setFund_num(fund_num);
		fundaccount.setUse_num(use_fund_num);
		this.updateFund_numAndUse_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_PAY);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(trade_fund_num);
		from_fundhistory.setIn_date(new Date());
		from_fundhistory.setUser_id(user_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	
	/**
	 * 商家资金账户支付
	 * @param fundAccountNo 资金账户
	 * @param payFundNum 支付金额
	 * @param actionDesc 支付描述
	 * @param cust_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String payByUseNumMemberFundaccount(final String accountNo, final BigDecimal payFundNum, final String actionDesc, final Integer cust_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_use_num = fundaccount.getUse_num();//资金账户可用余额
		if(payFundNum.compareTo(fund_use_num) > 0) {
			return "0203";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = payFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		fund_num = fund_num.subtract(trade_fund_num);
		
		//账户可用余额
		BigDecimal use_fund_num = fundaccount.getUse_num();
		use_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_fund_num = use_fund_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setFund_num(fund_num);
		fundaccount.setUse_num(use_fund_num);
		this.updateFund_numAndUse_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_PAY);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(trade_fund_num);
		from_fundhistory.setIn_date(new Date());
		from_fundhistory.setCust_id(cust_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	
	/**
	 * 个人资金账户支付--冻结金额
	 * @param fundAccountNo 资金账户
	 * @param payFundNum 支付金额
	 * @param actionDesc 支付描述
	 * @param user_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String payByFreezeNumUserFundaccount(final String accountNo, final BigDecimal payFundNum, final String actionDesc, final Integer user_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_freeze_num = fundaccount.getFreeze_num();//资金账户冻结余额
		if(payFundNum.compareTo(fund_freeze_num) > 0) {
			return "0204";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = payFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		fund_num = fund_num.subtract(trade_fund_num);
		
		//账户冻结余额
		BigDecimal freeze_fund_num = fundaccount.getFreeze_num();
		freeze_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		freeze_fund_num = freeze_fund_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setFund_num(fund_num);
		fundaccount.setFreeze_num(freeze_fund_num);
		this.updateFund_numAndFreeze_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_WITHDRAW);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(trade_fund_num);
		from_fundhistory.setIn_date(new Date());
//		from_fundhistory.setUser_id(user_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	
	/**
	 * 商家资金账户支付--冻结金额
	 * @param fundAccountNo 资金账户
	 * @param payFundNum 支付金额
	 * @param actionDesc 支付描述
	 * @param cust_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String payByFreezeNumMemberFundaccount(final String accountNo, final BigDecimal payFundNum, final String actionDesc, final Integer cust_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_freeze_num = fundaccount.getFreeze_num();//资金账户可用余额
		if(payFundNum.compareTo(fund_freeze_num) > 0) {
			return "0204";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = payFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		fund_num = fund_num.subtract(trade_fund_num);
		
		//账户冻结余额
		BigDecimal freeze_fund_num = fundaccount.getFreeze_num();
		freeze_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		freeze_fund_num = freeze_fund_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setFund_num(fund_num);
		fundaccount.setFreeze_num(freeze_fund_num);
		this.updateFund_numAndFreeze_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_WITHDRAW);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(trade_fund_num);
		from_fundhistory.setIn_date(new Date());
//		from_fundhistory.setCust_id(cust_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	
	/**
	 * 个人资金账户资金冻结
	 * @param fundAccountNo 资金账户
	 * @param freezeFundNum 冻结金额
	 * @param actionDesc 支付描述
	 * @param user_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String freezeUserFundaccount(final String accountNo, final BigDecimal freezeFundNum, final String actionDesc, final Integer user_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_use_num = fundaccount.getUse_num();//资金账户可用余额
		if(freezeFundNum.compareTo(fund_use_num) > 0) {
			return "0203";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = freezeFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户可用余额
		BigDecimal use_fund_num = fundaccount.getUse_num();
		use_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_fund_num = use_fund_num.subtract(trade_fund_num);
		
		BigDecimal freeze_fund_num = fundaccount.getFreeze_num();
		freeze_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		freeze_fund_num = freeze_fund_num.add(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setUse_num(use_fund_num);
		fundaccount.setFreeze_num(freeze_fund_num);
		this.updateUse_numAndFreeze_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_WITHDRAW);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(BigDecimal.ZERO);
		from_fundhistory.setIn_date(new Date());
		from_fundhistory.setUser_id(user_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	
	/**
	 * 解冻个人资金账户冻结金额
	 * @param fundAccountNo 资金账户
	 * @param freezeFundNum 冻结金额
	 * @param actionDesc 支付描述
	 * @param user_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String unfreezeUserFundaccount(final String accountNo, final BigDecimal unfreezeFundNum, final String actionDesc, final Integer user_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_freeze_num = fundaccount.getFreeze_num();//资金账户冻结余额
		if(unfreezeFundNum.compareTo(fund_freeze_num) > 0) {
			return "0204";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = unfreezeFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户可用余额
		BigDecimal use_fund_num = fundaccount.getUse_num();
		use_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_fund_num = use_fund_num.add(trade_fund_num);
		
		BigDecimal freeze_fund_num = fundaccount.getFreeze_num();
		freeze_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		freeze_fund_num = freeze_fund_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setUse_num(use_fund_num);
		fundaccount.setFreeze_num(freeze_fund_num);
		this.updateUse_numAndFreeze_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_WITHDRAW);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(BigDecimal.ZERO);
		from_fundhistory.setIn_date(new Date());
//		from_fundhistory.setUser_id(user_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	
	/**
	 * 商家资金账户资金冻结
	 * @param fundAccountNo 资金账户
	 * @param freezeFundNum 冻结金额
	 * @param actionDesc 支付描述
	 * @param user_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String freezeMemberFundaccount(final String accountNo, final BigDecimal freezeFundNum, final String actionDesc, final Integer cust_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_use_num = fundaccount.getUse_num();//资金账户可用余额
		if(freezeFundNum.compareTo(fund_use_num) > 0) {
			return "0203";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = freezeFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户可用余额
		BigDecimal use_fund_num = fundaccount.getUse_num();
		use_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_fund_num = use_fund_num.subtract(trade_fund_num);
		
		BigDecimal freeze_fund_num = fundaccount.getFreeze_num();
		freeze_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		freeze_fund_num = freeze_fund_num.add(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setUse_num(use_fund_num);
		fundaccount.setFreeze_num(freeze_fund_num);
		this.updateUse_numAndFreeze_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_WITHDRAW);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(BigDecimal.ZERO);
		from_fundhistory.setIn_date(new Date());
		from_fundhistory.setCust_id(cust_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	

	
	/**
	 * 解冻商家资金账户冻结金额
	 * @param fundAccountNo 资金账户
	 * @param freezeFundNum 冻结金额
	 * @param actionDesc 支付描述
	 * @param user_id 支付用户id
	 * @return 支付结果码
	 * @author 陈显革
	 */
	@Transactional
	public String unfreezeMemberFundaccount(final String accountNo, final BigDecimal unfreezeFundNum, final String actionDesc, final Integer cust_id) {
		Fundaccount fundaccount = fundaccountDao.getByPk(accountNo);
		if(fundaccount == null) {
			return "0201";//资金账户不存在
		}
		if(!FUNDACCOUNT_STATUS_ENABLED.equals(fundaccount.getEnabled())) {
			return "0202";//资金账户不可用
		}
		
		BigDecimal fund_freeze_num = fundaccount.getFreeze_num();//资金账户冻结余额
		if(unfreezeFundNum.compareTo(fund_freeze_num) > 0) {
			return "0204";
		}
		
		//交易金额
		final BigDecimal trade_fund_num = unfreezeFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//账户可用余额
		BigDecimal use_fund_num = fundaccount.getUse_num();
		use_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_fund_num = use_fund_num.add(trade_fund_num);
		
		//冻结金额
		BigDecimal freeze_fund_num = fundaccount.getFreeze_num();
		freeze_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		freeze_fund_num = freeze_fund_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		fundaccount.setUse_num(use_fund_num);
		fundaccount.setFreeze_num(freeze_fund_num);
		this.updateUse_numAndFreeze_numOfFundaccount(fundaccount);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(fundaccount.getAccount_no());
		from_fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_WITHDRAW);
		from_fundhistory.setBalance(fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(BigDecimal.ZERO);
		from_fundhistory.setIn_date(new Date());
//		from_fundhistory.setCust_id(cust_id);
		from_fundhistory.setReason(actionDesc);
		fundhistoryService.insert(from_fundhistory);
		
		return "0200";//订单支付成功
	}
	
	/**
	 * 
	 * @param fundAccount
	 * @param freezeFundNum
	 * @param actionDesc
	 * @return
	 */
	public String freezeFundAccount(final String fundAccount, final BigDecimal freezeFundNum, final String actionDesc) {
		Fundaccount fundaccount = fundaccountDao.getByPk(fundAccount);
		if(fundaccount == null) {
			return "0201";
		}
		
		
		return null;
	}
	
	/**
	 * 账户交易(退款)
	 * @param fromFundAccount
	 * @param toFundAccount
	 * @param trade_num
	 * @return -2 账户状态不正常; -1 可用余额不足; 0 交易失败; 1 交易成功
	 */
	public int tradeFundAccount(final String fromFundAccount, final String toFundAccount, final BigDecimal tradeFundNum, final String actionType, final String action_desc) {
		//交易金额
		final BigDecimal trade_fund_num = tradeFundNum.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//支付方
		Fundaccount from = fundaccountDao.getByPk(fromFundAccount);
		//资金账户不正常
		if(!ENABLE_STATUS_OF_FUNDACCOUNT.equals(from.getEnabled())) {
			return -2;
		}
		//接收方
		Fundaccount to = fundaccountDao.getByPk(toFundAccount);
		//资金账户不正常
		if(!ENABLE_STATUS_OF_FUNDACCOUNT.equals(to.getEnabled())) {
			return -2;
		}
		
		//支付方总金额
		BigDecimal from_fund_num = from.getFund_num();
		from_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		from_fund_num = from_fund_num.subtract(trade_fund_num);
		
		//支付方可用余额
		BigDecimal from_use_num = from.getUse_num();
		//总金额小于支付金额
		if(from_use_num.compareTo(trade_fund_num) < 0) {
			return -1;//交易失败
		}
		from_use_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		from_use_num = from_use_num.subtract(trade_fund_num);
		
		//支付方资金账户更新
		from.setFund_num(from_fund_num);
		from.setUse_num(from_use_num);
		fundaccountDao.updateFund_numAndUse_numOfFundaccount(from);
		
		//支付方资金异动表
		Fundhistory from_fundhistory = new Fundhistory();
		from_fundhistory.setAccount_no(from.getAccount_no());
		from_fundhistory.setAction_type(actionType);
		from_fundhistory.setBalance(from_fund_num);
		from_fundhistory.setFund_in(BigDecimal.ZERO);
		from_fundhistory.setFund_out(trade_fund_num);
		from_fundhistory.setIn_date(new Date());
		if(PERSONAL_CUST_TYPE_OF_FUNDACCOUNT.equals(from.getCust_type())) {
			//个人会员
			from_fundhistory.setUser_id(from.getCust_id());
		} else {
			//商家
			from_fundhistory.setUser_id(from.getCust_id());
		}
		from_fundhistory.setReason(action_desc);
		fundhistoryService.insert(from_fundhistory);
		
		
		/////接收方
		//接收方总金额
		BigDecimal to_fund_num = to.getFund_num();
		to_fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		to_fund_num = to_fund_num.add(trade_fund_num);
		
		//接收方可用余额
		BigDecimal to_use_num = to.getUse_num();
		to_use_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		to_use_num = to_use_num.add(trade_fund_num);
		
		//接收方资金账户更新
		to.setFund_num(to_fund_num);
		to.setUse_num(to_use_num);
		fundaccountDao.updateFund_numAndUse_numOfFundaccount(to);
		
		//接收方资金异动表
		Fundhistory to_fundhistory = new Fundhistory();
		to_fundhistory.setAccount_no(to.getAccount_no());
		to_fundhistory.setAction_type(actionType);
		to_fundhistory.setBalance(to_fund_num);
		to_fundhistory.setFund_in(trade_fund_num);
		to_fundhistory.setFund_out(BigDecimal.ZERO);
		to_fundhistory.setIn_date(new Date());
		if(PERSONAL_CUST_TYPE_OF_FUNDACCOUNT.equals(from.getCust_type())) {
			//个人会员
			to_fundhistory.setUser_id(from.getCust_id());
		} else {
			//商家
			to_fundhistory.setCust_id(from.getCust_id());
		}
		to_fundhistory.setUser_id(from.getCust_id());
		to_fundhistory.setReason(action_desc);
		fundhistoryService.insert(to_fundhistory);
		
		return 1;
	}
	
	/**
	 * 充值成功
	 * @param order_no
	 * @return
	 */
	@Transactional
	public void rechargeFundaccountOK(String order_no) {
		Fundrecharge fundrecharge = fundrechargeService.getFundrechargeByOrder_no(order_no);
		
		//资金账号
		String account_no = fundrecharge.getAccount_no();
		//充值金额
		final BigDecimal recharge_num = fundrecharge.getFund_num().setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//更新充值状态
		fundrecharge.setIs_enabled(FUNDRECHARGE_STATUS_FINISH);
		fundrechargeService.update(fundrecharge);
		
		//更新账户总额和可用金额
		Fundaccount fundaccount = fundaccountDao.getByPk(account_no);
		
		//当前用户id
		Integer user_id = fundaccount.getCust_id();
		//总金额
		BigDecimal fund_num = fundaccount.getFund_num();
		fund_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		fund_num = fund_num.add(recharge_num);
		//可用余额
		BigDecimal use_num = fundaccount.getUse_num();
		use_num.setScale(2, BigDecimal.ROUND_HALF_UP);
		use_num = use_num.add(recharge_num);
		
		fundaccount.setFund_num(fund_num);
		fundaccount.setUse_num(use_num);
		fundaccountDao.updateFund_numAndUse_numOfFundaccount(fundaccount);
		
		//资金异动表
		Fundhistory fundhistory = new Fundhistory();
		fundhistory.setAccount_no(account_no);
		fundhistory.setAction_type(FUNDHISTORY_ACTION_TYPE_RECHARGE);
		fundhistory.setBalance(fund_num);
		fundhistory.setFund_in(recharge_num);
		fundhistory.setFund_out(BigDecimal.ZERO);
		fundhistory.setIn_date(new Date());
		fundhistory.setUser_id(user_id);
		fundhistory.setReason("资金充值, 流水号: " + order_no);
		fundhistoryService.insert(fundhistory);
	}
	
	/**
	 * 启用资金账户
	 * @param account_no
	 */
	public void enableAccount(String account_no) {
		Fundaccount fundaccount = new Fundaccount();
		fundaccount.setAccount_no(account_no);
		fundaccount.setEnabled(ENABLE_STATUS_OF_FUNDACCOUNT);
		fundaccountDao.updateAccountStatus(fundaccount);
	}
	
	/**
	 * 禁用资金账户
	 * @param account_no
	 */
	public void disableAccount(String account_no) {
		Fundaccount fundaccount = new Fundaccount();
		fundaccount.setAccount_no(account_no);
		fundaccount.setEnabled(DISABLE_STATUS_OF_FUNDACCOUNT);
		fundaccountDao.updateAccountStatus(fundaccount);
	}
	
	/**
	 * 查询个人会员资金列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public PageResult getFundaccountListOfMemberuserByPage(BasePageForm basePageForm){
		List<Map<String, Object>> list = fundaccountDao.getFundaccountListOfMemberuserByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		return result;
	}
	
	/**
	 * 查询企业会员资金列表(分页)
	 * @param basePageForm
	 * @return
	 */
	public PageResult getFundaccountListOfMemberByPage(BasePageForm basePageForm){
		List<Map<String, Object>> list = fundaccountDao.getFundaccountListOfMemberByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		return result;
	}
	
	/**
	 * 更新账户总金额和可用金额
	 * @param fundaccount
	 */
	public void updateFund_numAndUse_numOfFundaccount(Fundaccount fundaccount) {
		fundaccountDao.updateFund_numAndUse_numOfFundaccount(fundaccount);
	}
	
	
	/**
	 * 更新账户总金额和冻结金额
	 * @param fundaccount
	 */
	public void updateFund_numAndFreeze_numOfFundaccount(Fundaccount fundaccount) {
		fundaccountDao.updateFund_numAndFreeze_numOfFundaccount(fundaccount);
	}
	
	/**
	 * 更新账户可用金额和可用
	 * @param fundaccount
	 */
	public void updateUse_numAndFreeze_numOfFundaccount(Fundaccount fundaccount) {
		fundaccountDao.updateUse_numAndFreeze_numOfFundaccount(fundaccount);
	}
	
	/**
	 * 更新账户总金额,可用金额,冻结金额
	 * @param fundaccount
	 */
	public void updateFund_numAndUse_numAndFreeze_numOfFundaccount(Fundaccount fundaccount) {
		fundaccountDao.updateFund_numAndUse_numAndFreeze_numOfFundaccount(fundaccount);
	}
	
	public static void main(String[] args) {
		BigDecimal bd1 = new BigDecimal(100.20);
		
		
		
		BigDecimal bd2 = new BigDecimal(2.000);

		System.out.println(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(bd2.setScale(2, BigDecimal.ROUND_HALF_UP)));
		bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
		bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		System.out.println(bd1.add(bd2));
		
		String s = "12345678901234567";
		System.out.println(RandomStrUtil.getNumberRand("18"));
	}
	
}

