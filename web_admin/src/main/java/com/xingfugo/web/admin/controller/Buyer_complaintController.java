package com.xingfugo.web.admin.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Buyer_complaint;
import com.xingfugo.business.module.Buyer_complaint_meg;
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
	private CommparaService commparaService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberuserService memberuserService;
	
	
	//类型
	private static final String COMP_TYPE = "comp_type";
	
	//列表
	@RequestMapping(value="buyer_complaint/index")
	public String list(Buyer_complaintQueryForm buyer_complaintQueryForm,ModelMap model) throws Exception {
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
	public String view(@PathVariable(value = "id")String id,ModelMap model,Buyer_complaint buyer_complaint_s) throws Exception {
		Buyer_complaint buyer_complaint = buyer_complaintService.getByPk(id);
		if(buyer_complaint==null){
			return "redirect:"+basePath()+"buyer_complaint/index.action";
		}
		Memberuser user = memberuserService.getByPk(buyer_complaint.getUser_id());
		String user_name="",cust_name="";
		if(user != null){
			user_name = user.getUser_name();
		}
		model.addAttribute("user_name", user_name);
		Member member = memberService.getByPk(buyer_complaint.getCust_id());
		if(member != null){
			cust_name = member.getCust_name();
		}
		model.addAttribute("cust_name", cust_name);
		
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
		
		model.addAttribute("buyer_complaint", buyer_complaint);
		model.addAttribute("buyer_complaint_s", buyer_complaint_s);
		return "buyer_complaint/view";
	}
	
	//运营商提交处理信息
	@RequestMapping(value="buyer_complaint/addComplaint",method=RequestMethod.POST)
	public String addComplaintMeg(Buyer_complaint buyer_complaint,ModelMap model,Errors errors,RedirectAttributes rAttr) throws Exception {
		String com_id = buyer_complaint.getComplaint_id();
		if(StringUtils.isBlank(com_id)){
			operatePrompt(rAttr, "运营商处理信息提交失败");
			return "redirect:"+basePath()+"buyer_complaint/index.action";
		}
		Buyer_complaint buyer_complaint_s = buyer_complaintService.getByPk(com_id);
		if(buyer_complaint_s == null){
			operatePrompt(rAttr, "运营商处理信息提交失败");
			return "redirect:"+basePath()+"buyer_complaint/index.action";
		}
		buyer_complaint_s.setRemark(buyer_complaint.getRemark());
		buyer_complaintService.update(buyer_complaint_s);
		operatePrompt(rAttr, "运营商处理信息已提交");
		return "redirect:"+basePath()+"buyer_complaint/viewComplaint-"+com_id+".action";
	}
	
	
	//会员 投诉已解决
	@RequestMapping(value="buyer_complaint/solveComplaint",method=RequestMethod.GET)
	public String solveAndAppComplaint(Buyer_complaint buyer_complaint,String com_id,RedirectAttributes rAttr) throws Exception {
		if(StringUtils.isBlank(com_id)){
			operatePrompt(rAttr, "运营商处理信息提交失败");
			return "redirect:"+basePath()+"buyer_complaint/viewComplaint-"+com_id+".action";
		}
		buyer_complaint = buyer_complaintService.getByPk(com_id);
		if(buyer_complaint == null){
			return "redirect:"+basePath()+"buyer_complaint/index.action";
		}
		//投诉已解决
		buyer_complaint.setStatus("1");
		buyer_complaintService.update(buyer_complaint);
		return "redirect:"+basePath()+"buyer_complaint/viewComplaint-"+com_id+".action";
	}
	
	
	//删除
	@RequestMapping(value="buyer_complaint/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		buyer_complaintService.delete(id);
		buyer_complaint_megService.deleteComplaintMeg(id);
		operatePrompt(rAttr, "删除投诉记录信息成功");
		return "redirect:"+basePath()+"buyer_complaint/index.action";
	}
	
	
}

