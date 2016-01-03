package com.xingfugo.web.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Shopconfig;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.MembercatService;
import com.xingfugo.business.service.ShopconfigService;
import com.xingfugo.util.ImgPathUitls;

//商家店铺
@Controller
public class ShopController extends BaseController{
	
	private final static Logger LOG = LoggerFactory.getLogger(ShopController.class.getSimpleName());
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ShopconfigService shopconfigService;
	
	@Autowired
	private MembercatService membercatService;
	
	//店铺首页
	@RequestMapping(value="shop/{id}",method=RequestMethod.GET)
	public String shopindex(@PathVariable(value = "id") Integer id,ModelMap model){
		GoodsQueryForm goodsQueryForm = new GoodsQueryForm();
		goodsQueryForm.setCust_id(id);
		goodsQueryForm.setIs_onsell(true); //上架
		goodsQueryForm.setInfo_state("1");//审核状态
		//默认提取热门商品
		goodsQueryForm.getPg().setSortCode("1");
		setGoodsListAndShopInfo(goodsQueryForm,model);
		//首页商品展示
		setShopGoodsShow(id,model);
		return "shop/index";
	}
	
	//店铺首页-商品展示
	public void setShopGoodsShow(int id,ModelMap model)
	{
		//自定义分类展示
		Map catMap = new HashMap();
		catMap.put("cust_id", id);
		catMap.put("cat_level", 0);
		catMap.put("is_display", 0);
		List<Map<String, Object>> membercatList= membercatService.getList(catMap);
		model.addAttribute("membercatList", membercatList);
		for(Map cat : membercatList){
			String cat_id = (String)cat.get("cat_id");
			if(StringUtils.isBlank(cat_id)){
				continue;
			}
			GoodsQueryForm goodsCatQueryForm = new GoodsQueryForm();
			goodsCatQueryForm.setCust_id(id);
			goodsCatQueryForm.setMembercat((String)cat.get("cat_id"));
			goodsCatQueryForm.setIs_onsell(true); //上架
			goodsCatQueryForm.setInfo_state("1");//审核状态
			goodsCatQueryForm.getPg().setSize(12);
			PageResult shoopGoodsList = goodsService.getGoodsListByPage_web(goodsCatQueryForm);
			cat.put("shoopGoodsList", shoopGoodsList);
		}
	}
	
	//店铺列表页
	@RequestMapping(value="shop/list",method=RequestMethod.GET)
	public String shoplist(GoodsQueryForm goodsQueryForm,ModelMap model){
		if(goodsQueryForm.getCust_id() == null){
			return "404";
		}
		setGoodsListAndShopInfo(goodsQueryForm,model);
		return "shop/list";
	}
	
	public void setGoodsListAndShopInfo(GoodsQueryForm goodsQueryForm,ModelMap model){
		//获取商家商品信息
		pageOper(model,goodsService.getGoodsListByPage_web(goodsQueryForm));
		Integer id = goodsQueryForm.getCust_id();
		//获取商家店铺信息
		Shopconfig shopconfig = shopconfigService.getShopconfig(id);
		
		if(shopconfig!=null){
			//商家LOGO图片url处理
			shopconfig.setShop_logo(ImgPathUitls.filterImagePath(shopconfig.getShop_logo()));
		}
		
		model.addAttribute("shopconfig",shopconfig);
		model.addAttribute("cust_id",id);
		model.addAttribute("goodsQueryForm",goodsQueryForm);
	}
}
