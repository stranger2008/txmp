package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Role_rightDao;
import com.xingfugo.business.module.Role_right;
import com.xingfugo.business.module.Sysuser;

/**
 * @function 功能 权限Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Thu Sep 04 16:42:37 CST 2014
 */

@Service
public class Role_rightService extends GenericService<Role_right,String>{
	

	//管理员标识
	private static final String ADMIN_CODE = "3";
	//用户标识
	private static final String USER_CODE = "4";
	//角色分类
	private static final String ROLE_CUST_ID = "0";
	//商城后台编码
	private static final String SYSCODE = "adm";

	private Role_rightDao role_rightDao;
	
	@Autowired
	private SysuserService sysuserService;
	@Autowired
	private RoleService roleService;
	
	public Role_rightService() {}
	
	@Autowired
	public Role_rightService(Role_rightDao role_rightDao) {
		super(role_rightDao);
		this.role_rightDao = role_rightDao;
	}
	
	public List getUserRole_rights(Map<String, String> map) {
		return role_rightDao.getUserRole_rights(map);
	}
	
	/**
	 * 取得用户的操作权限(可用权限为,有此操作权限,而且菜单可用;如果有此操作权限,但是菜单不可用,则为不可用权限)
	 * @param user_id 用户ID
	 * @param type 权限类型 0:可用权限 null:全部权限
	 * @return
	 */
	public List getUserRole_rights(String user_id, String type) {
		//用户id为空
		if(user_id == null || "".equals(user_id)) {
			return new ArrayList();
		}
		//用户不存在
		Sysuser sysuser = sysuserService.getByPk(user_id);
		if(sysuser == null) {
			return new ArrayList();
		}
		
		List role_rights = null;
		if(ADMIN_CODE.equals(sysuser.getUser_type())) {//管理员
			role_rights = this.role_rightDao.getList(null);
		} else if(USER_CODE.equals(sysuser.getUser_type())) {//普通用户
			if(sysuser.getRole_id() != null && !"".equals(sysuser.getRole_id())) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("cust_id", ROLE_CUST_ID);
				map.put("role_ids", sysuser.getRole_id());
				List roles = roleService.getList(map);
				if(roles != null && roles.size() > 0) {
					List<String> opers = new ArrayList<String>();//操作权限id列表
					String oStr = (String)((Map)roles.get(0)).get("oper_right");//操作权限
					
					//把第一个角色对应的权限操作串放入opers中
					if(oStr != null && !"".equals(oStr)) {
						String[] oRightArr = oStr.split(",");
						opers = Arrays.asList(oRightArr);
						opers = new ArrayList<String>(opers);
					}
					
					//遍历其他角色,把角色对应操作id放入opers中并去重
					for(int i=1; i<roles.size(); i++) {
						String oStr2 = (String)((Map)roles.get(i)).get("oper_right");//菜单权限串
						if(oStr2 != null && !"".equals(oStr2)) {
							String[] oArr2 = oStr2.split(",");
							for(int j=0; j<oArr2.length; j++) {
								boolean hasIt = false;//是否已经包含此菜单
								for(int k=0; k<opers.size(); k++) {
									if(oArr2[j].equals(opers.get(k))) {
										hasIt = true;
										break;
									}
								}
								if(!hasIt) {
									opers.add(oArr2[j]);
								}
							}
						}
					}
					
					String oIds = null;//权限操作id串
					if(opers.size() > 0) {
						oIds = opers.get(0);
					}
					for(int i=1; i<opers.size(); i++) {
						oIds = oIds + "," + opers.get(i);
					}
					
					if(oIds != null) {
						Map<String,String> map2 = new HashMap<String,String>();
						map2.put("syscode", SYSCODE);
						map2.put("right_ids", oIds);
						map2.put("enabled", type);
						role_rights = this.role_rightDao.getUserRole_rights(map2);
					}
				}
			}
		}
		
		if(role_rights != null) {
			return role_rights;
		}
		
		return new ArrayList();
	}

}