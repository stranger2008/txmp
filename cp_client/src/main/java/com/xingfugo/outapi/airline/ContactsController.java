package com.xingfugo.outapi.airline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xingfugo.business.outapi.airline.module.Contacts;
import com.xingfugo.business.outapi.airline.service.ContactsService;
import com.xingfugo.business.common.Constants;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.controller.BaseController;

/**
 * @author 联系人信息管理
 *
 */
@Controller
public class ContactsController extends BaseController{
	@Autowired
	private ContactsService contactsService;
	
	/**
	 * @return
	 * 进入 联系人列表页面
	 */
	@RequestMapping(value="contact/contactList",method=RequestMethod.GET)
	public String searchairline(HttpServletRequest request,ModelMap model){
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(StringUtils.isBlank(user_id)){
			return "redirect:"+basePath()+"login.action";
		}
		List<Contacts> list= contactsService.contactsListById(Integer.parseInt(user_id));
		model.addAttribute("contactList",list);
		return "/outapi/airline/airlinecontacts";
	}
	
	/**
	 * @param model
	 * @return
	 * 进入添加 联系人信息页面
	 */
	@RequestMapping(value="contact/toaAddContact",method=RequestMethod.GET)
	public String toaddPassenger(ModelMap model){
		return "/outapi/airline/aielineaddContact";
	}
	/**
	 * @param model
	 * @return
	 * 选择联系人
	 */
	@RequestMapping(value="contact/orderInfo",method=RequestMethod.POST)
	public String orderInfo(String id,HttpServletRequest request,Contacts contacts){
		if(contacts!=null&&contacts.getContact_id()!=null){
			SessionUtil.getSession(request).setAttribute("contacts",contacts);
		}
		return "/outapi/airline/airlineorder";
	}
	
	/**
	 * @param request
	 * @param passenger
	 * @param model
	 * @return
	 * 添加 联系人
	 */
	@RequestMapping(value="contact/addContact",method=RequestMethod.POST)
	public  String addPassenger(HttpServletRequest request,Contacts contacts,ModelMap model){
		contacts.setUser_id(Integer.parseInt(SessionUtil.getString(request,Constants.SESSION_USER_ID)));
		contactsService.insertPassenger(contacts);
		return "redirect:"+basePath()+"contact/contactList.action";
	}
	
	/**
	 * @param id
	 * @param model
	 * 删除联系人
	 */
	@RequestMapping(value="contact/deleteContact",method=RequestMethod.GET)
	public String  deletePassenger(String id,ModelMap model){
		if(id==null){
			return "redirect:"+basePath()+"login.action";
		}
		contactsService.deleteContactsById(id);
		return "redirect:"+basePath()+"contact/contactList.action";
	}
}
