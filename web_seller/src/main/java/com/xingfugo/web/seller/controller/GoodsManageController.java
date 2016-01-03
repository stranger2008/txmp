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
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Category;
import com.xingfugo.business.module.CategoryAttr;
import com.xingfugo.business.module.CategoryAttrValue;
import com.xingfugo.business.module.GoodsAttr;
import com.xingfugo.business.module.GoodsStockHistory;
import com.xingfugo.business.module.Goodsbrand;
import com.xingfugo.business.module.SellerGoodsWithBLOBs;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsStockHistoryQueryForm;
import com.xingfugo.business.module.query.SellerGoodsQueryForm;
import com.xingfugo.business.service.CategoryAttrService;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.GoodsbrandService;
import com.xingfugo.business.service.InfoAttrService;
import com.xingfugo.business.service.MembercatService;
import com.xingfugo.business.service.SellerGoodsService;
import com.xingfugo.web.seller.common.SessionUtil;

@Controller
@RequestMapping("/goods")
public class GoodsManageController extends BaseController{
	
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
	
	@Autowired
	private  MembercatService membercatService ;
	
	
	//商品列表
	@RequestMapping(value="/list")
	public String goodsPageListQuery(SellerGoodsQueryForm queryForm,
			@ModelAttribute("loginCustId") Integer custId, ModelMap modelMap) {

		String info_state = queryForm.getInfo_state();
		if(info_state == null || info_state.trim().length() == 0) {
			queryForm.setInfo_state(GOODS_STATUS_ENABLE);
		}
		queryForm.setCust_id(custId);
		queryForm.setIs_del(GOOD_DELETED_NO);
		
		PageResult pageResult = sellerGoodsService.getGoodsListByPage(queryForm);
		pageOper(modelMap, pageResult);
		
		List<Map<String, Object>> goodsStatusCounts = sellerGoodsService.getGoodsStatusCounts(queryForm);
		
		modelMap.addAttribute("goodsStatusCounts", goodsStatusCounts);

		modelMap.addAttribute("goodsQueryForm", queryForm);
		
		//基本信息列表
		return "/goods/list";
	}
	
	//商品库存列表
	@RequestMapping(value="/stockList")
	public String goodsStatePageListQuery(SellerGoodsQueryForm queryForm,
			@ModelAttribute("loginCustId") Integer custId, ModelMap modelMap) {
		
		queryForm.setCust_id(custId);
		queryForm.setIs_del(GOOD_DELETED_NO);
		
		PageResult pageResult = sellerGoodsService.getGoodsListByPage(queryForm);
		pageOper(modelMap, pageResult);
		
		modelMap.addAttribute("goodsQueryForm", queryForm);
		return "/goods/stockList";
	}
	
	//商品上下架列表
	@RequestMapping(value="/stateList")
	public String goodsStockPageListQuery(SellerGoodsQueryForm queryForm,
			@ModelAttribute("loginCustId") Integer custId, ModelMap modelMap) {
		
		queryForm.setCust_id(custId);
		queryForm.setIs_del(GOOD_DELETED_NO);
		
		PageResult pageResult = sellerGoodsService.getGoodsListByPage(queryForm);
		pageOper(modelMap, pageResult);
		
		modelMap.addAttribute("goodsQueryForm", queryForm);
		return "/goods/stateList";
	}
	
	//商品分类选择
	@RequestMapping(value="/category")
	public String changeCategory(String catAttr, String goodsId,  ModelMap modelMap){
		modelMap.addAttribute("catAttr", catAttr);
		modelMap.addAttribute("goodsId", goodsId);
		return "/goods/category";
	}
	
	//商品新增
	@RequestMapping(value="/new")
	public String newGoods(String catAttr, ModelMap modelMap){
		if(!StringUtils.isNotBlank(catAttr)){
			return "redirect:/goods/category.action";
		}
		
		SellerGoodsWithBLOBs goods = new SellerGoodsWithBLOBs();
		goods.setCatAttr(catAttr);
		
		//根据分类取品牌
		Map<String,Object> brandsMap  = this.getBrandList(catAttr,null);
		String autoGoodsNumber ="GN-"+(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		goods.setGoodsNo(autoGoodsNumber);	
		modelMap.addAttribute("brandMap", brandsMap);
		modelMap.addAttribute("sellerGoodsWithBLOBs", goods);
				
		List<Category> catTree = categoryService.getDetailCatTree(catAttr);
		StringBuffer buf = new StringBuffer();
		for(Category cat : catTree){
			if(buf.length() > 0){
				buf.append(",");
			}
			buf.append(cat.getCat_name());
		}
		modelMap.addAttribute("catAttrName", buf.toString());
		//商品属性
		Map<String, List<Map<String, Object>>> categoryAttrs = this.cookCategoryAttr(catAttr, null);
		modelMap.addAttribute("categoryAttrs", categoryAttrs);
		
		return "/goods/new";
	}
	
	//商品详情
	@RequestMapping(value="/detail/{goodsId}")
	public String goodsDetail(@PathVariable("goodsId") Integer goodsId,String catAttr, ModelMap modelMap) throws Exception{
		SellerGoodsWithBLOBs sellerGoods = sellerGoodsService.detail(goodsId);
		String tempCatAttr = sellerGoods.getCatAttr();		
		//显示重新选择后的商品分类
		if(StringUtils.isNotBlank(catAttr)){
			tempCatAttr = catAttr;
			sellerGoods.setCatAttr(catAttr);
		}
		
		//获取商品分类
		List<Category> catTree = categoryService.getDetailCatTree(tempCatAttr);
		StringBuffer buf = new StringBuffer();
		for(Category cat : catTree){
			if(buf.length() > 0){
				buf.append(",");
			}
			buf.append(cat.getCat_name());
		}
		modelMap.addAttribute("catAttrName", buf.toString());
		//根据分类取品牌
		String Brand_id = null;
		if(sellerGoods.getBrand_id()!=null && !"".equals(sellerGoods.getBrand_id()))
		{
			Brand_id = sellerGoods.getBrand_id().toString();
		}
		Map<String,Object> brandsMap  = this.getBrandList(sellerGoods.getCatAttr(),Brand_id);
		modelMap.addAttribute("brandMap", brandsMap);
		
		//自定义分类
		String membercat = sellerGoods.getMembercat();
		if(StringUtils.isNotBlank(membercat)){
			modelMap.addAttribute("membercatList", setMembercatName(membercat));
		}
		
		//*********************以下代码由陈显革编写*******************//
		//查询商品所有属性
		//商品已有属性
		List<Map<String, Object>> infoAttrs = infoAttrService.selectById(sellerGoods.getInfoattrId());
		
		Map<String, List<Map<String, Object>>> categoryAttrs = this.cookCategoryAttr(tempCatAttr, infoAttrs);
		modelMap.addAttribute("categoryAttrs", categoryAttrs);
		modelMap.addAttribute("sellerGoodsWithBLOBs", sellerGoods);
		return "/goods/detail";
	}
	
	//单个商品删除
	@RequestMapping(value="/del", method=RequestMethod.GET)
	public String deleteGoods(Integer goodsId, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoods = sellerGoodsService.detailBaseInfo(goodsId);
		if(sellerGoods.getGoodsId() != null){
			sellerGoodsService.del(goodsId);
			operatePrompt(rAttr, "成功删除商品["+sellerGoods.getGoodsName()+"]");
		}
		return "redirect:/goods/list.action?info_state=" + sellerGoods.getInfoState();
	}
	
	//商品批量删除
	@RequestMapping(value="/batchDel", method=RequestMethod.POST)
	public String batchDeleteGoods(Integer[] goodsId, RedirectAttributes rAttr){
		if(goodsId == null || goodsId.length == 0){
			return "redirect:/goods/list.action";
		}
		List<Integer> delGoodsIdList = Arrays.asList(goodsId);
		sellerGoodsService.batchDel(delGoodsIdList);
		operatePrompt(rAttr, "成功删除["+delGoodsIdList.size()+"]个商品");
		return "redirect:/goods/list.action";
	}
	
	//商品信息保存，包含新增和修改
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveGoods(@Valid SellerGoodsWithBLOBs sellerGoods, BindingResult result,
			@ModelAttribute("loginCustId") Integer custId, 
			ModelMap modelMap, RedirectAttributes rAttr) throws Exception{
		boolean isNew = (sellerGoods.getGoodsId() == null);
		
		//存放已经选择的属性
		List<Map<String, Object>> checkedAttrs = new ArrayList<Map<String, Object>>();;
		
		List<GoodsAttr> goodsAttrList = sellerGoods.getGoodsAttrList();
		if(!goodsAttrList.isEmpty()){
			int i = 0;
			for(GoodsAttr goodsAttr : goodsAttrList){
				//遍历已经选择的属性,放进checkedAttrs
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("attr_id", goodsAttr.getAttrId());
				String value_id = "";
				String attr_value = "";
				
				List<CategoryAttrValue> cavs = goodsAttr.getAttrValueList();
				if(cavs != null && cavs.size() > 0) {
					for(CategoryAttrValue cav: cavs) {
						value_id = value_id + (cav.getTradeId() == null ? cav.getTradeId() : cav.getTradeId().replace(",", "/")) + "/";
						attr_value = cav.getAttrValue();
					}
				}
				attr.put("value_id", value_id);
				attr.put("attr_value", attr_value);
				checkedAttrs.add(attr);
				
				if(!goodsAttr.isRequired()){
					i++;
					continue;
				}
				
				if(goodsAttr.getAttrValueList().isEmpty()){
					result.rejectValue("goodsAttrList["+i+"]", "NotEmpty");
				} else {
					String attr_type = goodsAttr.getAttrType();
					if(CATEGORY_ATTR_TYPE_TEXT.equals(attr_type) || CATEGORY_ATTR_TYPE_TEXTAREA.equals(attr_type)) {
						List<CategoryAttrValue> ca = goodsAttr.getAttrValueList();
						if("".equals(ca.get(0).getAttrValue().trim())) {
							result.rejectValue("goodsAttrList["+i+"]", "NotEmpty");
						} else if(ca.size() > 1) {
							result.rejectValue("goodsAttrList["+i+"]",null , "值过多");
						}
					}
				}
				i++;
			}
		}
		
		//自定义分类
		String membercat = sellerGoods.getMembercat();
		if(StringUtils.isNotBlank(membercat)){
			modelMap.addAttribute("membercatList", setMembercatName(membercat));
		}
		//根据分类取品牌
		String Brand_id = null;
		if(sellerGoods.getBrand_id()!=null && !"".equals(sellerGoods.getBrand_id()))
		{
			Brand_id = sellerGoods.getBrand_id().toString();
		}
		Map<String,Object> brandsMap  = this.getBrandList(sellerGoods.getCatAttr(),Brand_id);
		modelMap.addAttribute("brandMap", brandsMap);
		
		if(result.hasErrors()){
			List<Category> catTree = categoryService.getDetailCatTree(sellerGoods.getCatAttr());
			StringBuffer buf = new StringBuffer();
			for(Category cat : catTree){
				if(buf.length() > 0){
					buf.append(",");
				}
				buf.append(cat.getCat_name());
			}
			modelMap.addAttribute("catAttrName", buf.toString());
			modelMap.addAttribute("sellerGoodsWithBLOBs", sellerGoods);
			
			//*********************以下代码由陈显革编写*******************//
			//查询商品所有属性
			Map<String, List<Map<String, Object>>> categoryAttrs = this.cookCategoryAttr(sellerGoods.getCatAttr(), checkedAttrs);
			modelMap.addAttribute("categoryAttrs", categoryAttrs);

			if(isNew){
				return "/goods/new";
			}
			else{
				return "/goods/detail";
			}
		}
		
		sellerGoods.setCustId(custId);
		
		if(isNew){
			sellerGoodsService.insert(sellerGoods);
		}
		else{
			sellerGoodsService.update(sellerGoods);
		}
		
		operatePrompt(rAttr, isNew ? "成功新增商品["+sellerGoods.getGoodsName()+"]" : "成功修改商品["+sellerGoods.getGoodsName()+"]");
		return "redirect:/goods/list.action?info_state=" + GOODS_STATUS_UNAUDIT;
	}
	
	//库存管理（增加或减少库存页面）
	@RequestMapping(value="/stock/{goodsId}", method=RequestMethod.GET)
	public String goodsStockManage(@PathVariable("goodsId") Integer goodsId, 
			ModelMap modelMap){
		SellerGoodsWithBLOBs sellerGoodsWithBLOBs = sellerGoodsService.detailBaseInfo(goodsId);
		GoodsStockHistory goodsStock = new GoodsStockHistory();
		goodsStock.setGoodsId(sellerGoodsWithBLOBs.getGoodsId());
		//默认为增加库存
		goodsStock.setChangeType(true);
		modelMap.addAttribute("goodsStockHistory", goodsStock);
		modelMap.addAttribute("sellerGoodsWithBLOBs", sellerGoodsWithBLOBs);
		return "/goods/stock/manage";
	}
	
	//库存变化保存
	@RequestMapping(value="/stock/update", method=RequestMethod.POST)
	public String goodsStockUpdate(@Valid GoodsStockHistory goodsStock, BindingResult result,
			ModelMap modelMap, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoods = sellerGoodsService.detail(goodsStock.getGoodsId());
		if(result.hasErrors()){
			modelMap.addAttribute("sellerGoodsWithBLOBs", sellerGoods);
			modelMap.addAttribute("goodsStockHistory", goodsStock);
			return "/goods/stock/manage";
		}
		
		//减少库存情况下，不能超过当前库存
		if(!goodsStock.getChangeType() && goodsStock.getChangeAmount() > sellerGoods.getNowStock()){
			modelMap.addAttribute("sellerGoodsWithBLOBs", sellerGoods);
			modelMap.addAttribute("goodsStockHistory", goodsStock);
			result.rejectValue("changeAmount", "", "库存减少量不能大于当前库存");
			return "/goods/stock/manage";
		}
		
		sellerGoodsService.addStock(goodsStock, sellerGoods);
		operatePrompt(rAttr, "成功" + (goodsStock.getChangeType() ? "增加" : "减少") + "库存");
		return "redirect:/goods/stock/history.action?goodsId=" + sellerGoods.getGoodsId();
	}
	
	//库存变化历史列表
	@RequestMapping(value="/stock/history", method=RequestMethod.GET)
	public String goodsStockHistory(GoodsStockHistoryQueryForm queryForm, ModelMap modelMap){
		PageResult pageResult = sellerGoodsService.stockHistoryPageList(queryForm);
		modelMap.addAttribute("goodsId", queryForm.getGoodsId());
		pageOper(modelMap, pageResult);
		modelMap.addAttribute("queryForm", queryForm);
		return "/goods/stock/history";
	}
	
	//商品上架
	@RequestMapping(value="/up/{goodsId}", method=RequestMethod.GET)
	public String goodsUp(@PathVariable("goodsId") Integer goodsId, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoodsWithBLOBs = sellerGoodsService.detailBaseInfo(goodsId);
		sellerGoodsService.changeSaleState(goodsId, true);
		operatePrompt(rAttr, "商品["+sellerGoodsWithBLOBs.getGoodsName()+"]成功上架");
		return "redirect:/goods/stateList.action";
	}
	
	//商品下架
	@RequestMapping(value="/down/{goodsId}", method=RequestMethod.GET)
	public String goodsDown(@PathVariable("goodsId") Integer goodsId, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoodsWithBLOBs = sellerGoodsService.detailBaseInfo(goodsId);
		sellerGoodsService.changeSaleState(goodsId, false);
		operatePrompt(rAttr, "商品["+sellerGoodsWithBLOBs.getGoodsName()+"]成功下架");
		return "redirect:/goods/stateList.action";
	}
	
	@ModelAttribute("loginCustId")
	private Integer getLoginCustId(HttpServletRequest request){
		String session_cust_id = SessionUtil.getString(request, Constants.SESSION_CUST_ID);
		return Integer.parseInt(session_cust_id);
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
	
	
	
	
	
	/**
	 * //把自定义分类转换成分类名称串 4341673348,4335442618|2725876171,3262136646|2725876171|
	 */
	public List setMembercatName(String act_attr_s) throws Exception {
		List<Map<String,String>> act_attr_list = new ArrayList<Map<String,String>>();
		if(StringUtils.isNotBlank(act_attr_s)){
			String act_attr = act_attr_s.replace("|",",");
			String cat_ids = act_attr.substring(0, act_attr.lastIndexOf(","));
			Map<String,String> catMap = membercatService.getCatMapByIds(cat_ids);
			if(catMap == null) {
				return act_attr_list;
			}
			String[] catIds = act_attr_s.split("\\|");
			for(int i = 0;i<catIds.length;i++){
				String[] ids = catIds[i].split(",");
				String cat_attr_name = "";
				Map cat_s  = new HashMap();
				for(int j=0;j<ids.length;j++){
					if(ids[j]==null||"".equals(ids[j])){
						continue;
					}
					String name = catMap.get(ids[j]);
					if(name==null||"".equals(name)){
						continue;
					}
					cat_attr_name = cat_attr_name + name;
					if(j<ids.length-1){
						cat_attr_name = cat_attr_name + ">" ;
					}
				}
				if(!"".equals(catIds[i]) && !"".equals(cat_attr_name))
				{
					cat_s.put("mambercat_id", catIds[i]);
					cat_s.put("mambercat_name", cat_attr_name);
					act_attr_list.add(cat_s);
				}
			}
		}
		return  act_attr_list;
	}
	
	//
	
	
}