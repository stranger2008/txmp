package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xingfugo.business.dao.CategoryDao;
import com.xingfugo.business.module.Category;

/**
 * @function 功能 实体分类Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Tue Sep 02 14:07:15 CST 2014
 */

@Service
public class CategoryService extends GenericService<Category,String>{

	private CategoryDao categoryDao;
	
	public CategoryService() {}
	
	@Autowired
	public CategoryService(CategoryDao categoryDao) {
		super(categoryDao);
		this.categoryDao = categoryDao;
	}

	private static final String KEY_CAT_CHILD_LIST = "childs_cat";
	
	private static final String UP_CAT_ID = "1111111111";
	//分类类别
	private static final String DEFAULT_MODULE_TYPE = "goods";
	/**
	 * 根据上级id,查询显示的商品分类列表
	 * @param up_cat_id
	 * @return
	 */
	public List getCategoryByUpCatId(String up_cat_id) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("up_cat_id", up_cat_id);
		param.put("module_type", DEFAULT_MODULE_TYPE);
		return categoryDao.getCategoryByUpCatId(param);
	}
	
	/**
	 * 根据上级id,查询显示的分类列表
	 * @param up_cat_id 父级节点id
	 * @param module_type 分类类别（module_type==null,查询商品分类（goods））
	 * @return
	 */
	public List getCategoryByUpCatId(String up_cat_id , String module_type) {
		if(module_type==null){
			module_type = DEFAULT_MODULE_TYPE;
		}
		Map<String,String> param = new HashMap<String,String>();
		param.put("up_cat_id", up_cat_id);
		param.put("module_type", module_type);
		return categoryDao.getCategoryByUpCatId(param);
	}
	
	public List getDetailCatTree(String cat_attr){
		return categoryDao.getDetailCatTree(cat_attr);
	}
	
	public Category getDetailByPk(String cat_id){
		return categoryDao.getByPk(cat_id);
	}
	
	/**
	 * 根据传入的分类ID找出父级分类树结构
	 * 主要用于前台商品列表页的面包屑位置
	 */
	public void setCatTreeByCatid(List<Category> posTreeList,String cat_id){
		Category category = categoryDao.getByPk(cat_id);
		if(category != null && category.getUp_cat_id() != null){
			String up_cat_id = category.getUp_cat_id();
			if(!up_cat_id.equals(UP_CAT_ID)){
				setCatTreeByCatid(posTreeList,up_cat_id);
			}
			posTreeList.add(category);
		}
	}
	
	/** 根据上级ID得到分类数据，并封装该级分类数据的下级数据
	 * 2014-05-16
	 * 李良林 
	 */
	public List getCatWithDownCatByUpCatId(String up_cat_id){
		List catList = getCategoryByUpCatId(up_cat_id);
		if(CollectionUtils.isEmpty(catList)){
			return Collections.EMPTY_LIST;
		}
		
		//构建父级ID列表，用于查询子分类
		int size = catList.size();
		List<String> parentIds = new ArrayList<String>(size);
		Iterator itr = catList.iterator();
		while(itr.hasNext()){
			Map catMap = (HashMap)itr.next();
			String cat_id = "";
			if(catMap.get("cat_id") != null){
				cat_id = catMap.get("cat_id").toString();
			}
			parentIds.add(cat_id);
		}
		
		//根据父级分类ID列表，查询对应的下级分类
		List childCatList = categoryDao.getCategoryByUpCatIds(parentIds);
		if(CollectionUtils.isEmpty(childCatList)){
			return catList;
		}
		
		//将下级分类设置到相应的父级分类当中
		List resultList = new ArrayList(size);
		itr = catList.iterator();
		while(itr.hasNext()){
			Map catMap = (HashMap)itr.next();
			String cat_id = "";
			if(catMap.get("cat_id") != null){
				cat_id = catMap.get("cat_id").toString();
			}
			
			List childList = new ArrayList();
			boolean founded = false;
			Iterator childIt = childCatList.iterator();
			while(childIt.hasNext()){
				Map childCatMap = (HashMap)childIt.next();
				String child_up_cat_id = "";
				if(childCatMap.get("up_cat_id") != null){
					child_up_cat_id = childCatMap.get("up_cat_id").toString();
				}
				
				if(founded && !cat_id.equals(child_up_cat_id)){
					break;
				}
				
				if(cat_id.equals(child_up_cat_id)){
					founded = true;
					childList.add(childCatMap);
					childIt.remove();
				}
			}
			
			//将子分类列表添加到父分类对象中
			if(founded){
				catMap.put(KEY_CAT_CHILD_LIST, childList);
			}
			else{
				catMap.put(KEY_CAT_CHILD_LIST, null);
			}
			
			resultList.add(catMap);
		}
		
		return resultList;
	}
	
	/**
	 * 根据上级id,查询子分类id
	 * @param up_cat_id
	 * @return
	 */
	public List<String> getCategoryByParentId(String up_cat_id) {
		List idlist = categoryDao.getCategoryByParentId(up_cat_id);
		if(idlist==null||idlist.isEmpty()){
			return null;
		}
		List<String> result = new ArrayList<String>();
		for(Object obj : idlist){
			Map idInfo = (Map)obj;
			String id = (String)idInfo.get("cat_id");
			result.add(id);
		}
		return result;
	}
	
	/**
	 * 查询分类对象是否已经存在
	 * @param category
	 * @return
	 */
	public boolean hasExist(Category category){
		int num = categoryDao.hasExist(category);
		return num>0;
	}
	
	/**
	 * 根据分类ID串找出对应的分类信息
	 * @param cat_ids
	 * @return
	 */
	public Map<String,String> getCatMapByIds(String cat_ids){
		List<Map<String,String>> catInfoList= categoryDao.getCatMapByIds(cat_ids);
		if(catInfoList==null||catInfoList.isEmpty()){
			return null;
		}
		Map<String,String> result = new HashMap<String,String>();
		for(Map<String,String> catInfo : catInfoList){
			result.put(catInfo.get("cat_id"),catInfo.get("cat_name"));
		}
		return result;
	}
	
	/**
	 * 根据分类id,取得所有上级(包含自己)
	 * @param cat_id分类id
	 * @return
	 * @author 陈显革
	 * @date 2014-09-16
	 */
	public List getParentsByCatid(String cat_id) {
		return categoryDao.getParentsByCatid(cat_id);
	}
	/**
	 * 批量修改是否显示状态
	 * @param info
	 */
	public int updateWhetherDisplay(Map info){
		return categoryDao.updateWhetherDisplay(info);
	}

	public List<Map<String, Object>> getListForStatic(
			Map<String, String> attrMap, String mainHostName) {
		return categoryDao.getListForStatic(attrMap);
	}
	
	public List<Map<String,Object>> getAllListForStatic(
			Map<String, String> attrMap){
		return categoryDao.getAllListForStatic(attrMap);
	}
}

