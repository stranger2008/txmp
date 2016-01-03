package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.GoodsDao;
import com.xingfugo.business.module.AttrValue;
import com.xingfugo.business.module.Goods;
import com.xingfugo.business.module.SimpleGoodsCartItem;
import com.xingfugo.business.module.mybatis.DefaultPagingBean;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.PageResultBuilder;
import com.xingfugo.util.SortFieldModifyer;
import com.xingfugo.util.SortFiledHandler;

@Service
public class GoodsService{
	public static final String[] SORT_FIELD_MAPPER = {"g.up_date", "g.saled_num", "g.sale_price"};
	public static final String SORT_FIELD_DEFAULT = "g.up_date desc";

	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private InfoAttrService infoAttrService;
	@Autowired
	private AttrValueService attrValueService; 
	@Autowired
	private CategoryAttrService categoryAttrService;

	/**
	 * web版 天下名品 商品列表查询方法
	 * @param goodsQueryForm
	 * @return
	 */
	public PageResult getGoodsListByPage_web(GoodsQueryForm goodsQueryForm){
		int totalNum = goodsDao.getTotalNum(goodsQueryForm);
		goodsQueryForm.getPg().setTotalRecords(totalNum);
		 
		//转换排序编码为具体的排序字段信息
		String sortCode = goodsQueryForm.getPg().getSortCode();
		String sortField = handleSortField(sortCode);
		goodsQueryForm.getPg().setSortField(sortField);
		
		List<Map<String, Object>> list = goodsDao.getGoodsList(goodsQueryForm);

		ImgPathUitls.filterImagePath_spec(list,200,200);
		
		
		PageResult result = new PageResult();
		DefaultPagingBean pagingBean = goodsQueryForm.getPg();
		pagingBean.setTotalRecords(totalNum);
		result.setTotal(list == null ? 0 : (int)pagingBean.getTotalRecords());
		result.setSize(pagingBean.getSize());
		result.setCurPage((int)pagingBean.getCurrentPageNum());
		result.setTotalPage((int)pagingBean.getTotalPageNum());
		result.setCurTotal(list == null ? 0 : list.size());
		result.setList(list);
		return result;
	}
	/**
	 * 商品列表分页查询
	 * @param goodsQueryForm
	 * @return
	 */
	public PageResult getGoodsListByPage(GoodsQueryForm goodsQueryForm){
		//转换排序编码为具体的排序字段信息
		String sortCode = goodsQueryForm.getPg().getSortCode();
		String sortField = handleSortField(sortCode);
		goodsQueryForm.getPg().setSortField(sortField);
		
		List<Map<String, Object>> list = goodsDao.getGoodsListByPage(goodsQueryForm);
		
		if(StringUtils.isNotBlank(goodsQueryForm.getTrade_id())){
			List<Map<String, Object>>  infoAttrList=infoAttrService.getInfoAttrByValueId(goodsQueryForm.getTrade_id());
			 List<Map<String, Object>> newList =new ArrayList();
			for(int i =0;i<list.size();i++){
				Map goodsMap =list.get(i);
				for(int j =0;j<infoAttrList.size();j++){
					Map infoMap =infoAttrList.get(j);
					if(goodsMap.get("infoattr_id").equals(infoMap.get("infoattr_id"))){
						newList.add(goodsMap);
					}
					
				}
				
			}
			list=newList;
		}
		PageResult result = PageResultBuilder.build(goodsQueryForm.getPg(), list);
		ImgPathUitls.filterImagePath((List<Map<String, Object>>)result.getList());
		return result;
	}
	
	//商品详细页“据浏览猜你喜欢”，现在暂时先取简单的商品数据，后期丰富
	//TODO
	public List getGoodsOfUserLikeWeGuess(){
		List list = goodsDao.getGoodsOfUserLikeWeGuess();
		ImgPathUitls.filterImagePath_spec((List<Map<String, Object>>)list,200,200);
		return list;
	}
	//我的收藏 猜你喜欢
	public List getCollectGoodsOfUserLikeWeGuess(){
		List list = goodsDao.getGoodsOfUserLikeWeGuess();
		ImgPathUitls.filterImagePath_spec((List<Map<String, Object>>)list,70,70);
		return list;
	}
	public Map<String, Object> getGoodsDetailById(int goodsId,String source){
		Map<String, Object> goods = goodsDao.getGoodsDetailById(goodsId);
		//获取商品属性值
		setGoodsAttrValueByInfoattr_id(goods,source);
		return goods;
	}

	public List<Map<String, Object>> getGoodsDetailListByIds(List<Integer> goodsIds){
		List<Map<String, Object>> goodsList = goodsDao.getGoodsDetailListByIds(goodsIds);
		ImgPathUitls.filterImagePath(goodsList);
		return goodsList;
	}
	
	public Map<String, Object> getGoodsDetailWithCustById(int goodsId){
		Map<String, Object> goods = goodsDao.getGoodsDetailWithCustById(goodsId);
		ImgPathUitls.filterImagePath(goods);
		//获取商品属性值
		setGoodsAttrValueByInfoattr_id(goods,"");
		return goods;
	}
	
	public List<Map<String, Object>> getGoodsDetailListWithCustByIds(List<Integer> goodsIds){
		List<Map<String, Object>> goodsList = goodsDao.getGoodsDetailListWithCustByIds(goodsIds);
		ImgPathUitls.filterImagePath(goodsList);
		return goodsList;
	}
	
	public boolean haveEnoughStock(int goodsId){
		Map<String, Object> goods = goodsDao.getGoodsDetailById(goodsId);
		if(goods == null){
			return false;
		}
		
		Integer stock = (Integer)goods.get("now_stock");
		if(stock == null){
			return false;
		}
		
		return (stock > 0);
	}
	
	public void updateStockAndSaledNum(int goodsId){
		goodsDao.updateStockAndSaledNum(goodsId);
	}
	
	public void batchUpdateStockAndSaledNum(List<SimpleGoodsCartItem> goodsCartItemList){
		goodsDao.batchUpdateStockAndSaledNum(goodsCartItemList);
	}
	
	private String handleSortField(String origSort){
		return SortFiledHandler.handle(origSort, SORT_FIELD_MAPPER,
				SORT_FIELD_DEFAULT, new GoodsSortFieldModifyer());		
	}
	
	private class GoodsSortFieldModifyer implements SortFieldModifyer {
		public boolean trigger(int fieldIndex){
			return 1 == fieldIndex;
		}
		
		public String modify(String origStr){
			return origStr + SortFiledHandler.STRING_FIELD_SEPRATOR + "g.up_date desc";
		}
	}
	
	/**
	 * 取得商品列表(分页)
	 * @param goodsQueryForm 查询条件
	 * @return 商品列表
	 * @author 陈显革
	 * @date 2014-09-12
	 */
	public PageResult getListByPage(GoodsQueryForm goodsQueryForm){
		List<Map<String, Object>> list = goodsDao.getGoodsListByPage(goodsQueryForm);
		PageResult result = PageResultBuilder.build(goodsQueryForm.getPg(), list);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getGoodsAttr(GoodsQueryForm goodsQueryForm){
		if(StringUtils.isNotBlank(goodsQueryForm.getCat_id())){
			
			Map map =new HashMap();
//			map.put("attr_type", "3");
			map.put("attr_type_min", "2"); // 分类属性类型的大于等于2， 表示2：单选框 3：复选框
			map.put("is_must", "1");
			map.put("cat_attr", goodsQueryForm.getCat_id());
			//已选择的商品属性id
			String param_attr_id = goodsQueryForm.getAttr_id(); 
			
			if(!StringUtils.isEmpty(param_attr_id)){
				StringBuffer bf = new StringBuffer();
				String[] attr_ids = param_attr_id.split(",");
				for(String attr_id : attr_ids){
					if(StringUtils.isEmpty(attr_id.trim())){
						continue;
					}
					bf.append(",");
					bf.append(attr_id);
				}
				if(bf.length()>0){
					map.put("attr_id" , bf.substring(1));
				}
			}
			List<Map<String, Object>> list =categoryAttrService.getCategoryAttrByCat_attr(map);
			
			StringBuffer sb =new StringBuffer();
			
			 if(list!=null){
				 //存储商品属性信息
				 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
				 for(int i =0;i<list.size();i++){
					 Map attrMap =list.get(i);
					if(!attrMap.isEmpty()){
						String attr_id = attrMap.get("attr_id").toString();
						//过滤提已经选择的商品属性
						sb.append(changeStr("/",",",attr_id)).append(",");
						result.add(attrMap);
						 
					} 
				 }
				 list = result;
				 List<AttrValue> attrValues =null;
				 if(sb.length()>0){
						//查询该商品对应的所有属性值
						attrValues = attrValueService.selectByAttr_id(sb.substring(0, sb.length()-1).toString());
				 }
				 
				 if(attrValues!=null){
					 for(Map categoryMap:list){
						 List<AttrValue> attrValueList = new ArrayList();
						 for(AttrValue attrValuesMap : attrValues){
								 if(categoryMap.get("attr_id").equals(attrValuesMap.getAttr_id())){
									 attrValueList.add(attrValuesMap);
								 }
						 }
						 categoryMap.put("attrValue", attrValueList);
					 }
				 }
				 
			 }
			 return list;
		}
		return null;
		
	}
	
	
	//根据商品infoattr_id找出商品的属性值
	@SuppressWarnings("unchecked")
	public void setGoodsAttrValueByInfoattr_id(Map goodsMap,String source){
		String infoattr_id="";
		if(goodsMap.get("infoattr_id")!=null)
			infoattr_id = goodsMap.get("infoattr_id").toString();
		
		List<Map<String, Object>> infoAttrList;
		if("web".equals(source)){
			infoAttrList= infoAttrService.getGoodsAtrrsByInfoId(infoattr_id);
		}else{
			infoAttrList= infoAttrService.selectByInfoId(infoattr_id);
		}
		
		
		//属性的id串拼接
		StringBuffer attrSb = new StringBuffer();
		String attrIds="";
		//属性值的id串拼接
		StringBuffer attr_valueIdSb = new StringBuffer();
		String attr_valueIds="";
		List<Map<String,String>> attr_inputValue = new ArrayList<Map<String,String>>();
		for (Map<String, Object> map : infoAttrList) {
			//属性id串的拼接
			if(map.get("attr_id")!=null){
				attrIds = map.get("attr_id").toString();
				attrSb.append(attrIds);
				attrSb.append(",");
			}
			//属性值id串的拼接
			if(map.get("value_id")!=null&&!StringUtils.isBlank(map.get("value_id").toString())){
				attr_valueIds = map.get("value_id").toString();
				attr_valueIds = changeStr("/",",",attr_valueIds);
				attr_valueIdSb.append(attr_valueIds);
				attr_valueIdSb.append(",");
			}else{
				Map<String,String> attmap = new HashMap<String,String>();
				attmap.put("attr_id", attrIds);
				if(map.get("attr_value")!=null)
					attmap.put("attr_inputValue", map.get("attr_value").toString());
				attr_inputValue.add(attmap);
			}
			
		}
		if(attrSb.length()>0)
			attrIds = attrSb.substring(0, attrSb.length()-1);
		if(attr_valueIdSb.length()>0)
			attr_valueIds = attr_valueIdSb.substring(0, attr_valueIdSb.length()-1);
		
		List<Map<String, Object>> attrs =null;
		if(attrIds.length()>0){
			//查询该商品对应的所有属性
			attrs = categoryAttrService.selectByIds(attrIds);
		}
		List<Map<String, Object>> attrValues  = null;
		
		if(attr_valueIds.length()>0)
			//查询该商品对应的所有属性值
			attrValues = attrValueService.selectByTradeId(attr_valueIds);
			
		List<Map<String, Object>> attrValueList = null;
		//循环取出商品属性值保存成集合，并作为一个字段存入属性实体中。
		for (int i = 0;attrs!=null&&i<attrs.size();i++) {
			Map<String, Object> attr = attrs.get(i);
			attrValueList = new ArrayList<Map<String,Object>>();
			for (int j = 0 ;attrValues!=null&&j<attrValues.size();j++) {
				Map<String, Object> attrValue = attrValues.get(j);
				if(attrValue.get("attr_id")!=null&&attr.get("attr_id")!=null){
					if(attrValue.get("attr_id").toString().equals(attr.get("attr_id").toString())){
						attrValueList.add(attrValue);
					}
				}
			}
			attr.put("attrValue", attrValueList);
		}
		if("web".equals(source)){
			goodsMap.put("attrInputs", attr_inputValue);
		}
		goodsMap.put("attrs", attrs);
	}
	
	/**
	 * 传入 “/” 和 “，” 返回逗号隔开的字符串
	 * @param oldStr
	 * @param newStr
	 * @param str
	 * @return
	 */
	private String changeStr(String oldStr,String newStr,String str){
		if(str.indexOf(oldStr)==-1){
			return str;
		}
		str = str.replaceAll(oldStr, newStr);
		if(str.startsWith(newStr)){
			str = str.substring(1);
		}
		if(str.endsWith(newStr)){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	/**
	 * 根据商品id,查询商品信息
	 * @param goods_id 商品id
	 * @return 商品信息对象
	 */
	public Goods getByPk(String goods_id) {
		return goodsDao.getByPk(goods_id);
	}
	
	/**
	 * 更新商品状态,当未审核通过时,可附加未通过原因
	 * @param map
	 */
	public void updateGoodsStatus(Map<String, String> map) {
		goodsDao.updateGoodsStatus(map);
	}
	
	public List<Map<String, Object>> getListForStatic(Map<String, String> attrMap, String mainHostName){
		List<Map<String,Object>> list =  goodsDao.getListForStatic(attrMap);
		for (Map<String, Object> map : list) {
			if(map.get("img_path")!=null){
				map.put("img_path",ImgPathUitls.filterImagePath(map.get("img_path").toString()));
			}
		}
		return list;
	}
	
	/**
	 * 删除商品
	 * @param goods_id
	 */
	public void delete(String goods_id) {
		goodsDao.delete(goods_id);
	}
	
	/**
	 * 查询各状态的商品数量
	 * @param goodsQueryForm(is_del, cust_id, info_states, cat_id)
	 * @return
	 * @author 陈显革
	 * @date 2014-09-18
	 */
	public List<Map<String, Object>> selectGoodsCountGroupByInfoState(GoodsQueryForm goodsQueryForm) {
		return goodsDao.getGoodsStatusCounts(goodsQueryForm);
	}


	public void returnByPrimaryKey(Integer goodsId) {
		goodsDao.returnByPrimaryKey(goodsId);
		
	}


	public void delete_0(Integer id) {
		goodsDao.delete_0(id);
		
	}


	public void batchreturn(List<Integer> delGoodsIdList) {
		goodsDao.batchreturn(delGoodsIdList);
		
	}
	
	public List<AttrValue> getSearchInfoName(List<String> tradeIds ){
		if(tradeIds==null || tradeIds.isEmpty()){
			return null;
		}
		List<AttrValue> selInfoMap = new ArrayList<AttrValue>();
		//获取以选择属性
		StringBuffer id_bf = new StringBuffer();
		for(String id :tradeIds) {
			if(StringUtils.isEmpty(id.trim())){
				continue;
			}
			if(id_bf.length()>0){
				id_bf.append(",");
			}
			if(id.endsWith(",")){
				id = id.substring(0,id.lastIndexOf(","));
			}
			id_bf.append(id);
		}
		if(id_bf.length()<=0){
			return null;
		}
		List<Map<String, Object>> values = attrValueService.selectByTradeId(id_bf.toString());
		if(values!=null && !values.isEmpty()){
			for(String id :tradeIds) {
				String valNm = "";
				String valStr = "";
				String val_attr_id = "";
				if(id.indexOf(",")==-1){
					for(Map<String, Object> info : values){
						String tradeid = (String)info.get("trade_id"); //attr_id attr_name attr_value
						if(id.equals(tradeid)){
							valNm = (String)info.get("attr_name") ; 
							valStr = (String)info.get("attr_value");
							val_attr_id = (String)info.get("attr_id");
						}
					}
				}else{
					String[] ids = id.split(",");
					for(String tempId : ids){
						if(StringUtils.isEmpty(tempId)){
							continue;
						}
						for(Map<String, Object> info : values){
							String tradeid = (String)info.get("trade_id"); //attr_id attr_name attr_value
							String attr_value = (String)info.get("attr_value");
							String attr_name = (String)info.get("attr_name");
							if(tempId.equals(tradeid)){
								valNm = attr_name ; 
								val_attr_id = (String)info.get("attr_id");
								valStr += "、"+attr_value;
							}
						}
					}
					if(valStr.startsWith("、")){
						valStr = valStr.substring(1);
					}
				}
				AttrValue attr= new AttrValue();
				String info = valNm + ":" + valStr;
				attr.setTrade_id(id);
				attr.setAttr_name(info);
				attr.setAttr_id(val_attr_id);
				selInfoMap.add(attr);
			}
		}
		return selInfoMap;
	}
	public List<Map<String, Object>> getAllListForStatic() {
		List<Map<String, Object>> list = goodsDao.getAllListForStatic();
		for (Map<String, Object> map : list) {
			if(map.get("img_path")!=null){
				map.put("img_path",ImgPathUitls.filterImagePath(map.get("img_path").toString()));
			}
		}
		return list;
	}
}
