package com.xingfugo.web.client.controller;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.Advertise;
import com.xingfugo.business.module.UserCart;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.AdvertiseService;
import com.xingfugo.business.service.CartService;
import com.xingfugo.business.service.CollectService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.GoodsaskService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.NavService;
import com.xingfugo.business.service.SysconfigService;
import com.xingfugo.business.service.UserCartService;
import com.xingfugo.business.service.VisitorCartService;
import com.xingfugo.web.client.common.CartCookieUtil;
import com.xingfugo.web.client.common.SessionUtil;

//触屏版首页
@Controller
public class IndexController extends BaseController{

	private final static String SESSION_AERA_ID = "session_area_id";
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private AdvertiseService advertiseService;
	@Autowired
	private SysconfigService sysconfigService;
	
	@Autowired
	private VisitorCartService visitorCartService;
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private CollectService collectService;
	
	private CartService cartService;
	@Autowired
	private NavService navService;
	
	
	//触屏版首页
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(ModelMap model,HttpServletResponse response){
		
		String session_area_id = SessionUtil.getString(getRequest(),SESSION_AERA_ID);
		if(!session_area_id.equals("")){
			return "redirect:"+basePath()+"areaindex-"+session_area_id+".action";
		}
		//首页取热门商品，即销量多的商品
		GoodsQueryForm goodsQueryForm = new GoodsQueryForm();
		goodsQueryForm.getPg().setSortCode("11");
		goodsQueryForm.getPg().setSize(5);
		PageResult pageReuslt = goodsService.getGoodsListByPage_web(goodsQueryForm);
		model.addAttribute("pageResult", pageReuslt);
		//商品列表导航
		List navlist = navService.getallList();
		model.addAttribute("navlist",navlist);
		//首页广告获取
		adv(model);
		return "index";
	}
	
	@RequestMapping(value="/index/getLoginInfo")
	public void getLoginInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Object username = request.getSession().getAttribute("session_user_name");
		String hostName = sysconfigService.getByVarName("cfg_basehost");
		PrintWriter out = response.getWriter();
		if(username!=null){
			out.write("document.write('<a href=\""+hostName+"user/uccenter.action\">"+username+"</a>');");
			out.write("document.write('<a href=\""+hostName+"logout.action\">[退出]</a>');");
		}else{
			out.write("document.write('<a href=\""+hostName+"login.action\">[登录]</a>');");
			out.write("document.write('<a href=\""+hostName+"signup.action\">[注册]</a>');");
		}
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/index/getAreaInfo")
	public void getAreaInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Object area_name = request.getSession().getAttribute("session_selected_area_name");
		PrintWriter out = response.getWriter();
		if(area_name!=null){
			out.write("document.write('"+area_name+"');");
		}else{
			out.write("document.write('城市');");
		}
		out.flush();
		out.close();
	}
	
	//获取首页各种规格广告，按照广告位获取具体位置广告
	public void adv(ModelMap model){
		//此处的 2 对应数据库中的adv_pos字段，2代表触屏版，1代表app
		List<Advertise> advList = advertiseService.getAllAdvertise("2");
		//遍历map，key名称：advlist+[pos_id]，前台标签展示
		Map<Integer,List<Advertise>> advMap = advertiseService.getAdvMapByPosid(advList);
		Iterator advItr = advMap.keySet().iterator();
		while(advItr.hasNext()){
			Integer pos_id = (Integer)advItr.next();
			List _tempList = advMap.get(pos_id);
			model.addAttribute("advlist"+pos_id, _tempList);
		}
	}
	
}
