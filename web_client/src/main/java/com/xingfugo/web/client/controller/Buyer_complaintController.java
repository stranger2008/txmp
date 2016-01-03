package com.xingfugo.web.client.controller;

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
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.Buyer_complaintQueryForm;
import com.xingfugo.business.service.Buyer_complaintService;
import com.xingfugo.business.service.Buyer_complaint_megService;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.client.common.SessionUtil;

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
	
	private Buyer_complaint_meg buyer_complaint_meg;
	
	//类型
	private static final String COMP_TYPE = "comp_type";
	
	//列表
	@RequestMapping(value="user/buyer_complaint")
	public String list(HttpServletRequest request,Buyer_complaintQueryForm buyer_complaintQueryForm,ModelMap model,String order_id) throws Exception {
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		buyer_complaintQueryForm.setUser_id(user_id);
		if(StringUtils.isNotBlank(order_id)){
			buyer_complaintQueryForm.setOrder_id(order_id);
		}
		PageResult pageResult = buyer_complaintService.getListByPage(buyer_complaintQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("buyer_complaintQueryForm", buyer_complaintQueryForm);
		return "user/buyer_complaint/index";
	}
	
	//进入新增
	@RequestMapping(value="user/add_complaint",method=RequestMethod.GET)
	public String add(String id,ModelMap model) throws Exception {
		if(StringUtils.isBlank(id)){
			return "redirect:"+basePath()+"user/orderlist.action";
		}
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(id);
		if(goodsorder==null){
			return "redirect:"+basePath()+"user/orderlist.action";
		}
		List  complaintList = buyer_complaintService.getComplaintByOrderId(id);
		if(complaintList.size()>0){
			return "redirect:"+basePath()+"user/buyer_complaint.action?order_id="+id;
		}
		//类型
		Map compTypeMap = this.commparaService.getSelectMapByParacode(true,COMP_TYPE);
		model.addAttribute("compTypeMap", compTypeMap);
		
		model.addAttribute("order_id", id);
		Buyer_complaint buyer_complaint = new Buyer_complaint();
		model.addAttribute("buyer_complaint", buyer_complaint);
		return "user/buyer_complaint/add";
	}
	
	//新增
	@RequestMapping(value="user/add_complaint",method=RequestMethod.POST)
	public String insert(@Valid Buyer_complaint buyer_complaint,Errors errors,ModelMap model) throws Exception {
		String order_id  = buyer_complaint.getOrder_id();
		model.addAttribute("order_id", order_id);
		
		//类型
		Map compTypeMap = this.commparaService.getSelectMapByParacode(true,COMP_TYPE);
		model.addAttribute("compTypeMap", compTypeMap);
		
		if (errors.hasErrors()){
			return "user/buyer_complaint/add";
		}
		
		//查找投诉的订单ID是否存在
		List  complaintList = buyer_complaintService.getComplaintByOrderId(order_id);
		if(complaintList.size()>0){
			return "redirect:"+basePath()+"user/buyer_complaint.action?order_id="+order_id;
		}
		
		Goodsorder goodsorder = goodsorderService.getGoodsorderByOrderid(order_id);
		if(goodsorder==null){
			return "redirect:"+basePath()+"user/orderlist.action";
		}
		buyer_complaint.setOrder_id(order_id);
		buyer_complaint.setUser_id(goodsorder.getBuy_cust_id());
		buyer_complaint.setCust_id(goodsorder.getSale_cust_id());
		buyer_complaint.setStatus("0");
		buyer_complaintService.insert(buyer_complaint);
		return "redirect:"+basePath()+"user/buyer_complaint.action";
	}
	
	//投诉，留言管理
	@RequestMapping(value="user/view_complaint-{id}",method=RequestMethod.GET)
	public String view(HttpServletRequest request,@PathVariable(value = "id")String id,ModelMap model,Buyer_complaint_meg buyer_complaint_meg) throws Exception {
		Buyer_complaint buyer_complaint = buyer_complaintService.getByPk(id);
		if(buyer_complaint==null){
			return "redirect:"+basePath()+"user/buyer_complaint.action";
		}
		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		if(!user_id.equals(buyer_complaint.getUser_id())){
			return "redirect:"+basePath()+"user/buyer_complaint.action";
		}
		Member member = memberService.getByPk(buyer_complaint.getCust_id());
		model.addAttribute("cust_name", member.getCust_name());
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
		return "user/buyer_complaint/view";
	}
	
	//发送留言信息
	@RequestMapping(value="user/add_complaint_meg",method=RequestMethod.POST)
	public String addComplaintMeg(@Valid Buyer_complaint_meg buyer_complaint_meg,ModelMap model,Errors errors) throws Exception {
		String com_id = buyer_complaint_meg.getCom_id();
		model.addAttribute("com_id", com_id);
		if (errors.hasErrors()){
			return "redirect:"+basePath()+"user/view_complaint-"+com_id+".action";
		}
		buyer_complaint_meg.setCom_id(com_id);
		buyer_complaint_meg.setU_type("0");
		buyer_complaint_megService.insert(buyer_complaint_meg);
		return "redirect:"+basePath()+"user/view_complaint-"+com_id+".action";
	}
	
	
	//会员 投诉已解决 - 要求运营商介入
	@RequestMapping(value="user/solveAndAppComplaint",method=RequestMethod.GET)
	public String solveAndAppComplaint(Buyer_complaint buyer_complaint,String com_id,String remark) throws Exception {
		if(StringUtils.isBlank(com_id) ||  StringUtils.isBlank(remark)){
			return "redirect:"+basePath()+"user/buyer_complaint.action";
		}
		buyer_complaint = buyer_complaintService.getByPk(com_id);
		if(buyer_complaint == null){
			return "redirect:"+basePath()+"user/buyer_complaint.action";
		}
		//投诉已解决
		if("0".equals(remark)){
			buyer_complaint.setStatus("1");
		}
		//申请运营商介入
		if("1".equals(remark)){
			buyer_complaint.setStatus("2");
		}
		buyer_complaintService.update(buyer_complaint);
		return "redirect:"+basePath()+"user/view_complaint-"+com_id+".action";
	}
	
	//删除
	@RequestMapping(value="buyer_complaint/delete",method=RequestMethod.POST)
	public String delete(String id) throws Exception {
		buyer_complaintService.delete(id);
		return "redirect:"+basePath()+"buyer_complaint/index.action";
	}
	
}

