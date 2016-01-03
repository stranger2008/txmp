package com.xingfugo.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Member;
import com.xingfugo.business.module.Shopconfig;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.MemberQueryForm;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.business.service.ShopconfigService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.Md5;
import com.xingfugo.web.admin.common.SessionUtil;

/**
 * @function 功能 商家信息Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Sat Sep 20 14:01:11 CST 2014
 */
 
@Controller
public class MemberAuditController extends BaseController{
	
	//字符串结尾的逗号(正则表达式
	private static final String THE_COMMA_END_OF_STRING = "(?:,)*$";
	
	//未审核
	private static final String MEMBER_AUDIT_UNAUDIT = "0";
	//审核未通过
	private static final String MEMBER_AUDIT_REJECT = "2";
	
	@Autowired
	private MemberService memberService;
	
	//列表
	@RequestMapping(value="member-audit/index")
	public String list(MemberQueryForm memberQueryForm,ModelMap model) throws Exception {
		//去除地区串后面的逗号
		String area_attr = memberQueryForm.getArea_attr() == null ? null :memberQueryForm.getArea_attr().replaceAll(THE_COMMA_END_OF_STRING, "");
		memberQueryForm.setArea_attr(area_attr);
		//查询带有所属地区名
		memberQueryForm.setHas_area_attr_name(true);
		//查询已审核通过的店铺
		if(memberQueryForm.getAudit_status() == null || memberQueryForm.getAudit_status().trim().length() == 0) {
			memberQueryForm.setAudit_status(MEMBER_AUDIT_UNAUDIT + "," + MEMBER_AUDIT_REJECT);
		}
		PageResult pageResult = memberService.getListByPage(memberQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("memberQueryForm", memberQueryForm);
		return "memberaudit/index";
	}
	
	//进入查看
	@RequestMapping(value="member-audit/view",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Member member = memberService.selectMemberByIdWithAreaName(id);
		member.setLogo_img(ImgPathUitls.filterImagePath(member.getLogo_img()));
		member.setLic_img(ImgPathUitls.filterImagePath(member.getLic_img()));
		member.setPerson_id_img(ImgPathUitls.filterImagePath(member.getPerson_id_img()));
		member.setOrg_img(ImgPathUitls.filterImagePath(member.getOrg_img()));
		member.setTax_img(ImgPathUitls.filterImagePath(member.getTax_img()));
		member.setBank_img(ImgPathUitls.filterImagePath(member.getBank_img()));
		
		model.addAttribute("member", member);
		return "memberaudit/view";
	}
	
	//进入修改
	@RequestMapping(value="member-audit/update",method=RequestMethod.GET)
	public String view1(String id,ModelMap model) throws Exception {
		Member member = memberService.selectMemberByIdWithAreaName(id);
		member.setLogo_img(ImgPathUitls.filterImagePath(member.getLogo_img()));
		member.setLic_img(ImgPathUitls.filterImagePath(member.getLic_img()));
		member.setPerson_id_img(ImgPathUitls.filterImagePath(member.getPerson_id_img()));
		member.setOrg_img(ImgPathUitls.filterImagePath(member.getOrg_img()));
		member.setTax_img(ImgPathUitls.filterImagePath(member.getTax_img()));
		member.setBank_img(ImgPathUitls.filterImagePath(member.getBank_img()));
		
		model.addAttribute("member", member);
		return "memberaudit/update";
	}
	
	//修改
	@RequestMapping(value="member-audit/update",method=RequestMethod.POST)
	public String update(Member member,Errors errors,RedirectAttributes rAttr, ModelMap model) throws Exception {
		String audit_status = member.getAudit_status();
		if(audit_status == null || audit_status.length() != 1) {
			errors.rejectValue("audit_status", null, "审核状态不能为空且长度必须为1");
		}
		
		String no_reason = member.getNo_reason();
		if(MEMBER_AUDIT_REJECT.equals(audit_status)) {
			if(no_reason == null || no_reason.trim().length() == 0 || no_reason.length() > 200) {
				errors.rejectValue("no_reason", null, "原因不能为空,且长度不能大于200");
			}
		}
		if (errors.hasErrors()){
			member = memberService.selectMemberByIdWithAreaName(member.getCust_id());
			member.setLogo_img(ImgPathUitls.filterImagePath(member.getLogo_img()));
			member.setLic_img(ImgPathUitls.filterImagePath(member.getLic_img()));
			member.setPerson_id_img(ImgPathUitls.filterImagePath(member.getPerson_id_img()));
			member.setOrg_img(ImgPathUitls.filterImagePath(member.getOrg_img()));
			member.setTax_img(ImgPathUitls.filterImagePath(member.getTax_img()));
			member.setBank_img(ImgPathUitls.filterImagePath(member.getBank_img()));
			
			member.setAudit_status(audit_status);
			member.setNo_reason(no_reason);
			
			model.put("member", member);
			return "memberaudit/update";
		}
		
		member.setUser_id(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
		memberService.auditMember(member);
		operatePrompt(rAttr, "审核商家信息成功");
		return "redirect:"+basePath()+"member-audit/index.action";
	}
	
	//删除
	@RequestMapping(value="member-audit/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		memberService.delete(id);
		operatePrompt(rAttr, "删除商家信息成功");
		return "redirect:"+basePath()+"member-audit/index.action";
	}
	
}

