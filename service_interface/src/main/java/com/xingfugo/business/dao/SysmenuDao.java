package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;
import com.xingfugo.business.module.Sysmenu;

/**
 * @function 功能 菜单dao层业务接口
 * @author  创建人陈显革
 * @date  创建日期 Wed Sep 03 16:36:37 CST 2014
 */
public interface SysmenuDao extends GenericDao<Sysmenu,String>{
	//根据map找出菜单列表
	public List getSysmenuListByMap(Map map);
	
	public List getSysmenuListBySyscode(String syscode);
	
	public List getParentSysmenuByMenuid(String menu_id);
	
	public List getChildrenSysmenuByMenuId(String menu_id);
	
}













