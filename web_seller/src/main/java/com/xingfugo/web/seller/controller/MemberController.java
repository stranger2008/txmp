package com.xingfugo.web.seller.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xingfugo.business.module.Memberlevel;
import com.xingfugo.business.module.Shopconfig;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.GoodsreturnService;
import com.xingfugo.business.service.LevelinfoService;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.business.service.MemberlevelService;
import com.xingfugo.business.service.ShopconfigService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.Md5;
import com.xingfugo.web.seller.common.SessionUtil;
import com.xingfugo.web.seller.module.Login;
import com.xingfugo.web.seller.module.Updatepasswd;

//商家
@Controller
public class MemberController extends BaseController{
	
	//店铺默认级别
	private static final String DEFAULT_MEMBER_LEVEL = "def";
	//订单待发货
	private static final String GOODSORDER_STATE_SEND = "1";
	//订单待退货审核
	private static final String GOODSORDER_STATE_RETURN = "5";
	//商家未删除商品
	private static final String GOODS_DELETED_NO = "1";
	//待审核商品
	private static final String GOODS_STATUS_UNAUDIT = "0";
	//审核不通过商品
	private static final String GOODS_STATUS_REJECT = "2";
	//退货待审核
	private static final String GOODS_RETURN_INFO_STATE_UNAUDIT = "0";
	//申请入驻待审核状态
	private static final String JOIN_US_AUDIT_OK = "1";
	
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private ShopconfigService shopconfigService;
	@Autowired
	private LevelinfoService levelinfoService;
	@Autowired
	private MemberlevelService memberlevelService;
	@Autowired
	private GoodsorderService goodsorderService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsreturnService goodsreturnService;
	
	//密码修改-进入页面
	@RequestMapping(value="member/updatepasswd",method=RequestMethod.GET)
	public String updatepasswd(ModelMap model){
		model.addAttribute(new Updatepasswd());
		return "member/updatepasswd";
	}
	
	//密码修改-执行修改
	@RequestMapping(value="member/updatepasswd",method=RequestMethod.POST)
	public String updatepasswdok(@Valid Updatepasswd updatepasswd,Errors errors,ModelMap model,RedirectAttributes redirectAttributes){
		if (errors.hasErrors()){
	           return "member/updatepasswd";
		}
		String old_passwd = updatepasswd.getOld_passwd();
		String new_passwd = updatepasswd.getNew_passwd();
		String sure_passwd = updatepasswd.getSure_passwd();
		if(new_passwd != null && !new_passwd.equals(sure_passwd)){
			errors.rejectValue("sure_passwd", "member.sure_passwd.nosame", "两次密码输入不一样"); 
		}
		
		String user_name = SessionUtil.getString(getRequest(), Constants.SESSION_USER_NAME);
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		
		Map retMap = memberService.getMemberByUsername(user_name);
		String _passwd = "";
		if(retMap.get("passwd")!=null){
			_passwd = retMap.get("passwd").toString();
		}
		old_passwd = Md5.getMD5(old_passwd.getBytes());
		if(!old_passwd.equals(_passwd)){
			errors.rejectValue("old_passwd", "member.old_passwd.wrong", "输入旧密码不正确"); 
		}
		
		if (errors.hasErrors()){
	           return "member/updatepasswd";
		}
		
		sure_passwd = Md5.getMD5(sure_passwd.getBytes());
		
		this.memberService.updatePasswd(cust_id, sure_passwd);
		
		operatePrompt(redirectAttributes,"修改密码成功");
		
		return "redirect:"+basePath()+"member/updatepasswd.action";
	}
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(ModelMap model){
		SessionUtil.put(getRequest(), Constants.SESSION_CUST_ID, "");
		SessionUtil.put(getRequest(), Constants.SESSION_USER_NAME, "");
		return "redirect:"+basePath()+"login.action";
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(ModelMap model){
		model.addAttribute("login", new Login());
		return "login";
	}
	
	//商家登录
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String loginok(@Valid Login login,Errors errors){
		if (errors.hasErrors()){
	           return "login";
		}
		String user_name = login.getUser_name();
		String passwd = login.getPasswd();
		String check_code = login.getCheck_code();
		Map retMap = memberService.getMemberByUsername(user_name);
		if(retMap == null){
			errors.rejectValue("user_name", "member.user_name.login_username_not_exist", "用户名不存在"); 
		}
		passwd = Md5.getMD5(passwd.getBytes());
		String _passwd = "",cust_id = "";
		if(retMap.get("passwd")!=null){
			_passwd = retMap.get("passwd").toString();
		}
		if(retMap.get("cust_id")!=null){
			cust_id = retMap.get("cust_id").toString();
		}
		if(!passwd.equals(_passwd)){
			//密码不正确
			errors.rejectValue("passwd", "user.passwd.login_passwd_wrong", "密码不正确"); 
		}
		
		String randCheckCode = "";
		if(SessionUtil.get(getRequest(), "randCheckCode") != null){
			randCheckCode = SessionUtil.get(getRequest(), "randCheckCode").toString();
		}
		if(!check_code.equalsIgnoreCase(randCheckCode)){
			errors.rejectValue("check_code", "user.check_code.nosame", "验证码输入不正确"); 
		}
		
		if(!JOIN_US_AUDIT_OK.equals((String)retMap.get("audit_status"))) {
			errors.rejectValue("user_name", "member.user_name.login_username_not_exist", "该用户未通过审核"); 
		}
		
		if (errors.hasErrors()){
	           return "login";
		}
		//查询会员等级
		Map<String, String> map = new HashMap<String, String>();
		map.put("cust_id", cust_id);
		map.put("level_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		List<Map<String, Object>> levelinfos = levelinfoService.getList(map);
		if(levelinfos == null || levelinfos.size() == 0) {
			Memberlevel memberlevel = memberlevelService.getByPk(DEFAULT_MEMBER_LEVEL);
			if(memberlevel != null) {
				SessionUtil.put(getRequest(), Constants.SESSION_CUST_LEVEL_CODE, memberlevel.getLevel_code());
				SessionUtil.put(getRequest(), Constants.SESSION_CUST_LEVEL_NAME, memberlevel.getLevel_name());
			}
		} else {
			SessionUtil.put(getRequest(), Constants.SESSION_CUST_LEVEL_CODE, levelinfos.get(0).get("level_code"));
			SessionUtil.put(getRequest(), Constants.SESSION_CUST_LEVEL_NAME, levelinfos.get(0).get("level_name"));
		}
		
		SessionUtil.put(getRequest(), Constants.SESSION_CUST_ID, cust_id);
		SessionUtil.put(getRequest(), Constants.SESSION_USER_NAME, user_name);
		
		return "redirect:"+basePath()+"member/index.action";
	}
	
	//商家首页
	@RequestMapping(value="member/index",method=RequestMethod.GET)
	public String index(ModelMap model){
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		
		//查询店铺详细信息
		Map<String, String> rmap = new HashMap<String, String>();
		rmap.put("hasAreaName", "1");
		rmap.put("hasMember", "1");
//		rmap.put("hasLevel", "1");
		rmap.put("cust_id", cust_id);
		List<Map<String, Object>> shopconfigs = shopconfigService.selectShopconfigDetail(rmap);
		
		Map<String, Object> shopconfig = new HashMap();
		if(shopconfigs != null && shopconfigs.size()>0){
			shopconfig = shopconfigs.get(0);
			ImgPathUitls.filterImagePath(shopconfig,"shop_logo");
		}
		model.put("shopconfig", shopconfig);
		
		//查询商家待发货订单
		Map<String, String> omap = new HashMap<String, String>();
		omap.put("cust_id", cust_id);
		omap.put("order_states", GOODSORDER_STATE_SEND);
		List<Map<String, Object>> orderCounts = goodsorderService.selectOrderCountGroupByOrderState(omap);
		model.put("orderCounts", orderCounts);
		
		//退货待审核订单
		Map<String, Object> umap = new HashMap<String, Object>();
		umap.put("cust_id", cust_id);
		umap.put("info_states", GOODS_RETURN_INFO_STATE_UNAUDIT);
		List goodsreturnCounts = goodsreturnService.getStateCount(umap);
		model.put("goodsreturnCounts", goodsreturnCounts);
		
		//查询商家待审核和审核不通过的商品
		GoodsQueryForm goodsQueryForm = new GoodsQueryForm();
		goodsQueryForm.setIs_del(GOODS_DELETED_NO);
		goodsQueryForm.setCust_id(Integer.parseInt(cust_id));
		goodsQueryForm.setInfo_states(GOODS_STATUS_UNAUDIT + "," + GOODS_STATUS_REJECT);
		List<Map<String, Object>> goodsCounts = goodsService.selectGoodsCountGroupByInfoState(goodsQueryForm);
		model.put("goodsCounts", goodsCounts);
		

		return "member/index";
	}
	
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
	//进入查看
	@RequestMapping(value="member/sellerInfo",method=RequestMethod.GET)
	public String sellerInfo(ModelMap model) throws Exception {
		String cust_id = SessionUtil.getString(getRequest(), Constants.SESSION_CUST_ID);
		Member member = memberService.selectMemberByIdWithAreaName(cust_id);
		member.setLogo_img(ImgPathUitls.filterImagePath(member.getLogo_img()));
		member.setLic_img(ImgPathUitls.filterImagePath(member.getLic_img()));
		member.setPerson_id_img(ImgPathUitls.filterImagePath(member.getPerson_id_img()));
		member.setOrg_img(ImgPathUitls.filterImagePath(member.getOrg_img()));
		member.setTax_img(ImgPathUitls.filterImagePath(member.getTax_img()));
		member.setBank_img(ImgPathUitls.filterImagePath(member.getBank_img()));
		
		model.addAttribute("member", member);
		return "member/sellerInfo";
	}
	
	//
}
