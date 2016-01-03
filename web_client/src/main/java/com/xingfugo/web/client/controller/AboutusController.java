package com.xingfugo.web.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Aboutus;
import com.xingfugo.business.service.AboutusService;
import com.xingfugo.business.service.CategoryService;

//关于我们读取
@Controller
public class AboutusController extends BaseController{
	private final static Logger LOG = LoggerFactory.getLogger(AboutusController.class.getSimpleName());
	
	@Autowired
	private AboutusService aboutusService;
	@Autowired
	private CategoryService categoryService;
	
	private StringBuffer buff = new StringBuffer();
	
	private static final String HELP_UPCATID = "3738448167";
	
	//查询一条关于我们详细信息
	@RequestMapping(value="aboutus/{id}",method=RequestMethod.GET)
	public String aboutus(@PathVariable(value = "id") String id,ModelMap model){
		List list = categoryService.getCategoryByUpCatId(HELP_UPCATID,"article");
		
		Iterator itr = list.iterator();
		List catidsList = new ArrayList();
		while(itr.hasNext()){
			Map aboutusMap = (Map)itr.next();
			String cat_id = "";
			if(aboutusMap.get("cat_id") != null){
				cat_id = aboutusMap.get("cat_id").toString();
				catidsList.add(cat_id);
			}
		}
		
		List aboutusList = this.aboutusService.getAboutusInfoByCatids(catidsList);
		
		setAboutusInfoToCategory(list,aboutusList);
		
		Aboutus aboutus = aboutusService.getByPk(id);
		model.addAttribute("aboutus", aboutus);
		model.addAttribute("leftList", list);
		
		return "aboutus";
	}
	
	//把文章list中属于分类list中的文章提取出来，放入分类map中
	public void setAboutusInfoToCategory(List catList,List aboutusList){
		if(catList == null || aboutusList == null){
			return;
		}
		Iterator itr = catList.iterator();
		while(itr.hasNext()){
			Map aboutusMap = (Map)itr.next();
			String cat_id = "";
			if(aboutusMap.get("cat_id") != null){
				cat_id = aboutusMap.get("cat_id").toString();
				aboutusMap.put("aboutusList", getAboutusListByCatid(cat_id,aboutusList));
			}
		}
	}
	
	//根据分类ID找出文章中对应的文章list
	public List getAboutusListByCatid(String cat_id,List aboutusList){
		Iterator itr = aboutusList.iterator();
		List sonList = new ArrayList();
		while(itr.hasNext()){
			Map aboutusMap = (Map)itr.next();
			String cat_attr = "";
			if(aboutusMap.get("cat_attr") != null){
				cat_attr = aboutusMap.get("cat_attr").toString();
			}
			if(cat_attr.indexOf(cat_id) > -1){
				sonList.add(aboutusMap);
			}
		}
		return sonList;
	}
	
}
