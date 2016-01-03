package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.SysuserDao;
import com.xingfugo.business.module.Sysuser;

/**
 * @function 功能 用户Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Fri Sep 05 13:53:56 CST 2014
 */

@Service
public class SysuserService extends GenericService<Sysuser,String>{

	private SysuserDao sysuserDao;
	
	//管理员标识
	private static final String ADMIN_CODE = "3";
	//可用菜单标识
	private static final String SYSMENU_ENABLE = "0";
	//可用权限(有操作权限,但是如果菜单不可用,则属于不可用权限)
	private static final String ROLE_RIGHT_ENABLED = "0";
	
	public SysuserService() {}
	
	@Autowired
	public SysuserService(SysuserDao sysuserDao) {
		super(sysuserDao);
		this.sysuserDao = sysuserDao;
	}
	
	@Autowired
	private SysmenuService sysmenuService;
	@Autowired
	private Role_rightService role_rightService;
	
	public List isUsernameExist(Map map) {
		return sysuserDao.isUsernameExist(map);
	}
	
	public void updateUserPasswd(Sysuser sysuser) {
		sysuserDao.updateUserPasswd(sysuser);
	}
	
	
	public boolean hasPermission(String userId, String uri,String syscode) {
		if(uri == null || "".equals(uri)) {
			return true;
		}
		
		if(userId == null || "".equals(userId)) {
			return false;
		}
		Sysuser sysuser = this.getByPk(userId);
		if(sysuser == null) {
			return false;
		}
		
		//管理员直接放行
		if(ADMIN_CODE.equals(sysuser.getUser_type())) {
			return true;
		}
		List sysmenus = null;
		if(syscode!=null){
			sysmenus = sysmenuService.getUserSysmenus(userId, null, SYSMENU_ENABLE,syscode);
		}else{
			sysmenus = sysmenuService.getUserSysmenus(userId, null, SYSMENU_ENABLE);
		}
		if(sysmenus == null || sysmenus.size() == 0) {
			return false;
		}
		
		for(Map sysmenu : (List<Map>) sysmenus) {
			String oper_basic_right = (String) sysmenu.get("oper_basic_right");
			if(oper_basic_right == null || "".equals(oper_basic_right)) {
				continue;
			}
			String[] rights = oper_basic_right.split(",");
			for(int i=0; i<rights.length; i++) {
				if(uri.equals(rights[i])) {
					return true;
				}
			}
		}
		
		List userOperRights = role_rightService.getUserRole_rights(userId, ROLE_RIGHT_ENABLED);
		if(userOperRights == null || userOperRights.size() == 0) {
			return false;
		}
		for(Map userOperRight : (List<Map>) userOperRights) {
			String oper_right = (String) userOperRight.get("url");
			if(uri.equals(oper_right)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPermission(String userId, String uri) {
		return hasPermission(userId,uri,null);
	}
	
}

