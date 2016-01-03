package com.xingfugo.web.seller.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.xingfugo.business.module.Membercat;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.MembercatQueryForm;
import com.xingfugo.business.service.MembercatService;
import com.xingfugo.util.RandomStrUtil;
import com.xingfugo.web.seller.common.SessionUtil;

/**
 * @function 功能 商家自定义分类Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Sat Sep 27 16:03:05 CST 2014
 */
 
@Controller
public class MembercatController extends BaseController{
	
	@Autowired
	private MembercatService membercatService;
	
	//列表
	@RequestMapping(value="membercat/index")
	public String list(MembercatQueryForm membercatQueryForm,ModelMap model) throws Exception {
		PageResult pageResult = membercatService.getListByPage(membercatQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("membercatQueryForm", membercatQueryForm);
		return "membercat/index";
	}
	
	//进入新增
	@RequestMapping(value="membercat/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Membercat membercat = new Membercat();
		model.addAttribute("membercat", membercat);
		return "membercat/add";
	}
	
	//新增
	@RequestMapping(value="membercat/add",method=RequestMethod.POST)
	public String insert(@Valid Membercat membercat,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "membercat/add";
		}
		membercatService.insert(membercat);
		operatePrompt(rAttr, "新增商家自定义分类成功");
		return "redirect:"+basePath()+"membercat/index.action";
	}
	
	//进入修改
	@RequestMapping(value="membercat/update",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Membercat membercat = membercatService.getByPk(id);
		model.addAttribute("membercat", membercat);
		return "membercat/update";
	}
	
	//修改
	@RequestMapping(value="membercat/update",method=RequestMethod.POST)
	public String update(@Valid Membercat membercat,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
	           return "membercat/update";
		}
		membercatService.update(membercat);
		operatePrompt(rAttr, "修改商家自定义分类成功");
		return "redirect:"+basePath()+"membercat/index.action";
	}
	
	//分类显示隐藏
	@RequestMapping(value="membercat/shoHiddenCat",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void shoHiddenCat(String cat_id,String is_display) throws Exception {
		if(StringUtils.isNotBlank(cat_id) && StringUtils.isNotBlank(is_display)){
			Membercat membercat = membercatService.getByPk(cat_id);
			if("0".equals(is_display))
				is_display = "1" ;
			else
				is_display = "0" ;
			membercat.setIs_display(is_display);
			membercatService.update(membercat);
		}
	}
	
	
	//删除
	@RequestMapping(value="membercat/delete",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void delete(String cat_id) throws Exception {
		membercatService.delete(cat_id);
	}
	
	

	//自定义分类  增加、更新
	@RequestMapping(value="membercat/addUpdateMembercat",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUpdateMembercat(HttpServletRequest request,String cat) throws Exception {
		cat = URLDecoder.decode(cat,"utf-8");
		String cust_id = SessionUtil.getString(request,Constants.SESSION_CUST_ID);
		if(StringUtils.isNotBlank(cat)){
			if(cat.endsWith("|"))
			{
				cat = cat.substring(0, cat.lastIndexOf("|"));
			}
			String[] cat_o = cat.split("\\|");
			for(int i = 0;i<cat_o.length;i++){
				String cat_id_s = "";
				String[] cat_t = cat_o[i].split("-");
				for(int j=0;j<cat_t.length;j++){
					String[] cat_s = cat_t[j].split(",");
					if("0".equals(cat_s[0].toString().trim())){
						Membercat membercat = new Membercat();
						if(j==0)
						{
							cat_id_s = RandomStrUtil.getNumberRand();
							Membercat membercat_mark = membercatService.getByPk(cat_id_s);
							while(membercat_mark!=null)
							{	 
								 String cat_id_s_s  = RandomStrUtil.getNumberRand();
								 membercat_mark = membercatService.getByPk(cat_id_s_s);
								 cat_id_s = cat_id_s_s;
							}
							membercat.setCat_id(cat_id_s);
							membercat.setUp_cat_id(Constants.UP_INFO_ID); //等级为1时，为1111111111
							membercat.setCat_level("0");
						}
						else
						{
							membercat.setCat_id(RandomStrUtil.getNumberRand());
							membercat.setUp_cat_id(cat_id_s);
							membercat.setCat_level("1");
						}
						membercat.setCat_name(cat_s[1]);
						membercat.setCust_id(cust_id);
						membercat.setIs_display(cat_s[2]);
						membercat.setSort_no(cat_s[3]);
						membercatService.insert(membercat);
					}
					else{
						Membercat membercat = membercatService.getByPk(cat_s[0]);
						if(j==0)
							cat_id_s = membercat.getCat_id();
						membercat.setCat_name(cat_s[1]);
						membercat.setIs_display(cat_s[2]);
						membercat.setSort_no(cat_s[3]);
						membercatService.update(membercat);
					}
				}
			}
		}
		return "1";
	}
	
	
	@RequestMapping(value="public/membercat/getMembercatList",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getMembercatList(HttpServletRequest request) throws Exception {
		String cust_id = SessionUtil.getString(request,Constants.SESSION_CUST_ID);
		Map catMap = new HashMap();
		catMap.put("cust_id", cust_id);
		catMap.put("cat_level", 0);
		Map catMap_s = new HashMap();
		catMap_s.put("cust_id", cust_id);
		catMap_s.put("cat_level", 1);
		List<Map<String, Object>> list = membercatService.getList(catMap);
		List<Map<String, Object>> list_s = membercatService.getList(catMap_s);
		String memberCat="";
		for(Map cat : list){
			String cat_id = (String)cat.get("cat_id");
			String cat_name = (String)cat.get("cat_name");
			String is_display = cat.get("is_display").toString();
			String sort_no = cat.get("sort_no").toString();
			String in_date = cat.get("in_date").toString().substring(0, 10);
			memberCat = memberCat+cat_id+","+cat_name+","+is_display+","+sort_no+","+in_date+">";
			for(Map cat_s : list_s){
				String up_cat_id = (String)cat_s.get("up_cat_id");
				if(cat_id!=null && up_cat_id !=null && cat_id.equals(up_cat_id))
				{
					String cat_id_s = (String)cat_s.get("cat_id");
					String cat_name_s = (String)cat_s.get("cat_name");
					String is_display_s = cat_s.get("is_display").toString();
					String sort_no_s = cat_s.get("sort_no").toString();
					String in_date_s = cat_s.get("in_date").toString().substring(0, 10);
					memberCat = memberCat+cat_id_s+","+cat_name_s+","+is_display_s+","+sort_no_s+","+in_date_s+">";
				}
			}
			memberCat = memberCat+"|";
		}
		return memberCat;
	}
	
	
	
	
	
	
	
	
}

