package com.xingfugo.business.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.AreaDao;
import com.xingfugo.business.module.Area;
import com.xingfugo.business.module.Category;

/**
 * @function 功能 地区管理Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Tue Sep 02 14:10:46 CST 2014
 */

@Service
public class AreaService extends GenericService<Area,String>{
	
	
	private AreaDao areaDao;
	
	private static final String UP_AREA_ID = "9999999999";
	
	public AreaService() {}
	
	@Autowired
	public AreaService(AreaDao areaDao) {
		super(areaDao);
		this.areaDao = areaDao;
	}
	
	public void updateAreaSortNo(List list) {
		this.areaDao.updateAreaSortNo(list);
	}
	public void updateOneAreaSortNo(Map map) {
		this.areaDao.updateOneAreaSortNo(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getAreaIndexList(java.util.Map)
	 */
	public List getAreaIndexList(Map map) {
		return areaDao.getAreaIndexList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getWebAreaList(java.util.Map)
	 */
	public List getWebAreaList(Map map) {
		return areaDao.getWebAreaList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getAll()
	 */
	public List getAll() {
		return areaDao.getAll();
	}

	public List getWebAreaIndexList(Map map) {
		return areaDao.getWebAreaIndexList(map);
	}
	
	public List getAreaCityList(Map map) {
		return areaDao.getAreaCityList(map);
	}
	
	public List getCharacterList(Map map) {
		return areaDao.getCharacterList(map);
	}
	
	public List getCountryList(Map map) {
		return areaDao.getCountryList(map);
	}
	
	//根据地区串ID找出地区名称串
	public String getAreaAttrNameByAreaIdAttr(String area_attr){
		return getAreaAttrNameByAreaIdAttr(area_attr,"");
	}

	//根据地区串ID找出地区名称串，带 ， 号
	//flag为1时，带 ， 号
	public String getAreaAttrNameByAreaIdAttr(String area_attr,String flag){
		StringBuffer areaSb = new StringBuffer();
		if(area_attr == null || area_attr.equals("")){
			return "";
		}
		if(!area_attr.equals("") && area_attr.substring(area_attr.length()-1, area_attr.length()).equals(",")){
			area_attr = area_attr.substring(0, area_attr.length() - 1);
		}
		List areaList = areaDao.getAreaAttrNameByAreaIdAttr(area_attr);
		if(flag.equals("2")){
			areaSb.append("国家>");
		}
		Iterator itr = areaList.iterator();
		
		while(itr.hasNext()){
			Map areaMap = (Map)itr.next();
			String area_name = "";
			if(areaMap.get("area_name") != null){
				area_name = areaMap.get("area_name").toString();
				areaSb.append(area_name);
				if(flag.equals("1")){
					areaSb.append(",");
				}
				if(flag.equals("2")){
					areaSb.append(">");
				}
			}
		}
		if(areaSb.toString().endsWith(",")){
			areaSb.deleteCharAt(areaSb.length() - 1);
		}
		return areaSb.toString();
	}
	
	public Area getByPk(String value){
		return this.areaDao.getByPk(value);
	}
	
	public List getOpenWebArea() {
		return areaDao.getOpenWebArea();
	}
	
	public List getAreaListByUpareaid(String up_area_id){
		return areaDao.getAreaListByUpareaid(up_area_id);
	}
	
	public List<Map<String, Object>> getAreaNameListByIds(List<String> areaIds){
		return areaDao.getAreaNameListByIds(areaIds);
	}
	/**
	 * 根据传入的分类ID找出父级分类树结构
	 * 主要用于前台地区列表页的面包屑位置
	 */
	public void setAreaTreeByCatid(List<Area> posTreeList,String area_id){
		Area area = areaDao.getByPk(area_id);
		if(area != null && area.getUp_area_id() != null){
			String up_area_id = area.getUp_area_id();
			if(!up_area_id.equals(UP_AREA_ID)){
				setAreaTreeByCatid(posTreeList,up_area_id);
			}
			posTreeList.add(area);
		}
	}

}

