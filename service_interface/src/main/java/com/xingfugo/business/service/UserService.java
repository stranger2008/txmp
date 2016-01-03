package com.xingfugo.business.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.UserDao;
import com.xingfugo.business.module.Fundaccount;
import com.xingfugo.business.module.User;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.ExampleUserQueryForm;
import com.xingfugo.util.PageResultBuilder;
import com.xingfugo.util.RandomStrUtil;


@Service
public class UserService{
	//新增默认会员  普通会员
	private static String DEFAULT_LEVEL = "普通会员";
	//0：默认正常状态
	private static String DEFAULT_STATUS_VALUE="0";
	//个人-会员类型-资金账户
	private static final String PERSONAL_CUST_TYPE_OF_FUNDACCOUNT = "0";
	//可用-状态-资金账户
	private static final String ENABLED_STATUS_OF_FUNDACCOUNT = "0";

	@Autowired
	private UserDao userDao;
	@Autowired
	private FundaccountService fundaccountService;
	
	public User getMemberUserById(int user_id) {
		Map<String, Object> userMap = userDao.getMemberUserById(user_id);
		if(userMap == null){
			return null;
		}
		
		User user = new User();
		try {
			BeanUtils.populate(user, userMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List getUserByMap(Map map){
		return userDao.getUserByMap(map);
	}

	@Transactional
	public Integer insertUserGetUserId(User user) {
		user.setUser_level(DEFAULT_LEVEL);
		user.setState_code(DEFAULT_STATUS_VALUE);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setLogin_time(df.format(new Date()));
		userDao.insertUserGetUserId(user);
		
		//初始化资金账号
		Fundaccount fundaccount = new Fundaccount();
		fundaccount.setAccount_no(RandomStrUtil.getNumberRand("18"));
		fundaccount.setCust_id(user.getUser_id());
		fundaccount.setCust_type(PERSONAL_CUST_TYPE_OF_FUNDACCOUNT);
		fundaccount.setEnabled(ENABLED_STATUS_OF_FUNDACCOUNT);
		fundaccount.setFreeze_num(BigDecimal.ZERO);
		fundaccount.setFund_num(BigDecimal.ZERO);
		fundaccount.setUse_num(BigDecimal.ZERO);
		fundaccount.setIn_date(new Date());
		fundaccountService.insert(fundaccount);
		
		return user.getUser_id();
	}
	
	public PageResult exampleQueryMemberUserByPage(ExampleUserQueryForm userQueryForm) {
		List<User> list = userDao.exampleQueryMemberUserByPage(userQueryForm);
		PageResult result = PageResultBuilder.build(userQueryForm.getPg(), list);
		return result;
	}
	
	public int updatepwd(User user){
		return userDao.updatepwd(user);
	}
	
	public User getUserByPhone(String cellphone){
		return userDao.getUserByPhone(cellphone);
	}
	
	//根据user_name 查询 user_id
	public User getMemberUserByUsername(String user_name){
		return userDao.getMemberUserByUsername(user_name);
	}
}
