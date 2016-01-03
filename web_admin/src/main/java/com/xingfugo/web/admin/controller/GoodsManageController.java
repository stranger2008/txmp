package com.xingfugo.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Category;
import com.xingfugo.business.module.CategoryAttr;
import com.xingfugo.business.module.CategoryAttrValue;
import com.xingfugo.business.module.GoodsAttr;
import com.xingfugo.business.module.Goodsbrand;
import com.xingfugo.business.module.GoodsStockHistory;
import com.xingfugo.business.module.SellerGoodsWithBLOBs;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsStockHistoryQueryForm;
import com.xingfugo.business.module.query.SellerGoodsQueryForm;
import com.xingfugo.business.service.CategoryAttrService;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.GoodsbrandService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.SellerGoodsService;
import com.xingfugo.web.admin.common.SessionUtil;

@Controller
@RequestMapping("/goods")
public class GoodsManageController extends BaseController{
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private SellerGoodsService sellerGoodsService;
	
	@Autowired
	private GoodsbrandService goodsbrandService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryAttrService categoryAttrService;
	
	//商品列表，参数t=1时，库存管理；参数t=2时，上下架过来；其他情况，商品管理
	@RequestMapping(value="/list")
	public String goodsPageListQuery(SellerGoodsQueryForm queryForm,
			@ModelAttribute("loginCustId") Integer custId, ModelMap modelMap) {

		queryForm.setCust_id(custId);
		
		PageResult pageResult = goodsService.getGoodsListByPage(queryForm);
		pageOper(modelMap, pageResult);

		modelMap.addAttribute("goodsQueryForm", queryForm);
		//库存列表
		if(queryForm.getT() == 1){
			return "/goods/stockList";
		}
		
		//上下架列表
		if(queryForm.getT() == 2){
			return "/goods/stateList";
		}
		
		//基本信息列表
		return "/goods/list";
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
	public String newGoods(@ModelAttribute("brandList") List<Goodsbrand> brandList, 
			String catAttr, ModelMap modelMap){
		if(!StringUtils.isNotBlank(catAttr)){
			return "redirect:/goods/category.action";
		}
		
		SellerGoodsWithBLOBs goods = new SellerGoodsWithBLOBs();
		goods.setCatAttr(catAttr);
		
		String autoGoodsNumber ="GN-"+(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		goods.setGoodsNo(autoGoodsNumber);
		
		modelMap.addAttribute("sellerGoodsWithBLOBs", goods);
		modelMap.addAttribute("brandList", brandList);
		
		List<Category> catTree = categoryService.getDetailCatTree(catAttr);
		StringBuffer buf = new StringBuffer();
		for(Category cat : catTree){
			if(buf.length() > 0){
				buf.append(",");
			}
			buf.append(cat.getCat_name());
		}
		modelMap.addAttribute("catAttrName", buf.toString());
		
		List<CategoryAttr> catAttrList = categoryAttrService.getCategoryAttr(catAttr);
		modelMap.addAttribute("catAttrList", catAttrList);
		
		return "/goods/new";
	}
	
	//商品详情
	@RequestMapping(value="/detail/{goodsId}")
	public String goodsDetail(@PathVariable("goodsId") Integer goodsId,
			@ModelAttribute("brandList") List<Goodsbrand> brandList, 
			String catAttr, ModelMap modelMap){
		SellerGoodsWithBLOBs sellerGoods = sellerGoodsService.detail(goodsId);
		String tempCatAttr = sellerGoods.getCatAttr();		
		//显示重新选择后的商品分类
		if(StringUtils.isNotBlank(catAttr)){
			tempCatAttr = catAttr;
			sellerGoods.setCatAttr(catAttr);
		}
		
		List<Category> catTree = categoryService.getDetailCatTree(tempCatAttr);
		StringBuffer buf = new StringBuffer();
		for(Category cat : catTree){
			if(buf.length() > 0){
				buf.append(",");
			}
			buf.append(cat.getCat_name());
		}
		modelMap.addAttribute("catAttrName", buf.toString());
		
		List<CategoryAttr> catAttrList = categoryAttrService.getCategoryAttr(tempCatAttr);
		modelMap.addAttribute("catAttrList", catAttrList);
		modelMap.addAttribute("brandList", brandList);
		
		//将商品属性值转换为页面可显示的格式
		List<GoodsAttr> goodsAttrList = sellerGoods.getGoodsAttrList();
		int goodsAttrListSize = goodsAttrList == null ? 0 : goodsAttrList.size();
		if(goodsAttrListSize > 0){
			for(CategoryAttr categoryAttr : catAttrList){
				for(int i = 0; i < goodsAttrListSize; i++){
					GoodsAttr goodsAttr = goodsAttrList.get(i);
					if(categoryAttr.getAttrId().equals(goodsAttr.getAttrId())){
						goodsAttr.setAttrType(categoryAttr.getAttrType());
						goodsAttr.setRequired(categoryAttr.getIsMust() == "1");
						break;
					}
				}
			}
			
			for(GoodsAttr goodsAttr : goodsAttrList){
				List<CategoryAttrValue> attrValList = goodsAttr.getAttrValueList();
				if(attrValList == null || attrValList.isEmpty()){
					continue;
				}
				
				//单行文本
				if("0".equals(goodsAttr.getAttrType())){
					CategoryAttrValue catAttrVal = attrValList.get(0);
					String tradeId = catAttrVal.getTradeId();
					if(tradeId == null ){
						continue;
					}
					if(tradeId.endsWith("/")){
						catAttrVal.setTradeId(tradeId.substring(0, tradeId.length() - 1));
					}
					continue;
				}
				
				//单选钮
				if("2".equals(goodsAttr.getAttrType())){
					CategoryAttrValue catAttrVal = attrValList.get(0);
					String tradeId = catAttrVal.getTradeId();
					if(tradeId == null ){
						continue;
					}
					if(tradeId.endsWith("/")){
						tradeId = tradeId.substring(0, tradeId.length() - 1);
					}
					catAttrVal.setTradeId(tradeId);
					continue;
				}

				//复选框
				if("3".equals(goodsAttr.getAttrType())){
					CategoryAttrValue catAttrVal = attrValList.get(0);
					String tradeId = catAttrVal.getTradeId();
					if(tradeId == null ){
						continue;
					}
					
					if(tradeId.endsWith("/")){
						tradeId = tradeId.substring(0, tradeId.length() - 1);
					}
					
					tradeId = tradeId.replaceAll("/", ",");
					catAttrVal.setTradeId(tradeId);
				}
			}
		}
		
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
		return "redirect:/goods/list.action";
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
			@ModelAttribute("brandList") List<Goodsbrand> brandList,
			@ModelAttribute("loginCustId") Integer custId, 
			ModelMap modelMap, RedirectAttributes rAttr){
		boolean isNew = (sellerGoods.getGoodsId() == null);
		
		List<GoodsAttr> goodsAttrList = sellerGoods.getGoodsAttrList();
		if(!goodsAttrList.isEmpty()){
			int i = 0;
			for(GoodsAttr goodsAttr : goodsAttrList){
				if(!goodsAttr.isRequired()){
					i++;
					continue;
				}
				
				if(goodsAttr.getAttrValueList().isEmpty()){
					result.rejectValue("goodsAttrList["+i+"]", "NotEmpty");
				}
				i++;
			}
		}
		
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
			
			List<CategoryAttr> catAttrList = categoryAttrService.getCategoryAttr(sellerGoods.getCatAttr());
			modelMap.addAttribute("catAttrList", catAttrList);
			modelMap.addAttribute("brandList", brandList);
			modelMap.addAttribute("sellerGoodsWithBLOBs", sellerGoods);

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
		return "redirect:/goods/list.action";
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
		pageOper(modelMap, pageResult);
		return "/goods/stock/history";
	}
	
	//商品上架
	@RequestMapping(value="/up/{goodsId}", method=RequestMethod.GET)
	public String goodsUp(@PathVariable("goodsId") Integer goodsId, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoodsWithBLOBs = sellerGoodsService.detailBaseInfo(goodsId);
		sellerGoodsService.changeSaleState(goodsId, true);
		operatePrompt(rAttr, "商品["+sellerGoodsWithBLOBs.getGoodsName()+"]成功上架");
		return "redirect:/goods/list.action?t=2";
	}
	
	//商品下架
	@RequestMapping(value="/down/{goodsId}", method=RequestMethod.GET)
	public String goodsDown(@PathVariable("goodsId") Integer goodsId, RedirectAttributes rAttr){
		SellerGoodsWithBLOBs sellerGoodsWithBLOBs = sellerGoodsService.detailBaseInfo(goodsId);
		sellerGoodsService.changeSaleState(goodsId, false);
		operatePrompt(rAttr, "商品["+sellerGoodsWithBLOBs.getGoodsName()+"]成功下架");
		return "redirect:/goods/list.action?t=2";
	}
	
	@ModelAttribute("loginCustId")
	private Integer getLoginCustId(HttpServletRequest request){
		String session_cust_id = SessionUtil.getString(request, Constants.SESSION_CUST_ID);
		return Integer.parseInt(session_cust_id);
	}
	
	@ModelAttribute("brandList")
	private List<Goodsbrand> getAllBrandList(){
		return null;
		//TODO
		//return goodsbrandService.queryAll();
	}
}
