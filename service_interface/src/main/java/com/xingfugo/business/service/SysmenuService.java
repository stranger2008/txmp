package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.dao.SysmenuDao;
import com.xingfugo.business.module.Sysmenu;
import com.xingfugo.business.module.Sysuser;

/**
 * @function 功能 菜单Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Wed Sep 03 16:36:37 CST 2014
 */

@Service
public class SysmenuService extends GenericService<Sysmenu,String>{

	private SysmenuDao sysmenuDao;
	
	@Autowired
	public SysmenuService(SysmenuDao sysmenuDao) {
		super(sysmenuDao);
		this.sysmenuDao = sysmenuDao;
	}
	
	public SysmenuService() {
	}
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private SysuserService sysuserService;
	

	//商城后台编码
	private static final String SYSCODE = "adm";
	//角色所属商家编码(商城后台)
	private static final String ROLE_CUST_ID = "0";
	//管理员标识
	private static final String ADMIN_CODE = "3";
	//用户标识
	private static final String USER_CODE = "4";
	
	//根据syscode找出一级菜单列表
	public List getOneLevelMenuBySyscode(String syscode) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("syscode", syscode);
		map.put("up_menu_id", Constants.UP_INFO_ID);
		return this.sysmenuDao.getSysmenuListByMap(map);
	}
	
	//根据菜单列表得到第一个菜单ID，根据sort_no asc排序所得
	public String getFirstMenuByList(List menuList){
		if(menuList != null && menuList.size()>0){
			Map map = (Map)menuList.get(0);
			if(map.get("menu_id")!=null){
				return map.get("menu_id").toString();
			}
		}
		return "";
	}

	//根据上机菜单ID得到下级菜单列表
	public List getMenuListByUpmenuid(String up_menu_id){
		if(up_menu_id == null || up_menu_id.equals("")) return null;
		Map<String,String> map = new HashMap<String,String>();
		map.put("up_menu_id", up_menu_id);
		return this.sysmenuDao.getSysmenuListByMap(map);
	}
	
	/**
	 * 根据syscode查找出所有菜单，根据菜单级别升序排序
	 * @param syscode 所属后台编码
	 * @return 该编码下的所有菜单
	 * @author 陈显革
	 * @date 2014-09-03
	 */
	public List getSysmenuListBySyscode(String syscode) {
		return this.sysmenuDao.getSysmenuListBySyscode(syscode);
	}
	
	/**
	 * 根据菜单id，查询上级菜单（包括id对应菜单）
	 * @param menu_id 菜单id
	 * @return 上级菜单（包括id对应菜单）
	 * @author 陈显革
	 * @date 2014-09-03
	 */
	public List getParentSysmenuByMenuid(String menu_id) {
		return this.sysmenuDao.getParentSysmenuByMenuid(menu_id);
	}
	
	/**
	 * 根据菜单id,删除菜单和其子菜单(递归)
	 * @param menu_id 菜单id
	 * @author 陈显革
	 * @date 2014-09-03
	 */
	@Transactional
	public void deleteSysmenuWithChildren(String menu_id) {
		if(menu_id == null || "".equals(menu_id)) {
			return;
		}
		String[] ids = menu_id.split(",");
		for(int i=0; i<ids.length; i++) {
			String id = ids[i];
			List childrenSysmenus = this.sysmenuDao.getChildrenSysmenuByMenuId(menu_id);
			if(childrenSysmenus != null && childrenSysmenus.size() > 0) {
				for(Map<String, Object> sysmenu : ((List<Map<String, Object>>) childrenSysmenus)) {
					this.deleteSysmenuWithChildren((String) sysmenu.get("menu_id"));
				}
			}
			this.delete(id);
		}
	}
	
	
	public List getUserSysmenus(String user_id, String up_menu_id, String type) {
		return getUserSysmenus(user_id,up_menu_id,type,SYSCODE);
	}
	
	/**
	 * 取得用户权限菜单,如果没有菜单,则返回空列表
	 * @param user_id 用户id
	 * @param up_menu_id 上级菜单,如果为空则取全部菜单
	 * @param type 菜单类型(0:可用 1:不可用 null:全部)
	 * @return 用户权限菜单
	 */
	public List getUserSysmenus(String user_id, String up_menu_id, String type,String syscode) {
		//用户id为空
		if(user_id == null || "".equals(user_id)) {
			return new ArrayList();
		}
		//用户不存在
		Sysuser sysuser = sysuserService.getByPk(user_id);
		if(sysuser == null) {
			return new ArrayList();
		}
		List sysmenus = null;
		if(ADMIN_CODE.equals(sysuser.getUser_type())) {//管理员
			Map<String,String> map = new HashMap<String,String>();
			map.put("syscode", syscode);
			map.put("up_menu_id", up_menu_id);
			map.put("enabled", null);
			sysmenus = this.sysmenuDao.getList(map);
		} else if(USER_CODE.equals(sysuser.getUser_type())) {//普通用户
			if(sysuser.getRole_id() != null && !"".equals(sysuser.getRole_id())) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("cust_id", ROLE_CUST_ID);
				map.put("role_ids", sysuser.getRole_id());
				List roles = roleService.getList(map);
				if(roles != null && roles.size() > 0) {
					List<String> menus = new ArrayList<String>();//菜单权限id
					
					//把第一个角色对应的权限菜单串放入menus中
					String mStr = (String)((Map)roles.get(0)).get("menu_right");//菜单权限串
					if(mStr != null && !"".equals(mStr)) {
						String[] mArr = mStr.split(",");
						menus = Arrays.asList(mArr);
						menus = new ArrayList<String>(menus);
					}
					
					//遍历其他角色,把角色对应菜单id放入menus中并去重
					for(int i=1; i<roles.size(); i++) {
						String mStr2 = (String)((Map)roles.get(i)).get("menu_right");//菜单权限串
						if(mStr2 != null && !"".equals(mStr2)) {
							String[] mArr2 = mStr2.split(",");
							for(int j=0; j<mArr2.length; j++) {
								boolean hasIt = false;//是否已经包含此菜单
								for(int k=0; k<menus.size(); k++) {
									if(mArr2[j].equals(menus.get(k))) {
										hasIt = true;
										break;
									}
								}
								if(!hasIt) {
									menus.add(mArr2[j]);
								}
							}
						}
					}
					
					String mIds = null;//权限菜单id串
					if(menus.size() > 0) {
						mIds = menus.get(0);
					}
					for(int i=1; i<menus.size(); i++) {
						mIds = mIds + "," + menus.get(i);
					}
					
					if(mIds != null) {
						Map<String,String> map2 = new HashMap<String,String>();
						map2.put("syscode", syscode);
						map2.put("menu_ids", mIds);
						map2.put("up_menu_id", up_menu_id);
						map2.put("enabled", type);
						sysmenus = this.sysmenuDao.getList(map2);
					}
				}
			}
		}
		if(sysmenus != null) {
			return sysmenus;
		}
		return new ArrayList();
	}
	
}