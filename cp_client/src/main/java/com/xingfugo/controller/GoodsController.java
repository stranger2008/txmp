package com.xingfugo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.util.ImgPathUitls;

//商品管理
@Controller
public class GoodsController extends BaseController{
	
	private final static String SESSION_AERA_ID = "session_area_id";
	
	private final static String SOURCE ="cp";
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CategoryService categoryService;
	//商品未删除
	private static final String GOODS_NO_DEL = "1";
	//商品状态：正常
	private static final String GOODS_INFO_STATE_OK = "1";
	
	//商品列表
	@RequestMapping(value="goodslist",method=RequestMethod.GET)
	public String goodslist(GoodsQueryForm goodsQueryForm,ModelMap model){
		
		//选子站的时候放入了session
		//如果不为空说明取了子站，默认商品列表页按照地区过滤
		String session_area_id = SessionUtil.getString(getRequest(),SESSION_AERA_ID);
		if(!session_area_id.equals("")){
			goodsQueryForm.setArea_id(session_area_id);
		}
		
		//找出面包屑分类位置
		String cat_id = goodsQueryForm.getCat_id();
		if(!StringUtils.isBlank(cat_id)){
			List posTreeList = new ArrayList();
			categoryService.setCatTreeByCatid(posTreeList,cat_id);
			model.addAttribute("posTreeList", posTreeList);
		}
		//商品列表
		goodsQueryForm.setIs_onsell(true);
		goodsQueryForm.setIs_del(GOODS_NO_DEL);
		goodsQueryForm.setInfo_state(GOODS_INFO_STATE_OK);
		pageOper(model,goodsService.getGoodsListByPage(goodsQueryForm));
		model.addAttribute("g",goodsQueryForm);
		
		return "goodslist";
	}
	
	//商品详细
	@RequestMapping(value="goods/{id}",method=RequestMethod.GET)
	public String goodsdetail(@PathVariable(value = "id") Integer id,ModelMap model){
		
		Map<String, Object> goodsMap = goodsService.getGoodsDetailById(id,SOURCE);
		ImgPathUitls.filterImagePath(goodsMap);
		model.addAttribute("goods", goodsMap);
		if(!goodsMap.isEmpty()){
			List attrsList =(List)goodsMap.get("attrs");
			model.addAttribute("goodsAttrs", attrsList);
		}
		
		
		//面包屑网页位置，根据分类ID串获取分类名称串List
		if(goodsMap!=null && goodsMap.get("cat_attr")!=null && !goodsMap.get("cat_attr").toString().equals("")){
			String cat_attr = goodsMap.get("cat_attr").toString();
			List posTreeList = categoryService.getDetailCatTree(cat_attr);
			model.addAttribute("posTreeList", posTreeList);
		}else{
			return "404";
		}
		
		//猜你喜欢
		List guessList = goodsService.getGoodsOfUserLikeWeGuess();
		model.addAttribute("guessList", guessList);
		
		return "goodsdetail";
	}
	
}
