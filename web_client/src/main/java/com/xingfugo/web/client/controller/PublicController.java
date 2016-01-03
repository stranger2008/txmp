package com.xingfugo.web.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.service.AreaService;
import com.xingfugo.business.service.CategoryService;

//公共业务
@Controller
public class PublicController extends BaseController{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 地区业务层接口
	 */
	@Autowired
	private AreaService areaService;
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 方法描述：select级联功能
	 * 目前支持地区、分类级联
	 * js包在/inc/com/selectCascade/selectlink.js这个位置
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="showSelectLink",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getShowSelectLink(String model_name,String up_model_id) throws Exception {
		String resutlStr = "";
		if(model_name.equals("area") && !up_model_id.trim().equals(""))
		{
			List list = areaService.getAreaListByUpareaid(up_model_id);
			resutlStr = getOptionStrByList(list,"area_id","area_name");
		}
		else if(model_name.equals("category") && !up_model_id.trim().equals(""))
		{
			List list = categoryService.getCategoryByUpCatId(up_model_id);
			resutlStr = getOptionStrByList(list,"cat_id","cat_name");
		}
		return resutlStr;
	}
	
	public String getOptionStrByList(List list,String key,String value){
		StringBuffer resutlStr = new StringBuffer();
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				Map tempMap = (HashMap)list.get(i);
				String _key = "",_value = "";
				if(tempMap.get(key) != null){
					_key = tempMap.get(key).toString();
				}
				if(tempMap.get(value) != null){
					_value = tempMap.get(value).toString();
				}
				resutlStr.append("<option value='"+_key+"'>"+_value+"</option>");
			}
		}
		return resutlStr.toString();
	}
}
