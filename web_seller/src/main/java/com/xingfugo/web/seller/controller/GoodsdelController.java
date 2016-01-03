package com.xingfugo.web.seller.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Category;
import com.xingfugo.business.module.Goodsbrand;
import com.xingfugo.business.module.SellerGoodsWithBLOBs;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.SellerGoodsQueryForm;
import com.xingfugo.business.service.CategoryAttrService;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.GoodsbrandService;
import com.xingfugo.business.service.InfoAttrService;
import com.xingfugo.business.service.SellerGoodsService;
import com.xingfugo.web.seller.common.SessionUtil;

@Controller
public class GoodsdelController extends BaseController{
	
	//0：未审核 1：审核通过 2：审核不通过 3：禁用
	private static final String GOODS_STATUS_UNAUDIT = "0";
	private static final String GOODS_STATUS_ENABLE = "1";
	
	//分类属性类型,默认单行文本框
	private static final String CATEGORY_ATTR_TYPE_TEXT = "0";
	//多行文本框
	private static final String CATEGORY_ATTR_TYPE_TEXTAREA = "1";
	//单选按钮
	private static final String CATEGORY_ATTR_TYPE_RADIO = "2";
	//复选框
	private static final String CATEGORY_ATTR_TYPE_CHECKBOX = "3";
	//模块类型
	private static final String MODULE_TYPE_GOODS = "goods";
	//未删除商品
	private static final String GOOD_DELETED_NO = "1";
	
	@Autowired
	private SellerGoodsService sellerGoodsService;
	
	@Autowired
	private GoodsbrandService goodsbrandService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryAttrService categoryAttrService;
	
	@Autowired
	private InfoAttrService infoAttrService;
	
	//商品列表
	@RequestMapping(value="goodsdel/list")
	public String goodsPageListQuery(SellerGoodsQueryForm queryForm,
			@ModelAttribute("loginCustId") Integer custId, ModelMap modelMap) {

		String info_state = queryForm.getInfo_state();
		queryForm.setCust_id(custId);
		queryForm.setIs_del("0");
		
		PageResult pageResult = sellerGoodsService.getGoodsListByPage(queryForm);
		pageOper(modelMap, pageResult);
		
		List<Map<String, Object>> goodsStatusCounts = sellerGoodsService.getGoodsStatusCounts(queryForm);
		
		modelMap.addAttribute("goodsStatusCounts", goodsStatusCounts);

		modelMap.addAttribute("goodsQueryForm", queryForm);
		
		//基本信息列表
		return "goodsdel/index";
	}
	@ModelAttribute("loginCustId")
	private Integer getLoginCustId(HttpServletRequest request){
		String session_cust_id = SessionUtil.getString(request, Constants.SESSION_CUST_ID);
		return Integer.parseInt(session_cust_id);
	}
	
	//单个商品还原
	@RequestMapping(value="goodsdel/return", method=RequestMethod.GET)
	public String deleteGoods(Integer goodsId, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoods = sellerGoodsService.detailBaseInfo(goodsId);
		if(sellerGoods.getGoodsId() != null){
			sellerGoodsService.returnByPrimaryKey(goodsId);
			operatePrompt(rAttr, "成功还原商品["+sellerGoods.getGoodsName()+"]");
		}
		return "redirect:/goodsdel/list.action";
	}
	//商品批量还原
	@RequestMapping(value="goodsdel/batchreturn", method=RequestMethod.POST)
	public String batchDeleteGoods(Integer[] goodsId, RedirectAttributes rAttr){
		if(goodsId == null || goodsId.length == 0){
			return "redirect:/goodsdel/list.action";
		}
		List<Integer> delGoodsIdList = Arrays.asList(goodsId);
		sellerGoodsService.batchreturn(delGoodsIdList);
		operatePrompt(rAttr, "成功还原["+delGoodsIdList.size()+"]个商品");
		return "redirect:/goodsdel/list.action";
	}
	//单个商品删除
	@RequestMapping(value="goodsdel/del", method=RequestMethod.GET)
	public String delete(Integer goodsId, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoods = sellerGoodsService.detailBaseInfo(goodsId);
		if(sellerGoods.getGoodsId() != null){
			sellerGoodsService.deletegoodsByPrimaryKey(goodsId);
			operatePrompt(rAttr, "成功还原商品["+sellerGoods.getGoodsName()+"]");
		}
		return "redirect:/goodsdel/list.action";
	}
	
	
	//根据分类取品牌 参数：分类串，品牌ID
	public  Map<String,Object> getBrandList(String catAttr,String brind_id)
	{	
		Goodsbrand goodsbrand  = new Goodsbrand();
		String [] catAttrList = catAttr.split(",");
		List<String> catAttr_list_s = new ArrayList<String>();
		for(int i=0;i<catAttrList.length;i++){
			catAttr_list_s.add(catAttrList[i]);
		}
		Map brandMap = new HashMap();
		brandMap.put("catAttr_list_s", catAttr_list_s);
		brandMap.put("info_state",0);
		List<Map<String,Object>> brandList = this.goodsbrandService.selectByGoodsAttr(brandMap);
		Map<String,Object> brandsMap = new HashMap<String,Object>();
		if(brind_id !=null && !"".equals(brind_id)){
			goodsbrand = this.goodsbrandService.getByPk(brind_id);
			if(goodsbrand!=null)
			{
				String info_state = goodsbrand.getInfo_state();
				if(info_state!=null && !"".equals(info_state) && "0".equals(info_state))
					brandsMap.put(goodsbrand.getBrand_id(),goodsbrand.getBrand_name());
			}
		}
		brandsMap.put("","请选择");
		if(!brandList.isEmpty())
			for(Map<String,Object> map : brandList){
				if(map.get("brand_id")!=null && map.get("brand_name")!=null){					
					if(brind_id !=null && goodsbrand != null){
						goodsbrand = this.goodsbrandService.getByPk(brind_id);
						if(!(goodsbrand.getBrand_id().toString()).equals(map.get("brand_id").toString()))
							brandsMap.put(map.get("brand_id").toString(),map.get("brand_name"));
					}
					else
						brandsMap.put(map.get("brand_id").toString(),map.get("brand_name"));
				}
			}
		return brandsMap;
	}
	
	/**
	 * 给商品已选择的属性值添加is_check: true
	 * @param cat_attr
	 * @return
	 */
	private Map<String, List<Map<String, Object>>> cookCategoryAttr(String cat_attr, List<Map<String, Object>> checkedAttrs) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("module_type", MODULE_TYPE_GOODS);
		map.put("attr_types", CATEGORY_ATTR_TYPE_TEXT + "," + CATEGORY_ATTR_TYPE_RADIO + "," + CATEGORY_ATTR_TYPE_CHECKBOX);
		map.put("cat_attrs", cat_attr);
		List<Map<String, Object>> categoryAttrList = categoryAttrService.getCategoryAttrListWithValue(map);
		
		Map<String, List<Map<String, Object>>> categoryAttrs = new HashMap<String, List<Map<String, Object>>>();
		if(categoryAttrList != null && categoryAttrList.size() > 0) {
			for(Map<String, Object> ca : categoryAttrList) {
				String attr_id = (String) ca.get("attr_id");
				List<Map<String, Object>> cas = categoryAttrs.get(attr_id);
				if(cas == null) {
					cas = new ArrayList<Map<String, Object>>();
					categoryAttrs.put(attr_id, cas);
				}
				cas.add(ca);
			}
		}
		
		if(checkedAttrs == null) {
			return categoryAttrs;
		}
		
		//遍历已有属性
		for(Map<String, Object> ia : checkedAttrs) {
			String attr_id = (String) ia.get("attr_id");//属性id
			String value_id = (String) ia.get("value_id");//属性值id
			String attr_value = (String) ia.get("attr_value");//单行文本框,属性值
			
			List<Map<String, Object>> cas = categoryAttrs.get(attr_id);
			if(cas != null && cas.size() > 0) {
				//遍历所有属性,如果商品已添加此属性,则添加is_checked: true;
				for(Map<String, Object> ca : cas) {
					String attr_type = (String) ca.get("attr_type");
					String trade_id = (String) ca.get("trade_id");
					//如果是单行文本框,值为attr_value
					if(CATEGORY_ATTR_TYPE_TEXT.equals(attr_type)) {
						ca.put("attr_value", attr_value);
					} else if(CATEGORY_ATTR_TYPE_RADIO.equals(attr_type) || CATEGORY_ATTR_TYPE_CHECKBOX.equals(attr_type)) {
						//单选按钮,复选框
						if(value_id != null && value_id.trim().length() > 0) {
							String[] trade_idArr = value_id.split("/");
							Set<String> trade_ids = new HashSet<String>();
							for(String tradeId : trade_idArr) {
								if(tradeId.trim().length() > 0) {
									trade_ids.add(tradeId.trim());
								}
							}
							for(String tradeId : trade_ids) {
								if(tradeId.equals(trade_id)) {
									ca.put("is_checked", true);
									trade_ids.remove(tradeId);
									break;
								}
							}
						}
					}
				}
			}
		}
		return categoryAttrs;
	}
	
}