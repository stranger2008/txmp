package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import com.xingfugo.business.module.Category;

/**
 * @function 功能 实体分类dao层业务接口
 * @author  创建人刘香玲
 * @date  创建日期 Tue Sep 02 14:07:15 CST 2014
 */
public interface CategoryDao extends GenericDao<Category,String>{
	
	public List getCategoryByUpCatId(Map<String, String> param);
	
	public List getCategoryByUpCatIds(List<String> up_cat_ids);
	
	public Category getByPk(String cat_id);
	
	//根据商品所属的分类ID串找出对应的分类信息
	public List getDetailCatTree(String cat_attr);
	
	//根据分类ID串找出对应的分类信息
	public List<Map<String,String>> getCatMapByIds(String cat_ids);
	
	/**
	 * 根据上级id,查询子分类id
	 * @param up_cat_id
	 * @return
	 */
	public List getCategoryByParentId(String up_cat_id);
	
	/**
	 * 查询分类对象是否已经存在
	 * @param category
	 * @return
	 */
	public int hasExist(Category category);
	
	/**
	 * 批量修改是否显示状态
	 * @param info
	 */
	int updateWhetherDisplay(Map info);
	
	/**
	 * 根据分类id,取得所有上级(包含自己)
	 * @param cat_id分类id
	 * @return
	 * @author 陈显革
	 * @date 2014-09-16
	 */
	public List getParentsByCatid(String cat_id);

	public List<Map<String, Object>> getListForStatic(
			Map<String, String> attrMap);
	
	public List<Map<String, Object>> getAllListForStatic(
			Map<String, String> attrMap);
}













