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

import com.xingfugo.business.module.Aboutus;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.AboutusQueryForm;
import com.xingfugo.business.service.AboutusService;
import com.xingfugo.business.service.CategoryService;

/**
 * @function 功能 文章管理Service层业务实现
 * @author 创建人 刘香玲
 * @date 创建日期 Thu Sep 04 17:05:56 CST 2014
 */
 
@Controller
public class AboutusController extends BaseController{
	
	@Autowired
	private AboutusService aboutusService;
	
	@Autowired
	private CategoryService categoryService;
	
	//列表
	@RequestMapping(value="aboutus/index")
	public String list(AboutusQueryForm aboutusQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = aboutusService.getListByPage(aboutusQueryForm);
		pageOper(model, pageResult);
		setCatName(pageResult);
		model.addAttribute("aboutusQueryForm", aboutusQueryForm);
		return "aboutus/index";
	}
	//分类转换为名称显示
	private void setCatName(PageResult pageResult){
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
		}
	}
	
	//进入新增
	@RequestMapping(value="aboutus/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Aboutus aboutus = new Aboutus();
		model.addAttribute("aboutus", aboutus);
		return "aboutus/add";
	}
	
	//新增
	@RequestMapping(value="aboutus/add",method=RequestMethod.POST)
	public String insert(@Valid Aboutus aboutus,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "aboutus/add";
		}
		Timestamp dt = new Timestamp(System.currentTimeMillis());
		aboutus.setIn_date(dt);
		aboutusService.insert(aboutus);
		operatePrompt(rAttr, "新增文章管理成功");
		return "redirect:"+basePath()+"aboutus/index.action";
	}
	
	//进入修改
	@RequestMapping(value="aboutus/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Aboutus aboutus = aboutusService.getByPk(id);
		model.addAttribute("aboutus", aboutus);
		return "aboutus/update";
	}
	
	//修改
	@RequestMapping(value="aboutus/update",method=RequestMethod.POST)
	public String update(@Valid Aboutus aboutus,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "aboutus/update";
		}
//		Timestamp dt = new Timestamp(System.currentTimeMillis());
//		aboutus.setIn_date(dt);
		aboutusService.update(aboutus);
		operatePrompt(rAttr, "修改文章管理成功");
		return "redirect:"+basePath()+"aboutus/index.action";
	}
	
	//删除
	@RequestMapping(value="aboutus/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		aboutusService.delete(id);
		operatePrompt(rAttr, "删除文章管理成功");
		return "redirect:"+basePath()+"aboutus/index.action";
	}
	
}

