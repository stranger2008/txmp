package com.xingfugo.web.seller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Buyer_complaint;
import com.xingfugo.business.module.Buyer_complaint_meg;
import com.xingfugo.business.module.Goodsorder;
import com.xingfugo.business.module.Member;
import com.xingfugo.business.module.Memberuser;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.Buyer_complaintQueryForm;
import com.xingfugo.business.service.Buyer_complaintService;
import com.xingfugo.business.service.Buyer_complaint_megService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.business.service.MemberuserService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.seller.common.SessionUtil;

/**
 * @function 功能 投诉记录信息
 * @author 创建人 陈晓艺
 * @date 创建日期 Tue Oct 14 17:58:10 CST 2014
 */
 
@Controller
public class Buyer_complaintController extends BaseController{
	
	@Autowired
	private Buyer_complaintService buyer_complaintService;
	
	@Autowired
	private Buyer_complaint_megService buyer_complaint_megService;
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private CommparaService commparaService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberuserService memberuserService;
	
	private Buyer_complaint_meg buyer_complaint_meg;
	
	//类型
	private static final String COMP_TYPE = "comp_type";
	
	//列表
	@RequestMapping(value="buyer_complaint/list")
	public String list(HttpServletRequest request,Buyer_complaintQueryForm buyer_complaintQueryForm,ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		buyer_complaintQueryForm.setCust_id(cust_id);
		PageResult pageResult = buyer_complaintService.getListByPage(buyer_complaintQueryForm);
		pageOper(model, pageResult);
		//类型
		Map compTypeMap = this.commparaService.getSelectMapByParacode(true,COMP_TYPE);
		model.addAttribute("compTypeMap", compTypeMap);
		
		model.addAttribute("buyer_complaintQueryForm", buyer_complaintQueryForm);
		return "buyer_complaint/index";
	}
	
	
	//投诉，留言管理
	@RequestMapping(value="buyer_complaint/viewComplaint-{id}",method=RequestMethod.GET)
	public String view(HttpServletRequest request,@PathVariable(value = "id")String id,ModelMap model,Buyer_complaint_meg buyer_complaint_meg) throws Exception {
		Buyer_complaint buyer_complaint = buyer_complaintService.getByPk(id);
		if(buyer_complaint==null){
			return "redirect:"+basePath()+"buyer_complaint/list.action";
		}
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		if(!cust_id.equals(buyer_complaint.getCust_id())){
			return "redirect:"+basePath()+"buyer_complaint/list.action";
		}
		Memberuser user = memberuserService.getByPk(buyer_complaint.getUser_id());
		model.addAttribute("user_name", user.getUser_name());
		//类型
		String comp_type = this.commparaService.getParakeyByParacode(COMP_TYPE,buyer_complaint.getCom_type());
		model.addAttribute("comp_type", comp_type);
		//回复留言
		Map userMap = new HashMap();
		userMap.put("com_id", id);
		List  megList = buyer_complaint_megService.getList(userMap);
		model.addAttribute("megList", megList);
		
		String img_evidence = buyer_complaint.getImg_evidence();
		if(StringUtils.isNotBlank(img_evidence)){
			buyer_complaint.setImg_evidence(ImgPathUitls.filterImagePath(img_evidence));
		}
		
		model.addAttribute("com_id", id);
		model.addAttribute("buyer_complaint", buyer_complaint);
		model.addAttribute("buyer_complaint_meg", buyer_complaint_meg);
		return "buyer_complaint/view";
	}
	
	//发送留言信息
	@RequestMapping(value="buyer_complaint/addComplaintMeg",method=RequestMethod.POST)
	public String addComplaintMeg(@Valid Buyer_complaint_meg buyer_complaint_meg,ModelMap model,Errors errors) throws Exception {
		String com_id = buyer_complaint_meg.getCom_id();
		model.addAttribute("com_id", com_id);
		if (errors.hasErrors()){
			return "redirect:"+basePath()+"buyer_complaint/viewComplaint-"+com_id+".action";
		}
		buyer_complaint_meg.setCom_id(com_id);
		buyer_complaint_meg.setU_type("1");
		buyer_complaint_megService.insert(buyer_complaint_meg);
		return "redirect:"+basePath()+"buyer_complaint/viewComplaint-"+com_id+".action";
	}
	
	
	
	//删除
	@RequestMapping(value="buyer_complaint/delete",method=RequestMethod.POST)
	public String delete(String id) throws Exception {
		buyer_complaintService.delete(id);
		return "redirect:"+basePath()+"buyer_complaint/index.action";
	}
	
}

