package com.xingfugo.web.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Collect;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CollectQueryForm;
import com.xingfugo.business.service.CollectService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.ShopconfigService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.client.common.SessionUtil;

//会员收藏，商品、店铺
@Controller
public class CollectController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(CollectController.class.getSimpleName());
	
	@Autowired
	private CollectService collectService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ShopconfigService shopconfigService;
	
	//商品收藏列表
	@RequestMapping(value="user/collectgoods",method=RequestMethod.GET)
	public String collectgoods(HttpServletRequest request,CollectQueryForm collectQueryForm,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		collectQueryForm.setUser_id(user_id);
		//分页查出收藏信息
		pageOper(model,collectService.getCollectGoodsListByPage(collectQueryForm));
		//猜你喜欢
		List guessList = goodsService.getCollectGoodsOfUserLikeWeGuess();
		
		model.addAttribute("guessList", guessList);
		return "user/collect/goods";
	}
	
	//商家收藏列表
	@RequestMapping(value="user/collectshop",method=RequestMethod.GET)
	public String collectshop(HttpServletRequest request,CollectQueryForm collectQueryForm,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		collectQueryForm.setUser_id(user_id);
		PageResult pageResult = collectService.getCollectShopListByPage(collectQueryForm);
		pageOper(model,pageResult);
		//猜你喜欢店铺
		List guessList = goodsService.getGoodsOfUserLikeWeGuess();
		model.addAttribute("guessList", guessList);
		return "user/collect/shop";
	}
	
	//删除收藏
	@RequestMapping(value="user/collectdel-{id}-{type}",method=RequestMethod.GET)
	public String collectdel(@PathVariable(value = "id") Integer id,@PathVariable(value = "type") Integer type){
		collectService.deleteCollect(id);
		if(type.equals(0)){
			return "redirect:"+basePath()+"user/collectgoods.action";
		}else{
			return "redirect:"+basePath()+"user/collectshop.action";
		}
	}
	
	//批量删除
	@RequestMapping(value="user/delete-{id}-{type}",method=RequestMethod.GET)
	public String delete(@PathVariable(value = "id") String id,@PathVariable(value = "type") Integer type) throws Exception {
		collectService.delete(id);
		if(type.equals(0)){
			return "redirect:"+basePath()+"user/collectgoods.action";
		}else{
			return "redirect:"+basePath()+"user/collectshop.action";
		}
	}
	
	//添加收藏
	@RequestMapping(value="collectadd",method=RequestMethod.GET)
	@ResponseBody
	public String collectadd(HttpServletRequest request,Collect collect,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		//判断是否登录
		if(user_id.equals("")){
			return "3";
		}
		collect.setUser_id(user_id);
		//判断是否已收藏
		if(isExistCollect(user_id,collect.getInfo_id(),collect.getInfo_type())){
			return "2";
		}
		collectService.insertCollect(collect);
		//插入成功
		return "1";
	}
	
	//判断是否已收藏
	public boolean isExistCollect(String user_id,String info_id,String info_type){
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("info_id", info_id);
		map.put("info_type", info_type);
		List collList = collectService.getCollectListByMap(map);
		if(collList != null && collList.size()>0){
			return true;
		}else{
			return false;
		}
	}
}
