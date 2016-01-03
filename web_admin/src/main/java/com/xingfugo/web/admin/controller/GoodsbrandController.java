package com.xingfugo.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Goodsbrand;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsbrandQueryForm;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.GoodsbrandService;
import com.xingfugo.web.admin.common.SessionUtil;

/**
 * @function 功能 商品品牌Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 11 19:32:19 CST 2014
 */
 
@Controller
public class GoodsbrandController extends BaseController{
	
	@Autowired
	private GoodsbrandService goodsbrandService;
	
	@Autowired
	private CategoryService categoryService;
	
	//列表
	@RequestMapping(value="goodsbrand/index")
	public String list(GoodsbrandQueryForm goodsbrandQueryForm,ModelMap model) throws Exception {
		String goods_attr_s = goodsbrandQueryForm.getGoods_attr();
		//去除,号结尾的,
		if(goods_attr_s!=null && goods_attr_s.endsWith(","))
		{
			goodsbrandQueryForm.setGoods_attr(goods_attr_s.substring(0, goods_attr_s.length()-1));
		}
		PageResult pageResult = goodsbrandService.getListByPage(goodsbrandQueryForm);
		pageOper(model, pageResult);
		setCatNameList(pageResult);
		if(goods_attr_s!=null && goods_attr_s.endsWith(","))
		{
			goodsbrandQueryForm.setGoods_attr(goods_attr_s);
		}
		model.addAttribute("goodsbrandQueryForm", goodsbrandQueryForm);
		return "goodsbrand/index";
	}
		
	//分类转换为名称显示
	private void setCatNameList(PageResult pageResult){
		List<Map> result = (List<Map>)pageResult.getList();
		if(result==null||result.size()<=0){
			return;
		}
		StringBuilder bf = new StringBuilder();
		for(Map at : result){
			String act_attr_s = (String)at.get("goods_attr");
			if(StringUtils.isBlank(act_attr_s)){
				continue;
			}
			String act_attr = act_attr_s.replace("|",",");
			bf.append(act_attr);
		}
		String cat_ids = bf.substring(0, bf.lastIndexOf(","));
		Map<String,String> catMap = categoryService.getCatMapByIds(cat_ids);
		if(catMap==null||catMap.isEmpty()){
			return;
		}
		for(Map at : result){
			String cat_attr_s = (String)at.get("goods_attr");
			if(StringUtils.isBlank(cat_attr_s)){
				continue;
			}
			String[] catIds = cat_attr_s.split("\\|");
			StringBuilder catNmSb = new StringBuilder();
			for(int i = 0;i<catIds.length;i++){
				String[] ids = catIds[i].split(",");
				for(int j=0;j<ids.length;j++)
				{
					if(ids[j]==null||"".equals(ids[j])){
						continue;
					}
					String name = catMap.get(ids[j]);
					if(name==null||"".equals(name)){
						continue;
					}
					catNmSb.append(catMap.get(ids[j]));
					if(j<ids.length-1){
						catNmSb.append(">");
					}
				}
				if(i<catIds.length-1){
					catNmSb.append("</br>");
				}
			}
			at.put("cat_attr_names", catNmSb.toString());
		}
	}
	
	
	//进入新增
	@RequestMapping(value="goodsbrand/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Goodsbrand goodsbrand = new Goodsbrand();
		goodsbrand.setSort_no("0");
		model.addAttribute("goodsbrand", goodsbrand);
		return "goodsbrand/add";
	}
	
	//新增
	@RequestMapping(value="goodsbrand/add",method=RequestMethod.POST)
	public String insert(@Valid Goodsbrand goodsbrand,Errors errors,RedirectAttributes rAttr,ModelMap model) throws Exception {
		if(goodsbrand == null) {
			 return "goodsbrand/add";
		}
		String brand_name = goodsbrand.getBrand_name();
		if(brand_name != null && !"".equals(brand_name)) {
			Map brandMap = new HashMap();
			brandMap.put("brand_name", brand_name);
			List brandL = goodsbrandService.isBrandNameExist(brandMap);
			if(brandL != null && brandL.size() > 0) {
				errors.rejectValue("brand_name", null, "商品品牌已存在");
			}
		}
		if (errors.hasErrors()){
	           return "goodsbrand/add";
		}
		goodsbrandService.insert(goodsbrand);
		operatePrompt(rAttr, "新增商品品牌成功");
		return "redirect:"+basePath()+"goodsbrand/index.action";
	}
	
	//进入修改
	@RequestMapping(value="goodsbrand/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Goodsbrand goodsbrand = goodsbrandService.getByPk(id);
		if(goodsbrand==null){
			return "redirect:"+basePath()+"goodsbrand/index.action";
		}
		model.addAttribute("goodsbrand", goodsbrand);
		return "goodsbrand/update";
	}
	
	//修改
	@RequestMapping(value="goodsbrand/update",method=RequestMethod.POST)
	public String update(@Valid Goodsbrand goodsbrand,Errors errors,RedirectAttributes rAttr) throws Exception {
		if(goodsbrand == null) {
			return "goodsbrand/update";
		}
		String brand_name = goodsbrand.getBrand_name();//页面填写的品牌名
		String old_brand_name = this.goodsbrandService.getByPk(goodsbrand.getBrand_id()).getBrand_name();//数据库原有的品牌名
		if(brand_name != null && !"".equals(brand_name)) {
			Map brandMap = new HashMap();
			brandMap.put("brand_name", brand_name);
			List brandL = goodsbrandService.isBrandNameExist(brandMap);
			if(brandL != null && brandL.size() > 0 && !brand_name.equals(old_brand_name)) {
				errors.rejectValue("brand_name", null, "商品品牌已存在");
			}
		}
		if (errors.hasErrors()){
	           return "goodsbrand/update";
		}
		goodsbrandService.update(goodsbrand);
		operatePrompt(rAttr, "修改商品品牌成功");
		return "redirect:"+basePath()+"goodsbrand/index.action";
	}
	
	//删除
	@RequestMapping(value="goodsbrand/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		goodsbrandService.delete(id);
		operatePrompt(rAttr, "删除商品品牌成功");
		return "redirect:"+basePath()+"goodsbrand/index.action";
	}
	
	/**
	 * //把品牌所属分类转换成分类名称串
	 */
	@RequestMapping(value="public/setCatAttrName",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String setCatAttrName(String act_attr_s) throws Exception {
		SessionUtil.putString(getRequest(),"act_attr_s",act_attr_s);
		ArrayList<String> act_attr_list = new ArrayList<String>();
		if(StringUtils.isNotBlank(act_attr_s)){
			String act_attr = act_attr_s.replace("|",",");
			String cat_ids = act_attr.substring(0, act_attr.lastIndexOf(","));
			Map<String,String> catMap = categoryService.getCatMapByIds(cat_ids);
			String[] catIds = act_attr_s.split("\\|");
			for(int i = 0;i<catIds.length;i++){
				String[] ids = catIds[i].split(",");
				String cat_attr_name = "";
				for(int j=0;j<ids.length;j++){
					if(ids[j]==null||"".equals(ids[j])){
						continue;
					}
					String name = catMap.get(ids[j]);
					if(name==null||"".equals(name)){
						continue;
					}
					cat_attr_name = cat_attr_name + name;
					if(j<ids.length-1){
						cat_attr_name = cat_attr_name + ">" ;
					}
				}
				if(!"".equals(catIds[i]) && !"".equals(cat_attr_name))
					act_attr_list.add(catIds[i]+"|" + cat_attr_name);
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(act_attr_list);
		return jsonArray.toString();
	}
	//
}

