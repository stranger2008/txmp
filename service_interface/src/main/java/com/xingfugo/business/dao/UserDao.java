package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Memberuser;
import com.xingfugo.business.module.User;
import com.xingfugo.business.module.query.ExampleUserQueryForm;

public interface UserDao {
	public Map<String, Object> getMemberUserById(int user_id);
	public List<User> exampleQueryMemberUserByPage(ExampleUserQueryForm userQueryForm);
	public Integer insertUserGetUserId(User user);
	public List<User> getUserByMap(Map map);
	public int updatepwd(User user);
	public User getUserByPhone(String cellphone);
	public User getMemberUserByUsername(String user_name);
}
