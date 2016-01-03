package com.xingfugo.controller;

import java.util.Iterator;
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

import com.xingfugo.business.module.Advertise;
import com.xingfugo.business.module.Area;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.AdvertiseService;
import com.xingfugo.business.service.AreaService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.common.SessionUtil;

//地区管理
@Controller
public class AreaController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(AreaController.class.getSimpleName());
	
	private final static String SESSION_AERA_ID = "session_area_id";
	private final static String SESSION_AERA_NAME = "session_area_name";
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private AdvertiseService advertiseService;
	
	//取出已开通子站的地区，area表 is_open字段值为1
	@RequestMapping(value="areaselect",method=RequestMethod.GET)
	public String areaselect(ModelMap model){
		List areaList = areaService.getOpenWebArea();
		model.addAttribute("areaList", areaList);
		return "areaselect";
	}
	
	//进入某个地区子站获取信息
	@RequestMapping(value="areaindex-{id}",method=RequestMethod.GET)
	public String areaindex(@PathVariable(value = "id") String id,ModelMap model){
		if(StringUtils.isBlank(id)){
			SessionUtil.putString(getRequest(),SESSION_AERA_ID, "");
			SessionUtil.putString(getRequest(),SESSION_AERA_NAME, "");
			return "redirect:"+basePath()+"index.action";
		}
		Area area = this.areaService.getByPk(id);
		
		SessionUtil.putString(getRequest(),SESSION_AERA_ID, id);
		SessionUtil.putString(getRequest(),SESSION_AERA_NAME, area.getArea_name());
		
		
		GoodsQueryForm goodsQueryForm = new GoodsQueryForm();
		goodsQueryForm.setArea_id(id);
		goodsQueryForm.getPg().setSize(4);
		
		//0：热卖 1：精品 2：新品 3： 橱窗推荐  4:热销
		
		//新品上架
		goodsQueryForm.setLabel("2");
		PageResult pageResult1 = goodsService.getGoodsListByPage(goodsQueryForm);
		
		//超级划算
		goodsQueryForm.setLabel("0");
		PageResult pageResult2 = goodsService.getGoodsListByPage(goodsQueryForm);
		
		//持续热卖
		goodsQueryForm.setLabel("1");
		PageResult pageResult3 = goodsService.getGoodsListByPage(goodsQueryForm);
		
		//强力推荐
		goodsQueryForm.setLabel("3");
		PageResult pageResult4 = goodsService.getGoodsListByPage(goodsQueryForm);
		
		model.addAttribute("pageResult1", pageResult1);
		model.addAttribute("pageResult2", pageResult2);
		model.addAttribute("pageResult3", pageResult3);
		model.addAttribute("pageResult4", pageResult4);
		
		adv(model);
		
		return "areaindex";
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
