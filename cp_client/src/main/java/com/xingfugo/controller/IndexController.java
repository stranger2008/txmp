package com.xingfugo.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Advertise;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.AdvertiseService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.common.SessionUtil;

//触屏版首页
@Controller
public class IndexController extends BaseController{

	private final static String SESSION_AERA_ID = "session_area_id";
	
	//0：未审核 1：审核通过 2：审核不通过 3：禁用
	private static final String GOODS_STATUS_ENABLE = "1";
	//0:删除 1:未删除
	private static final String GOODS_DELETED_NO = "1";
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private AdvertiseService advertiseService;
	
	//触屏版首页
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(ModelMap model){
		
		String session_area_id = SessionUtil.getString(getRequest(),SESSION_AERA_ID);
		if(!session_area_id.equals("")){
			return "redirect:"+basePath()+"areaindex-"+session_area_id+".action";
		}
		
		//首页取热门商品，即销量多的商品
		GoodsQueryForm goodsQueryForm = new GoodsQueryForm();
		goodsQueryForm.setInfo_state(GOODS_STATUS_ENABLE);
		goodsQueryForm.setIs_del(GOODS_DELETED_NO);
		goodsQueryForm.getPg().setSortCode("11");
		goodsQueryForm.getPg().setSize(5);
		PageResult pageReuslt = goodsService.getGoodsListByPage(goodsQueryForm);
		model.addAttribute("pageResult", pageReuslt);
		//首页广告获取
		adv(model);
		return "index";
	}
	
	//获取首页各种规格广告，按照广告位获取具体位置广告
	public void adv(ModelMap model){
		//此处的 2 对应数据库中的adv_pos字段，2代表触屏版，1代表app
		List<Advertise> advList = advertiseService.getAllAdvertise("2");
		//遍历map，key名称：advlist+[pos_id]，前台标签展示
		Map<Integer,List<Advertise>> advMap = advertiseService.getAdvMapByPosid(advList);
		Iterator advItr = advMap.keySet().iterator();
		while(advItr.hasNext()){
			Integer pos_id = (Integer)advItr.next();
			List _tempList = advMap.get(pos_id);
			model.addAttribute("advlist"+pos_id, _tempList);
		}
	}
	
}
