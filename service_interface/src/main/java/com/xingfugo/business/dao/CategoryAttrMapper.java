package com.xingfugo.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xingfugo.business.module.CategoryAttr;
import com.xingfugo.business.module.query.CategoryAttrQueryForm;

public interface CategoryAttrMapper {
    List<CategoryAttr> selectByCategoryIds(List<String> categoryIds);
    
    List<Map<String, Object>> selectByIds(@Param(value = "attrIds") String attrIds);
    
    /**
	 * 取得分类属性列表(分页)
	 * @param categoryAttrQueryForm 查询条件
	 * @return 分类属性列表
	 * @author 陈显革
	 * @date 2014-09-15
	 */
	public List<Map<String, Object>> getListByPage(CategoryAttrQueryForm categoryAttrQueryForm);
	
	/**
	 * 新增商品分类属性
	 * @param categoryAttr
	 * @author 陈显革
	 */
	public void insert(CategoryAttr categoryAttr);
	
	/**
	 * 查询商品分类属性
	 * @param attr_id
	 * @return
	 */
	public CategoryAttr getByPk(String attr_id);
	
	/**
	 * 更新商品分类属性
	 * @param categoryAttr
	 */
	public void update(CategoryAttr categoryAttr);
	
	/**
	 * 删除分类属性
	 * @param attr_id
	 */
	public void delete(String attr_id);
	
	/**
	 * 查询属性及其对应属性值
	 * @param map module_type: 属性所属模块; cat_attr: 商品分类id串(用,分隔); attr_types: 属性类型串(用,分隔)
	 * @return
	 * @author 陈显革
	 * @date 2014-09-19
	 */
	public List<Map<String, Object>> getCategoryAttListrWithValue(Map<String, Object> map);
	/**
	 * 通过商品分类查找商品属性
	 * @return
	 */
	public List<Map<String, Object>> getCategoryAttrByCat_attr(Map<String, Object> map);
}