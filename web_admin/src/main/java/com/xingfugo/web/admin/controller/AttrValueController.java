package com.xingfugo.web.admin.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.AttrValue;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CategoryAttrQueryForm;
import com.xingfugo.business.module.query.CategoryQueryForm;
import com.xingfugo.business.service.AttrValueService;
import com.xingfugo.business.service.CategoryAttrService;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.InfoAttrService;
import com.xingfugo.util.RandomStrUtil;

/**
 * @function 功能 分类属性Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Mon Sep 15 17:23:41 CST 2014
 */
 
@Controller
public class AttrValueController extends BaseController{
	
	@Autowired
	private AttrValueService attrValueService;
	
	//新增
	@RequestMapping(value="attrvalue/insert",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String insert(AttrValue attrValue) throws Exception {
		//结果
		Map<String, Object> map = new HashMap<String, Object>();
		
		String attr_id = attrValue.getAttr_id();
		if(attr_id == null || attr_id.trim().length() == 0) {
			map.put("isSuccess", false);
			map.put("info", "无法取得所属属性,请刷新后再试");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		String attr_value = attrValue.getAttr_value();
		if(attr_value == null || attr_value.trim().length() == 0 || attr_value.length() > 600) {
			map.put("isSuccess", false);
			String info = "属性值不能为空,且长度不能大于600";
			map.put("info", info);
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		attrValue.setTrade_id(RandomStrUtil.getNumberRand());
		attrValueService.insert(attrValue);
		map.put("isSuccess", true);
		map.put("info", "新增属性值成功");
		
		Map<String, String> rmap = new HashMap<String, String>();
		rmap.put("attr_id", attr_id);
		List attrValues = attrValueService.getList(rmap);
		map.put("attrValues", attrValues);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
	
	//查询
	@RequestMapping(value="attrvalue/selectbyid",method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String selectById(String trade_id) throws Exception {
		//结果
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(trade_id == null || trade_id.trim().length() == 0) {
			map.put("isSuccess", false);
			map.put("info", "无法取得属性值,请刷新后重试");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		AttrValue attrValue = attrValueService.selectById(trade_id);
		if(attrValue == null) {
			map.put("isSuccess", false);
			map.put("info", "无法取得属性值,请刷新后重试");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		map.put("isSuccess", true);
		map.put("attrValue", attrValue);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
	
	//修改
	@RequestMapping(value="attrvalue/update",method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String update(AttrValue attrValue) throws Exception {
		//结果
		Map<String, Object> map = new HashMap<String, Object>();
		
		String trade_id = attrValue.getTrade_id();
		if(trade_id == null || trade_id.trim().length() == 0) {
			map.put("isSuccess", false);
			map.put("info", "无法取得属性值,请刷新后再试");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		String attr_value = attrValue.getAttr_value();
		if(attr_value == null || attr_value.trim().length() == 0 || attr_value.length() > 600) {
			map.put("isSuccess", false);
			String info = "属性值不能为空,且长度不能大于600";
			map.put("info", info);
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		attrValueService.update(attrValue);
		map.put("isSuccess", true);
		map.put("info", "修改属性值成功");
		
		AttrValue attrValue1 = attrValueService.selectById(trade_id);
		
		Map<String, String> rmap = new HashMap<String, String>();
		rmap.put("attr_id", attrValue1.getAttr_id());
		List attrValues = attrValueService.getList(rmap);
		map.put("attrValues", attrValues);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
	
	//删除
	@RequestMapping(value="attrvalue/delete",method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String delete(String trade_id) throws Exception {
		//结果
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(trade_id == null || trade_id.trim().length() == 0) {
			map.put("isSuccess", false);
			map.put("info", "无法取得属性值,请刷新后再试");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		
		AttrValue attrValue = attrValueService.selectById(trade_id);
		if(attrValue == null) {
			map.put("isSuccess", false);
			map.put("info", "无法取得属性值,请刷新后再试");
			JSONObject jsonObject = JSONObject.fromObject(map);
			return jsonObject.toString();
		}
		
		attrValueService.delete(trade_id);
		
		map.put("isSuccess", true);
		map.put("info", "删除属性值成功");
		
		Map<String, String> rmap = new HashMap<String, String>();
		rmap.put("attr_id", attrValue.getAttr_id());
		List attrValues = attrValueService.getList(rmap);
		map.put("attrValues", attrValues);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}
	
}
