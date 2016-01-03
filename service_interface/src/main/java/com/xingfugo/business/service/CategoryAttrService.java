package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.CategoryAttrMapper;
import com.xingfugo.business.module.AttrValue;
import com.xingfugo.business.module.CategoryAttr;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CategoryAttrQueryForm;
import com.xingfugo.util.PageResultBuilder;
import com.xingfugo.util.RandomStrUtil;

@Service
public class CategoryAttrService {


	//分类属性类型,默认单行文本框
	private static final String CATEGORY_ATTR_TYPE_TEXT = "0";
	//多行文本框
	private static final String CATEGORY_ATTR_TYPE_TEXTAREA = "1";
	//单选按钮
	private static final String CATEGORY_ATTR_TYPE_RADIO = "2";
	//复选框
	private static final String CATEGORY_ATTR_TYPE_CHECKBOX = "3";
	
	@Autowired
	private CategoryAttrMapper mapper;
	
	@Autowired
	private AttrValueService attrValueService;
	
	/**
	 * 获取商品分类的属性和属性值
	 * @param categoryIds 商品分类ID，多个以逗号分隔，需按照一级分类ID、二级分类ID、三级分类ID顺序排列
	 * @return
	 */
	public List<CategoryAttr> getCategoryAttr(String categoryIds){
		String[] catId = categoryIds.split(",");
		int size = catId.length;
		List<String> ids = new ArrayList<String>(size);
		for(int i = 0; i < size; i++){
			if(i == 0){
				ids.add(catId[i]);
				continue;
			}
			
			StringBuffer buf = new StringBuffer();
			if(ids.size() > 0){
				buf.append(ids.get(ids.size() - 1));
				buf.append(",");
			}
			buf.append(catId[i]);
			
			ids.add(buf.toString());
		}
		
		List<CategoryAttr> catAttrList = mapper.selectByCategoryIds(ids);
		return catAttrList;
	}
	
	public List<Map<String, Object>> selectByIds(String attrIds){
		return mapper.selectByIds(attrIds);
	}
	
	/**
	 * 取得分类属性列表(分页)
	 * @param categoryAttrQueryForm 查询条件
	 * @return 分类属性列表
	 * @author 陈显革
	 * @date 2014-09-12
	 */
	public PageResult getListByPage(CategoryAttrQueryForm categoryAttrQueryForm){
		List<Map<String, Object>> list = mapper.getListByPage(categoryAttrQueryForm);
		PageResult result = PageResultBuilder.build(categoryAttrQueryForm.getPg(), list);
		return result;
	}
	
	/**
	 * 新增商品分类属性
	 * @param categoryAttr
	 * @author 陈显革
	 */
	@Transactional
	public void insert(CategoryAttr categoryAttr) {
		String attrValues = categoryAttr.getDefaultVal();
		if(attrValues != null && attrValues.trim().length() > 0) {
			attrValues = attrValues.trim();
			if(CATEGORY_ATTR_TYPE_RADIO.equals(categoryAttr.getAttrType()) || CATEGORY_ATTR_TYPE_CHECKBOX.equals(categoryAttr.getAttrType())) {
				String[] attrValueArr = attrValues.split("\\|");
				for(String attr_value : attrValueArr) {
					if(attr_value.trim().length() == 0) {
						continue;
					}
					AttrValue attrValue = new AttrValue();
					attrValue.setTrade_id(RandomStrUtil.getNumberRand());
					attrValue.setAttr_id(categoryAttr.getAttrId());
					attrValue.setAttr_value(attr_value);
					attrValueService.insert(attrValue);
				}
			} else {
				AttrValue attrValue = new AttrValue();
				attrValue.setTrade_id(RandomStrUtil.getNumberRand());
				attrValue.setAttr_id(categoryAttr.getAttrId());
				attrValue.setAttr_value(attrValues);
				attrValueService.insert(attrValue);
			}
		}
		categoryAttr.setDefaultVal(null);
		mapper.insert(categoryAttr);
	}
	
	/**
	 * 查询商品分类属性
	 * @param attr_id
	 * @return
	 */
	public CategoryAttr getByPk(String attr_id) {
		return mapper.getByPk(attr_id);
	}
	
	/**
	 * 更新商品分类属性
	 * @param categoryAttr
	 */
	public void update(CategoryAttr categoryAttr) {
		String attrType = categoryAttr.getAttrType();
		if(!CATEGORY_ATTR_TYPE_RADIO.equals(attrType) && !CATEGORY_ATTR_TYPE_CHECKBOX.equals(attrType)) {
			String defaultVal = categoryAttr.getDefaultVal();
			if(defaultVal != null && defaultVal.trim().length() > 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("attr_id", categoryAttr.getAttrId());
				List attrValues = attrValueService.getList(map);
				if(attrValues == null || attrValues.size() == 0) {
					AttrValue attrValue = new AttrValue();
					attrValue.setTrade_id(RandomStrUtil.getNumberRand());
					attrValue.setAttr_id(categoryAttr.getAttrId());
					attrValue.setAttr_value(defaultVal);
					attrValueService.insert(attrValue);
				} else if(attrValues.size() == 1) {
					String trade_id = (String)((Map<String, Object>)attrValues.get(0)).get("trade_id");
					AttrValue attrValue = new AttrValue();
					attrValue.setTrade_id(trade_id);
					attrValue.setAttr_value(defaultVal);
					attrValueService.update(attrValue);
				}
			}
		}
		mapper.update(categoryAttr);
	}
	
	/**
	 * 删除分类属性,同时删除属性值
	 * @param attr_id
	 */
	@Transactional
	public void delete(String attr_id) {
		attrValueService.deleteByAttrId(attr_id);
		mapper.delete(attr_id);
	}
	
	/**
	 * 查询属性及其对应属性值
	 * @param map module_type: 属性所属模块; cat_attr: 商品分类id串(用,分隔); attr_types: 属性类型串(用,分隔)
	 * @return
	 * @author 陈显革
	 * @date 2014-09-19
	 */
	public List<Map<String, Object>> getCategoryAttrListWithValue(Map<String, Object> map) {
		return mapper.getCategoryAttListrWithValue(map);
	}
	
	
	/**
	 * 根据分类查找商品属性
	 * @return
	 */
	public List<Map<String, Object>> getCategoryAttrByCat_attr(Map<String, Object> map){
		return mapper.getCategoryAttrByCat_attr(map);
	}
}
