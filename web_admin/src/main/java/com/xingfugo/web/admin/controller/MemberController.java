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
public class MemberController extends BaseController{
	
	//字符串结尾的逗号(正则表达式
	private static final String THE_COMMA_END_OF_STRING = "(?:,)*$";
	
	/**
	 * 店铺审核通过
	 */
	private static final String MEMBER_AUDIT_PASSED = "1";
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ShopconfigService shopconfigService;
	
	@RequestMapping(value="member/shopconfig",method=RequestMethod.GET)
	public String shopconfig(ModelMap model){
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		Shopconfig shopconfig = shopconfigService.getShopconfig(Integer.parseInt(cust_id));
		if(shopconfig == null){
			shopconfig = new Shopconfig();
		}
		model.addAttribute("shopconfig", shopconfig);
		return "member/shopconfig";
	}
	
	@RequestMapping(value="member/shopconfig",method=RequestMethod.POST)
	public String shopconfigok(@Valid Shopconfig shopconfig,Errors errors,RedirectAttributes redirectAttributes){
		if (errors.hasErrors()){
	           return "member/shopconfig";
		}
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		shopconfig.setCust_id(Integer.parseInt(cust_id));
		this.shopconfigService.save(shopconfig);
		
		this.operatePrompt(redirectAttributes, "商铺资料修改成功");
		
		return "redirect:"+basePath()+"member/shopconfig.action";
	}
	
	//列表
	@RequestMapping(value="member/index")
	public String list(MemberQueryForm memberQueryForm,ModelMap model) throws Exception {
		//去除地区串后面的逗号
		String area_attr = memberQueryForm.getArea_attr() == null ? null :memberQueryForm.getArea_attr().replaceAll(THE_COMMA_END_OF_STRING, "");
		memberQueryForm.setArea_attr(area_attr);
		//查询带有所属地区名
		memberQueryForm.setHas_area_attr_name(true);
		//查询已审核通过的店铺
		memberQueryForm.setAudit_status(MEMBER_AUDIT_PASSED);
		PageResult pageResult = memberService.getListByPage(memberQueryForm);
		pageOper(model, pageResult);
		model.addAttribute("memberQueryForm", memberQueryForm);
		return "member/index";
	}
	
	//进入新增
	@RequestMapping(value="member/add",method=RequestMethod.GET)
	public String add(ModelMap model) throws Exception {
		Member member = new Member();
		model.addAttribute("member", member);
		return "member/add";
	}
	
	//新增
	@RequestMapping(value="member/add",method=RequestMethod.POST)
	public String insert(@Valid Member member,Errors errors,RedirectAttributes rAttr) throws Exception {
		String passwd = member.getPasswd();
		if(passwd == null || passwd.trim().length() ==0 || passwd.length() > 20) {
			errors.rejectValue("passwd", null, "密码不能为空且不能大于20位");
		}
		if (errors.hasErrors()){
			member.setLogo_img(ImgPathUitls.filterImagePath(member.getLogo_img()));
			member.setLic_img(ImgPathUitls.filterImagePath(member.getLic_img()));
			member.setPerson_id_img(ImgPathUitls.filterImagePath(member.getPerson_id_img()));
			member.setOrg_img(ImgPathUitls.filterImagePath(member.getOrg_img()));
			member.setTax_img(ImgPathUitls.filterImagePath(member.getTax_img()));
			member.setBank_img(ImgPathUitls.filterImagePath(member.getBank_img()));
	    	return "member/add";
		}
		member.setPasswd(Md5.getMD5(member.getPasswd().getBytes()));
		member.setAudit_status(MEMBER_AUDIT_PASSED);
		member.setUser_id(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
		member.setNo_reason(null);
		memberService.insertMemberWithShopConfig(member);
		operatePrompt(rAttr, "新增商家信息成功");
		return "redirect:"+basePath()+"member/index.action";
	}
	
	//进入查看
	@RequestMapping(value="member/view",method=RequestMethod.GET)
	public String view(String id,ModelMap model) throws Exception {
		Member member = memberService.selectMemberByIdWithAreaName(id);
		member.setLogo_img(ImgPathUitls.filterImagePath(member.getLogo_img()));
		member.setLic_img(ImgPathUitls.filterImagePath(member.getLic_img()));
		member.setPerson_id_img(ImgPathUitls.filterImagePath(member.getPerson_id_img()));
		member.setOrg_img(ImgPathUitls.filterImagePath(member.getOrg_img()));
		member.setTax_img(ImgPathUitls.filterImagePath(member.getTax_img()));
		member.setBank_img(ImgPathUitls.filterImagePath(member.getBank_img()));
		
		model.addAttribute("member", member);
		return "member/view";
	}
	
	//进入修改
	@RequestMapping(value="member/update",method=RequestMethod.GET)
	public String update(String id,ModelMap model) throws Exception {
		Member member = memberService.getByPk(id);
		model.addAttribute("member", member);
		return "member/update";
	}
	
	//修改
	@RequestMapping(value="member/update",method=RequestMethod.POST)
	public String update(@Valid Member member,Errors errors,RedirectAttributes rAttr) throws Exception {
		if (errors.hasErrors()){
			member.setLogo_img(ImgPathUitls.filterImagePath(member.getLogo_img()));
			member.setLic_img(ImgPathUitls.filterImagePath(member.getLic_img()));
			member.setPerson_id_img(ImgPathUitls.filterImagePath(member.getPerson_id_img()));
			member.setOrg_img(ImgPathUitls.filterImagePath(member.getOrg_img()));
			member.setTax_img(ImgPathUitls.filterImagePath(member.getTax_img()));
			member.setBank_img(ImgPathUitls.filterImagePath(member.getBank_img()));
			
	        return "member/update";
		}
		member.setUser_id(SessionUtil.getString(this.getRequest(), Constants.SESSION_USER_ID));
		memberService.update(member);
		operatePrompt(rAttr, "修改商家信息成功");
		return "redirect:"+basePath()+"member/index.action";
	}
	
	//删除
	@RequestMapping(value="member/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		memberService.deleteMemberWithShopConfigAndLevelinfo(id);
		operatePrompt(rAttr, "删除商家信息成功");
		return "redirect:"+basePath()+"member/index.action";
	}
	
	//密码修改-进入页面
	@RequestMapping(value="member/reset-passwd",method=RequestMethod.GET)
	public String resetPasswd(Member member, ModelMap model){
		model.addAttribute("member", member);
		return "member/resetpasswd";
	}
	
	//密码修改
	@RequestMapping(value="member/reset-passwd",method=RequestMethod.POST)
	public String resetPasswd(Member member, Errors errors, RedirectAttributes rAttr){
		String passwd = member.getPasswd();
		if(passwd == null || passwd.trim().length() == 0 || passwd.trim().length() > 20) {
			errors.rejectValue("passwd", null, "密码不能为空且长度不能多于20位");
		}
		if (errors.hasErrors()){
			return "member/resetpasswd";
		}
		this.memberService.updatePasswd(member.getCust_id(), Md5.getMD5(member.getPasswd().getBytes()));
		operatePrompt(rAttr, "重置商家密码成功");
		return "redirect:"+basePath()+"member/index.action";
	}
	
}

