package com.xingfugo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Shopconfig;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.GoodsService;
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
	
	//商品未删除
	private static final String GOODS_NO_DEL = "1";
	//商品状态：正常
	private static final String GOODS_INFO_STATE_OK = "1";
	
	//店铺首页
	@RequestMapping(value="shop/{id}",method=RequestMethod.GET)
	public String shopindex(@PathVariable(value = "id") Integer id,ModelMap model){
		GoodsQueryForm goodsQueryForm = new GoodsQueryForm();
		goodsQueryForm.setCust_id(id);
		//默认提取热门商品
		goodsQueryForm.getPg().setSortCode("1");
		setGoodsListAndShopInfo(goodsQueryForm,model);
		return "shop/index";
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
		goodsQueryForm.setIs_del(GOODS_NO_DEL);
		goodsQueryForm.setInfo_state(GOODS_INFO_STATE_OK);
		pageOper(model,goodsService.getGoodsListByPage(goodsQueryForm));
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
