package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Area;

/**
 * @function 功能 地区管理dao层业务接口
 * @author  创建人史倩倩
 * @date  创建日期 Tue Sep 02 14:10:46 CST 2014
 */
public interface AreaDao extends GenericDao<Area,String>{
	
	public List getOpenWebArea();
	
	public List getAreaListByUpareaid(String up_area_id);
	
	public List<Map<String, Object>> getAreaNameListByIds(List<String> areaIds);
	
	//根据地区ID串得到地区名称串
	public List getAreaAttrNameByAreaIdAttr(String area_attr);
	
	public Area getByPk(String value);

	public List getCountryList(Map map);

	public List getCharacterList(Map map);

	public List getAreaCityList(Map map);

	public List getWebAreaIndexList(Map map);

	public List getAll();

	public List getWebAreaList(Map map);

	public List getAreaIndexList(Map map);

	public void updateOneAreaSortNo(Map map);

	public void updateAreaSortNo(List list);
	
}













