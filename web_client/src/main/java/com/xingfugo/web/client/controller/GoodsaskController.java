package com.xingfugo.web.client.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Goodsask;
import com.xingfugo.business.module.query.GoodsaskQueryForm;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.GoodsaskService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.client.common.SessionUtil;

//商品咨询
@Controller
public class GoodsaskController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(GoodsaskController.class.getSimpleName());
	
	@Autowired
	private GoodsaskService goodsaskService;
	
	@Autowired
	private GoodsService goodsService;
	
	//前台添加商品咨询留言
	@RequestMapping(value="goodsaskadd",method=RequestMethod.POST)
	@ResponseBody
	public String goodsaskadd(Goodsask goodsask,String check_code){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		//未登录
		if(user_id.equals("")){
			return "2";
		}
		//留言内容不能为空
		if(StringUtils.isBlank(goodsask.getC_content())){
			return "3";
		}
		//验证码不能为空
		if(StringUtils.isBlank(check_code)){
			return "4";
		}
		String randCheckCode = "";
		if(SessionUtil.get(getRequest(), "randCheckCode") != null){
			randCheckCode = SessionUtil.get(getRequest(), "randCheckCode").toString();
		}
		//验证码输入不正确
		if(!check_code.equalsIgnoreCase(randCheckCode)){
			return "5";
		}
		goodsask.setUser_id(Integer.parseInt(user_id));
		goodsaskService.insertGoodsask(goodsask);
		//评论成功
		return "1";
	}
	
	//用户中心查看商品咨询列表
	@RequestMapping(value="user/goodsasklist",method=RequestMethod.GET)
	public String goodsasklist(GoodsaskQueryForm goodsaskQueryForm,ModelMap model){
		String user_id = SessionUtil.getString(getRequest(),Constants.SESSION_USER_ID);
		goodsaskQueryForm.setUser_id(user_id);
		pageOper(model,goodsaskService.getGoodsaskByPage(goodsaskQueryForm));
		return "user/goodsask/index";
	}
	
	//用户中心删除商品咨询
	@RequestMapping(value="user/gaskdel-{id}",method=RequestMethod.GET)
	public String gaskdel(@PathVariable(value = "id") Integer id,ModelMap model){
		this.goodsaskService.deleteGoodsask(id);
		return "redirect:"+basePath()+"user/goodsasklist.action";
	}
	
	//前台咨询列表
	@RequestMapping(value="gasklist",method=RequestMethod.GET)
	public String gasklist(GoodsaskQueryForm goodsaskQueryForm,ModelMap model){
		Integer goods_id = goodsaskQueryForm.getGoods_id();
		if(goods_id != null){
			pageOper(model,goodsaskService.getGoodsaskByPage(goodsaskQueryForm));
			Map<String, Object> goodsMap = goodsService.getGoodsDetailById(goods_id,"web");
			ImgPathUitls.filterImagePath_spec(goodsMap,"350_350");
			model.addAttribute("goods", goodsMap);
		}
		return "goodsasklist";
	}
	
	//获取前台商品详细页商品咨询总数
	@RequestMapping(value="getGoodsConsultNum-{id}",method=RequestMethod.GET)
	@ResponseBody
	public int getGoodsConsultNum(@PathVariable(value = "id") Integer id,ModelMap model){
		return this.goodsaskService.getGoodsaskByGoodsid(id);
	}
}
