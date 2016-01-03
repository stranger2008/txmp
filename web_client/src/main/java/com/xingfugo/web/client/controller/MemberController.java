package com.xingfugo.web.client.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Member;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.util.Md5;
import com.xingfugo.web.client.common.SessionUtil;

//商家
@Controller
public class MemberController extends BaseController{
	
	//入驻协议
	private static final String JOIN_US_PROTOCOL = "join_us_protocol";
	//入驻协议-同意
	private static final String JOIN_US_PROTOCOL_AGREE = "1";
	//session商家信息
	private static final String JOIN_US_MEMBER_INFO = "join_us_member_info";
	//申请入驻待审核状态
	private static final String JOIN_US_AUDIT_WATING = "0";
	
	@Autowired
	private MemberService memberService;
	
	//商家入驻
	@RequestMapping(value="member/joinus", method=RequestMethod.GET)
	public String joinUs(ModelMap model) {
		
		return "member/joinus";
	}
	
	//入驻协议-------入驻协议
	@RequestMapping(value="member/joinus-step0", method=RequestMethod.GET)
	public String joinUsStep0(ModelMap model) {
		
		return "member/joinus-step0";
	}
	
	//入驻步骤1-------填写联系方式
	@RequestMapping(value="member/joinus-step1", method=RequestMethod.GET)
	public String joinUsStep1(String agree, ModelMap model) {
		//入驻协议
		if(!JOIN_US_PROTOCOL_AGREE.equals(agree)) {
			return "redirect:"+basePath() + "member/joinus-step0.action";
		}
		SessionUtil.put(this.getRequest(), JOIN_US_PROTOCOL, JOIN_US_PROTOCOL_AGREE);
		model.addAttribute("member", new Member());
		return "member/joinus-step1";
	}
	
	//入驻步骤2-------完善商家基本信息
	@RequestMapping(value="member/joinus-step2", method=RequestMethod.POST)
	public String joinUsStep2(@Valid Member member, Errors errors, RedirectAttributes rAttr, ModelMap model) {
		//入驻协议
		Object agree = SessionUtil.get(this.getRequest(), JOIN_US_PROTOCOL);
		if(agree == null || (!JOIN_US_PROTOCOL_AGREE.equals((String) agree))) {
			return "redirect:" + basePath() + "member/joinus-step0.action";
		}
		if(errors.hasFieldErrors("contact_name") || errors.hasFieldErrors("contact_phone") || errors.hasFieldErrors("contact_email")) {
			return "member/joinus-step1";
		}
		int cnt = memberService.isContactNameAndContactPhoneUsed(member);
		if(cnt > 0) {
			errors.rejectValue("contact_name", null, "此联系人和联系电话已被使用过");
			return "member/joinus-step1";
		}
		SessionUtil.put(this.getRequest(), JOIN_US_MEMBER_INFO, member);
		model.addAttribute("member", new Member());
		return "member/joinus-step2";
	}
	
	//入驻步骤3-------提交审核
	@RequestMapping(value="member/joinus-step3", method=RequestMethod.POST)
	public String joinUsStep3(@Valid Member member, Errors errors, RedirectAttributes rAttr, ModelMap model) {
		//入驻协议
		Object agree = SessionUtil.get(this.getRequest(), JOIN_US_PROTOCOL);
		if(agree == null || (!JOIN_US_PROTOCOL_AGREE.equals((String) agree))) {
			return "redirect:" + basePath() + "member/joinus-step0.action";
		}
		//如果没有第一步
		Object member_info = SessionUtil.get(this.getRequest(), JOIN_US_MEMBER_INFO);
		if(member_info == null) {
			return "redirect:"+basePath() + "member/joinus-step1";
		}
		if(member.getPasswd() == null || member.getPasswd().length() == 0) {
			errors.rejectValue("passwd", null, "密码不能为空");
		}
		if(!errors.hasFieldErrors("user_name")) {
			int cnt = memberService.isUsernameExist(member.getUser_name());
			if(cnt > 0) {
				errors.rejectValue("user_name", null, "登录账号已存在");
			}
		}
		if (errors.hasErrors()) {
			if (!(errors.getErrorCount() == 3
					&& errors.hasFieldErrors("contact_name")
					&& errors.hasFieldErrors("contact_phone")
					&& errors.hasFieldErrors("contact_email"))) {
				return "member/joinus-step2";
			}
		}
		member.setContact_name(((Member) member_info).getContact_name());
		member.setContact_phone(((Member) member_info).getContact_phone());
		member.setContact_email(((Member) member_info).getContact_email());
		member.setPasswd(Md5.getMD5(member.getPasswd().getBytes()));
		member.setAudit_status(JOIN_US_AUDIT_WATING);
		memberService.insert(member);
		//删除入驻session信息
		this.getRequest().getSession().removeAttribute(JOIN_US_PROTOCOL);
		this.getRequest().getSession().removeAttribute(JOIN_US_MEMBER_INFO);
		return "member/joinus-step3";
	}
	
	//入驻-------查看入驻进度
	@RequestMapping(value="member/joinus-view", method=RequestMethod.GET)
	public String joinUsViewProgress(ModelMap model) {
		model.addAttribute("member", new Member());
		return "member/joinus-view";
	}
	
	//入驻-------查看入驻进度
	@RequestMapping(value="member/joinus-progress", method=RequestMethod.POST)
	public String joinUsViewProgress(@Valid Member member, Errors errors, RedirectAttributes rAttr, ModelMap model) {
		if(errors.hasFieldErrors("contact_name") || errors.hasFieldErrors("contact_phone")) {
			return "member/joinus-view";
		}
		List<Member> members = memberService.selectJoinUsProgress(member);
		if(members != null && members.size() > 0) {
			member = members.get(0);
		}
		model.addAttribute("member", member);
		return "member/joinus-progress";
	}
}
