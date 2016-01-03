package com.xingfugo.web.admin.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Advpos;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.AdvposQueryForm;
import com.xingfugo.business.service.AdvinfoService;
import com.xingfugo.business.service.AdvposService;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.web.admin.common.ConstantUtil;

/**
 * @function 功能 广告管理Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 10 16:25:07 CST 2014
 */
 
@Controller
public class AdvposController extends BaseController{
	
	@Autowired
	private AdvposService advposService;
	
	@Autowired
	private CommparaService commparaService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdvinfoService advinfoService;
	
	//列表
	@RequestMapping(value="advpos/index")
	public String list(AdvposQueryForm advposQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = advposService.getListByPage(advposQueryForm);
		pageOper(model, pageResult);
		Map pos_type = commparaService.getSelectMapByParacode(ConstantUtil.ADV_TYPE);
		setShowInfo(pageResult , pos_type);
		model.addAttribute("advposQueryForm", advposQueryForm);
		model.addAttribute("posTypeMap", pos_type);
		return "advpos/index";
	}
	
	//设置列表分类和类型显示的内容
	private void setShowInfo(PageResult pageResult , Map posTypeMap){
		List<Map> result = (List<Map>)pageResult.getList();
		if(result==null||result.size()<=0){
			return;
		}
		StringBuilder bf = new StringBuilder();
		for(Map at : result){
			String act_attr = (String)at.get("cat_attr");
			bf.append(act_attr);
			if(!act_attr.endsWith(",")){
				bf.append(",");
			}
		}
		String cat_ids = bf.substring(0, bf.lastIndexOf(","));
		Map<String,String> catMap = categoryService.getCatMapByIds(cat_ids);
		if(catMap==null||catMap.isEmpty()){
			return;
		}
		for(Map at : result){
			String cat_attr = (String)at.get("cat_attr");
			
			String[] catIds = cat_attr.split(",");
			StringBuilder catNmSb = new StringBuilder();
			for(int i = 0;i<catIds.length;i++){
				String id = catIds[i];
				if(id==null||"".equals(id)){
					continue;
				}
				if(i != 0){
					catNmSb.append(">");
				}
				String name = catMap.get(id);
				if(name==null||"".equals(name)){
					continue;
				}
				catNmSb.append(catMap.get(id));
			}
			
			at.put("cat_attr_names", catNmSb.toString());
			String pos_type = (String)at.get("pos_type");
			at.put("pos_type_name", posTypeMap.get(pos_type));
		}
	}
	//进入新增
	@RequestMapping(value="advpos/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Advpos advpos = new Advpos();
		Map pos_type = commparaService.getSelectMapByParacode(ConstantUtil.ADV_TYPE);
		model.addAttribute("posTypeMap", pos_type);
		model.addAttribute("advpos", advpos);
		return "advpos/add";
	}
	
	//新增
	@RequestMapping(value="advpos/add",method=RequestMethod.POST)
	public String insert(@Valid Advpos advpos,Errors errors,RedirectAttributes rAttr,ModelMap model) throws Exception {
		if (errors.hasErrors()){
			Map pos_type = commparaService.getSelectMapByParacode(ConstantUtil.ADV_TYPE);
			model.addAttribute("posTypeMap", pos_type);
	        return "advpos/add";
		}
		Timestamp in_date = new Timestamp(System.currentTimeMillis());
		advpos.setIn_date(in_date);
		advposService.insert(advpos);
		operatePrompt(rAttr, "新增广告管理成功");
		return "redirect:"+basePath()+"advpos/index.action";
	}
	
	//进入修改
	@RequestMapping(value="advpos/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Advpos advpos = advposService.getByPk(id);
		Map pos_type = commparaService.getSelectMapByParacode(ConstantUtil.ADV_TYPE);
		model.addAttribute("posTypeMap", pos_type);
		model.addAttribute("advpos", advpos);
		return "advpos/update";
	}
	
	//修改
	@RequestMapping(value="advpos/update",method=RequestMethod.POST)
	public String update(@Valid Advpos advpos,Errors errors,RedirectAttributes rAttr ,ModelMap model) throws Exception {
		if (errors.hasErrors()){
			Map pos_type = commparaService.getSelectMapByParacode(ConstantUtil.ADV_TYPE);
			model.addAttribute("posTypeMap", pos_type);
	        return "advpos/update";
		}
		advposService.update(advpos);
		operatePrompt(rAttr, "修改广告管理成功");
		return "redirect:"+basePath()+"advpos/index.action";
	}
	
	//删除
	@RequestMapping(value="advpos/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		advinfoService.delAdvinfosByPosId(id);
		advposService.delete(id);
		operatePrompt(rAttr, "删除广告位成功");
		return "redirect:"+basePath()+"advpos/index.action";
	}
	
}

