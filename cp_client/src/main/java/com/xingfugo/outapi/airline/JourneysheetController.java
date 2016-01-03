package com.xingfugo.outapi.airline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.outapi.airline.module.JourneySheet;
import com.xingfugo.business.outapi.airline.service.JourneysheetService;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.controller.BaseController;

/**
 * @author wyl
 *行程单管理
 */
@Controller
public class JourneysheetController extends BaseController{
	
	@Autowired
	private JourneysheetService journeysheetService;
	
	/**
	 * 进入行程单页面
	 */
	@RequestMapping(value="journeysheet/toJourneysheet",method=RequestMethod.GET)
	public String toJourneysheet(){
		return "/outapi/airline/airlinejourneySheet";
	}
	
	/**
	 * 进入行程单添加页面
	 */
	@RequestMapping(value="journeysheet/toAddJourneysheet",method=RequestMethod.GET)
	public String toAddJourneysheet(HttpServletRequest request,ModelMap model){
		if(StringUtils.isBlank(SessionUtil.getString(request,Constants.SESSION_USER_ID))){
			return "redirect:"+basePath()+"login.action";
		}
		List<JourneySheet> js=journeysheetService.getJourneySheetById(SessionUtil.getString(request,Constants.SESSION_USER_ID));
		JourneySheet jSheet=null;
		if(js!=null){
			jSheet=js.get(0);
		}
		SessionUtil.getSession(request).setAttribute("JourneySheet",jSheet);
		model.addAttribute("js",js);
		return "/outapi/airline/airlineaddJourneySheet";
	}

	/**
	 * 添加行程单
	 */
	@RequestMapping(value="journeysheet/insertJourneysheet",method=RequestMethod.POST)
	public String insertJourneysheet(HttpServletRequest request,JourneySheet journeySheet,ModelMap model){
		if(journeySheet.getJourneysheet_id()!=null){
			journeysheetService.updateJourneySheet(journeySheet);
		}else{
			 journeySheet.setUser_id(Integer.parseInt(SessionUtil.getString(request,Constants.SESSION_USER_ID)));
			 Integer id=journeysheetService.insertJourneySheet(journeySheet);
			 journeySheet.setJourneysheet_id(id);
		}
		SessionUtil.getSession(request).setAttribute("JourneySheet",journeySheet);
		SessionUtil.getSession(request).setAttribute("jsheet",1);
		return "/outapi/airline/airlineorder";
	}
	
	/**
	 * @param model
	 * @return
	 * 选择联系人
	 */
	@RequestMapping(value="journeysheet/orderInfo",method=RequestMethod.GET)
	public String orderInfo(String jsheet,HttpServletRequest request){
		if(StringUtils.isNotBlank(jsheet)){
			SessionUtil.getSession(request).setAttribute("jsheet",jsheet);
		}
		return "/outapi/airline/airlineorder";
	}
}
