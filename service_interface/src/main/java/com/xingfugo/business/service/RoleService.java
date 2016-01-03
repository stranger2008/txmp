package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.RoleDao;
import com.xingfugo.business.module.Role;
import com.xingfugo.business.module.Sysuser;

/**
 * @function 功能 角色管理Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Tue Sep 02 15:21:30 CST 2014
 */

@Service
public class RoleService extends GenericService<Role,String>{

	//管理员标识
	private static final String ADMIN_CODE = "3";
	//用户标识
	private static final String USER_CODE = "4";
	//角色分类
	private static final String CUST_ID = "0";
	
	private RoleDao roleDao;
	
	public RoleService() {}
	
	@Autowired
	public RoleService(RoleDao roleDao) {
		super(roleDao);
		this.roleDao = roleDao;
	}
	
	@Autowired
	private SysuserService sysuserService;

	/**
	 * 取得用户的角色
	 * @param user_id 用户id
	 * @return 用户角色列表
	 */
	public List getUserRoles(String user_id) {
		//用户id为空
		if(user_id == null || "".equals(user_id)) {
			return new ArrayList();
		}
		//用户不存在
		Sysuser sysuser = sysuserService.getByPk(user_id);
		if(sysuser == null) {
			return new ArrayList();
		}
		List roles = null;
		if(ADMIN_CODE.equals(sysuser.getUser_type())) {//管理员
			Map<String,String> map = new HashMap<String,String>();
			map.put("cust_id", CUST_ID);
			roles = this.getList(map);
		} else if(USER_CODE.equals(sysuser.getUser_type())) {//普通用户
			if(sysuser.getRole_id() != null && !"".equals(sysuser.getRole_id())) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("cust_id", CUST_ID);
				map.put("role_ids", sysuser.getRole_id());
				roles = this.getList(map);
			}
		}
		if(roles == null) {
			return new ArrayList();
		}
		return roles;
	}

}

